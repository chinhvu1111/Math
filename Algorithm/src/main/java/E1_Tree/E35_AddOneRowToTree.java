package E1_Tree;

public class E35_AddOneRowToTree {

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

    public static TreeNode solution(TreeNode curNode, int depth, int newVal, int targetDepth){
        if(curNode==null){
            return null;
        }
        TreeNode leftNode=curNode.left;
        TreeNode rightNode=curNode.right;

        if(targetDepth==depth+1){
            leftNode=new TreeNode(newVal);
            leftNode.left=curNode.left;
            curNode.left=leftNode;
            rightNode=new TreeNode(newVal);
            rightNode.right=curNode.right;
            curNode.right=rightNode;
            return curNode;
        }
        curNode.left=solution(leftNode, depth+1, newVal, targetDepth);
        curNode.right=solution(rightNode, depth+1, newVal, targetDepth);
        return curNode;
    }

    public static TreeNode addOneRow(TreeNode root, int val, int depth) {
        if(depth==1){
            TreeNode originRoot=root;
            TreeNode newRoot=new TreeNode(val);
            newRoot.left=originRoot;
            return newRoot;
        }
        return solution(root, 1, val, depth);
    }

    public static void main(String[] args) {
        //**Requirement
        //Given the root of a binary tree and two integers val and depth, add a row of nodes with value val at the given depth depth.
        //* Note that the root node is at depth 1.
        //The adding rule is:
        // + Given (the integer depth), for each not null tree node cur at the (depth - 1), create two tree nodes with value val as
        // cur's (left subtree root) and (right subtree root).
        // + cur's original left subtree should be the left subtree of the new left subtree root.
        // + cur's original right subtree should be the right subtree of the new right subtree root.
        // + If depth == 1 that means there is no (depth - 1) at all, then create a tree node with value val
        // as the new root of the whole original tree, and the original tree is the new root's left subtree.
        //
        //**Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is in the range [1, 10^4].
        //The depth of the tree is in the range [1, 10^4].
        //-100 <= Node.val <= 100
        //-10^5 <= val <= 10^5
        //1 <= depth <= the depth of tree + 1
        //
        //- Brainstorm
        //Ex:
        //Input: root = [4,2,6,3,1,5], val = 1, depth = 2
        //Output: [4,1,1,2,null,null,6,3,1,5]
        //
        //          4
        //        /    \
        //      2       6
        //    /  \     /
        //  3     1  5
        //->
        //          4
        //        /    \
        //      1       1
        //     /         \
        //    2          6
        //  /  \       /
        //3     1    5
        //- Chuyển về dạng
        //  + node.left = solution(...)
        //  + node.right = solution(...)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(H)
        //- Time : O(V+E)
        //
        //#Reference:
        //2596. Check Knight Tour Configuration
        //1628. Design an Expression Tree With Evaluate Function
        //2328. Number of Increasing Paths in a Grid
    }
}
