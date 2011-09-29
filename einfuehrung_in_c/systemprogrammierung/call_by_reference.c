#include <stdio.h>

void add_one(int []);

// Call by reference for arrays
int main() {
  int digit[] = {1};

  printf("Before call: %d\n", digit[0]);
  add_one(digit);
  printf("After call: %d\n", digit[0]);
}

// tmp[] supposedly should be local, but is it?
void add_one(int tmp[]) {
  tmp[0]++;
}
