#include <algorithm>
#include <map>
#include <set>
#include <iostream>

using namespace std;

static int n, m;

class path {
  int x1, y1;
  int x2, y2;
  int w;

public:
  path(int x1, int y1, int x2, int y2, int w) {
    this->x1 = x1;
    this->y1 = y1;
    this->x2 = x2;
    this->y2 = y2;
    this->w = w;
  }

  int compareTo(path o) { return this->w - o.w; }
};

static int **s;
static map<int, set<int>> setMap = new map<int, set<int>()>;
static path[] paths;

static long addToSet() {
  long result = 0;
  int s1, s2, cnt = 1;
  int x1, x2, y1, y2;
  for (int i = 0; i < paths.length && setMap.entrySet().size() != n * m; i++) {
    x1 = paths[i].x1;
    y1 = paths[i].y1;
    x2 = paths[i].x2;
    y2 = paths[i].y2;
    s1 = s[x1][y1];
    s2 = s[x2][y2];
    if (s1 != s2 && s1 != 0 && s2 != 0) {
      result += paths[i].w;
      if (s1 > s2) {
        for (int p : setMap.get(s1)) {
          s[p / m][p % m] = s2;
        }
        setMap.get(s2).addAll(setMap.get(s1));
        setMap.remove(s1);
      } else {
        for (int p : setMap.get(s2)) {
          s[p / m][p % m] = s1;
        }
        setMap.get(s1).addAll(setMap.get(s2));
        setMap.remove(s2);
      }
    } else if (s1 == 0 && s2 != 0) {
      result += paths[i].w;
      setMap.get(s2).add(x1 * m + y1);
      s[x1][y1] = s2;
    } else if (s1 != 0 && s2 == 0) {
      result += paths[i].w;
      setMap.get(s1).add(x2 * m + y2);
      s[x2][y2] = s1;
    } else if (s1 == 0) {
      result += paths[i].w;
      setMap.put(cnt, new HashSet<>());
      setMap.get(cnt).add(x1 * m + y1);
      setMap.get(cnt).add(x2 * m + y2);
      s[x1][y1] = cnt;
      s[x2][y2] = cnt++;
    }
  }
  return result;
}

public
static void main(String[] args) {
  long start = System.currentTimeMillis();
  n = in.nextInt();
  m = in.nextInt();
  s = new int[n][m];
  int w, cnt = 0;
  int[][] weight = new int[2][m];
  paths = new path[n * (m - 1) + m * (n - 1)];
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      w = in.nextInt();
      weight[i % 2][j] = w;
      if (i > 0) {
        w = weight[(i - 1) % 2][j] ^ weight[i % 2][j];
        paths[cnt++] = new path(i - 1, j, i, j, w);
      }
      if (j > 0) {
        w = weight[i % 2][j - 1] ^ weight[i % 2][j];
        paths[cnt++] = new path(i, j - 1, i, j, w);
      }
    }
  }
  Arrays.sort(paths);
  out.println(addToSet());
  long end = System.currentTimeMillis();
  out.println(end - start);
  out.close();
}