/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package cs2.sampler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

import java.util.*;

class AppTest {
    static Sampler sample, sample2;
    static Set<String> correctWords = new HashSet<String>();
    @BeforeAll 
    static void setup() {
        sample = new Sampler("samplerTestData.tsv");
        sample2 = new Sampler("samplerTestData.tsv");
        correctWords.add("One");
        correctWords.add("Two");
        correctWords.add("Three");
        correctWords.add("Four");
        correctWords.add("Five");
        correctWords.add("Six");
        correctWords.add("Seven");
        correctWords.add("Eight");
        correctWords.add("Nine");
        correctWords.add("Ten");
    }

    @Test 
    void appCanStart() {
        assertNotNull(sample, "Sampler is not initializing");
    }

    @Test
    void runSamplerTester() {
        SamplerTester tester = new SamplerTester();
        tester.run("samplerTestData.tsv");

    }

    @Test
    void testGetWords() {
        Set<String> words = sample.getWords();
        assertTrue(words.containsAll(correctWords));
    }

    @Test
    void testGetCount() {
        assertEquals(1, sample.getCount("One"));
        assertEquals(2, sample.getCount("Two"));
        assertEquals(3, sample.getCount("Three"));
        assertEquals(4, sample.getCount("Four"));
        assertEquals(5, sample.getCount("Five"));
        assertEquals(6, sample.getCount("Six"));
        assertEquals(7, sample.getCount("Seven"));
        assertEquals(8, sample.getCount("Eight"));
        assertEquals(9, sample.getCount("Nine"));
        assertEquals(10, sample.getCount("Ten"));
        assertEquals(0, sample.getCount("Nothing"));
    }

    @Test
    void testTotalCount() {
        assertEquals(55, sample.totalCount());
    }
    @Test
    void testGetProbability() {
        assertEquals(1/55.0, sample.getProbability("One"));
        assertEquals(2/55.0, sample.getProbability("Two"));
        assertEquals(3/55.0, sample.getProbability("Three"));
        assertEquals(4/55.0, sample.getProbability("Four"));
        assertEquals(5/55.0, sample.getProbability("Five"));
        assertEquals(6/55.0, sample.getProbability("Six"));
        assertEquals(7/55.0, sample.getProbability("Seven"));
        assertEquals(8/55.0, sample.getProbability("Eight"));
        assertEquals(9/55.0, sample.getProbability("Nine"));
        assertEquals(10/55.0, sample.getProbability("Ten"));
        assertEquals(0.0, sample.getProbability("Nothing"));
    }
    @Test
    void testIncrement() {
        sample2.increment("Eleven");
        assertEquals(1, sample2.getCount("Eleven"));
        sample2.increment("Five");
        assertEquals(6,sample2.getCount("Five"));
        assertEquals(1/57.0, sample2.getProbability("Eleven"));
        assertEquals(6/57.0, sample2.getProbability("Five"));
    }
}
