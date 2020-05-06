#include "CSphere.hpp"

const double pi = 3.14;
CSphere::CSphere(double radius) { this->radius = radius; }
CSphere::CSphere(const CSphere &other) { radius = other.radius; }
double CSphere::GetArea() const { return 4 * pi * radius * radius; }
double CSphere::GetVolume() const {
  return 4 * pi * radius * radius * radius / 3;
}
void CSphere::Show() const {
  Formatting flag = SetFormat();
  std::cout << "radius: " << radius << std::endl
            << "area: " << this->GetArea() << std::endl
            << "volume: " << this->GetVolume() << std::endl;
  Restore(flag);
}