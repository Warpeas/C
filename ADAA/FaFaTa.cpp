#include <algorithm>
#include <cmath>
#include <complex>
#include <iostream>

#define cp complex<double>
#define PI acos((long double)-1.0)
#define pi PI

using namespace std;
int zys[50];
int n;

struct polynumber {
  long long num, exp;
  polynumber *next;
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
void fft(cp *a, int n, int inv) {
  int bit = 0;
  int rev[n];
  while ((1 << bit) < n)
    bit++;
  for (int i = 0; i < n; i++) {
    rev[i] = (rev[i >> 1] >> 1) | ((i & 1) << (bit - 1));
    if (i < rev[i])
      swap(a[i], a[rev[i]]); //不加这条if会交换两次（就是没交换）
  }
  for (int mid = 1; mid < n; mid *= 2) // mid是准备合并序列的长度的二分之一
  {
    cp temp(cos(pi / mid), inv * sin(pi / mid)); //单位根，pi的系数2已经约掉了
    for (int i = 0; i < n;
         i += mid * 2) // mid*2是准备合并序列的长度，i是合并到了哪一位
    {
      cp omega(1, 0);
      for (int j = 0; j < mid;
           j++, omega *= temp) //只扫左半部分，得到右半部分的答案
      {
        cp x = a[i + j], y = omega * a[i + j + mid];
        a[i + j] = x + y, a[i + j + mid] = x - y; //这个就是蝴蝶变换什么的
      }
    }
  }
}
double *FFT(int n, double a[], double inv) {
  if (n == 1)
    return a;
  double *e = new double[n / 2]();
  double *d = new double[n / 2]();
  for (int k = 0; 2 * k < n / 2; k++) {
    e[k] = a[2 * k];
    a[2 * k] = 0;
    d[k] = a[2 * k + 1];
    a[2 * k + 1] = 0;
  }
  e = FFT(n / 2, e, inv);
  d = FFT(n / 2, d, inv);
  double w;
  double t = inv == 1.0 ? n : 1;
  for (int k = 0; k < n / 2; k++) {
    w = cos(inv * 2 * PI * k / n) + sin(inv * 2 * PI * k / n);
    a[k] = (e[k] + w * d[k]) / t;
    a[k + n / 2] = (e[k] - w * d[k]) / t;
  }
  return a;
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
  long long n, p, o = 0, pr;
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
  long long *rtx = new long long[p];
  long long rem = 0;
  long long *xtr = new long long[p];
  rtx[0] = 0, xtr[0] = 0;
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

  long long Ai;

  for (int i = 0; i < n; i++) {
    cin >> Ai;
    e[i] = rtx[Ai % p];
    // b[e[i]]+=1;
  }

  //  FFT
  // int len = next_power_of_two(p) + 1;
  // long long *b = new long long[len]();
  // double *b = new double[len]();
  // cp *b = new cp[len]();
  // for (int i = 0; i < len; i++) {
  //   b[i] = 0;
  // }
  // double *c = new double[len]();
  // b++;
  // b = FFT(len - 1, b, -1);
  // // fft(b, len - 1, -1);
  // for (int i = 0; i < len - 1; i++) {
  //   b[i] = b[i] * b[i];
  // }
  // c++;
  // c = FFT(len - 1, b, 1);
  // // fft(b, len - 1, 1);
  // c--;
  // for (int i = 0; i < len; i++) {
  //   cout << c[i] << " ";
  // }
  // cout << endl;
  // long long exp, r;
  // for (int i = 1; i < p; i++) {
  //   exp = 2 * i;
  //   r = xtr[exp % (p - 1)];
  //   rtn[r] = rtn[r] + c[i];
  // }
  // b--;
  // for (int i = 0; i < p; i++) {
  //   rtn[0] += b[0] * b[i];
  // }

  sort(e, e + n);

  //  n^2 poly multiply
  polynumber *pn = new polynumber{1, e[0]};
  polynumber *head = pn;
  for (int i = 1; i < n; i++) {
    if (e[i] == pn->exp) {
      pn->num++;
    } else {
      pn->next = new polynumber{1, e[i]};
      pn = pn->next;
    }
  }

  long exp;
  polynumber *p1 = head, *p2 = head;
  while (p1 != nullptr) {
    while (p2 != nullptr) {
      exp = (p1->exp + p2->exp) % (p - 1);
      exp = exp == 0 ? (p - 1) : exp;
      rtn[xtr[exp]] += p1->num * p2->num;
      // cout << xtr[exp] << " " << rtn[xtr[exp]] << endl;
      p2 = p2->next;
    }
    p2 = head;
    p1 = p1->next;
  }

  for (int i = 0; i < p; i++) {
    cout << rtn[i] << endl;
  }

  return 0;
}
