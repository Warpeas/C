#include "box.hpp"
using namespace std;

int main() {
  Box *b1 = new Box();
  Box b2 = Box(1, 5, 10);
  cout << "b1: " << endl;
  cout << b1 << endl;
  delete b1;
  cout << "b2: " << endl;
  cout << b2.getLength() << " ";
  cout << b2.getBreadth() << " ";
  cout << b2.getHeight() << endl;
  cout << b2 << endl;
  Box b3 = b2;
  cout << "b3: " << endl;
  cout << b3 << endl;
}