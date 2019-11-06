#include "iostream"
using namespace std;

struct ListNode {
  int coefficient;
  int exponent;
  ListNode *next;
};

void InsertNode(ListNode *node, int e, int c) {
  ListNode *i = new ListNode();
  i->exponent = e;
  i->coefficient = c;
  i->next = node->next;
  node->next = i;
}

void InsertNote(ListNode *node, ListNode *InsertNode) {
  ListNode *p;
  p = node;
  ListNode *i;
  i = InsertNode;
  i->next = p->next;
  p->next = i;
}

int main() {
  int T, n, c, e;
  long m, q, num;

  cin >> T;
  while (T > 0) {
    T--;
    ListNode *head1;
    head1 = new ListNode;
    ListNode *middle1;
    ListNode *tail1 = new ListNode();
    cin >> n;

    middle1 = head1;
    for (int i = 0; i < n; i++) {
      middle1->next = new ListNode();
      middle1 = middle1->next;
      cin >> middle1->coefficient >> middle1->exponent;
    }
    middle1->next = tail1;
    cin >> m;

    ListNode *p;

    p = head1;
    for (int i = 0; i < m; i++) {
      cin >> c >> e;
      if (p->next != tail1) {
        while (p->next->exponent <= e) {
          p = p->next;
          if (p->next == tail1) {
            break;
          }
        }
      }

      if (e == p->exponent) {
        p->coefficient += c;
      } else {
        InsertNode(p, e, c);
      }
    }
    p = head1->next;

    cin >> q;
    for (int i = 0; i < q; i++) {

      cin >> num;
      // if (p == nullptr) {
      //   cout << 0 << " ";
      //   continue;
      // }
      while (p!=tail1&&p->exponent < num) {
        p = p->next;
        // if (p == tail1) {
        //   break;
        // }
      }
      if (p == tail1) {
        cout << 0 << " ";
        continue;
      }
      if (p->exponent == num) {
        cout << p->coefficient << " ";
      } else if (p->exponent > num) {
        cout << 0 << " ";
      }
    }

    // while (head1 != nullptr) {
    //   p = head1;
    //   head1 = head1->next;
    //   delete p;
    // }

    cout << endl;
  }
}
