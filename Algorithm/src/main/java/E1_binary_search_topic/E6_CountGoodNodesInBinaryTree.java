package E1_binary_search_topic;

public class E6_CountGoodNodesInBinaryTree {

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

    public static int rs;

    public static void solution(TreeNode node, int currentMax){
        if(node==null){
            return;
        }
        if(node.val>=currentMax){
            rs++;
        }
        int nextMax=Math.max(node.val, currentMax);
        solution(node.left, nextMax);
        solution(node.right, nextMax);
    }

    public static int goodNodes(TreeNode root) {
        if(root==null){
            return 0;
        }
        rs=0;
        solution(root, root.val);
        return rs;
    }

    public static void main(String[] args) {
        //
        //#Reference
        //2458. Height of Binary Tree After Subtree Removal Queries
        //1087. Brace Expansion
        //2538. Difference Between Maximum and Minimum Price Sum
    }
}
