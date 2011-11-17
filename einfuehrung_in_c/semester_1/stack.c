#include "stack.h"

char push(char c) {
  if(stack.size == STACKSIZE)
    return 0;
  return stack.data[stack.size++] = c;
}

char pop() {
  if(stack.size == 0)
    return 0;
  return stack.data[--stack.size];
}

char peek() {
  if(stack.size == 0)
    return 0;
  return stack.data[stack.size-1];
}
