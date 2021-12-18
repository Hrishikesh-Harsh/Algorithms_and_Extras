import java.util.*;

import javax.lang.model.util.ElementScanner6;

import java.io.*;

public class Search_Graph
{
    public static HashMap<Character, Integer> sortByValue(HashMap<Character, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Character, Integer> > list =
               new LinkedList<Map.Entry<Character, Integer> >(hm.entrySet());
 
        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer> >() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
         
        // put data from sorted list to hashmap
        HashMap<Character, Integer> temp = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static void depth_first(int n, char start, int index, int adjacency[][])
    {
        Stack <Character> last_visited=new Stack <Character> ();
        ArrayList <Character> visited= new ArrayList <Character> ();
        char current=start;
        HashMap <Character,Integer> adj=new HashMap <Character,Integer> ();
        Map <Character,Integer> adj_copy;

        while(visited.size()!=n) //all vertices have been visited or not
        {
            index=(int)(current)-65;        //The adjacency row of current vertex
            if(!visited.contains(current))
                visited.add(current);           //It is now added to visited list
            last_visited.push(current);     //Stack to maintain last visited vertex to enable backtracking

            int flag=0;                     //Flag to signify if any more unvisited vertices are neighbours or not
            for(int i=0;i<n;i++)
            {
                //System.out.println(index);
                adj.put((char)(i+65),adjacency[index][i]);      //Putting that adjacency row in HashMap
            }
            adj_copy=sortByValue(adj);              //Sorting adjacency weights to decide which vertex to visit first
            //Collections.sort(adj_copy);
            Iterator it=adj_copy.entrySet().iterator();
            int j=0;
            while(it.hasNext())
            {
                Map.Entry mapElement = (Map.Entry)it.next();
                int value = (int)mapElement.getValue();

                if(value==0)
                {
                    continue;
                }
                //System.out.println("j="+j);
                if(value!=0)
                {
                    if(visited.contains((char)mapElement.getKey()))
                    {
                        //j+=1;
                        continue;                                               //if neighbour already present,then skip
                    }
                    else
                    {
                        //System.out.println("j="+j);
                        flag=1;
                        current=(char)mapElement.getKey();                  //if unvisited neighbour with least weight, visit it
                        break;
                    }
                }
                //j++;
            }
            if(flag==0)                                                     //if no unvisited neighbours left, backtrack to last visited vertex
            {
                char x=last_visited.pop();
                char c=last_visited.peek();

                current=c;
            }
            adj.clear();
            adj_copy.clear();

        }
        System.out.println("\nDepth First Traversal Order:\n");
        for(int i=0;i<n;i++)
        {
            if(i==(n-1))
            System.out.println(visited.get(i));
            else 
            System.out.print(visited.get(i)+"->");
        }
        System.out.println();
    }
    
    public static void breadth_first(int n, char start, int index, int adjacency[][])
    {
        Queue <Character> queue=new LinkedList <Character> ();
        ArrayList <Character> visited=new ArrayList <Character> ();
        /*Queue Structure to maintain order of visiting neighbours of vertices 
          as they have appeared while visiting */
        queue.add(start);               //Add first vertex to Queue
        //visited.add(start);
        index=(int)(start)-65;
        char current=start;
        do                              //Loop until no more vertices left in Queue
        {
            index=(int)(current)-65;
            visited.add(current);           //Add current vertex to visited list of Vertices
            //queue.add(current);

            for(int i=0;i<n;i++)            //Add vertex to Queue only if it carries a weighted edge and it isn't present in both Queue and Visited List
            {
                if(adjacency[index][i]!=0 && !visited.contains((char)(i+65)) && !queue.contains((char)(i+65)))
                queue.add((char)(i+65));
            }
            //System.out.println(queue);
            char w=queue.remove();              //Removing last visited vertex 
            if(!queue.isEmpty())
            current=queue.peek();               //Visiting next vertex in Queue order
        }
        while(!queue.isEmpty());
        
        System.out.println("\nBreadth First Traversal Order:\n");
        for(int i=0;i<n;i++)
        {
            if(i==(n-1))
            System.out.println(visited.get(i));
            else 
            System.out.print(visited.get(i)+"->");
        }
        System.out.println();
    }
    
    public static void main(String args[])
        {
            Scanner sc=new Scanner(System.in);

            System.out.print("No of Vertices: ");
            int n=sc.nextInt();

            int adjacency[][]=new int[n][n];

            System.out.println();
            System.out.println("Enter weights of edges in Adjacency Matrix:");

            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    adjacency[i][j]=-1;
                }
                //System.out.println();
            }
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    if(adjacency[j][i]!=-1)
                        adjacency[i][j]=adjacency[j][i];         /*Uncomment this portion (if commented) to perform the algorithm on undirected graphs*/
                                                                /* If commented then this acts for undirected+directed Graphs */
                    else
                    {
                        System.out.print((char)(i+65)+" and "+(char)(j+65)+":");
                        adjacency[i][j]=sc.nextInt();
                    }
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
            System.out.print("Enter Starting Vertex: ");
            char start=sc.next().charAt(0);
            int index=(int)(start)-65;

            // System.out.println("1. Breadth First Search");
            // System.out.println("2. Depth First Search");

            // System.out.print("Enter choice: ");
            // int ch=sc.nextInt();
            //if(ch==1)
            breadth_first(n, start, index, adjacency);
            //else
            depth_first(n,start,index,adjacency);
            
        }
}
