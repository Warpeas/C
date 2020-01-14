#include <iostream>
using namespace std;
int main() {
    int *p=new int[10]();
    int *q = p;
    p[0] = 1;
    p++;
    delete (p+1);
    p--;
    cout << q << endl;
 }