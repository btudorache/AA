import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AcyclicShortestPath {
    private double[] distTo;

    public AcyclicShortestPath(EdgeWeightedDigraph digraph, int s) {
        distTo = new double[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        // visit vertices in topological order
        DFS dfs = new DFS(digraph);
        for (int v : dfs.topologicalOrder()) {
            for (DirectedEdge e : digraph.adj(v)) {
                relax(e);
            }
        }
    }

    // relax edge e
    private void relax(DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
}