package E1_Tree;

import java.util.HashSet;

public class E28_TwoSumBSTs {

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

    public static void solution(HashSet<Integer> values, TreeNode node){
        if(node==null){
            return;
        }
        values.add(node.val);
        solution(values, node.left);
        solution(values, node.right);
    }

    public static boolean isValid(TreeNode node, HashSet<Integer> values, int target){
        if(node==null){
            return false;
        }
        if(values.contains(target-node.val)){
            return true;
        }
        boolean left=isValid(node.left, values, target);
        if(left){
            return true;
        }
        return isValid(node.right, values, target);
    }

    public static boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        HashSet<Integer> leftVals=new HashSet<>();
        solution(leftVals, root1);

        return isValid(root2, leftVals, target);
    }

    public static void main(String[] args) {
        // Requirement
        //- Given the roots of two binary search trees, root1 and root2,
        //* return true if and only if there is a node in the first tree and a node in the second tree
        // whose values sum up to a given integer target.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in each tree is in the range [1, 5000].
        //-10^9 <= Node.val, target <= 10^9
        //+ Số lượng nodes không nhiều --> DFS được
        //
        //- Brainstorm
        //==> Nên dùng binary search tree
        //#Reference:
        //1944. Number of Visible People in a Queue
        //662. Maximum Width of Binary Tree
        //1190. Reverse Substrings Between Each Pair of Parentheses
    }
}
