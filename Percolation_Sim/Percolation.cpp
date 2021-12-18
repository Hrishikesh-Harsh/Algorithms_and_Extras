# include <bits/stdc++.h>
using namespace std;

class Percolate
{
    public:
    int *id;
    int *size;
    char **v;
    int n;
    int count_open=0;

    Percolate(int x)
    {
        id= new int[x*x+2];
        size= new int[x*x+2];
        v = new char*[x];
        for(int i = 0; i < x; ++i) {
            v[i] = new char[x];
    }

        for(int i=0;i<x;i++)
        {
            for(int j=0;j<x;j++)
            {
                v[i][j]='X';
            }
        }
        fill_n(id, x*x , -5);
        id[x*x]=-1;                 //Initialising every ID to -5 except last 2 which are reserved for virtual parent of top and bottom rows
        id[x*x+1]=-2;
        fill_n(size, x*x+2, 0);
        n=x;
    }
    void unlock(int r,int c)          //to unlock a particular site and connect it to adjacent unlocked sites
    {
        if (is_open(r,c))
        return;

        id[site_num(r,c)]=site_num(r,c);
        size[site_num(r,c)]+=1;
        count_open+=1;
        cout<<"("<<r<<","<<c<<")"<<" Opened\n"; v[r][c]='_';
        if(r==0)
        {
            id[site_num(r,c)]=-1; 
            size[n*n]+=1; 
        }
        if(r==(n-1))
        {
            id[site_num(r,c)]=-2; 
            size[n*n+1]+=1; 
        }
        if(is_open(r-1,c))
        {
            combine(site_num(r,c), site_num(r-1,c));
            cout<<"Combined: "<<"("<<r<<","<<c<<")"<<" and "<<"("<<r-1<<","<<c<<")\n";
        }
        if(is_open(r+1,c))
        {
            combine(site_num(r,c), site_num(r+1,c));
            cout<<"Combined: "<<"("<<r<<","<<c<<")"<<" and "<<"("<<r+1<<","<<c<<")\n";
        }
        if(is_open(r,c+1))
        {
            combine(site_num(r,c), site_num(r,c+1));
            cout<<"Combined: "<<"("<<r<<","<<c<<")"<<" and "<<"("<<r<<","<<c+1<<")\n";
        }
        if(is_open(r,c-1))
        {
            combine(site_num(r,c), site_num(r,c-1));
            cout<<"Combined: "<<"("<<r<<","<<c<<")"<<" and "<<"("<<r<<","<<c-1<<")\n";
        }
        return;
    }
    void combine(int p,int q)       //performs Union of two elements (sites)
    {
        int i=root(p/n, p%n);
        int j=root(q/n, q%n);

        if(i==j)
        return;

        if(i==-1)
        i=n*n;

        if(j==-1)
        j=n*n;
    
        if(i==-2)
        i=n*n+1;
    
        if(j==-2)
        j=n*n+1;

        if(size[i]<size[j])
        {
            if(j==n*n)
            {
                id[i]=-1;
            }
            else if(j==n*n+1)
            {
                id[i]=-2;
            }
            else
            {
                id[i]=j;
            }
            size[j]+=size[i];
        }
        else if(size[i]==size[j] && (i==(n*n) || i==(n*n+1)) || j==(n*n) || j==(n*n+1))
        {
            if(i==(n*n))
            {
                id[j]= -1;
                size[i]+=size[j];
            }
            if(i==(n*n+1))
            {
                id[j]= -2;
                size[i]+=size[j];
            }
            if(j==(n*n))
            {
                id[i]= -1;
                size[j]+=size[i];
            }
            if(j==(n*n+1))
            {
                id[i]= -2;
                size[j]+=size[i];
            }
        }
        else
        {
            if(i==n*n)
            {
                id[j]=-1;
            }
            else if(i==n*n+1)
            {
                id[j]=-2;
            }
            else
            {
                id[j]=i;
            }
            size[i]+=size[j];
        }
        return;
    }
    int site_num(int r, int c)          //returns the site number (0 to n^2-1)
    {
        return r*n+c;
    }
    bool is_open(int r, int c)          //checks if a site is open/unlocked
    {
        if(r<0 || c<0 || r>=n || c>=n)
        return false;

        if (id[site_num(r,c)]==-5)
        return false;
        else
        return true;
    }
    int root(int r, int c)              //returns root of an element/site(top most parent)
    {
        int i= site_num(r,c);
        while(id[i]!=-1 && id[i]!=i && id[i]!=-2)
        {
            i=id[i];
        }
    return id[i];
    }
    void display()
    {
        for (int i=0;i<n*n+2;i++)
        {
            cout<<id[i]<<",";
        
        }
        cout<<"\n";
        for (int i=0;i<n*n+2;i++)
        {
            cout<<size[i]<<",";
        
        }
        cout<<"\n";
        cout<<count_open<<endl;
    }
};

int main()
{
    
    int x;
    cout<<"Enter value of n for n X n grid\n";
    cin>>x;
    Percolate ob(x);
    cout<<"Keep taking row and column to unlock\n";
    int row,column;
    double p;
    srand((unsigned) time(0));
    while((ob.id[ob.n*ob.n])!=(ob.id[(ob.n*ob.n)+1]))             //keeps unlocking random sites until the grid can percolate
    {
        row= rand()%x;
        column= rand()%x;
        cout<<"---------------------\n";
        ob.unlock(row, column);
        cout<<"---------------------\n";
        
    }
    cout<<"                        "<<endl;
    cout<<"X: Closed site\n";
    cout<<"_: Open site\n";
    cout<<"\n";
    cout<<"The Grid:\n";

        for(int i=0;i<x;i++)
        {
            for(int j=0;j<x;j++)
            {
                cout<<ob.v[i][j]<<" ";
            }
            cout<<"\n";
        }
    cout<<"\n";
    cout<<"Number of sites opened = "<<ob.count_open<<endl;
    cout<<"Fraction of sites opened before grid can percolate = "<<(double)ob.count_open/(x*x)<<endl; 
    return 0;   

    /*The Fraction gives a rough idea of the probability with which 
      the sites may be opened such that the grid can percolate*/                                                                                      
}
