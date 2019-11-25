#include <cstdio>
#include <iostream>
using namespace std;
const int maxN = (int)1e5 + 7;
int m, k, a[maxN], q;
namespace BBST {
struct Tree {
  int key, l, r, size;
} tree[maxN];
int root, num;
void RR(int &x) {
  int y = tree[x].l;
  tree[x].l = tree[y].r;
  tree[y].r = x;
  tree[y].size = tree[x].size;
  tree[x].size = tree[tree[x].l].size + tree[tree[x].r].size + 1;
  x = y;
}
void LR(int &x) {
  int y = tree[x].r;
  tree[x].r = tree[y].l;
  tree[y].l = x;
  tree[y].size = tree[x].size;
  tree[x].size = tree[tree[x].l].size + tree[tree[x].r].size + 1;
  x = y;
}
void maintain(int &x, bool flag) {
  if (flag == false) {
    if (tree[tree[tree[x].l].l].size > tree[tree[x].r].size) {
      RR(x);
    } else if (tree[tree[tree[x].l].r].size > tree[tree[x].r].size) {
      LR(tree[x].l);
      RR(x);
    } else
      return;
  } else {
    if (tree[tree[tree[x].r].r].size > tree[tree[x].l].size) {
      LR(x);
    } else if (tree[tree[tree[x].r].l].size > tree[tree[x].l].size) {
      RR(tree[x].r);
      LR(x);
    } else
      return;
  }
  maintain(tree[x].l, false);
  maintain(tree[x].r, true);
}
void add(int &x, int key) {
  if (x == 0) {
    x = ++num;
    tree[x] = {key, 0, 0, 1};
  } else {
    tree[x].size++;
    if (key > tree[x].key) {
      add(tree[x].r, key);
    } else {
      add(tree[x].l, key);
    }
    maintain(x, key > tree[x].key);
  }
}
void remove(int &x, int key) {
  tree[x].size--;
  if (key > tree[x].key) {
    remove(tree[x].r, key);
  } else if (key < tree[x].key) {
    remove(tree[x].l, key);
  } else {
    if (!tree[x].l && !tree[x].r) {
      x = 0;
    } else if (!tree[x].l && tree[x].r) {
      x = tree[x].r;
    } else if (!tree[x].r && tree[x].l) {
      x = tree[x].l;
    } else {
      int temp = tree[x].r;
      while (tree[temp].l) {
        temp = tree[temp].l;
      }
      tree[x].key = tree[temp].key;
      remove(tree[x].r, tree[temp].key);
    }
  }
}
int topk(int &x, int k) {
  int cnt = tree[tree[x].l].size + 1;
  if (cnt == k) {
    return tree[x].key;
  } else if (cnt > k) {
    return topk(tree[x].l, k);
  } else {
    return topk(tree[x].r, k - cnt);
  }
}

} // namespace BBST
int main() {}
/*
6 3
201 91 29 13 11 -5
3 1 2 1
*/