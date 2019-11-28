#include "Number.h"
#include <iostream>

int &opeartor ++(Number num){
    return ++num.num;
}

Number Number::operator--() { return --num; }
}