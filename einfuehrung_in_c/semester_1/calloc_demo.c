#include <stdlib.h>
#include <stdio.h>

int main() {
  unsigned long long num;
  int *ptr;

  printf("Enter the number of type int to allocate: ");
  scanf("%lli", &num);

  ptr = (int*)calloc(num, sizeof(int));

  if (ptr)
    puts("Memory allocation was successful.");
  else
    puts("Memory allocation failed.");


  free(ptr);

  return 0;
}
