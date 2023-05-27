package E1_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E2_NumberOfProvinces {
    public static int bfs(int[][] isConnected){
        int n=isConnected.length;
        boolean[] visited =new boolean[n+1];
        int numGroups=0;

        for(int i=0;i<n;i++){
            if(!visited[i]){
                numGroups++;
            }else{
                continue;
            }
            Queue<Integer> cities=new LinkedList<>();
            cities.add(i);

            while (!cities.isEmpty()){
                Integer currentCity=cities.poll();
                visited[currentCity]=true;
//                System.out.printf("%s %s\n",currentCity, numGroups);

                for(int j=0;j<n;j++){
                    if(!visited[j]&&isConnected[currentCity][j]==1){
                        cities.add(j);
                        visited[j]=true;
                    }
                }
            }
        }
        return numGroups;
    }

    public static int bfsOptimization(int[][] isConnected){
        int n=isConnected.length;
        boolean[] visited =new boolean[n+1];
        int numGroups=0;
        List<Integer>[] adjNodes=new ArrayList[n];

        for(int i=0;i<n;i++){
            adjNodes[i]=new ArrayList<>();
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(isConnected[i][j]==1){
                    adjNodes[i].add(j);
                }
            }
        }

        Queue<Integer> cities=new LinkedList<>();

        for(int i=0;i<n;i++){
            if(!visited[i]){
                numGroups++;
            }else{
                continue;
            }
            cities.add(i);

            while (!cities.isEmpty()){
                Integer currentCity=cities.poll();
                visited[currentCity]=true;
//                System.out.printf("%s %s\n",currentCity, numGroups);
                for(Integer node: adjNodes[currentCity]){
                    if(!visited[node]){
                        cities.add(node);
                        visited[node]=true;
                    }
                }
            }
        }
        return numGroups;
    }

    public static int findCircleNum(int[][] isConnected) {
        return bfs(isConnected);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Count the number of provinces
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint:
        //+ 1 <= n <= 200
        //
        //- Find the group of node which are connected together.
        //+ Scan all nodes --> if we have traversed 1 group : We mark them as visited
        //+ If we find any nodes have visited=false --> new group <=> num_group++
        //
        //- Implementation:
        //+ each node in city --> add queue<Integer>
        //Example:
        //A --> B --> E , C--> D,
        //
        //1.1, Optimization
        //- Nếu đi đến node nào thì nên gán visit[node]=true.
        //-
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(isConnected));
        //#Reference:
        //802. Find Eventual Safe States
        //323. Number of Connected Components in an Undirected Graph
        //657. Robot Return to Origin
        //734. Sentence Similarity
    }
}
