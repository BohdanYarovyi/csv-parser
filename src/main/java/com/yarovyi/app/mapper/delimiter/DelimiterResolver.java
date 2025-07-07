package com.yarovyi.app.mapper.delimiter;

public interface DelimiterResolver {

    <T> String resolveDelimiter(Class<T> clazz);

}
