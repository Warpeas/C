#include "iostream"
using namespace std;

void merge(int *A, int *L, int nl, int *R, int nr) {
  int n = nl + nr;
  int i = 0, j = 0;
  for (int k = 0; k < n; k++) {
    if (i < nl && (j == nr || L[i] <= R[j])) {
      A[k] = L[i++];
    } else {
      A[k] = R[j++];
    }
  }
}

void mergesort(int *A, int n) {
  if (n > 1) {
    int p = n / 2;
    int *B;
    B = new int[p];
    for (int i = 0; i < p; i++) {
      B[i] = A[i];
    }
    int *C;
    int j = 0;
    C = new int[n - p];
    for (int i = p; i < n; i++) {
      C[j++] = A[i];
    }
    mergesort(B, p);
    mergesort(C, n - p);
    merge(A, B, p, C, n - p);
  }
}