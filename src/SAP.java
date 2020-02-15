import edu.princeton.cs.algs4.*;

public class SAP {
    private Digraph G;
    private static final int INFINITY = Integer.MAX_VALUE;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = new Digraph(G);
    }

  class d{
        d(){
            System.out.println("hi");
        }
  }

    private int[] LA(int v, int w) {
        int[] LA = new int[2];
        int dist = INFINITY;
        int ancestor = -1;

        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(G, w);

        for (int vertex = 0; vertex < G.V(); vertex++) {
            if (bfs1.hasPathTo(vertex) && bfs2.hasPathTo(vertex)) {
                if (bfs1.distTo(vertex) + bfs2.distTo(vertex) < dist) {
                    dist = bfs1.distTo(vertex) + bfs2.distTo(vertex);
                    ancestor = vertex;
                }
            }
        }
        LA[0] = ancestor;
        LA[1] = (dist == INFINITY ? -1 : dist);
        return LA;
    }

    private int[] LA(Iterable<Integer> v, Iterable<Integer> w) {
        int[] LA = new int[2];
        int dist = INFINITY;
        int ancestor = -1;

        BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(G, w);

        for (int vertex = 0; vertex < G.V(); vertex++) {
            if (bfs1.hasPathTo(vertex) && bfs2.hasPathTo(vertex)) {
                if (bfs1.distTo(vertex) + bfs2.distTo(vertex) < dist) {
                    dist = bfs1.distTo(vertex) + bfs2.distTo(vertex);
                    ancestor = vertex;
                }
            }
        }
        LA[0] = ancestor;
        LA[1] = (dist == INFINITY ? -1 : dist);
        return LA;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In("digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = 232332;
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}