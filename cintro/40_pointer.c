#include <stdio.h>

int main(void)
{
  double *a, b;
  a = &b;
  b = 1.234;

  printf("sizeof(a): %i sizeof(b): %i\n", sizeof(a), sizeof(b));
  printf("*a: %f b: %f\n", *a, b);
}
