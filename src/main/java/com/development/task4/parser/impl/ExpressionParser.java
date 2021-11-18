package com.development.task4.parser.impl;

import com.development.task4.entity.ComponentType;
import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.parser.TextParser;

public class ExpressionParser implements TextParser {
    private final TextParser letterParser = new LetterParser();

    @Override
    public TextComposite parse(String expressionValue) {
        TextComposite expressionComposite = new TextComposite(ComponentType.EXPRESSION);
        TextComponent expressionComponent = letterParser.parse(expressionValue);
        expressionComposite.add(expressionComponent);
        return expressionComposite;
    }
}
