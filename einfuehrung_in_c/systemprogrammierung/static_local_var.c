#include <stdio.h>

int main() {
  int i;

  printf("First call with argument '10': \t%d\n", foo(10));
  for(i=1; i<10; i++)
    printf("%dth call without argument: \t%d\n", i, foo());
}

int foo(int n) {
  static int x;
  if(n)
    x = n;
  else
    x++;
  return x;
}
