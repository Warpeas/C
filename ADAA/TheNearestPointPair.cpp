// A divide and conquer program in C++ to find the smallest distance from a
// given set of points.

#include <algorithm>
#include <float.h>
#include <iostream>
#include <math.h>
#include <stdlib.h>
using namespace std;

struct point_1 {
  double x;
};

struct point_2 {
  double x, y;
};

struct point_3 {
  double x, y, z;
};


// Needed to sort array of points according to X coordinate
template <class T> bool compareX(const T &a, T &b) { return (a.x - b.x); }
// Needed to sort array of points according to Y coordinate
template <class T> bool compareY(const T &a, const T &b) { return (a.y - b.y); }
// Needed to sort array of points according to Z coordinate
template <class T> bool compareZ(const T &a, const T &b) { return (a.z - b.z); }

// A utility function to find the distance between two points
double dist(point_1 p1, point_1 p2) { return (p1.x - p2.x); }

double dist(point_2 p1, point_2 p2) {
  return sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
}

// A Brute Force method to return the smallest distance between two points
// in P[] of size n
double bruteForce(point_1 P[], int n) {
  double min = DBL_MAX;
  for (int i = 0; i < n; ++i)
    for (int j = i + 1; j < n; ++j)
      if (dist(P[i], P[j]) < min)
        min = dist(P[i], P[j]);
  return min;
}

double bruteForce(point_2 P[], int n) {
  double min = DBL_MAX;
  for (int i = 0; i < n; ++i)
    for (int j = i + 1; j < n; ++j)
      if (dist(P[i], P[j]) < min)
        min = dist(P[i], P[j]);
  return min;
}

// A utility function to find a minimum of two double values
double min(double x, double y) { return (x < y) ? x : y; }

// A utility function to find the distance between the closest points of
// strip of a given size. All points in strip[] are sorted according to
// y coordinate. They all have an upper bound on minimum distance as d.
// Note that this method seems to be a O(n^2) method, but it's a O(n)
// method as the inner loop runs at most 6 times
double stripClosest(point_2 strip[], int size, double d) {
  double min = d; // Initialize the minimum distance as d

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
double closestUtil(point_2 Px[], point_2 Py[], int n) {
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
  double dl = closestUtil(Px, Pyl, mid);
  double dr = closestUtil(Px + mid, Pyr, n - mid);

  // Find the smaller of two distances
  double d = min(dl, dr);

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
// double closest(point_1 P[], int n) {
//   point_1 Px[n];
//   for (int i = 0; i < n; i++) {
//     Px[i] = P[i];
//   }

//   sort(Px, Px + n, compareX);

//   // Use recursive function closestUtil() to find the smallest distance
//   return closestUtil(Px, n);
// }
double closest(point_2 P[], int n) {
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
// double closest(point_3 P[], int n) {
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
  double x, y, z;
  if (d == 1) {
    point_1 P[n];
    for (int i = 0; i < n; i++) {
      cin >> x;
      P[i] = point_1{x};
    }
    // closest(P, n);

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
