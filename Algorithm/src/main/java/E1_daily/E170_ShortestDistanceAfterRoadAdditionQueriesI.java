package E1_daily;

import java.util.*;

public class E170_ShortestDistanceAfterRoadAdditionQueriesI {

    public static int shortestPath(HashMap<Integer, HashSet<Integer>> graph, int n){
        //Time: O(n)
        //Space: O(n)
        Queue<Integer> nodes=new LinkedList<>();
        HashSet<Integer> visited=new HashSet<>();
        nodes.add(0);
        int depth=1;
        while (!nodes.isEmpty()){
            while (!nodes.isEmpty()){
                int size = nodes.size();

                for (int i=0;i<size;i++){
                    Integer curNode = nodes.poll();
                    HashSet<Integer> adjs=graph.get(curNode);

                    for (Integer nextNode: adjs){
                        if(visited.contains(nextNode)){
                            continue;
                        }
                        nodes.add(nextNode);
                        visited.add(nextNode);
                        if(nextNode==n-1){
                            return depth;
                        }
                    }
                }
                depth++;
            }
        }
        return -1;
    }

    public static int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        //Space: O(n+m)
        HashMap<Integer, HashSet<Integer>> graph=new HashMap<>();

        //Time: O(n)
        for(int i=0;i<n-1;i++){
            HashSet<Integer> adjNodes=graph.getOrDefault(i, new HashSet<>());
            adjNodes.add(i+1);
            graph.put(i, adjNodes);
//            HashSet<Integer> adjNodes1=graph.getOrDefault(i+1, new HashSet<>());
//            adjNodes1.add(i);
//            graph.put(i+1, adjNodes1);
        }
        int m = queries.length;
        //Time: O(m)
        //Space: O(m)
        int[] rs=new int[m];

