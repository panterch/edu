#include <stdio.h>
#include <unistd.h>

int main(void) {
  pid_t ret_f = fork();
  pid_t pid   = getpid();
  printf("%i: return value of fork was %i\n", pid, ret_f);
  if (ret_f > 0) {
    waitpid(ret_f);
  }
  printf("%i: program end\n", pid, ret_f);
}
