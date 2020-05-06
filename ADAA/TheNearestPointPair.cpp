// A divide and conquer program in C++ to find the smallest distance from a
// given set of points.
#include <algorithm>
#include <cmath>
#include <iostream>
#define LONG_LONG_MAX 9223372036854775807ll

using namespace std;

struct point_1 {
  long long x;
};

struct point_2 {
  long long x, y;
};

struct point_3 {
  long long x, y, z;
  int c[3], i[3];
};
bool lexicographical(const point_2 &a, const point_2 &b, const point_2 &c,
                     const point_2 &d) {
  if (a.x < c.x || (a.x == c.x && a.y < c.y) ||
      (a.x == c.x && a.y == c.y && b.x < d.x) ||
      (a.x == c.x && a.y == c.y && b.x == d.x && b.y < d.y)) {
    return true;
  } else {
    return false;
  }
}
template <class T> T closest_pair[2];
// Needed to sort array of points according to Z coordinate
template <class T> bool compareZ(const T &a, const T &b) { return a.z < b.z; }
// Needed to sort array of points according to Y coordinate
template <class T> bool compareY(const T &a, const T &b) { return a.y < b.y; }
bool compareY3(const point_3 &a, const point_3 &b) {
  if (a.y < b.y) {
    return true;
  } else if (a.y == b.y) {
    return compareZ(a, b);
  } else {
    return false;
  }
}
// Needed to sort array of points according to X coordinate
bool compareX1(const point_1 &a, const point_1 &b) { return a.x < b.x; }
bool compareX2(const point_2 &a, const point_2 &b) {
  if (a.x < b.x) {
    return true;
  } else if (a.x == b.x) {
    return compareY(a, b);
  } else {
    return false;
  }
}
bool compareX3(const point_3 &a, const point_3 &b) {
  if (a.x < b.x) {
    return true;
  } else if (a.x == b.x) {
    return compareY(a, b);
  } else if (a.x == b.x && a.y == b.y) {
    return compareZ(a, b);
  } else {
    return false;
  }
  // return a.x < b.x;
}

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
template <class T> void bruteForce(T P[], int n, long long *d) {
  for (int i = 0; i < n; ++i)
    for (int j = i + 1; j < n; ++j) {
      long long distance = dist(P[i], P[j]);
      if (distance < *d) {
        *d = distance;
        closest_pair<T>[0] = P[i];
        closest_pair<T>[1] = P[j];
      }
    }
}

void bruteForce2(point_2 P[], int n, long long *d) {
  for (int i = 0; i < n; ++i)
    for (int j = i + 1; j < n; ++j) {
      long long distance = dist(P[i], P[j]);
      if (distance < *d) {
        *d = distance;
        closest_pair<point_2>[0] = P[i];
        closest_pair<point_2>[1] = P[j];
      } else if (distance == *d &&
                 !lexicographical(closest_pair<point_2>[0],
                                  closest_pair<point_2>[1], P[i], P[j])) {
        closest_pair<point_2>[0] = P[i];
        closest_pair<point_2>[1] = P[j];
      }
    }
}

// A utility function to find a *dimum of two long long values
// long long *d(long long x, long long y) { return (x < y) ? x : y; }

