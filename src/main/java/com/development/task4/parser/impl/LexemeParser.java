package com.development.task4.parser.impl;

import com.development.task4.entity.ComponentType;
import com.development.task4.entity.SymbolLeaf;
import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {
    private static final String LEXEME_DELIMITER_REGEX = "\\S+";
    private final TextParser wordParser = new WordParser();

    @Override
    public TextComposite parse(String textValue) {
        TextComposite lexemeComposite = new TextComposite(ComponentType.LEXEME);
        Pattern lexemePattern = Pattern.compile(LEXEME_DELIMITER_REGEX);
        Matcher lexemes = lexemePattern.matcher(textValue);
        while (lexemes.find()) {
            TextComponent lexemeComponent = wordParser.parse(lexemes.group());
            lexemeComposite.add(lexemeComponent);
        }
        return lexemeComposite;
    }
}
