#include <iostream>
#include <hash_map>

using namespace std;
/*
|   score   |   points  |
|   2:0     |   3       |
|   2:1     |   2       |
|   1:1     |   1       |
|   1:2     |   1       |
|   0:2     |   0       |
*/

/*
hashmap<(string)number+score:(int)cnt>
*/

/*
a (n)*(n) matrix
and a n array store each score
first dfs, fill each grid
when the left empty grid is the number n then find if the situation is calculate
use sorted array? relate with an int
*/
int n;
int dfs(int player[10],int scores[10][10]){
    __gnu_cxx::hash_map<int[10], int> hm;
    hm.insert(player[10], 1);
    cout << hm.begin()->second;
    return 0;
}

int main(){
    int player[10] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int scores[10][10];
    dfs(player, scores);
    return 0;
}
