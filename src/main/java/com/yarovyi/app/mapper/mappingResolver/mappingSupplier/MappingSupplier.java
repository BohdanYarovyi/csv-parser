package com.yarovyi.app.mapper.mappingResolver.mappingSupplier;

import java.util.Map;

public interface MappingSupplier extends Comparable<MappingSupplier> {

    int getPriority();  // todo: make configurable

    <T> Map<String, String> getMappingHelper(Class<T> clazz);

    default <T> Map<String, String> getMapping(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Parameter 'clazz' is null");
        }

        return getMappingHelper(clazz);
    }

    @Override
    default int compareTo(MappingSupplier other) {
        return Integer.compare(getPriority(), other.getPriority());
    }

}
