#include "iostream"
using namespace std;

struct ListNode {
  long value;
  ListNode *next;
  ListNode *pre;
};

void merge(ListNode *A, ListNode *L, int nl, ListNode *R, int nr) {
  int n = nl + nr;
  int i = 0, j = 0;
  ListNode *a, *l, *r;
  a = A->next;
  l = L->next;
  r = R->next;
  for (int k = 0; k < n; k++) {
    if (i < nl && (j == nr || l->value <= r->value)) {
      a->value = l->value;
      l = l->next;
    } else {
      a->value = r->value;
      r = r->next;
    }
    a = a->next;
  }
}

void mergesort(ListNode *A, int n) {
  if (n > 1) {
    int p = n / 2;
    ListNode *a;
    a = A;
    ListNode *B;
    ListNode *b;
    B = new ListNode();
    b = B;
    for (int i = 0; i < p; i++) {
      b->next = new ListNode();
      b->next->pre = b;
      b = b->next;
      b->value = a->value;
      a = a->next;
    }
    b = b->pre;
    b->next = nullptr;
    ListNode *C;
    ListNode *c;
    C = new ListNode();
    c = C;
    for (int i = p; i < n; i++) {
      c->next = new ListNode();
      c->next->pre = c;
      c = c->next;
      c->value = a->value;
      a = a->next;
    }
    mergesort(B, p);
    mergesort(C, n - p);
    merge(A, B, p, C, n - p);
  }
}

int main() {
  long n, v1, v2;
  ListNode *head = new ListNode();
  ListNode *l;
  l = head;
  cin >> n;
  for (int i = 0; i < n; i++) {
    l->next = new ListNode();
    l->next->pre = l;
    l = l->next;
    cin >> l->value;
  }
  l = head->next;

  mergesort(l, n);
  l = head->next;
  for (int i = 0; i < n - 1; i++) {
    v1 = abs(l->pre->value - l->value);
    v2 = abs(l->value - l->next->value);
    if (v1 < v2) {
      cout << v1 << " ";
    } else {
      cout << v2 << " ";
    }
  }
  cout << endl;
}