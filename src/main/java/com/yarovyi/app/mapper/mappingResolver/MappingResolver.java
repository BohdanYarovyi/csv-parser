package com.yarovyi.app.mapper.mappingResolver;

import java.lang.reflect.Field;
import java.util.Map;

public interface MappingResolver {

    <T> Map<String, Field> resolveMapping(Class<T> clazz);
    <T> Map<String, Field> getResolvedMapping(Class<T> clazz);
}
