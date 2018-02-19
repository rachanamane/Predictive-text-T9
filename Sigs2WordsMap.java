package predictive;


public class Sigs2WordsMap {


    public static void main(String[] args) {
        Dictionary dict = new MapDictionary("/usr/share/dict/words");
        for (String sig : args) {
            System.out.println(dict.signatureToWords(sig));
        }
    }
}
