#include <stdio.h>
#include <string.h>

// count all characters in an array except for one
int count_everything_but(char c, char *s) {
  int count = 0;
  int i;
  for (i = 0; i<strlen(s)-1; i++)
    if(s[i] == c)
      continue;
    else
      count++;
  return count;
}

int main(void) {
  char s[] = "foo bar foobar";
  char c;

  printf("Please enter a character: ");
  scanf("%c", &c);

  printf("'%s' has '%d' '%c's\n", s, count_everything_but(c, s), c);
}
