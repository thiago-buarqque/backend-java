package com.evry.analytics.common;

import com.evry.analytics.model.annotation.JSONField;
import com.evry.analytics.model.annotation.JSONSerializable;
import com.evry.analytics.model.annotation.exception.JSONSerializableException;

import java.lang.reflect.Field;
import java.util.Collection;
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

    private void checkIfSerializable(Object object) throws JSONSerializableException {

        if (Objects.isNull(object)) {
            throw new JSONSerializableException("Object to serialize is null.");
        }

        Class<?> clazz = object.getClass();

        if (!clazz.isAnnotationPresent(JSONSerializable.class)) {
            throw new JSONSerializableException("Object is not serializable.");
        }
    }

    private String getJSONString(Object object) throws IllegalAccessException {

        Class<?> clazz = object.getClass();

        Map<String, Object> jsonFields = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JSONField.class)) {
                jsonFields.put(field.getName(), field.get(object));
            }
        }

        Set<Map.Entry<String, Object>> entrySet = jsonFields.entrySet();

        Stream<Map.Entry<String, Object>> stream = entrySet.stream();

        String jsonString =
                stream.map(
                                (entry) -> {
                                    try {
                                        return getField(
                                                getFieldFormattedName(entry), entry.getValue());
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                        .collect(Collectors.joining(",\n"));

        return "{\n" + jsonString + "\n}";
    }

    private String getField(String fieldFormattedName, Object fieldValue)
            throws IllegalAccessException {

        if (fieldValue == null) {
            return fieldFormattedName + "null";
        }

        Class<?> clazz = fieldValue.getClass();
        String value;
        if (clazz.isArray()) {
            value = getArrayFieldValue(fieldValue);
        } else if (fieldValue instanceof Collection) {
            value = getCollectionFieldValue(fieldValue);
        } else {
            value = getFieldValue(fieldValue);
        }

        return fieldFormattedName + value;
    }

    private String getFieldFormattedName(Map.Entry<String, Object> entry) {
        String DEFAULT_TAB_VALUE = "    ";

        return DEFAULT_TAB_VALUE + "\"" + entry.getKey() + "\": ";
    }

    private String getArrayFieldValue(Object object1) {
        Object[] array = (Object[]) object1;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(getFieldValue(array[i]));

            if (i != array.length - 1) {
                stringBuilder.append(", ");
            }
        }

        return "[" + stringBuilder + "]";
    }

    private String getCollectionFieldValue(Object object1) {
        Collection<Object> collection = (Collection<Object>) object1;

        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Object object2 : collection) {
            stringBuilder.append(getFieldValue(object2));

            if (i != collection.size() - 1) {
                stringBuilder.append(", ");
            }
            i++;
        }

        return "[" + stringBuilder + "]";
    }

    private String getFieldValue(Object object) {
        if (object instanceof String || object instanceof Character) {
            return "\"" + object + "\"";
        }

        try {
            Character c = (Character) object;

            return "\"" + c + "\"";
        } catch (Exception exception) {

            return object.toString();
        }
    }
}
