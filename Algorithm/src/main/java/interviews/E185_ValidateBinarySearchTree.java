package interviews;

public class E185_ValidateBinarySearchTree {

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

    public static boolean findIsValidBST(TreeNode treeNode, int low, int high){
        if(treeNode==null){
            return true;
        }
        if(treeNode.left==null&&treeNode.right==null){
            return true;
        }

        int leftValue=treeNode.val-1;
        if(treeNode.left!=null){
            leftValue=treeNode.left.val;
        }

        int rightValue=treeNode.val+1;
        if(treeNode.right!=null){
            rightValue=treeNode.right.val;
        }

        if((treeNode.left !=null && treeNode.val <= leftValue) || (treeNode.right!=null && treeNode.val >= rightValue)){
            return false;
        }

        boolean left=findIsValidBST(treeNode.left, 0, leftValue);
        boolean right=findIsValidBST(treeNode.right, rightValue, Integer.MAX_VALUE);

        return left&&right;
    }

    public static boolean isValidBST(TreeNode root) {
        int leftValue=root.left==null?0:root.left.val;
        int rightValue=root.right==null?root.val+1:root.right.val;

        if(root.left==null&&root.right==null){
            return true;
        }

        return findIsValidBST(root, leftValue, rightValue);
    }

    public static void main(String[] args) {
        Integer[] arr =new Integer[]{3,1,5,0,2,4,6};
        TreeNode[] treeNodes=new TreeNode[arr.length];
        for(int i=0;i<treeNodes.length;i++) treeNodes[i]=new TreeNode(0);

        TreeNode root=null;

        for(int i=arr.length-1;i>=0;i--){
            if(arr[i]==null){
                continue;
            }
            TreeNode currentNode=treeNodes[i];
            currentNode.val=arr[i];
            if(i==0){
                root=currentNode;
            }

            TreeNode treeNodeNext1=null;
            if(2*i+1<arr.length&&arr[2*i+1]!=null){
                treeNodeNext1=treeNodes[2*i+1];
                treeNodeNext1.val=arr[2*i+1];
            }

            TreeNode treeNodeNext2=null;
            if(2*i+2<arr.length){
                treeNodeNext2=treeNodes[2*i+2];
                treeNodeNext2.val=arr[2*i+2];
            }
            currentNode.left=treeNodeNext1;
            currentNode.right=treeNodeNext2;
        }
        System.out.println(isValidBST(root));
    }
}
