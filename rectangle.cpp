#include "rectangle"

rectangle::rectangle() {
  width = 1;
  height = 1;
}
rectangle::rectangle(double width, double height) {
  this->width = width;
  this->height = height;
}
int rectangle::getArea() { return width * height; }
int rectangle::getPerimeter() { return 2 * (width + height); }
void rectangle::display() {
  std::cout << "Width:           " << width << std::endl
            << "Height:          " << height << std::endl
            << "Area:            " << getArea() <<std::endl
            << "Perimeter:       " << getPerimeter() << std::endl;
}
