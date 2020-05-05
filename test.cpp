// #include <iostream>

// class myVector {
//     int size;
//     double* array;
// public:
//     myVector(const myVector& rhs) {  // 复制构造函数
//         std::cout << "Copy Constructor\n";
//         size = rhs.size; 
//         array = new double[size];
//         for (int i=0; i<size; i++) { array[i] = rhs.array[i]; }
//     }

//     myVector(int n) {
//         size = n;
//         array = new double[n];
//     }
// };
// void foo(myVector v) {/* Do something */}
// myVector createMyVector();  // Creates a myVector

// int main() {
//     myVector reusable = createMyVector();
//     // 这里会调用 myVector 的复制构造函数，如果我们不希望 foo 乱动 reusable，这种情况下是 ok 的
//     foo(reusable); 
//     /* Do something else with reusable */

//     // 这里 createMyVector 会返回一个临时的右值，传参过程中会多余地被复制一次
//     // 虽然大部分情况下会被编译器优化掉
//     foo(createMyVector());
// }

#include <iostream>
using namespace std;
int main(){
    printf("%lld", 1e-6);
    return 0;
}