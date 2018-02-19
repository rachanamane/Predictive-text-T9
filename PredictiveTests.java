package predictive;

import Worksheet2.Tree;
import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;


public class PredictiveTests {

    ListDictionary listDictionary = new ListDictionary("/home/rachana/IdeaProjects/JavaPractice/src/predictive/sampleDict1.txt");
    MapDictionary mapDictionary =  new MapDictionary("/home/rachana/IdeaProjects/JavaPractice/src/predictive/sampleDict1.txt");
    TreeDictionary treeDictionary = new TreeDictionary("/home/rachana/IdeaProjects/JavaPractice/src/predictive/sampleDict1.txt");
    TreeDictionary treeDictionary1 = new TreeDictionary("/home/rachana/IdeaProjects/JavaPractice/src/predictive/sampleDict2.txt");

    @Test
    public void testPredictivePrototype(){
        assertEquals("4663",PredictivePrototype.wordToSignature("home"));
    }

    @Test
    public void testPredictivePrototype1() {
        Set<String> expected = new LinkedHashSet<>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        assertEquals(expected,PredictivePrototype.signatureToWords("2"));
    }

    @Test
    public void testListDictionary(){
        Set<String> expected = new LinkedHashSet<>();
        expected.add("any");
        assertEquals(expected,listDictionary.signatureToWords("269"));
    }

    @Test
    public void testListDictionary1(){
        Set<String> expected = new LinkedHashSet<>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        assertEquals(expected,listDictionary.signatureToWords("2"));
    }

    @Test
    public void testMapDictionary(){
        Set<String> expected = new LinkedHashSet<>();
        expected.add("a");
        expected.add("b");
        expected.add("c");
        assertEquals(expected,mapDictionary.signatureToWords("2"));
    }

    @Test
    public void testMapDictionary2(){
        Set<String> expected = new LinkedHashSet<>();
        expected.add("dog");
        assertEquals(expected, mapDictionary.signatureToWords("364"));
    }

    @Test
    public void testTreeDictionary(){
        Set<String> expected = new LinkedHashSet<>();
        expected.add("any");
        assertEquals(expected,treeDictionary.signatureToWords("269"));
    }

    @Test
    public void testTreeDictionary2(){
        Set<String> expected = new LinkedHashSet<>();
        expected.add("a");
        assertEquals(expected,treeDictionary1.signatureToWords("2"));
    }

    @Test
    public void testTreeDictionary3() {
        Set<String> expected = new LinkedHashSet<>();
        expected.add("an");
        expected.add("am");
        expected.add("ao");
        assertEquals(expected,treeDictionary1.signatureToWords("26"));
    }

    @Test
    public void testTreeDictionary4() {
        Set<String> expected = new LinkedHashSet<>();
        expected.add("any");
        assertEquals(expected,treeDictionary1.signatureToWords("269"));
    }


}
