package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class TreeDictionary implements Dictionary{

    private final TreeNode treeDictionary;

    /**
     * Constructor takes the String path to the dictionary and populates the tree with words
     * @param path
     */
    TreeDictionary(String path){
        treeDictionary = new TreeNode();
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
     * Method to get a set of words corresponding to given signature
     * @param signature
     * @return set of words corresponding to given signature
     */
    public Set<String> signatureToWords(String signature){

        Set<String> words = signatureToWordsHelper(signature,treeDictionary);
        Set<String> resultSet = new LinkedHashSet<>();

        //Trim all the words which have prefix the same as the input signature
        for (String s : words) {
            resultSet.add(s.substring(0,signature.length()));
        }
        return resultSet;
    }

    /**
     * Helper method to get a set of words corresponding to a signature
     * @param signature, a given signature for which we need corresponding words
     * @param node
     * @return a set of all the words corresponding to signature
     */
    private Set<String> signatureToWordsHelper(String signature, TreeNode node){
        //if empty dictionary, return empty set
        if (node == null) {
            return new HashSet<>();
        }
        if (signature.isEmpty()) {
            return node.getWords();
        }

        //indexing
        int charPos = (int)signature.charAt(0) - 50;
        return signatureToWordsHelper(signature.substring(1),node.getChildren()[charPos]);
    }

    /**
     * Method to add word to a dictionary
     * @param word given word to be added to the dictionary
     */
    private void addToDictionary(String word) {
        addToDictionaryHelper(word,treeDictionary,0);
    }

    /**
     * Helper method to add words to a dictionary
     * @param word, a word that needs to be added to the tree dictionary
     * @param node, a node at which a word should be added
     * @param wordPos, position of a char
     */
    private void addToDictionaryHelper(String word, TreeNode node, int wordPos) {
        if (wordPos == word.length()) {
            return;
        }

        int charPos = (int)PredictivePrototype.wordToSignature(word).charAt(wordPos) - 50;

        //If treenode at charPos is empty/null, create a new TreeNode and add the word to that node
        if (node.getChildren()[charPos] == null) {
            node.getChildren()[charPos] = new TreeNode();
            node.getChildren()[charPos].addWord(word);
        } else {    //If the node has children at WordPos, then add the word at that position
            node.getChildren()[charPos].addWord(word);
        }

        //Recursively do this till wordPos is length of the word
        addToDictionaryHelper(word,node.getChildren()[charPos],wordPos+1);

    }

}
