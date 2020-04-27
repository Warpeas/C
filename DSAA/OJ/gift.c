#include <stdio.h>
#include <stdlib.h>
long x, y, gift;
long score[2][100001];
long f(long l, long s, long g1, long g2)
{
    if (l == gift)
        return s;
    long q1 = 0, q2 = 0, q3 = 0;
    if (g1 < x)
        q1 = f(l + 1, s + score[0][l], g1 + 1, g2);
    if (g2 < y)
        q2 = f(l + 1, s+ score[1][l], g1, g2+1);
    if (g2 < y && g1 < x)
        q3 = f(l + 1, s , g1, g2);
    if(q1>=q2&&q1>=q3)
        return q1;
    if(q2>=q1&&q2>=q3)
        return q2;
    if(q3>=q1&&q3>=q2)
        return q3;
    //return s;
}

int main(void)
{
    long times;
    scanf("%ld", &times);
    long x1[times];
    for (long t = 0; t < times; t++)
    {
        scanf("%ld %ld %ld", &gift, &x, &y);
        for (long r = 0; r < gift; r++)
            scanf("%ld", &score[0][r]);
        for (long r = 0; r < gift; r++)
            scanf("%ld", &score[1][r]);
        x1[t] = f(0, 0, 0, 0);
    }
    for (long t = 0; t < times; t++)
    {
        printf("%ld ",x1[t]);
    }
}