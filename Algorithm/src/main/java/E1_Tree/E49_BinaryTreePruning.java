package E1_Tree;

public class E49_BinaryTreePruning {

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

    public static boolean dfs(TreeNode node){
        if(node==null){
            return false;
        }
        boolean isValid=false;
        if(node.val==1){
            isValid=true;
        }
        boolean isValidLeft=dfs(node.left);
        if(!isValidLeft){
            node.left=null;
        }
        boolean isValidRight=dfs(node.right);
        if(!isValidRight){
            node.right=null;
        }
        return isValid||isValidLeft||isValidRight;
    }

    public static TreeNode pruneTree(TreeNode root) {
        boolean isValid=dfs(root);
        return isValid?root:null;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (the root of a binary tree),
        //* return the same tree where (every subtree) (of the given tree) (not containing a 1) has been removed.
        //- A subtree of a node "node" is node plus (every node) that is (a descendant of node).
        //  + Eliminate subtrees (whose) child nodes do not (have) (a value of 1).
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //The number of nodes in the tree is in the range [1, 200].
        //Node.val is either 0 or 1.
        //
        //- Brainstorm
        //- is current valid = isLeftValid || isRightValid || isCurrentValid
        //
        //#Reference:
        //1766. Tree of Coprimes
        //1373. Maximum Sum BST in Binary Tree
        //329. Longest Increasing Path in a Matrix
    }
}
