package com.development.task4.parser.impl;

import com.development.task4.entity.ComponentType;
import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.parser.TextParser;

public class WordParser implements TextParser {
    private final TextParser letterParser = new LetterParser();

    @Override
    public TextComposite parse(String lexemeValue) {
        TextComposite wordComposite = new TextComposite(ComponentType.WORD);
        TextComponent wordComponent = letterParser.parse(lexemeValue);
        wordComposite.add(wordComponent);
        return wordComposite;
    }
}
