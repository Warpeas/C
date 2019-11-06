#include "iostream"
#include "malloc.h"
using namespace std;

struct ListNode {
  double value;
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
  p->next->pre = p->pre;
  p->pre->next = p->next;
  delete p;
}

void DeleteNode(ListNode *head, int n) {
  ListNode *p;
  p = head;
  for (int i = 0; i < n; i++) {
    p = p->next;
  }
  p->next->pre = p->pre;
  p->pre->next = p->next;
  delete p;
}

void InsertNote(ListNode *node, ListNode *InsertNode) {
  ListNode *p;
  p = node;
  ListNode *i;
  i = InsertNode;
  // for(int i=0;i<n-1;i++)
  // {
  //     p=p->next;
  // }
//   i = (ListNode *)malloc(sizeof(ListNode));
  i->next = p->next;
  p->next->pre = i;
  p->next = i;
  i->pre = p;
}

void InsertNote(ListNode *head, double value, int n) {
  ListNode *p;
  p = head;
  for (int i = 0; i < n - 1; i++) {
    p = p->next;
  }
  ListNode *insertNode;
  insertNode = (ListNode *)malloc(sizeof(ListNode));
  insertNode->value = value;
  insertNode->next = p->next;
  p->next->pre = insertNode;
  p->next = insertNode;
  insertNode->pre = p;
}

ListNode *getNode(ListNode *head, int n) {
  ListNode *p;
  p = head;
  for(int i=0;i<n;i++)
  {
      p=p->next;
  }
  return p;
}
