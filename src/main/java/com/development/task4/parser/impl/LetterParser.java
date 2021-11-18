package com.development.task4.parser.impl;

import com.development.task4.entity.ComponentType;
import com.development.task4.entity.SymbolLeaf;
import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.parser.TextParser;

public class LetterParser implements TextParser {
    private static final String LETTER_DELIMITER_REGEX = "";

    @Override
    public TextComposite parse(String textValue) {
        TextComposite letterComposite = new TextComposite(ComponentType.LETTER);
        String[] symbols = textValue.split(LETTER_DELIMITER_REGEX);
        for (String symbol : symbols) {
            TextComponent letterComponent = new SymbolLeaf(ComponentType.LETTER, symbol.charAt(0));
            letterComposite.add(letterComponent);
        }
        return letterComposite;
    }
}
