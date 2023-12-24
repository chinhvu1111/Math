package E1_Graph;

import java.util.HashMap;
import java.util.HashSet;

public class E38_NumberOfConnectedComponentsInAnUndirectedGraph {

    public static void traverse(int node, HashMap<Integer, HashSet<Integer>> adjNodes, boolean[] visited){
        visited[node]=true;
        HashSet<Integer> adj=adjNodes.get(node);

        if(adj==null){
            return;
        }
        for(Integer nextNode: adj){
            if(!visited[nextNode]){
                traverse(nextNode, adjNodes, visited);
            }
        }
    }

    public static int countComponents(int n, int[][] edges) {
        boolean[] visited=new boolean[n];
        //Space: O(N + E)
        HashMap<Integer, HashSet<Integer>> adjNodes=new HashMap<>();

        //Time : O(E)
        for(int[] e: edges){
            HashSet<Integer> adj = adjNodes.computeIfAbsent(e[0], k -> new HashSet<>());
            adj.add(e[1]);
            adj = adjNodes.computeIfAbsent(e[1], k -> new HashSet<>());
            adj.add(e[0]);
        }
        int rs=0;

        //TIME : O(N)
        for(int i=0;i<n;i++){
            if(!visited[i]){
                //TIME : O(E) in total
                traverse(i, adjNodes, visited);
                rs++;
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You have (a graph of n nodes). You are given an integer (n) and an array edges where edges[i] = [ai, bi] indicates
        // that there is an edge between ai and bi in the graph.
        //* Return the number of connected components in the graph.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= n <= 2000
        //1 <= edges.length <= 5000
        //edges[i].length == 2
        //0 <= ai <= bi < n
        //ai != bi
        //
        //- Brainstorm
        //- Bài này chỉ đơn giản là ta sẽ traverse hết các nodes
        //==> Nếu node nào chưa đi + 1 lên là được
        //- Quan trọng là số node được cover như thế nào?
        //+ Lấy nodes từ edges
        //+ Node : 1 -> n
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(N+E)
        //- Time : O(N+E)
        //
        //#Reference:
        //2077. Paths in Maze That Lead to Same Room
        //2685. Count the Number of Complete Components
    }
}
