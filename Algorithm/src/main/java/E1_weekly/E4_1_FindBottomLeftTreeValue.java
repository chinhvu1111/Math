package E1_weekly;

import java.util.LinkedList;
import java.util.Queue;

public class E4_1_FindBottomLeftTreeValue {

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

    public static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int rs=root.val;

        while (!queue.isEmpty()){
            int size=queue.size();

            for(int i=0;i<size;i++){
                TreeNode curNode = queue.poll();
                if(i==0){
                    rs=curNode.val;
                }
                if(curNode.left!=null){
                    queue.add(curNode.left);
                }
                if(curNode.right!=null){
                    queue.add(curNode.right);
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the root of a binary tree,
        //* Return (the leftmost value) in (the last row) of the tree.
        //  - Return lại node sâu nhất bên left.
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //The number of nodes in the tree is in the range [1, 10^4].
        //-2^31 <= Node.val <= 2^31 - 1
        //
        //- Brainstorm
        //==> Scan từng layer là được.
        //
        //#Reference:
        //1644. Lowest Common Ancestor of a Binary Tree II
        //2792. Count Nodes That Are Great Enough
        //1367. Linked List in Binary Tree
    }
}
