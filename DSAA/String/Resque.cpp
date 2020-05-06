#include "iostream"
#include "string"
using namespace std;

int main(){
    char l[26];
    char s[500000];
    int len = 0;
    for(int i = 0;i < 26;i++){
        scanf("%s", &l[i]);
    }
    getchar();
    // do{
    //     // scanf("%s", &s[len++]);
    //     s[len++] = getchar();
    // } while (s[len - 1] != '\0');
    cin.get(s,500000);
    while(s[len]!='\0'){
        len++;
    }
    int mid = len / 2;
    while(l[s[mid]-'a']!=s[len-1]){
        mid--;
    }
    printf("%d", 2 * mid - len);
}