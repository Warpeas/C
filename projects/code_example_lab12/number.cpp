#include "Number.h"
#include <iostream>

Number Number::operator++(int) {
  int a = num++;
  return a;
}

Number Number::operator++() { return ++num; }

Number::Number(int num) : num(num){};

Number Number::operator--(int) {
  int a = num--;
  return a;
}

Number Number::operator--() { return --num; }

std::ostream &operator<<(std::ostream &os, const Number &other) {
  os << "x = " << other.num;
  return os;
}