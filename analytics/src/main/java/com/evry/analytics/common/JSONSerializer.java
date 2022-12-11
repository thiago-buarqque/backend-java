package com.evry.analytics.common;

import com.evry.analytics.annotations.JSONField;
import com.evry.analytics.annotations.JSONSerializable;
import com.evry.analytics.annotations.exceptions.JSONSerializableException;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSONSerializer {

    public String convertToJSONString(Object object)
            throws JSONSerializableException, IllegalAccessException {

        checkIfSerializable(object);
        return getJSONString(object);
    }

    private void checkIfSerializable(Object object)
            throws JSONSerializableException {

        if(Objects.isNull(object)) {
            throw new JSONSerializableException("Object to serialize is null");
        }

        Class<?> clazz = object.getClass();

        if(!clazz.isAnnotationPresent(JSONSerializable.class)) {
            throw new JSONSerializableException("Object is not serializable");
        }
    }

    private String getJSONString(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();

        Map<String, Object> jsonFields = new HashMap<>();

        for(Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JSONField.class)) {
                jsonFields.put(field.getName(), field.get(object));
            }
        }

        Set<Map.Entry<String, Object>> entrySet = jsonFields.entrySet();

        Stream<Map.Entry<String, Object>> stream = entrySet.stream();

        return stream.map((entry) -> "\"" + entry.getKey() + "\":" +
                getFieldReturn(entry.getValue())
        ).collect(Collectors.joining(","));
    }

    private String getFieldReturn(Object object) {
        if(object instanceof String) {
            return "\"" +object + "\"";
        }

        return object.toString();
    }
}
