#include <bits/stdc++.h>
#include <limits.h>
using namespace std;

struct Input {
    string name;
    vector<string> emails;

    Input(string _name, vector<string> _emails)
        : name(_name)
        , emails(_emails) { }
};

struct Result {
    string name;
    set<string> emails;

    Result(string _name, set<string> _emails)
        : name(_name)
        , emails(_emails) { }
};

int main() {
    long n;

    ifstream fin("adrese.in");
    fin >> n;

    // adj array for persons
    vector<vector<int>> person_neigh;

    // map used to build the adj array
    unordered_map<string, int> email_neigh;

    // all input data stored conveniently
    vector<Input> data;

    // read the input and build the person graph person_neigh
    for (int i = 0; i < n; i++) {
    	string name;
        int num_adr;
        fin >> name >> num_adr;

        vector<int> pers_adj;
        person_neigh.push_back(pers_adj);

        vector<string> mails;

        for (int j = 0; j < num_adr; j++) {
            string email;
            fin >> email;

            mails.push_back(email);

            if (email_neigh.find(email) == email_neigh.end()) {
                email_neigh[email] = i;
            } else {
                // if more persons have the same mails,
                // draw an 'edge' between them
                int first = email_neigh[email];
                person_neigh[first].push_back(i);
                person_neigh[i].push_back(first);
            }
        }
        data.push_back(Input(name, mails));
    }
    fin.close();

    vector<int> visited_people(n, 0);
    vector<vector<int>> components;
    stack<int> st;

    // apply dfs to calculate all conex components
    for (int i = 0; i < n; i++) {
        if (visited_people[i] == 0) {
            visited_people[i] = 1;

            vector<int> component;

            st.push(i);
            while (!st.empty()) {
                auto top = st.top();
                st.pop();
                component.push_back(top);
                for (size_t j = 0; j < person_neigh[top].size(); j++) {
                    int node = person_neigh[top][j];
                    if (visited_people[node] == 0) {
                        visited_people[node] = 1;
                        st.push(node);
                    }
                }
            }
            components.push_back(component);
        }
    }

    // build the results using the conex components
    vector<Result> res;
    for (size_t i = 0; i < components.size(); i++) {
        set<string> mails;
        int min_name = components[i][0];

        for (size_t j = 0; j < components[i].size(); j++) {
            int node = components[i][j];
            for (size_t k = 0; k < data[node].emails.size(); k++) {
                mails.insert(data[node].emails[k]);
            }

            if (data[node].name < data[min_name].name) {
                min_name = node;
            }
        }

        res.push_back(Result(data[min_name].name, mails));
    }

    // sort the results
    sort(res.begin(), res.end(),
        [](const Result& first, const Result& second) {
            if (first.emails.size() == second.emails.size()) {
                return first.name <= second.name;
            } else {
                return first.emails.size() <= second.emails.size();
            }
        });

    ofstream fout("adrese.out");
    fout << res.size() << "\n";
    for (size_t i = 0; i < res.size(); i++) {
        fout << res[i].name << " " << res[i].emails.size() << "\n";
        for (auto x : res[i].emails) {
            fout << x << "\n";
        }
    }
    fout.close();
    return 0;
}
