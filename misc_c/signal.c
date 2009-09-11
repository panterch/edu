#include <signal.h>
#include <stdio.h>
#include <stdlib.h>

void signal_handler() {
  printf(" time's up!\n");
  exit(0);
}

int main() {
  struct sigaction sa;
  sa.sa_handler = signal_handler;
  sigemptyset(&sa.sa_mask);
  sa.sa_flags = 0;
  sigaction(SIGALRM, &sa, NULL);
  alarm(5);
  while(1) {
    printf(".");
    fflush(stdout);
    sleep(1);
  }
}

