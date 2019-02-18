package com.logs.utils;

import com.logs.exception.ParameterValidationException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FileUtils {

    private FileUtils() {}

    static List<String> getLinesFromTextFile(String filePath) {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new ParameterValidationException(e.getMessage());
        }
    }

}
