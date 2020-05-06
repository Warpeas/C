#include "stdio.h"

    int main() {
        int t;
        scanf("%d",&t);
        for (int i = 0; i < t; i++) {
            long long n = 0;
            long long k;
            scanf("%lld%lld",&n,&k);
            long long sum = 1;
            long long b = 1;
            for (; sum <= n; sum = sum * k + 1) {
                b *= k;
            }
            long long a = (b - 1) / (k - 1);
            long long out = (b - n + a) / k + n - a;
            printf("%lld\n",out);
        }
    }
