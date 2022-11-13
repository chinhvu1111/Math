package interviews;

import java.util.Stack;

public class E188_BinarySearchTreeIterator_BST {

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

    public static class BSTIterator {

        Stack<TreeNode> stack;
        TreeNode tmp=null;

        public BSTIterator(TreeNode root) {
            stack=new Stack<>();
            tmp=root;
            TreeNode currentNode=tmp;

            while (currentNode!=null){
                stack.add(currentNode);
                currentNode=currentNode.left;
            }
        }

        public int next() {
            int currentValue=-1;
            tmp=null;

            if(!stack.isEmpty()){
                tmp=stack.peek().right;
                currentValue=stack.pop().val;
            }
            TreeNode currentNode=tmp;
            while (currentNode!=null){
                stack.add(currentNode);
                currentNode=currentNode.left;
            }
            return currentValue;
        }

        public boolean hasNext() {
            return tmp!=null||!stack.isEmpty();
        }
    }

    public static void main(String[] args) {
        //
        //
        //** Đề bài
        //
        //** Ta tư duy như sau:
        //
        //# reference:
        //- Flatten 2D Vector
        //- Zigzag Iterator
        //- Peeking Iterator
        //- Inorder Successor in BST
        //- Binary Search Tree Iterator II
    }
}
