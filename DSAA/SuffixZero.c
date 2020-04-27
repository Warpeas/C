#include "stdio.h"

long zeros(long N){
    if(N<5){
        return 0;
    }
    return N / 5 + zeros(N / 5);
}

int main(){
    int T;
    long N;
    scanf("%d",&T);
    while(T>0){
        scanf("%ld", &N);
        printf("%ld\n",zeros(N));
        T--;
    }
}