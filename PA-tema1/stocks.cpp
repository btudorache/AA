#include <bits/stdc++.h>
using namespace std;

struct Stock {
    int curr;
    int minValue;
    int maxValue;

    Stock(int _curr, int _minValue, int _maxValue)
        : curr(_curr)
        , minValue(_minValue)
        , maxValue(_maxValue) { }
};

int main() {
    int n, b, l;
    vector<Stock> stocks;
    stocks.push_back(Stock(-1, -1, -1));

    ifstream fin("stocks.in");
    fin >> n >> b >> l;
    for (int i = 0, curr, minValue, maxValue; i < n; i++) {
        fin >> curr >> minValue >> maxValue;
        stocks.push_back(Stock(curr, minValue, maxValue));
    }
    fin.close();

    // declaring a dp 3d array for a backpack type problem
    vector<vector<vector<int>>>
    dp(n + 1, vector<vector<int>>(b + 1, vector<int>(l + 1, 0)));

    for (int i = 1; i <= n; i++) {
        for (int cap = 0; cap <= b; cap++) {
            for (int min = 0; min <= l; min++) {
                dp[i][cap][min] = dp[i - 1][cap][min];
                if (cap - stocks[i].curr >= 0
                    && min - stocks[i].curr + stocks[i].minValue >= 0) {
                    int first = dp[i - 1][cap - stocks[i].curr]
                                  [min - stocks[i].curr + stocks[i].minValue];
                    first += (stocks[i].maxValue - stocks[i].curr);

                    dp[i][cap][min] = max(first, dp[i][cap][min]);
                }
            }
        }
    }

    ofstream fout("stocks.out");
    fout << dp[n][b][l];
    fout.close();
    return 0;
}
