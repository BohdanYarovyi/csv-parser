package com.yarovyi.app.mapper.delimiter;

import com.yarovyi.app.configuration.CSVMapperProperties;
import com.yarovyi.app.configuration.CSVMapperProperties.EntityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class SimpleDelimiterResolver implements DelimiterResolver {
    private final CSVMapperProperties configurationContext;

    @Override
    public <T> String resolveDelimiter(Class<T> clazz) {
            String configuredDelimiter = getDelimiterFromConfigurationsForClass(clazz);

            return configuredDelimiter != null
                    ? configuredDelimiter
                    : EntityProperties.DEFAULT_DELIMITER;
    }

    private <T> String getDelimiterFromConfigurationsForClass(Class<T> clazz) {
        String className = clazz.getSimpleName();

        Map<String, EntityProperties> classMapping = configurationContext.getClassMapping();
        if (classMapping == null) {
            return null;
        }

        EntityProperties entityProperties = classMapping.get(className);
        if (entityProperties == null) {
            return null;
        }

        return entityProperties.getDelimiter();
    }
}
