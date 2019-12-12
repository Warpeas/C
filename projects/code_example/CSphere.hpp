#ifndef CSphere_HPP
#define CSphere_HPP

#include "CStereoShape.hpp"

class CSphere : public CStereoShape {
public:
  CSphere();
  CSphere(double radius);
  CSphere(const CSphere &other);
  double GetArea() const;
  double GetVolume() const;
  void Show() const;

private:
  double radius;
};

#endif