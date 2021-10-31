package com.development.task4;

import com.development.task4.entity.TextComposite;
import com.development.task4.exception.TextException;
import com.development.task4.parser.TextParser;
import com.development.task4.parser.impl.ParagraphParser;
import com.development.task4.reader.TextReader;
import com.development.task4.reader.impl.TextReaderImpl;

public class Main {
    public static void main(String[] args) {
        TextReader textReader = new TextReaderImpl();
        try {
            String text = textReader.readText("data/text.txt");
            TextParser parser = new ParagraphParser();
            TextComposite textComposite = parser.parse(text);
            System.out.println("\n" + textComposite.toString());
        } catch (TextException e) {
            e.printStackTrace();
        }
    }
}
