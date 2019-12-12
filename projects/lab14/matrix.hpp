#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

#define MAXROWS 2
#define MAXCOLS 3

template <class T> class Matrix {
private:
  T matrix[MAXROWS][MAXCOLS];
  int rows;
  int cols;

public:
  // Constructor
  Matrix() {
    rows = MAXROWS;
    cols = MAXCOLS;
    // for (int i = 0; i < MAXROWS; i++) {
    //   for (int j = 0; j < MAXCOLS; j++) {
    //     matrix[i][j] = initValue;
    //   }
    // }
  }; // Set rows to MAXROWS and cols to MAXCOLS
  // Initialize all the values of matrix to zero
  // print Functions
  void printMatrix() {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        std::cout << setiosflags(ios::left) << setw(10) << matrix[i][j]
                  << resetiosflags(ios::right) << "\t";
      }
      std::cout << std::endl;
    }
  };
  // Setter Functions
  void setMatrix(T array[][MAXCOLS]) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        matrix[i][j] = array[i][j];
      }
    }
  }; // set the doubleArray to what is sent
  void addMatrix(T array[][MAXCOLS]) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        matrix[i][j] += array[i][j];
      }
    }
  }; // add an array to matrix
  void addMatrix(Matrix otherMatrix) { matrix = otherMatrix; };
  // No destructor needed
};