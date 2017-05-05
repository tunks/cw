/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support.solr;

import com.att.cw.model.Searchable;
import com.att.cw.model.SearchableDocument;
import com.att.cw.support.DataUtils;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * Solr Document to Object converter - concrete implementation of Converter interface
 * @author ebrimatunkara
 */
public final class SolrDocumentToObjectConverter implements Converter<SearchableDocument, Map> {
    /**
     * Convert SearchableDocument to Map object
     * @param  source -> SearchableDocument
     * @return object -> Map
     **/
    @Override
    public Map convert(SearchableDocument source) {
        try {
            return new ObjectContentBuilder().setId(source.getId())
                    .setClassName(source.getType())
                    .setContentValues(source.getTextContent())
                    .setContentValues(source.getDateContent())
                    .build();
        } catch (IOException ex) {
            Logger.getLogger(SolrDocumentToObjectConverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Collections.EMPTY_MAP;
    }

    /**
     * Object converter content builder
     */
    private static class ObjectContentBuilder {

        private final Map<String, Object> content = new HashMap();
        private Class<?> classType;

        public ObjectContentBuilder setId(Long id) {
            content.put(Searchable.ID_FIELD, id);
            return this;
        }

        public ObjectContentBuilder setClassName(String name) {
            try {
                classType = Class.forName(name);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SolrDocumentToObjectConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
            return this;
        }

        public ObjectContentBuilder setContentValues(Map<String, Object> values) {
            content.putAll(values);
            return this;
        }

        public Map build() throws IOException {
            if (classType != null) {
                Map<String, Field> fields = getObjectFields(classType, true);
                return mapDocumentContent(fields).stream()
                        .distinct()
                        .flatMap(map -> map.entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (p1, p2) -> {
                            if (p1 instanceof Map && p2 instanceof Map) {
                                ((Map) p1).putAll((Map) p2);
                            }
                            return p1;
                        }));
            }
            return Collections.EMPTY_MAP;
        }

        private List<Map<String, Object>> mapDocumentContent(Map<String, Field> fields) {
            return content.entrySet().stream().map(x -> {
                String[] fieldNames = getFieldNames(x.getKey());
                Field field = fields.get(fieldNames[0]);
                return mapFieldValues(fieldNames, x.getValue(), 0, field);
            }).collect(Collectors.toList());
        }

        private String[] getFieldNames(String key) {
            return key.split("\\" + SearchableDocument.FIELD_SEPERATOR);
        }

        private Map<String, Object> mapFieldValues(String[] fields, Object value, int index, Field field) {
            Map<String, Object> values = new HashMap();
            String fieldName = fields[index];
            if (index < fields.length - 1) {
                if (field.getType().equals(Set.class)) {
                    List list = mapCollectionValues(fields, (Collection) value, index, field);
                    values.put(fieldName, list);
                } else {
                    Map<String, Field> flds = getObjectFields(field.getType(), false);
                    String _name = fields[index + 1];
                    values.put(fieldName, mapFieldValues(fields, value, index + 1, flds.get(_name)));
                }
            } else if (index == fields.length - 1) {
                //TODO refactoring
                if (field.getType().equals(Set.class)) {
                    values.put(fieldName, value);
                } else {
                    if (value instanceof Collection) {
                        values.put(fieldName, getFirstItem((Collection) value, field));
                    } else {
                        values.put(fieldName, value);
                    }
                }

            }
            return values;
        }

        private List mapCollectionValues(String[] fields, Collection items, int index, Field field) {
            return (List) items.stream().map(x -> {
                String[] subFields = ArrayUtils.subarray(fields, index + 1, fields.length);
                return mapFieldValues(subFields, x, 0, field);
            }).collect(Collectors.toList());
        }

        private Object getFirstItem(Collection value, Field field) {
            try {
                return (value.stream().findFirst().get());
            } catch (NoSuchElementException ex) {
                return value;
            }
        }

        private Map<String, Field> getObjectFields(Class<?> type, boolean all) {
            Map<String, Field> fields = DataUtils.getClassFields(type, all, Searchable.EXCLUDED_FIELDS);
            return fields;
        }
    }
}
