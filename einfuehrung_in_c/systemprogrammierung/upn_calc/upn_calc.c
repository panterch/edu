/* Calculator in reverse polish notation (UPN/postfix notation)
 *
 * Accepts as input:
 *   number literals
 *   +/-*
 *   =
 *   quit
 *
 * Sample calculation:
 *   In well-known infix:
 *     (2 + 3) * 4
 *   In UPN:
 *     2 3 + 4 *
 *
 *   Input for program:
 *     2 [return] 3 [return] + [return] 4 [return] * [return] = [return]
 *
 *   Output: 20
 */

#include <stdio.h>
#include "int_stack.h"

int main(void) {
  char line[100];
  int x, y;

  do {
    fgets(line, sizeof(line), stdin);

    // process addition
    if(!strcmp(line, "+\n"))
      push(pop() + pop());

    // process subtraction
    else if(!strcmp(line, "-\n")) {
      // for subtraction, order is relevant
      x = pop();
      y = pop();
      push(y - x);
    }

    // process multiplication
    else if(!strcmp(line, "*\n"))
      push(pop() * pop());

    // process division
    else if(!strcmp(line, "/\n")) {
      // for division, order is relevant
      x = pop();
      y = pop();
      push(y / x);
    }

    // show result
    else if(!strcmp(line, "=\n")) {
      printf("Ergebnis: %d\n", peek());
    }

    // process operand
    else {
      sscanf(line, "%d", &x);
      push(x);
    }

  } while(strcmp(line, "quit\n"));
}
