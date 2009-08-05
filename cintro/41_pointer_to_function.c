#include <stdio.h>

void greet(int times) {
  int i;
  for (i=0; i<times; i++) {
    puts("Hello, World!");
  }
}

int main(void) {

  void (*f)(int) = &greet;
  f(3);
  return 0;

}
