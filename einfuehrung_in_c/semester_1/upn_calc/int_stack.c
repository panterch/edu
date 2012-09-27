#include "int_stack.h"

int push(int c) {
  if(stack.size == STACKSIZE)
    return 0;
  return stack.data[stack.size++] = c;
}

int pop() {
  if(stack.size == 0)
    return 0;
  return stack.data[--stack.size];
}

int peek() {
  if(stack.size == 0)
    return 0;
  return stack.data[stack.size-1];
}
