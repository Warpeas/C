#include <cmath>
#include <iostream>

using namespace std;

long long T[][3] = {
    {1, 0, 0},
    {1, 1, 0},
    {1, 2, 1},
    {2, 3, 3},
    {5, 5, 6},
    {11, 10, 11},
    {22, 21, 21},
    {43, 43, 42},
    {85, 86, 85},
    {170, 171, 171},
    {341, 341, 342},
    {683, 682, 683},
    {1366, 1365, 1365},
    {2731, 2731, 2730},
    {5461, 5462, 5461},
    {10922, 10923, 10923},
    {21845, 21845, 21846},
    {43691, 43690, 43691},
    {87382, 87381, 87381},
    {174763, 174763, 174762},
    {349525, 349526, 349525},
    {699050, 699051, 699051},
    {1398101, 1398101, 1398102},
    {2796203, 2796202, 2796203},
    {5592406, 5592405, 5592405},
    {11184811, 11184811, 11184810},
    {22369621, 22369622, 22369621},
    {44739242, 44739243, 44739243},
    {89478485, 89478485, 89478486},
    {178956971, 178956970, 178956971},
    {357913942, 357913941, 357913941},
    {715827883, 715827883, 715827882},
    {1431655765, 1431655766, 1431655765},
    {2863311530, 2863311531, 2863311531},
    {5726623061, 5726623061, 5726623062},
    {11453246123, 11453246122, 11453246123},
    {22906492246, 22906492245, 22906492245},
    {45812984491, 45812984491, 45812984490},
    {91625968981, 91625968982, 91625968981},
    {183251937962, 183251937963, 183251937963},
    {366503875925, 366503875925, 366503875926},
    {733007751851, 733007751850, 733007751851},
    {1466015503702, 1466015503701, 1466015503701},
    {2932031007403, 2932031007403, 2932031007402},
    {5864062014805, 5864062014806, 5864062014805},
    {11728124029610, 11728124029611, 11728124029611},
    {23456248059221, 23456248059221, 23456248059222},
    {46912496118443, 46912496118442, 46912496118443},
    {93824992236886, 93824992236885, 93824992236885},
    {187649984473771, 187649984473771, 187649984473770},
    {375299968947541, 375299968947542, 375299968947541},
    {750599937895082, 750599937895083, 750599937895083},
    {1501199875790165, 1501199875790165, 1501199875790166},
    {3002399751580331, 3002399751580330, 3002399751580331},
    {6004799503160662, 6004799503160661, 6004799503160661},
    {12009599006321323, 12009599006321323, 12009599006321322},
    {24019198012642645, 24019198012642646, 24019198012642645},
    {48038396025285290, 48038396025285291, 48038396025285291},
    {96076792050570581, 96076792050570581, 96076792050570582},
    {192153584101141163, 192153584101141162, 192153584101141163},
};

long long *cnt(long long l) {
  long long *result = new long long[3]();
  if (l == 0) {
    return result;
  }
  long long index = log2(l);
  long long *left = T[index];
  long long *right = cnt(l - (long long)pow(2, index));
  for (int i = 0; i < 3; i++) {
    result[i] = left[i] + right[(i + 2) % 3];
  }
  return result;
}

int main() {
  int n;
  cin >> n;
  long long l;
  long long *output;
  for (int i = 0; i < n; i++) {
    cin >> l;
    output = cnt(l);
    for (int i = 0; i < 3; i++) {
      cout << output[i] << " ";
    }
    cout << endl;
  }
  return 0;
}