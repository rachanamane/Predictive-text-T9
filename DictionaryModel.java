package predictive;

import java.io.IOException;
import java.util.*;

public class DictionaryModel extends Observable implements DictionaryModelInterface {

    private TreeDictionary dictionary;
    private List<String> sentence;
    private StringBuilder word;
    private Iterator<String> iterator;
    private String currentWord, lastWord;

    DictionaryModel(String dictionaryFile) throws IOException{
        this.dictionary = new TreeDictionary(dictionaryFile);
        this.sentence = new ArrayList<>();
        this.word = new StringBuilder();
        this.currentWord = "";
        this.lastWord = "";

    }

    DictionaryModel() throws IOException{
        this("/home/rachana/IdeaProjects/JavaPractice/src/predictive/sampleDict1.txt");
    }

    /**
     * To get the list of words typed in so far, including the last word which has not yet
     * been accepted
     * @return list of words
     */
    public List<String> getMessage() {

        List<String> result = new ArrayList<>();
        result.addAll(sentence);
        result.add(currentWord);

        return result;
    }

    /**
     * Adds a numeric key that has been typed in by user
     * @param key
     */
    public void addCharacter(char key) {
        this.word.append(key);
        this.iterator = dictionary.signatureToWords(this.word.toString()).iterator();
        this.currentWord = this.iterator.next();
        setChanged();
        notifyObservers(this.sentence + this.currentWord);
    }

    /**
     * Removes last character type in.
     */
    public void removeLastCharacter(){
        if (currentWord != null && currentWord.length() >= 1){
            currentWord = currentWord.substring(0,currentWord.length()-1);
            this.iterator = this.dictionary.signatureToWords(PredictivePrototype.wordToSignature(this.currentWord)).iterator();
            if (currentWord.length() <= 0){
                //reset variables
                this.word = new StringBuilder();
                this.currentWord = "";
                this.lastWord = "";
                this.iterator = new Iterator<String>() {
                    @Override
                    public boolean hasNext() {
                        return false;
                    }

                    @Override
                    public String next() {
                        return null;
                    }
                };
            }
        }

        setChanged();
        notifyObservers(this.sentence + this.currentWord);

    }

    //pressed *

    /**
     * Cycles through the possible matches for the current word (Changes the current word to the next match
     * if there is one, or goes back to the first match otherwise.)
     */
    public void nextMatch(){

        if (this.iterator.hasNext()) {
            this.currentWord = this.iterator.next();
            this.lastWord = this.currentWord;
        } else {
            this.iterator = this.dictionary.signatureToWords(PredictivePrototype.wordToSignature(this.lastWord)).iterator();
            this.currentWord = this.iterator.next();
        }
        setChanged();
        notifyObservers(this.sentence + this.currentWord);
    }

    //pressed 0

    /**
     * Accepts the current matched word and adds it to the composed message
     */
    public void acceptWord(){
        this.word = new StringBuilder();
        this.sentence.add(currentWord);
        this.sentence.add(" ");

        //Reset the variables
        this.currentWord = "";
        this.lastWord = "";
        this.iterator = new Iterator<String>(){
            @Override
            public boolean hasNext(){
                return false;
            }

            public String next(){
                return null;
            }
        };
        setChanged();
        notifyObservers(this.sentence);
    }
}
