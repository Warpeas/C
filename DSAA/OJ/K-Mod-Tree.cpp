#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 10000000
#define MAXSIZE 5000
long matrix[MAXSIZE][MAXSIZE];
long dij(int m, int n, int mod);
int main(void)
{
    int num;
    scanf("%d", &num);
    for (int i = 0; i < num + 1; i++)
        for (int j = 0; j < num + 1; j++)
            matrix[i][j] = MAX;
    for (int i = 1; i <= num - 1; i++)
    {
        int x, y;
        long value;
        scanf("%d %d %ld", &x, &y, &value);
        matrix[x][y] = value;
        matrix[y][x] = value;
    }
    int t;
    scanf("%d", &t);
    int max[t];
    for (int i = 1; i <= t; i++)
    {
        int from, mod;
        scanf("%d %d", &from, &mod);
        max[i] = dij(num + 1, from, mod);
    }
    for (int i = 1; i <= t; i++)
    {
        if (i != t)
            printf("%d\n", max[i]);
        else
            printf("%d", max[i]);
    }
}
long dij(int m, int n, int mod)
{
    long x[m], y[m], flag[m];
    for (int i = 0; i < m; i++)
    {
        x[i] = matrix[n][i];
        y[i] = -1;
        flag[i] = 0;
    }
    flag[n] = 1;
    int l = 0;
    int V = MAX;
    long f = 0;
    for (int i = 0; i < m - 1; i++)
    {
        l = 0;
        V = MAX;
        for (int j = 0; j < m; j++)
        {
            if (flag[j] != 1 && x[j] < V)
            {
                l = j;
                V = x[j];
            }
        }
        if (V == MAX)
            continue;
        flag[l] = 1;
        if (f < V % mod)
            f = V % mod;
        for (int j = 0; j < m; j++)
        {
            if (matrix[l][j] + V < x[j])
            {
                x[j] = matrix[l][j] + V;
                y[j] = l;
            }
        }
    }
    return f;
}
