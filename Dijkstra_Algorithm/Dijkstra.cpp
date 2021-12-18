# include <bits/stdc++.h>
using namespace std;

bool sortbysec(const pair<char,int> &a,const pair<char,int> &b)
{
    return (a.second < b.second);
}

int main()
{
    cout<<"\n\n--------------Dijkstra's Way of Finding Shortest Path For Postive-Weighted Graphs---------------\n";
    cout<<"\n";
    int n;
    cout<<"Enter no. of Vertices in the Graph\n";\
    cin>>n;
    int adjacency[n][n];

    cout<<"\nEnter weights between two Vertices. Enter ZERO if vertices are not adjacent or there is no directed edge.\n";
    //cout<<"CheckPoint 1\n";
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            adjacency[i][j]=-1;
        }
    }
    //cout<<"CheckPoint 2\n";

    /*Intialising the Matrix Symmetrically (only for undirected graphs)*/
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            //if(adjacency[j][i]!=-1)
                //adjacency[i][j]=adjacency[j][i];         /*Uncomment this portion to perform the algorithm on undirected graphs*/
            //else
            //{
                cout<<(char)(i+65)<<" and "<<(char)(j+65)<<":";
                cin>>adjacency[i][j];
            //}
        }
    }
    //cout<<"CheckPoint 3\n";
    cout<<"\nThe Adjacency Matrix:\n";
    cout<<"\n";
    for(int i=0;i<n;i++)
    {
        for(int j=0;j<n;j++)
        {
            cout<<adjacency[i][j]<<" ";
        }
        cout<<"\n";
    }
    //cout<<"CheckPoint 4\n";
    char start;
    cout<<"\nEnter your Start Vertex: ";
    cin>>start;

    vector <char> visited;
    vector <char> unvisited;

    //Adding all vertices here
    for(int i=0;i<n;i++)
    {
        unvisited.push_back((char)(i+65));
    }
    char last_visited[n];
    //int shortest_distance[n];
    vector <pair <char,int>> short_dist;

    //Vector to keep track of shortest path to a particular vertex
    for(int i=0;i<n;i++)
    {
        if(((int)(start)-65)==i)
        short_dist.push_back(make_pair(start, 0));
        else
        short_dist.push_back(make_pair((char)(i+65), -1));
    }
    char current=start;
    int index=(int)(current)-65;
    last_visited[index]=start;
    // for(int i=0;i<n;i++)
    // {
    //     cout<<last_visited[i]<<" ";
    // }
    vector <char>::iterator it;
    //it=unvisited.begin();

    // for(int i=0;i<n;i++)
    // {
    //     cout<<short_dist[i].first<<" "<<short_dist[i].second<<endl;
    // }
    vector <pair<char,int>> duplicate;
    //duplicate.assign(short_dist.begin(), short_dist.end());

    //Keep visiting vertices till all have been visited
    while(unvisited.empty()!=true)
    {
        //Index of Current vertex
        index=(int)(current)-65;

        //Added to list of visited vertices
        visited.push_back(current);
        it=find(unvisited.begin(),unvisited.end(),current);

        //removed from list of unvisited vertices
        unvisited.erase(it);

        //int min=adjacency[index][0];
        for(int i=0;i<n;i++)
        {
            //Check for path only if it is a neighbour
            if(adjacency[index][i]!=0)
            {
                //If path is infinity(here "-1"), initialise it to first path found
                if(short_dist[i].second==-1)
                {
                    short_dist[i].second=short_dist[index].second+adjacency[index][i];
                    last_visited[i]=current;
                }
                else
                {
                    //Checking for better path
                    int acc_dist=short_dist[index].second+adjacency[index][i]; //Shortest distance upto current vertex + Distance to adjacent one
                    if(acc_dist<short_dist[i].second) //If it is shorter update shortest path
                    {
                        short_dist[i].second=acc_dist;
                        last_visited[i]=current;
                    }
                }
                
            }
        }
        duplicate.assign(short_dist.begin(), short_dist.end()); //Duplicate to preserve original
        sort(duplicate.begin(),duplicate.end(),sortbysec); //Sorting by path lengths
        int flag=0;int i=0;
        while(flag==0)
        {
            if(duplicate[i].second==-1 || duplicate[i].second==0) //Ignoring infinity Paths and no Paths
            {
                i++;
                continue;
            }
            else
            {
                if(find(visited.begin(),visited.end(),duplicate[i].first)!=visited.end()) //Checking if the vertex has been visited already
                {
                    if(i==duplicate.size()-1) //If all vertices have been visited
                    {break;}

                    i++;
                    //continue;
                }
                else
                {
                    flag=1; //Smallest path value found
                    current=duplicate[i].first; //Next vertex to visit is the one with shortest path from start vertex
                }
            }
        }
        duplicate.assign(short_dist.begin(),short_dist.end());
        //More code to come
    }
    cout<<"\n";
    cout<<"Start Vertex: "<<start<<"\n";
    cout<<"\nVertex\t"<<"Shortest_Path\t"<<"Last_Vertex\n";
    cout<<"\n";
    for(int i=0;i<n;i++)
    {
        cout<<short_dist[i].first<<"\t\t"<<short_dist[i].second<<"\t\t"<<last_visited[i]<<endl;
    }
    return 0;
}
