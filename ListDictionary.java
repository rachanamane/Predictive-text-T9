package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ListDictionary implements Dictionary{

    private final ArrayList<WordSig> storedDictionary;
    private final ArrayList<String> sortedListOfSignatures;

    /**
     * Constructor which takes String path to the dictionary, reads stores it in an ArrayList.
     * @param path, path to the dictionary
     */
    ListDictionary(String path) {
        storedDictionary = new ArrayList<>();
        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine() ) {
                String word = sc.nextLine().toLowerCase();
                //If it is a valid word, we store its signature and word in the arraylist(WordSig) of WordSig
                if (PredictivePrototype.isValidWord(word)) {
                   WordSig wordSigPair = new WordSig(word);
                    storedDictionary.add(wordSigPair);
                }
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Sorting the dictionary only once
        Collections.sort(storedDictionary);

        sortedListOfSignatures = new ArrayList<>();

        for (WordSig s: storedDictionary) {
            sortedListOfSignatures.add(s.getSignature());
        }

    }

    /**
     * Method to get a set of words corresponding to a given signature
     * @param signature
     * @return, a set of possible words from a given signature
     */
    @Override
    public Set<String> signatureToWords(String signature){

        //We use LinkedHashSet instead of HashSet is because LinkedHashSet is ordered,
        //as in, it maintains the insertion order. However, its not specifically mentioned in the problem statement
        //whether the output should be in alphabetical order.
        Set<String> words = new LinkedHashSet<>();

        //Perform binary search on sortedListOfSignatures, it will give us the first occurrence of signature
        int indexOfFirstMatch = Collections.binarySearch(sortedListOfSignatures, signature);

        //We have this condition because when binary search fails, (Doesn't find the value)the return value
        //indicates the insertion point. But in our case, when binary search fails to search, we will return
        //an empty set.
        if (indexOfFirstMatch < 0 || indexOfFirstMatch >= sortedListOfSignatures.size()){
            return new HashSet<>();
        }

        int upperIndex = indexOfFirstMatch;
        int lowerIndex = indexOfFirstMatch;

        while (upperIndex < storedDictionary.size() && storedDictionary.get(upperIndex).getSignature().equals(signature)){
            upperIndex++;
        }
        while (lowerIndex >= 0 && storedDictionary.get(lowerIndex).getSignature().equals(signature)){
            lowerIndex--;
        }

        for (int i = lowerIndex+1; i< upperIndex; i++) {
            words.add(storedDictionary.get(i).getWords().toLowerCase());
        }
        return words;

    }
}
