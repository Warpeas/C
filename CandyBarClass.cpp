#include "CandyBarClass"
#include "iostream"

using namespace std;

int CandyBar::setCandyBar() {
  cout << "Enter the brand name of a Candy bar: ";
  // string str;
  // getchar();
  getline(cin, this->brand);
  // this->brand = *str.c_str();
  // getline(cin, this->brand);
  if (this->brand.length() == 0) {
    cout << "empty name detected!" << endl;
    return 1;
  }
  cout << "Enter weight of the candy bar: ";
  cin >> this->weight;
  cout << "Enter calories (an integer value) in the candy bar: ";
  cin >> this->calorie;
  return 0;
}
void CandyBar::showCandyBar() {
  cout << "Brand Name: " << this->brand << endl
       << "Weight: " << this->weight << endl
       << "Calories: " << this->calorie << endl;
};
