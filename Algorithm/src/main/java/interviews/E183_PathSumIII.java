package interviews;

import java.util.ArrayList;
import java.util.List;

public class E183_PathSumIII {

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

    public static int countPath=0;

    public static void findPathSumRefactor(TreeNode currentNode, long sum){
        if(currentNode==null){
            return;
        }
        if(currentNode.val==sum){
            countPath++;
        }
        // System.out.println(currentNode+ " "+sum);
        findPathSumRefactor(currentNode.left, sum-currentNode.val);
        findPathSumRefactor(currentNode.right, sum-currentNode.val);
    }

    public static void subfindPathSumRefactor(TreeNode currentNode, long sum){
        if(currentNode==null){
            return;
        }
        findPathSumRefactor(currentNode, sum);
        subfindPathSumRefactor(currentNode.left, sum);
        subfindPathSumRefactor(currentNode.right, sum);
    }

    public static int pathSum(TreeNode root, int targetSum) {
        countPath=0;

        subfindPathSumRefactor(root, (long)targetSum);
        return countPath;
    }

    public static void main(String[] args) {
        System.out.println(pathSum(new TreeNode(1), 1));

        //##Reference
        //- Binary Tree Paths
        //- Path Sum III
        //- Path Sum IV
        //- Step-By-Step Directions From a Binary Tree Node to Another
    }
}
