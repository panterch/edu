#include <stdio.h>
#include <string.h>

int main(void)
{
  char one[] = "test";
  char two[] = "test";

  if (one == two) {
    puts("same reference");
  }
  if (0 == (strcmp(one, two))) {
    puts("same value");
  }
}
