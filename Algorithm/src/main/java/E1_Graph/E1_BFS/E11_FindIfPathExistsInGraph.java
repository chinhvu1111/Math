package E1_Graph.E1_BFS;

import java.util.*;

public class E11_FindIfPathExistsInGraph {
    public static boolean validPathBFS(int n, int[][] edges, int source, int destination) {
        if(source==destination){
            return true;
        }
        HashMap<Integer, List<Integer>> adjNodes=new HashMap<>();

        for(int[] e: edges){
            adjNodes.computeIfAbsent(e[0],k -> new ArrayList<>()).add(e[1]);
            adjNodes.computeIfAbsent(e[1],k -> new ArrayList<>()).add(e[0]);
        }
        Queue<Integer> nodes=new LinkedList<>();
        boolean[] visited=new boolean[n];
        nodes.add(source);
        visited[source]=true;

        while(!nodes.isEmpty()){
            Integer curNode=nodes.poll();
            List<Integer> adj=adjNodes.get(curNode);
            if(adj==null){
                continue;
            }
            for(Integer nextNode: adj){
                if(!visited[nextNode]){
                    if(nextNode==destination){
                        return true;
                    }
                    visited[nextNode]=true;
                    nodes.add(nextNode);
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        // Đề bài:
        //There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
        // The edges in the graph are represented as a 2D integer array edges,
        // where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi.
        //- Every vertex pair is connected by (at most one edge), and no vertex has (an edge to itself).
        //You want to determine if there is (a valid path) that exists from vertex (source) to vertex (destination).
        //- Given edges and the integers n, source, and destination
        //* Return true if there is (a valid path) from (source) to (destination), or false otherwise.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= n <= 2 * 10^5
        //0 <= edges.length <= 2 * 10^5
        //edges[i].length == 2
        //0 <= ui, vi <= n - 1
        //ui != vi
        //0 <= source, destination <= n - 1
        //There are no duplicate edges.
        //There are no self edges.
        //
        //- Brainstorm
        //- Bài này có thể làm
        //  + BFS
        //  + Union find
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(V+E)
        //- Time : O(V+E)
        //
        //#Reference:
        //2097. Valid Arrangement of Pairs
        //2077. Paths in Maze That Lead to Same Room
    }
}
