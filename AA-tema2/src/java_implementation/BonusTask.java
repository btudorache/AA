import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class BonusTask extends Task {
    int n;
    int m;
    int[][] edges; // array to hold vertex of edges

    String answer;
    ArrayList<Integer> vertexCover;

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

        this.edges = new int[this.m][2];

        for (int i = 0; i < this.m; i++) {
            String[] values = reader.nextLine().split(" ");
            this.edges[i][0] = Integer.parseInt(values[0]);
            this.edges[i][1] = Integer.parseInt(values[1]);
        }

        this.vertexCover = new ArrayList<>();
    }

    @Override
    public void formulateOracleQuestion() throws IOException {
        int nr_variables = this.n;

        int first_clause_num = this.m;
        int second_clause_num = this.n;
        int nr_clauses = first_clause_num + second_clause_num;

        int total_weights = this.n;

        BufferedWriter bw = new BufferedWriter(new FileWriter(oracleInFilename));

        // write first line
        bw.write("p wcnf " + nr_variables + " " + nr_clauses + " " + (total_weights + 1) + "\n");

        // write first clause
        for (int i = 0; i < m; i++) {
			bw.write((total_weights + 1) + " " + this.edges[i][0] + " " + this.edges[i][1] + " 0\n");
        }

        // write second clause (soft clause)
        for (int i = 1; i <= this.n; i++) {
        	bw.write(1 + " " + -i + " 0\n");
        }

        bw.close();
    }

    @Override
    public void decipherOracleAnswer() throws IOException {
        Scanner reader = new Scanner(new File(oracleOutFilename));

 	    reader.nextLine();
        String[] values = reader.nextLine().split(" ");

        for (String value : values) {
        	int parsedValue = Integer.parseInt(value);
        	if (parsedValue > 0) {
        		this.vertexCover.add(parsedValue);

        	}
    	}
    }

    @Override
    public void writeAnswer() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outFilename));

        for (Integer member : this.vertexCover) {
        	bw.write(member + " ");
        }

        bw.close();
    }
}
