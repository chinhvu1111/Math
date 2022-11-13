package interviews;

public class E195_ConvertSortedArrayToBinarySearchTree {

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

    public TreeNode sortedArrayToBST(int[] nums) {
        int n=nums.length;
        TreeNode node=null;

        for(int i=0;i<n;i++){
            TreeNode currentNode=new TreeNode(nums[i]);
            if(node!=null){
                node.left=currentNode;
                node=currentNode;
            }
        }
        return null;
    }

    public static void main(String[] args) {

    }
}
