package contest;

import javafx.util.Pair;

import java.util.*;

public class E79_MinimumTimeToVisitDisappearingNodes {

    public static int[] minimumTime(int n, int[][] edges, int[] disappear) {
        Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0], v = edges[i][1];
            int length = edges[i][2];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Pair<>(v, length));
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Pair<>(u, length));
        }

        int[] minLength = new int[n];
        Arrays.fill(minLength, Integer.MAX_VALUE);
        minLength[0] = 0;
        boolean[] visited=new boolean[n];

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.getKey(), b.getKey()));
        pq.add(new Pair<>(0, 0));
        int numNode=1;
        while (!pq.isEmpty()) {
            Pair<Integer, Integer> cur = pq.poll();
            int curLen = cur.getKey();
            int curNode = cur.getValue();
            if(!visited[curNode]){
                numNode++;
            }
            visited[curNode]=true;
//            if (curNode == end) {
//                return curProb;
//            }
            for (Pair<Integer, Integer> nxt : graph.getOrDefault(curNode, new ArrayList<>())) {
                int nxtNode = nxt.getKey();
                int len = nxt.getValue();
                if (!visited[nxtNode] && curLen + len < minLength[nxtNode] && curLen + len < disappear[nxtNode]) {
                    minLength[nxtNode] = curLen + len;
                    pq.add(new Pair<>(minLength[nxtNode], nxtNode));
                }
            }
            //Đếm số node đã xử lý ==n ==> break là được
            if(numNode==n){
                break;
            }
        }
        for(int i=0;i<n;i++){
            if(minLength[i]>=disappear[i]){
                minLength[i]=-1;
            }
        }
//        for(int i=0;i<n;i++){
//            System.out.println(minLength[i]);
//        }

        return minLength;
    }

    public static void main(String[] args) {
        //* Requirement
        //- There is an undirected graph of n nodes. You are given a 2D array edges, where edges[i] = [ui, vi, lengthi]
        // describes an edge between node ui and node vi with a traversal time of lengthi units.
        //Additionally, you are given an array disappear, where disappear[i] denotes the time when the node i disappears from the graph
        // and you won't be able to visit it.
        //* Notice that the graph might be disconnected and might contain multiple edges.
        //* Return the array answer, with answer[i] denoting the minimum units of time required to reach node i from node 0.
        // If node i is unreachable (from node 0) then (answer[i] is -1).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n <= 5 * 10^4
        //0 <= edges.length <= 10^5
        //edges[i] == [ui, vi, lengthi]
        //0 <= ui, vi <= n - 1
        //1 <= lengthi <= 10^5
        //disappear.length == n
        //1 <= disappear[i] <= 10^5
        //--> length >=0 ==> Có thể dùng dijistra được
        //
        //- Brainstorm
        //- Có thể lúc đi qua 1 node khác ==> Nó disappear rồi ==> Không thể đi tiếp được nữa
        //
        int n = 3;
        int[][]edges = {{0,1,2},{1,2,1},{0,2,4}};
        int[] disappear = {1,3,5};
//
//        int n = 2;
//        int[][] edges = {{0,1,1}};
//        int[] disappear = {1,1};
        minimumTime(n, edges, disappear);
    }
}
