#include "cstdio"
#include "cstring"
#include "fstream"
#include "iostream"
#include "sstream"
#include "utf8.h"

using namespace std;

const int array_Size = 300;
int arraySize = 0;

struct block {
  unsigned char name[50];
  int start;
  int end;
} block;

struct block blocks[array_Size];

int main() {
  std::ifstream inFile("Blocks.txt", std::ios::in);
  if (!inFile) {
    cout << "Warning! Filed" << endl << "System exit" << endl;
    return 1;
  }
  string line;
  for (int i = 0; i < array_Size; i++) {
    // string l;
    getline(inFile, line);
    // string_to_cstring(line, l);
    if ((line[0] == '#' && line == "# EOF") || line[0] == '\n') {
      i--;
      continue;
    } else if (line == "# EOF\n") {
      arraySize = i;
      break;
    } else {
      sscanf(line, "%x..%x; %s", blocks[i].start, blocks[i].end,
             blocks[i].name);
    }
  }
  *blocks[++arraySize].name = *"No_Block";
  blocks[arraySize].start = 0;
  blocks[arraySize].end = 0x10FFFF;
  inFile.close();
}

// void search(unsigned char *str) {
//   char charInStr;
//   int arr[arraySize];
//   for (int i = 0; i < arraySize; i++) {
//     arr[i] = 0;
//   }
//   for (int i = 0; i < ; i++) {
//     charInStr = str[i];
//     utf8_bytes_to_charpos(str, i);
//   }
// }

void string_to_cstring(char *c, string str) {
  int i = 0;
  for (; i < str.length(); i++) {
    c[i] = str[i];
  }
  c[++i] = '\0';
}