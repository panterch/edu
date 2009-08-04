#include <stdio.h>

int main()
{
  int x;
  printf("%i\n", x);

  x = 1337;
  printf("%i\n", x);

  {
    int x = 0;
    printf("%i\n", x);
  }

  printf("%i\n", x);
  return 0;
}
