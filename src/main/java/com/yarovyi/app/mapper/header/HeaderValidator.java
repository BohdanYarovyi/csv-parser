package com.yarovyi.app.mapper.header;

import com.yarovyi.app.exception.HeaderValidationException;

import java.util.Set;

public interface HeaderValidator {

    String[] validateHeader(String rawHeader, String delimiter, Set<String> requiredHeaders) throws HeaderValidationException;

}
