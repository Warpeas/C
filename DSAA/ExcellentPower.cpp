#include "iostream"
using namespace std;

struct gain {
  long long gain1, gain2, gain3;
  int num;
  int flag;
};

void merge1(gain *A, gain *R, int nr, gain *L, int nl) {
  int n = nl + nr;
  int i = 0, j = 0;
  for (int k = 0; k < n; k++) {
    if (i < nl && (j == nr || L[i].gain1 >= R[j].gain1)) {
      A[k] = L[i++];
    } else {
      A[k] = R[j++];
    }
    // if (j < nr && (i == nl || R[i].gain1 >= L[j].gain1)) {
    //   A[k] = R[j++];
    // } else {
    //   A[k] = L[i++];
    // }
  }
}

void mergesort1(gain *A, int n) {
  if (n > 1) {
    int p = n / 2;
    gain *B;
    B = new gain[p];
    for (int i = 0; i < p; i++) {
      B[i] = A[i];
    }
    gain *C;
    int j = 0;
    C = new gain[n - p];
    for (int i = p; i < n; i++) {
      C[j++] = A[i];
    }
    mergesort1(B, p);
    mergesort1(C, n - p);
    merge1(A, B, p, C, n - p);
  }
}

void merge2(gain *A, gain *R, int nr, gain *L, int nl) {
  int n = nl + nr;
  int i = 0, j = 0;
  for (int k = 0; k < n; k++) {
    if (i < nl && (j == nr || L[i].gain2 >= R[j].gain2)) {
      A[k] = L[i++];
    } else {
      A[k] = R[j++];
    }
    // if (j < nr && (i == nl || R[i].gain1 >= L[j].gain1)) {
    //   A[k] = R[j++];
    // } else {
    //   A[k] = L[i++];
    // }
  }
}

void mergesort2(gain *A, int n) {
  if (n > 1) {
    int p = n / 2;
    gain *B;
    B = new gain[p];
    for (int i = 0; i < p; i++) {
      B[i] = A[i];
    }
    gain *C;
    int j = 0;
    C = new gain[n - p];
    for (int i = p; i < n; i++) {
      C[j++] = A[i];
    }
    mergesort2(B, p);
    mergesort2(C, n - p);
    merge2(A, B, p, C, n - p);
  }
}

void merge3(gain *A, gain *R, int nr, gain *L, int nl) {
  int n = nl + nr;
  int i = 0, j = 0;
  for (int k = 0; k < n; k++) {
    if (i < nl && (j == nr || L[i].gain3 >= R[j].gain3)) {
      A[k] = L[i++];
    } else {
      A[k] = R[j++];
    }
    // if (j < nr && (i == nl || R[i].gain1 >= L[j].gain1)) {
    //   A[k] = R[j++];
    // } else {
    //   A[k] = L[i++];
    // }
  }
}

void mergesort3(gain *A, int n) {
  if (n > 1) {
    int p = n / 2;
    gain *B;
    B = new gain[p];
    for (int i = 0; i < p; i++) {
      B[i] = A[i];
    }
    gain *C;
    int j = 0;
    C = new gain[n - p];
    for (int i = p; i < n; i++) {
      C[j++] = A[i];
    }
    mergesort3(B, p);
    mergesort3(C, n - p);
    merge3(A, B, p, C, n - p);
  }
}

int main() {
  long n, p, q;
  long long total_atk = 0, doubles = 1, atk, hp;
  struct gain *gain;
  cin >> n >> p >> q;
  gain = new struct gain[n];
  for (; p > 0; p--) {
    doubles *= 2;
  }
  for (int i = 0; i < n; i++) {
    cin >> hp >> atk;
    total_atk += atk;
    gain[i].gain1 = hp - atk;
    // cout << gain[i].gain1 << " ";
    if (atk > hp) {
      gain[i].gain2 = doubles * hp - atk;
    } else {
      gain[i].gain2 = doubles * hp - hp;
    }
    gain[i].gain3 = doubles * hp - atk;
    // cout << gain[i].gain2 << " ";
    gain[i].num = i;
    // cout << gain[i].num << " ";
    gain[i].flag = 0;
    // cout << endl;
  }
  mergesort1(gain, n);
  // for(int i = 0;i < n;i++){
  //   cout << gain[i].gain1 << endl;
  // }
  int num=gain[0].num;
  for (int i = 0; i < q - 1; i++) {
    if (gain[i].gain1 <= 0) {
      break;
    }
    total_atk += gain[i].gain1;
    gain[i].flag = 1;
    num = gain[i + 1].num;
  }
  if(q != 1){
  mergesort2(gain, n);
  //   for(int i = 0;i < n;i++){
  //   cout << gain[i].gain1<< gain[i].gain2 <<" "<< num << endl;
  // }
  if (gain[0].gain2 > 0) {
    total_atk += gain[0].gain2;
    if(gain[0].flag == 0){
      total_atk += gain[0].gain1;
    }
    if (gain[0].flag == 1) {
      for (int i = 1; i < n; i++) {
        if (gain[i].num == num) {
          if(gain[i].gain1 > 0){
          total_atk += gain[i].gain1;
          }
          break;
        }
      }
    }
  }
  }
  else {
    mergesort3(gain, n);
    total_atk += gain[0].gain3;
  }
  cout << total_atk << endl;
}