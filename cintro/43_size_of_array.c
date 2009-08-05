#include <stdio.h>

int main(void)
{
  int array[5] = { 1, 2, 3, 4, 5 };
  printf("%i Bytes (one int is %i Bytes)\n", sizeof(array), sizeof(int));
}

