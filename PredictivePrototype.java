package predictive;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class PredictivePrototype {

    /**
     * This method converts a word to a signature as per the T9 predictive text.
     *
     * @param word input word
     * @return signature of a word
     */
    public static String wordToSignature(String word) {

        //We are using StringBuilder instead of String because String is immutable,
        //which means that whenever we try to alter its value, another object gets created.
        //StringBuilder is mutable, so we can alter its value without having to create another
        //object. StringBuilder is more efficient than StringBuffer because it is not synchronised.
        //But in this method, we do not have to worry because there is only one thread accessing the object
        //at one time.
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            String c = word.substring(i, i + 1);
            if (Pattern.matches("[ABCabc]", c)) {
                sb.append(2);
            }
            if (Pattern.matches("[DEFdef]", c)) {
                sb.append(3);
            }
            if (Pattern.matches("[GHIghi]", c)) {
                sb.append(4);
            }
            if (Pattern.matches("[JKLjkl]", c)) {
                sb.append(5);
            }
            if (Pattern.matches("[MNOmno]", c)) {
                sb.append(6);
            }
            if (Pattern.matches("[PQRSpqrs]", c)) {
                sb.append(7);
            }
            if (Pattern.matches("[TUVtuv]", c)) {
                sb.append(8);
            }
            if (Pattern.matches("[WXYZwxyz]", c)) {
                sb.append(9);
            }
            //Any non-alphabetic characters are replaced by a space
            if (Pattern.matches("[^A-Za-z]", c)) {
                sb.append(" ");
            }
        }
        return sb.toString();

    }

    /**
     * Method which takes a signature and return a set of all the possible words corresponding to the signature.
     * This method does not store a dictionary in the Java program. It is inefficient because for every signature
     * the program converts the all the words in the external dictionary to a signature. This is a repeated operation.
     *
     * @param signature is a string of signature
     * @return a set of words which correspond to the given signature
     */
    public static Set<String> signatureToWords(String signature) {

        Set<String> possibleWords = new LinkedHashSet<>();
        File file = new File("/home/rachana/IdeaProjects/JavaPractice/src/predictive/sampleDict1.txt");
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String word = sc.nextLine();
                //Check if the length of the word and length of a signature is same in the dictionary
                if (word.length() == signature.length() && isValidWord(word)) {
                    if (wordToSignature(word.toLowerCase()).equals(signature)) {
                        possibleWords.add(word.toLowerCase());
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return possibleWords;
    }

    /**
     * Helper method to check whether a given word is a valid word according to the given criteria
     *
     * @param word, given word
     * @return true if it consists of only letters.
     */
    public static boolean isValidWord(String word) {
        return word.chars().allMatch(Character::isLetter);
    }

}
