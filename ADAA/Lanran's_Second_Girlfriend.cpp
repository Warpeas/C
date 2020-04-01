#include <algorithm>
#include <iostream>

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

int isValidTime(int time) {}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);
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
  maxTime = min(maxInterval, (End - Start + 1) / n);
  int high = maxTime / 2, low = 1, result = 0;
  while (high < low) {
    result = (high + low) / 2;
    if (isValidTime(result)) {
      low = result + 1;
    } else {
      high = result;
    }
  }
  cout << result << "\n";
  return 0;
}