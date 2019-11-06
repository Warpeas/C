#include "cstring"
#include "iostream"

using namespace std;

struct box {
  char maker[40];
  float height;
  float width;
  float length;
  float volume;
};

void display(box b) {
  cout << "Maker:  " << b.maker << endl
       << "Height: " << b.height << endl
       << "Width:  " << b.width << endl
       << "Length: " << b.length << endl
       << "Volume: " << b.volume << endl;
}

void setVolume(box *b) { b->volume = b->height * b->width * b->length; }

int main() {
  struct box b{"Jinan University",3.4,4.5,5.6};
  // strcpy(b.maker, "Jinan University");
  // b.height = 3.4;
  // b.width = 4.5;
  // b.length = 5.6;
  cout << "Before setting valume:" << endl;
  display(b);
  setVolume(&b);
  cout << "After setting valume:" << endl;
  display(b);
}