#ifndef BOX_HPP
#define BOX_HPP

#include <iostream>

using namespace std;

class Box {
private:
  int l;
  int b;
  int h;

protected:
  int b_max = 100000;
  int b_min = 0;
  int h_max = 100000;
  int h_min = 0;

public:
  Box() {
    l = 0;
    b = 0;
    h = 0;
  };
  Box(int length, int breadth, int height) {

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
  };
  Box(const Box &other) {
    l = other.l;
    b = other.b;
    h = other.h;
  };
  ~Box(){  };
  int getLength() { return l; };
  int getBreadth() { return b; };
  int getHeight() { return h; };
  long long CalculateVolume() { return (long long)l * b * h; };
  friend bool operator<(Box &A, Box &B) {
    return A.l < B.l ||
           ((A.l == B.l && (A.b < B.b || (A.b == B.b && A.h < B.h))));
  };
  friend std::ostream &operator<<(std::ostream &os, const Box &box) {
    os << box.l << " " << box.b << " " << box.h;
    return os;
  };
};

#endif