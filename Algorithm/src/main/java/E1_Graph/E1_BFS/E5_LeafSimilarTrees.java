package E1_Graph.E1_BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class E5_LeafSimilarTrees {

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

    public static void solution(TreeNode node, List<Integer> leafNodes){
        if(node==null){
            return;
        }
        boolean isLeafNode=true;
        if(node.left!=null){
            solution(node.left, leafNodes);
            isLeafNode=false;
        }
        if(node.right!=null){
            solution(node.right, leafNodes);
            isLeafNode=false;
        }
        if(isLeafNode){
            leafNodes.add(node.val);
        }
    }

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leafNodes1=new ArrayList<>();
        List<Integer> leafNodes2=new ArrayList<>();
        //Time : O(n)
        //Space : O(h)
        solution(root1, leafNodes1);
        solution(root2, leafNodes2);
        if(leafNodes1.size()!=leafNodes2.size()){
            return false;
        }
        //Time : O(n)
        for(int i=0;i<leafNodes1.size();i++){
            if(!Objects.equals(leafNodes1.get(i), leafNodes2.get(i))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //**Requirement
        //- Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
        //* Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
        //
        //**Idea
        //1.
        //1.0,
        //- Constraint
        //The number of nodes in each tree will be in the range [1, 200].
        //Both of the given trees will have values in the range [0, 200].
        //
        //- Brainstorm
        //- two binary tree are the same if only if they have the same the sequence of leaf nodes.
        //
        //- We just need to get list of leaf nodes and then comparing them together.
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space : O(n)
        //- Time : O(n)
        //
        //#Reference:
        //1905. Count Sub Islands
        //783. Minimum Distance Between BST Nodes
        //530. Minimum Absolute Difference in BST
    }
}
