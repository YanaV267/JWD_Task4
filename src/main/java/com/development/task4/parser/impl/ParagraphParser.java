package com.development.task4.parser.impl;

import com.development.task4.entity.ComponentType;
import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.parser.TextParser;

public class ParagraphParser implements TextParser {
    private static final String PARAGRAPH_DELIMITER_REGEX = "\\r\\n";
    private final TextParser sentenceParser = new SentenceParser();

    @Override
    public TextComposite parse(String textValue) {
        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        String[] paragraphs = textValue.split(PARAGRAPH_DELIMITER_REGEX);
        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = sentenceParser.parse(paragraph);
            textComposite.add(paragraphComponent);
        }
        return textComposite;
    }
}
