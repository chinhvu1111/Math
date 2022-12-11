package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class E220_BinaryTreePreorderTraversal_stack {

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

    public static List<Integer> result;

    public static void solution(TreeNode root){
        if(root==null){
            return;
        }
        result.add(root.val);
        if(root.left!=null){
            solution(root.left);
        }
        if(root.right!=null){
            solution(root.right);
        }
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        result=new ArrayList<>();
        solution(root);
        return result;
    }

    public static List<Integer> preorderTraversalStack(TreeNode root) {
        TreeNode tmp=root;
        Stack<TreeNode> stack=new Stack<>();
        List<Integer> result=new ArrayList<>();
        stack.add(tmp);

        while (tmp!=null){
            stack.add(tmp);
            result.add(tmp.val);

            while (tmp.left!=null){
                result.add(tmp.left.val);
                tmp=tmp.left;
            }

        }
        return result;
    }

    public static void main(String[] args) {

        //Inorder => Left, Root, Right.
        //
        //Preorder => Root, Left, Right.
        //
        //Post order => Left, Right, Root.
//        TreeNode treeNode=new TreeNode();
//        treeNode.val=1;
//        TreeNode treeNode1=new TreeNode();
//        treeNode1.val=2;
//        TreeNode treeNode2=new TreeNode();
//        treeNode2.val=3;
//        treeNode.left=treeNode1;
//        treeNode.right=treeNode2;

        TreeNode treeNode=new TreeNode();
        treeNode.val=1;
        TreeNode treeNode1=new TreeNode();
        treeNode1.val=2;
        TreeNode treeNode2=new TreeNode();
        treeNode2.val=3;
        treeNode.right=treeNode2;
        treeNode2.left=treeNode1;
        System.out.println(preorderTraversalStack(treeNode));
    }
}
