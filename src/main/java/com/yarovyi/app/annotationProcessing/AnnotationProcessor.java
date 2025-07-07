package com.yarovyi.app.annotationProcessing;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface AnnotationProcessor {

    <T> List<Field> getRequiredFields(Class<T> clazz);
    <T> Map<String, String> getAnnotationMapping(Class<T> clazz);

}
