#include <stdio.h>

int fib(int n) {
  return (n < 2) ? n : fib(n-1) + fib(n-2);
}

int main(int argc, char *argv[]) {
  int num = atoi(argv[1]);
  printf("%d\n", fib(num));
}
