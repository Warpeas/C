#include "CStereoShape.hpp"
using namespace std;
int CStereoShape::numberOfObject = 0;
Formatting CStereoShape::SetFormat() const {
  // set up ###.## format
  Formatting f;
  f.flag = cout.setf(ios_base::fixed, ios_base::floatfield);
  f.pr = cout.precision(2);
  return f;
}

void CStereoShape::Restore(Formatting &f) const {
  cout.setf(f.flag, ios_base::floatfield);
  cout.precision(f.pr);
}
double CStereoShape::GetArea() const {
  std::cout << "CStereoShape :: GetArea()" << std::endl;
  return 0.0;
}
double CStereoShape::GetVolume() const {
  std::cout << "CStereoShape :: Getvolume()" << std::endl;
  return 0.0;
}
void CStereoShape::Show() const {
  std::cout << "CStereoShape :: Show()" << std::endl;
}
