#include "fstream"
#include "iostream"
#include "sstream"
#include "string"
#include "vector"
#include <cmath>

using namespace std;
const int name_max = 35;
const int array_size = 1000;
int ptr;
const int radius = 6371;

/*Transform the string to lower level
Copied from CSDN blocker「荷叶田田_」Link:
https://blog.csdn.net/qian2213762498/article/details/79553372
*/
string toLower(string s) {
  int len = s.size();
  for (int i = 0; i < len; i++) {
    if (s[i] >= 'A' && s[i] <= 'Z') {
      s[i] += 32; //+32转换为小写
                  // s[i]=s[i]-'A'+'a';
    }
  }
  return s;
}

// Find if s1 is a prefix of s2
int isEqual(string s1, string s2) {
  if (s1.length() <= s2.length()) {
    int len;
    len = s1.length();
    for (int i = 0; i < len; i++) {
      if (s1[i] != s2[i]) {
        return false;
      }
    }
  } else {
    return false;
  }
  return true;
}

// Determine if the input is number
int isNumber(string s) {
  for (int i = 0; i < s.length(); i++) {
    if (!(s[i] > '0' && s[i] < '9')) {
      return false;
    }
  }
  return true;
}

// Process the user's select
int inputSelect(int max) {
  string s;
  int number;
  while (true) {
    cout << "Please select one you want: ";
    cin >> s;
    if (!isNumber(s)) {
      cout << "Invalid input" << endl;
      continue;
    }
    number = stoi(s);
    if (number < 0 || number > max) {
      cout << "Invalid number" << endl;
      continue;
    }
    return number;
  }
}

// Prompt the use to input the city's name
string nameInput() {
  string name;
  while (true) {
    cin >> ws;
    getline(cin, name);
    if (name.length() > name_max) {
      name = name.substr(0, name_max);
    }

    for (ptr = name_max - 1; ptr > 0; ptr--) {
      if (name[ptr] != ' ') {
        break;
      }
    }

    name = name.substr(0, ptr + 1);

    if (name.length() < 3) {
      cout << "The input name too short, please input again" << endl;
    } else {
      return name;
    }
    cout << "The city's name: ";
  }
}

struct city {
  int index;
  string city;
  string country;
  string province;
  float latitude;
  float longitude;
};

