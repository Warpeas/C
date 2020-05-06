#include <iostream>
#include <string>
using namespace std;

long long jie(int);

int main()
{
    int k, t;
    long long s, n;
    cin >> t;
    for (int j = 0; j < t; j++)
    {
        cin >> k >> n;
        int str[k];
        s = jie(n);
        int i;
        for (i = 0; i < k; i++)
        {
            if (s)
            {
                str[i] = s % 10;
                s /= 10;
            }
            else
            {
                break;
            }
        }
        for (int l = i - 1; l >= 0; l--)
        {
            cout << str[l];
        }
        cout << endl;
    }
    return 0;
}

long long jie(int n)
{
    long long s = 1;
    for (int i = 1; i <= n; i++)
    {
        s = s * i;
        while (s % 10 == 0)
            s /= 10;
        s = s % 1000000000;
    }
    return s;
}
