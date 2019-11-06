#include "iostream"
using namespace std;

struct ListNode {
  long index, m;
  ListNode *next;
  ListNode *pre;
};

void DeleteNode(ListNode *node) {
  ListNode *p;
  p = node;
  p->next->pre = p->pre;
  p->pre->next = p->next;
  delete p;
}

int main() {
  int T;
  long n, m;
  ListNode *list, *head;

  cin >> T;
  for (int i = 0; i < T; i++) {
    cin >> n >> m;
    head = new ListNode();
    list = head;
    for (int i = 1; i <= n; i++) {
      list->index = i;
      cin >> list->m;
      list->next = new ListNode();
      list->next->pre = list;
      list = list->next;
    }
    list = list->pre;
    // list->next = head;
    // head->pre = list;
    // head = head->next;
    head->pre = list;
    list->next = head;
    list = head;
    while (n > 1) {
      m = m % n;
      if (m == 0) {
        list = list->pre;
      } else {
        if (n - m > m) {
          for (int i = 1; i < m; i++) {
            list = list->next;
          }
        } else {
          for (int i = 0; i <= n - m; i++) {
            list = list->pre;
          }
        }
      }
      m = list->m;
      list = list->next;
      DeleteNode(list->pre);
      // DeleteNode(list);
      n--;
    }
    cout << list->index << endl;
    // cout << JosephusPreblem(list, n, m)->index << endl;
  }
}