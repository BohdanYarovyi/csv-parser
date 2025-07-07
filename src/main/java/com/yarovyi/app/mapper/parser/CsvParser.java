package com.yarovyi.app.mapper.parser;

import com.yarovyi.app.entity.HeaderPosition;

import java.util.List;

public interface CsvParser {

    <T> List<T> processMapping(Class<T> clazz, HeaderPosition headerPosition, List<String> content, String delimiter);

}
