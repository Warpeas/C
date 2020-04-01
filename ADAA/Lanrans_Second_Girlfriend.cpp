#include <algorithm>
#include <iostream>
#include <map>
#include <fstream>

using namespace std;
int n, t, maxTime;
class trick {
private:
  int start;
  int end;

public:
  trick(){};
  trick(int s, int e) {
    start = s;
    end = e;
  }
  int getStart() { return start; }
  int getEnd() { return end; }
  friend bool operator<(const trick &t1, const trick &t2) {
    return t1.end < t2.end;
  }
} * tricks;

struct interval {
  int start, end;
};
map<int, interval> intervals;

int isValidTime(int t, int time[], int end, trick *tricks) {
  int cnt, s, e;
  for (int i = 0; i < n; i++) {
    cnt = t;
    s = tricks[i].getStart();
    e = tricks[i].getEnd();
    int start = -1, end = -1;
    for (int j = s - 1; j < e && cnt > 0; j++) {
      if (time[j] != t) {
        if (start == -1) {
          start = j;
        }
        cnt--;
        time[j] = t;
        end = j;
      }else {
        if(intervals.find(j)!=intervals.end()){
          j = intervals.find(j)->second.end;
        }
      }
    }
    if (cnt != 0) {
      return 0;
    }
    if (start != -1) {
      intervals.insert(pair<int, interval>(start, interval{start, end}));
    }
  }
  intervals.clear();
  return 1;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);
  ifstream fin;
  fin.open("input.txt");
  cin.rdbuf(fin.rdbuf());
  cin >> n;
  trick *tricks = new trick[n]();
  int start, end, maxInterval = 0, End = 0, Start = 0;
  for (int i = 0; i < n; i++) {
    cin >> start >> end;
    if (end - start > maxInterval - 1) {
      maxInterval = end - start + 1;
    }
    End = max(end, End);
    Start = min(Start, start);
    tricks[i] = *new trick(start, end);
  }
  sort(tricks, tricks + n);
  int *time = new int[End]();
  maxTime = min(maxInterval, (End - Start + 1) / n);
  int high = maxTime, low = 1, result = 0, p = 0;
  while (high >= low) {
    p = (high + low) / 2;
    if (isValidTime(p, time, end, tricks)) {
      result = p;
      low = p + 1;
    } else {
      high = p - 1;
    }
  }
  cout << result << "\n";
  return 0;
}