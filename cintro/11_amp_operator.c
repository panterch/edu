#include <stdio.h>

int main(void)
{
  int x = 42;
  printf("%p points to %i\n", (void *)&x, x);
  return 0;
}
