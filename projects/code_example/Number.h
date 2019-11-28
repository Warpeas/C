#include <iostream>

class Number{
    public:
    int &operator ++(int);
    int &operator ++();
    
    private:
    int num;
}