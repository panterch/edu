#include <stdio.h>

int main()
{
  float pi = 3.141;
  // will cause an error
  // pi = 3.123;
  printf("pi is approx. %f\n", pi);
  return 0;
}
