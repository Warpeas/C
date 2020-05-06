#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>

struct test {
  u_int8_t data;
}test;

int main() {
  __uint8_t *data=(u_int8_t *)malloc(8);
  *data = 1;
  memcpy(&test.data, data, 1);
  printf("%d %d\n",test.data,*data);
  return 0;
}