        //Time: O(m)
        for (int i=0;i<m;i++){
            int[] q=queries[i];
            HashSet<Integer> adjNodes=graph.getOrDefault(q[0], new HashSet<>());
            adjNodes.add(q[1]);
            graph.put(q[0], adjNodes);
//            HashSet<Integer> adjNodes1=graph.getOrDefault(q[1], new HashSet<>());
//            adjNodes1.add(q[0]);
//            graph.put(q[1], adjNodes1);
            rs[i]=shortestPath(graph, n);
        }
        return rs;
    }

    // Helper function to perform BFS and find the number of edges in the shortest path from node 0 to node n-1
    private static int bfs(int n, List<List<Integer>> adjList) {
        boolean[] visited = new boolean[n];
        Queue<Integer> nodeQueue = new LinkedList<>();

        // Start BFS from node 0
        nodeQueue.add(0);
        visited[0] = true;

        // Track the number of nodes in the current layer and the next layer
        int currentLayerNodeCount = 1;
        int nextLayerNodeCount = 0;
        // Initialize layers explored count
        int layersExplored = 0;

        // Perform BFS until the queue is empty
        while (!nodeQueue.isEmpty()) {
            // Process nodes in the current layer
            for (int i = 0; i < currentLayerNodeCount; i++) {
                int currentNode = nodeQueue.poll();

                // Check if we reached the destination node
                if (currentNode == n - 1) {
                    return layersExplored; // Return the number of edges in the shortest path
                }

                // Explore all adjacent nodes
                for (int neighbor : adjList.get(currentNode)) {
                    if (visited[neighbor]) continue;
                    nodeQueue.add(neighbor); // Add neighbor to the queue for exploration
                    nextLayerNodeCount++; // Increment the count of nodes in the next layer
                    visited[neighbor] = true;
                }
            }

            // Move to the next layer
            currentLayerNodeCount = nextLayerNodeCount;
            nextLayerNodeCount = 0; // Reset next layer count
            layersExplored++; // Increment the layer count after processing the current layer
        }

        return -1; // Algorithm will never reach this point
    }

    public static int[] shortestDistanceAfterQueriesBFSReference(int n, int[][] queries) {
        List<Integer> answer = new ArrayList<>();
        List<List<Integer>> adjList = new ArrayList<>(n);

        // Initialize the adjacency list for the graph
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Initialize the graph with edges between consecutive nodes
        for (int i = 0; i < n - 1; i++) {
            adjList.get(i).add(i + 1);
        }

        // Process each query to add new roads
        for (int[] road : queries) {
            int u = road[0];
            int v = road[1];
            adjList.get(u).add(v); // Add road from u to v
            // Perform BFS to find the shortest path after adding the new road
            answer.add(bfs(n, adjList));
        }

        // Convert List<Integer> to int[]
        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static int findMinDistance(List<List<Integer>> graph, int n){
        int[] dp=new int[n];

        //n <- nextNode
        //+ Loop node from n-2 -> 0
        for(int currentNode = n-2;currentNode>=0;currentNode--){
            int minDist=n;

            for(int nextNode: graph.get(currentNode)){
                minDist=Math.min(minDist, dp[nextNode]+1);
            }
            dp[currentNode]=minDist;
        }
        return dp[0];
    }

    public static int[] shortestDistanceAfterQueriesDynamicProgramming(int n, int[][] queries) {
        List<Integer> answer = new ArrayList<>();
        List<List<Integer>> adjList = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Initialize edges between consecutive nodes
        for (int i = 0; i < n - 1; i++) {
            adjList.get(i).add(i + 1);
        }

        // Process each query to add new edges
        for (int[] road : queries) {
            int u = road[0];
            int v = road[1];
            adjList.get(u).add(v); // Add the directed edge from u to v

            // Calculate the minimum distance after adding the new edge
            answer.add(findMinDistance(adjList, n));
        }

        // Convert List<Integer> to int[] before returning
        int[] result = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result; // Return the results for each query
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer n and a 2D integer array queries.
        //- There are n cities numbered from (0 to n - 1).
        //- Initially, there is (a unidirectional road) from (city i to city i + 1) for all 0 <= i < n - 1.
        //  + queries[i] = [ui, vi] represents (the "addition" of a new unidirectional road) from (city ui to city vi).
        //- After each query, you need to find the length of the (shortest path) from (city 0 to city n - 1).
        //* Return an array answer where for (each i) in the range [0, queries.length - 1],
        //- answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.
        //
        //Example 1:
        //Input: n = 5, queries = [[2,4],[0,2],[0,4]]
        //Output: [3,2,1]
        // 0 -> 1 -> 2 -> 3 -> 4
        //
        //Explanation:
        //After the addition of the road from 2 to 4, the length of the shortest path from (0 to 4) is 3.
        //After the addition of the road from 0 to 2, the length of the shortest path from (0 to 4) is 2.
        //After the addition of the road from 0 to 4, the length of the shortest path from (0 to 4) is 1.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //Constraints:
        //3 <= n <= 500
        //1 <= queries.length <= 500
        //queries[i].length == 2
        //0 <= queries[i][0] < queries[i][1] < n
        //1 < queries[i][1] - queries[i][0]
        //There are no repeated roads among the queries.
        //  + n <= 500 ==> Time: O(n^2)
        //
        //- Brainstorm
        //- BFS
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n+m)
        //- Time: O(n*m)
        //
        //2.
        //2.0, Dynamic programming
        //-
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n+m)
        //- Time: O(n*m)
        //
        int  n = 5;
        int[][] queries = {{2,4},{0,2},{0,4}};
//        int[] rs = shortestDistanceAfterQueries(n, queries);
//        int[] rs = shortestDistanceAfterQueriesBFSReference(n, queries);
        int[] rs = shortestDistanceAfterQueriesDynamicProgramming(n, queries);

        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        //#Reference:
        //2772. Apply Operations to Make All Array Elements Equal to Zero
        //2608. Shortest Cycle in a Graph
        //789. Escape The Ghosts
        //2923. Find Champion I
        //2996. Smallest Missing Integer Greater Than Sequential Prefix Sum
        //3148. Maximum Difference Score in a Grid
    }
}
