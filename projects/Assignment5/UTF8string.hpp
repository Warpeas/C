#ifndef UTF8STRING_HPP
#define UTF8STRING_HPP

#include "utf8.h"
#include <iostream>
#include <string>

class UTF8string {
private:
  std::string str;

public:
  UTF8string();
  UTF8string(std::string str);
  int length();
  int bytes();
  int find(std::string substr);
  int replace(std::string to_remove, std::string replacement);
  friend std::ostream &operator<<(std::ostream &os, const UTF8string &str);
  friend unsigned char *string_to_cstring(const UTF8string &str);
  void operator+=(UTF8string str);
  UTF8string operator+(UTF8string other);
  UTF8string operator*(int times);
  UTF8string operator!();
};

#endif
