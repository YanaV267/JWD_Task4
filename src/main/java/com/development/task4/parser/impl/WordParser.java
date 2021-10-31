package com.development.task4.parser.impl;

import com.development.task4.entity.ComponentType;
import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.parser.TextParser;

public class WordParser implements TextParser {
    private static final String WORD_DELIMITER_REGEX = "[А-я\\w]+";
    private final TextParser letterParser = new LetterParser();

    @Override
    public TextComposite parse(String lexemeValue) {
        TextComposite wordComposite = new TextComposite(ComponentType.WORD);
        if (lexemeValue.matches(WORD_DELIMITER_REGEX)) {
            TextComponent lexemeComponent = letterParser.parse(lexemeValue);
            wordComposite.add(lexemeComponent);
        }
        return wordComposite;
    }
}
