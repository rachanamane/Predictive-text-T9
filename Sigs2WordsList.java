package predictive;

public class Sigs2WordsList {
    public static void main(String[] args) {
        Dictionary dict = new ListDictionary("/usr/share/dict/words");
        for (String sig : args) {
            System.out.println(dict.signatureToWords(sig));
        }
    }
}
