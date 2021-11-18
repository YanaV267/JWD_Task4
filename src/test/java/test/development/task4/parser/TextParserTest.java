package test.development.task4.parser;

import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.exception.TextException;
import com.development.task4.parser.TextParser;
import com.development.task4.parser.impl.*;
import com.development.task4.reader.TextReader;
import com.development.task4.reader.impl.TextReaderImpl;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class TextParserTest {
    private TextComposite textComposite;

    @Mock
    TextReader textReader = new TextReaderImpl();
    String suggestedText = "It is a long a!=b established fact that a reader will be distracted by the readable content of a " +
            "page when looking at its layout. \r\nThe point of using  Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English? " +
            "It is a established fact that a reader will be of a page when looking at its layout…";

    @BeforeTest
    public void init() throws TextException {
        MockitoAnnotations.openMocks(this);
        when(textReader.readText("data/text.txt")).thenReturn(suggestedText);

        String text = textReader.readText("data/text.txt");
        TextParser paragraphParser = new ParagraphParser();
        textComposite = paragraphParser.parse(text);
    }

    @Test
    public void parseText() {
        int expected = 2;
        int actual = textComposite.getChildren().size();
        Assert.assertEquals(actual, expected, "invalid parsing of text");
    }

    @Test(dataProvider = "paragraphs")
    public void parseParagraph(TextComponent paragraph) {
        int expected = 1;
        TextParser sentenceParser = new SentenceParser();
        TextComposite textComposite = sentenceParser.parse(paragraph.toString());
        int actual = textComposite.getChildren().size();
        Assert.assertEquals(actual, expected, "invalid parsing of paragraph");
    }

    @Test(dataProvider = "sentences")
    public void parseSentence(TextComponent sentence) {
        int expected = 28;
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
                {sentences.get(flag)},
        };
    }
}
