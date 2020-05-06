#include "iostream"
using namespace std;

int main(){
    long T=0,k =0;
    cin >> T;
    while(T>0){
        cin >> k;
        cout << (k + 1) * (k + 2) * k / 6 << endl;
        T--;
    }
}