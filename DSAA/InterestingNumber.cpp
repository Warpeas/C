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

int main() {
  int T, n;
  cin >> T;
  while (T > 0) {
    T--;
    cin >> n;
    int *A;
    A = new int[n];
    for (int i = 0; i < n; i++) {
      cin >> A[i];
    }
    mergesort(A, n);
    if (n == 3) {
      if (A[n - 3] == A[n - 2]) {
        cout << "wa" << endl;
      } else {
        cout << A[n - 3] << endl;
      }
    } else {
      if (A[n - 3] == A[n - 4] || A[n - 3] == A[n - 2]) {
        cout << "wa" << endl;
      } else {
        cout << A[n - 3] << endl;
      }
    }
  }
}