package com.yarovyi.app.annotationProcessing;

import com.yarovyi.app.annotationProcessing.annotation.RequiredField;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SimpleAnnotationProcessor implements AnnotationProcessor {
    public static final Class<RequiredField> REQUIRED_FIELD_ANNOTATION = RequiredField.class;

    @Override
    public <T> List<Field> getRequiredFields(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz is null");
        }

        List<Field> requiredFields = new ArrayList<>();
        Field[] declaredFields = clazz.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(REQUIRED_FIELD_ANNOTATION)) {
                requiredFields.add(declaredField);
            }
        }

        return requiredFields;
    }

    @Override
    public <T> Map<String, String> getAnnotationMapping(Class<T> clazz) {
        Map<String, String> result = new HashMap<>();

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(REQUIRED_FIELD_ANNOTATION)) {
                RequiredField fieldAnnotation = field.getAnnotation(REQUIRED_FIELD_ANNOTATION);
                String fieldName = field.getName();
                String name = fieldAnnotation.value().isBlank()
                        ? fieldName
                        : fieldAnnotation.value();

                result.put(fieldName, name);
            }
        }

        return result;
    }

}
