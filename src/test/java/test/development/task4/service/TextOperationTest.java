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
import org.testng.annotations.DataProvider;
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
        int expected = 2;
        List<TextComponent> sentences = textOperation.deleteSentences(textComposite, 24);
        int actual = sentences.size();
        Assert.assertEquals(actual, expected, "remaining amount of sentences is invalid");
    }

    @Test
    public void countSimilarWords() {
        int expected = 25;
        Map<String, Long> similarWords = textOperation.countSimilarWords(textComposite);
        int actual = similarWords.size();
        Assert.assertEquals(actual, expected, "amount of repeating words is invalid");
    }

    @Test(dataProvider = "sentences")
    public void countConsonants(TextComponent sentenceComponent) {
        long expected = 57;
        long actual = textOperation.countConsonants(sentenceComponent);
        Assert.assertEquals(actual, expected, "amount of consonants is invalid");
    }

    @Test(dataProvider = "sentences")
    public void countVowels(TextComponent sentenceComponent) {
        long expected = 42;
        long actual = textOperation.countVowels(sentenceComponent);
        Assert.assertEquals(actual, expected, "amount of vowels is invalid");
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
