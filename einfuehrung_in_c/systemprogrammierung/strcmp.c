#include <stdio.h>
#include <string.h>

int main(void) {
  char line[100];
  int x;

  fgets(line, sizeof(line), stdin);

  if(strcmp(line, "foo\n"))
    printf("yes, foo!\n");
  else
    printf("no, foo!\n");
}
