package predictive;

public class Sigs2WordsTree {

    public static void main(String[] args) {

        Dictionary dict = new TreeDictionary("/usr/share/dict/words");
        for (String sig : args) {
            System.out.println(dict.signatureToWords(sig));
        }
    }
}
