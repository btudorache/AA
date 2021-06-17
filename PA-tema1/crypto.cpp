#include <bits/stdc++.h>
using namespace std;

struct Computer {
    int profit;
    int price;

    Computer(int _profit, int _price)
        : profit(_profit)
        , price(_price) { }
};

int main() {
    long n, b;
    vector<Computer> comps;

    ifstream fin("crypto.in");
    fin >> n >> b;
    for (int i = 0, profit, price; i < n; i++) {
        fin >> profit >> price;
        comps.push_back(Computer(profit, price));
    }
    fin.close();

    // sorting the computers
    sort(comps.begin(), comps.end(),
        [](const Computer& first, const Computer& second) {
            return first.profit < second.profit;
        });


    long neededSum;
    long cumulatedSum = comps[0].price;
    int maximumCoins = comps[0].profit;
    for (int i = 1; i < n; i++) {
        // if we are getting on a "new level of profit"
        if (comps[i].profit != comps[i - 1].profit) {
            // calculate the needed sum for upgrades
            neededSum = (comps[i].profit - comps[i - 1].profit) * cumulatedSum;

            // if we have enough money, update maximumCoins and remaining budget
            if (b - neededSum > 0) {
                maximumCoins += (comps[i].profit - comps[i - 1].profit);
                b -= neededSum;
            } else {
                break;
            }
        }
        cumulatedSum += comps[i].price;
    }

    // if we have budget remaining, spend it all on upgrades
    maximumCoins += (b / cumulatedSum);

    ofstream fout("crypto.out");
    fout << maximumCoins;
    fout.close();
    return 0;
}
