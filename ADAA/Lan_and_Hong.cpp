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

int pruned(int x, int y, int path, int graph[8][8]) {
  path = BFS(x, y, path, graph);
  if (isPathValid(path)) {
    return 1;
  } else {
    return 0;
  }
}

int BFS(int x, int y, int path, int graph[8][8]) {
  queue<node> q;
  q.push(node{x, y});
  while (!q.empty()) {
    node node = q.front();
    q.pop();
    for (int i = node.x - 1; i < n; i += 2) {
      if (graph[i][node.y] == 1) {
      }
    }
  }
  return path;
}

void DFS(int x, int y, int path, int graph[8][8]) {
  if (x == n - 1 && y == 0) {
    if (isPathValid(path)) {
      count = count + 1;
    }
    return;
  }
  for (int i = x - 1; i < n; i += 2) {
    if (graph[i][y] == 1) {
      int prune = pruned(x + i, y, path, graph);
      if (!prune) {
        graph[i][y] = 0;
        DFS(i, y, path + 1, graph);
      }
    }
  }
  for (int i = x - 1; i < n; i += 2) {
    if (graph[x][i] == 1) {
      int prune = pruned(x + i, y, path, graph);
      if (!prune) {
        graph[x][i] = 0;
        DFS(x, i, path + 1, graph);
      }
    }
  }
  return;
}

void construct() {
  iostream::sync_with_stdio(0);
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
  construct();
  return 0;
}