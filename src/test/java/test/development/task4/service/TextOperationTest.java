package test.development.task4.service;

import com.development.task4.entity.TextComponent;
import com.development.task4.entity.TextComposite;
import com.development.task4.exception.TextException;
import com.development.task4.parser.TextParser;
import com.development.task4.parser.impl.ParagraphParser;
import com.development.task4.reader.TextReader;
import com.development.task4.reader.impl.TextReaderImpl;
import com.development.task4.service.TextOperation;
import com.development.task4.service.impl.TextOperationImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TextOperationTest {
    private TextOperation textOperation;
    private TextComposite textComposite;

    @BeforeTest
    public void init() throws TextException {
        textOperation = new TextOperationImpl();
        TextReader textReader = new TextReaderImpl();
        TextParser textParser = new ParagraphParser();

        String text = textReader.readText("data/text.txt");
        textComposite = textParser.parse(text);
    }

    @Test
    public void sortParagraphsBySentenceAmount() {
        int expected = 1;
        List<TextComponent> sortedSentences = textOperation.sortParagraphsBySentenceAmount(textComposite);
        int actual = sortedSentences.get(0).getChildren().size();
        Assert.assertEquals(actual, expected, "sorting paragraphs by sentences amount is invalid");
    }

    @Test
    public void findLongestWordSentences() {
        int expected = 1;
        List<TextComponent> sentences = textOperation.findLongestWordSentences(textComposite);
        int actual = sentences.size();
        Assert.assertEquals(actual, expected, "amount of sentences with the longest word is invalid");
    }

    @Test
    public void deleteSentences() {
        int expected = 3;
        List<TextComponent> sentences = textOperation.deleteSentences(textComposite, 20);
        int actual = sentences.size();
        Assert.assertEquals(actual, expected, "remaining amount of sentences is invalid");
    }

    @Test
    public void countSimilarWords() {
        int expected = 24;
        Map<String, Integer> similarWords = textOperation.countSimilarWords(textComposite);
        int actual = similarWords.size();
        Assert.assertEquals(actual, expected, "amount of repeating words is invalid");
    }

    @Test
    public void countConsonants() {
        long expected = 176;
        long actual = textOperation.countConsonants(textComposite.getChildren().get(0));
        Assert.assertEquals(actual, expected, "amount of consonants is invalid");
    }

    @Test
    public void countVowels() {
        long expected = 117;
        long actual = textOperation.countVowels(textComposite.getChildren().get(0));
        Assert.assertEquals(actual, expected, "amount of vowels is invalid");
    }
}
