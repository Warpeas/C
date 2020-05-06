// A divide and conquer program in C++ to find the smallest distance from a
// given set of points.
#include <algorithm>
#include <iostream>
using namespace std;

struct point_1 {
  long long x;
};

struct point_2 {
  long long x, y;
};

struct point_3 {
  long long x, y, z;
};

template <class T> T closest_pair[2];

// Needed to sort array of points according to X coordinate
template <class T> bool compareX(const T &a, T &b) { return a.x < b.x; }
// Needed to sort array of points according to Y coordinate
template <class T> bool compareY(const T &a, const T &b) { return a.y < b.y; }
// Needed to sort array of points according to Z coordinate
template <class T> bool compareZ(const T &a, const T &b) { return a.z < b.z; }

// A utility function to find the distance between two points
long long dist(point_1 p1, point_1 p2) { return (p2.x - p1.x); }

long long dist(point_2 p1, point_2 p2) {
  return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
}

long long dist(point_3 p1, point_3 p2) {
  return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y) +
         (p1.z - p2.z) * (p1.z - p2.z);
}

// A Brute Force method to return the smallest distance between two points
// in P[] of size n
template <class T> long long bruteForce(T P[], int n) {
  long long min = LONG_LONG_MAX;
  for (int i = 0; i < n; ++i)
    for (int j = i + 1; j < n; ++j)
      if (dist(P[i], P[j]) < min)
        min = dist(P[i], P[j]);
  return min;
}

long long minDist(point_1 P[], int n) {
  long long min = LONG_LONG_MAX;
  for (int i = 0; i < n - 1; i++) {
    long long distance = dist(P[i], P[i + 1]);
    if (distance < min) {
      min = distance;
      closest_pair<point_1>[0] = P[i];
      closest_pair<point_1>[1] = P[i + 1];
    }
  }
  return min;
}

// A utility function to find a minimum of two long long values
// long long min(long long x, long long y) { return (x < y) ? x : y; }

// A utility function to find the distance between the closest points of
// strip of a given size. All points in strip[] are sorted according to
// y coordinate. They all have an upper bound on minimum distance as d.
// Note that this method seems to be a O(n^2) method, but it's a O(n)
// method as the inner loop runs at most 6 times
long long stripClosest(point_2 strip[], int size, long long d) {
  long long min = d; // Initialize the minimum distance as d

  // Pick all points one by one and try the next points till the difference
  // between y coordinates is smaller than d.
  // This is a proven fact that this loop runs at most 6 times
  for (int i = 0; i < size; ++i)
    for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j)
      if (dist(strip[i], strip[j]) < min)
        min = dist(strip[i], strip[j]);

  return min;
}

// A recursive function to find the smallest distance. The array Px contains
// all points sorted according to x coordinates and Py contains all points
// sorted according to y coordinates
long long closestUtil(point_2 Px[], point_2 Py[], int n) {
  // If there are 2 or 3 points, then use brute force
  if (n <= 3)
    return bruteForce(Px, n);

  // Find the middle point
  int mid = n / 2;
  point_2 midpoint = Px[mid];

  // Divide points in y sorted array around the vertical line.
  // Assumption: All x coordinates are distinct.
  point_2 Pyl[mid];     // y sorted points on left of vertical line
  point_2 Pyr[n - mid]; // y sorted points on right of vertical line
  int li = 0, ri = 0;   // indexes of left and right subarrays
  for (int i = 0; i < n; i++) {
    if (Py[i].x <= midpoint.x && li < mid)
      Pyl[li++] = Py[i];
    else
      Pyr[ri++] = Py[i];
  }

  // Consider the vertical line passing through the middle point
  // calculate the smallest distance dl on left of middle point and
  // dr on right side
  long long dl = closestUtil(Px, Pyl, mid);
  long long dr = closestUtil(Px + mid, Pyr, n - mid);

  // Find the smaller of two distances
  long long d = min(dl, dr);

  // Build an array strip[] that contains points close (closer than d)
  // to the line passing through the middle point
  point_2 strip[n];
  int j = 0;
  for (int i = 0; i < n; i++)
    if (abs(Py[i].x - midpoint.x) < d)
      strip[j] = Py[i], j++;

  // Find the closest points in strip. Return the minimum of d and closest
  // distance is strip[]
  return stripClosest(strip, j, d);
}

// The main function that finds the smallest distance
// This method mainly uses closestUtil()
long long closest(point_1 P[], int n) {
  sort(P, P + n, compareX<point_1>);
  return minDist(P, n);
}

long long closest(point_2 P[], int n) {
  point_2 Px[n];
  point_2 Py[n];
  for (int i = 0; i < n; i++) {
    Px[i] = P[i];
    Py[i] = P[i];
  }

  sort(Px, Px + n, compareX<point_2>);
  sort(Py, Py + n, compareY<point_2>);

  // Use recursive function closestUtil() to find the smallest distance
  return closestUtil(Px, Py, n);
}
// long long closest(point_3 P[], int n) {
//   point_3 Px[n];
//   point_3 Py[n];
//   point_3 Pz[n];
//   for (int i = 0; i < n; i++) {
//     Px[i] = P[i];
//     Py[i] = P[i];
//     Pz[i] = P[i];
//   }

//   sort(Px, Px + n, compareX);
//   sort(Py, Py + n, compareY);
//   sort(Pz, Pz + n, compareZ);

//   // Use recursive function closestUtil() to find the smallest distance
//   return closestUtil(Px, Py, n);
// }

// Driver program to test above functions
int main() {
  int n, d;
  cin >> n >> d;
  long long x, y, z;
  if (d == 1) {
    point_1 P[n];
    for (int i = 0; i < n; i++) {
      cin >> x;
      P[i] = point_1{x};
    }
    closest(P, n);
    // cout << closest(P, n) << endl;
    for (int i = 0; i < 2; i++) {
      cout << closest_pair<point_1>[i].x << endl;
    }
  } else if (d == 2) {
    point_2 P[n];
    for (int i = 0; i < n; i++) {
      cin >> x >> y;
      P[i] = point_2{x, y};
    }
    cout << closest(P, n) << endl;
  } else {
    point_3 P[n];
    for (int i = 0; i < n; i++) {
      cin >> x >> y >> z;
      P[i] = point_3{x, y, z};
    }
    // closest(P, n);
  }

  return 0;
}
