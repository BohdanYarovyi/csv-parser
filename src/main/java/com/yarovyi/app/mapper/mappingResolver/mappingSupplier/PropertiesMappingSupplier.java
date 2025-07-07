package com.yarovyi.app.mapper.mappingResolver.mappingSupplier;

import com.yarovyi.app.configuration.CSVMapperProperties;
import com.yarovyi.app.configuration.CSVMapperProperties.EntityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class PropertiesMappingSupplier implements MappingSupplier {
    private final CSVMapperProperties configurationProperties;
    // todo: here may by some cache

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public <T> Map<String, String> getMappingHelper(Class<T> clazz) {
        String className = clazz.getSimpleName();
        Map<String, EntityProperties> classMapping = configurationProperties.getClassMapping();

        // if not found, that name of class is absent in config file
        EntityProperties entityProperties = classMapping.get(className);
        if (entityProperties == null) {
            return Map.of();
        }

        // if not found, that fields mapping is absent
        Map<String, String> fieldsMapping = entityProperties.getFields();
        if (fieldsMapping == null) {
            return Map.of();
        }

        return fieldsMapping;
    }

}
