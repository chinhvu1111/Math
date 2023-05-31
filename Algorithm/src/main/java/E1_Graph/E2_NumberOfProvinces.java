package E1_Graph;

import java.util.*;

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

        //O(n)
        for(int i=0;i<n;i++){
            if(!visited[i]){
                numGroups++;
            }else{
                continue;
            }
            cities.add(i);

            //O(V+E) : V is the number of vertices, E is the number of edges
            //
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

    public static int findParent(int[] parent, int node){
        int tmp=node;
        int prevNode=node;
        while (node!=-1){
            prevNode=node;
            node=parent[node];
        }
        if(prevNode==tmp){
            return -1;
        }
        return prevNode;
    }

    public static void addEdege(int[] parent, int u, int v){
        int parentU=findParent(parent, u);
        int parentV=findParent(parent, v);
//        System.out.printf("%s parent : %s, %s parent : %s \n",u, parentU, v, parentV);
        parent[u]=parentU;
        parent[v]=parentV;

        if(parentU==-1&&parentV==-1){
            parent[v]=u;
            return;
        }
        if(parentU==parentV){
            return;
        }
        //U doesn't have the parent node
        if(parentU==-1){
            if(parentV!=u) parent[u]=parentV;
            return;
        }
        if(parentV==-1){
            if(parentU!=v) parent[v]=parentU;
            return;
        }
        parent[parentV]=parentU;
    }

    public static int findCircleNumUnionFind(int[][] isConnected) {
        int n=isConnected.length;
        int[] parent=new int[n];
        Arrays.fill(parent, -1);

        // 1+2+3+n
        //n*(n+1)/2 = n^2
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(i!=j&&isConnected[i][j]==1){
                    addEdege(parent, i, j);
                }
            }
        }
        HashSet<Integer> groups=new HashSet<>();

        for(int i=0;i<n;i++){
            int currentParent=findParent(parent, i);

            if(currentParent==-1){
                groups.add(i);
//                System.out.println(i);
            }else{
                groups.add(currentParent);
//                System.out.println(currentParent);
            }
        }

        return groups.size();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Count the number of provinces
        //
        //** Idea
        //1.
        //1.0, Idea
        //https://stackoverflow.com/questions/26549140/breadth-first-search-time-complexity-analysis
        //- Time Complexity of BFS = O(V+E) where V is vertices and E is edges.
        //- Time Complexity of DFS is also O(V+E) where V is vertices and E is edges.
        // + BFS requires more memory space
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
        //
        //1.2, Complexity:
        //- Time complexity : O(n^2)
        //- Space complexity : O(n)
        //
        //* Method 2:
        //2.
        //2.0, Idea
        //- Using union find method
        //+ Add new edge (u, v) --> edge created by connecting between u and v
        //--> New return false
        //- The simplest way is add all edges to build graph
        //- After that we will check all nodes and find the groups
        //
        //Method 3:
        //- The most efficient way
//        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        int[][] isConnected =
                {{1,0,0,1}, {0,1,1,0}, {0,1,1,1}, {1,0,1,1}};
        //0 : 0, 3
        //1 : 1, 2
        //2 : 1, 2, 3 //parent[3]=2
        //3 : 0, 2, 3
        //Edges:
        //(0, 3)
        //(1, 2)
        //(2, 3)
        //* Union find có các cases cần chú ý như sau:
        //- findParent[] :
        //+ Cần cache prevValue
        //+ Nếu prevValue==currentNode : return -1
        //
        //- addEdges() : Cần các cases như:
        //+ parentU==-1 và parentV==-1 : default parent[u]=v
        //+ 1 trong 2 bằng -1
        //VD: parentU=-1 --> parent[u]=v
        //---> Cần chú ý thêm case : parent[u]=u ==> Loop forever (Cần phải check (parentV!=u) trước khi gán)
        //+ parent[parentU]=parentV
        //
        //2.1,
        //- Sửa lại 1 chút loop: (i : 0 --> n-1), (j : i+1 --> n-1) : Để tránh lặp edges
        //2.2, Complexity :
        //- Time complexity : O(n^2)
        //- Space complexity : O(n)
        //
        System.out.println(findCircleNumUnionFind(isConnected));
        //#Reference:
        //802. Find Eventual Safe States
        //323. Number of Connected Components in an Undirected Graph
        //657. Robot Return to Origin
        //734. Sentence Similarity
    }
}
