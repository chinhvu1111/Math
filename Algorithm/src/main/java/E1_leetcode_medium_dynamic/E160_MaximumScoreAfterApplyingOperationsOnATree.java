package E1_leetcode_medium_dynamic;

import java.util.*;

public class E160_MaximumScoreAfterApplyingOperationsOnATree {

    public static Long sumScore(int node, boolean[] visited, HashMap<Integer, HashSet<Integer>> adjNodes, int[] values, long[] sumScore){
        if(sumScore[node]!=-1){
            return sumScore[node];
        }
        HashSet<Integer> adjs = adjNodes.get(node);
        long curScore=values[node];

        for(Integer nextNode: adjs){
//            if(nextNode==prevNode){
//                continue;
//            }
            if(visited[nextNode]){
               continue;
            }
            visited[nextNode]=true;
            curScore+=sumScore(nextNode, visited, adjNodes, values, sumScore);
            visited[nextNode]=false;
        }
        return sumScore[node]=curScore;
    }

    public static long solution(
            int node,
            boolean[] visited,
            HashMap<Integer, HashSet<Integer>> adjNodes,
            int[] values, long[] dp, long[] sumScore){
        if(dp[node]!=-1){
            return dp[node];
        }
        HashSet<Integer> adjs= adjNodes.get(node);
        long deleteScore=values[node];
        long nonDeleteScore=0;
        boolean isLeafNode=true;

        for(Integer nextNode: adjs){
            if(visited[nextNode]){
                continue;
            }
            isLeafNode=false;
            visited[nextNode]=true;
            //Non delete current node --> delete all remaining nodes
            long nonDeletedNodeScore = sumScore(nextNode, visited, adjNodes, values, sumScore);
            nonDeleteScore+=nonDeletedNodeScore;
            visited[nextNode]=false;
        }
        for(Integer nextNode: adjs){
//            if(nextNode==prevNode){
//                continue;
//            }
            if(visited[nextNode]){
                continue;
            }
            visited[nextNode]=true;
            //Delete current node --> depend
            long deletedNodeScore = solution(nextNode, visited, adjNodes, values, dp, sumScore);
            visited[nextNode]=false;
            deleteScore+=deletedNodeScore;
        }
        if(isLeafNode){
            return 0;
        }
        return dp[node]=Math.max(deleteScore, nonDeleteScore);
    }

    public static long maximumScoreAfterOperations(int[][] edges, int[] values) {
        HashMap<Integer, HashSet<Integer>> adjNodes= new HashMap<>();
        int n=values.length;
        long[] sumScore=new long[n];
        long[] dp=new long[n];
        boolean[] visited=new boolean[n];
        Arrays.fill(sumScore, -1);
        Arrays.fill(dp, -1);

        for(int[] e: edges){
            HashSet<Integer> curSet= adjNodes.getOrDefault(e[0],new HashSet<>());
            HashSet<Integer> curSet1= adjNodes.getOrDefault(e[1],new HashSet<>());
            curSet.add(e[1]);
            curSet1.add(e[0]);
            adjNodes.put(e[0], curSet);
            adjNodes.put(e[1], curSet1);
        }
        visited[0]=true;
        return solution(0, visited, adjNodes, values, dp, sumScore);
    }

    public static long dfs(int node, int parent, HashMap<Integer, HashSet<Integer>> adjNodes, int[] values){
        HashSet<Integer> adj=adjNodes.get(node);

        if(adj.size()==1&&node!=0){
            return values[node];
        }
        long curMin=values[node];
        long nextMinSum=0;

        for(Integer nextNode: adj){
            if(parent==nextNode){
                continue;
            }
            nextMinSum+=dfs(nextNode, node, adjNodes, values);
            //Pruning
            if(curMin<=nextMinSum){
                return curMin;
            }
        }
        return Math.min(curMin, nextMinSum);
    }


