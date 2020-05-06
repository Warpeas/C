#include "stdio.h"

long long mod(long m){
    long long sum = 1;
    for(int i = 1;i <= 720;i++){
        sum *= (i % m);
        sum %= m;
    }
    // for(int i = 0;i < 720;i++){
    //     sum *= (720 - i) % m;
    // }
    return sum;
}

int main(){
    long n,m;
    scanf("%ld",&n);
    scanf("%ld",&m);
    if(n == 0){
        n = 1;
        printf("%ld",n%m);
        return 0;
    }
    else if(n <= 2){
        printf("%ld", n%m);
        return 0;
    }
    else if(n > 3){
        printf("%d",0);
    }
    else if(n==3){
        printf("%lld", mod(m));
    }
}