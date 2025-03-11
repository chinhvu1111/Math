package contest;

import java.util.*;

public class E280_NumberOfGoodPaths_hard_classic {

    public static int[] findParent(int[] parent, int node){
        int curNode = node;
        int curDepth=0;

        while(curNode!=parent[curNode]){
            curNode = parent[curNode];
            curDepth++;
        }
        parent[node]=curNode;
        //- Depth is not real depth ==> If we want to store the actual dept
        //  + We can use the array as depth
        return new int[]{curNode, curDepth};
    }

    public static void union(int u, int v, int[] parent){
        int[] parentU=findParent(parent, u);
        int[] parentV=findParent(parent, v);
        if(parentU[0]!=parentV[0]){
            if(parentU[1]>=parentV[1]){
                parent[parentV[0]]=parentU[0];
            }else{
                parent[parentU[0]]=parentV[0];
            }
        }
    }

    public static int numberOfGoodPaths(int[] vals, int[][] edges) {
        int n=edges.length;
        int m=vals.length;
        //Space: O(n+m)
        HashMap<Integer, List<Integer>> adj=new HashMap<>();
        TreeMap<Integer, List<Integer>> sortValToNodes=new TreeMap<>();

        for(int i=0;i<m;i++){
            sortValToNodes.computeIfAbsent(vals[i], value -> new ArrayList<Integer>()).add(i);
            adj.put(i, new ArrayList<>());
        }
        for(int[]e : edges){
            List<Integer> curAdj=adj.getOrDefault(e[0], new ArrayList<>());
            curAdj.add(e[1]);
            adj.put(e[0], curAdj);
            List<Integer> curAdj1=adj.getOrDefault(e[1], new ArrayList<>());
            curAdj1.add(e[0]);
            adj.put(e[1], curAdj1);
        }
//        System.out.println(adj);
//        System.out.println(sortValToNodes);

        int[] parent=new int[m];
        for (int i = 0; i < m; i++) {
            parent[i]=i;
        }
        int rs=0;
        //Time: O(n*log(n))
        for(Map.Entry<Integer, List<Integer>> e: sortValToNodes.entrySet()){
             List<Integer> listNodes = e.getValue();

             //1: [0,1]
            //Time: O(n)
             for(int node: listNodes){
                 List<Integer> neighbors=adj.get(node);
//                 System.out.printf("node: %s, neighbor: %s\n", node, neighbors);
                 for(int neighbor: neighbors){
                     if(vals[neighbor]<=vals[node]){
//                         System.out.printf("Union: %s %s\n", neighbor, node);
                         union(neighbor, node, parent);
                     }
                 }
             }
             HashMap<Integer, Integer> groupCount=new HashMap<>();
//             for(int i=0;i<m;i++){
            //Only traverse the node with the current value
             for(int node: listNodes){
                 int curParent = findParent(parent, node)[0];
                 groupCount.put(curParent, groupCount.getOrDefault(curParent,0)+1);
             }
//             System.out.printf("%s %s\n", curNode, groupCount);
             for(Map.Entry<Integer, Integer> g: groupCount.entrySet()){
                 rs+=g.getValue()*(g.getValue()+1)/2;
             }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is a tree (i.e. a connected, undirected graph with no cycles) consisting of n nodes numbered from
        //  + (0 to n - 1) and exactly (n - 1 edges).
        //- You are given a 0-indexed integer array vals of length n where vals[i] denotes the value of the (ith node).
        //- You are also given a 2D integer array edges where edges[i] = [ai, bi]
        // denotes that there exists an undirected edge connecting (nodes ai and bi).
        //* (A good path is a simple path that satisfies the following conditions:
        //  + The (starting node) and the (ending node) have the ("same" value).
        //  + All nodes between (the starting node) and (the ending node) have values (less than or equal) to the (starting node)
        // (i.e. the starting node's value should be the maximum value along the path).
        //* Return the number of (distinct) good paths.
        //* Note that
        //  + a path and (its reverse) are counted as (the same path).
        // For example, 0 -> 1 is considered to be the same as 1 -> 0.
        //  + (A single node) is also considered as (a valid path).
        //
        //Ex:
        //Input: vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
        //Output: 6
        //Explanation: There are 5 good paths consisting of a single node.
        //There is 1 additional good path: 1 -> 0 -> 2 -> 4.
        //(The reverse path 4 -> 2 -> 0 -> 1 is treated as the same as 1 -> 0 -> 2 -> 4.)
        //Note that 0 -> 2 -> 3 is not a good path because vals[2] > vals[0].
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == vals.length
        //1 <= n <= 3 * 10^4
        //0 <= vals[i] <= 10^5
        //edges.length == n - 1
        //edges[i].length == 2
        //0 <= ai, bi < n
        //ai != bi
        //edges represents a valid tree.
        //  + Length <= 3*10^4 ==> Time: O(n)
        //
        //- Brainstorm
        //Ex:
        //Input: vals = [1,3,2,1,3], edges = [[0,1],[0,2],[2,3],[2,4]]
        //Output: 6
        //Explanation: There are 5 good paths consisting of a single node.
        //There is 1 additional good path: 1 -> 0 -> 2 -> 4.
        //(The reverse path 4 -> 2 -> 0 -> 1 is treated as the same as 1 -> 0 -> 2 -> 4.)
        //Note that 0 -> 2 -> 3 is not a good path because vals[2] > vals[0].
        //
        //- No cycle:
        //  + Between 2 nodes:
        //      + We have only 1 path
        //
        //0 -> (1 -> 2 -> 3) -> 4
        //  + [1,2,3] could be a good path
        //- Sub problem:
        //  + Count the number of good path in the array:
        //Ex:
        //arr = [1,3,2,1,3]
        //  + How to find a good path?
        //      ==> We need to find the max value of [i,j] in the array
        //- max[i][j] == arr[i] && max[i][j] == arr[j]:
        //  + rs++
        //- Process from small to big?
        //  + 1 -> go to 2,3,...
        //      + If we continue to go ==> It is not valid
        //Ex:
        //x=3
        // [3,2,1,3]
        //  ==> After traversing this range
        //x=4
        //  + We don't need to traverse again
        // [4,(3,2,1,3),4]
        //
        //** Main idea:
        //- UNION FIND
        //
        //- (the current node) can go to the (smaller node)
        //- How to determine the (starting/ending) node:
        //Ex:
        //(3,x,x,x,x,3),x,x,6,5,(3,x,x,x,3)
        //  + How to traverse this graph
        //- Add 3 ==> Traverse until meet 3:
        //  + rs++
        //- How about:
        //Ex:
        //(3,x,x,x,x,3),x,x,(3,x,x,x,3)
        //  + Number of group = 4 (UNION FIND)
        //  + rs = 6(combination) + 4(single) = 4*(4+1)/2 = 10
        //  But if we add 3 and traverse ==> we only have (rs = 2)
        //==> Add 3 but we (don't mark) the ending node with 3 value as (visited)
        //Ex:
        //(3,x,x,x,x,3),6,5,(3,x,x,3),8,9,(3,x,x,x,3)
        //** (the number of groups):
        //  + How many parent we have ==> sum(count[node])
        //      + rs+=count[node]*(count[node]+1)
        //
        //  + We can not count the value
        //  ==> Because the values are (duplicated)
        //
        //1.1, Special cases
//        int[]vals = {1,3,2,1,3};
//        int[][] edges = {{0,1},{0,2},{2,3},{2,4}};
//        int[]vals = {1};
//        int[][] edges = {};
        int[]vals = {1,1,2,2,3};
        int[][] edges = {{0,1},{1,2},{2,3},{2,4}};
        System.out.println(numberOfGoodPaths(vals, edges));
        //
        //1.2, Optimization
        //1.3, Complexity
        //- Space: O(n+m)
        //- Time: O(n*log(n))
        //
        //#Reference:
        //1724. Checking Existence of Edge Length Limited Paths II - HARD
        //1763. Longest Nice Substring
        //2179. Count Good Triplets in an Array - HARD
    }
}
