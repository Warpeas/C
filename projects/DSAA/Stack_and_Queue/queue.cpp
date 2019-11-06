#include "iostream"
using namespace std;

class arrayQueue {
private:
  int *data;
  int max_size;
  int front;
  int rear;

  void initarrayQueue() {
    data = new int(max_size);
    front = 0;
    rear = 0;
  }

public:
  arrayQueue() {
    max_size = 0;
    initarrayQueue();
  }
  arrayQueue(int max) {
    max_size = max;
    initarrayQueue();
  }
  int isFull() { return ((rear+1)%max_size == front) ? 1 : 0; }
  int isEmpty() { return (rear - front == 0) ? 1 : 0; }
  void enQueue(int item) {
    if (!isFull()) {
      data[rear] = item;
      rear=(rear+1)%max_size;
    }
  }
  void deQueue() {
    if (!isEmpty()) {
      front = (front+1)%max_size;
    }
  }
  int getFront() {
    if (isEmpty()) {
      return NULL;
    }
    return data[front];
  }
  void clear() {
    front = 0;
    rear = 0;
  }
  int size() { return (rear - front+max_size)%max_size; }
};