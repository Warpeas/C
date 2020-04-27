#include <climits>
#include <cmath>
#include <iostream>

using namespace std;

int n;
int graph[10000][10000];
int path[1000000];
double dist[10000];
bool sptSet[10000];

int minDistance() {
  // Initialize min value
  int min = INT_MAX, min_index;
  for (int v = 0; v < n; v++)
    if (sptSet[v] == false && dist[v] <= min) {
      min = dist[v];
      min_index = v;
    }
  return min_index;
}

// A utility function to print the constructed distance array
int printSolution() {
  int last = path[0];
  int result = 1;
  for (int i = 1; i < n; i++) {
    result *= graph[last][path[i]] % 19260817;
    result %= 19260817;
    last = path[i];
  }
  cout << result;
  return 0;
}

void dijkstra(int src) {
  path[n - 1] = n - 1;
  for (int i = 0; i < n; i++)
    dist[i] = INT_MAX, sptSet[i] = false;
  dist[src] = 0;

  for (int count = 0; count < n - 1; count++) {
    int u = minDistance();
    path[count] = u;
    sptSet[u] = true;
    for (int v = 0; v < n; v++)
      if (!sptSet[v] && graph[u][v] && dist[u] != INT_MAX &&
          dist[u] + log(graph[u][v]) < dist[v])
        dist[v] = dist[u] + log(graph[u][v]);
  }

  printSolution();
}

int main() {
  iostream::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);
  int m;
  cin >> n >> m;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      graph[i][j] = 0;
    }
  }
  int u, v, w;
  for (int i = 0; i < m; i++) {
    cin >> u >> v >> w;
    graph[u - 1][v - 1] = w;
  }
  dijkstra(0);
  return 0;
}
