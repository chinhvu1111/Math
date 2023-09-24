package E1_Graph.E1_BFS;

import java.util.*;

public class E3_BinaryTreeRightSideView {

    public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    public static List<Integer> rightSideView(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        Deque<TreeNode> nodes=new LinkedList<>();
        nodes.add(root);
        List<Integer> rs=new LinkedList<>();
        rs.add(root.val);

        while(!nodes.isEmpty()){
            Deque<TreeNode> nextNodes=new LinkedList<>();

            while(!nodes.isEmpty()){
                TreeNode currentNode=nodes.poll();

                if(currentNode.left!=null){
                    nextNodes.add(currentNode.left);
                }
                if(currentNode.right!=null){
                    nextNodes.add(currentNode.right);
                }
            }
            if(!nextNodes.isEmpty()){
                rs.add(nextNodes.getLast().val);
            }
            nodes=nextNodes;
        }
        return rs;
    }

    public static void main(String[] args) {
        //**Requirement
        //- Give the root of the binary tree, return the values of the right most node at each level of the tree from top to bottom.
        //
        //**Idea
        //1.
        //1.0,
        //- Constraint
        //The number of nodes in the tree is in the range [0, 100].
        //-100 <= Node.val <= 100
        //
        //- Brainstorm
        //
        //#Reference:
        //116. Populating Next Right Pointers in Each Node
        //545. Boundary of Binary Tree
        //
    }
}
