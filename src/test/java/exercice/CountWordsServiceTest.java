package exercice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountWordsServiceTest {

    private CountWordsService service;

    @BeforeEach
    public void setUp() {
        service = new CountWordsService();
    }

    @Test
    @DisplayName("process single line should count number of words")
    void processLine() {
        //empty line
        assertEquals(new Integer(0), service.getWordCount("foo"));
        service.processLine("");
        assertEquals(new Integer(0), service.getWordCount("foo"));
    }

    @Test
    void processLine2() {
        //line with one word
        service.processLine("foo");
        assertEquals(new Integer(1), service.getWordCount("foo"));
    }

    @Test
    void processLine3() {
        //line with multiple words
        service.processLine("foo bar");
        assertEquals(new Integer(1), service.getWordCount("foo"));
        assertEquals(new Integer(1), service.getWordCount("bar"));
    }

    @Test
    void processLine4() {
        //line with multiple words
        service.processLine("foo bar foo bar");
        assertEquals(new Integer(2), service.getWordCount("foo"));
        assertEquals(new Integer(2), service.getWordCount("bar"));
    }

    @Test
    void processLine5() {
        //line with multiple words
        service.processLine("foo& bar! foo-bar foo{ bar}");
        assertEquals(new Integer(2), service.getWordCount("foo"));
        assertEquals(new Integer(2), service.getWordCount("bar"));
        assertEquals(new Integer(1), service.getWordCount("foo-bar"));
    }

}