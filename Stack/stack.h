#include <stdio.h>

struct link
{
    int data;
    struct link* previous;
    struct link* next;
};
typedef struct link* LINK;

struct stack
{
    int size;
    LINK start;
    LINK end;
    struct stack *Redo;
};
typedef struct stack* Stack;