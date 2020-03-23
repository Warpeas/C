#include <algorithm>
#include <fstream>
#include <iostream>

using namespace std;
class day {
  int index;
  long long value;
  int nxt_index;
  int pre_index;

public:
  day(){};
  day(int index, long value) {
    this->index = index;
    this->value = value;
    nxt_index = -1;
    pre_index = -1;
  }
  long long getValue() { return value; }
  int getIndex() { return index; }
  void setNxt(int i) { nxt_index = i; }
  void setPre(int i) { pre_index = i; }
  int getNxt() { return nxt_index; }
  int getPre() { return pre_index; }
  friend bool operator<(const day d1, const day d2) {
    if (d1.value < d2.value && d1.index < d2.index) {
      return true;
    }
    return false;
  }
  // friend bool operator>(const day d1, const day d2) {
  //   if (d1.value > d2.value) {
  //     return true;
  //   }
  //   return false;
  // }
  friend ostream &operator<<(ostream &os, const day d) {
    os << d.value << " ";
    return os;
  }
};

class day_s {
  int index;
  long long value;

public:
  day_s(){};
  day_s(int index, long value) {
    this->index = index;
    this->value = value;
  }
  long long getValue() { return value; }
  int getIndex() { return index; }
  friend bool operator<(const day_s d1, const day_s d2) {
    if (d1.value < d2.value) {
      return true;
    }
    return false;
  }
};

static int n;
static day da[2000000] = {};

void construct() {
  iostream::sync_with_stdio(false);
  cin.tie(0);
  //  ifstream fin;
  //  fin.open("input.txt");
  //  cin.rdbuf(fin.rdbuf());
  cin >> n;
  long tmp;
  day_s *d = new day_s[n]();
  for (int i = 0; i < n; i++) {
    cin >> tmp;
    da[i] = *new day(i, tmp);
    d[i] = *new day_s(i, tmp);
  }
  sort(d, d + n);
  // for (int i = 0; i < n; i++) {
  //   cout << da[i];
  // }
  // cout << endl;
  for (int i = 0; i < n; i++) {
    da[d[i].getIndex()].setPre(i - 1 > -1 ? d[i - 1].getIndex() : -1);
    da[d[i].getIndex()].setNxt(i + 1 < n ? d[i + 1].getIndex() : -1);
  }
  // for (int i = 0; i < n; i++) {
  //   cout << i << " " << da[i] << " " << da[i].getPre() << " " <<
  //   da[i].getNxt()
  //        << endl;
  // }
  delete[] d;
}
long long calculate() {
  long long result = da[0].getValue();
  long long tmp1, tmp2;
  int p;
  for (int i = n - 1; i > 0; i--) {
    p = da[i].getNxt();
    if (p != -1) {
      tmp1 = abs(da[i].getValue() - da[p].getValue());
      da[p].setPre(da[i].getPre());
    } else {
      tmp1 = 2147483648;
    }
    p = da[i].getPre();
    if (p != -1) {
      tmp2 = abs(da[i].getValue() - da[p].getValue());
      da[p].setNxt(da[i].getNxt());
    } else {
      tmp2 = 2147483648;
    }
    if (tmp1 > tmp2) {
      result += tmp2;
    } else {
      result += tmp1;
    }
  }
  return result;
}

int main() {
  // iostream::sync_with_stdio(0);
  // cin.tie(0);
   cout.tie(0);
  construct();
  // for (int i = 0; i < n; i++) {
  //   cout << i << " " << da[i] << " " << da[i].getNxt() << " " <<
  //   da[i].getPre()
  //        << endl;
  // }
  cout << calculate() << "\n";
  return 0;
}