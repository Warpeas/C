#include <cmath>
#include <iostream>
#include <string>
using namespace std;

int main() {
  string s;
  cin >> s;
  int S = 0;
  int w;
  for (int i = 0; i < 17; i++) {
    w = (int)pow(2, 18 - i) % 11;
    w *= stoi(s.substr(i, 1));
    S += w;
  }
  cout << w << endl;
  return 0;
}