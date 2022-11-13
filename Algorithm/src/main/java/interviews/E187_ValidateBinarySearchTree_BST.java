package interviews;

import java.util.Stack;

public class E187_ValidateBinarySearchTree_BST {

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

    public static boolean findIsValidBST(TreeNode treeNode, long low, long high){
        if(treeNode==null){
            return true;
        }

        if((treeNode.val <= low) || (treeNode.val>= high)){
            return false;
        }
        if(treeNode.left==null&&treeNode.right==null){
            return true;
        }

        int leftValue=treeNode.val;
        int rightValue=treeNode.val;

        boolean left=findIsValidBST(treeNode.left, low, leftValue);
        boolean right=findIsValidBST(treeNode.right, rightValue, high);

        return left&&right;
    }

    public static boolean isValidBST(TreeNode root) {

        if(root.left==null&&root.right==null){
            return true;
        }
        long leftValue=Long.MIN_VALUE;
        long rightValue=Long.MAX_VALUE;

        return findIsValidBST(root, leftValue, rightValue);
    }

    public static boolean isValidBSTInorderTraversal(TreeNode root) {
        Stack<TreeNode> stack=new Stack<>();
        TreeNode tmp=root;
        long preVal=Long.MIN_VALUE;

        while (tmp!=null || !stack.isEmpty()){
            TreeNode currentNode=tmp;

            while (currentNode!=null){
                stack.add(currentNode);
                currentNode=currentNode.left;
            }
            tmp=null;
            if(!stack.isEmpty()){
                tmp=stack.peek().right;

                int currentValue=stack.pop().val;
                if(currentValue<=preVal){
                    return false;
                }
                preVal=currentValue;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        Integer[] arr =new Integer[]{5,4,6,null,null,3,7};
        //Case 1 :
        //- Case này xảy ra khi
        //Min, max khởi tạo ==> Có thể sẽ (<= số MIN), (>=số MAX) luôn
        //--> Mà đây là điều kiện để nhận 1 currentValue.
        //==> Gây ra return false
//        Integer[] arr =new Integer[]{-2147483648,null,2147483647};
        //Case 2 :
        //Expected result : false
        //- Case liên quan đến
        //+ currentValue == Integer.MAX_VALUE
        //+
        Integer[] arr =new Integer[]{2147483647,2147483647};
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
        System.out.println(isValidBST(root));

        //
        //** Đề bài:
        //- Kiểm tra 1 tree có phải binary search tree hay không thỏa mãn điều kiện
        //VD:
        //          a
        //       b     c
        //           (d)    e
        //+ d > a : tất cả các nodes là right of a > a
        //
        //** Ta tư duy như sau:
        //Cách 1:
        //1,
        //1.1,
        //VD:
        //
        //                      5(MIN, MAX)
        //           4(MIN,5)       	        6(5, MAX)
        //       2(MIN:4)                  3(5:6)        7(6:MAX)
        //- Ta sẽ truyền (MIN, MAX) vào đầu method ==> Có thể qua từng nhánh ta sẽ chia ra
        //Có các cases như sau:
        //- Nếu sang right : Ta sẽ truyền MIN cúa gía trị tiếp theo == clurrentValue
        //- Nếu sang left : Ta sẽ truyền MAX cúa gía trị tiếp theo == clurrentValue
        //- Ta sẽ truyền node tiếp theo vào input + kiểm tra ở đầu node tiếp theo.
        //
        //Cách 2:
        //2, Inorder traversal
        //2.1,
        //- Dùng Inorder traversal
        //VD:
        //          a
        //       b     c
        //           (d)    e
        //Traverse: b a d c e ==> Xu hướng tăng dần
        //* ==> Dùng tính chất tăng dần ==> Nếu không tăng dần ==> return false
        //2.2, Implement
        //
        //- Dùng stack + (pre value và current value) nếu:
        //+ pre_value >= value : return false
        //
        System.out.println(isValidBSTInorderTraversal(root));
    }
}
