#include <stdio.h>
#include <unistd.h>
#include <wait.h>

int main(void) {
  pid_t ret_f = fork();
  if (0 == ret_f) {
    exit(-1);
  }
  int status;
  wait(&status);
  status = WEXITSTATUS(status);
  //status = ((int)(((status)>>8)&0xFF));
  printf("child exit status: %i\n", status);
  exit(-1);
}
