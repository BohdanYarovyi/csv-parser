package com.yarovyi.app.mapper;

import com.yarovyi.app.exception.CsvFileHeaderReadingException;
import com.yarovyi.app.exception.CsvFileReadingException;
import com.yarovyi.app.exception.HeaderValidationException;
import com.yarovyi.app.input.CsvFileReader;
import com.yarovyi.app.mapper.delimiter.DelimiterResolver;
import com.yarovyi.app.entity.HeaderPosition;
import com.yarovyi.app.mapper.header.HeaderValidator;
import com.yarovyi.app.mapper.parser.CsvParser;
import com.yarovyi.app.mapper.mappingResolver.MappingResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class CsvEntityMapper implements EntityMapper {
    private final DelimiterResolver delimiterResolver;
    private final CsvFileReader fileReader;
    private final HeaderValidator headerValidator;
    private final MappingResolver mappingResolver;
    private final CsvParser csvParser;

    // todo: is it secure?? it is convenient now, but maybe needs to give this parameter by method args
    private String delimiter;

    @Override
    public <T> List<T> mapFromFile(Class<T> clazz, Path path) {
        if (clazz == null || path == null) {
            throw new IllegalStateException("Parameters 'clazz' or 'path' is null");
        }

        if (delimiter == null) {
            delimiter = delimiterResolver.resolveDelimiter(clazz);
        }

        try {
            Set<String> requiredFieldNames = mappingResolver.resolveMapping(clazz).keySet();
            HeaderPosition headerPosition = processHeader(path, requiredFieldNames);
            List<String> fileContent = fileReader.getContent(path);

            return csvParser.processMapping(clazz, headerPosition, fileContent, delimiter);
        } catch (CsvFileReadingException e) {
            throw new RuntimeException(e);
        } catch (HeaderValidationException e) {
            throw new RuntimeException("Failed to validate header in file: " + path, e);
        } finally {
            delimiter = null;
        }
    }

    @Override
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    private HeaderPosition processHeader(Path path, Set<String> requiredHeaders) throws CsvFileHeaderReadingException, HeaderValidationException {
        String rawHeader = fileReader.getRawHeader(path);
        String[] fileHeaders = headerValidator.validateHeader(rawHeader, delimiter, requiredHeaders);

        return new HeaderPosition(fileHeaders, requiredHeaders);
    }

}
