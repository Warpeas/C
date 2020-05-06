#include <iostream>
#include <map>
#include <queue>
#include <string>
#include <hash_map>

using namespace std;
class man;
class woman;
int n;
queue<int> singles;      // Including all singles' id
map<int, man> men; // First key is the man's id, the second value corespond to
                   // the object man
map<int, woman> women; // First key is the woman's id, the second value
                       // corespond to the object woman


class person {
  string name;
  int id;

public:
  person(string name, int id) {
    this->name = name;
    this->id = id;
  }
  string getName() { return name; }
  int getId() { return id; }
};

class man : public person {
private:
  queue<int> lovers;
  int matched;

public:
  man(string name, int id) : person(name, id) { matched = -1; }
  int loversEmpty() { return (int)lovers.empty(); }
  int firstLover() { return lovers.front(); }
  int nextLover() {
    lovers.pop();
    if (lovers.empty()) {
      return 1;
    }
    return 0;
  }
  int getMatched() const { return matched; }
  void youAreAGoodMan() {
    lovers.pop();
    matched = -1;
  }
  void changeMatched(int matched) { this->matched = matched; }
  friend int invite(const man &m);
};

class woman : public person {
public:
  int matched;
  map<int, int> preference; // The first int is the id of man and the second is
                            // the preference degree

public:
  woman(string name, int id) : person(name, id) { matched = n; }
  friend int invite(const man &m);
};

int invite(man &m) {
  while (!m.loversEmpty()) {
    int m_preference =
        women.find(m.firstLover())->second.preference.find(m.getId())->second;
    if (m_preference < women.find(m.firstLover())->second.matched) {
      women.find(m.firstLover())->second.matched = m_preference;
      m.changeMatched(women.find(m.firstLover())->second.getId());
      break;
    } else {
      m.nextLover();
    }
  }
  return 0;
}

int main() {
  cin >> n;
  string in[n][n];
  for (int i = 0; i < 2 * n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> in[i][j];
    }
  }
  for (int i = 0; i < n; i++) {
    man *tmpM = new man(in[i][0], i);
    woman *tmpW = new woman(in[n + i][0], i + n);
    men.insert(pair<int, man>(i, *tmpM));
    women.insert(pair<int, woman>(i + n, *tmpW));
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      
    }
  }
  return 0;
}