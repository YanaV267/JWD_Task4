package com.development.task4.entity;

public enum ComponentType {
    PARAGRAPH("\n\t"),
    SENTENCE(""),
    LEXEME(" "),
    WORD(""),
    EXPRESSION(""),
    LETTER(""),
    SYMBOL("");

    private final String delimiter;

    ComponentType(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
