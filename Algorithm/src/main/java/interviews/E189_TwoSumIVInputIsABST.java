package interviews;

public class E189_TwoSumIVInputIsABST {

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

    public static boolean findNode(TreeNode node, TreeNode nodePair, int value){
        if(node==null){
            return false;
        }
        if(node.val==value&&node!=nodePair){
            return true;
        }
        boolean isInclude=false;
        if(node.val<value){
            isInclude=findNode(node.right, nodePair, value);
        }else{
            isInclude=findNode(node.left, nodePair, value);
        }
        return isInclude;
    }

    public static TreeNode rootFinal;

    public static boolean subFindTarget(TreeNode root, int k) {
        if(root==null){
            return false;
        }

        int currentValue=root.val;
        boolean isInclude=false;

        isInclude=findNode(rootFinal, root,k-currentValue);
        if(isInclude){
            return true;
        }
        isInclude=subFindTarget(root.left, k);
        if(!isInclude){
            isInclude=subFindTarget(root.right, k);
        }
        return isInclude;
    }

    public static boolean findTarget(TreeNode root, int k) {
        rootFinal=root;
        return subFindTarget(root, k);
    }

    public static void main(String[] args) {
//        Integer[] arr =new Integer[]{5,3,6,2,4,null,7};
//        int k=9;
//        Integer[] arr =new Integer[]{5,3,6,2,4,null,7};
//        int k=28;
//        Integer[] arr =new Integer[]{1};
//        int k=2;
//        Integer[] arr =new Integer[]{2,1,3};
//        int k=4;
        Integer[] arr =new Integer[]{2,0,3,-4,1};
        int k=-1;
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
            if(2*i+2<arr.length&&arr[2*i+2]!=null){
                treeNodeNext2=treeNodes[2*i+2];
                treeNodeNext2.val=arr[2*i+2];
            }
            currentNode.left=treeNodeNext1;
            currentNode.right=treeNodeNext2;
        }

        System.out.println(findTarget(root, k));
    }
}
