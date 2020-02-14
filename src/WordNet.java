import edu.princeton.cs.algs4.Digraph;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    private final int NSYNSETS;
    private HashMap<String, List<Integer>> synsetMap;
    private List<String> synsetList;
    private HashSet<String> totalSynsetSet;
    private Digraph hypernymsGraph;
    private SAP hypernymSAP;


    public WordNet(String synsets, String hypernyms) throws FileNotFoundException {
        File synsetsFile = new File(synsets);
        File hypernymsFile = new File(hypernyms);
        synsetMap = new HashMap<String, List<Integer>>();
        synsetList = new ArrayList<String>();
        totalSynsetSet = new HashSet<String>();

        Scanner sc = new Scanner(synsetsFile);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] words = line.split(",");
            String[] subwords = words[1].split(" ");
            for (String word : subwords) {
                if (synsetMap.containsKey(word)) {
                    synsetMap.get(word).add(Integer.parseInt(words[0]));
                }
                else {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(Integer.parseInt(words[0]));
                    synsetMap.put(word, temp);
                }
                totalSynsetSet.add(word);
            }
            synsetList.add(words[1]);
        }
        NSYNSETS = synsetList.size();
        hypernymsGraph = new Digraph(NSYNSETS);

        sc = new Scanner(hypernymsFile);
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] numbers = line.split(",");
            List<Integer> temp = new ArrayList<Integer>();
            for (String number : numbers) temp.add(Integer.parseInt(number));
            for (int i = 1; i < numbers.length; i++) {
                hypernymsGraph.addEdge(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[i]));
            }
        }
        hypernymSAP = new SAP(hypernymsGraph);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return totalSynsetSet;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new NullPointerException();
        return synsetMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new NullPointerException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        return hypernymSAP.length(synsetMap.get(nounA), synsetMap.get(nounB));
    }

    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null) throw new NullPointerException();
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        return synsetList.get(hypernymSAP.ancestor(synsetMap.get(nounA), synsetMap.get(nounB)));
    }

    public static void main(String[] args) throws FileNotFoundException {
        WordNet wordnet = new WordNet(args[0], args[1]);

    }
}