// A utility function to find the distance between the closest points of
// strip of a given size. All points in strip[] are sorted according to
// y coordinate. They all have an upper bound on *dimum distance as d.
// Note that this method seems to be a O(n^2) method, but it's a O(n)
// method as the inner loop runs at most 6 times
void stripClosest(point_2 strip[], int size, long long *d) {
  // Pick all points one by one and try the next points till the difference
  // between y coordinates is smaller than d.
  // This is a proven fact that this loop runs at most 6 times
  for (int i = 0; i < size; ++i)
    for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < *d; ++j) {
      long long distance = dist(strip[i], strip[j]);
      if (distance < *d) {
        *d = distance;
        closest_pair<point_2>[0] = strip[i];
        closest_pair<point_2>[1] = strip[j];
      } else if (distance == *d && !lexicographical(closest_pair<point_2>[0],
                                                    closest_pair<point_2>[1],
                                                    strip[i], strip[j])) {
        closest_pair<point_2>[0] = strip[i];
        closest_pair<point_2>[1] = strip[j];
      }
    }
}
// Find the distance between the closest points of strip of given size
void stripClosest(point_3 strip[], int size, long long *d) {
  // If point_3 count in strip is 2 or 3, find distance with naive method
  if (size <= 3) {
    return bruteForce(strip, size, d);
  }

  // If point_3 count is more than 3, start processing of creating columns
  // Find the smallest z value among point_3s
  long long minZ = strip[0].z;
  long long maxZ = strip[0].z;
  for (int i = 1; i < size; i++) {
    if (strip[i].z < minZ) {
      minZ = strip[i].z;
    }
    if (strip[i].z > maxZ) {
      maxZ = strip[i].z;
    }
  }

  // Find range between z values and column count
  long long columnNumber = ((maxZ - minZ) / (long long)sqrt(*d)) + 1;

  // Create an array of point_3 pointer vectors to put pointers of point_3s into
  // proper columns
  point_3 columns[3][columnNumber];
  int index[3];
  for (int i = 0; i < 3; i++) {
    index[i] = 0;
  }
  // Put all point_3s into proper columns (Phase 1)
  // Start picking point_3s one by one with the smallest Y first
  for (int i = 0; i < size; i++) {
    // Find middle(main) column of point_3, put point_3 into it, save index
    strip[i].c[1] = (int)((long long)(strip[i].z - minZ) / *d);
    columns[strip[i].c[1]][index[strip[i].c[1]]] = strip[i];
    strip[i].i[1] = index[strip[i].c[1]]++;

    // Find and save left and right columns of point_3
    strip[i].c[0] = strip[i].c[1] - 1;
    strip[i].c[2] = strip[i].c[1] + 1;

    // There is no column at left of leftmost column, and right of rightmost
    // column Save indices in left and right columns
    if (strip[i].c[1] != 0) // If not leftmost column
      strip[i].i[0] = index[strip[i].c[0]];

    if (strip[i].c[1] != columnNumber - 1) // If not rightmost column
      strip[i].i[2] = index[strip[i].c[2]];
  }

  // Find if such lesser distance exists between any pair of point_3s inside
  // strip (Phase 2) Start picking point_3s one by one with the smallest Y first
  for (int i = 0; i < size; i++) {
    // For left, middle and right columns of the selected point_3
    for (int j = 0; j < 3; j++) {
      // Skip invalid out of bounds columns
      if ((strip[i].c[j] == -1) || (strip[i].c[j] == columnNumber))
        continue;
      // Look through increasing Y value = O(1)
      for (int k = strip[i].i[j] + 1; k < index[strip[i].c[j]]; k++) {
        // If Y distance is already more than strip distance, no need to check
        // remaning point_3s in column
        if (columns[strip[i].c[j]][k].y > strip[i].y + *d)
          break;

        // Check distance and if it is smaller than current *dimum distance,
        // save it as new *dimum distance
        long long distance = dist(strip[i], columns[strip[i].c[j]][k]);
        if (distance < *d) {
          *d = distance;
        }
      }
    }
  }
}
// A recursive function to find the smallest distance. The array Px contains
// all points sorted according to x coordinates and Py contains all points
// sorted according to y coordinates
void closestUtil(point_1 P[], int n) {
  long long d = LONG_LONG_MAX;
  for (int i = 0; i < n - 1; ++i) {
    long long distance = dist(P[i], P[i + 1]);
    if (distance < d) {
      d = distance;
      closest_pair<point_1>[0] = P[i];
      closest_pair<point_1>[1] = P[i + 1];
    }
  }
}
void closestUtil(point_2 Px[], point_2 Py[], int n, long long *d) {
  // If there are 2 or 3 points, then use brute force
  if (n <= 3)
    return bruteForce2(Px, n, d);

  // Find the middle point
  int mid = n / 2;
  point_2 midpoint = Px[mid];

  // Divide points in y sorted array around the vertical line.
  // Assumption: All x coordinates are distinct.
  point_2 Pyl[mid];     // y sorted points on left of vertical line
  point_2 Pyr[n - mid]; // y sorted points on right of vertical line
  int li = 0, ri = 0;   // indexes of left and right subarrays
  for (int i = 0; i < n; ++i) {
    if (Py[i].x <= midpoint.x && li < mid)
      Pyl[li++] = Py[i];
    else
      Pyr[ri++] = Py[i];
  }

  // Consider the vertical line passing through the middle point
  // calculate the smallest distance dl on left of middle point and
  // dr on right side
  closestUtil(Px, Pyl, mid, d);
  closestUtil(Px + mid, Pyr, n - mid, d);

  // Build an array strip[] that contains points close (closer than d)
  // to the line passing through the middle point
  point_2 strip[n];
  int j = 0;
  for (int i = 0; i < n; ++i)
    if (abs(Py[i].x - midpoint.x) < *d) {
      strip[j++] = Py[i];
    }

  // Find the closest points in strip. Return the *dimum of d and closest
  // distance is strip[]
  stripClosest(strip, j, d);
}
// Divide array and conquer value recursively
void closestUtil(point_3 Px[], point_3 Py[], int n, long long *d) {
  // If size is small enough, find distance with naive method
  if (n <= 3)
    return bruteForce(Py, n, d);

  // Get middle point_3 for X axis
  int mid = n / 2;
  point_3 midpoint = Px[mid];
  // Initialize left and right subarrays
  point_3 Pyl[mid];
  point_3 Pyr[n - mid];

  // Distribute point_3s into left and right vectors
  int li = 0, ri = 0;
  for (int i = 0; i < n; i++) {
    if (Py[i].x < midpoint.x && li < mid)
      Pyl[li++] = Py[i];
    else
      Pyr[ri++] = Py[i];
  }

  // Pass subarrays to same function recursively
  closestUtil(Px, Pyl, mid, d);
  closestUtil(Px + mid, Pyr, n - mid, d);

  // Initialize strip vector
  point_3 strip[n];

  // Put point_3s which are close enough to middle into strip
  int j = 0;
  for (int i = 0; i < n; ++i)
    if (abs(Py[i].x - midpoint.x) < *d) {
      strip[j++] = Py[i];
    }

  // Search in strip and return the *dimum distance
  return stripClosest(strip, j, d);
}

