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
  int isFull() { return ((rear + 1) % max_size == front) ? 1 : 0; }
  int isEmpty() { return (rear == front) ? 1 : 0; }
  void enQueue(int item) {
    if (!isFull()) {
      data[rear] = item;
      rear = (rear + 1) % max_size;
    }
  }
  void deQueue() {
    if (!isEmpty()) {
      front++;
    }
  }
  int getFront() { return data[front]; }
};

int main() {
  long n;
  char o;
  int x;
  cin >> n;
  arrayQueue q = *new arrayQueue(n + 1);
  for (int i = 0; i < n; i++) {
    cin >> o;
    if (o == 'E') {
      cin >> x;
      q.enQueue(x);
    } else if (o == 'D') {
      q.deQueue();
    } else if (o == 'A') {
      if (!q.isEmpty()) {
        cout << q.getFront() << endl;
      }
    }
  }
  while (!q.isEmpty()) {
    cout << q.getFront() << " ";
    q.deQueue();
  }
  cout << endl;
  return 0;
}
