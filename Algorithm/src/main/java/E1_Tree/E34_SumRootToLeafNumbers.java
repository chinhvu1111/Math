package E1_Tree;

public class E34_SumRootToLeafNumbers {

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

    public static void solution(TreeNode root, int curVal, int[] rs) {
        if(root==null){
            return;
        }
        curVal=curVal*10+root.val;
        if(root.left==null&&root.right==null){
            rs[0]+=curVal;
            // System.out.println(curVal);
            return;
        }
        solution(root.left, curVal, rs);
        solution(root.right, curVal, rs);
    }

    public static int sumNumbers(TreeNode root) {
        int[] rs=new int[1];
        solution(root, 0, rs);
        return rs[0];
    }

    public static void main(String[] args) {
        //**Requirement
        //- You are given the root of a binary tree containing digits from 0 to 9 only.
        //- Each root-to-leaf path in the tree represents (a number).
        //For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
        // Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
        //A leaf node is a node with no children.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is in the range [1, 1000].
        //0 <= Node.val <= 9
        //The depth of the tree will not exceed 10.
        //
        //- Brainstorm
        // root(1) -> 2 -> 3
        //       4
        //     /   \
        //    9     0
        //  /   \
        // 5     1
        //- root(9): val = 95 + 91
        //- root(4): val = (95 + 91)+4100
        //- Nhưng depth có thể khác nhau
        //       4
        //     /   \
        //    9     0
        //  /   \
        // 5     1
        //        \
        //        10
        //- Bài này hơi sửa cái đoạn liên quan đến traverse:
        //  + Vì đến leaf node ta vẫn có thể:
        //      + Go right
        //      + Go left
        //  ==> Đến khi (root==null) : Cộng result ==> WRONG IDEA (Bị duplicate mấy trường hợp root==null)
        //- Check (root.left==null && root.right==null):
        //  + Cộng rs
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(H)
        //- Time: O(V+E)
        //
        //#Reference:
        //988. Smallest String Starting From Leaf
    }
}
