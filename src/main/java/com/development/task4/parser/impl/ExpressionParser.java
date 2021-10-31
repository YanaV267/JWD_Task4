package com.development.task4.parser.impl;

import com.development.task4.entity.ComponentType;
import com.development.task4.entity.SymbolLeaf;
import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.parser.TextParser;

public class ExpressionParser implements TextParser {
    private static final String EXPRESSION_DELIMITER_REGEX = "";

    @Override
    public TextComposite parse(String textValue) {
        TextComposite expressionComposite = new TextComposite(ComponentType.EXPRESSION);
        String[] symbols = textValue.split(EXPRESSION_DELIMITER_REGEX);
        for (String symbol : symbols) {
            char currentSymbol = symbol.charAt(0);
            if (Character.isLetter(currentSymbol)) {
                TextComponent letterComponent = new SymbolLeaf(ComponentType.LETTER, currentSymbol);
                expressionComposite.add(letterComponent);
            } else {
                System.out.println(currentSymbol);
                TextComponent symbolComponent = new SymbolLeaf(ComponentType.SYMBOL, currentSymbol);
                expressionComposite.add(symbolComponent);
            }
        }
        return expressionComposite;
    }
}
