#include <bits/stdc++.h>
#include <limits.h>
using namespace std;

struct Mountain {
    int height;
    int cost;

    Mountain(int _height, int _cost)
        : height(_height)
        , cost(_cost) { }
};

int main() {
    long long n;
    vector<Mountain> mnts;
    mnts.push_back(Mountain(-1, -1));

    ifstream fin("ridge.in");
    fin >> n;
    for (int i = 0, height, cost; i < n; i++) {
        fin >> height >> cost;
        mnts.push_back(Mountain(height, cost));
    }
    fin.close();

    vector<vector<long long>> dp(n + 1, vector<long long>(3, 0));

    for (long long i = 0; i <= 2; i++) {
        dp[1][i] = i * mnts[i].cost;
    }

    for (long long i = 2; i <= n; i++) {
        for (long long j = 0; j <= 2; j++) {
            if (mnts[i].height - j < 0) {
                dp[i][j] = LLONG_MAX;
            } else if (mnts[i - 1].height == mnts[i].height - j) {
                dp[i][j] = min(dp[i - 1][1], dp[i - 1][2]) + j * mnts[i].cost;
            } else if (mnts[i - 1].height - 1 == mnts[i].height - j) {
                dp[i][j] = min(dp[i - 1][0], dp[i - 1][2]) + j * mnts[i].cost;
            } else if (mnts[i - 1].height - 2 == mnts[i].height - j) {
                dp[i][j] = min(dp[i - 1][0], dp[i - 1][1]) + j * mnts[i].cost;
            } else {
                dp[i][j] = min(dp[i - 1][0], min(dp[i - 1][1], dp[i - 1][2]));
                dp[i][j] += j * mnts[i].cost;
            }
        }
    }

    ofstream fout("ridge.out");
    fout << min(dp[n][0], min(dp[n][1], dp[n][2]));
    fout.close();
    return 0;
}
