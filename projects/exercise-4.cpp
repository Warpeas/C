#include "iostream"
#include "fstream"
using namespace std;

int main(){
    fstream fOut("exercise4.txt");
    if(fOut.is_open()){
        for(int i = 2;i < 9;i++){
        for(int j = 1;j < i;j++){
            fOut << j;
            cout << j;
        }
        for(int k = 8 - i;k>0;k--){
            fOut << '*';
            cout << '*';
        }
        fOut << endl;
        cout << endl;
        }
    }
}