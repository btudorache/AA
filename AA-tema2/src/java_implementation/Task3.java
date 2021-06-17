import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Task3 extends Task {
    String task2InFilename;
    String task2OutFilename;

    int n;
    int m;
    boolean[][] adj_matrix;
    ArrayList<Integer> complementMembersToArrest;

    @Override
    public void solve() throws IOException, InterruptedException {
        task2InFilename = inFilename + "_t2";
        task2OutFilename = outFilename + "_t2";
        Task2 task2Solver = new Task2();
        task2Solver.addFiles(task2InFilename, oracleInFilename, oracleOutFilename, task2OutFilename);
        readProblemData();

        
        int minimumVertexCoverSize = this.n;
        while (true) {
            this.reduceToTask2(--minimumVertexCoverSize);

            task2Solver.solve();

            if (this.areThereMembersToArrest()) {
                this.extractAnswerFromTask2();
                break;
            }
        }  
        writeAnswer();
    }

    @Override
    public void readProblemData() throws IOException {
        Scanner reader = new Scanner(new File(inFilename));

        String[] inputValues = reader.nextLine().split(" ");

        this.n = Integer.parseInt(inputValues[0]);
        this.m = Integer.parseInt(inputValues[1]);

        this.adj_matrix = new boolean[this.n + 1][this.n + 1]; // adiacency starts from 1 and skips 0 index for convenience
        for (int i = 0; i < this.m; i++) {
            String[] values = reader.nextLine().split(" ");
            int from = Integer.parseInt(values[0]);
            int to = Integer.parseInt(values[1]);

            this.adj_matrix[from][to] = true;
            this.adj_matrix[to][from] = true;
        }

        this.complementMembersToArrest = new ArrayList<>();
    }

    public void reduceToTask2(int sizeOfClique) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(task2InFilename));

        int complementEdgeSize = this.n * (this.n - 1) / 2 - m;
        bw.write(this.n + " " + complementEdgeSize + " " + sizeOfClique + "\n");
        for (int i = 1; i <= this.n - 1; i++) {
            for (int j = i + 1; j <= this.n; j++) {
                if (this.adj_matrix[i][j] == false) {
                    bw.write(i + " " + j + "\n");
                }
            }
        }

        bw.close();
    }

    public void extractAnswerFromTask2() throws IOException {
        Scanner reader = new Scanner(new File(task2OutFilename));

        reader.nextLine();
        String[] inputValues = reader.nextLine().split(" ");

        for (String complementMember : inputValues) {
            this.complementMembersToArrest.add(Integer.parseInt(complementMember));
        }
    }

    public boolean areThereMembersToArrest() throws IOException {
        File file = new File(task2OutFilename);
        Scanner reader = new Scanner(file);

        String hasFoundClique = reader.nextLine();
        if (hasFoundClique.equals("True")) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void writeAnswer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outFilename));

        for (int i = 1; i <= this.n; i++) {
            if (!this.complementMembersToArrest.contains(i)) {
                bw.write(i + " ");
            }
        }

        bw.close();
    }
}
