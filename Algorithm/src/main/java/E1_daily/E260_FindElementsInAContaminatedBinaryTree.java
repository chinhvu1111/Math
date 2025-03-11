package E1_daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class E260_FindElementsInAContaminatedBinaryTree {

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

    static class FindElements {

        public HashSet<Integer> exists;

        public void solution(TreeNode node, int curVal, HashSet<Integer> set){
            set.add(curVal);
            if(node.left!=null){
                solution(node.left, curVal*2+1, set);
            }
            if(node.right!=null){
                solution(node.right, curVal*2+2, set);
            }
        }

        public FindElements(TreeNode root) {
            exists=new HashSet<>();
            solution(root, 0, this.exists);
        }

        public boolean find(int target) {
            return this.exists.contains(target);
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a binary tree with the following rules:
        //  + root.val == 0
        //  + For any treeNode:
        //      + If treeNode.val has a value x and (treeNode.left) != null, then treeNode.left.val == 2 * x + 1
        //      + If treeNode.val has a value x and (treeNode.right) != null, then treeNode.right.val == 2 * x + 2
        //- Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.
        //- Implement the FindElements class:
        //  + FindElements(TreeNode* root) Initializes the object with a contaminated binary tree and recovers it.
        //  + bool find(int target) Returns true if the target value exists in the recovered binary tree.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //TreeNode.val == -1
        //The height of the binary tree is less than or equal to 20
        //The total number of nodes is between [1, 10^4]
        //Total calls of find() is between [1, 10^4]
        //0 <= target <= 10^6
        //  + Num_node <= 10^4 ==> Time: O(n*log(n))
        //
        //* Brainstorm:
        //- Binary tree:
        //  + We can not find the node on the (left/ right)
        //-
        //
        //1.1, Special cases
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //2080. Range Frequency Queries
        //205. Isomorphic Strings
        //1282. Group the People Given the Group Size They Belong To
    }
}
