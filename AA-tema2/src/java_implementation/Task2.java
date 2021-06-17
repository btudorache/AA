import java.io.*;
import java.util.Scanner;

public class Task2 extends Task {
    int n;
    int m;
    int k;
    boolean[][] adj_matrix;

    String answer;
    int[] variable_values;

    @Override
    public void solve() throws IOException, InterruptedException {
        readProblemData();
        formulateOracleQuestion();
        askOracle();
        decipherOracleAnswer();
        writeAnswer();
    }

    @Override
    public void readProblemData() throws IOException {
        Scanner reader = new Scanner(new File(inFilename));

        String[] inputValues = reader.nextLine().split(" ");

        this.n = Integer.parseInt(inputValues[0]);
        this.m = Integer.parseInt(inputValues[1]);
        this.k = Integer.parseInt(inputValues[2]);

        this.adj_matrix = new boolean[this.n + 1][this.n + 1]; // adiacency starts from 1 and skips 0 index for convenience
        for (int i = 0; i < this.m; i++) {
            String[] values = reader.nextLine().split(" ");
            int from = Integer.parseInt(values[0]);
            int to = Integer.parseInt(values[1]);

            this.adj_matrix[from][to] = true;
            this.adj_matrix[to][from] = true;
        }
    }

    @Override
    public void formulateOracleQuestion() throws IOException {
        int nr_variables = this.n * this.k;

        int first_clause_num = this.k;
        int second_clause_num = this.n * (this.k * (this.k - 1) / 2);
        int third_clause_num = (this.n * (this.n - 1) / 2 - this.m) * (this.k * (this.k - 1));
        int nr_clauses = first_clause_num + second_clause_num + third_clause_num;

        BufferedWriter bw = new BufferedWriter(new FileWriter(oracleInFilename));

        // write first line
        bw.write("p cnf " + nr_variables + " " + nr_clauses + "\n");

        // some node is the i-th node of the clique
        for (int i = 1; i <= this.k; i++) {
        	for (int j = 1; j <= this.n; j++) {
        		int variable_ind = this.k * (j - 1) + i;
        		bw.write(variable_ind + " ");
        	}
        	bw.write("0\n");
        }

        // no node is both the j-th and k-th node of the clique
        for (int i = 1; i <= this.n; i++) {
            for (int j = 1; j <= this.k - 1; j++) {
                for (int k = j + 1; k <= this.k; k++) {
                    int first_variable_ind = (i - 1) * this.k + j;
                    int second_variable_ind = (i - 1) * this.k + k;
                    bw.write(-first_variable_ind + " " + -second_variable_ind + " 0\n");
                }
            }
        }

        // if there is no edge from k to l then nodes k and l cannot be in the same clique
        for (int i = 1; i <= this.k; i++) {
        	for (int j = 1; j <= this.k; j++) {
        		for (int k = 1; k <= this.n - 1; k++) {
        			for (int l = k + 1; l <= this.n; l++) {
        				if (this.adj_matrix[k][l] == false && i != j) {
        					int first_vertex_pos = (k - 1) * this.k + i;
        					int second_vertex_pos = (l - 1) * this.k + j;
        					bw.write(-first_vertex_pos + " " + -second_vertex_pos + " 0\n");
        				}
        			}
        		}
        	}
        }
        bw.close();
    }

    @Override
    public void decipherOracleAnswer() throws IOException {
        Scanner reader = new Scanner(new File(oracleOutFilename));

        this.answer = reader.nextLine();
        if (this.answer.equals("True")) {
            int num_variables = Integer.parseInt(reader.nextLine());

            this.variable_values = new int[num_variables];
            String[] values = reader.nextLine().split(" ");
            for (int i = 0; i < num_variables; i++) {
                this.variable_values[i] = Integer.parseInt(values[i]);
            }
        }
    }

    @Override
    public void writeAnswer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outFilename));

        bw.write(this.answer + "\n");
        if (this.answer.equals("True")) {
            for (int i = 0; i < this.variable_values.length; i++) {
                // write answer only if variable is true
                if (variable_values[i] > 0) {
                    int answer;
                    if (variable_values[i] % this.k == 0) {
                        answer = variable_values[i] / this.k;
                    } else {
                        answer = (variable_values[i] / this.k) + 1;
                    }
                    bw.write(answer + " ");
                }
            }
        }

        bw.close();
    }
}
