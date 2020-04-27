#include <stdio.h>
#include <stdlib.h>
#define MAX 32767
#define MAXSIZE 100

typedef struct {
  int data[MAXSIZE];
  int top;
} SqStack;

void Dij(int m, int matrix[m][m]);
void dij(int m, int n, int matrix[m][m]);
void Floyd(int m, int matrix[m][m]);

void InitStack(SqStack **s);
int Push(SqStack *s, int i);
int Pop(SqStack *s);

int main(void) {
  /*   int matrix[4][4] = {
      {0, 1, MAX, 4},
      {MAX, 0, 9, 2},
      {3, 5, 0, 8},
      {MAX, MAX, 6, 0}};
  */
  int matrix[6][6] = {
      {0, MAX, 10, MAX, 30, 100},
      {MAX, 0, 5, MAX, 1, MAX},
      {MAX, MAX, 0, 50, MAX, MAX},
      {MAX, MAX, MAX, 0, MAX, 10},
      {MAX, MAX, MAX, 20, 0, 60},
      {MAX, MAX, MAX, MAX, MAX, 0}};

  // Dij(6, matrix);
  dij(6, 0, matrix);
  // Floyd(6, matrix);
}

void dij(int m, int n, int matrix[m][m]) {
  int x[m], y[m], flag[m], Path[m][m];
  for (int i = 0; i < m; i++) {
    x[i] = matrix[n][i];
    y[i] = -1;
    flag[i] = 0;
    for (int j = 0; j < m; j++)
      Path[i][j] = -1;
  }
  flag[n] = 1;
  int l = 0;
  int V = MAX;
  for (int i = 0; i < m - 1; i++) {
    l = 0;
    V = MAX;
    for (int j = 0; j < m; j++) {
      if (flag[j] != 1 && x[j] < V) {
        l = j;
        V = x[j];
      }
    }
    if (V == MAX)
      continue;
    flag[l] = 1;
    int o = 0;
    printf("%d", n);
    if (y[l] != -1) {
      while (Path[y[l]][o] != -1) {
        printf("->%d", Path[y[l]][o]);
        Path[l][o] = Path[y[l]][o];
        o++;
      }
      printf("->%d", y[l]);
      Path[l][o] = y[l];
    }
    printf("->%d value:%d\n", l, V);
    for (int j = 0; j < m; j++) {
      if (matrix[l][j] + V < x[j]) {
        x[j] = matrix[l][j] + V;
        y[j] = l;
      }
    }
  }
}

void Floyd(int m, int matrix[m][m]) {
  int d[m][m], p[m][m];
  for (int i = 0; i < m; i++)
    for (int j = 0; j < m; j++) {
      if (matrix[i][j] != MAX)
        p[i][j] = i;
      else
        p[i][j] = -1;
      d[i][j] = matrix[i][j];
    };
  for (int i = 0; i < m; i++) {
    for (int x = 0; x < m; x++) {
      for (int y = 0; y < m; y++) {
        if (d[x][y] > d[x][i] + d[i][y]) {
          d[x][y] = d[x][i] + d[i][y];
          p[x][y] = p[i][y];
        }
      }
    }
  }
  SqStack *s;
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < m; j++) {
      if (i == j || d[i][j] == MAX) {
        continue;
      };
      InitStack(&s);
      printf("%d", i);
      int t = p[i][j];
      while (t != i) {
        Push(s, t);
        t = p[i][t];
      }
      t = Pop(s);
      while (t != -1) {
        printf("->%d", t);
        t = Pop(s);
      }

      printf("->%d value:%d\n", j, d[i][j]);
    }
  }
  printf("\n");
}

void Dij(int m, int matrix[m][m]) {
  for (int i = 0; i < m; i++) {
    dij(m, i, matrix);
  }
  printf("\n");
}
void InitStack(SqStack **s) {
  *s = (SqStack *)malloc(sizeof(SqStack));
  (*s)->top = -1;
}
int Push(SqStack *s, int i) {
  if (s->top == 100)
    return -1;
  s->data[++s->top] = i;
  return 1;
}

int Pop(SqStack *s) {
  if (s->top == -1)
    return -1;
  return s->data[s->top--];
}
