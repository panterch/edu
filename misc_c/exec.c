#include <stdio.h>
#include <unistd.h>

int main(void) {
  execl("/bin/echo", "echo", "Hello, World!", NULL);
}
