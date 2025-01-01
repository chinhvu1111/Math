package E1_Tree;

import java.util.LinkedList;
import java.util.Queue;

public class E46_InvertBinaryTree_classic {

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

    public static TreeNode solutionDFS(TreeNode root){
        if(root==null){
            return null;
        }
        TreeNode left= solutionDFS(root.left);
        TreeNode right= solutionDFS(root.right);
        root.left=right;
        root.right=left;
        return root;
    }

    public TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return root;
    }

    public TreeNode invertTreeDFS(TreeNode root) {
        solutionDFS(root);
        return root;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the root of a binary tree, invert the tree, and
        //* return its root.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //The number of nodes in the tree is in the range [0, 100].
        //-100 <= Node.val <= 100
        //
        //- Brainstorm
        //* KINH NGHIỆM:
        //- If we want to invert tree:
        //- DFS:
        //  + We need to (invert the subtree "first"):
        //      + We use the ("BOTTOM UP") approach
        //- BFS:
        //  <>
        //
    }
}
