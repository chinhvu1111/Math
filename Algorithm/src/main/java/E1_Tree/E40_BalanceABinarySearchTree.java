package E1_Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class E40_BalanceABinarySearchTree {

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

    public static int getHeight(TreeNode node){
        if(node==null){
            return 0;
        }
        int left=getHeight(node.left)+1;
        int right=getHeight(node.right)+1;
        return Math.max(left, right);
    }

    public static TreeNode leftRotate(TreeNode node){
        //      A
        //    /   \
        //   B     C
        //        /
        //      D
        //=>
        //      C
        //    /
        //   A
        // /  \
        //B    D
        TreeNode right = node.right;
        TreeNode leftOfRight = right!=null?right.left:null;
        right.left=node;
        node.right=leftOfRight;
        return right;
    }
    public static TreeNode rightRotate(TreeNode node){
        //       A
        //     /   \
        //   D      B
        // /   \
        //E     C
        //=>
        //       D
        //     /  \
        //   E     A
        //       /  \
        //      C    B
        TreeNode left=node.left;
        TreeNode rightOfLeft=left!=null?left.right:null;
        left.right=node;
        node.left=rightOfLeft;
        return left;
    }

    public static int getBalance(TreeNode node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    public static TreeNode insertNode(TreeNode node, int value){
        if(node==null){
            return new TreeNode(value);
        }
        if(value<node.val){
            node.left=insertNode(node.left, value);
        }else if(value>node.val){
            node.right=insertNode(node.right, value);
        }
        int balance = getBalance(node);
        if(balance>1&&value<node.left.val){
            return rightRotate(node);
        }
        if(balance>-1&value>node.right.val){
            return leftRotate(node);
        }
        if(balance>1&&value>node.left.val){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if(balance<-1&&value<node.right.val){
            node.right=rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public static TreeNode balanceBST(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        TreeNode node=root;
        List<TreeNode> listNodes=new ArrayList<>();

        while(!stack.isEmpty()|| node!=null){
            while(node!=null){
                stack.add(node);
                node=node.left;
            }
            node=stack.pop();
            listNodes.add(node);
            node=node.right;
        }
        TreeNode newRoot=null;

        for(TreeNode curNode: listNodes){
            newRoot=insertNode(newRoot, curNode.val);
        }
        return newRoot;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (the root of a binary search tree),
        //* Return (a balanced binary search tree) with the same node values.
        //- If there is more than one answer, return any of them.
        //- A binary search tree is balanced if the depth of the (two subtrees) of (every node) never differs by more than 1.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //The number of nodes in the tree is in the range [1, 10^4].
        //1 <= Node.val <= 10^5
        //+ N lớn --> Time: O(n)
        //
        //- Brainstorm
        //  1
        //    \
        //     2
        //       \
        //        3
        //         \
        //          4
        //           \
        //            5
        //==>
        //     2
        //   /  \
        // 1     3
        //        \
        //         4
        //          \
        //           5
        //- Khi nào thì có thể rotate?
        //    1
        //      \
        //       2
        //     /
        //   3
        //=>
        //   1
        //    \
        //     2
        //      \
        //       3
        //=>
        //    2
        //  /  \
        // 1    3
        //
        //- Bài này nếu mà add node + balance liên tục thì sẽ dễ hơn
        //==> Convert ra 1 array node ==> Convert thành binary thì sẽ dễ hơn
        //  + Array node phải có đảm bảo order : Tăng dần thì sẽ dễ hơn
        //          7
        //        /   \
        //       4     8
        //     /   \
        //   3      6
        //        /
        //       5
        //- Convert sort array --> tree
        //  + Add từng node + balance tree đó mỗi lần
        //- Left rotate:
        //  + left height < right height
        //- Right rotate:
        //  + left height > right height
        //
        //- Left Left Case
        //T1, T2, T3 and T4 are subtrees.
        //         z                                      y
        //        / \                                   /   \
        //       y   T4      Right Rotate (z)          x      z
        //      / \          - - - - - - - - ->      /  \    /  \
        //     x   T3                               T1  T2  T3  T4
        //    / \
        //  T1   T2
        //#Reference:
        //2459. Sort Array by Moving Items to Empty Space
        //1257. Smallest Common Region
        //1104. Path In Zigzag Labelled Binary Tree
    }
}
