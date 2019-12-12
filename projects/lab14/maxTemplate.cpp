/****************************************************
 *
 * FileName: maxTemplate.cpp
 * Purpose: Demonstrate the use of function templates
 *
 ********************************************************/
#include <iostream>
#include <iomanip>
#include <string>
using namespace std;
// Make a template out of the prototype
template <typename T> T Max(T, T);
class imcomparable {
private:
  int value;

public:
  imcomparable(int value) { this->value = value; }
  static int Max(imcomparable one, imcomparable two) {
    if (one.value >= two.value) {
      return one.value;
    }
    return two.value;
  }
};
int main() {
  int i_one = 3, i_two = 5;
  cout << "The max of " << i_one << " and " << i_two << " is "
       << Max(i_one, i_two) << endl;
  // Test your template on float and string types
  double d_one = 3.6, d_two = 5.7;
  cout << "The max of " << setiosflags(ios::left) <<setw(5) << d_one << " and " << d_two << " is "
       << Max(d_one, d_two) << endl;
  string s_one = "donkey", s_two = "apple";
  cout << "The max of " << s_one << " and " << s_two << " is "
       << Max(s_one, s_two) << endl;
  imcomparable *im1 = new imcomparable(0);
  imcomparable *im2 = new imcomparable(1);
  cout << "The max of " << im1 << " and " << im2 << " is " << imcomparable::Max(*im1, *im2)
       << endl;
  return 0;
}
// Make a template out of this function. Don't forget the return type.
template <typename T> T Max(T one, T two) {
  // T biggest;
  // if (one < two) {
  //   biggest = two;
  // } else {
  //   biggest = one;
  // }
  // return biggest;
  if (one <= two) {
    return two;
  } else if (one > two) {
    return one;
  } else {
    return NULL;
  }
}