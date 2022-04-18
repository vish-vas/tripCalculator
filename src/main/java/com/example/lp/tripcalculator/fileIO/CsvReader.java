package com.example.lp.tripcalculator.fileIO;

import com.fasterxml.jackson.dataformat.csv.CsvParser;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Component
public class CsvReader {
    private final CsvMapper csvMapper;

    CsvReader() {
        this.csvMapper = new CsvMapper();
    }

    public <T>Iterator<T> parse(String fileName, Class<T> clazz) throws IOException {
        return csvMapper.enable(CsvParser.Feature.TRIM_SPACES).readerFor(clazz).with(CsvSchema.emptySchema().withHeader())
                .readValues(new File(fileName));
    }
}
