#include "CandyBar.h"
#include "iostream"

using namespace std;

int main() {
  int n;
  cout << "Please enter the number of candybar:" << endl;
  cin >> n;
  CandyBar *candybars[n];
  for (int i = 0; i < n; i++) {
    candybars[i] = new CandyBar();
    cout << "CandyBar[" << i << "]" << endl;
    if (setCandyBar(*candybars[i]) == 1) {
      break;
    };
  }
  for (int i = 0; i < n; i++) {
    cout << "candybar[" << i << "]" << endl;
    showCandyBar(*candybars[i]);
    cout << endl;
  }
}