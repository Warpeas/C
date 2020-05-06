#include "CCube.hpp"

CCube::CCube(double length, double width, double height) {
  this->length = length;
  this->width = width;
  this->height = height;
}
CCube::CCube(const CCube &other) {
  length = other.length;
  width = other.width;
  height = other.height;
}
double CCube::GetArea() const {
  return 2 * (length * width + length * height + width * height);
}
double CCube::GetVolume() const { return length * width * height; }
void CCube::Show() const {
  Formatting flag = SetFormat();
  std::cout << "length: " << length << std::endl
            << "width: " << width << std::endl
            << "height: " << height << std::endl
            << "area: " << this->GetArea() << std::endl
            << "volume: " << this->GetVolume() << std::endl;
  Restore(flag);
}