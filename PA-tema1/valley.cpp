#include <bits/stdc++.h>
#include <limits.h>
using namespace std;


int main() {
    long n;

    ifstream fin("valley.in");
    fin >> n;

    vector<int> heights(n, 0);

    int min = INT_MAX;
    int min_index = -1;
    for (int i = 0, height; i < n; i++) {
        fin >> height;

        // finding global minimum
        if (height < min) {
            min = height;
            min_index = i;
        }
        heights[i] = height;
    }
    fin.close();


    // calculate number of cuts based on minimum found
    long cuts = 0;
    if (min_index == 0) {
        cuts = heights[1] - heights[0];
    } else if (min_index == n - 1) {
        cuts = heights[n - 2] - heights[n - 1];
    } else {
        for (int i = 1; i < min_index; i++) {
            if (heights[i] > heights[i - 1]) {
                cuts += (heights[i] - heights[i - 1]);
                heights[i] = heights[i - 1];
            }
        }

        for (int i = n - 1; i > min_index; i--) {
            if (heights[i] < heights[i - 1]) {
                cuts += (heights[i - 1] - heights[i]);
                heights[i - 1] = heights[i];
            }
        }
    }

    ofstream fout("valley.out");
    fout << cuts;
    fout.close();
    return 0;
}
