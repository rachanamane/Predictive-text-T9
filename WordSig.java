package predictive;

/**
 * A class which pairs numeric signatures with words
 */
public class WordSig implements Comparable<WordSig>{


    private String words;
    private String signature;

    /**
     * Constructor which assigns word and computes the signature of the word and stores it.
     * @param words
     */
    public WordSig(String words){
        this.words =  words;
        this.signature = PredictivePrototype.wordToSignature(words);
    }

    /**
     * Method which allows efficient search for signatures. First compare with signatures,
     * If the signatures are same, then compare by word (Alphabetically)
     * @param ws
     * @return -1,0,1 according to whether the current object is less than, equal to or greater than the agrument
     * object.
     */
    public int compareTo(WordSig ws) {
        //if signature if different, compare by signature
        if (!this.signature.equals(ws.signature)){
            return this.signature.compareTo(ws.signature);
        }
        //if signature is same, compare by word
        else {
            return this.words.compareTo(ws.words);
        }
    }

    public String getSignature() {
        return signature;
    }

    public String getWords() {
        return words;
    }
}
