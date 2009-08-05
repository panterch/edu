#include <stdio.h>

int main(void)
{
  int array[4] = { 1, 2, 3, 4 };
  int *pointer = &array[0];

  int i;
  for (i = 0; i<4; i++) {
    printf("%p - %i - %i\n", (void *)pointer, *pointer, array[i]);
    pointer++;
  }

  pointer = array;
  *pointer = 123;
  printf("\n%i\n", array[0]);
}
