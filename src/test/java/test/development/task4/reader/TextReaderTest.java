package test.development.task4.reader;

import com.development.task4.exception.TextException;
import com.development.task4.reader.impl.TextReaderImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TextReaderTest {
    private TextReaderImpl textReader;

    @BeforeTest
    public void init() {
        textReader = new TextReaderImpl();
    }

    @Test
    public void readText() throws TextException {
        String text = textReader.readText("data/text.txt");
        Assert.assertFalse(text.isEmpty());
    }
}
