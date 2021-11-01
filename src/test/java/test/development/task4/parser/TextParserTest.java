package test.development.task4.parser;

import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.exception.TextException;
import com.development.task4.parser.TextParser;
import com.development.task4.parser.impl.*;
import com.development.task4.reader.TextReader;
import com.development.task4.reader.impl.TextReaderImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TextParserTest {
    private String text;
    private TextComposite textComposite;

    @BeforeTest
    public void init() throws TextException {
        TextReader textReader = new TextReaderImpl();
        text = textReader.readText("data/text.txt");

        TextParser paragraphParser = new ParagraphParser();
        textComposite = paragraphParser.parse(text);
    }

    @Test
    public void parseText() {
        int expected = 4;
        TextParser paragraphParser = new ParagraphParser();
        TextComposite textComposite = paragraphParser.parse(text);
        int actual = textComposite.getChildren().size();
        Assert.assertEquals(actual, expected, "invalid parsing of text");
    }

    @Test(dataProvider = "paragraphs")
    public void parseParagraph(TextComponent paragraph) {
        int expected = 2;
        TextParser sentenceParser = new SentenceParser();
        TextComposite textComposite = sentenceParser.parse(paragraph.toString());
        int actual = textComposite.getChildren().size();
        Assert.assertEquals(actual, expected, "invalid parsing of paragraph");
    }

    @Test(dataProvider = "sentences")
    public void parseSentence(TextComponent sentence) {
        int expected = 26;
        TextParser lexemeParser = new LexemeParser();
        TextComposite textComposite = lexemeParser.parse(sentence.toString());
        int actual = textComposite.getChildren().size();
        Assert.assertEquals(actual, expected, "invalid parsing of sentence");
    }

    @DataProvider(name = "paragraphs")
    public Object[][] getParagraphs() {
        int flag = 0;
        return new Object[][]{
                {textComposite.getChildren().get(flag++)},
                {textComposite.getChildren().get(flag++)},
                {textComposite.getChildren().get(flag++)},
                {textComposite.getChildren().get(flag)}
        };
    }

    @DataProvider(name = "sentences")
    public Object[][] getSentences() {
        List<TextComponent> sentences = textComposite.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .toList();
        int flag = 0;
        return new Object[][]{
                {sentences.get(flag++)},
                {sentences.get(flag++)},
                {sentences.get(flag++)},
                {sentences.get(flag++)},
                {sentences.get(flag++)},
                {sentences.get(flag)},
        };
    }
}