    public static long maximumScoreAfterOperationsReference(int[][] edges, int[] values) {
        HashMap<Integer, HashSet<Integer>> adjNodes= new HashMap<>();
        long sum=0;

        for(int e: values){
            sum+=e;
        }

        for(int[] e: edges){
            HashSet<Integer> curSet= adjNodes.getOrDefault(e[0],new HashSet<>());
            HashSet<Integer> curSet1= adjNodes.getOrDefault(e[1],new HashSet<>());
            curSet.add(e[1]);
            curSet1.add(e[0]);
            adjNodes.put(e[0], curSet);
            adjNodes.put(e[1], curSet1);
        }
        return sum-dfs(0, -1, adjNodes, values);
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is (an undirected tree) with n nodes labeled from (0 to n - 1),
        // and (rooted at node 0).
        // You are given a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
        //You are also given a 0-indexed integer array values of length n, where values[i] is the value associated with (the ith node).
        //You start with (a score of 0).
        // In one operation, you can:
        //- Pick any node i.
        //  + Add (values[i]) to your score.
        //  + Set values[i] to 0.
        //- (A tree is healthy) if (the sum of values) on the path from the ((root) to (any leaf node)) is different than (zero).
        //* Return (the maximum score) you can obtain after performing these operations on the tree (any number of times) so that it remains (healthy).
        //
        //Example 1:
        //Input: edges = [[0,1],[0,2],[0,3],[2,4],[4,5]], values = [5,2,5,2,1,1]
        //Output: 11
        //Explanation: We can choose nodes 1, 2, 3, 4, and 5. The value of the root is non-zero.
        // Hence, the sum of values on the path from the root to any leaf is different than zero.
        // Therefore, the tree is healthy and the score is values[1] + values[2] + values[3] + values[4] + values[5] = 11.
        //It can be shown that 11 is the maximum score obtainable after any number of operations on the tree.
        //--> Get (max score) cho tree (vẫn healthy)
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //2 <= n <= 2 * 10^4
        //edges.length == n - 1
        //edges[i].length == 2
        //0 <= ai, bi < n
        //values.length == n
        //1 <= values[i] <= 10^9
        //The input is generated such that edges represents a valid tree.
        //+ Số lượng nodes khá lớn
        //+ value trong danh sách node > 1 (Positive value)
        //+ n nodes (0 --> n-1):
        //  + Thế nên có thể lưu dp[n]
        //
        //- Brainstorm
        //- Xoá các nodes như thế nào?
        //  + Sum của root --> ((Any leaf node)!=0)
        //
        //- Giữa các path từ root --> leaf node có thể:
        //  + Chung nhau node
        //- Để sum mọi path từ (root --> leaf !=0)
        //==> Mỗi path sẽ chỉ để lại 1 node thôi
        //- 2 trees cùng chung root, thì có thể xoá ntn?
        //E.g
        //      1
        //      |
        //      2 ==> Nếu delete 2 ==> score lúc này sẽ là (all sum bên dưới)
        //    /  \
        //   3    5
        //  |     |
        //  6     8
        //
        //- Tại 1 node ta có 2 cases:
        //  + Not delete
        //  + Delete
        //- Nếu not delete:
        //  + Tất cả các node nối với node đó
        //  ==> Ta có thể lấy hết score
        //- Nếu Delete
        //  ==> Ta cần xét tiếp
        //
        //- Mỗi call function
        //  ==> Ta cần check 2 trường hợp
        //
        //---
        //solution():
        //  + Delete: solution() + curScore
        //  + Not delete : ==> return sum
        //
        //- Vấn đề ở đây là:
        //  - Đằng trước không delete --> All sum (OK)
        //  - Đằng trước delete --> Đằng sau mỗi path phải có 1 thằng bị delete.
        //* Nếu đi sâu thêm thì tức là:
        //  - Những node đằng trước chưa delete.
        //  ==> Phải delete
        //- Nếu ta traverse được đến leaf node
        //  ==> The leaf node must be deleted
        //
        //* Kinh nghiệm:
        //- Tìm max --> tức là tìm min X:
        //  <=> max = sum - x
        //
        //- X min tức là tìm MIN mỗi path thoả mãn:
        //  + sum các node root chỉ cộng 1 lần
        //  + Sum các nodes !=root thì cộng riêng
        //      1
        //    /   \
        //   2     5
        // /  \      \
        //3   4       6
        //- Đi ngược từ leaf --> root
        //- Ta sẽ so sánh:
        //  + MIN(values[3]+values[4], values[2])
        //  ==> Nhờ xét min mà root node sẽ chỉ được + 1 lần.
        //
        //* Kinh nghiệm trong tree không có cycle:
        //- Nên để không đi lại node cũ
        //  ==> check (parentNode != childNode) là được.
        //- Để biết 1 node là leaf node:
        //  + Bậc của nó <-> (số đỉnh đến current node == 1)
        //  + (Số đỉnh kề == 1)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(V+E)
        //- Time : O(V+E)
        //
        //#Reference:
        //2603. Collect Coins in a Tree
        //
//        int[][] edges= {{0,1},{0,2},{0,3},{2,4},{4,5}};
//        int[] values = {5,2,5,2,1,1};
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6}};
        int[] values = {20,10,9,7,4,3,5};
        System.out.println(maximumScoreAfterOperations(edges, values));
        System.out.println(maximumScoreAfterOperationsReference(edges, values));
    }
}
