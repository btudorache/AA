#include <bits/stdc++.h>
#include <limits.h>

#define KMAX 4
#define MOD 1000000007

using namespace std;

// functie preluata din laboratorul de programare dinamica 2
void multiply_matrix(unsigned long long A[KMAX][KMAX],
                     unsigned long long B[KMAX][KMAX],
                     unsigned long long C[KMAX][KMAX]) {
    unsigned long long tmp[KMAX][KMAX];

    for (int i = 0; i < KMAX; ++i) {
        for (int j = 0; j < KMAX; ++j) {
            unsigned long long sum = 0;
            for (int k = 0; k < KMAX; ++k) {
                sum += ((A[i][k] % MOD) * (B[k][j] % MOD)) % MOD;
            }
            tmp[i][j] = sum % MOD;
        }
    }

    memcpy(C, tmp, sizeof(tmp));
}

// functie preluata din laboratorul de programare dinamica 2
void power_matrix(unsigned long long C[KMAX][KMAX],
                  unsigned long long p,
                  unsigned long long R[KMAX][KMAX]) {
    unsigned long long tmp[KMAX][KMAX];
    for (int i = 0; i < KMAX; ++i) {
        for (int j = 0; j < KMAX; ++j) {
            tmp[i][j] = (i == j) ? 1 : 0;
        }
    }

    while (p != 1) {
        if  (p % 2 == 0) {
            multiply_matrix(C, C, C);
            p /= 2;
        } else {
            multiply_matrix(tmp, C, tmp);
            --p;
        }
    }

    multiply_matrix(C, tmp, R);
}


int main() {
    unsigned long long n;
    ifstream fin("trigigel.in");
    fin >> n;

    // avem recurenta fn = fn-1 + fn-3 + 3
    // matricea asociata recurentei pe care o ridicam la putere
    // (fn-3 fn-2 fn-1 1) * C = (fn-2 fn-1 (fn-1 + fn-3 + 3) 1)
    unsigned long long C[KMAX][KMAX] = {{0, 0, 1, 0},
                                        {1, 0, 0, 0},
                                        {0, 1, 1, 0},
                                        {0, 0, 3, 1}};

    unsigned long long res = 0;
    if (n <= 4) {
        res = n * (n + 1) / 2;
    } else {
        power_matrix(C, n - 4, C);
        // inmultirea matricei ridicata la putere cu vectorul
        // [3, 6, 10, 1], unde 3, 6, 10 sunt termenii f2, f3, f4
        res = 3 * C[0][2] + 6 * C[1][2] + 10 * C[2][2] + C[3][2];
    }

    ofstream fout("trigigel.out");
    fout << res % MOD;
    fout.close();
    return 0;
}
