#include <iostream>

class Number {
public:
  Number();
  Number(int num);
  Number operator++(int);
  Number operator++();
  Number operator--(int);
  Number operator--();
  friend std::ostream &operator<<(std::ostream &os, const Number &other);

private:
  int num;
};