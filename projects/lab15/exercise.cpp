#include <iostream>
using namespace std;
class OutOfRangeException : public exception {
    public:
  const char *what() const throw() { return " which out of range(0-100)"; }
};
double calculateAverage(double n1, double n2, double n3, double n4) {
  if (0 > n1 || n1 > 100) {
    cerr << "The parameter1 is " << n1;
    throw OutOfRangeException();
  }
  if (0 > n2 || n2 > 100) {
    cerr << "The parameter2 is " << n2;
    throw OutOfRangeException();
  }
  if (0 > n3 || n3 > 100) {
    cerr << "The parameter3 is " << n3;
    throw OutOfRangeException();
  }
  if (0 > n4 || n4 > 100) {
    cerr << "The parameter4 is " << n4;
    throw OutOfRangeException();
  }
  return (n1 + n2 + n3 + n4) / 4.0;
}
int main() {
  cout << "Please enter marks for 4 courses: ";
  double n1, n2, n3, n4;
  cin >> n1 >> n2 >> n3 >> n4;
  try {
      double result = calculateAverage(n1, n2, n3, n4);
    cout <<"The average of the four courses is " << result << endl;
  } catch (OutOfRangeException &e) {
    cerr << e.what() << endl;
  }
}