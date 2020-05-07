#include <iostream>
#include <fstream>

using namespace std;
int main() {
  long long v = 1, c = 0, n = 0, lv, lc, ln;
    ofstream fo;
  fo.open("output.txt");
  cout.rdbuf(fo.rdbuf());
  for (int i = 0; i < 60; i++) {
    lv = v;
    lc = c;
    ln = n;
    cout << "{" << v << ", " << c << ", " << n << "}, ";
    v = lv + ln;
    c = lc + lv;
    n = ln + lc;
  }
}