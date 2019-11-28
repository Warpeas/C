//
// Created by lenovo on 2019/11/27.
//
// Complex.cpp -- implementing Complex methods
#include "Complex.h"
#include <iostream>

std::ostream &operator<<(std::ostream &os, const Complex &other) {
  os << other.real << "+" << other.imag << "i";
  return os;
}

void operator>>(std::istream &is, Complex &other) {
  double result_imag, result_real;
  std::cout << "real: ";
  is >> result_real;
  std::cout << "imaginary: ";
  is >> result_imag;
  other = Complex(result_real, result_imag);
}

Complex::Complex() : real(0), imag(0) {}

Complex::Complex(double re, double im) : real(re), imag(im) {}

Complex operator+(double r, const Complex &other) {
  double result_real = r + other.real;
  double result_imaginary = other.imag;
  return Complex(result_real, result_imaginary);
}

Complex Complex::operator+(const Complex &other) const {
  double result_real = real + other.real;
  double result_imaginary = imag + other.imag;
  return Complex(result_real, result_imaginary);
}

Complex Complex::operator+(double real) const {
  double result_real = real + this->real;
  double result_imaginary = imag;
  return Complex(result_real, result_imaginary);
}

Complex operator-(double r, const Complex &other) {
  double result_real = r - other.real;
  double result_imaginary = -other.imag;
  return Complex(result_real, result_imaginary);
}

Complex Complex::operator-(const Complex &other) const {
  double result_real = real - other.real;
  double result_imaginary = imag - other.imag;
  return Complex(result_real, result_imaginary);
}

Complex Complex::operator-(double real) const {
  double result_real = this->real - real;
  double result_imaginary = -imag;
  return Complex(result_real, result_imaginary);
}

Complex operator*(double r, const Complex &other) {
  double result_real = r * other.real;
  double result_imaginary = r * other.imag;
  return Complex(result_real, result_imaginary);
}

Complex Complex::operator*(const Complex &other) const {
  double result_real = real * other.real - imag * other.imag;
  double result_imaginary = real * other.imag + other.real * imag;
  return Complex(result_real, result_imaginary);
}

Complex Complex::operator*(double real) const {
  double result_real = real * this->real;
  double result_imaginary = real * imag;
  return Complex(result_real, result_imaginary);
}

Complex Complex::operator~() const {
  double result_imaginary = -imag;
  return Complex(real, result_imaginary);
}

bool operator==(double r, const Complex &other) {
  if (other.imag == 0) {
    return r == other.real;
  } else {
    return false;
  }
}

bool Complex::operator==(const Complex &other) const {
  return real == other.real && imag == other.imag;
}

bool Complex::operator==(double real) const {
  if (imag == 0) {
    return this->real == real;
  } else {
    return false;
  }
}

bool operator!=(double r, const Complex &other) {
  if (other.imag == 0) {
    return !(r == other.real);
  } else {
    return true;
  }
}

bool Complex::operator!=(const Complex &other) const {
  return !(real == other.real && imag == other.imag);
}

bool Complex::operator!=(double real) const {
  if (imag == 0) {
    return !(this->real == real);
  } else {
    return true;
  }
}

void Complex::Show() const {
  std::cout << real << "+" << imag << "i" << std::endl;
}
