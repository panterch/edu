#include <stdio.h>
#include <string.h>

int main(void) {
  char string[] = "foo";
  printf("\"%s\" holds %d characters\n", string, strlen(string));
}
