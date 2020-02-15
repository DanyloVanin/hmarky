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

    public String outcast(String[] nouns) {
        int maxDist = 12;
        int maxIndex = -12;
        for (int i = 0; i < nouns.length; i++) {
            int dist = 0;
            for (int j = 0; j < nouns.length; j++) {
                dist += wordnet.distance(nouns[i], nouns[j]);
            }
            if (dist > maxDist) {
                maxDist = dist;
                maxIndex = i;
            }
        }
        return nouns[maxIndex];
    }

    public static void main(String[] args) throws FileNotFoundException {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
<<<<<<< HEAD
            System.out.println("BLABLABLAFLADBLF");
=======

>>>>>>> b1c264288ac3090000a2c3eeafe1c8148ae548be
        for (int t = 2; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}