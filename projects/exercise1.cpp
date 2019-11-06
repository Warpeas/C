#include <iostream>
using namespace std;

struct CandyBar {
  /* data */
  char brand[30];
  double weight;
  int calorie;
};

void set(CandyBar &cb) {
  cout << "Call the set function of Passing by reference: " << endl
       << "Please set the brand name: ";
  cin.get(cb.brand, 30);
  cout << "Please set the weight: ";
  cin >> cb.weight;
  cout << "Please set the calorie: ";
  cin >> cb.calorie;
}

void set(CandyBar *const cb) {
  cout << "Call the set function of Passing by pointer: " << endl
       << "Please set the brand name: ";
  cin.get(cb->brand, 30);
  cout << "Please set the weight: ";
  cin >> cb->weight;
  cout << "Please set the calorie: ";
  cin >> cb->calorie;
}

void show(const CandyBar &cb) {
  cout << "Call the set function of Passing by reference: " << endl
       << "Brand: " << cb.brand << endl
       << "Weight: " << cb.weight << endl
       << "Calorie: " << cb.calorie << endl;
}

void show(const CandyBar *cb) {
  cout << "Call the set function of Passing by pointer: " << endl
       << "Brand: " << cb->brand << endl
       << "Weight: " << cb->weight << endl
       << "Calorie: " << cb->calorie << endl;
}

int main() {
  CandyBar *cb = new CandyBar;
  set(cb);
  show(cb);
  cin.sync();
  cout << endl;
  set(*cb);
  show(*cb);
  return 0;
}