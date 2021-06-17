#include <bits/stdc++.h>
#include <limits.h>
using namespace std;

struct Coord {
    int x;
    int y;

    Coord(int _x, int _y)
        : x(_x)
        , y(_y) { }
};

int main() {
    long n, m, startX, startY;

    ifstream fin("poduri.in");
    fin >> n >> m >> startX >> startY;
    vector<vector<char>> bridges(n + 2, vector<char>(m + 2, 0));

    for (int i = 1; i <= n; i++) {
    	for (int j = 1; j <= m; j++) {
    		fin >> bridges[i][j];
    	}
    }
    fin.close();

    // add extra row and column
    vector<vector<int>> distances(n + 2, vector<int>(m + 2, INT_MAX));
    vector<vector<int>> visited(n + 2, vector<int>(m + 2, 0));

    Coord start = Coord(startX, startY);
    queue<Coord> q;
    q.push(start);
    visited[startX][startY] = 1;
    distances[startX][startY] = 0;

    // apply bfs to calculate distances
    while (!q.empty()) {
    	auto top = q.front();
    	q.pop();
        // if bridge is vertical, go up and down
    	if (bridges[top.x][top.y] == 'V') {
    		if (top.x + 1 <= n + 1 && visited[top.x + 1][top.y] == 0) {
    			distances[top.x + 1][top.y] = distances[top.x][top.y] + 1;
    			q.push(Coord(top.x + 1, top.y));
    			visited[top.x + 1][top.y] = 1;
    		}
    		if (top.x - 1 >= 0 && visited[top.x - 1][top.y] == 0) {
    			distances[top.x - 1][top.y] = distances[top.x][top.y] + 1;
    			q.push(Coord(top.x - 1, top.y));
    			visited[top.x - 1][top.y] = 1;
    		}
        // if bridge is horizontal, go left and right
    	} else if (bridges[top.x][top.y] == 'O') {
    		if (top.y + 1 <= m + 1 && visited[top.x][top.y + 1] == 0) {
    			distances[top.x][top.y + 1] = distances[top.x][top.y] + 1;
    			q.push(Coord(top.x, top.y + 1));
    			visited[top.x][top.y + 1] = 1;
    		}
    		if (top.y - 1 >= 0 && visited[top.x][top.y - 1] == 0) {
    			distances[top.x][top.y - 1] = distances[top.x][top.y] + 1;
    			q.push(Coord(top.x, top.y - 1));
    			visited[top.x][top.y - 1] = 1;
    		}
        // if bridge is double, go in all 4 directions
    	} else if (bridges[top.x][top.y] == 'D') {
    		if (top.x + 1 <= n + 1 && visited[top.x + 1][top.y] == 0) {
    			distances[top.x + 1][top.y] = distances[top.x][top.y] + 1;
    			q.push(Coord(top.x + 1, top.y));
    			visited[top.x + 1][top.y] = 1;
    		}
    		if (top.x - 1 >= 0 && visited[top.x - 1][top.y] == 0) {
    			distances[top.x - 1][top.y] = distances[top.x][top.y] + 1;
    			q.push(Coord(top.x - 1, top.y));
    			visited[top.x - 1][top.y] = 1;
    		}
    		if (top.y + 1 <= m + 1 && visited[top.x][top.y + 1] == 0) {
    			distances[top.x][top.y + 1] = distances[top.x][top.y] + 1;
    			q.push(Coord(top.x, top.y + 1));
    			visited[top.x][top.y + 1] = 1;
    		}
    		if (top.y - 1 >= 0 && visited[top.x][top.y - 1] == 0) {
    			distances[top.x][top.y - 1] = distances[top.x][top.y] + 1;
    			q.push(Coord(top.x, top.y - 1));
    			visited[top.x][top.y - 1] = 1;
    		}
    	}
    }

    // calculate the min dist on the margins
    int minDist = INT_MAX;
    for (int i = 0; i <= n + 1; i++) {
		minDist = min(distances[i][0], minDist);
		minDist = min(distances[i][m + 1], minDist);
    }

    for (int j = 0; j <= m + 1; j++) {
		minDist = min(distances[0][j], minDist);
		minDist = min(distances[n + 1][j], minDist);
    }

    ofstream fout("poduri.out");
    fout << minDist;
    fout.close();
    return 0;
}
