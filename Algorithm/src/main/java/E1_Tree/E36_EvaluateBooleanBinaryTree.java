package E1_Tree;

public class E36_EvaluateBooleanBinaryTree {

    public class TreeNode {
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

    public static boolean evaluateTree(TreeNode root) {
        if(root.val==0){
            return false;
        }
        if(root.val==1){
            return true;
        }
        if(root.val==2){
            return evaluateTree(root.left) | evaluateTree(root.right);
        }else{
            return evaluateTree(root.left) && evaluateTree(root.right);
        }
    }

    public static void main(String[] args) {
        //**Requirement
        //- You are given the root of a full binary tree with the following properties:
        //  - Leaf nodes have either the value 0 or 1, where 0 represents False and 1 represents True.
        //  - Non-leaf nodes have either the value 2 or 3, where (2 represents the boolean OR) and (3 represents the boolean AND).
        //The evaluation of a node is as follows:
        //- If the node is (a leaf node), the evaluation is the value of the node, i.e. True or False.
        //Otherwise, evaluate the node's two children and apply the boolean operation of its value with (the children's evaluations).
        //* Return the boolean result of evaluating the root node.
        //- (A full binary tree) is a binary tree where each node has either (0 or 2 children).
        //- (A leaf node) is a node that has (zero children).
        //Ex:
        //      2
        //    /   \
        //  1      3
        //       /   \
        //     0      1
        //
        //Input: root = [2,1,3,null,null,0,1]
        //Output: true
        //Explanation: The above diagram illustrates the evaluation process.
        //The AND node evaluates to False AND True = False.
        //The OR node evaluates to True OR False = True.
        //The root node evaluates to True, so we return true.
        //
        //**Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is in the range [1, 1000].
        //0 <= Node.val <= 3
        //Every node has either 0 or 2 children.
        //Leaf nodes have a value of 0 or 1.
        //Non-leaf nodes have a value of 2 or 3.
        //
        //- Brainstorm
        //- Bài này ta tính return từ leaf node thôi.
        //
        //
        //#Reference:
        //1612. Check If Two Expression Trees are Equivalent
        //1628. Design an Expression Tree With Evaluate Function
        //2313. Minimum Flips in Binary Tree to Get Result
    }
}
