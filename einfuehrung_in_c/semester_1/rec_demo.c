#include <stdio.h>

int rec(int n) {
  if(n == 10)
    return;
  printf("Aufruf: %d\n", n);
  rec(n+1);

}
int main(void) {
  rec(0);
}
