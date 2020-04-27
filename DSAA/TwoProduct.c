#include "stdio.h"

int main(){
    int N, l = 0, l0=-1, r0=-1, r,count=0, m=0, lN=-1, rP=-1;
    long long M;
    scanf("%d%lld", &N, &M);
    r=N - 1;
    long long as[N];
    long equalM[N];
    for(int i = 0;i < N;i++){
        scanf("%lld", &as[i]);
    }
    if(N==2){
        if(as[0]*as[1]==M){
            printf("1");
        }
        else {
        printf("0");
        }
        return 0;
    }
    for(int i = 1;i<N;i++){
        if(as[0] == 0){
            l0 = 0;
        }
        if(as[0] > 0){
            rP = 0;
        }
        else if(as[i-1]<0&&as[i]==0){
        l0=i;
        }
        else if (as[i-1]==0&&as[i]>0) {
        r0=i-1;
        }
        if(as[i-1]<0){
        lN=i-1;
        }
        if (as[i-1]<=0&&as[i]>0) {
        rP=i;
        }
        else if (as[i]==0) {
        r0 = i;
        }
    }

    if(M == 0){
        if(l0==-1&&r0==-1){
            printf("%d",0);
            return 0;
        }
        else if(r0-l0 >= 1){
            count++;
        }
        for(int i = l;i < l0;i++){
        if(i>0){
            if(as[i]==as[i-1]){
                continue;
            }
        }
        count++;
        }
        for(int i = r0;i < r;i++){
        if(i>r0){
            if(as[i]==as[i-1]){
                continue;
            }
        }
        count++;
        }
        printf("%d", count);
    }

    else if (M > 0) {
    // if(l0==-1&&r0==-1){
    //     while(l != r){
    //         if(as[l]*as[r] > M){
    //             l++;
    //         }
    //         else if (as[l]*as[r]<M) {
    //             r--;
    //         }
    //         else if (as[l]*as[r]==M) {
    //             equalM[m] = as[l] + as[r];
    //             m++;
    //             l++;
    //         }
    //     }
    // }
    // else if (l0!=-1) {
    if(lN!=-1){
    while(l !=lN){
        if(as[l]*as[lN] > M){
            l++;
        }
        else if (as[l]*as[lN]<M) {
            lN--;
        }
        else if (as[l]*as[lN]==M) {
            equalM[m] = as[l] + as[lN];
            m++;
            l++;
        }
    }
    }
    if(rP!=-1){
    while(rP !=r){
        if(as[r]*as[rP] > M){
                r--;
            }
            else if (as[r]*as[rP]<M) {
                rP++;
            }
            else if (as[r]*as[rP]==M) {
                equalM[m] = as[r] + as[rP];
                m++;
                rP++;
            }
    }
        }
    if(m>0){
        count++;
    }
    for(int i = 1;i < m;i++){
    if(equalM[i]==equalM[i-1]){
        continue;
    }
    count++;
    }
    printf("%d",count);
    return 0;
    //}
    }

    else if (M<0) {
        if((lN==-1)||(rP==-1)){
            printf("%d",0);
            return 0;
        }
        while(lN>=0&&r>=rP){
            if(as[lN]*as[r]>M){
                lN--;
            }
            else if (as[lN]*as[r]<M) {
                r--;
            }
            else if (as[lN]*as[r]==M) {
            equalM[m] = as[lN] + as[r];
            m++;
            if(r>rP){
                r--;
            }
            else if (lN > 0) {
                lN--;
            }
            }
        }
    if(m>0){
        count++;
    }
    for(int i = 1;i < m;i++){
    if(equalM[i]==equalM[i-1]){
        continue;
    }
    count++;
    }
    printf("%d",count);
    return 0;
    }
}