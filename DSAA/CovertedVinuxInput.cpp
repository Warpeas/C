#include "iostream"
#include "malloc.h"
using namespace std;

struct ListNode {
  char c;
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

void InsertNote(ListNode *node, ListNode *InsertNode) {
  ListNode *p;
  p = node;
  ListNode *i;
  i = InsertNode;
  i->next = p->next;
  p->next->pre = i;
  p->next = i;
  i->pre = p;
}

int main() {
  int T;
  int n;

  cin >> T;
  while (T > 0) {
    T--;
    ListNode *head = new ListNode();
    ListNode *tmp;
    ListNode *node;
    tmp = head;
    tmp->next = new ListNode();
    tmp->next->pre = tmp;
    tmp = tmp->next;
    tmp->c = 'e';

    cin >> n;
    char in[n];
    for(int i = 0;i < n;i++){
    cin >> in[i];
    }
    for (int i = 0; i < n; i++) {
      switch (in[i]) {
      case 'r':
        if (tmp->c == 'e') {
          tmp->next = new ListNode();
          tmp->next->pre = tmp;
          tmp->next->c = 'e';
        }
        if(i == n-1){
          break;
        }
        tmp->c = in[++i];
        break;
      case 'I':
        tmp = head->next;
        break;
      case 'H':
        if (tmp->pre != nullptr && tmp->pre != head) {
          tmp = tmp->pre;
        }
        break;
      case 'L':
        if (tmp->c != 'e') {
          tmp = tmp->next;
        }
        break;
      case 'x':
        if (tmp->c != 'e') {
          tmp = tmp->next;
          DeleteNode(tmp->pre);
        }
        break;
      default:
          node = new ListNode();
          node->c= in[i];
          InsertNote(tmp->pre, node);
      }
    }
    tmp = head->next;
    while (tmp != nullptr&&tmp->c!='e') {
      cout << tmp->c;
      tmp = tmp->next;
    }
    cout << endl;
  }
  return 0;
}