#include "box.hpp"

using namespace std;

Box::Box() {
  l = 0;
  b = 0;
  h = 0;
}

Box::Box(int length, int breadth, int height) {

  l = length;

  if (breadth > b_max) {
    b = b_max;
  } else if (breadth < b_min) {
    b = b_min;
  } else {
    b = breadth;
  }

  if (height > h_max) {
    h = h_max;
  } else if (breadth < h_min) {
    h = h_min;
  } else {
    h = height;
  }
}
Box::Box(const Box &other) {
  l = other.l;
  b = other.b;
  h = other.h;
}
int Box::getLength() { return l; }
int Box::getBreadth() { return b; }
int Box::getHeight() { return h; }
long long Box::CalculateVolume() { return (long long)l * b * h; }
bool operator<(Box &A, Box &B) {
  return A.l < B.l ||
         ((A.l == B.l && (A.b < B.b || (A.b == B.b && A.h < B.h))));
}
std::ostream &operator<<(std::ostream &os, const Box &box) {
  os << "length: " << box.l << endl
     << "breadth: " << box.b << endl
     << "height: " << box.h << endl;
  return os;
}