package interviews;

public class E195_ConvertSortedArrayToBinarySearchTree_BST {

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

    /*
    - Sẽ gán right=root của balanced tree bên right
    - Sẽ gán right=root của balanced tree bên right
     */
    public static TreeNode createBST(int nums[], int low, int high){
        if(low>high){
            return null;
        }
        int midValue=low+ (high-low)/2;
        TreeNode currentNode=new TreeNode(nums[midValue]);

//        if(low==high){
//            return currentNode;
//        }
        currentNode.right=createBST(nums,midValue+1, high);
        currentNode.left=createBST(nums,low, midValue-1);
        return currentNode;
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        int n=nums.length;
        TreeNode root;

        root=createBST(nums, 0, n-1);
        return root;
    }

    public static void println(TreeNode node){
        if(node==null){
            return;
        }
        System.out.println(node.val);
        println(node.left);
        println(node.right);
    }

    public static void main(String[] args) {
        int[] arr=new int[]{-10,-3,0,5,9};

        TreeNode root=sortedArrayToBST(arr);
        println(root);
        //
        //** Đề bài:
        //- Convert binary tree --> Sang 1 height-balanced binary search tree
        //+ The difference between the heights of the left and the right subtree for any node is not more than one.
        //+ The left subtree is balanced.
        //+ The right subtree is balanced.
        //VD:
        //                 3
        //             (2)   4
        //           1         5
        //         0
        //--> Vị trí (2) subtree không cân bằng.
        //
        //** Bài này tư duy như sau:
        //1,Vấn đề chính là cây cân bằng khi tất cả các subtree của nó cũng cân bằng
        //
        //1.1, Chia nhỏ tree để giúp nó cân bằng hết
        //- low, high : Chính là thứ tự index của sub-tree hiện tại ==> Mỗi subtree có 1 node root
        //--> chình là mid= low + (high-low)/2
        //- Ta chỉ cần gán:
        //+ Sẽ gán right=root của balanced tree bên right
        //+Sẽ gán left=root của balanced tree bên left
        //2,
        //2.1, Time complexity:
        //- O(N)
        //2.2, Space complexity:
        //- O(N)
        //
    }
}
