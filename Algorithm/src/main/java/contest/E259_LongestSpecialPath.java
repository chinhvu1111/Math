package contest;

import java.util.*;

public class E259_LongestSpecialPath {

    public static void solution(
            HashMap<Integer, List<Integer>> valToIndex, int depth,
            int node, int previous, int[] prefixSum,
            int curSum, int[] nums, HashMap<Integer, List<int[]>> graph,
            int start, int[] minNodeNum, int[] maxLengthSpecialPath){
        List<int[]> adj=graph.get(node);

        for(int[] e: adj){
            if(e[0]==previous){
                continue;
            }
            //Add e[1] to the current list node
            List<Integer> prevIndices = valToIndex.get(nums[e[0]]);
            int newStart = start;
            int curLength = curSum+e[1];
            prefixSum[depth+1]=curSum+e[1];
            if(!prevIndices.isEmpty()){
                int previousIndex = Math.max(prevIndices.get(prevIndices.size()-1)+1, start);
                newStart=Math.max(newStart, previousIndex);
                curLength-=prefixSum[previousIndex];
            }else{
                curLength-=prefixSum[start];
            }
            prevIndices.add(depth+1);
//            System.out.printf("%s %s\n", curLength, depth-newStart+2);
            //Update index
            valToIndex.put(nums[e[0]], prevIndices);
            if(maxLengthSpecialPath[0]<curLength){
                maxLengthSpecialPath[0]=curLength;
                minNodeNum[0]=depth-newStart+2;
            }else if(maxLengthSpecialPath[0]==curLength&&minNodeNum[0]>depth-newStart+2){
                minNodeNum[0]=depth-newStart+2;
            }
            solution(valToIndex, depth+1, e[0], node, prefixSum, curSum+e[1], nums, graph, newStart, minNodeNum, maxLengthSpecialPath);
            //If we remove this ==> this can make the impaction on the map of the previous value
            //      0 (*)
            //     /
            //    1
            //     \
            //      0 (remove(0)) ==> Remove(*) at the same time.
//            valToIndex.remove(nums[e[0]]);
            prevIndices.remove(prevIndices.size()-1);
            valToIndex.put(nums[e[0]], prevIndices);
//            System.out.printf("%s %s\n", valToIndex, depth);
            prefixSum[depth+1]=0;
        }
    }

    public static int[] longestSpecialPath(int[][] edges, int[] nums) {
        int[] minNodeNum = new int[]{Integer.MAX_VALUE};
        int[] maxLengthSpecialPath = new int[]{Integer.MIN_VALUE};
//        System.out.printf("Init: %s %s\n", maxLengthSpecialPath[0], minNodeNum[0]);
        int m=edges.length;
        int n= nums.length;
        HashMap<Integer, List<int[]>> tree=new HashMap<>();

        //Time: O(n)
        for (int i = 0; i < n; i++) {
            tree.put(i, new ArrayList<>());
        }
        //Time: O(m)
        for(int i=0;i<m;i++){
            List<int[]> adj=tree.get(edges[i][0]);
            adj.add(new int[]{edges[i][1], edges[i][2]});
            tree.put(edges[i][0], adj);
            List<int[]> adj1=tree.getOrDefault(edges[i][1], new ArrayList<>());
            adj1.add(new int[]{edges[i][0], edges[i][2]});
            tree.put(edges[i][1], adj1);
        }
        HashMap<Integer, List<Integer>> valToIndex=new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<int[]> adj = tree.get(i);
            adj.sort((a, b) -> Integer.compare(a[0], b[0]));  // Ensure consistent traversal order
        }
        for (int i = 0; i < n; i++) {
            valToIndex.put(nums[i], new ArrayList<>());
        }
//        int root=-1;
        int root=0;
//        for(Integer e: tree.keySet()){
//            if(tree.get(e).size()==1){
//                root=e;
//                break;
//            }
//        }
//        System.out.println(root);
        List<Integer> initRoot=new ArrayList<>();
        initRoot.add(0);
        valToIndex.put(nums[root], initRoot);
        solution(valToIndex, 0, root, -1, new int[n], 0, nums, tree, 0, minNodeNum, maxLengthSpecialPath);
//        valToIndex.remove(nums[root]);
        return new int[]{maxLengthSpecialPath[0], minNodeNum[0]};
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an undirected "tree") rooted at (node 0) with n nodes numbered from (0 to n - 1),
        // represented by a 2D array edges of length n - 1,
        // where edges[i] = [ui, vi, length-i] indicates an edge between (nodes ui and vi with length ("length-i")).
        //- You are also given an integer array nums, where nums[i] represents the value at (node i).
        //- A special path is defined as (a downward path) from (an ancestor node) to (a descendant node)
        // such that (all the values of the nodes) in that path are (unique).
        //* Note that (a path) may (start and end) (at the same node).
        //* Return an array result of size 2, where result[0] is (the length of the longest ("special path")),
        // and result[1] is (the minimum number of nodes) in (all possible longest "special paths").
        //
        //Example 1:
        //
        //Input: edges = [[0,1,2],[1,2,3],[1,3,5],[1,4,4],[2,5,6]], nums = [2,1,2,1,3,1]
        //Output: [6,2]
        //Explanation:
        //- In the image below, nodes are colored by their corresponding values in nums
        //- The longest special paths are 2 -> 5 and 0 -> 1 -> 4, both having a length of 6.
        //- The minimum number of nodes across all longest special paths is 2.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= n <= 5 * 10^4
        //edges.length == n - 1
        //edges[i].length == 3
        //0 <= ui, vi < n
        //1 <= lengthi <= 10^3
        //nums.length == n
        //0 <= nums[i] <= 5 * 10^4
        //The input is generated such that edges represents (a valid tree).
        //  + n <= 5*10^4 ==> Time: O(n*k)
        //
        //- Brainstorm
        //- Find root:
        //  + indegree[i]=0
        //      + These nodes may be (leaf/ root) node
        //Ex:
        //[(1,2,3),2,4,5]
        //[1,2,(3,2),4,5]
        //- (Max size) of subarray such that (the elements of the array) are (unique)
        //  + Use hashmap index for that.
        //  + Index as the depth of the tree
        //  + start, end
        //- How to reuse the previous calculated value?
        //
        //              0
        //              |
        //              |
        //             1
        //        /    |  \
        //       /     |   \
        //      2      5    4
        //      |
        //      |
        //      6
        //- First | Second
        // (1,2,3)(4,5,3)
        //- Space = Time = dp[n][n]
        //
