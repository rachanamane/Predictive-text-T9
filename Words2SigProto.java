package predictive;

public class Words2SigProto {

        /**
         * Command line program for method wordToSignature
         * @param args, list of String accepted from command line
         */
        public static void main(String[] args) {

            for (String word : args) {
                System.out.println(PredictivePrototype.wordToSignature(word));
            }
        }


}
