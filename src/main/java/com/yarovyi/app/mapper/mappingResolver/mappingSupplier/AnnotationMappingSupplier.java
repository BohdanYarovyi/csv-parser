package com.yarovyi.app.mapper.mappingResolver.mappingSupplier;

import com.yarovyi.app.annotationProcessing.AnnotationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class AnnotationMappingSupplier implements MappingSupplier {
    private final AnnotationProcessor annotationProcessor;

    @Override
    public int getPriority() {
        return 200;
    }

    @Override
    public <T> Map<String, String> getMappingHelper(Class<T> clazz) {
        return annotationProcessor.getAnnotationMapping(clazz);
    }
}
