package E1_daily;

import E1_Tree.E15_ClosestNodesQueriesInABinarySearchTree;

import java.util.*;

public class E137_HeightOfBinaryTreeAfterSubtreeRemovalQueries {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //Intermediate value:
    //  +
    public static int dfs(TreeNode node, HashMap<Integer, Integer> result){
        if(node==null){
            return 0;
        }
        //Same level
//        TreeNode leftNode = node.left;
//        TreeNode rightNode = node.right;
        //          -1
        //             \
        //              0
        //                \
        //                 1
        int leftVal = dfs(node.left, result)+1;
        int rightVal = dfs(node.right, result)+1;
        int curHeight = Math.max(leftVal, rightVal);
        result.put(node.val, curHeight-1);
        return curHeight;
    }

    public static void dfs1(TreeNode node, int prevHeight, int depth, HashMap<Integer, Integer> heightCache, HashMap<Integer, Integer> result){
        if(node==null) {
            return;
        }
        result.put(node.val, Math.max(prevHeight, depth-1));
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        int prevHeightLeft=Math.max(prevHeight, depth);
        int prevHeightRight=prevHeightLeft;
        if(rightNode!=null){
            prevHeightLeft = Math.max(prevHeightLeft, heightCache.get(rightNode.val)+depth+1);
        }
        if(leftNode!=null){
            prevHeightRight = Math.max(prevHeightRight, heightCache.get(leftNode.val)+depth+1);
        }
        dfs1(node.left, prevHeightLeft, depth+1, heightCache, result);
        dfs1(node.right, prevHeightRight, depth+1, heightCache, result);
    }

    public static int[] treeQueries(TreeNode root, int[] queries) {
        HashMap<Integer, Integer> cacheHeight=new HashMap<>();
        HashMap<Integer, Integer> result=new HashMap<>();
        dfs(root, cacheHeight);
//        System.out.println(cacheHeight);
        dfs1(root, 0, 0, cacheHeight, result);
//        System.out.println(result);
        int n=queries.length;
        int[] rs=new int[n];

        for (int i = 0; i < n; i++) {
            rs[i]=result.get(queries[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n.
        // You are also given an array queries of size m.
        //- You have to perform m independent queries on the tree where in the ith query you do the following:
        //  + Remove the subtree rooted at the node with the value queries[i] from the tree.
        //  It is guaranteed that queries[i] will not be equal to the value of the root.
        //      + Remove cả subtree với node có (value = queries[i])
        //* Return (an array answer of size m) where answer[i] is (the height of the tree) after performing (the ith query).
        //* Note:
        //- The queries are independent, so the tree returns to its initial state after each query.
        //- The height of a tree is (the number of edges) in the longest simple path from the root to some node in the tree.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //The number of nodes in the tree is n.
        //2 <= n <= 10^5
        //1 <= Node.val <= n
        //All the values in the tree are unique.
        //m == queries.length
        //1 <= m <= min(n, 10^4)
        //1 <= queries[i] <= n
        //queries[i] != root.val
        //  + Length of queries = 10^4 ==> Time: O(n*log(n))
        //
        //- Brainstorm
        //- Tìm height mỗi lần delete
        //- Ta cần tìm height của toàn bộ node là root trong tree
        //- Do node là distinct ta có thể dùng treeSet
        //- Với mỗi lần delete node:
        //  + Ta sẽ remove trong treeSet
        //- Mỗi lần check height:
        //  + Ta lấy last là được
        //
        //- Để tìm được height:
        //  + Dễ thì ta dùng DFS là được.
        //- Current height = max(left_height, right_height)
        //==> WRONG IDEA
        //- Bên trên vẫn sai vì nếu remove (sub-root):
        //  + Là remove hết các sub node của tree đó
        //- Remove ntn?
        //  + Nếu remove all of nodes:
        //      + Not optimal:
        //          + Time complexity: O(m*n) (n is the number of deleted node)
        //
        //            5
        //        /     \
        //       8       9
        //     /   \    /  \
        //   2      1  3    7
        // /  \
        //4    6
        //- Post order: 4,6,(2),1,8,3,7,9,[5]
        //- In order: 4,(2),6,8,1,[5],3,9,7
        //  + It is quite hard to get the height of the tree
        //      + 6 will be (children) or (parent) of node with 2 value
        //==> Traversal is not solution
        //- Delete 1 node, height có thể:
        //  + Changed:
        //      + The current node lay on the longest path from root
        //  + Not changed
        //      + The current node doesn't lay on the longest path from root
        //      + The current node lay on the longest path (h=x) from root but we have multiple paths with (x length)
        //
        //- Nếu remove(9):
        //  + height của tree sẽ là (left site)
        //* Hiểu sai đề bài:
        //  + Remove each node separately rather doing this stuff in order
        //  ==> Remove từng nodes riêng rẽ mà thôi.
        //
        //- Height của tree sau khi remove node x
        //  + result = height of the tree before reaching the x node.
        //
        //- Cách traverse:
        //- Cần phải dfs 2 lần:
        //- Lần 1:
        //  + Ta cần traverse để tìm được (height của subtree) với (root là curNode)
        //  ==> Caching
        //- Lần 2:
        //  + Khi stand at the current node:
        //      + Look at left node:
        //          + Height of the left node before reaching the left node:
        //              ==> Traverse from (the current node) to (right node)
        //              + Height of right = cache[rightNode.val] + depth
        //      + Look at right node
        //          + Height of the right node before reaching the right node:
        //              ==> Traverse from (the current node) to (left node)
        //              + Height of left = cache[leftNode.val] + depth
        //- We just scan queries
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the number of node (Binary tree)
        //- Space: O(n)
        //- Time: O(n)
        Integer[] nodes=new Integer[]{1,3,4,2,null,6,5,null,null,null,null,null,7};
        TreeNode root=new TreeNode(nodes[0]);
        Queue<TreeNode> queueNodes=new LinkedList<>();
        queueNodes.add(root);
        int i=0;
        for(;i<nodes.length;i++){
            TreeNode currentNode=queueNodes.poll();
            TreeNode next=null;
            TreeNode next1=null;
            if(i*2+1<nodes.length&&nodes[i*2+1]!=null){
                next=new TreeNode(nodes[i*2+1]);
                queueNodes.add(next);
            }
            if(i*2+2<nodes.length&&nodes[i*2+2]!=null){
                next1=new TreeNode(nodes[i*2+2]);
                queueNodes.add(next1);
            }
            if(currentNode!=null){
                currentNode.left=next;
                currentNode.right=next1;
            }
        }
        int[] queries={4};
        int[] rs= treeQueries(root, queries);
        for (int j = 0; j < rs.length; j++) {
            System.out.printf("%s,", rs[j]);
        }
        System.out.println();
        //
        //#Reference:
        //1287. Element Appearing More Than 25% In Sorted Array
        //2919. Minimum Increment Operations to Make Array Beautiful
        //2869. Minimum Operations to Collect Elements
        //2653. Sliding Subarray Beauty
        //109. Convert Sorted List to Binary Search Tree
        //1751. Maximum Number of Events That Can Be Attended II
        //
    }
}
