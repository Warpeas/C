#include "iostream"
using namespace std;
int cnt = 0;

int binaryLowerBound(long long key, long long a[], int l, int n) {
  int left = l + 1, right = n - 1;
  while (left < right) {
    // int mid = left + ((right - left) >> 1);
    int mid = (left + right) / 2;
    if (a[mid] < key)
      left = mid + 1;
    else
      right = mid;
  }
  if (left < n && a[left] == key) {
    return left;
  }
  return -1;
}

int binaryUpperBound(long key, long long a[], int l, int n) {
  int left = l + 1, right = n - 1;
  while (left < right) {
    // int mid = left + ((right - left) >> 1);
    int mid = (left + right) / 2;
    if (a[mid] <= key)
      left = mid + 1;
    else
      right = mid;
  }
  if (a[left] == key) {
    return left;
  } else if (a[left - 1] == key) {
    return left - 1;
  }
  return -1;
}

int binary(long key, long long a[], int l, int n) {
  int left = l + 1;
  int right = n - 1;
  int mid = (left + right) / 2;
  while (a[mid] != key) {
    mid = (left + right) / 2;
    if (a[mid] < key) {
      left = mid + 1;
    } else if (a[mid] > key) {
      right = mid - 1;
    }
    if (left >= right) {
      return 0;
    }
  }
  int i, j, num = -1;
  for (i = mid; a[i] == key && i > l; i--) {
    num++;
  }
  for (j = mid; a[j] == key; j++) {
    num++;
  }
  return num;
}

void merge(long long *A, long long *L, int nl, long long *R, int nr) {
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

void mergesort(long long *A, int n) {
  if (n > 1) {
    int p = n / 2;
    long long *B;
    B = new long long[p];
    for (int i = 0; i < p; i++) {
      B[i] = A[i];
    }
    long long *C;
    int j = 0;
    C = new long long[n - p];
    for (int i = p; i < n; i++) {
      C[j++] = A[i];
    }
    mergesort(B, p);
    mergesort(C, n - p);
    merge(A, B, p, C, n - p);
  }
}

int main() {
  int n;
  long long *A, m;
  cin >> n >> m;
  A = new long long[n];
  for (int i = 0; i < n; i++) {
    cin >> A[i];
  }
  mergesort(A, n);
  // long long B[n][2];
  // int a = 0;
  // for(int i = 0;i < n;i++){
  //   B[a][0] = A[i];
  //   B[a][1]++;
  //   if(A[i]!=A[i+1]){
  //     a++;
  //   }
  // }
  // for(int i = 0, j = 1, k = a - 1;;){
  // if(B[i][0] + B[j][0] + B[k][0] == m){
  //   cnt +=
  // }
  // }
  // for (int j = 1, k = n - 1, i = 0; A[i] < m;) {

  //   if (j == k) {
  //     j++;
  //     k = n - 1;
  //   }

  //   if (A[i] + A[j] + A[k] == m) {
  //     // if (A[j] == A[k]) {
  //     // long long c = k - j;
  //     // j = k - 1;
  //     // int total = 1;
  //     //   // for (; c >= k - j; c--) {
  //     //   //   total *= c;
  //     //   // }
  //     //   // total /= 2;
  //     // for(;c>1;c--){
  //     //   total *= c;
  //     // }
  //     //   cnt += c * (c + 1) / 2;
  //     // } else {
  //     //   int a = 1, b = 1;

  //     //   for (; A[j] == A[j + 1] && j < k - 1; j++) {
  //     //     a++;
  //     //   }
  //     //   for (; A[k] == A[k - 1] && j < k - 1; k--) {
  //     //     b++;
  //     //   }
  //     //   cnt += a * b;
  //     //   i = j;
  //     // }
  //     cnt++;
  //   }
  //   k--;
  //   if (j == n - 2) {
  //     i++;
  //     j = i + 1;
  //     k = n - 1;
  //     if (k == j) {
  //       break;
  //     }
  //   }
  //   // if (j == k - 1) {
  //   //   j++;
  //   //   k = n - 1;
  //   // }
  //   // } else if (A[i] + A[j] + A[k] < m) {
  //   //   j++;
  //   // } else if (A[i] + A[j] + A[k] > m) {
  //   //   k--;
  //   // }
  // }
  // int j = 1, k = n - 1, i = 0;
  // while (0) {
  //   if (A[i] + A[j] + A[k] == m) {
  //     cnt++;
  //     k--;
  //   } else if (A[i] + A[j] + A[k] < m) {
  //     j++;
  //   } else if (A[i] + A[j] + A[k] > m) {
  //     k--;
  //   }
  //   if (j == k) {
  //     i++;
  //     j = i + 1;
  //     k = n - 1;
  //     if(k == j){
  //       break;
  //     }
  //   }
  for (int i = 0, j = 1; j <= n - 2; j++) {
    // int k = binaryUpperBound(m - A[i] - A[j], A, j, n) -
    //         binaryLowerBound(m - A[i] - A[j], A, j, n) + 1;
    // if (k > 0 && binaryLowerBound(m - A[i] - A[j], A, j, n) != -1) {
    //   cnt += k;
    // }

    cnt += binary(m - A[i] - A[j], A, j, n);
    if (j == n - 2) {
      i++;
      j = i;
    }
  }
  cout << cnt << endl;
}