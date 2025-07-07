package com.yarovyi.app.mapper.mappingResolver;

import com.yarovyi.app.annotationProcessing.AnnotationProcessor;
import com.yarovyi.app.exception.NotFoundMappedFieldNameException;
import com.yarovyi.app.mapper.mappingResolver.mappingSupplier.MappingSupplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Slf4j
@Component
public class SimpleMappingResolver implements MappingResolver {
    private final TreeSet<MappingSupplier> mappingSuppliers;
    private final AnnotationProcessor annotationProcessor;
    private final Map<Class<?>, Map<String, Field>> mappingCache = new HashMap<>();

    public SimpleMappingResolver(List<MappingSupplier> mappingSuppliers, AnnotationProcessor annotationProcessor) {
        this.mappingSuppliers = new TreeSet<>(mappingSuppliers);
        this.annotationProcessor = annotationProcessor;
    }

    @Override
    public <T> Map<String, Field> resolveMapping(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("class is null");
        }
        Map<String, Field> result = new HashMap<>();

        List<Field> requiredFields = annotationProcessor.getRequiredFields(clazz);
        for (Field requiredField : requiredFields) {
            String fieldName = requiredField.getName();
            String mappedName = getMappedNameForFieldName(fieldName, clazz);

            result.put(mappedName, requiredField);
        }

        mappingCache.put(clazz, result);
        return result;
    }

    @Override
    public <T> Map<String, Field> getResolvedMapping(Class<T> clazz) {
        Map<String, Field> fieldMapping = mappingCache.get(clazz);

        if (fieldMapping == null) {
            log.error("getResolvedMapping returns null, cause mapping not resolved for class {}", clazz.getName());
        }

        return fieldMapping;
    }

    private <T> String getMappedNameForFieldName(String fieldName, Class<T> clazz) {
        String mappedName;

        for (MappingSupplier mappingSupplier : mappingSuppliers) {
            mappedName = mappingSupplier.getMapping(clazz).get(fieldName);

            if (mappedName != null) {
                return mappedName;
            }
        }

        String errorMessage = "Not found mapped name for field %s class %s";
        throw new NotFoundMappedFieldNameException(errorMessage.formatted(fieldName, clazz.getName()));
    }

}
