/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.att.cw.support;

import com.att.cw.model.Searchable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicates;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.sql.JDBCType;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import java.util.stream.Collectors;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Data type Helper
 *
 * @author ebrimatunkara
 */
public class DataUtils {

    private static final Logger logger = LoggerFactory.getLogger(DataUtils.class);
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    public static byte[] stringToByte(String text) {
        return (text != null) ? text.getBytes(StandardCharsets.UTF_8) : null;
    }

    public static String bytesToString(byte[] bytes) {
        return (bytes != null) ? new String(bytes, StandardCharsets.UTF_8) : null;
    }

    public static String toUtcDate(String dateStr) {
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        // Add other parsing formats to try as you like:
        String[] dateFormats = {"yyyy-MM-dd", "MMM dd, yyyy hh:mm:ss Z"};
        for (String dateFormat : dateFormats) {
            try {
                return out.format(new SimpleDateFormat(dateFormat).parse(dateStr));
            } catch (ParseException ex) {
                logger.error(ex.getMessage());
            }
        }
        throw new IllegalArgumentException("Invalid date: " + dateStr);
    }

    public static <T> T mapToObject(Map map, Class<T> klazzType) throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        return mapper.readValue(json, klazzType);
    }

    public static Map<String, Field> getClassFields(Class<?> classType, String... excludeFields) {
        return getClassFields(classType, true, excludeFields);
    }

    public static Map<String, Field> getClassFields(Class<?> classType, boolean all, String... excludeFields) {
        Set<Field> fields;
        if (all) {
            fields = ReflectionUtils.getAllFields(classType, Predicates.notNull());
        } else {
            fields = ReflectionUtils.getFields(classType, Predicates.notNull());
        }
        return fields.stream().filter(f -> {
            Set<String> items = new HashSet(Arrays.asList(excludeFields));
            return !items.contains(f.getName());
        })
                .collect(Collectors.toMap(i -> i.getName(), Function.identity(), (x1, x2) -> x2));
    }

    public static boolean classTypeExists(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (ClassNotFoundException ex) {
            logger.error(ex.getMessage());
        }
        return false;
    }

    public static String mapClassName(Class<?> base, String name) {
        StringBuilder builder = new StringBuilder();
        builder.append(base.getPackage().getName())
                .append(Searchable.FIELD_SEPERATOR)
                .append(StringUtils.capitalize(name.toLowerCase()));
        return builder.toString();
    }

    public static String objectToJson(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }
}
