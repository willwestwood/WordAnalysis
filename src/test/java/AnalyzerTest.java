import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    @BeforeEach
    void setUp() {
        String example = "Hello world & good morning. The \\date/ is * 18/05/2016. ******";
        analyzer = new Analyzer(example);
    }

    @Test
    void wordCount() {
        assertEquals(9, analyzer.WordCount());
    }

    @Test
    void getWordLengths() {
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 1);
        expected.put(2, 1);
        expected.put(3, 1);
        expected.put(4, 2);
        expected.put(5, 2);
        expected.put(7, 1);
        expected.put(10, 1);

        assertTrue(expected.equals(analyzer.GetWordLengths()));
    }

    @Test
    void getAverageWordLength() {
        assertTrue(new Float(4.556).compareTo(new Float(analyzer.GetAverageWordLength(3))) == 0);
    }

    @Test
    void getMostFrequentWordLengths() {
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(4, 2);
        expected.put(5, 2);

        assertTrue(expected.equals(analyzer.GetMostFrequentWordLengths()));
    }

    private Analyzer analyzer;
}