// The main function that finds the smallest distance
// This method mainly uses closestUtil()
void closest(point_1 P[], int n) {
  sort(P, P + n, compareX1);
  closestUtil(P, n);
}

void closest(point_2 P[], int n) {
  point_2 Px[n];
  point_2 Py[n];
  for (int i = 0; i < n; ++i) {
    Px[i] = P[i];
    Py[i] = P[i];
  }

  sort(Px, Px + n, compareX2);
  sort(Py, Py + n, compareY<point_2>);

  long long *d = new long long();
  *d = LONG_LONG_MAX;
  // Use recursive function closestUtil() to find the smallest distance
  closestUtil(Px, Py, n, d);
}
void closest(point_3 P[], int n) {
  point_3 Px[n];
  point_3 Py[n];
  point_3 Pz[n];
  for (int i = 0; i < n; i++) {
    Px[i] = P[i];
    Py[i] = P[i];
    Pz[i] = P[i];
  }

  sort(Px, Px + n, compareX3);
  sort(Py, Py + n, compareY3);
  sort(Pz, Pz + n, compareZ<point_3>);

  long long *d = new long long();
  *d = LONG_LONG_MAX;
  // Use recursive function closestUtil() to find the smallest distance
  closestUtil(Px, Py, n, d);
}

// Driver program to test above functions
int main() {
  int n, d;
  cin >> n >> d;
  long long x, y, z;
  if (d == 1) {
    point_1 P[n];
    for (int i = 0; i < n; ++i) {
      cin >> x;
      P[i] = point_1{x};
    }
    closest(P, n);
    for (int i = 0; i < 2; ++i) {
      cout << closest_pair<point_1>[i].x << endl;
    }
  } else if (d == 2) {
    point_2 P[n];
    for (int i = 0; i < n; i++) {
      cin >> x >> y;
      P[i] = point_2{x, y};
    }
    closest(P, n);
    for (int i = 0; i < 2; ++i) {
      cout << closest_pair<point_2>[i].x << " " << closest_pair<point_2>[i].y
           << endl;
    }
  } else {
    point_3 P[n];
    for (int i = 0; i < n; ++i) {
      cin >> x >> y >> z;
      P[i] = point_3{x, y, z};
    }
    closest(P, n);
    for (int i = 0; i < 2; ++i) {
      cout << closest_pair<point_3>[i].x << " " << closest_pair<point_3>[i].y
           << " " << closest_pair<point_3>[i].z << endl;
    }
  }
  return 0;
}
