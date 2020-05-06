#include "cstring"
#include "iostream"

using namespace std;
char *commands[] = {"start", "stop", "restart", "status", "exit"};
#define START_CMD 0
#define STOP_CMD 1
#define RESTART_CMD 2
#define STATUS_CMD 3
#define EXIT_CMD 4

int findCommand(char *str) {
  for (int i = 0; i < 5; i++) {
    if (!strcmp(str, commands[i])) {
      return i;
    }
  }
  return -1;
}

int main() {
  string str;
  int status = 1;
  char *command;
  while (status != 4) {
    do {
      cout << ">";
      getline(cin, str);
      command = new char[8]();
      for (int i = 0, j = 0; i < str.size(); i++) {
        if (str[i] != ' ') {
          command[j++] = str[i];
          if (str[i + 1] == ' ') {
            break;
          }
        }
      }
    } while (command[0] == '\000');

    status = findCommand(command);

    delete[] command;

    switch (status) {
    case START_CMD:
    case STOP_CMD:
    case RESTART_CMD:
    case STATUS_CMD:
      cout << "command <" << commands[status] << "> recognized" << endl;
      break;
    case EXIT_CMD:
      break;
    default:
      cout << "Invalid command" << endl;
    }
  }
}