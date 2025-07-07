package com.yarovyi.app.mapper.parser;

import com.yarovyi.app.exception.ObjectMappingException;
import com.yarovyi.app.entity.HeaderPosition;
import com.yarovyi.app.mapper.mappingResolver.MappingResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

@RequiredArgsConstructor
@Component
public class SimpleCsvParser implements CsvParser {
    private final MappingResolver mappingResolver;

    @Override
    public <T> List<T> processMapping(Class<T> clazz, HeaderPosition headerPosition, List<String> content, String delimiter) {
        List<T> result = new ArrayList<>();

        try {
            for (String row : content) {
                String[] values = row.strip().split(delimiter);

                T createdObject = createObject(clazz, values, headerPosition);
                result.add(createdObject);
            }
        } catch (ReflectiveOperationException e) {
            String message1 = "Failed to create reflectively an objects.";
            String message2 = "Make sure about no args constructor in class " + clazz.getName();
            throw new ObjectMappingException(message1 + " " + message2, e);
        }

        return result;
    }

    private <T> T createObject(Class<T> clazz, String[] values, HeaderPosition headerPosition) throws ReflectiveOperationException{
        Map<String, Field> requiredFields = mappingResolver.getResolvedMapping(clazz);
        Set<String> requiredFieldNames = requiredFields.keySet();

        T object = clazz.getDeclaredConstructor().newInstance();
        for (String fieldName : requiredFieldNames) {
            Integer valuePosition = headerPosition.getPosition(fieldName);

            if (valuePosition < values.length && values[valuePosition] != null) {
                String value = values[valuePosition];
                Field field = requiredFields.get(fieldName);

                field.setAccessible(true);
                field.set(object, value);
            }
        }

        return object;
    }
}
