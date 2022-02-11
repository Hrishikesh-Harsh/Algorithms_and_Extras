#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>
#include "stack.h"

#define LINK_SIZE sizeof(struct link)
#define STACK_SIZE sizeof(struct stack)

void* myAlloc(void *pointer,int space)
{ 
    pointer=malloc(space);
    //spaceAllocated+=space;
    //printf("current status=%d\n",spaceAllocated);
    return pointer;
}

void myFree(void *pointer)
{
    //spaceAllocated-=space;
    free(pointer);
    pointer=NULL;
}

Stack createStack(Stack stack)
{
    stack=(Stack)myAlloc(stack,STACK_SIZE);
    stack->Redo=(Stack)myAlloc(stack->Redo,STACK_SIZE);

    stack->start=NULL;
    stack->end=NULL;
    stack->size=0;

    stack->Redo->start=NULL;
    stack->Redo->end=NULL;
    stack->Redo->size=0;

    return stack;
}

void push(Stack stack, int data)
{
    LINK link;
    link=(LINK)myAlloc(link,LINK_SIZE);
    link->data=data;
    
    if(stack->size==0)
    {
        stack->size++;
        //printf("size=%d\n",stack->size);
        stack->start=link;
        stack->end=link;

        link->previous=NULL;
        link->next=NULL;
        return;
    }

    stack->size++;
    //printf("size=%d\n",stack->size);
    
    link->previous=stack->end;
    stack->end->next=link;
    link->next=NULL;
    stack->end=link;
    return;
}

void pushRedoStack(Stack Redo,LINK popped)
{
    push(Redo,popped->data);
    return;
}

LINK pop(Stack stack)
{
    if(stack->size==0)
    return NULL;

    LINK popped;
    popped=stack->end;

    stack->end=popped->previous;

    if(stack->size>=2)
    popped->previous->next=NULL;

    stack->size--;
    pushRedoStack(stack->Redo,popped);
    return popped;
}

void undo(Stack stack)
{
    if(stack->Redo->size==0)
    return;

    LINK popped;
    popped=stack->Redo->end;

    stack->Redo->end=popped->previous;

    if(stack->Redo->size>=2)
    popped->previous->next=NULL;

    stack->Redo->size--;

    push(stack,popped->data);
    return;
}

void peek(Stack stack)
{
    if(stack->size==0)
    {
        printf("Stack Empty..!\n");
        return;
    }

    printf("Top of Stack=%d\n\n",stack->end->data);
    return ;
}

void printStack(Stack stack)
{
    if(stack->size==0)
    {
        printf("[ ]\n\n");
        return;
    }
    LINK temp=stack->start;

    printf("Bottom --> [");
    for(int i=0;i<stack->size;i++)
    {        
        if(i!=stack->size-1)
        printf("%d,",temp->data);
        else
        printf("%d] <-- Top\n\n",temp->data);

        temp=temp->next;
    }
    temp=NULL;
}

int main()
{
    printf("----------Stacking Operations----------\n\n");

    Stack stack;LINK popped;
    int choice=0;
    int n;int e;
    stack=(Stack)createStack(stack);
    printf("Stack has been created..\n\n");
    printf("1. Push in Stack\n");
    printf("2. Pop from Stack\n");
    printf("3. Peek into Stack\n");
    printf("4. Undo Last Pop\n");
    printf("5. Print Stack + Undo Stack\n");
    printf("6. Exit\n\n");

    printf("Choice: ");
    scanf("%d",&choice);
    

    do{
        switch(choice)
        {
            case 1:
                printf("\n\n---------Push Operation---------\n\n");
                printf("Number of elements to push: ");
                scanf("%d",&n);
                printf("\n\n Enter elements: \n\n");
                for(int i=0;i<n;i++)
                {
                    scanf("%d",&e);
                    push(stack,e);
                }
                printf("Done..!\n\n");
                break;
            
            case 2:
                printf("\n\n---------Pop Operation---------\n\n");
                popped=pop(stack);
                printf("Popped=%d\n\n",popped->data);
                printf("Done..!\n\n");
                break;

            case 3:
                printf("\n\n---------Peek Operation---------\n\n");
                peek(stack);
                printf("Done..!\n\n");
                break;

            case 4:
                printf("\n\n---------Undo Operation---------\n\n");
                undo(stack);
                printStack(stack);
                printf("Done..!\n\n");
                break;
            
            case 5:
                printf("\n\n---------Print Stack---------\n\n");
                printf("Stack:\n");
                printStack(stack);
                printf("--------------------------------------\n\n");
                printf("Undo Stack:\n");
                printStack(stack->Redo);
                printf("Done..!\n\n");
                break;

            case 6:
                printf("Exit...\n");
                exit(0);
                break;

            default:
                printf("Wrong Choice..! Try Again..\n\n");
                break;
        }

        //printf("Stack has been created..\n\n");
    printf("1. Push in Stack\n");
    printf("2. Pop from Stack\n");
    printf("3. Peek into Stack\n");
    printf("4. Undo Last Pop\n");
    printf("5. Print Stack + Undo Stack\n");
    printf("6. Exit\n\n");

    printf("Choice: ");
    scanf("%d",&choice);
    }while(choice!=6);

    /* push(stack,5);
    push(stack,7);
    push(stack,2);
    push(stack,3);
    push(stack,8);
    push(stack,0);
    push(stack,6);

    printf("Stack:\n");
    printStack(stack);
    printf("Redo Stack:\n");
    printStack(stack->Redo);

    
    

    printf("Stack:\n");
    printStack(stack);
    printf("Redo Stack:\n");
    printStack(stack->Redo);

    popped=pop(stack);
    printf("Popped=%d\n",popped->data);
    
    printf("Stack:\n");
    printStack(stack);
    printf("Redo Stack:\n");
    printStack(stack->Redo);

    undo(stack);

    printf("Stack:\n");
    printStack(stack);
    printf("Redo Stack:\n");
    printStack(stack->Redo); */
    return 0;
}