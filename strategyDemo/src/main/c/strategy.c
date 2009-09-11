#include <stdio.h>
#include <time.h>

typedef void (*strategy)(char *);

void noop(char* msg) {
}

void standart_output(char* msg) {
  printf("%s\n", msg);
}

void standart_error(char* msg) {
  fprintf(stderr, "%s\n", msg);
}

void with_time(char* msg) {
  time_t t = time(NULL);
  printf("%d: %s\n", (int)t, msg);
}

int main(void) {
  char* msg = "Hello, World!";
  strategy strategies[4] = {
     &noop,
     &standart_output,
     &standart_error,
     &with_time
  };
  int i;
  for (i=0; i<4; i++) {
     strategies[i](msg);
  }
}
