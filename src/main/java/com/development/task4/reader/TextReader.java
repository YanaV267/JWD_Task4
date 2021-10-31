package com.development.task4.reader;

import com.development.task4.exception.TextException;

public interface TextReader {
    String readText(String filename) throws TextException;
}