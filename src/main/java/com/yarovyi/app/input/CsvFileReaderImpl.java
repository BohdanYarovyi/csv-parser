package com.yarovyi.app.input;

import com.yarovyi.app.exception.CsvFileContentReadingException;
import com.yarovyi.app.exception.CsvFileHeaderReadingException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvFileReaderImpl implements CsvFileReader {

    @Override
    public String getRawHeader(Path path) throws CsvFileHeaderReadingException {
        if (path == null) {
            throw new IllegalArgumentException("Parameter 'path' is null");
        }

        File file = path.toFile();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return br.readLine();
        } catch (IOException e) {
            throw new CsvFileHeaderReadingException("Failed to read header from file " + path, e);
        }
    }

    @Override
    public List<String> getContent(Path path) throws CsvFileContentReadingException {
        List<String> result = new ArrayList<>();

        File file = path.toFile();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            // skipping first line
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                result.add(line);
            }

            return result;
        } catch (IOException e) {
            throw new CsvFileContentReadingException("Failed to read file " + path, e);
        }
    }

}
