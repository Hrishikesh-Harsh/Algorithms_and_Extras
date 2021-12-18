# include<stdio.h>
# include<stdlib.h>

struct Node
{
  int data;
  struct Node *next;
} *start = NULL;

typedef struct Node node;

void
append (int x)
{
  node *new = malloc (1 * sizeof (node));
  new->data = x;
  if (start == NULL)
    {
      start = new;
      new->next = NULL;
    }
  else
    {
      node *temp = start;
      while (temp->next != NULL)
	{
	  temp = temp->next;
	}
      temp->next = new;
      new->next = NULL;
    }
}

void
addStart (int x)
{
  node *new = malloc (1 * sizeof (node));
  new->data = x;
  if (start == NULL)
    {
      start = new;
      new->next = NULL;
    }
  else
    {
      new->next = start;
      start = new;
    }
}

void
addAfter (int r, int x)
{
  node *new = malloc (sizeof (node));
  new->data = x;
  node *temp = start;
  while ((temp->data) != r && temp->next != NULL)
    {
      temp = temp->next;
    }
  new->next = temp->next;
  temp->next = new;
}

void
del(int x)
{
  if (start->data == x)
    {
      node *temp = start;
      start = start->next;
      free (temp);
    }
  else
    {
      node *prev = start;
      node *temp = start->next;

      while (temp->data != x && temp->next != NULL)
	{
	  prev = temp;
	  temp = temp->next;
	}
      prev->next = temp->next;
      printf ("Removed element:%d\n", x);
      free (temp);
    }
}

void
search (int x)
{
  node *temp = start;
  int flag = 0;
  while (temp != NULL)
    {
      if (temp->data == x)
	flag = 1;
      temp = temp->next;

    }
  if (flag == 1)
    printf ("Element %d found!\n", x);
  else
    printf ("Element %d not found!\n", x);

}

int
get_nth_node (int n)
{
  node *temp = start;
  for (int i = 1; i < n; i++)
    temp = temp->next;

  return (temp->data);
}

int
get_nth_node_from_end (int l, int n)
{
  int n_eff = l - n + 1;
  node *temp = start;
  for (int i = 1; i < n_eff; i++)
    temp = temp->next;

  return (temp->data);
}

int
length ()
{
  node *temp = start;
  int c = 0;
  while (temp != NULL)
    {
      c = c + 1;
      temp = temp->next;
    }
  return c;
}

void
remove_duplicate ()
{
  node *t1 = start;
  while (t1 != NULL)
    {
      node *t2_p = t1;
      node *t2_c = t1->next;

      while (t2_c != NULL)
	{
	  if (t2_c->data == t1->data)
	    {
	      t2_p->next = t2_c->next;
	      t2_c = t2_p->next;
	    }
	  else
	    {
	      t2_p = t2_c;
	      t2_c = t2_c->next;
	    }

	}
      t1 = t1->next;
    }
}

void
check_Palindrome (int l)
{
  int p[l];
  int i = 0;

  node *temp = start;
  while (temp != NULL)
    {
      p[i] = temp->data;
      i = i + 1;
      temp - temp->data;
    }
  int flag = 0;
  for (int i = 0; i < l / 2; i++)
    {
      if (p[i] != p[l - 1 - i])
	flag = 1;
    }
  if (flag == 1)
    printf ("Not a Palindrome Linked List");
  else
    printf ("Palindrome Linked List");
}

void
anti_rotation (int k)
{
  int i;
  if (k == 0)
    return;

  node *curr = start;
  while (curr->next != NULL)
    {
      curr = curr->next;
    }
  curr->next = start;

  curr = start;
  for (int i = 0; i < k - 1; i++)
    curr = curr->next;

  start = curr->next;
  curr->next = NULL;
}

void
swap (int x, int y)
{
  node *f1,*f2;
  node *temp=start;
  while(temp!=NULL)
  {
      if(temp->data==x)
      f1=temp;
      temp=temp->next;
  }
  node *s=start;
    while(s!=NULL)
  {
      if(s->data==y)
      f2=s;
      s=s->next;
  }
  int t=f1->data;
  f1->data=f2->data;
  f2->data=t;
  
}
void even_odd()
{
    node *temp=start;
    node *even=NULL;
    node *odd=NULL;
    node *t1,*t2;
    
    while(temp!=NULL)
    {
        if(temp->data%2==0)
        {
            if(even==NULL)
            {
                even=temp;
                t1=temp;
            }
            else
            {
                t1->next=temp;
                t1=temp;
            }
        }
        else
        {
            if(odd==NULL)
            {
                odd=temp;
                t2=temp;
            }
            else
            {
                t2->next=temp;
                t2=temp;
            }
        }
        temp=temp->next;
    }
    t2->next=NULL;
    t1->next=odd;
    start=even;
}
void reverse()
{
    node *prev=start;
    node *curr=start->next;
    node *suc=curr->next;
    
    while(curr!=NULL)
    {
        curr->next=prev;
        prev=curr;
        curr=suc;
        if(curr==NULL)
        break;
        suc=suc->next;
    }
    start->next=NULL;
    start=prev;
}
void
display ()
{
  node *temp = start;
  while (temp != NULL)
    {
      if (temp->next == NULL)
	printf ("%d", temp->data);
      else
	printf ("%d=>", temp->data);
      temp = temp->next;
    }
  printf ("\n");
}

