package E1_daily;

import java.util.*;

public class E334_MaximizeTheNumberOfTargetNodesAfterConnectingTreesII {

    public static List<int[]> getDistanceEvenAndOdd(HashMap<Integer, HashSet<Integer>> tree, int n){
        Queue<int[]> nodes=new LinkedList<>();
        boolean[] visited=new boolean[n];
        visited[0]=true;
        int numEvenDistance=1;
        int numOddDistance=0;
        nodes.add(new int[]{0, 0});
        System.out.println(n);

        //    1
        //2 2 2 3 5
        //==>
        int[] distance=new int[n];
        int[] evenDist=new int[n];
        int[] oddDist=new int[n];
        while(!nodes.isEmpty()){
            int[] curNode = nodes.poll();
            visited[curNode[0]]=true;
            HashSet<Integer> adj= tree.getOrDefault(curNode[0], new HashSet<>());
            for(Integer nextNode: adj){
                if(visited[nextNode]){
                    continue;
                }
                visited[nextNode]=true;
                nodes.add(new int[]{nextNode, curNode[1]+1});
                distance[nextNode]=curNode[1]+1;
                if((curNode[1]+1)%2==0){
                    numEvenDistance++;
                }else{
                    numOddDistance++;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if(distance[i]%2==0){
                evenDist[i]=numEvenDistance;
                oddDist[i]=numOddDistance;
            }else{
                oddDist[i]=numEvenDistance;
                evenDist[i]=numOddDistance;
            }
//            System.out.printf("Even: %s,", evenDist[i]);
//            System.out.printf("Odd: %s, ", oddDist[i]);
        }
//        System.out.println("\n");
        List<int[]> rs=new ArrayList<>();
        rs.add(evenDist);
        rs.add(oddDist);
        return rs;
    }

    public static int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        //Root is 0
        //
        int n=0;
        int m=0;
        HashMap<Integer, HashSet<Integer>> tree1=new HashMap<>();
        HashMap<Integer, HashSet<Integer>> tree2=new HashMap<>();

        for(int[] e: edges1){
            HashSet<Integer> adj=tree1.getOrDefault(e[0], new HashSet<>());
            adj.add(e[1]);
            tree1.put(e[0], adj);
            HashSet<Integer> adj1=tree1.getOrDefault(e[1], new HashSet<>());
            adj1.add(e[0]);
            tree1.put(e[1], adj1);
            n=Math.max(n, Math.max(e[0], e[1]));
        }
        for(int[] e: edges2){
            HashSet<Integer> adj=tree2.getOrDefault(e[0], new HashSet<>());
            adj.add(e[1]);
            tree2.put(e[0], adj);
            HashSet<Integer> adj1=tree2.getOrDefault(e[1], new HashSet<>());
            adj1.add(e[0]);
            tree2.put(e[1], adj1);
            m=Math.max(m, Math.max(e[0], e[1]));
        }
        n++;
        m++;
        List<int[]> dist1=getDistanceEvenAndOdd(tree1, n);
        List<int[]> dist2=getDistanceEvenAndOdd(tree2, m);
        int maxNumEven1=0, maxNumOdd1=0;
        int maxNumEven2=0, maxNumOdd2=0;

        for(int i=0;i<n;i++){
            maxNumEven1=Math.max(maxNumEven1, dist1.get(0)[i]);
            maxNumOdd1=Math.max(maxNumOdd1, dist1.get(1)[i]);
        }
        for(int i=0;i<m;i++){
            maxNumEven2=Math.max(maxNumEven2, dist2.get(0)[i]);
            maxNumOdd2=Math.max(maxNumOdd2, dist2.get(1)[i]);
        }
        int[] dist=new int[n];
        for(int i=0;i<n;i++){
            //even+1+odd
            //odd+1+even
            dist[i]=dist1.get(0)[i]+maxNumOdd2;
//            dist[i]=Math.max(dist[i], maxNumOdd1+dist2.get(0)[i]);
        }
        return dist;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There exist two (undirected trees) with n and m nodes, labeled from [0, n - 1] and [0, m - 1], respectively.
        //- You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively,
        // where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree
        // and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
        //- Node u is (target) to node v if (the number of edges) on the path from u to v is (even).
        //* Note that a node is always target to itself.
        //* Return an array of n integers answer, where answer[i] is (the maximum) possible (number of nodes)
        // that are target to (node i) of (the first tree) if you had to connect (one node)
        // from the first tree to another node in the second tree.
        //* Note that queries are independent from each other.
        //- That is, for every query you will remove the added edge before proceeding to the next query.
        //
        //Example 1:
        //
        //Input: edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]]
        //
        //Output: [8,7,7,8,8]
        //
        //Explanation:
        //
        //For i = 0, connect node 0 from the first tree to node 0 from the second tree.
        //For i = 1, connect node 1 from the first tree to node 4 from the second tree.
        //For i = 2, connect node 2 from the first tree to node 7 from the second tree.
        //For i = 3, connect node 3 from the first tree to node 0 from the second tree.
        //For i = 4, connect node 4 from the first tree to node 4 from the second tree.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= n, m <= 10^5
        //edges1.length == n - 1
        //edges2.length == m - 1
        //edges1[i].length == edges2[i].length == 2
        //edges1[i] = [ai, bi]
        //0 <= ai, bi < n
        //edges2[i] = [ui, vi]
        //0 <= ui, vi < m
        //The input is generated such that edges1 and edges2 represent valid trees.
        //  + 2 <= n, m <= 10^5 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //- Add 1 edges ==> Plus 1
        //- How many path for each vertex such that this path contains (odd/even) edges?
        //
        //          1
        //       /   \    \
        //     2      3    4
        //   /   \
        // 5      6
        //==> We can find (the medium node) as (the root)
        //
        //- For finding all path between two of vertices in the tree
        //  + ==> This will be took O(n^2) ==> Big
        //- even[u] is (the number of nodes) at the (even) distance
        //
        //- Choose the root node
        //- dist_even[i]
        //  + If(root -> i == even):
        //      + dist_even[i] = (the number of even path)
        //  + If(root -> i == odd):
        //      + dist_even[i] = (the number of odd path)
        //==> Vice versa
        //
        //- answer[i] = even[i]+ max(odd[1], odd[2], …, odd[m - 1])
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n+m)
        //- Space: O(n+m)
        //
        ///#Reference:
        //563. Binary Tree Tilt - EASY
        //3241. Time Taken to Mark All Nodes - MED
        //1810. Minimum Path Cost in a Hidden Grid - HARD
        //
        int[][] edges1 = {{0,1},{0,2},{2,3},{2,4}}, edges2 = {{0,1},{0,2},{0,3},{2,7},{1,4},{4,5},{4,6}};
        int[] dist=maxTargetNodes(edges1, edges2);
        for (int i = 0; i < dist.length; i++) {
            System.out.printf("%s,", dist[i]);
        }
    }
}
