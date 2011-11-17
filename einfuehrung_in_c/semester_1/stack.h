#define STACKSIZE 10

struct c_stack {
  char data[STACKSIZE];
  int size;
} stack;




// returns 0 on stack overflow
char push(char c);
// returns 0 on stack underflow
char pop();
// returns 0 on stack underflow
char peek();
