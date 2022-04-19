package com.example.lp.tripcalculator.fileIO;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@Component
public class CsvHelper {
    private final CsvMapper csvMapper;

    CsvHelper() {
        this.csvMapper = new CsvMapper();
    }

    public <T> Iterator <T> readFromFile(String fileName, Class<T> clazz) throws IOException {
        return csvMapper.enable(CsvParser.Feature.TRIM_SPACES).readerFor(clazz).with(CsvSchema.emptySchema().withHeader())
                .readValues(new File(fileName));
    }

    public <T> void writeToFile(String fileName, Class<T> clazz, List<T> records) throws IOException {
        Path pathToFile = Paths.get(fileName);
        Files.createDirectories(pathToFile.getParent());
        Files.createFile(pathToFile);
        ObjectWriter writer = csvMapper.writer(csvMapper.schemaFor(clazz).withHeader());
        writer.writeValue(pathToFile.toFile(), records);
    }
}
