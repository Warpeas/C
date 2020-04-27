#include <stdio.h>
#include <stdlib.h>
int main(void) {
  long n, i, m, k;
  scanf("%ld %ld %ld %ld", &n, &i, &m, &k);
  long p[m];
  long have[m];
  for (int r = 0; r < m; r++) {
    scanf("%ld", &p[r]);
    have[r] = 0;
  }
  long youxi = m;
  long data = 0, evening = 0;
  for (; youxi != 0;) {
    data++;
    evening = 0;
    if (data != 1)
      for (long ee = 0; ee < m; ee++) {
        if (p[ee] > k)
          p[ee] -= k;
      }
    for (long ee = 0; ee < m; ee++) {
      if (n >= p[ee] && have[ee] == 0) {
        n -= p[ee];
        have[ee] = 1;
        youxi--;
      }
    }
    evening = 1;
    n += i;
    for (long ee = 0; ee < m; ee++) {
      if (n >= p[ee] && have[ee] == 0) {
        n -= p[ee];
        have[ee] = 1;
        youxi--;
      }
    }
  }
  printf("%d ", data);
  if (evening == 0)
    printf("morning");
  if (evening == 1)
    printf("evening");
}