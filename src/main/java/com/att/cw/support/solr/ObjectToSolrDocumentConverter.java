/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support.solr;

import com.att.cw.model.SearchableDocument;
import com.att.cw.model.SearchableDocument.SearchableDocumentBuilder;
import com.att.cw.support.DataUtils;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.util.FieldUtils;

/**
 * Object to SolrDocument Converter
 * @author ebrimatunkara
 */
public final class ObjectToSolrDocumentConverter implements Converter<Object, SearchableDocument> {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SolrSearchQueryFactory.class);

    private final String[] DEFAULT_FIELDS = {"serialVersionUID", "version"};
    private final String[] defaultExcludedFields = ArrayUtils.add(DEFAULT_FIELDS, SearchableDocument.ID_FIELD);
    private String[] excludedFields;

    public ObjectToSolrDocumentConverter() {
        this(ArrayUtils.EMPTY_STRING_ARRAY);
    }

    public ObjectToSolrDocumentConverter(String[] excludedFields) {
        initExcludedFields(excludedFields);
    }

    private void initExcludedFields(String[] fields) {
        this.excludedFields = ArrayUtils.addAll(defaultExcludedFields, fields);
    }
    /**
     * Convert object to solr document
     * @param obj
     * @reeturn SearchableDocument
     **/
    @Override
    public SearchableDocument convert(Object obj) {
        SearchableDocument doc = new SearchableDocument();
        try {
            Long id = (Long) FieldUtils.getFieldValue(obj, SearchableDocument.ID_FIELD);
            doc = new SearchableDocumentBuilder()
                    .setId(id)
                    .setDocType(obj.getClass().getName())
                    .setContent(mapObjectFieldValue(null, obj, excludedFields))
                    .build();
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(ObjectToSolrDocumentConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }
    /**
     * Recursive function to map object field values
     * @param fieldPrefix :String -> root name of object field
     * @param obj: Object -> object to map
     * @param fields: Map -> objects fields in has
     */
    private Map mapObjectFieldValues(String prefixName, Object obj, Map<String, Field> fields) {
        Map<String, Object> contents = new HashMap();
        fields.entrySet().stream().forEach(f -> {
            try {
                String name = (prefixName != null) ? prefixName + SearchableDocument.FIELD_SEPERATOR + f.getKey() : f.getKey();
                Object value = FieldUtils.getFieldValue(obj, f.getKey());
                //if null
                if (value != null) {
                    //insert primitive  data type
                    if (value instanceof String || value instanceof Enum || value instanceof Timestamp
                            || value instanceof Long || f.getValue().getType().isPrimitive() || value instanceof byte[]
                            || value instanceof Date) {
                        if (value instanceof byte[]) {
                            contents.put(name, new String((byte[]) value, StandardCharsets.UTF_8));
                        }
                        else if(value instanceof Enum){
                           contents.put(name, value.toString());
                        }
                        else {
                           contents.put(name, value);
                        }
                    } else if (value instanceof Collection || value instanceof Object[]) {
                        Collection items = value.getClass().isArray() ? Arrays.asList((Object[]) value) : (Collection) value;
                        items.stream().forEach((item) -> {
                            Map<String, Object> map = mapObjectFieldValue(name, item, excludedFields);
                            map.entrySet().forEach(x -> {
                                String key = x.getKey();
                                if (!contents.containsKey(key)) {
                                    contents.put(key, new ArrayList());
                                }
                               ((List) contents.get(key)).add(x.getValue());
                            });
                        });
                    } else {
                        contents.putAll(mapObjectFieldValue(name, value, excludedFields));
                    }
                }

            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        });
        return contents;
    }
    private Map<String, Object> mapObjectFieldValue(String prefixName, Object item, String... filterField) {
        Map<String, Field> _fields = DataUtils.getClassFields(item.getClass(), filterField);
        return mapObjectFieldValues(prefixName, item, _fields);
    }

}
