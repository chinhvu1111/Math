package E1_daily;

import java.util.*;

public class E208_MaximumNumberOfKDivisibleComponents_hard {

    public static long[] solutionDFS(
            Integer curNode, Integer prevNode, int[] values,
            HashMap<Integer, HashSet<Integer>> tree, int k){
        HashSet<Integer> adj=tree.get(curNode);
        long[] rs={values[curNode],0};
        //[0]: sum
        //[1]: count

        for(int nextNode: adj){
            if(prevNode!=nextNode){
                long[] nextRs=solutionDFS(nextNode, curNode, values, tree, k);
                if(nextRs[0]%k==0){
                    rs[1]+=nextRs[1];
                }else{
                    rs[0]+=nextRs[0];
                    rs[1]+=nextRs[1];
                }
            }
        }
        if(rs[0]%k==0){
            rs=new long[]{0, rs[1]+1};
        }
        return rs;
    }

    public static int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        int m=edges.length;
        HashMap<Integer, HashSet<Integer>> tree=new HashMap<>();

        //Time: O(n)
        for (int i = 0; i < n; i++) {
            tree.put(i, new HashSet<>());
        }
        //Time: O(m)
        for(int i=0;i<m;i++){
            HashSet<Integer> adj=tree.get(edges[i][0]);
            adj.add(edges[i][1]);
            tree.put(edges[i][0], adj);
            HashSet<Integer> adj1=tree.getOrDefault(edges[i][1], new HashSet<>());
            adj1.add(edges[i][0]);
            tree.put(edges[i][1], adj1);
        }
        long[] rs = solutionDFS(0, -1, values, tree, k);
        return (int) rs[1];
    }

    public static int maxKDivisibleComponentsDFSRefer(
            int n,
            int[][] edges,
            int[] values,
            int k
    ) {
        // Step 1: Create adjacency list from edges
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            adjList[node1].add(node2);
            adjList[node2].add(node1);
        }

        // Step 2: Initialize component count
        int[] componentCount = new int[1]; // Use array to pass by reference

        // Step 3: Start DFS traversal from node 0
        dfs(0, -1, adjList, values, k, componentCount);

        // Step 4: Return the total number of components
        return componentCount[0];
    }

    private static int dfs(
            int currentNode,
            int parentNode,
            List<Integer>[] adjList,
            int[] nodeValues,
            int k,
            int[] componentCount
    ) {
        // Step 1: Initialize sum for the current subtree
        int sum = 0;

        // Step 2: Traverse all neighbors
        for (int neighborNode : adjList[currentNode]) {
            if (neighborNode != parentNode) {
                // Recursive call to process the subtree rooted at the neighbor
                sum += dfs(
                        neighborNode,
                        currentNode,
                        adjList,
                        nodeValues,
                        k,
                        componentCount
                );
                sum %= k; // Ensure the sum stays within bounds
            }
        }

        // Step 3: Add the value of the current node to the sum
        sum += nodeValues[currentNode];
        sum %= k;

        // Step 4: Check if the sum is divisible by k
        if (sum == 0) {
            componentCount[0]++;
        }

        // Step 5: Return the computed sum for the current subtree
        return sum;
    }

    public static int maxKDivisibleComponentsBFS(
            int n,
            int[][] edges,
            int[] values,
            int k
    ) {
        // Base case: if there are less than 2 nodes, return 1
        if (n < 2) return 1;

        int componentCount = 0;
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        // Step 1: Build the graph
        for (int[] edge : edges) {
            int node1 = edge[0], node2 = edge[1];
            graph.computeIfAbsent(node1, key -> new HashSet<>()).add(node2);
            graph.computeIfAbsent(node2, key -> new HashSet<>()).add(node1);
        }

        // Convert values to long to prevent overflow
        long[] longValues = new long[values.length];
        for (int i = 0; i < values.length; i++) {
            longValues[i] = values[i];
        }

        // Step 2: Initialize the BFS queue with leaf nodes (nodes with only one connection)
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Set<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                queue.add(entry.getKey());
            }
        }

        // Step 3: Process nodes in BFS order
        while (!queue.isEmpty()) {
            int currentNode = queue.poll();

            // Find the neighbor node
            int neighborNode = -1;
            if (
                    graph.get(currentNode) != null &&
                            !graph.get(currentNode).isEmpty()
            ) {
                neighborNode = graph.get(currentNode).iterator().next();
            }

            if (neighborNode >= 0) {
                // Remove the edge between current and neighbor
                graph.get(neighborNode).remove(currentNode);
                graph.get(currentNode).remove(neighborNode);
            }

            // Check divisibility of the current node's value
            if (longValues[currentNode] % k == 0) {
                componentCount++;
            } else if (neighborNode >= 0) {
                // Add current node's value to the neighbor
                longValues[neighborNode] += longValues[currentNode];
            }

            // If the neighbor becomes a leaf node, add it to the queue
            if (
                    neighborNode >= 0 &&
                            graph.get(neighborNode) != null &&
                            graph.get(neighborNode).size() == 1
            ) {
                queue.add(neighborNode);
            }
        }

        return componentCount;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is (an undirected tree) with n nodes labeled from (0 to n - 1).
        // You are given the integer n and a 2D integer array edges of length n - 1,
        // where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
        //- You are also given (a 0-indexed integer array values) of length n, where values[i] is the value associated with (the ith node),
        // and (an integer k).
        //- (A valid split of the tree) is obtained by removing (any set of edges), (possibly empty),
        // from the tree such that (the resulting components all) have values that are (divisible by k),
        // where (the value of a connected component) is (the sum of the values of its nodes).
        //* Return (the maximum number of components) in any valid split.
        //  + Divide the tree into (many parts) (as much as) possible
        //
        //Input: n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], k = 6
        //Output: 2
        //Explanation: We remove the edge connecting node 1 with 2. The resulting split is valid because:
        //- The value of the component containing nodes 1 and 3 is (values[1] + values[3]) = 12.
        //- The value of the component containing nodes 0, 2, and 4 is (values[0] + values[2] + values[4]) = 6.
        //It can be shown that no other valid split has more than 2 connected components.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n <= 3 * 10^4
        //edges.length == n - 1
        //edges[i].length == 2
        //0 <= ai, bi < n
        //values.length == n
        //0 <= values[i] <= 10^9
        //1 <= k <= 10^9
        //Sum of values is divisible by k.
        //The input is generated such that edges represents a valid tree.
        //  + n<=3*10^4 ==> Time: O(n*k)
        //
        //- Brainstorm
        //- Cut anyway
        //- max (the number of the connected components)
        //- Cut --> edge
        //- Max the number = n times
        //
        //- Undirected tree:
        //  + Determine the root of the tree
        //
        //- sum = a + b + c + d
        //  + (sum%k)==0
        //  + It does not mean:
        //      + ((a+b)%k==0)
        //      + ((c+d)%k==0)
        //- How to split array with (sum%k==0) into many parts:
        //  + such that the number of part is (maximized)
        //Ex:
        //(1+2+3)+(6+7+5)
        //  + 5+6+7 = (18%k)==0
        //
        //- In the tree, we have (less flexible) than array:
        //Ex:
        //(1+2+3)+(6+7+5)
        //6+7+5
        //  ==> If we traverse from 6->7->5
        //  + We only have one connected component
        //7+5+6
        //  ==> If we traverse from 7->5->6
        //  + We have (7+5), (6)
        //  => We have 2 connected components
        //
        //- Tree as the fixed array:
        //==> We can get elment by element following rule:
        //* Main point:
        //- If a leaf node is (not divisible by) k,
        //  it must be in (the same component) as its parent node so we merge it with (its parent node).
        //
        //- Consider the root as node 0
        //- In each step, we either cut a leaf node down or merge a leaf node.
        //  + The number of nodes on the tree reduces by one.
        //  + Repeat this process until only one node is left.
        //+ DFS ==> rs=[sum, count] of (the tree with root) as (the current node)
        //
        //- BFS:
        //- Add all of leaf nodes:
        //  + Traverse by using leaf nodes
        //  + Remove edge by using map
        //
        //1.1, Optimization
        //- Đoạn reset sum nếu (sum%k==0)
        //  + Ta có thể viết thành sum=(sum%k)
        //- Count số lượng:
        //  + sum=sum%k
        //  + if sum==0: count[0]++
        //
        //1.2, Complexity
        //- Space: O(N+E)
        //- Time: O(N+E)
        //
        int n = 5;
        int[][] edges = {{0,2},{1,2},{1,3},{2,4}};
        int[] values = {1,8,1,4,4};
        int k = 6;
        System.out.println(maxKDivisibleComponents(n, edges, values, k));
        System.out.println(maxKDivisibleComponentsDFSRefer(n, edges, values, k));
        System.out.println(maxKDivisibleComponentsBFS(n, edges, values, k));
        //
        //#Reference:
        //2440. Create Components With Same Value
        //
    }
}
