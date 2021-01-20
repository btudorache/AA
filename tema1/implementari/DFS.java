import java.util.*;

public class DFS {
    private boolean[] marked;
    private Deque<Integer> topologicalOrder;

    public DFS(EdgeWeightedDigraph digraph) {
        topologicalOrder = new ArrayDeque<>();
        marked    = new boolean[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            if (!marked[v]) {
                runDFS(digraph, v);
            }
        }
    }

    private void runDFS(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                runDFS(G, w);
            }
        }
        topologicalOrder.push(v);
    }

    public Iterable<Integer> topologicalOrder() {
        return topologicalOrder;
    }
}
