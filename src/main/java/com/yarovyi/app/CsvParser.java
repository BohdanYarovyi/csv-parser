package com.yarovyi.app;

import com.yarovyi.app.mapper.CsvEntityMapper;
import com.yarovyi.app.mapper.EntityMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class CsvParser {

    public static void main(String[] args) {
        // start spring boot application
        var context = SpringApplication.run(CsvParser.class, args);

        // mapper bean
        var mapper = context.getBean(CsvEntityMapper.class);

        // persons mapping
        File persons = new File("csv","person");
        mapFiles(Person.class, persons, mapper);

        // cars mapping
        File cars = new File("csv","car");
        mapFiles(Car.class, cars, mapper);
    }

    private static <T> void mapFiles(Class<T> type, File filesDirectory, EntityMapper mapper) {
        if (type == null || filesDirectory == null || mapper == null) {
            throw new IllegalArgumentException("Parameters 'type', 'filesDirectory' or 'mapper' is null");
        }

        if (!filesDirectory.exists()) {
            throw new IllegalStateException("Such directory is not exists: " + filesDirectory.getPath());
        }

        File[] files = filesDirectory.listFiles(File::isFile);
        for (File file : files) {
            List<T> objects = mapper.mapFromFile(type, file.toPath());
            showObjectsList(objects, file);
        }
    }

    // view
    private static <T> void showObjectsList(List<T> objects, File file) {
        System.out.println();
        System.out.println("-".repeat(60));
        System.out.println("Mapped from file: " + file.getPath());

        objects.forEach(System.out::println);

        System.out.println("-".repeat(60));
        System.out.println();
    }

}
