#include "CCube.hpp"
#include "CSphere.hpp"
#include <iostream>


using namespace std;

int main() {
  CCube a_cube = CCube(4.0, 5.0, 6.0);
  CSphere c_sphere = CSphere(7.9);
  CStereoShape *p = &a_cube;
  cout << "a_cube:" << endl;
  p->Show();
  CCube b_cube = a_cube;
  p = &a_cube;
  cout << "b_cube" << endl;
  p->Show();
  p = &c_sphere;
  cout << "c_sphere:" << endl;
  p->Show();
  cout << CStereoShape::GetNumOfObject() << endl;
}