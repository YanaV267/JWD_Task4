package com.development.task4.reader.impl;

import com.development.task4.exception.TextException;
import com.development.task4.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String readText(String filename) throws TextException {
        if (getClass().getClassLoader().getResource(filename) == null) {
            LOGGER.error("File {} doesn't exist in specified directory.", filename);
            throw new TextException("File " + filename + " doesn't exist in specified directory.");
        }
        String fileText;
        try {
            Path pathToFile = Paths.get(getClass().getClassLoader().getResource(filename).toURI());
            fileText = Files.readString(pathToFile);
        } catch (IOException | URISyntaxException exception) {
            LOGGER.error("Error of reading file \"{}\": {}", filename, exception);
            throw new TextException("Error of reading file \"" + filename + "\": ", exception);
        }
        return fileText;
    }
}
