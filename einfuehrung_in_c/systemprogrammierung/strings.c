#include <stdio.h>

int main() {
  char str_1[] = "ab\n";
  char str_2[] = {'b', 'b', '\n', '\0'};

  printf("str_1: %s", str_1);
  printf("str_2: %s", str_2);

  printf("Equality test: %i\n", (str_1 == str_2));
  printf("Equality test: %i\n", strcmp(str_1, str_2));
}
