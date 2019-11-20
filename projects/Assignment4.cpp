#include "cstdio"
#include "fstream"
#include "iostream"
#include "sstream"
#include "cstring"
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

void search(string str);

int main() {
  std::ifstream inFile("Blocks.txt", std::ios::in);
  if (!inFile) {
    cout << "Warning! Filed to read database" << endl << "System exit" << endl;
    return 1;
  }
  char line[50];
  for (int i = 0; i < array_Size; i++) {
    scanf("%[^\n]", line);
    if ((line[0] == '#' && strcmp(line, "# EOF\n")==1) || line[0] == '\n') {
      i--;
      continue;
    } else if (strcmp(line, "# EOF\n")==0) {
      arraySize = i;
      break;
    } else {
      scanf("%x..%x; %s", blocks[i].start, blocks[i].end, blocks[i].name);
    }
  }
  *blocks[++arraySize].name = *"No_Block";
  inFile.close();
}

void search(unsigned char *str){
  char charInStr;
  int arr[arraySize];
  for(int i = 0;i < arraySize;i++){
    arr[i] = 0;
  }
  for(int i = 0;i < str.length();i++){
    charInStr = str[i];
    utf8_bytes_to_charpos(str, i);
  }
}