#include <bits/stdc++.h>
#include <limits.h>
using namespace std;


int combinePieces(vector<int> &solution, vector<int> &domain,
                  int stop, int t, vector<int> &result) {
    // save initial combination values conveniently
    vector<int> dom;
    for (int i = 0; i < stop; ++i) {
        dom.push_back(domain[solution[i]]);
    }

    // build sums
    unordered_set<int> sums;
    for (size_t i = 0; i < dom.size(); i++) {
        sums.insert(dom[i]);
    }

    for (int i = 0; i < t - 1; i++) {
        vector<int> tmp;
        for (auto x : sums) {
            tmp.push_back(x);
        }

        for (size_t j = 0; j < tmp.size(); j++) {
            for (size_t k = 0; k < dom.size(); k++) {
                sums.insert(tmp[j] + dom[k]);
            }
        }
    }

    // mark frequency vector with sums calculated
    vector<int> consec(dom[dom.size() - 1] * t + 2, 0);
    for (auto x : sums) {
        consec[x] = 1;
    }

    // calculate longest subsequence
    int longest_subsec = -1;
    int curr_subsec = 0;
    for (size_t i = 0; i < consec.size(); i++) {
        if (consec[i] == 0) {
            if (curr_subsec > longest_subsec) {
                longest_subsec = curr_subsec;
                result.clear();
                for (size_t j = 0; j < dom.size(); j++) {
                    result.push_back(dom[j]);
                }
            }
            curr_subsec = 0;
        } else {
            curr_subsec++;
        }
    }

    return longest_subsec;
}

void back(int step, int stop, vector<int> &domain, vector<int> &solution,
          int t, int* longest, vector<int> &finRes) {
    // for every combination, check if we have a better result
    if (step == stop) {
        vector<int> result;
        int res = combinePieces(solution, domain, stop, t, result);

        // if the result is found is better, store it
        if (res > *longest) {
            finRes.clear();
            for (size_t i = 0; i < result.size(); i++) {
                finRes.push_back(result[i]);
            }
            *longest = res;
        }
        return;
    }

    // generate all combinations
    unsigned i = step > 1 ? solution[step - 1] + 1 : 1;
    for (; i < domain.size(); ++i) {
        solution[step] = i;
        back(step + 1, stop, domain, solution, t, longest, finRes);
    }
}

int main() {
    int n, k, t;

    ifstream fin("lego.in");
    fin >> n >> k >> t;
    fin.close();

    vector<int> domain(n), solution(k), result;
    solution.push_back(1);
    for (int i = 0; i < n; ++i) {
        domain[i] = i + 1;
    }

    int longest = -1;

    // generate solutions in variables longest and result
    back(1, k, domain, solution, t, &longest, result);

    ofstream fout("lego.out");
    fout << longest << "\n";
    for (size_t i = 0; i < result.size(); i++) {
        fout << result[i] << " ";
    }
    fout.close();
    return 0;
}
