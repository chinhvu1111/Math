package mock;

public class Test_10_amazone {

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

    //** Đề bài:
    //- Check 1 tree xem có phải là BST hay không
    //
    //** Bài này tư duy như sau:
    //1.
    //1.1, Idea
    //- Graph là binary tree khi:
    //+ Mỗi intermediate node có min=1 nodes và max= 2 nodes.
    //+ Leaf node không có node nào.
    //- Traverse cả tree --> Kiểm tra left right:
    //+ key <= left or key >= right --> return false;

    public static boolean checkValidBST(TreeNode root, long min, long max){
        if(root==null){
            return true;
        }
        boolean isBst=true;

        if(min>=root.val||max<=root.val){
            return false;
        }
        if(root.left!=null){
            isBst=checkValidBST(root.left, min, root.val);
        }
        if(!isBst){
            return false;
        }
        //
        if(root.right!=null){
            if(root.right.val<=root.val){
                return false;
            }
            isBst=checkValidBST(root.right, root.val, max);
        }
        if(!isBst){
            return false;
        }
        return isBst;
    }

    public static boolean isValidBST(TreeNode root) {
        return checkValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public static void main(String[] args) {

    }
}
