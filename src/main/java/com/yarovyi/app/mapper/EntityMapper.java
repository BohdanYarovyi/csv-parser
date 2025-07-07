package com.yarovyi.app.mapper;

import java.nio.file.Path;
import java.util.List;

public interface EntityMapper {

    <T> List<T> mapFromFile(Class<T> clazz, Path path);
    void setDelimiter(String delimiter);

}
