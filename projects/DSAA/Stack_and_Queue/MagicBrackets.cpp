#include "iostream"
using namespace std;

void pop(int top) { top--; }

void push(char c[], int top, char b) { c[++top] = b; }

bool isMatch(char c[], int top, char b) {
  if (c[top] == b) {
    return true;
  }
  return false;
}

int main() {
  int t, n;
  char b;
  cin >> t;
  for (int i = 0; i < t; i++) {
    cin >> n;
    // if(n%2!=0){
    //     cout << "No" << endl;
    //     continue;
    // }
    char c[n];
    int top = -1;
    for (int i = 0; i < n; i++) {
      cin >> b;
      if (b == '(' || b == '[' || b == '{') {
        // push(c, top, b);
        c[++top] = b;
      } else {
        if (top <= -1) {
          top = -2;
          break;
        }
        if (isMatch(c, top, b)) {
        //   pop(top);
        top--;
        } else {
          break;
        }
      }
    }
    if (top != -1) {
      cout << "No" << endl;
    } else {
      cout << "Yes" << endl;
    }
  }
}