#include <fstream>
#include <iostream>
#include <queue>

using namespace std;

/*
graph
|   int |   graph status    |
|   -2  |   reached by bfs  |
|   -1  |   unable to reach |
|   0   |   reachable       |
|   1   |   first           |
*/
int n;
int count = 0; //    total route can do
int total;     //    total node sum should be reached
int validPath;
int global_graph[8][8];
int tg[8][8];
void construct();
struct node {
  int x;
  int y;
};

void array_copy(int sg[8][8], int rg[8][8]) {
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      rg[i][j] = sg[i][j];
    }
  }
}

int out(node nod, int graph[8][8]) {
  int flag = 0;
  int i;
  i = nod.x - 1;
  if (i >= 0 && i < n && graph[i][nod.y] == 0) {
    ++flag;
  }
  i += 2;
  if (i >= 0 && i < n && graph[i][nod.y] == 0) {
    ++flag;
  }
  i = nod.y - 1;
  if (i >= 0 && i < n && graph[nod.x][i] == 0) {
    ++flag;
  }
  i += 2;
  if (i >= 0 && i < n && graph[nod.x][i] == 0) {
    ++flag;
  }
  return flag;
}

int BFS(node start, node end, int graph[8][8]) {
  queue<node> q;
  q.push(node{start.x, start.y});
  int i;
  while (!q.empty()) {
    node nod = q.front();
    q.pop();
    i = nod.x - 1;
    if (i >= 0 && i < n && graph[i][nod.y] == 0) {
      if (i == end.x && nod.y == end.y) {
        return 1;
      }
      q.push(node{i, nod.y});
      graph[i][nod.y] = -2;
    }
    i += 2;
    if (i >= 0 && i < n && graph[i][nod.y] == 0) {
      if (i == end.x && nod.y == end.y) {
        return 1;
      }
      q.push(node{i, nod.y});
      graph[i][nod.y] = -2;
    }
    i = nod.y - 1;
    if (i >= 0 && i < n && graph[nod.x][i] == 0) {
      if (nod.x == end.x && i == end.y) {
        return 1;
      }
      q.push(node{nod.x, i});
      graph[nod.x][i] = -2;
    }
    i += 2;
    if (i >= 0 && i < n && graph[nod.x][i] == 0) {
      if (nod.x == end.x && i == end.y) {
        return 1;
      }
      q.push(node{nod.x, i});
      graph[nod.x][i] = -2;
    }
  }
  return 0;
}

void DFS(node nod, int path, int g[8][8]) {
  if (nod.x == n - 1 && nod.y == 0) {
    if (path == total) {
      ++count;
      // cout << "Yes" << endl;
      // for (int i = 0; i < n; i++) {
      //   for (int j = 0; j < n; j++) {
      //     cout << g[i][j] << "\t";
      //   }
      //   cout << "\n";
      // }
      // cout << endl;
    }
    return;
  }
  int graph[8][8];
  array_copy(g, graph);
  graph[nod.x][nod.y] = path;
  vector<node> nodes;
  vector<node> one_out;
  int i;
  i = nod.x - 1;
  if (i >= 0 && i < n && graph[i][nod.y] == 0) {
    if (!(i == n - 1 && nod.y == 0) && out(node{i, nod.y}, graph) == 1) {
      one_out.push_back(node{i, nod.y});
    } else {
      nodes.push_back(node{i, nod.y});
    }
  }
  i += 2;
  if (i >= 0 && i < n && graph[i][nod.y] == 0) {
    if (!(i == n - 1 && nod.y == 0) && out(node{i, nod.y}, graph) == 1) {
      one_out.push_back(node{i, nod.y});
    } else {
      nodes.push_back(node{i, nod.y});
    }
  }
  i = nod.y - 1;
  if (i >= 0 && i < n && graph[nod.x][i] == 0) {
    if (!(nod.x == n - 1 && i == 0) && out(node{nod.x, i}, graph) == 1) {
      one_out.push_back(node{nod.x, i});
    } else {
      nodes.push_back(node{nod.x, i});
    }
  }
  i += 2;
  if (i >= 0 && i < n && graph[nod.x][i] == 0) {
    if (!(nod.x == n - 1 && i == 0) && out(node{nod.x, i}, graph) == 1) {
      one_out.push_back(node{nod.x, i});
    } else {
      nodes.push_back(node{nod.x, i});
    }
  }

  // judge
  if (one_out.size() > 0) {
    if (one_out.size() > 1) {
      return;
    } else {
      array_copy(graph, tg);
      tg[one_out[0].x][one_out[0].y] = path;
      if (BFS(one_out[0], node{n - 1, 0}, tg)) {
        DFS(one_out[0], path + 1, graph);
      }
    }
  } else {
    node current, start, end;
    if (nodes.size() == 3) {
      for (int i = 0; i < 3; i++) {
        array_copy(graph, tg);
        current = nodes[i];
        start = nodes[(i + 1) % 3];
        end = nodes[(i + 2) % 3];
        tg[current.x][current.y] = path;
        if (BFS(start, end, tg)) {
          DFS(current, path + 1, graph);
        }
      }
    } else if (nodes.size() == 2) {

      for (int i = 0; i < 2; i++) {
        array_copy(graph, tg);
        current = nodes[i];
        start = nodes[(i + 1) % 2];
        end = node{n - 1, 0};
        tg[current.x][current.y] = path;
        if (BFS(start, end, tg)) {
          DFS(current, path + 1, graph);
        }
      }
    } else if (nodes.size() == 1) {
      current = nodes[0];
      DFS(current, path + 1, graph);
    } else {
      return;
    }
  }
}

void construct() {
  iostream::sync_with_stdio(0);
  cin.tie(0);
  ifstream fin;
  fin.open("input.txt");
  cin.rdbuf(fin.rdbuf());
  cin >> n;
  char c;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> c;
      if (c == 'x') {
        global_graph[i][j] = -1;
      } else {
        global_graph[i][j] = 0;
        ++total;
      }
    }
  }
}

int main() {
  iostream::sync_with_stdio(0);
  cout.tie(0);
  construct();
  ofstream fo;
  fo.open("output.txt");
  cout.rdbuf(fo.rdbuf());
  DFS(node{0, 0}, 1, global_graph);
  cout << count << "\n";
  return 0;
}