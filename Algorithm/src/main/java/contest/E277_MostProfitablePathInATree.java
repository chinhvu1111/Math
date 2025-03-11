package contest;

import java.util.*;

public class E277_MostProfitablePathInATree {

    public static void solutionDFSAlice(
            int prevNode,
            int node, HashMap<Integer, List<Integer>> graph,
            int cost, int[] depthBob, int curDepth, int[] amount, int[] rs, int depthOfBob){
        List<Integer> adj=graph.get(node);
        int curCost=cost;
        // x --- x --- x --- x
        // x --- x --- x
        if(depthBob[node]!=-1){
            if(curDepth<= ((float)depthOfBob+1) /2){
                if(curDepth==((float)depthOfBob+1)/2){
                    curCost+=amount[node]/2;
                }else if(curDepth<((float)depthOfBob+1)/2){
                    curCost+=amount[node];
                }
            }else if(curDepth>depthOfBob){
                curCost+=amount[node];
            }
        }else{
            curCost+=amount[node];
        }
        boolean isLeaf=true;

        for(int e: adj){
            if(prevNode==e){
                continue;
            }
            isLeaf=false;
            solutionDFSAlice(node, e, graph, curCost, depthBob, curDepth+1, amount, rs, depthOfBob);
        }
        if(isLeaf){
            rs[0]=Math.max(rs[0], curCost);
        }
    }

    public static boolean getDepthBob(int curDepth, int bob, int prevNode, int node, HashMap<Integer, List<Integer>> graph, int[] depth, boolean[] pathBob){
        if(bob==node){
            depth[0]=curDepth;
            pathBob[node]=true;
            return true;
        }
        boolean isInPath=false;
        List<Integer> adj=graph.get(node);
        for (int e: adj){
            if(prevNode==e){
                continue;
            }
            boolean curNodeIsInPath = getDepthBob(curDepth+1, bob, node, e, graph, depth, pathBob);
            if(curNodeIsInPath){
                isInPath=true;
                break;
            }
        }
        if(isInPath){
            pathBob[node]=true;
        }
        return isInPath;
    }

    public static int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        Queue<int[]> queue=new LinkedList<>();
        int n=amount.length;
        HashMap<Integer, List<Integer>> graph=new HashMap<Integer, List<Integer>>();

        for(int[] e: edges){
            int u = e[0];
            int v = e[1];
            List<Integer> list = graph.getOrDefault(u, new ArrayList<>());
            list.add(v);
            graph.put(u, list);
            List<Integer> list1 = graph.getOrDefault(v, new ArrayList<>());
            list1.add(u);
            graph.put(v, list1);
        }
        boolean[] visited=new boolean[n];
        int[] depthBob=new int[n];
        Arrays.fill(depthBob, -1);
        int[] depthOfBob=new int[1];
        boolean[] pathBob=new boolean[n];
        //Mark bob path
        getDepthBob(1, bob, -1, 0, graph, depthOfBob, pathBob);
        queue.add(new int[]{bob, 1});
        depthBob[bob]=1;
        visited[bob]=true;

