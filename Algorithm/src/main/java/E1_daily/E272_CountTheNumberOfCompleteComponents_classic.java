package E1_daily;

import java.util.*;

public class E272_CountTheNumberOfCompleteComponents_classic {

    public static int[] findParent(int[] parent, int node){
//        int prevNode=node;
        int depth=1;
        int initNode=node;

        while(parent[node]!=node){
//            prevNode=node;
            node=parent[node];
            depth++;
        }
        parent[initNode]=node;
        return new int[]{node, depth};
    }

    public static int addEdge(int u, int v, int[] parent){
        int[] parentU= findParent(parent, u);
        int[] parentV= findParent(parent, v);
//        System.out.printf("u: %s, parentu: %s\n", u, parentU[0]);
//        System.out.printf("v: %s, parentV: %s\n", v, parentV[0]);

        //1 -> 2
        //2 -> 3
        //==> parent(1) != parent(3) ==> Nhưng findParent(1)==findParent(3)
        //==>
        if(parentU[0]!=parentV[0]){
            if(parentU[1]>parentV[1]){
                parent[parentV[0]]=parentU[0];
            }else{
                parent[parentU[0]]=parentV[0];
            }
            return 0;
        }
        return 1;
    }


    public static int countCompleteComponents(int n, int[][] edges) {
        int[] parent=new int[n];
        int[][] graph=new int[n][n];

        //Time: O(n)
        for (int i = 0; i < n; i++) {
            parent[i]=i;
        }
        //Time: O(m)
        for(int[] e: edges){
            //Time: O(n)
            addEdge(e[0], e[1], parent);
            graph[e[0]][e[1]]=1;
            graph[e[1]][e[0]]=1;
        }
        HashMap<Integer, List<Integer>> parentToChildren=new HashMap<>();
        //Time: O(n)
        for(int i=0;i<n;i++){
            //Time: O(m)
            int curParent = findParent(parent, i)[0];
//            System.out.println(curParent);
            List<Integer> curGraph=parentToChildren.getOrDefault(curParent, new ArrayList<>());
            curGraph.add(i);
            parentToChildren.put(curParent, curGraph);
        }
//        System.out.println(parentToChildren);
        int rs=0;
        for(Map.Entry<Integer, List<Integer>> e: parentToChildren.entrySet()){
            List<Integer> listNode=e.getValue();
            int l=listNode.size();
            boolean isValid=true;

            for(int i=0;i<l;i++){
                for(int j=i+1;j<l;j++){
                    if(graph[listNode.get(i)][listNode.get(j)]==0){
                        isValid=false;
                    }
                }
            }
            if(isValid){
                rs++;
            }
        }
        return rs;
    }

    public static int countCompleteComponentsRefer(int n, int[][] edges) {
        // Initialize Union Find and edge counter
        UnionFind dsu = new UnionFind(n);
        Map<Integer, Integer> edgeCount = new HashMap<>();

        // Connect components using edges
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }

        // Count edges in each component
        for (int[] edge : edges) {
            int root = dsu.find(edge[0]);
            edgeCount.put(root, edgeCount.getOrDefault(root, 0) + 1);
        }

        // Check if each component is complete
        int completeCount = 0;
        for (int vertex = 0; vertex < n; vertex++) {
            if (dsu.find(vertex) == vertex) { // If vertex is root
                int nodeCount = dsu.size[vertex];
                int expectedEdges = (nodeCount * (nodeCount - 1)) / 2;
                if (edgeCount.getOrDefault(vertex, 0) == expectedEdges) {
                    completeCount++;
                }
            }
        }
        return completeCount;
    }

    static class UnionFind {

        int[] parent;
        int[] size; // Tracks size of each component

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(parent, -1);
            Arrays.fill(size, 1);
        }

        // Find root of component with path compression
        int find(int node) {
            if (parent[node] == -1) {
                return node;
            }
            return parent[node] = find(parent[node]);
        }

        // Union by size
        void union(int node1, int node2) {
            int root1 = find(node1);
            int root2 = find(node2);

            if (root1 == root2) {
                return;
            }

            // Merge smaller component into larger one
            if (size[root1] > size[root2]) {
                parent[root2] = root1;
                size[root1] += size[root2];
            } else {
                parent[root1] = root2;
                size[root2] += size[root1];
            }
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer n. There is (an undirected graph) with n vertices, numbered from (0 to n - 1).
        //- You are given a 2D integer array edges where edges[i] = [ai, bi] denotes
        // that there exists (an undirected edge) connecting vertices ai and bi.
        //* Return (the number of complete connected components) of the graph.
        //- (A connected component) is a subgraph of a graph in which there exists a path between (any two vertices),
        // and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.
        //- (A connected component) is said to be complete if there exists an edge between (every pair of its vertices).
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n <= 50
        //0 <= edges.length <= n * (n - 1) / 2
        //edges[i].length == 2
        //0 <= ai, bi <= n - 1
        //ai != bi
        //There are no repeated edges.
        //  + n<=50 ==> Time: O(n^2)
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //1.2, Optimization
        //- Rather than looping (all of pairs) in the connected component
        //- Count (the number of node) of each connected component
        //  + Using (the union find traverse) to count (the number of nodes) of each (connected component)
        //- Count (the number of edge) of each connected component
        //  + Loop (all of edges) of each (parent node)
        //
        //- N nodes ==> N*(N-1) edges if this graph is complete
        //
        //1.3, Complexity
        //- Space: O(n*m) [O(n+mα(n))]
        //- Time: O(n*m)
        //  + The solution uses a Union-Find data structure with path compression and union by size.
        //  + Building the Union-Find structure takes O(n) time for initialization.
        //  Processing all edges through union operations takes O(mα(n)) time, where α(n) is (the inverse Ackermann function),
        //  which grows (extremely slowly) and is practically constant.
        //  + Counting edges in each component requires iterating through all edges again, taking O(m) time.
        //  Finally, checking if each component is complete involves iterating through all vertices once, taking O(n) time.
        //  Therefore, the overall time complexity is O(n+mα(n)), which is essentially linear in practice.
//        int n = 6;
//        int[][] edges = {{0,1},{0,2},{1,2},{3,4}};
        int n = 6;
        int[][] edges = {{0,1},{0,2},{1,2},{3,4},{3,5}};
        System.out.println(countCompleteComponents(n, edges));
        System.out.println(countCompleteComponentsRefer(n, edges));
        //
        //#Reference:
//        2092. Find All People With Secret
//        1778. Shortest Path in a Hidden Grid
//        3378. Count Connected Components in LCM Graph

    }
}
