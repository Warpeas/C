#include "iostream"
using namespace std;
template <typename T> T max5(T *arr) {
  T max = arr[0];
  cout << arr <<endl;
  for (int i = 1; i < 5; i++) {
    if (arr[i] > max) {
      max = arr[i];
    }
  }
  return max;
}
int main() {
  int arr1[] = {1, 2, 3, 4, 5};
  double arr2[] = {1.1, 2.0, 3.0, 4.0, 5.5};
    cout << "Max int = " << max5(arr1) << endl
         << "Max double = " << max5(arr2) << endl;
}