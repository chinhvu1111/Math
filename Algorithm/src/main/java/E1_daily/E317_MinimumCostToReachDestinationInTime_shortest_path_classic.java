package E1_daily;

import java.util.*;

public class E317_MinimumCostToReachDestinationInTime_shortest_path_classic {

    public static int minCost(int maxTime, int[][] edges, int[] passingFees) {
        PriorityQueue<int[]> minHeap=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        HashMap<Integer, Set<int[]>> graph=new HashMap<>();
        int maxNode=0;

        for(int[] e: edges){
            Set<int[]> adj=graph.getOrDefault(e[0], new HashSet<>());
            adj.add(new int[]{e[1], e[2]});
            graph.put(e[0], adj);

            Set<int[]> adj1=graph.getOrDefault(e[1], new HashSet<>());
            adj1.add(new int[]{e[0], e[2]});
            graph.put(e[1], adj1);
            maxNode=Math.max(maxNode, Math.max(e[0], e[1]));
        }
        if(maxNode==0){
            return passingFees[0];
        }
        //[Node, fee, cost]
        minHeap.add(new int[]{0, passingFees[0], 0});
        int[] cost=new int[maxNode+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[0]=0;

        while(!minHeap.isEmpty()){
            int[] curNode = minHeap.poll();
//            System.out.println(curNode[0]);
            Set<int[]> adj=graph.getOrDefault(curNode[0], new HashSet<>());
            cost[curNode[0]]=curNode[2];
            if(curNode[0]==maxNode){
                return curNode[1];
            }

            for(int[] nextNode: adj){
                if(curNode[2]+nextNode[1]<=maxTime&&cost[nextNode[0]]>curNode[2]+nextNode[1]){
                    minHeap.add(new int[]{nextNode[0], curNode[1]+passingFees[nextNode[0]], curNode[2]+nextNode[1]});
                    cost[nextNode[0]]=curNode[2]+nextNode[1];
                }
            }
        }
        return -1;
    }

    static List<int []> [] graph;

    public static int minCostRefer(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        graph = new List [n];

        int [] minTime = new int [n];

        Arrays.fill(minTime, Integer.MAX_VALUE);

        for (int i = 0; i < n; ++i){
            graph[i] = new ArrayList<>();
        }

        for (int [] edge : edges){
            graph[edge[0]].add(new int [] {edge[1], edge[2] });
            graph[edge[1]].add(new int [] {edge[0], edge[2] });
        }

        PriorityQueue<int []> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        queue.add(new int [] {0, passingFees[0], 0});

        // node, fee, time spent
        int [] current;
        int size;

        int time, score;

        while (!queue.isEmpty()){
            current = queue.poll();

            if (current[2] >= minTime[current[0]])
                continue;

            minTime[current[0]] = current[2];

            if (current[0] == n - 1)
                return current[1];

            for (int [] next : graph[current[0]]){
                time = current[2] + next[1];
                score = current[1] + passingFees[next[0]];

                if (time > maxTime)
                    continue;
                else if (time > minTime[next[0]])
                    continue;

                queue.add(new int [] { next[0], score, time });
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is a country of n cities numbered from 0 to n - 1
        // where all the cities are connected by (bi-directional roads).
        //- The roads are represented as a 2D integer array edges
        // where edges[i] = [xi, yi, timei] denotes a road between cities (xi and yi) that takes (time-i minutes) to travel.
        //- There may be (multiple roads) of (differing travel times) connecting (the same two cities),
        // but no road connects a city to itself.
        //- (Each time) you pass through a city, you must pay (a passing fee).
        //
        //- This is represented as (a 0-indexed integer array passingFees of length n)
        // where passingFees[j] is the amount of dollars you must pay when you pass through (city j).
        //- In the beginning, you are at (city 0) and want to reach (city n - 1) in (maxTime) minutes or less.
        //- The cost of your journey is the summation of passing fees for each city
        // that you passed through at some moment of your journey (including the source and destination cities).
        //- Given maxTime, edges, and passingFees,
        //* return (the minimum cost) to complete your journey, or -1 if you cannot complete it within maxTime minutes.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //* Brainstorm:
        //
        //Ex:
        //
        //Input: maxTime = 30,
        // edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
        //Output: 11
        //Explanation: The path to take is 0 -> 1 -> 2 -> 5, which takes 30 minutes and has $11 worth of passing fees.
        //
        //- Use dijikstra
        //- Create cost array:
        //  + We need to recalculate the old node if:
        //      + current cost[node] < cost_prev + current cost
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n+n*log(e))
        //
        int maxTime = 30;
        int[][] edges = {{0,1,10},{1,2,10},{2,5,10},{0,3,1},{3,4,10},{4,5,15}};
        int[] passingFees = {5,1,2,20,20,3};
        System.out.println(minCost(maxTime, edges, passingFees));
        System.out.println(minCostRefer(maxTime, edges, passingFees));
    }
}
