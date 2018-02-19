package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MapDictionary implements Dictionary{

    private final Map<String,Set<String>> storedDictionary;

    /**
     * Constructor which takes String path to the dictionary, reads stores it in a generic multivalued Map.
     * @param path
     */
    MapDictionary(String path) {
        storedDictionary = new HashMap<>();
        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine() ) {
                String word = sc.nextLine().toLowerCase();
                if (PredictivePrototype.isValidWord(word)) {
                    addToDictionary(word);
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which returns a set of words corresponding to given signature
     * @param signature
     * @return a set of words corresponding to given signature
     */
    @Override
    public Set<String> signatureToWords(String signature){
        Set<String> words = storedDictionary.get(signature);
        return words != null ? words : new HashSet<>();
    }

    /**
     * Method to add words to a dictionary Map. If the Map already contains the signature,
     * add that word to corresponding set of words for that signature. Else, put the (key,value) pair in the
     * Map.
     * @param word
     */
    private void addToDictionary(String word){
        String signature = PredictivePrototype.wordToSignature(word);
        if (storedDictionary.containsKey(signature)){
            storedDictionary.get(signature).add(word);
        } else {
            Set<String> wordSet = new LinkedHashSet<>();
            wordSet.add(word);
            storedDictionary.put(signature, wordSet);
        }
    }


}