//        int[][] edges = {{0,1,2},{1,2,3},{1,3,5},{1,4,4},{2,5,6}};
//        int[] nums = {2,1,2,1,3,1};
//        int[][] edges = {{0,1,2},{1,2,3},{1,3,5},{1,4,4},{2,5,6}};
//        int[] nums = {2,1,2,1,3,1};
//        int[][] edges = {{0,1,2},{1,2,3},{1,3,5},{1,4,4},{2,5,6}};
//        int[] nums = {2,1,2,1,3,1};
//        int[][] edges = {{0,1,8}};
//        int[] nums = {2,2};
//        for (int i = 0; i < 10; i++) {
//            int[] rs= longestSpecialPath(edges, nums);
//            System.out.printf("%s %s\n", rs[0], rs[1]);
//        };
//        int[][] edges1 = {{0,1,2},{1,2,3},{1,3,5},{1,4,4},{2,5,6}};
//        int[] nums1 = {2,1,2,1,3,1};
//        int[][] edges1 = {{1,0,7},{1,2,4}};
//        int[] nums1 = {1,1,3};
        //          1 [1]
        //        /  \
        //     0 [1]  2 [3]
//        int[][] edges = {{0,2,3},{1,2,2}};
//        int[] nums = {2,5,5};
        //0 -> 2 -> 1
        //2 -> 5 -> 5
        //          0
        //        / (3)
        //      2
        //        \ (2)
        //         1
        //- Sai do root=0
        //  + Không phải tự tìm root
//        int[][] edges = {{1,0,4},{0,2,9}};
//        int[] nums = {4,3,1};
        //    1
        //      \ (4)
        //       0
        //      / (9)
        //    2
//        int[][] edges = {{4,0,2},{3,1,8},{3,2,9},{3,4,8}};
//        int[] nums = {5,5,1,3,3};
        // nums  = [5,5,1,3,3]
        //indices= [0,1,2,3,4]
        //          0 [5]
        //          | (2)
        //          4 [3]
        //        / (8)
        //       3 [3]
        //    /(8) \(9)
        //  1 [5]   2 [1]
        //
        int[][] edges = {{3,0,8},{7,12,3},{8,10,2},{9,3,4},{6,10,2},{11,3,3},{2,3,1},{2,6,5},{5,6,2},{4,5,4},{1,12,5},{4,1,2},{4,13,8}};
        int[] nums = {6,5,7,6,9,14,5,2,15,12,5,11,14,5};
        int[] rs1= longestSpecialPath(edges, nums);
        System.out.printf("%s %s\n", rs1[0], rs1[1]);
        //
    }
}
