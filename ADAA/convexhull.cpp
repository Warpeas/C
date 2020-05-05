//凸包-Graham扫描
#include <algorithm>
#include <cstdio>
#include <vector>

using namespace std;

struct vertex {
  long x;
  long y;
} vertexes[500000];

//  Cross product of v0v1 and v0v2, if > 0, the angle of v0v2 is larger than
//  v0v1, v1 is on the right of v2, vice versa
long long cross_product(vertex v0, vertex v1, vertex v2) {
  return (v1.x - v0.x) * (v2.y - v0.y) - (v2.x - v0.x) * (v1.y - v0.y);
}

//  distance between v1 and v2
long long dis(vertex v1, vertex v2) {
  return (v1.x - v2.x) * (v1.x - v2.x) + (v1.y - v2.y) * (v1.y - v2.y);
}

bool com(const vertex &v1, const vertex &v2) {
  long long tmp = cross_product(vertexes[0], v1, v2);
  //  if angle the same, then larger dist get first
  // if (fabs(tmp) < 1e-6) {
  //   return dis(vertexes[0], v1) < dis(vertexes[0], v2);
  // } else {
  //   return tmp > 0;
  // }
  if (tmp == 0) {
    return dis(vertexes[0], v1) > dis(vertexes[0], v2);
  } else {
    return tmp > 0;
  }
}

vector<vertex> graham_scan(int n) {
  vector<vertex> v;
  int top = 2;
  int index = 0;
  int insides = 0;
  // find the least y, if equal than least x, and other 3 point
  for (int i = 1; i < n; ++i) {
    if (vertexes[i].y < vertexes[index].y ||
        (vertexes[i].y == vertexes[index].y &&
         vertexes[i].x < vertexes[index].x)) {
      index = i;
    }
  }
  swap(vertexes[0], vertexes[index]);
  v.push_back(vertexes[0]);
  // sort by angle
  sort(vertexes + 1, vertexes + n, com);
  vertex lam = vertexes[1];
  v.push_back(vertexes[1]);
  // int i = 2;
  // while (cross_product(vertexes[0], v.back(), vertexes[i]) == 0) {
  //   ++insides;
  //   ++i;
  // }
  v.push_back(vertexes[2]);
  for (int i = 3; i < n; ++i) {
    while (top > 0 &&
           cross_product(lam, vertexes[i], v[top]) >
               0 && //  may inside the new triangle, left of v right_most to
                    //  vertexes[i]
           //        cross_product(vertexes[0], v[top], vertexes[i]) > 0 &&
           //        cross_product(vertexes[0], v[top], v[top - 1]) != 0 &&
           cross_product(vertexes[0], vertexes[1],
                         vertexes[top]) //  not on the bottom line
    ) {
      --top;
      v.pop_back();
    }
    if (cross_product(vertexes[0], lam, vertexes[i]) < 0) {
      lam = vertexes[i];
    }
    v.push_back(vertexes[i]);
    ++top;
  }
  printf("%d", v.size() + insides);
  return v;
}

int main() {
  int n;
  scanf("%d", &n);
  long x, y;
  int i = 0;
  if (n > 8) {
    scanf("%d %d", &x, &y);
    vertex leftUp = vertex{x, y}, leftDown = vertex{x, y},
           rightUp = vertex{x, y}, rightDown = vertex{x, y};
    long long leftUpVal = leftUp.x - leftUp.y;
    long long leftDownVal = leftDown.x + leftDown.y;
    long long rightUpVal = rightUp.x + rightUp.y;
    long long rightDownVal = rightDown.x - rightDown.y;
    long long minX = x, minY = y, maxX = x, maxY = y;
    vector<vertex> vertexList;
    vertexList.push_back(leftDown);
    for (int j = 1; j < n; ++j) {
      scanf("%d %d", &x, &y);
      vertex v = vertex{x, y};
      int x = v.x;
      int y = v.y;
      if (!(x > minX && x < maxX && y > minY && y < maxY)) {
        vertexList.push_back(v);
        //  sum to the right up
        int sum = x + y;
        //  diff to the right down
        int diff = x - y;
        //  the least diff, left up
        if (diff < leftUpVal)
          leftUp = v;
        //  the largest diff, right down
        if (diff > rightDownVal)
          rightDown = v;
        // the least sum, left down
        if (sum < leftDownVal)
          leftDown = v;
        //  the largest sum, right up
        if (sum > rightUpVal)
          rightUp = v;
        leftUpVal = leftUp.x - leftUp.y;
        rightDownVal = rightDown.x - rightDown.y;
        leftDownVal = leftDown.x + leftDown.y;
        rightUpVal = rightUp.x + rightUp.y;
        minX = max(leftUp.x, leftDown.x);
        minY = max(leftDown.y, rightDown.y);
        maxX = min(rightUp.x, rightDown.x);
        maxY = min(leftUp.y, rightUp.y);
      }
    }

    // reset the vert's array, and do one more filtering pass
    // on vertexList
    // for (int i = 0; i < vertexList.size(); ++i) {
    //   vertex v = vertexList[i];
    //   int x = v.x;
    //   int y = v.y;
    //   if (!(x > minX && x < maxX && y > minY && y < maxY))
    //     verts.push(v);
    // }
    for (vector<vertex>::iterator v = vertexList.begin(); v != vertexList.end();
         v++) {
      long long x = v->x;
      long long y = v->y;
      if (!(x > minX && x < maxX && y > minY && y < maxY))
        vertexes[i++] = *v;
    }
    // verts now only contains a subset of vertices.
  } else {
    for (i = 0; i < n; i++) {
      scanf("%d %d", &x, &y);
      vertexes[i] = vertex{x, y};
    }
  }
  if (n < 4) {
    printf("%d", n);
  } else {
    graham_scan(i);
  }
  return 0;
}
