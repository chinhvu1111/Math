package E1_daily;

import java.util.*;

public class E273_FindTheClosestMarkedNode {

    public static int minimumDistance(int n, List<List<Integer>> edges, int s, int[] marked) {
//        int m=edges.size();
        PriorityQueue<int[]> minHeap=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        minHeap.add(new int[]{s, 0});
        //Time: O(n+m)
        HashMap<Integer, HashSet<int[]>> graph=new HashMap<>();

        //Time: O(m)
        for(List<Integer> e : edges){
            HashSet<int[]> adj=graph.getOrDefault(e.get(0), new HashSet<>());
            adj.add(new int[]{e.get(1), e.get(2)});
            graph.put(e.get(0), adj);
//            HashSet<int[]> adj1=graph.getOrDefault(e.get(1), new HashSet<>());
//            adj1.add(new int[]{e.get(0), e.get(2)});
//            graph.put(e.get(1), adj1);
        }
        //Time: O(n)
        boolean[] visited=new boolean[n];
        visited[s]=true;
        int[] dist=new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
//        int l=marked.length;
//        int index=0;
        HashSet<Integer> setNode=new HashSet<>();

        for (int e: marked){
            setNode.add(e);
        }
//        if(setNode.contains(s)){
////            index++;
//            setNode.add(s);
//        }

        //Time: O(n*log(m))
        while(!minHeap.isEmpty()){
            int[] curNode=minHeap.poll();
            visited[curNode[0]]=true;
            HashSet<int[]> adj=graph.get(curNode[0]);

            if(setNode.contains(curNode[0])){
                return curNode[1];
//                index++;
//                setNode.add(curNode[0]);
            }
//            if(index==l){
//                break;
//            }
            if(adj==null){
                continue;
            }
            for(int[] e: adj){
                if(!visited[e[0]]){
                    minHeap.add(new int[]{e[0],curNode[1]+e[1]});
                    dist[e[0]]=Math.min(dist[e[0]], curNode[1]+e[1]);
                }
            }
        }
//        int rs=Integer.MAX_VALUE;
//        for(int e: marked){
//            rs=Math.min(rs, dist[e]);
//        }
//        return rs==Integer.MAX_VALUE?-1:rs;
        return -1;
    }

    public static int minimumDistanceRefer(
            int n,
            List<List<Integer>> edges,
            int s,
            int[] marked
    ) {
        // Convert marked array to set for O(1) lookups
        Set<Integer> markSet = new HashSet<>();
        for (int node : marked) {
            markSet.add(node);
        }

        // Build adjacency list representation of the graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> edge : edges) {
            adj.get(edge.get(0)).add(new int[] { edge.get(1), edge.get(2) });
        }

        // Initialize distance array with infinity values
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        // Min heap prioritized by distance
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> a[0] - b[0]
        );
        minHeap.offer(new int[] { 0, s });

        // Dijkstra's algorithm
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int node = current[1];
            int distance = current[0];

            // Found a marked node, return its distance
            if (markSet.contains(node)) {
                return dist[node];
            }

            // Explore neighbors
            for (int[] edge : adj.get(node)) {
                int nextNode = edge[0];
                int weight = edge[1];
                int newDist = distance + weight;

                // If we found a shorter path, update and add to the priority queue
                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    minHeap.offer(new int[] { newDist, nextNode });
                }
            }
        }

        // No path found to any marked node
        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a positive integer n which is the number of nodes of a 0-indexed directed weighted graph
        // and a 0-indexed 2D array edges where edges[i] = [ui, vi, wi]
        // indicates that there is an edge from node ui to node vi with weight wi.
        //- You are also given (a node s) and (a node array) ("marked");
        //- your task is to find (the minimum distance) from s to any of the nodes in marked.
        //* Return an integer denoting (the minimum distance) from (s) to (any node) in (marked) or -1
        // if there are (no paths) from s to any of the (marked) nodes.
        //
        //Example 1:
        //
        //Input: n = 4, edges = [[0,1,1],[1,2,3],[2,3,2],[0,3,4]], s = 0, marked = [2,3]
        //Output: 4
        //Explanation:
        // There is one path from node 0 (the green node) to node 2 (a red node), which is 0->1->2,
        // and has a distance of 1 + 3 = 4.
        //There are two paths from node 0 to node 3 (a red node), which are 0->1->2->3 and 0->3,
        //  + the first one has a distance of 1 + 3 + 2 = 6 and
        //  + the second one has a distance of 4.
        //The minimum of them is 4.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= n <= 500
        //1 <= edges.length <= 10^4
        //edges[i].length = 3
        //0 <= edges[i][0], edges[i][1] <= n - 1
        //1 <= edges[i][2] <= 10^6
        //1 <= marked.length <= n - 1
        //0 <= s, marked[i] <= n - 1
        //s != marked[i]
        //marked[i] != marked[j] for every i != j
        //The graph might have repeated edges.
        //The graph is generated such that it has no self-loops.
        //  + Length <=10^4 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //- Dijikstra
        //
        //
        //1.1, Case
        //1.2, Optimization
        //- We don't need to traverse all of nodes in (the marked array)
        //  ==> Just the node with min distance
        //- Adding all of nodes in marked array to the set
        //  + If we go to the node exists in the marked array
        //  ==> Return (node) immediately
        //
        //- Use (dist) array rather than using (visited) array
        //==> Compare:
        //  + (x>=dist[y]) continue
        //
        //1.3, Complexity
        //- Space: O(n+m)
        //- Time: O(n+m+n*log(m))
        //
        int n = 4;
        Integer[][] edges = {{0,1,1},{1,2,3},{2,3,2},{0,3,4}};
        int s = 0;
        int[] marked = {2,3};
        List<List<Integer>> edgeList=new ArrayList<>();
        for(Integer[] e: edges){
            edgeList.add(Arrays.asList(e));
        }
        System.out.println(minimumDistance(n, edgeList, s, marked));
        System.out.println(minimumDistanceRefer(n, edgeList, s, marked));
        //
        //#Reference:
        //2512. Reward Top K Students
        //1630. Arithmetic Subarrays
        //1908. Game of Nim
        //2277. Closest Node to Path in Tree
        //2105. Watering Plants II
        //2219. Maximum Sum Score of Array
    }
}
