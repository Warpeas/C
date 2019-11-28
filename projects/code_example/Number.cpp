#include "Number.h"
#include <iostream>

int &opeartor ++(Number num){
    return ++num.num;
}

int &operator ++(){
    return num++;
}