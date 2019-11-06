#include "iostream"
using namespace std;

int Fill_array(double arr[], int size) {
  double d;
  for (int i = 1; i <= size; i++) {
    cout << "Enter value #" << i << ": ";
    // if(cin >> d){
    //   arr[i - 1] = d;
    // }else {
    //   return i - 1;
    // }
    cin >> arr[i - 1];
    if(getchar()=='\n'){
      continue;
    }else {
      return i - 1;
    }
  }
  return size;
}

void Show_array(double *arr, int size) {
  for (int i = 0; i < size; i++) {
    cout << arr[i] << " ";
  }
  cout << endl;
}

void Reverse_array(double *arr, int size) {
  if (size >= 1) {
    double temp;
    temp = arr[0];
    arr[0] = arr[size - 1];
    arr[size - 1] = temp;
    arr++;
    Reverse_array(arr, size-2);
  }
}

int main() {
  int size;
  cout << "Enter the size of the array:";
  cin >> size;
  double arr[size];
  double *a;
  a = arr;
  size = Fill_array(arr, size);
  cout << "The output is as follows:" << endl;
  Show_array(arr, size);
  Reverse_array(arr, size);
  Show_array(arr, size);
  a++;
  Reverse_array(a, size - 2);
  Show_array(arr, size);
}