#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
  bool hasPath(char *matrix, int rows, int cols, int startX, int startY,
               int endX, int endY, vector<int> &Path) {
    if (matrix == NULL || rows < 1 || cols < 1 || startX < 0 || startY < 0 ||
        endX < 0 || endY < 0 || (startX == endX && startY == endY))
      return false;
    bool *visited =
        new bool[rows *
                 cols]; //定义一个辅助矩阵，用来标记路径是否已经进入了每个格子
    memset(visited, 0, rows * cols);
    int pathLength = 0;
    if (hasPathCore(matrix, rows, cols, startX, startY, endX, endY, visited,
                    Path)) {
      return true;
    }
    delete[] visited;
    return false;
  }

  /*此函数用来判断在当前路径满足条件下，相邻格子中是否存在一个格子满足条件*/
  bool hasPathCore(char *matrix, int rows, int cols, int row, int col, int endX,
                   int endY, bool *visited, vector<int> &Path) {
    if ((row == endX) && (col == endY) && (matrix[row * cols + col] == '1')) {
      Path.push_back(endY);
      Path.push_back(endX);
      return true;
    }
    bool hasPath = false;
    if (row >= 0 && row < rows && col >= 0 && col < cols &&
        matrix[row * cols + col] == '1' && !visited[row * cols + col]) {
      // ++pathLength;
      visited[row * cols + col] = true;
      Path.push_back(col);
      Path.push_back(row);
      /*如果矩阵格子(row,col)字符为1时，从它的4个相邻格子中寻找下一个字符为1的格子*/
      hasPath = hasPathCore(matrix, rows, cols, row, col - 1, endX, endY,
                            visited, Path) ||
                hasPathCore(matrix, rows, cols, row - 1, col, endX, endY,
                            visited, Path) ||
                hasPathCore(matrix, rows, cols, row, col + 1, endX, endY,
                            visited, Path) ||
                hasPathCore(matrix, rows, cols, row + 1, col, endX, endY,
                            visited, Path);
      if (!hasPath) //如果没找到，则说明当前第n个格子定位不正确，返回上一个位置重新定位
      {
        visited[row * cols + col] = false;
        Path.pop_back();
        Path.pop_back();
      }
    }
    return hasPath;
  }
};

int main() {
  // char* matrix = "abcesfcsadee";
  char *matrix = "1000000110110001101000010111011110100000010000001"; //设置迷宫
  int startX, startY, endX, endY;
  cin >> startX >> startY >> endX >> endY; //输入起始结束坐标
  Solution s;
  vector<int> Path;
  bool re = s.hasPath(matrix, 7, 7, startX, startY, endX, endY, Path);
  cout << re << endl;
  for (int i = 0; i < Path.size();)
    cout << "(" << Path[i++] << ',' << Path[i++] << ")"
         << " ";
  cout << endl;
  return 0;
}