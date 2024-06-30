package E1_Tree;

import java.util.Stack;

public class E39_BinarySearchTreeToGreaterSumTree {

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

    public static int solution(TreeNode node, int greaterVal){
        if(node==null){
            return greaterVal;
        }
        int right = solution(node.right, greaterVal);
        int left = solution(node.left, greaterVal+right+node.val);
        int newVal= node.val+left+right;
        node.val+=right+greaterVal;
        return newVal;
    }

    public static TreeNode bstToGst(TreeNode root) {
        solution(root, 0);
        return root;
    }

    public static TreeNode bstToGstInteration(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        TreeNode node = root;
        int curSum=0;

        while(!stack.isEmpty() || node!=null){
            while(node!=null){
                stack.add(node);
                node=node.right;
            }
            node=stack.pop();
            curSum+= node.val;
            node.val = curSum;
            node = node.left;
        }
        return root;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the root of a Binary Search Tree (BST), convert it to a (Greater Tree)
        // such that (every key) of (the original BST) is changed to (the original key) plus (the sum of all keys) (greater than) (the original key) in BST.
        //As a reminder, a binary search tree is a tree that satisfies these constraints:
        //- The left subtree of a node contains only nodes with keys less than the node's key.
        //- The right subtree of a node contains only nodes with keys greater than the node's key.
        //- Both the left and right subtrees must also be binary search trees.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //The number of nodes in the tree is in the range [1, 100].
        //0 <= Node.val <= 100
        //All the values in the tree are unique.
        //==> Số node không nhiều
        //
        //- Brainstorm
        //            4 (18)
        //        /            \
        //      1 (21)         6 (7)
        //    /   \          /      \
        //  0      2 (20)  5 (13)    7
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(h)
        //- Time: O(n)
        //
        //#Reference:
        //703. Kth Largest Element in a Stream
        //2596. Check Knight Tour Configuration
        //2277. Closest Node to Path in Tree
    }
}
