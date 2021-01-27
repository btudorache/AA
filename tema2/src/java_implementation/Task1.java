import java.io.*;
import java.util.Scanner;

public class Task1 extends Task {
    int n;
    int m;
    int k;
    int[][] edges; // array to hold vertex of edges

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

        this.edges = new int[this.m][2];
        for (int i = 0; i < this.m; i++) {
            String[] values = reader.nextLine().split(" ");
            this.edges[i][0] = Integer.parseInt(values[0]);
            this.edges[i][1] = Integer.parseInt(values[1]);
        }
    }

    @Override
    public void formulateOracleQuestion() throws IOException {
        int nr_variables = this.n * this.k;

        int first_clause_num = this.n;
        int second_clause_num = this.n * this.k * (this.k - 1) / 2;
        int third_clause_num = this.m * this.k;
        int nr_clauses = first_clause_num + second_clause_num + third_clause_num;

        BufferedWriter bw = new BufferedWriter(new FileWriter(oracleInFilename));

        // write first line
        bw.write("p cnf " + nr_variables + " " + nr_clauses + "\n");

        // write at least one color clauses
        for (int i = 1; i <= this.n; i++) {
            for (int j = 1; j <= this.k; j++) {
                int variable_ind = (i - 1) * this.k + j;
                bw.write(variable_ind + " ");
            }
            bw.write("0\n");
        }

        // write at most one color clauses
        for (int i = 1; i <= this.n; i++) {
            for (int j = 1; j <= this.k - 1; j++) {
                for (int k = j + 1; k <= this.k; k++) {
                	int first_variable_ind = (i - 1) * this.k + j;
                    int second_variable_ind = (i - 1) * this.k + k;
                    bw.write(-first_variable_ind + " " + -second_variable_ind + " 0\n");
                }
            }
        }

        // write different color clause
        for (int i = 0; i < this.m; i++) {
            int first_node = this.edges[i][0];
            int second_node = this.edges[i][1];

            int first_start_ind = this.k * (first_node - 1);
            int second_start_ind = this.k * (second_node - 1);
            for (int j = 1; j <= this.k; j++) {
                int first_variable_ind = first_start_ind + j;
                int second_variable_ind = second_start_ind + j;

                bw.write(-first_variable_ind + " " + -second_variable_ind + " 0\n");
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
                    int answer = variable_values[i] % this.k;
                    if (answer == 0) {
                        answer = this.k;
                    }
                    bw.write(answer + " ");
                }
            }
        }

        bw.close();
    }
}
