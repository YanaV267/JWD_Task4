package com.development.task4.parser.impl;

import com.development.task4.entity.ComponentType;
import com.development.task4.entity.SymbolLeaf;
import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements TextParser {
    private static final String WORD_DELIMITER_REGEX = "[А-я\\w]+";
    private final TextParser letterParser = new LetterParser();
    private final TextParser expressionParser = new ExpressionParser();

    @Override
    public TextComposite parse(String textValue) {
        TextComposite wordComposite = new TextComposite(ComponentType.WORD);
        if (textValue.matches(WORD_DELIMITER_REGEX)) {
            Pattern wordPattern = Pattern.compile(WORD_DELIMITER_REGEX);
            Matcher words = wordPattern.matcher(textValue);
            while (words.find()) {
                TextComponent wordComponent = letterParser.parse(words.group());
                wordComposite.add(wordComponent);
            }
        } else {
            TextComponent expressionComponent = expressionParser.parse(textValue);
            wordComposite.add(expressionComponent);
        }
        return wordComposite;
    }
}
