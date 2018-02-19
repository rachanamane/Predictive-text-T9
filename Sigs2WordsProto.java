package predictive;

public class Sigs2WordsProto {

    /**
     * Command line main method for signatureToWords method
     * @param args, list of String arguments accepted from command line
     */
    public static void main(String[] args) {
         for (String sig : args) {
            System.out.println(PredictivePrototype.signatureToWords(sig));
         }
    }
}
