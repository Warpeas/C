#include "utf8.h"
#include <string>

看不到
Arvin:
[歪头]
我要把所有都删掉

class UTF8string {
private:
  std::string str;

public:
  int length();
  int bytes();
  int find(std::string substr);
  UTF8string &replace(UTF8string to_remove, UTF8string replacement);
};
