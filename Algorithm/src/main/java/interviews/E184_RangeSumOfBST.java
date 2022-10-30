package interviews;

public class E184_RangeSumOfBST {

    public static class TreeNode {
        int val;
        E183_PathSumIII.TreeNode left;
        E183_PathSumIII.TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, E183_PathSumIII.TreeNode left, E183_PathSumIII.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int rangeSumBST(TreeNode root, int low, int high) {
        return 1;
    }

    public static void main(String[] args) {

    }
}
