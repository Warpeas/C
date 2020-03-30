#include <iostream>
#include <algorithm>

using namespace std;
class trick{
    private:
        int start;
        int end;

    public:
    trick(int s, int e){
        start = s;
        end = e;
    }
    int getStart() { return start; }
    int getEnd() { return end; }
    friend bool operator< (trick &t1, trick &t2){
        return t1.end < t2.end;
    }
};

int t, n;

int main(){
    cin.sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cout.sync_with_stdio(0);
    cin >> t;
    while(t > 0){
        --t;
        cin >> n;
    }
    return 0;
}