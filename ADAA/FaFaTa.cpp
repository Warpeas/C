#include <algorithm>
#include <cmath>
#include <iostream>

#define PI acos((long double)-1.0)
#define Pi PI

using namespace std;
int zys[50];
int n;

struct polynumber {
  long long num, exp;
  polynumber *next;
};

struct complex {
  long double x, y;
  inline complex operator+(const complex b) const {
    return (complex){x + b.x, y + b.y};
  }
  inline complex operator*(const complex b) const {
    return (complex){x * b.x - y * b.y, x * b.y + y * b.x};
  }
  inline complex operator-(const complex b) const {
    return (complex){x - b.x, y - b.y};
  }
  inline complex operator/(const double t) { return complex{x / t, y / t}; }
};

unsigned long next_power_of_two(unsigned long v) {
  v--;
  v |= v >> 1;
  v |= v >> 2;
  v |= v >> 4;
  v |= v >> 8;
  v |= v >> 16;
  v++;
  return v;
}

// void FFT(int length, complex *A, const int fla) {
//   if (length == 1)
//     return;
//   complex A1[length >> 1], A2[length >> 1];
//   for (int i = 0; i < length; i += 2)
//     A1[i >> 1] = A[i], A2[i >> 1] = A[i + 1];
//   FFT(length >> 1, A1, fla), FFT(length >> 1, A2, fla);
//   const complex w =
//       (complex){static_cast<double>(cos(Pi * 2.0 / length)),
//                 static_cast<double>(sin(Pi * 2.0 / length) * fla)};
//   complex k = (complex){1, 0};
//   length >>= 1;
//   for (int i = 0; i < length; i++, k = k * w) {
//     A[i] = A1[i] + k * A2[i];
//     A[i + length] = A1[i] - k * A2[i];
//   }
// }

void FFT(int n, complex a[], double inv) {
  if (n == 1)
    return;
  complex *e = new complex[n / 2]();
  complex *d = new complex[n / 2]();
  for (int k = 0; k < n / 2; k++) {
    e[k] = a[2 * k];
    d[k] = a[2 * k + 1];
  }
  FFT(n / 2, e, inv);
  FFT(n / 2, d, inv);
  complex w;
  double t = inv == 1.0 ? 1 : n;
  for (int k = 0; k < n / 2; k++) {
    w = complex{static_cast<double>(cos(-inv * 2.0 * PI * k / n)),
                static_cast<double>(sin(-inv * 2.0 * PI * k / n))};
    a[k] = (e[k] + w * d[k]) / t;
    a[k + n / 2] = (e[k] - w * d[k]) / t;
  }
  // for (int i = 1; i < n; i <<= 1) {
  //   const complex w = (complex){cos(PI / i), -inv * sin(PI / i)};
  //   for (int j = 0; j < n; j += (i << 1)) {
  //     complex K = (complex){1, 0};
  //     for (int k = 0; k < i; k++, K = K * w) {
  //       const complex x = a[j + k], y = a[j + k + i] * K;
  //       a[j + k] = x + y;
  //       a[j + k + i] = x - y;
  //     }
  //   }
  // }
}

//  get res
long long ksm(int a, int c, int b) {
  long long re = 1;
  long long t = a % b;
  while (c) {
    if (c & 1)
      re = re % b * t % b;
    t *= t;
    t %= b;
    c >>= 1;
  }
  return re;
}

int pd(int x, int p, int o) {
  for (int i = 1; i <= o; i++) {
    if (ksm(x, (p - 1) / zys[i], p) % p == 1)
      return 0;
  }
  return 1;
}

int main() {
  long long n, p, o = 0, pr = 0;
  cin.sync_with_stdio(0);
  cin.tie(0);
  cout.sync_with_stdio(0);
  cout.tie(0);
  cin >> n >> p;
  long long pp = p;
  p--;
  for (int i = 2; i <= sqrt(p); i++) {
    if (p == 1)
      break;
    if (p % i == 0) {
      zys[++o] = i;
      while (p % i == 0) {
        p /= i;
      }
    }
  }
  if (p != 1) {
    zys[++o] = p;
  }
  p = pp;
  for (int i = 2; i < p; i++) {
    if (pd(i, p, o)) {
      pr = i;
      // printf("%d", i);
      break;
    }
  }
  // cout <<p<<" "<< pr << endl;
  long long rtx[p];
  long long rem;
  long long *xtr = new long long[p];
  rtx[0] = 0, xtr[0] = 1;
  for (int i = 1; i < p; i++) {
    rem = pow(pr, i);
    rem %= p;
    rtx[rem] = i;
    xtr[i] = rem;
  }
  long long *rtn = new long long[p]();
  long long e[n];
  for (int i = 0; i < p; i++) {
    rtn[i] = 0;
  }
  int len = next_power_of_two(2 * p) + 1;

  long long Ai;
  // long long *b = new long long[len]();
  // double *b = new double[len]();
  complex b[len];
  for (int i = 0; i < len; i++) {
    b[i].x = 0;
    b[i].y = 0;
  }
  for (int i = 0; i < n; i++) {
    cin >> Ai;
    e[i] = rtx[Ai % p];
    b[e[i]].x += 1;
  }
  for (int i = 0; i < len; i++) {
    cout << b[i].x << " ";
  }
  cout << endl;
  //  FFT
  FFT(len - 1, b + 1, 1.0);
  for (int i = 0; i < len; i++) {
    cout << b[i].x << " ";
  }
  cout << endl;
  for (int i = 1; i < len; i++) {
    b[i] = b[i] * b[i];
  }
  for (int i = 0; i < len; i++) {
    cout << b[i].x << " ";
  }
  cout << endl;
  FFT(len - 1, &b[1], -1.0);
  for (int i = 0; i < len; i++) {
    cout << b[i].x << " ";
  }
  cout << endl;
  int r;
  for (int i = 1; i < len; i += 2) {
    r = xtr[(i + 1) % (p - 1)];
    rtn[r] = rtn[r] + (long long)(b[i].x + 0.5);
  }
  for (int i = 0; i < len; i++) {
    rtn[0] += b[0].x * b[i].x;
  }

  // sort(e, e + n);

  // //  n^2 poly multiply
  // polynumber *pn = new polynumber{1, e[0]};
  // polynumber *head = pn;
  // for (int i = 1; i < n; i++) {
  //   if (e[i] == pn->exp) {
  //     pn->num++;
  //   } else {
  //     pn->next = new polynumber{1, e[i]};
  //     pn = pn->next;
  //   }
  // }

  // long exp;
  // polynumber *p1 = head, *p2 = head;
  // while (p1 != nullptr) {
  //   while (p2 != nullptr) {
  //     exp = (p1->exp + p2->exp) % (p - 1);
  //     exp = exp == 0 ? (p - 1) : exp;
  //     rtn[xtr[exp]] += p1->num * p2->num;
  //     // cout << xtr[exp] << " " << rtn[xtr[exp]] << endl;
  //     p2 = p2->next;
  //   }
  //   p2 = head;
  //   p1 = p1->next;
  // }

  for (int i = 0; i < p; i++) {
    cout << rtn[i] << endl;
  }

  return 0;
}
