#include <stdio.h>

int foo() {
  static int x;
  if(!x)
    x = 5;
  else
    x++;
  return x;
}

int main() {
  int i;

  for(i=1; i<10; i++)
    printf("%dth call: \t%d\n", i, foo());
}
