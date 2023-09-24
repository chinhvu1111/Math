package E1_binary_search_topic;

public class E9_InsertIntoABinarySearchTree {

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

    public static void solution(TreeNode node, TreeNode beforeNode, int val, int dir){
        if(node==null){
            if(dir==1){
                beforeNode.left=new TreeNode(val);
            }else{
                beforeNode.right=new TreeNode(val);
            }
            return;
        }
        if(val>node.val){
            solution(node.right, node, val, 2);
        }else if(val<node.val){
            solution(node.left, node, val, 1);
        }
    }

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null){
            return new TreeNode(val);
        }
        solution(root, null, val, -1);
        return root;
    }

    public static void main(String[] args) {
        //#Reference:
        //331. Verify Preorder Serialization of a Binary Tree
        //1719. Number Of Ways To Reconstruct A Tree
        //1382. Balance a Binary Search Tree
    }
}
