#include <iostream>
#include <queue>
#include <string>

using namespace std;
class man;
class woman;
int n;

class man {
  public:
  queue<woman> lovers;
  woman *matched;
  string name;

public:
  man(){};
    man(string name)
    {
        this->name = name;
        matched = nullptr;
  }
  friend int invite(const man &m);
};
class woman {
  public:
  string name;
  int level;
  man *lovers[];

public:
  friend int invite(const man &m);
};

int invite(man &m) {
  int a = 0;
  for(;a < n;a++){
  if(m.lovers.front().lovers[a]->name == m.name){
    break;
  }
  else if(a > m.lovers.front().level){
    m.lovers.pop();
    a = 0;
  }
  }
  return 0;
}