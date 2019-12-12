#include "UTF8string.hpp"

unsigned char *string_to_cstring(const std::string &str);
std::string &replace_all_distinct(std::string &str,
                                  const std::string &old_value,
                                  const std::string &new_value);
UTF8string::UTF8string() : str("") {}
UTF8string::UTF8string(std::string str) : str(str) {}

//return numbers of characters of the string
int UTF8string::length() {
  unsigned char *str = string_to_cstring(*this);
  return utf8_charlen(str);
}

//return bytes length of the sting
int UTF8string::bytes() {
  int bytecnt = 0;
  int len;
  unsigned char *str = string_to_cstring(*this);

  while (*str != '\0') {
      len = isutf8(str);
      if (len == 0)
      {
          return -1; // Invalid UTF-8
    }
    bytecnt += len;
    _utf8_incr(str);
  }
  return bytecnt;
}

//find the start position of the substring and return
int UTF8string::find(std::string substr) {
  int cp = -1;
  unsigned char *str = string_to_cstring(*this);
  // std::cout << "good";
  unsigned char *sub = string_to_cstring(substr);
  unsigned char *p = utf8_search(str, sub);
  if (p != NULL) {
    while (str != p) {
      cp++;
      // Beware that the macro increments p
      _utf8_incr(str);
    }
    cp++;
  }
  return cp;
}

//replace the correspond substring and return the string
int UTF8string::replace(std::string to_remove, std::string replacement) {
  unsigned char *str = string_to_cstring(*this);
  unsigned char *sub = string_to_cstring(to_remove);
  unsigned char *p = utf8_search(str, sub);
  if (p != NULL) {
    replace_all_distinct(this->str, to_remove, replacement);
    return 1;
  } else {
    return 0;
  }
}
std::ostream &operator<<(std::ostream &os, const UTF8string &str) {
  os << str.str;
  return os;
}

//append another UTF8 string to current string
void UTF8string::operator+=(UTF8string str) { this->str.append(str.str); }
UTF8string UTF8string::operator+(const UTF8string other) {
  UTF8string str = UTF8string(this->str);
  str.str.append(other.str);
  return str;
}

//multiple the string by n times
UTF8string UTF8string::operator*(int times) {
  std::string str = "";
  for (int i = 0; i < times; i++) {
    str.append(this->str);
  }
  return str;
}

//reverse the string
UTF8string UTF8string::operator!() {
  UTF8string restr = UTF8string("");
  unsigned char *s = string_to_cstring(*this);
  int bytelen = this->bytes();
  int len;
  for(int i = 0;i < bytelen;){
    len = isutf8((const unsigned char *)&(s[i]));
    restr.str = this->str.substr(i, len) + restr.str;
    i += len;
  }
  return restr;
}

unsigned char *string_to_cstring(const UTF8string &str) {
  return (unsigned char *)str.str.c_str();
}

unsigned char *string_to_cstring(const std::string &str) {
  return (unsigned char *)str.c_str();
}

// From https://www.cnblogs.com/lpxblog/p/9855791.html
//function replace
std::string &replace_all_distinct(std::string &str,
                                  const std::string &old_value,
                                  const std::string &new_value) {
  for (std::string::size_type pos(0); pos != std::string::npos;
       pos += new_value.length()) {
    if ((pos = str.find(old_value, pos)) != std::string::npos) {
      str.replace(pos, old_value.length(), new_value);
    } else {
      break;
    }
  }
  return str;
}