#include "iostream"
using namespace std;
struct ListNode {
  char c;
  ListNode *next;
  ListNode *pre;
};
void DeleteNode(ListNode *node) {
  ListNode *p;
  p = node;
  // for(int i=0;i<n;i++)
  // {
  //     p=p->next;
  // }
  //   p->next->pre = p->pre;
  p->pre->next = p->next;
  delete p;
}
long calculator(int n, long mo) {
  int num;
  ListNode *head = new ListNode();
  ListNode *l;
  l = head;
  long m = mo;
  for (; m != 0;) {
    l->next = new ListNode();
    l->next->pre = l;
    l->c = m % 10;
    m /= 10;
    l = l->next;
  }
  l = l->pre;
  //   l->next = nullptr;
  DeleteNode(l->next);
  for (int i = n - 1; i >= 0; i--) {
    num = l->c;
    for (int j = 0; j < i; j++) {
      num *= 10;
    }
    m += num;
    l = l->pre;
  }
  m *= m;

  for (; m != 0;) {
    l->next = new ListNode();
    l->next->pre = l;
    l->c = m % 10;
    m /= 10;
    l = l->next;
  }
  l = l->pre;
  //   l->next = nullptr;
  DeleteNode(l->next);
  for (int i = n - 1; i >= 0; i--) {
    num = l->c;
    for (int j = 0; j < i; j++) {
      num *= 10;
    }
    m += num;
    l = l->pre;
  }
  delete head;
  if (m == mo) {
    return m;
  }
  return calculator(n, m);
}
int main() {
  int t, n;
  long m;
  cin >> t;
  for (int i = 0; i < t; i++) {
    cin >> n >> m;
    cout << calculator(n, m) << endl;
  }
}