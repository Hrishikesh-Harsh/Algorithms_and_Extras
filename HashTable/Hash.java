import java.util.*;
import java.io.*;
import java.nio.file.SecureDirectoryStream;

//import javax.lang.model.util.ElementScanner6;
public class Hash
{
    static LinkedList <Integer> chainTable[];
    static int linearProbeTable[]; static int elementsInLinearProbeTable;
    static int quadraticProbeTable[]; static int elementsInQuadraticProbeTable;
    static Scanner sc=new Scanner(System.in);
    static int r;

    //static int size;

    public static boolean isPrime(int n)        //Checks if a number is Prime
    {
        int c=0;
        for(int i=2;i<=(int)Math.sqrt(n);i++)
        {
            if(n%i==0)
            {
                c++;
                break;
            }
        }
        if(c>0)
        return false;
        else
        return true;
    }
    public static int find_nearest_prime(int n)     //Returns next Higher Prime Number to the argument(inclusive)
    {
        int flag=0; boolean response;
        while(flag==0)
        {
            response=isPrime(n);
            n++;
            if(response)
            flag=1;
        }
        return (n-1);
    }
    public static void chaining(int n)              //Performs the Hash Table creation through Chaining Mechanism
    {
        // Scanner sc=new Scanner(System.in);
        // int num=(int)Math.ceil((1.3*n));
        // int size=find_nearest_prime(num);
        chainTable=new LinkedList[n];
        System.out.println("Elements (Only Non-Negative):");

        for(int i=0;i<n;i++)
        {
            int x=sc.nextInt();
            int index=x%n;                      //Hash Indexing Function f(x)=x MOD (number of elements)

            if (chainTable[index] == null) {
                chainTable[index] = new LinkedList<Integer>();
            }
            chainTable[index].add(x);       //If index already has an element, it just adds(chains) current element to the LinkedList at that index
        }
        System.out.println();
        display(chainTable);
        //sc.close();
        return;
    }
    public static void display(LinkedList <Integer> h[])    
    {
        System.out.println("The Hash Table (By Chaining):");
        System.out.println();
        for(int i=0;i<h.length;i++)
        {
            System.out.println(i+" : "+h[i]);
        }
        System.out.println();
        return;
    }
    public static void linear_probing(int n)    //Performs the Hash Table creation through Linear Probing Mechanism (f(inc)=inc)
    {
        // Scanner sc=new Scanner(System.in);
        int num=(int)Math.ceil(1.3*n);      
        int size=find_nearest_prime(num);       //Conventionally, the size of the Table for n elements should be nearest Prime to m(m=1.3xn)

        linearProbeTable=new int[size];
        System.out.println("Elements (Only Non-Negative):");
        Arrays.fill(linearProbeTable,-1);
        int inc=1;
        int index;
        for(int i=0;i<n;i++)
        {
            inc=1;
            int x=sc.nextInt();
            index=x%n;                      //Hash Indexing Function f(x)=x MOD (number of elements)

            while(linearProbeTable[index]!=-1)
            {
                index=(index+inc)%n;        //If index is occupied, it looks for another index through Linear Probing
                inc+=1;
            }

            linearProbeTable[index]=x;
        }
        System.out.println();
        display(linearProbeTable,1);
        //sc.close();
        return;
    }
    public static void display(int h[], int c)
    {
        if(c==1)
        System.out.println("The Hash Table (By Linear Probing):");
        else
        System.out.println("The Hash Table (By Quadratic Probing):");

        System.out.println();
        for(int i=0;i<h.length;i++)
        {
            System.out.println(i+" : "+h[i]);
        }
        System.out.println();
        return;
    }
    public static void quadratic_probing(int n)    //Performs the Hash Table creation through Quadratic Probing Mechanism (f(inc)=inc^2)
    {
        // Scanner sc=new Scanner(System.in);
        int num=(int)Math.ceil(1.3*n);
        int size=find_nearest_prime(num);

        quadraticProbeTable=new int[size];
        System.out.println("Elements (Only Non-Negative):");
        Arrays.fill(quadraticProbeTable,-1);
        int inc=1;
        int index;
        for(int i=0;i<n;i++)
        {
            inc=1;
            int x=sc.nextInt();
            index=x%n;                  //Hash Indexing Function f(x)=x MOD (number of elements)

            while(quadraticProbeTable[index]!=-1)
            {
                index=(index+inc*inc)%n;            //If index is occupied, it looks for another index through Quadratic Probing
                inc+=1;
            }

            quadraticProbeTable[index]=x;
        }
        System.out.println();
        display(quadraticProbeTable,2);
        //sc.close();
        return;
    }
    public static void search(int x, LinkedList <Integer> h[])
    {
        System.out.println();
        int n=h.length;
        int index=x%n;

        if(chainTable[index]==null)
        System.out.println("Element Not Found");
        else
        {
            int id=h[index].indexOf(x);
            if(id==-1)
            System.out.println("Element Not Found");
            else
            {
            System.out.println("Element Found...! ");
            System.out.println("[index: "+index+", position: "+id+"]");
            }
        }
        System.out.println();
        return;
    }
    public static void delete(int x, LinkedList <Integer> h[])
    {
        System.out.println();
        int n=h.length;
        int index=x%n;

        if(chainTable[index]==null)
        System.out.println("Element Not Found");
        else
        {
            int id=h[index].indexOf(x);
            if(id==-1)
            System.out.println("Element Not Found");
            else
            {
            System.out.println("Element Deleted...! ");
            System.out.println("[index: "+index+", position: "+id+"]");
            chainTable[index].remove(id);
            }
        }
        System.out.println();
        return;
    }
    public static void search(int x, int h[], int w)
    {
        System.out.println();
        //int n=h.length;
        int index;
        if(w==1)
        index=x%elementsInLinearProbeTable;
        else
        index=x%elementsInQuadraticProbeTable;

        int initial_index=index; int c=1;
        int element=h[index];
        int flag=0;
        int inc=1;
        while(element!=x && c!=2)       //Until element is found or same index is repeated, keep looping
        {
            if(element==-1)
            {flag=1;
            break;}
            
            if(w==1)
            index=(index+inc)%elementsInLinearProbeTable;
            else
            index=(index+inc*inc)%elementsInQuadraticProbeTable;
            
            element=h[index];

            if(index==initial_index)
            c+=1;

            inc+=1;
        }

        //System.out.println(h[5]);
        if(flag==1 || c==2)
        System.out.println("Element Not Found");
        else
        {
            System.out.println("Element Found...! ");
            System.out.println("[index: "+index+"]");
        }

    }
    public static void delete(int x, int h[], int w)
    {
        System.out.println();
        //int n=h.length;
        int index;
        if(w==1)
        index=x%elementsInLinearProbeTable;
        else
        index=x%elementsInQuadraticProbeTable;

        int initial_index=index;
        int element=h[index];
        int flag=0;
        int inc=1; int c=1;
        while(element!=x && c!=2)       ////Until element is found or same index is repeated, keep looping
        {
            if(element==-1)
            {flag=1;
            break;}
            
            if(w==1)
            index=(index+inc)%elementsInLinearProbeTable;
            else
            index=(index+inc*inc)%elementsInQuadraticProbeTable;

            element=h[index];

            if(index==initial_index)
            c+=1;

            inc+=1;
        }

        //System.out.println(h[5]);
        if(flag==1 || c==2)
        System.out.println("Element Not Found");
        else
        {
            System.out.println("Element Deleted...! ");
            System.out.println("[index: "+index+"]");
            
            if(w==1)
            linearProbeTable[index]=-1;
            else
            quadraticProbeTable[index]=-1;
        }
    }
    public static void perform()
    {
        
        System.out.println();
        System.out.println("1. Insert");
        System.out.println("2. Search");
        System.out.println("3. Delete");
        System.out.println("4. Display");
        System.out.println();
        System.out.println("[Please be careful about the above choice]");
        System.out.println("[Do not choose 2, 3 or 4 without any Insertion Operations Performed beforehand]");
        
        System.out.println();

        System.out.print("Choice: ");
        int choice=sc.nextInt();
        switch(choice)
        {
            case 1:
                System.out.println();
                System.out.println("--------Element Insertion---------");
                System.out.println();

                System.out.println();
                System.out.println("1. By Chaining");
                System.out.println("2. By Linear Probing");
                System.out.println("3. By Quadratic Probing");

                System.out.println();
                System.out.print("Choice: ");
                int ch=sc.nextInt();
                System.out.println();
                if(ch==1)
                {
                    System.out.print("Number of Elements: ");
                    int n=sc.nextInt();
                    chaining(n);
                }
                else if(ch==2)
                {
                    System.out.print("Number of Elements: ");
                    elementsInLinearProbeTable=sc.nextInt();
                    linear_probing(elementsInLinearProbeTable);
                }
                else if(ch==3)
                {
                    System.out.print("Number of Elements: ");
                    elementsInQuadraticProbeTable=sc.nextInt();
                    quadratic_probing(elementsInQuadraticProbeTable);
                }
                break;
            
            case 2:
                System.out.println();
                System.out.println("--------Element Search---------");
                System.out.println();
                
                System.out.print("Element: ");
                int e=sc.nextInt();

                System.out.println();
                System.out.println("1. In Chaining Table");
                System.out.println("2. In Linear Probe Table");
                System.out.println("3. In Quadratic Probe Table");
                System.out.println();
                System.out.println("[Please be careful about the above choice]");
                System.out.println("[Do not choose uninitialised Hash Table]");

                System.out.println();
                System.out.print("Choice: ");
                int s=sc.nextInt();

                if(s==1)
                search(e, chainTable);
                else if(s==2)
                search(e, linearProbeTable, 1);
                else if(s==3)
                search(e, quadraticProbeTable, 2);
                break;
            
            case 3:
                System.out.println();
                System.out.println("--------Element Delete---------");
                System.out.println();
                
                System.out.print("Element: ");
                int x=sc.nextInt();

                System.out.println();
                System.out.println("1. In Chaining Table");
                System.out.println("2. In Linear Probe Table");
                System.out.println("3. In Quadratic Probe Table");
                System.out.println();
                System.out.println("[Please be careful about the above choice]");
                System.out.println("[Do not choose uninitialised Hash Table]");

                System.out.println();
                System.out.print("Choice: ");
                int c=sc.nextInt();

                if(c==1)
                delete(x, chainTable);
                else if(c==2)
                delete(x, linearProbeTable, 1);
                else if(c==3)
                delete(x, quadraticProbeTable, 2);
                break;
            
            case 4:
                System.out.println();
                System.out.println("--------Table Display---------");
                System.out.println();

                System.out.println();
                System.out.println("1. Chaining Table");
                System.out.println("2. Linear Probe Table");
                System.out.println("3. Quadratic Probe Table");
                System.out.println();
                System.out.println("[Please be careful about the above choice]");
                System.out.println("[Do not choose uninitialised Hash Table]");

                System.out.println();
                System.out.print("Choice: ");
                int d=sc.nextInt();

                if(d==1)
                display(chainTable);
                else if(d==2)
                display(linearProbeTable,1);
                else if(d==3)
                display(quadraticProbeTable,2);
                break;
        }
        return;
    }
    public static void main(String args[])
    {
        do
        {
            perform();                          

            System.out.println("\n1. Perform More Actions");
            System.out.println("2. Exit");

            System.out.print("\nChoice: ");

            r=sc.nextInt();
              
        }while(r==1);
    }
}
