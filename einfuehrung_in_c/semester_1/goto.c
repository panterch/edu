#include <stdio.h>
#include <string.h>

// Integer division
int divide(int a, int b) {
  if(!b) {
    goto division_by_zero;
  }
  else
    return (int)a/b;
  
  division_by_zero:
    printf("honk!\n");
    return 0;
}

int main(void) {
  printf("%d\n", divide(4, 2));
  printf("%d\n", divide(4, 0));
}

