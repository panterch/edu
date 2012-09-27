#include <stdio.h>
#include "read_num.h"


int read_num(void) {
  char buf[MAXSIZE-1];
  fgets(buf, 4, stdin);
  return atoi(buf);
}
