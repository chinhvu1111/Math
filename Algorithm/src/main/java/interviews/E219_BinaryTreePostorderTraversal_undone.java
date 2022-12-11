package interviews;

import java.util.ArrayList;
import java.util.List;

public class E219_BinaryTreePostorderTraversal_undone {

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

    public static List<Integer> result;

    public static TreeNode solution(TreeNode node){
        if(node==null){
            return null;
        }
        TreeNode left=null;
        TreeNode right=null;
        if(node.left!=null){
            left=solution(node.left);
        }
        if(left!=null){
            result.add(left.val);
        }
        if(node.right!=null){
            right=solution(node.right);
        }
        if(right!=null){
            result.add(right.val);
        }
        return node;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        result=new ArrayList<>();
        if(root==null){
            return result;
        }
        result.add(root.val);
        solution(root);
        return result;
    }

    public static void main(String[] args) {

    }
}
