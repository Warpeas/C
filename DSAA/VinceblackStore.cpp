#include "iostream"
using namespace std;
long cost = 0;

void merge(long *A, long *L, int nl, long *R, int nr) {
  int n = nl + nr;
  int i = 0, j = 0;
  for (int k = 0; k < n; k++) {
    if (i < nl && (j == nr || L[i] <= R[j])) {
      A[k] = L[i++];
    } else {
      cost += R[j] * (nl - i);
      for (int l = i; l < nl; l++)
      {
        cost += L[l];
        }
      A[k] = R[j++];
    }
  }
}

void mergesort(long *A, int n) {
  if (n > 1) {
    int p = n / 2;
    long *B;
    B = new long[p];
    for (int i = 0; i < p; i++) {
      B[i] = A[i];
    }
    long *C;
    int j = 0;
    C = new long[n - p];
    for (int i = p; i < n; i++) {
      C[j++] = A[i];
    }
    mergesort(B, p);
    mergesort(C, n - p);
    merge(A, B, p, C, n - p);
  }
}

int main() {
  long n, *v;
  cin >> n;
  v = new long[n];
  for (int i = 0; i < n; i++) {
    cin >> v[i];
  }
  mergesort(v, n);
  cout << cost << endl;
  return 0;
}