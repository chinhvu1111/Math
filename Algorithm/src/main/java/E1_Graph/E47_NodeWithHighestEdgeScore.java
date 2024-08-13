package E1_Graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class E47_NodeWithHighestEdgeScore {

    public static int edgeScore(int[] edges) {
        int n=edges.length;
        HashMap<Integer, Long> nodeToScore=new HashMap<>();

        for(int i=0;i<n;i++){
            nodeToScore.put(edges[i], nodeToScore.getOrDefault(edges[i], 0L)+i);
        }
        //[score, index]
//        PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[0]!=o2[0]){
//                    return o1[0]-o2[0];
//                }
//                return ;
//            }
//        });
//        System.out.println(nodeToScore);
        long maxScore=0;
        int index=Integer.MAX_VALUE;

        for(Map.Entry<Integer, Long> e: nodeToScore.entrySet()){
            if(maxScore<e.getValue()){
                maxScore=e.getValue();
                index=e.getKey();
            }else if(maxScore==e.getValue()&&e.getKey()<index){
                index=e.getKey();
            }
        }
        return index;
    }

    public static int edgeScoreArray(int[] edges) {
        int n = edges.length;
        long ans[] = new long[n];

        long max = -1;
        int result = -1;
        for(int i=0; i<n; i++){
            int node = edges[i];
            ans[node]+=i;

            if(ans[node]>max){
                max = ans[node];
                result = node;
            }else if(ans[node]==max){
                if(result>node) result = node;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a directed graph) with n nodes labeled (from 0 to n - 1),
        // where (each node) has exactly ("one") (outgoing edge).
        //  + Mỗi node có chính xác (1 outgoing edge)
        //- The graph is represented by a given 0-indexed integer (array edges) of length n,
        // where edges[i] indicates that there is (a directed edge) from node ((i) to node edges[i]).
        //- The edge score of a node (i) is defined as (the sum of the labels) of (all the nodes) that have (an edge) pointing to (i).
        //* Return the node with (the highest edge score).
        //- If multiple nodes have (the same edge score),
        //- return the node with (the smallest index).
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //
        //
        //- Brainstorm
        //- Return node having the biggest edge score of the node
        //  + Score của 1 node là sum các node connect với nó.
        //- Bài này dùng priority queue + sort by (sum(value) + index)
        //
        //
        int[] edges = {1,0,0,0,0,7,7,5};
        System.out.println(edgeScore(edges));
        System.out.println(edgeScoreArray(edges));
        //#Reference:
        //2846. Minimum Edge Weight Equilibrium Queries in a Tree
        //1733. Minimum Number of People to Teach
        //2077. Paths in Maze That Lead to Same Room
        //
    }
}
