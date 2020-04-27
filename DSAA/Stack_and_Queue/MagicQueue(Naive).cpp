#include "cstdio"
using namespace std;

int main(){
    int n;
    char o;
    int x;
    scanf("%d", &n);
    int *q = new int[n+1];
    int f = 0;
    int r = 0;
    for(int i = 0;i < n;i++){
        o = getchar();
        if (o == 'E'){
            scanf("%d", &x);
            q[r] = x;
            r++;
    }else if (o == 'D') {
        f++;
    }else if (o == 'A') {
    if(f != r){
        printf("%d\n", q[f]);
    }
    }
    }
    for(int i = f;i < r;i++){
        printf("%d ", q[f]);
    }
}