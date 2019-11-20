#include "fun.h"
#include "cstdio"
#include "iostream"

using namespace std;

// copy with reference notation
void CopyArray(double (&target)[5], double (&source)[5]) {
  for (int i = 0; i < 5; i++) {
    target[i] = source[i];
  }
};
// copy with pointer notation
void CopyArray(double *target, double *source, int len) {
  for (int i = 0; i < len; i++) {
    *target = *source;
    target++;
    source++;
  }
};
// copy with two pointers
void CopyArray(double *target, double *source_start, double *source_end) {
  for (; source_start != source_end; source_start++) {
    *target = *source_start;
    target++;
  }
  *target = *source_end;
};
// Print the values of three arrays in format
void PrintArray(double *target1, double *target2, double *target3, int len) {
    printf("%s\t%s\t%s\n","target1|","target2|","target3|");
    for(int i = 0;i < 5;i++){
    printf("%7.4f|\t%7.4f|\t%7.4f|\n",target1[i],target2[i],target3[i]);
    }
  cout << endl;
};