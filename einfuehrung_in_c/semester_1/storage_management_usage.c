#include <stdio.h>

#define ALLOCSIZE 10000 /* size of available space */

static char allocbuf[ALLOCSIZE]; /* storage for alloc */
static char *allocp = allocbuf; /* next free position */

char *alloc(int n)	/* return pointer to n characters */
{
  if (allocbuf + ALLOCSIZE - allocp >= n) {
    /* it fits */
    allocp += n;
  return allocp - n; /* old p */
  }
  else	/* not enough room */
    return 0;
}

void afree(char *p) /* free storage pointed to by p */
{
  if (p >= allocbuf && p < allocbuf + ALLOCSIZE)
    allocp = p;
}

int main() {
  char *data = alloc(10);
  int i;
  for (i=65; i<=75; i++) {
    data[i-65] = i;
    printf("data[%d]: %c\n", i-65, i);
  }
}
