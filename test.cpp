#include<iostream>
#include<string>
using namespace std;
int main(){
    string str;
    string str1;
    cin >> str;
    // cout << sizeof(str) << endl;
    // cout << sizeof(str[0]) << endl;
    // cout << str.max_size() << endl;
    // cout << str.length() << endl;
    for (int i = 0; i < str.length(); i++){
        if(str[i]<='Z'&&str[i]>='A'){
            str1[i] = 155 - str[i];
        }if(str[i]<='z'&&str[i]>='a'){
            str1[i] = 219 - str[i];
        }
        cout << str1[i];
    }
return 0;
}