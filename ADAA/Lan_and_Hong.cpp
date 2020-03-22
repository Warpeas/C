#include <fstream>
#include <iostream>
#include <queue>

using namespace std;

/*
graph
|   int |   graph status    |
|   -1  |   unable to reach |
|   0   |   reached         |
|   1   |   reachable       |
*/
int n;
int count = 0; //    total route can do
int total;     //    total node sum should be reached
int validPath;
int graph[8][8];
int isPathValid(int);
int BFS(int, int, int, int graph[8][8]);
int prune(int, int, int, int graph[8][8]);
void construct();
struct node {
  int x;
  int y;
};

int isPathValid(int path) {
  if (path == total) {
    return 1;
  } else {
    return 0;
  }
}

int pruned(int x, int y, int path, int g[8][8]) {
  int graph[8][8];
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      graph[i][j] = g[i][j];
    }
  }
  graph[x][y] = 0;
  path = BFS(x, y, path, graph);
  if (isPathValid(path)) {
    return 0;
  } else {
    return 1;
  }
}

int BFS(int x, int y, int path, int graph[8][8]) {
  queue<node> q;
  q.push(node{x, y});
  int i;
  while (!q.empty()) {
    node nod = q.front();
    q.pop();
    i = nod.x - 1;
    if (i >= 0 && i < n && graph[i][nod.y] == 1) {
      q.push(node{i, nod.y});
      graph[i][nod.y] = 0;
      ++path;
    }
    i += 2;
    if (i >= 0 && i < n && graph[i][nod.y] == 1) {
      q.push(node{i, nod.y});
      graph[i][nod.y] = 0;
      ++path;
    }
    i = nod.y - 1;
    if (i >= 0 && i < n && graph[nod.x][i] == 1) {
      q.push(node{nod.x, i});
      graph[nod.x][i] = 0;
      ++path;
    }
    i += 2;
    if (i >= 0 && i < n && graph[nod.x][i] == 1) {
      q.push(node{nod.x, i});
      graph[nod.x][i] = 0;
      ++path;
    }
  }
  return path;
}

void DFS(int x, int y, int path, int g[8][8]) {
  if (x == n - 1 && y == 0) {
    if (isPathValid(path)) {
      ++count;
    }
    return;
  }
  int graph[8][8];
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      graph[i][j] = g[i][j];
    }
  }
  graph[x][y] = 0;
  int i;
  i = x - 1;
  if (i >= 0 && i < n && graph[i][y] == 1) {
    int prune = pruned(i, y, path + 1, graph);
    if (!prune) {
      DFS(i, y, path + 1, graph);
    }
  }
  i += 2;
  if (i >= 0 && i < n && graph[i][y] == 1) {
    int prune = pruned(i, y, path + 1, graph);
    if (!prune) {
      DFS(i, y, path + 1, graph);
    }
  }
  i = y - 1;
  if (i >= 0 && i < n && graph[x][i] == 1) {
    int prune = pruned(x, i, path + 1, graph);
    if (!prune) {
      DFS(x, i, path + 1, graph);
    }
  }
  i += 2;
  if (i >= 0 && i < n && graph[x][i] == 1) {
    int prune = pruned(x, i, path + 1, graph);
    if (!prune) {
      DFS(x, i, path + 1, graph);
    }
  }
  return;
}

void construct() {
  iostream::sync_with_stdio(0);
  ifstream fin;
  fin.open("input.txt");
  cin.rdbuf(fin.rdbuf());
  cin >> n;
  char c;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> c;
      if (c == 'x') {
        graph[i][j] = -1;
      } else {
        graph[i][j] = 1;
        ++total;
      }
    }
  }
}

int main() {
  iostream::sync_with_stdio(0);
  construct();
  //   for (int i = 0; i < n; i++) {
  //     for (int j = 0; j < n; j++) {
  //       cout << graph[i][j] << " ";
  //     }
  //     cout << "\n";
  //   }
  int prune = pruned(0, 0, 1, graph);
  if (!prune) {
    DFS(0, 0, 1, graph);
  }
  cout << count << "\n";
  return 0;
}