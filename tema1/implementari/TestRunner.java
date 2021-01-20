import java.io.*;
import java.util.Scanner;

public class TestRunner {
    public static void main(String[] args) throws IOException {
        File f = new File("in/");
        String[] fileList = f.list();

        assert fileList != null;
        for (String str : fileList) {
            File file = new File("in/" + str);
            Scanner reader = new Scanner(file);
            String[] inputValues = reader.nextLine().split(" ");

            int V = Integer.parseInt(inputValues[0]);

            int E = Integer.parseInt(inputValues[1]);
            int s = Integer.parseInt(inputValues[2]);

            EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(V);

            for (int i = 0; i < E; i++) {
                String line = reader.nextLine();
                String[] values = line.split(" ");
                DirectedEdge edge = new DirectedEdge(Integer.parseInt(values[0]),
                        Integer.parseInt(values[1]),
                        Double.parseDouble(values[2]));
                digraph.addEdge(edge);
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter("out/" + str.substring(0, str.length() - 3) + ".out" ));

            if (str.startsWith("test0")) {
            	double starttime = System.nanoTime();
                Dijkstra sp = new Dijkstra(digraph, s);
                double endtime = System.nanoTime();
                double result = (endtime - starttime) / 1000000;
                System.out.println("Speed for Dijkstra test no. " + str.substring(5, 6) + ": " + result);
                for (int v = 0; v < digraph.V(); v++) {
                    if (sp.hasPathTo(v)) {
                        bw.write((int) sp.distTo(v) + "\n");
                    }
                    else {
                        bw.write( "-1\n");
                    }

                }
            } else if (str.startsWith("test1")) {
            	double starttime = System.nanoTime();
                BellmanFord sp = new BellmanFord(digraph, s);
                double endtime = System.nanoTime();
                double result = (endtime - starttime) / 1000000;
                System.out.println("Speed for BellmanFord test no. " + str.substring(5, 6) + ": " + result);
                for (int v = 0; v < digraph.V(); v++) {
                    if (sp.hasPathTo(v)) {
                        bw.write((int) sp.distTo(v) + "\n");
                    }
                    else {
                        bw.write( "-1\n");
                    }
                }
            } else if (str.startsWith("test2")) {
            	double starttime = System.nanoTime();
                AcyclicShortestPath sp = new AcyclicShortestPath(digraph, s);
                double endtime = System.nanoTime();
                double result = (endtime - starttime) / 1000000;
                System.out.println("Speed for Acyclic test no. " + str.substring(5, 6) + ": " + result);
                for (int v = 0; v < digraph.V(); v++) {
                    if (sp.hasPathTo(v)) {
                        bw.write((int) sp.distTo(v) + "\n");
                    }
                    else {
                        bw.write( "-1\n");
                    }
                }
            }
            
            bw.close();
        }
    }
}
