#include <iostream>
using namespace std;

// Array type stack
class arrayStack {
private:
  int *data;
  int max_size;
  int top;

  void initarrayStack() {
    data = new int(max_size);
    top = -1;
  }

public:
  arrayStack() {
    max_size = 10;
    initarrayStack();
  }
  arrayStack(int max) {
    max_size = max;
    initarrayStack();
  }

  int isEmpty() { return (top == -1) ? 1 : 0; }

  int isFull() { return (top >= max_size - 1) ? 1 : 0; }

  int push(int item) {
    if (isFull()) {
      return 0;
    } else
      data[++top] = item;
    return 1;
  }

  int pop() {
    if (isEmpty()) {
      return 0;
    } else
      top--;
    return 1;
  }
  int peek() {
    if (isEmpty()) {
      return 0;
    }
    return data[top];
  }

  void clear() { top = -1; }

  int size() { return top + 1; }
};

// Linked list type stack
struct ListNode {
  int data;
  ListNode *next;
  ListNode *pre;
};

class linkedStack {
private:
  int max_size;
  int t;
  ListNode *head;
  ListNode *top;

  void initlinkedStack() {
    head = new ListNode();
    top = head;
    t = -1;
  }

public:
  linkedStack() {
    max_size = -1;
    initlinkedStack();
  }
  linkedStack(int max) {
    max_size = max;
    initlinkedStack();
  }
  int isEmpty() { return (top == head) ? 1 : 0; }

  int isFull() { return (t == max_size - 1) ? 1 : 0; }

  int push(int item) {
    if (isFull()) {
      return 0;
    }
    top->next = new ListNode();
    top->next->pre = top;
    top = top->next;
    t++;
    return 1;
  }

  int pop() {
    if (isEmpty()) {
      return 0;
    }
    top = top->pre;
    delete top->next;
    top->next = nullptr;
    t--;
    return 1;
  }

  ListNode *peek() {
    if (isEmpty()) {
      return nullptr;
    }
    return top;
  }

  void clear() {
    while (top != head) {
      top = top->pre;
      delete top->next;
      top->next = nullptr;
    }
    t = -1;
  }

  int size() { return t; }
};