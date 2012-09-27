#include <stdio.h>
#include <stdlib.h>

int main() {
  struct t_list {
    int data;
    struct t_list *next;
  };

  typedef struct t_list list;


  list *item = (list*)malloc(sizeof(list));

  item->data = 54;

  list *item2 = (list*)malloc(sizeof(list));


  item2->data = 99;

  item->next = item2;


  printf("item 1: %d\n", item->data);
  printf("item 2: %d\n", item->next->data);
}
