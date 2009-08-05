#include <stdio.h>

int main(void)
{
  int i;
  const int MAX = 100;
  for(i=2; i < MAX; i++) {
    int j;
    for(j=2; j < i; j++) {
      if (0 == (i%j)) {
        break;
      }
    }
    if (i <= j) {
      printf("%i\t", i);
    }
  }
  printf("\n");
}
