#include "fun.h"
#include "iostream"

int main() {
  double source[] = {1.100, 2.200, 3.300, 4.400, 5.500};
  double target1[5];
  double target2[5];
  double target3[5];
  CopyArray(target1, source);
  CopyArray(target2, source, 5);
  CopyArray(target3, &source[0], &source[4]);
  PrintArray(target1, target2, target3, 5);
}