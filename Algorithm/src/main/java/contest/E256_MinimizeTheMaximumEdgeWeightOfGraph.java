package contest;

import java.util.*;

public class E256_MinimizeTheMaximumEdgeWeightOfGraph {

    public static int minMaxWeight(int n, int[][] edges, int threshold) {
        return 1;
    }
    // Function to check if it's possible to satisfy the conditions with a given max weight
    static boolean isValid(int maxWeight, int[][] originGraph, int n, int threshold) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : originGraph) {
            if (edge[2] <= maxWeight) {
                graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge);
            }
        }

        boolean[] visited = new boolean[n];
        if (!dfs(0, graph, visited)) {
            return false;
        }
        for (Map.Entry<Integer, List<int[]>> entry : graph.entrySet()) {
            if (entry.getValue().size() > threshold) {
                return false;
            }
        }

        return true;
    }

    // DFS to check if all nodes can reach node 0
    static boolean dfs(int node, Map<Integer, List<int[]>> graph, boolean[] visited) {
        visited[node] = true;
        for (int[] edge : graph.getOrDefault(node, new ArrayList<>())) {
            int nextNode = edge[1];
            if (!visited[nextNode] && !dfs(nextNode, graph, visited)) {
                return false;
            }
        }
        return true;
    }

    static public int minimizeMaxEdgeWeight(int n, int threshold, int[][] edges) {
        List<int[]> claridomep = new ArrayList<>(Arrays.asList(edges));
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for (int[] edge : claridomep) {
            left = Math.min(left, edge[2]);
            right = Math.max(right, edge[2]);
        }

        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (isValid(mid, edges, n, threshold)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two integers, n and threshold, as well as (a directed weighted graph of n nodes) numbered from (0 to n - 1).
        //- The graph is represented by a 2D integer array edges, where edges[i] = [Ai, Bi, Wi] indicates that
        // there is an edge going from (node Ai to node Bi with weight Wi).
        //- You have to remove some edges from this graph (possibly none), so that it satisfies the following conditions:
        //  + Node 0 (must be reachable) from (all other nodes).
        //  + (The maximum edge weight) in the resulting graph is (minimized).
        //  + (Each node) has (at most threshold) (outgoing edges).
        //* Return (the minimum possible value) of (the maximum edge weight) after removing (the necessary edges).
        //- If it is impossible for all conditions to be satisfied, return -1.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= n <= 10^5
        //1 <= threshold <= n - 1
        //1 <= edges.length <= min(10^5, n * (n - 1) / 2).
        //edges[i].length == 3
        //0 <= Ai, Bi < n
        //Ai != Bi
        //1 <= Wi <= 10^6
        //There may be multiple edges between a pair of nodes, but they must have unique weights.
        //  + Time: O(n)
        //
        //- Brainstorm
        //- Remove (the redundant edges) such that (node 0) (must be reachable from all of other nodes)
        //
        //
//        int n = 5;
//        int[][] edges = {{1,0,1},{2,0,2},{3,0,1},{4,3,1},{2,1,1}};
//        int threshold = 2;
        int n = 5;
        int[][] edges = {{1,2,1},{1,3,3},{1,4,5},{2,3,2},{3,4,2},{4,0,1}};
        int threshold = 1;
        System.out.println(minimizeMaxEdgeWeight(n, threshold, edges));
    }
}
