import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BellmanFord {
    private double[] distTo;
    private boolean[] onQueue;
    private Queue<Integer> queue;

    public BellmanFord(EdgeWeightedDigraph digraph, int s) {
        distTo  = new double[digraph.V()];
        onQueue = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        // Bellman-Ford algorithm
        queue = new LinkedList<>();
        queue.add(s);
        onQueue[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            onQueue[v] = false;
            relax(digraph, v);
        }

    }

    // relax vertex v and put other endpoints on queue if changed
    private void relax(EdgeWeightedDigraph digraph, int v) {
        for (DirectedEdge e : digraph.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                if (!onQueue[w]) {
                    queue.add(w);
                    onQueue[w] = true;
                }
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
}