int main()
{
    
    int y;int k;int len;int n;int a,b,c;
    char ch='Y';
    
    while(ch=='Y' || ch=='y')
    {
    printf("Welcome to Linked Lists!\n");
    printf("----------------------------------\n");
    printf("1. To Append\n");
    printf("2. To Add at Start\n");
    printf("3. To Add after a Node\n");
    printf("4. To Search a Node\n");
    printf("5. To Delete a Node \n");
    printf("6. To get the nth Node\n");
    printf("7. To get the nth Node from the end\n");
    printf("8. To remove Duplicates\n");
    printf("9. To rotate list anti-clockwise by k times\n");
    printf("10. To check for a Palindrome List\n");
    printf("11. To Find length of the List\n");
    printf("12. To Swap two Nodes\n");
    printf("13. To Sort the List by Even-Odd Values\n");
    printf("14. To Reverse the List\n");
    printf("15. To Display the List\n");
    printf("----------------------------------\n");
    
    printf("Enter the choice\n");
    scanf("%d", &y);
    printf("\n");
    
    switch(y)
    {
        case 1:
        printf("Enter value:\n");
        scanf("%d",&n);
        append(n);
        printf("\n");
        display();
        printf("----------------------------------\n");
        break;
        
        case 2:
        printf("Enter value:\n");
        scanf("%d",&n);
        addStart(n);
        printf("\n");
        display();
        printf("----------------------------------\n");
        break;
        
        case 3:
        printf("Enter value after which Node to be added:\n");
        int z;
        scanf("%d", &z);
        printf("Enter value:\n");
        scanf("%d", &n);
        addAfter(z,n);
        printf("\n");
        display();
        printf("----------------------------------\n");
        break;
        
        case 4:
        printf("Enter value to be searched:\n");
        scanf("%d",&n);
        search(n);
        printf("----------------------------------\n");
        break;
        
        case 5:
        printf("Enter value to be deleted:\n");
        scanf("%d",&n);
        del(n);
        printf("\n");
        display();
        printf("----------------------------------\n");
        break;
        
        case 6:
        printf("Enter value:\n");
        scanf("%d",&n);
        printf("%dth node value=%d\n", n, get_nth_node(n));
        printf("----------------------------------\n");
        break;
        
        case 7:
        printf("Enter value:\n");
        scanf("%d",&n);
        printf("%dth node value from the end=%d\n", n, get_nth_node_from_end(length(),n));
        printf("----------------------------------\n");
        break;
        
        case 8:
        printf("Before:\n");
        display();
        remove_duplicate();
        printf("After:\n");
        display();
        printf("----------------------------------\n");
        break;
        
        case 9:
        
        printf("Enter value of k:\n");
        scanf("%d", &k);
        printf("Before:\n");
        display();
        anti_rotation(k);
        printf("After:\n");
        display();
        break;

        case 10:
        len=length();
        check_Palindrome(len);
        printf("----------------------------------\n");
        break;
        
        case 11:
        printf("Length of the Linked List=%d\n", length());
        printf("----------------------------------\n");
        break;
        
        case 12:
        printf("Enter the values to be swapped\n");
        scanf("%d", &a);
        scanf("%d", &b);
        swap(a,b);
        printf("\n");
        display();
        printf("----------------------------------\n");
        break;
        
        case 13:
        even_odd();
        printf("\n");
        display();
        printf("----------------------------------\n");;
        break;
        
        case 14:
        printf("Before:\n");
        display();
        reverse();
        printf("After:\n");
        display();
        printf("----------------------------------\n");
        break;
        
        case 15:
        display();
        printf("----------------------------------\n");
        break;
        
        default:
        printf("Enter again\n");
    }
    printf("Enter \'Y\' to Continue and \'N\' to exit these operations\n");
    ch=getchar();
    ch=getchar();
    
    }
    return 0;
}