        while(!queue.isEmpty()){
            int[] curNode = queue.poll();
            List<Integer> adj=graph.get(curNode[0]);
            visited[curNode[0]]=true;

            for(int nextNode: adj){
                if(visited[nextNode]){
                   continue;
                }
                if(!pathBob[nextNode]){
                    continue;
                }
                depthBob[nextNode]=depthBob[curNode[0]]+1;
                queue.add(new int[]{nextNode, depthBob[nextNode]});
                visited[nextNode]=true;
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.printf("i:%s-%s, ", i, depthBob[i]);
//        }
//        System.out.println();
        int[] rs=new int[1];
        rs[0]=Integer.MIN_VALUE;
        solutionDFSAlice(-1,  0, graph, 0, depthBob, 1, amount, rs, depthOfBob[0]);
        return rs[0];
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is (an undirected tree) with n nodes
        //  + labeled from (0 to n - 1), rooted at node 0.
        //- You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates
        // that there is an edge between nodes (ai and bi) in the tree.
        //
        //- At every node i, there is a gate.
        //- You are also given (an array of even integers amount), where amount[i] represents:
        //  + the price needed to open the gate at node i, if amount[i] is negative, or,
        //  + the cash reward obtained on opening the gate at node i, otherwise.
        //- The game goes on as follows:
        //  + Initially, Alice is at node 0 and Bob is at node bob.
        //  + At (every second), Alice and Bob each move to an adjacent node. Alice moves towards some leaf node, while Bob moves towards node 0.
        //  + For every node along their path, Alice and Bob either spend money to open the gate at that node, or accept the reward. Note that:
        //      + If the gate is already open, no price will be required, nor will there be any cash reward.
        //      + If Alice and Bob reach the node simultaneously, they share the price/reward for opening the gate there. In other words, if the price to open the gate is c, then both Alice and Bob pay c / 2 each. Similarly, if the reward at the gate is c, both of them receive c / 2 each.
        //      + If Alice reaches a leaf node, she stops moving.
        //          + Similarly, if Bob reaches node 0, he stops moving.
        //* Note that these events are (independent) of (each other).
        //* Return (the maximum net income) Alice can have if she travels towards the optimal leaf node.
        //
        //Example 1:
        //
        //Input: edges = [[0,1],[1,2],[1,3],[3,4]], bob = 3, amount = [-2,4,2,-4,6]
        //Output: 6
        //Explanation:
        //The above diagram represents the given tree. The game goes as follows:
        //- Alice is initially on node 0, Bob on node 3. They open the gates of their respective nodes.
        //  Alice's net income is now -2.
        //- Both Alice and Bob move to node 1.
        //  Since they reach here simultaneously, they open the gate together and share the reward.
        //  Alice's net income becomes -2 + (4 / 2) = 0.
        //- Alice moves on to node 3. Since Bob already opened its gate, Alice's income remains unchanged.
        //  Bob moves on to node 0, and stops moving.
        //- Alice moves on to node 4 and opens the gate there. Her net income becomes 0 + 6 = 6.
        //Now, neither Alice nor Bob can make any further moves, and the game ends.
        //It is not possible for Alice to get a higher net income.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= n <= 10^5
        //edges.length == n - 1
        //edges[i].length == 2
        //0 <= ai, bi < n
        //ai != bi
        //edges represents a valid tree.
        //1 <= bob < n
        //amount.length == n
        //amount[i] is an even integer in the range [-10^4, 10^4].
        //
        //- Brainstorm
        //- Maximize the income of Alice:
        //  + We don't need to care about the Bob
        //- Both of the traverse using BFS
        //- It is the ("regular") tree
        //- Bob go up
        //- Alice go down
        //Ex:
        //                      1
        //             /  \        \   \
        //           2     3        5   6
        //         /  \   /  \     /  \   \
        //       7     8 9    10  11  12  13
        //- From bob to root:
        //  + We have only 1 path
        //- If they stand at the same node, the cost will be divied
        //- For each node in the path:
        //  + We store the depth for that
        //- At the node-i:
        //  + If depth of alice > depth of the bob:
        //      + cost+=0
        //  + If the depth of alice == depth of the bob:
        //      + cost+=val/2
        //  + If the depth of alice < depth of the bob:
        //      + cost+=val
        //
//        int[][] edges = {{0,1},{1,2},{1,3},{3,4}};
//        int bob = 3;
//        int[] amount = {-2,4,2,-4,6};
//        int[][] edges = {{0,2},{1,8},{1,6},{2,3},{2,4},{3,7},{4,5},{4,9},{6,7}};
//        int bob = 3;
//        int[] amount = {-378,-3744,-638,9938,3818,-336,2722,154,-1750,-1672};
        //                   0 (-378)
        //                 /
        //               2  (-638)
        //         /          \
        //        4 (3818)     3 (9938)
        //    /       \           \
        //  9(-1672)   5 (-336)  7 (154)
        //                      /
        //                    6 (2722)
        //                     \
        //                      1 (-3744)
        //                        \
        //                         8 (-1750)
//        int[][] edges = {{0,1}};
//        int bob = 1;
//        int[] amount = {-7280,2350};
        //- Special cases:
        //  + From 1 --> Dùng depth thì ta sẽ tính depth của các nodes trên path cho 4:
        //      + 4 node is not in the bob's path ==> Ignore 4 from the path by using visited array
        //      + 1 -> 2 -> 0 -> (4)
        //
        //* Solution:
        //- Alice ---- x --Y-- x ----- bob
        //  + Distance from Alice to Bob = X
        //  + Alice cost is changed following this rule:
        //      + c+=cost: if Y - Alice < (float)(X+1)/2
        //      + c+cost/2 if Y - Alice == (float)(X+1)/2 (Alice meet bob)
        //  else:
        //      + out of Bob's path: c+=cost
        //* Main point:
        //  + We need to find the nodes aren't in the Bob's path ==> c+=cost
        //  + We need to find the depth of the node when traversing follows the Alice's path and then comparing to the DISTANCE (X+1)/2
        //
        //1.1, Special cases
        //
        int[][] edges = {{0,2},{0,4},{1,3},{1,2}};
        int bob = 1;
        int[] amount = {3958,-9854,-8334,-9388,3410};
        //                   0 (3958)
        //               /      \
        //          2 (-8334)    4 (3410)
        //           \
        //            1 (-9854)
        //          /
        //        3 (-9388)
        //
        //
        System.out.println(mostProfitablePath(edges, bob, amount));
        //
        //1.2, Optimization
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //Construct a binary tree from inorder and preorder traversal
        //Construct a binary tree from inorder and postorder traversals
        //Construct a binary tree from inorder and level order sequence
        //Construct a full binary tree from preorder sequence with leaf node information
        //
        //#Refence:
        //909. Snakes and Ladders
        //3241. Time Taken to Mark All Nodes -- HARD
    }
}
