package E1_Tree;

import java.util.HashMap;

public class E41_FindCenterOfStarGraph {

    public static int findCenter(int[][] edges) {
        HashMap<Integer, Integer> vertexWeight=new HashMap<>();
        int n=edges.length;

        for(int[] e: edges){
            vertexWeight.put(e[0], vertexWeight.getOrDefault(e[0], 0)+1);
            vertexWeight.put(e[1], vertexWeight.getOrDefault(e[1], 0)+1);
            if(vertexWeight.get(e[0])==n){
                return e[0];
            }
            if(vertexWeight.get(e[1])==n){
                return e[1];
            }
        }
        return 1;
    }

    public static int findCenterOptimization(int[][] edges) {
        int n=edges.length;
        int[] count=new int[n+2];

        for(int[] e: edges){
            count[e[0]]++;
            count[e[1]]++;
            if(count[e[0]]>=2){
                return e[0];
            }
            if(count[e[1]]>=2){
                return e[1];
            }
        }
        return 1;
    }

    public static int findCenterOptimization1(int[][] edges) {
        int[] firstEdge=edges[0];
        int[] secondEdge=edges[1];
        if(firstEdge[0]==secondEdge[0]){
            return firstEdge[0];
        }
        if(firstEdge[0]==secondEdge[1]){
            return firstEdge[0];
        }
        return firstEdge[1];
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is (an undirected star graph) consisting of n nodes labeled from (1 to n).
        //- A star graph is a graph where there is (one center node) and (exactly n - 1 edges) that connect the center node with (every other node).
        //- You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates
        // that there is an edge between the nodes ui and vi.
        //* Return the center of the given star graph.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //3 <= n <= 10^5
        //edges.length == n - 1
        //edges[i].length == 2
        //1 <= ui, vi <= n
        //ui != vi
        //The given edges represent a valid star graph.
        //+ N khá lớn => Time: O(n)
        //
        //- Brainstorm
        //- Đếm bậc là được:
        //  + A -> B
        //  => Đếm bậc của all vertex
        //
    }
}
