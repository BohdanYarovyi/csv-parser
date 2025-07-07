package com.yarovyi.app.input;

import com.yarovyi.app.exception.CsvFileContentReadingException;
import com.yarovyi.app.exception.CsvFileHeaderReadingException;

import java.nio.file.Path;
import java.util.List;

public interface CsvFileReader {

    String getRawHeader(Path path) throws CsvFileHeaderReadingException;

    List<String> getContent(Path path) throws CsvFileContentReadingException;

}
