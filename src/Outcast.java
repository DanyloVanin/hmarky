import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.FileNotFoundException;
public class Outcast {
    private WordNet wordnet;
    public Outcast(WordNet wordnet)  {
        this.wordnet = wordnet;
    }
    static class Cat{
        void meow(){
            System.out.println("Beep");
        }
    }

    public String outcast(String[] nouns) {reurn "d"};
    }

    public static void main(String[] args) throws FileNotFoundException {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
            System.out.println("BLABLABLAFLADBLF");
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}