package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class E186_KthSmallestElementInABST {


    public class TreeNode {
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

    public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack=new Stack<>();
        TreeNode tmp=root;

        while (tmp!=null || !stack.isEmpty()){
            TreeNode currentNode=tmp;
            while (currentNode!=null){
                stack.add(currentNode);
                currentNode=currentNode.left;
            }
            tmp=null;
            if(!stack.isEmpty()){
                tmp=stack.peek().right;
                k--;
                int currentValue=stack.pop().val;

                if(k==0){
                    return currentValue;
                }
            }
        }
        return k;
    }

    public static int number=0;
    public static int count=0;

    public static int kthSmallestRecursion(TreeNode root, int k) {
        count=k;
        helper(root);
        return number;
    }

    public static void helper(TreeNode node){
        if(node.left!=null){
            helper(node.left);
        }
        count--;
        if(count==0){
            number=node.val;
            return;
        }
        if(node.right!=null){
            helper(node.right);
        }
    }

    public static void main(String[] args) {
        //
        //** Đề bài:
        //- Tìm số lớn thứ k của BST (binary search tree)
        //
        //** Bài này tư duy như sau:
        //Cách 1:
        //1,
        //Ta dùng cách duyệt inorder traversal
        //--> Nên nó luôn tăng dần ==> Ta chính cần dùng stack + count
        //Cách 2:
        //2,
        //2.1,
        //Khoãng giữa helper(node.left); count--; helper(count.right)
        //--> Chính là lúc giảm count
        //==> Nó sẽ chạy theo inorder traverse.
    }

}
