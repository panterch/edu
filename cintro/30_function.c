#include <stdio.h>

int is_prime(int cand)
{
  int i;
  for(i=2; i < cand; i++) {
    if (0 == (cand%i)) {
      return 0;
    }
  }
  return 1;
}

int main(void)
{
  int i;
  const int MAX = 100;
  for(i=2; i < MAX; i++) {
    if(is_prime(i)) {
      printf("%i\t",i);
    }
  }
  printf("\n");
}
