#include <stdio.h>

#define SIZE 10

int main(void)
{
  int array[SIZE];
  int i;
  for(i = 0; i<SIZE; i++) {
    array[i] = i;
  }
  for(i = 0; i<SIZE; i++) {
    printf("%i\t", array[i]);
  }
  puts("");
}
