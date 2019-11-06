#include<stdio.h>

int binary(int money, int prices[],int l,int r){
    if(money < prices[l]){
        return 0;
    }
    if(money >= prices [r]){
        return prices[r];
    }

    if(r==l+1){
        if(prices[r] < money){
            return prices[r];
        }else {
            return prices[l];
        }
    }

    int mid = (l + r) / 2;

    if(money == prices[mid]){
        return prices[mid];
    }else if (money > prices[mid]) {
        l = mid;
    }else if (money < prices[mid]) {
        r = mid;
    }
         return binary(money, prices, l, r);
}

int main(){
    int T, N = 0, day = 0, cash, price, l=0 ,r;
    
    scanf("%d",&T);
    int money[T];

    scanf("%d",&N);
    int prices[N];
        r = N-1;

    for(int i = 0;i < T;i++){
        scanf("%d",&cash);
        money[i] = cash;
        }
        for(int i = 0;i<N;i++){
        scanf("%d",&price);
        prices[i] = price;
        }
        
    while(day < T){
        money[day] -= binary(money[day],prices,l,r);
        if(money[day]==0){
            printf("Meow\n");
        }else {
        printf("%d\n",money[day]);
        }
        day++;
    }
}