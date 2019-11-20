#include "CandyBar.h"
#include "cstring"
#include "string"
#include "iostream"
using namespace std;

int setCandyBar(CandyBar &snack) {
  cout << "Enter the brand name of a Candy bar: ";
  string str;
  getchar();
  getline(cin, str);
  *snack.brand = *str.c_str();
  if (strlen(snack.brand) == 0) {
    cout << "empty name detected!" << endl;
    return 1;
  }
  cout << "Enter weight of the candy bar: ";
  cin >> snack.weight;
  cout << "Enter calories (an integer value) in the candy bar: ";
  cin >> snack.calorie;
  return 0;
};
void showCandyBar(const CandyBar &snack) {
  cout << "Brand Name: " << snack.brand << endl
       << "Weight: " << snack.weight << endl
       << "Calories: " << snack.calorie << endl;
};