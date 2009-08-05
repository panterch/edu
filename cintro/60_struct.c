#include <stdio.h>

int main(void)
{
  struct date
  {
    int day;
    int month;
    int year;
  };

  struct date d = { 31, 12, 2009 };

  printf("%d.%d.%d\n", d.day, d.month, d.year);
}

