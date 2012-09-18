#define STACKSIZE 10

struct c_stack {
  int data[STACKSIZE];
  int size;
} stack;


// returns 0 on stack overflow
int push(int c);
// returns 0 on stack underflow
int pop();
// returns 0 on stack underflow
int peek();
