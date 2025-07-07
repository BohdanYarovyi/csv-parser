package com.yarovyi.app.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "csv-parser")
public class CSVMapperProperties {
    private Map<String, EntityProperties> classMapping;

    @Setter
    @Getter
    public static class EntityProperties {
        public static final String DEFAULT_DELIMITER = ",";
        private String delimiter = DEFAULT_DELIMITER;
        private Map<String, String> fields;
    }

}