int main() {
  /* First part of the program, read the city.csv
  This part of code is copied from CSDN blocker「木兄」link:
  https://blog.csdn.net/chenlin41204050/article/details/78429437 And made some
  changes to fit my acquirement
  */
  ifstream inFile("world_cities.csv", ios::in);

  // If file open failed, system exit
  if (!inFile) {
    cout << "Warning! Filed to read database" << endl << "Syetem exit" << endl;
    return 1;
  }

  int i = 0;
  string line;
  city cities[array_size];
  vector<city> warningCities;
  // An array of the alphabet of the city list, only work when data is in
  // dictionary order
  int alphabet[28];
  for (int i = 0; i < 28; i++) {
    alphabet[i] = -1;
  }

  // Struct the city list
  for (i = 0; i < array_size; i++) {
    int flag = 0;
    getline(inFile, line);
    if (line.empty()) {
      break;
    }
    string field;
    istringstream sin(line);
    cities[i].index = i + 1;
    getline(sin, field, ',');
    if (field.size() > name_max) {
      cities[i].city = field.substr(0, name_max) + "...";
      flag = 1;
    } else {
      cities[i].city = field;
    }

    if (alphabet[field[0] - 'A'] == -1) {
      alphabet[field[0] - 'A'] = i;
    }

    getline(sin, field, ',');
    if (field.size() > name_max) {
      cities[i].province = field.substr(0, name_max) + "...";
    } else {
      cities[i].province = field;
    }

    getline(sin, field, ',');
    if (field.size() > name_max) {
      cities[i].country = field.substr(0, name_max) + "...";
      flag = 1;
    } else {
      cities[i].country = field;
    }

    getline(sin, field, ',');
    cities[i].latitude = stof(field);

    getline(sin, field);
    cities[i].longitude = stof(field);

    if (flag == 1) {
      warningCities.push_back(cities[i]);
    }
  }

  inFile.close();
  alphabet[27] = i;
  // If there's only two cities in the list, we think that's invalid
  if (i < 2) {
    cout << "The input file invalid, system exit" << endl;
    return 0;
  }
  if (!warningCities.empty()) {
    cout << "Warning! The input data does not fit program's preference, "
            "please check"
         << endl;
    for (int i = 0; i < warningCities.size(); i++) {
      cout << warningCities[i].index << " " << warningCities[i].city << ", "
           << warningCities[i].province << ", " << warningCities[i].country
           << " " << warningCities[i].latitude << " "
           << warningCities[i].longitude << endl;
    }
    cout << warningCities.size() << " warnings, system exit" << endl;
    return 1;
  }

  // Second part of the program, display the distance between two city
  // The picture is just for fun, please don't reduce my grade for this reason
  cout
      << "Database loaded" << endl
      << "                   _                                       _   "
      << endl
      << "     /\\           (_)                                     | |  "
      << endl
      << "    /  \\   ___ ___ _  __ _  ___ _ __  _ __ ___   ___ _ __ | |_ "
      << endl
      << "   / /\\ \\ / __/ __| |/ _` |/ _ \\ '_ \\| '_ ` _ \\ / _ \\ '_ \\| "
         "__|"
      << endl
      << "  / ____ \\__ \\__ \\ | (_| |  __/ | | | | | | | |  __/ | | | |_ "
      << endl
      << " /_/    \\_\\___/___/_|\\__, |\\___|_| |_|_| |_| |_|\\___|_| |_|\\__|"
      << endl
      << "                      __/ |                                    "
      << endl
      << "                     |___/                                     "
      << endl
      << endl
      << "Hello, I'm city distance calculate helper ADworld" << endl
      << "There's " << i << " cities in my system database" << endl;
  float phi1, theta1, phi2, theta2;
  string name;
  vector<city> match1;
  int m1 = 0;
  vector<city> match2;
  int m2 = 0;
  while (true) {
    match1.clear();
    match2.clear();

    cout << endl
         << "Please input two cities' name, or type \"bye\" to quit the system"
         << endl;

    cout << "The first city's name: ";
    name = nameInput();
    if (toLower(name) == "bye" || toLower(name) == "exit") {
      break;
    }

    ptr = alphabet[toLower(name)[0] - 'a'];
    for (; ptr < alphabet[toLower(name)[0] - 'a' + 1]; ptr++) {
      if (isEqual(toLower(name), toLower(cities[ptr].city))) {
        match1.push_back(cities[ptr]);
      }
    }

    if (match1.empty()) {
      cout << "There's no matched city" << endl;
      continue;
    } else if (match1.size() == 1) {
      cout << "The city 1 is" << endl
           << "--------------------------------------------------------------"
           << endl
           << "#1 ";
    } else {
      cout << "The matched cities are" << endl
           << "--------------------------------------------------------------"
           << endl;
      for (int i = 0; i < match1.size(); i++) {
        cout << "#" << i + 1 << " " << match1[i].city << ", "
             << match1[i].country << " " << match1[i].latitude << " "
             << match1[i].longitude << endl;
      }
      cout << "--------------------------------------------------------------"
           << endl;
      m1 = inputSelect(match1.size());
      m1--;
      cout << endl
           << "You select" << endl
           << "--------------------------------------------------------------"
           << endl;
    }
    cout << match1[m1].city << ", " << match1[m1].country << " "
         << match1[m1].latitude << " " << match1[m1].longitude << endl
         << "--------------------------------------------------------------"
         << endl
         << endl;
    phi1 = 90 - match1[m1].latitude;
    theta1 = match1[m1].longitude;

    cout << "The second city's name: ";
    name = nameInput();
    if (toLower(name) == "bye" || toLower(name) == "exit") {
      break;
    }

    ptr = alphabet[toLower(name)[0] - 'a'];
    for (; ptr < alphabet[toLower(name)[0] - 'a' + 1]; ptr++) {
      if (isEqual(toLower(name), toLower(cities[ptr].city))) {
        match2.push_back(cities[ptr]);
      }
    }
    if (match2.empty()) {
      cout << "There's no matched city" << endl;
      continue;
    } else if (match2.size() == 1) {
      cout << "The city 2 is" << endl
           << "--------------------------------------------------------------"
           << endl
           << "#1 ";
    } else {
      cout << "The matched cities are" << endl
           << "--------------------------------------------------------------"
           << endl;
      for (int i = 0; i < match2.size(); i++) {
        cout << "#" << i + 1 << " " << match2[i].city << ", "
             << match2[i].country << " " << match2[i].latitude << " "
             << match2[i].longitude << endl;
      }
      cout << "--------------------------------------------------------------"
           << endl;
      m2 = inputSelect(match2.size());
      m2--;
      cout << endl
           << "You select" << endl
           << "--------------------------------------------------------------"
           << endl;
    }
    cout << match2[m2].city << ", " << match2[m2].country << " "
         << match2[m2].latitude << " " << match2[m2].longitude << endl
         << "--------------------------------------------------------------"
         << endl
         << endl;
    phi2 = 90 - match2[m2].latitude;
    theta2 = match2[m2].longitude;

    // Calculate and output
    cout << "--------------------------------------------------------------"
         << endl
         << "The distance between " << match1[m1].city << " and "
         << match2[m2].city << " is "
         << radius * acos(sin(phi1 * atan(1) * 4 / 180) *
                              sin(phi2 * atan(1) * 4 / 180) *
                              cos((theta1 - theta2) * atan(1) * 4 / 180) +
                          cos(phi1 * atan(1) * 4 / 180) *
                              cos(phi2 * atan(1) * 4 / 180))
         << " km" << endl
         << "--------------------------------------------------------------"
         << endl;
  }
  cout << "Bye" << endl;
  return 0;
}
