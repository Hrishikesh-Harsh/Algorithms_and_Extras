import java.util.*;

import javax.lang.model.util.ElementScanner6;

import java.io.*;

public class Bellman
{
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);

            System.out.print("No of Vertices: ");
            int n=sc.nextInt();

            int adjacency[][]=new int[n][n];

            System.out.println();
            System.out.println("Enter weights of edges in Adjacency Matrix:");

            // for(int i=0;i<n;i++)
            // {
            //     for(int j=0;j<n;j++)
            //     {
            //         adjacency[i][j]=-1;
            //     }
            //     //System.out.println();
            // }
            /*Enter Weights for Directed Graph (can be negative)*/
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    // if(adjacency[j][i]!=-1)
                    //     adjacency[i][j]=adjacency[j][i];       
                    // else
                    // {
                    System.out.print((char)(i+65)+" and "+(char)(j+65)+":");
                    adjacency[i][j]=sc.nextInt();
                    //}
                }
            }
            System.out.println();
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    System.out.print(adjacency[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();

            int flag=0;
            HashMap <Character, String> shortest_path=new HashMap <Character, String> ();

            for (int i=0;i<n;i++)
            {
                if(i==0)                                    //Putting distance to every vertex as undefined except first vertex
                shortest_path.put((char)(i+65), 0+"");
                else
                shortest_path.put((char)(i+65), "-");
            }

            for(int i=1;i<n;i++)            //At most n-1 iterations
            {
                flag=0;
                for(int j=0;j<n;j++)            //Through all vertices
                {
                    if(shortest_path.get((char)(j+65)).equals("-"))     
                    continue;

                    for(int k=0;k<n;k++)            //Checking all adjacent vertices for a given vertex 
                    {
                        if(adjacency[j][k]!=0)      //If vertex is adjacent
                        {
                            if(shortest_path.get((char)(k+65)).equals("-"))     //If distance is undefined
                            {
                                int new_dist=adjacency[j][k]+Integer.parseInt(shortest_path.get((char)(j+65)));
                                shortest_path.replace((char)(k+65),new_dist+"");            //Update distance
                                flag=1;
                            }
                            else
                            {
                                int new_dist=adjacency[j][k]+Integer.parseInt(shortest_path.get((char)(j+65)));     //If a shorter path found
                                if(new_dist<Integer.parseInt(shortest_path.get((char)(k+65))))
                                {
                                    shortest_path.replace((char)(k+65), new_dist+"");       //Update distance
                                    flag=1;
                                }
                            }
                        }
                    }
                }
                if(flag==0)     //If no distance was updated in this iteration, no need to iterate further
                break;
            }
        System.out.println("\nShortest Distances (start vertex:A) Using Bellman-Ford Algorithm:\n");
        
        Iterator it = shortest_path.entrySet().iterator();
  
        while (it.hasNext()) {
            Map.Entry mapElement = (Map.Entry)it.next();
            String dist = (String)mapElement.getValue();
            System.out.println(mapElement.getKey() + " : " + dist);
        }

            //Detecting Negative Cycles 
            flag=0;
                for(int j=0;j<n;j++)
                {
                    for(int k=0;k<n;k++)
                    {
                        if(adjacency[j][k]!=0)
                        {
                            int new_dist=adjacency[j][k]+Integer.parseInt(shortest_path.get((char)(j+65)));
                            if(new_dist<Integer.parseInt(shortest_path.get((char)(k+65))))
                            {
                                //shortest_path.replace((char)(k+65), new_dist+"");
                                flag=1;
                            }
                        }
                    }
                }
        if(flag==0)
        System.out.println("\nNo negative Cycles were Detected");
        else
        {
        System.out.println("\nNOTE: Negative cycle was Detected..!!");
        System.out.println("The shortest distances of the vertices reachable by the negative cycle vertices is -Inf..");
        }

    }
    
}
