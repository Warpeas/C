//
// Created by lenovo on 2019/11/27.
//
// Complex.cpp -- implementing Complex methods
#include "Complex1.h"
#include <iostream>

Complex::Complex() : real(0), imag(0) {}
Complex::Complex(double re, double im) : real(re), imag(im) {}
Complex Complex::operator+(const Complex &other) const {
  double result_real = real + other.real;
  double result_imaginary = imag + other.imag;
  return Complex(result_real, result_imaginary);
}
void Complex::Show() { std::cout << real << "+" << imag << "i" << std::endl; }
