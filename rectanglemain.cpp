#include "rectangle"

using namespace std;

int main(){
    rectangle *r1 = new rectangle(4,40);
    rectangle *r2 = new rectangle(3.5,35.9);
    cout << "Rectangle 1" << endl
         << "----------------" << endl;
    r1->display();
    cout << "Rectangle 1" << endl
         << "----------------" << endl;
    r2->display();
}