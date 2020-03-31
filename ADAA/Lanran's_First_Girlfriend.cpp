#include <algorithm>
#include <iostream>


using namespace std;
class trick {
private:
  int start;
  int end;

public:
  trick();
  trick(int s, int e) {
    start = s;
    end = e;
  }
  int getStart() { return start; }
  int getEnd() { return end; }
  friend bool operator<(trick &t1, trick &t2) { return t1.end < t2.end; }
};

int t, n;

int main() {
  cin.sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);
  cout.sync_with_stdio(0);
  cin >> t;
  while (t > 0) {
    --t;
    cin >> n;
    trick *tricks[n];
    int start, end;
    for (int i = 0; i < n; i++) {
      cin >> start >> end;
      tricks[i] = new trick(start, end);
    }
    sort(tricks, tricks + n);
    int currentEnd = 0, cnt = 0;
    for(int i = 0;i < n ;i++){
    if(tricks[i]->getStart()>currentEnd){
        ++cnt;
        currentEnd = tricks[i]->getEnd();
    }
    }
    cout << cnt << "\n";
  }
  return 0;
}