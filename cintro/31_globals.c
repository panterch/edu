#include <stdio.h>

int x = 42;

void print_the_x();

int main(void)
{
  print_the_x();
  x = 23;
  print_the_x();
}

void print_the_x()
{
  printf("x is %i\n", x);
}
