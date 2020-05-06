#include "iostream"
using namespace std;

struct ListNode {
  int id;
  ListNode *next;
  ListNode *pre;
};

// void InsertNote(ListNode *node, ListNode *InsertNode) {
//   ListNode *p;
//   p = node;
//   ListNode *i;
//   i = InsertNode;
//   // for(int i=0;i<n-1;i++)
//   // {
//   //     p=p->next;
//   // }
//   //   i = (ListNode *)malloc(sizeof(ListNode));
//   i->next = p->next;
//   p->next->pre = i;
//   p->next = i;
//   i->pre = p;
// }

int main() {
  int T, N, M;
  int x1, y1, x2, y2;
  cin >> T;
  while (T > 0) {
    T--;
    cin >> N >> M;
    ListNode *head;
    head = new ListNode();
    ListNode *tail = new ListNode();
    ListNode *tmp;
    tmp = head;
    ListNode *id[N];
    for (int i = 0; i < N; i++) {
      tmp->next = new ListNode();
      tmp->next->pre = tmp;
      tmp = tmp->next;
      cin >> tmp->id;
      id[tmp->id]=tmp;
    }
    // tmp = tmp->pre;
    tail->pre = tmp;
    tmp->next = tail;
    for (int i = 0; i < M; i++) {
      cin >> x1 >> y1 >> x2 >> y2;
      ListNode *ptr1, *ptr2, *ptr3, *ptr4, *pre1, *pre2, *next1, *next2;
      ptr1 = id[x1];
      ptr2 = id[y1];
      ptr3 = id[x2];
      ptr4 = id[y2];
      pre1 = ptr1->pre;
      pre2 = ptr3->pre;
      next1 = ptr2->next;
      next2 = ptr4->next;

      if (next1 == ptr3) {
        ptr3->pre = pre1;
        pre1->next = ptr3;

        ptr2->next = ptr4->next;
        next2->pre = ptr2;

        ptr1->pre = ptr4;
        ptr4->next = ptr1;
      } else {
        ptr3->pre = pre1;
        pre1->next = ptr3;

        ptr1->pre = pre2;
        pre2->next = ptr1;

        ptr2->next = next2;
        next2->pre = ptr2;

        ptr4->next = next1;
        next1->pre = ptr4;
      }
    }
    tmp = head->next;
    for (int i = 0; i < N; i++) {
      cout << tmp->id << " ";
      tmp = tmp->next;
    }
    cout<<endl;
  }
}