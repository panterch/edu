#include <stdio.h>

// count digits
int main() {
  int i, c;
  int ndigit[10];
  
  // Initializing array to count of zero
  for (i = 0; i < 10; ++i)
    ndigit[i] = 0;

  // Actual count
  while ((c = getchar()) != EOF)
    if (c >= '0' && c <= '9')
      ++ndigit[c-'0'];

  // Print result
  printf("Digits = ");
  for (i = 0; i < 10; ++i)
    printf(" %d", ndigit[i]);
}
