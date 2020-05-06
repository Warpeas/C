#include<stdio.h>
#include "stdlib.h"
#include "math.h"

long long constructMagicNumber(int front[],int bits, int size){
    int magicNumber=0;
    if((bits/2)*2<bits){
        for(int i = 0;i < size;i++){
            if(i==0)
            magicNumber += front[i] * pow(10, bits / 2);
            if(bits>=3)
            magicNumber += front[i] * pow(10, bits / 2 - i) + front[i] * pow(10, bits / 2 + i);
        }
    }
    else {
    for(int i = 0;i < size;i++){
        if(bits==1){
            magicNumber = front[i];
        }else {
            magicNumber += front[i] * pow(10, bits / 2 - i -1) + front[i] * pow(10, bits / 2+i);
        }
    }
    }
    return magicNumber;
}
int main(){
    int amount = 0;
    long long max = 0;
    int bits = 0;
    int sum = 0;
    int size;
    int magicNumber = 0;
    long count;
    long long total = 0;
    scanf("%d",&amount);
    for(;amount>0;amount--){
        scanf("%lld",&max);
        int m = max;
        bits = 0;
        sum = 0;
        size = 0;
        total = 0;
        count = 1;
        while (m>0){
            m /= 10;
            bits++;
        }
        m = max / pow(10,bits/2);

        if(bits!=0){
            int front[bits / 2 + 1];
            for(int i =0;m!=0;m/=10){
            front[i] = m%10;
            i++;
            size++;
            }

        magicNumber = constructMagicNumber(front, bits, size);

        while(magicNumber > max){
            for(int i = 0;i<size;i++){
                if(front[i]>=1){
                front[i]--;
                break;
                }
            }
            if(front[size-1]==0){
                if(bits!=2)
                    size--;
                bits--;   
                }
            for(int i =0;i < size;i++){
                    front[i] = 9;
                }


            magicNumber = constructMagicNumber(front, bits, size);
            }

            for(int i = 0;i < size-1;i++){
                count *= (front[i]+1);
            }
            count *= front[size - 1];
            total += count;

            bits--;

            while(bits!=0){
                if(bits/2*2==bits){
                    total += (9 * pow(10, bits / 2 - 1));
                }else {
                    total += (9 * pow(10, bits / 2));
                }
                bits--;
            }
    }
        total += 1;
        printf("%lld\n",total);
    }
}