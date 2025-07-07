package com.yarovyi.app.mapper.header;

import com.yarovyi.app.exception.HeaderValidationException;
import com.yarovyi.app.exception.InvalidDelimiterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.PatternSyntaxException;

@RequiredArgsConstructor
@Component
public class SimpleHeaderValidator implements HeaderValidator {

    @Override
    public String[] validateHeader(String rawHeader, String delimiter, Set<String> requiredHeaders) throws HeaderValidationException {
        String[] headers = splitHeader(rawHeader, delimiter);
        Set<String> headersSet = Set.of(headers);

        validateHeaderLength(headers);
        validateRequiredHeadersPresent(headersSet, requiredHeaders);

        return headers;
    }

    private String[] splitHeader(String rawHeader, String delimiter) throws InvalidDelimiterException {
        try {
            return rawHeader.split(delimiter);
        } catch (PatternSyntaxException e) {
            throw new InvalidDelimiterException("Delimiter '" + delimiter + "' is invalid", e);
        }
    }

    private void validateHeaderLength(String[] headers) throws HeaderValidationException {
        if (headers.length == 0) {
            throw new HeaderValidationException("Header length is 0. Make sure, that delimiter is correct");
        }
    }

    private void validateRequiredHeadersPresent(Set<String> fileHeaders, Set<String> requiredHeaders) throws HeaderValidationException {
        Set<String> requiredHeadersSet = new HashSet<>(requiredHeaders);
        Set<String> fileHeadersSet = new HashSet<>(fileHeaders);

        boolean containsAll = fileHeadersSet.containsAll(requiredHeadersSet);
        if (!containsAll) {
            requiredHeadersSet.removeAll(fileHeadersSet);
            String errorMessage = "File not contains all required headers " + requiredHeadersSet;
            throw new HeaderValidationException(errorMessage);
        }
    }

}
