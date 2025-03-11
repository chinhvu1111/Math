package contest;

public class E276_ConstructBinaryTreeFromPreorderAndPostorderTraversal_construction_classic {

    static public class TreeNode {
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

    public static TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return null;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given two integer arrays, (preorder and postorder) where preorder is (the preorder traversal of a binary tree of distinct values) and
        // (postorder is the postorder traversal of the same tree),
        //* reconstruct and return the binary tree.
        //- If there exist multiple answers, you can return any of them.
        //
        //Ex:
        //Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
        //Output: [1,2,3,4,5,6,7]
        //
        //* Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= preorder.length <= 30
        //1 <= preorder[i] <= preorder.length
        //All the values of preorder are (unique).
        //postorder.length == preorder.length
        //1 <= postorder[i] <= postorder.length
        //All the values of postorder are (unique).
        //It is guaranteed that preorder and postorder are the preorder traversal and postorder traversal of the same binary tree.
        //
        //- Brainstorm
        //- Post order meaning?
        //
        //Ex:
        //Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
        //Output: [1,2,3,4,5,6,7]
        //              1
        //           /   \
        //         2       3
        //      /   \     /   \
        //    4      5  6      7
        //
        //#Reference:
        //Construct a binary tree from inorder and preorder traversal
        //Construct a binary tree from inorder and postorder traversals
        //Construct a binary tree from inorder and level order sequence
        //Construct a full binary tree from preorder sequence with leaf node information
    }
}
