package interviews;

public class E184_RangeSumOfBST_BST {

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

    public static int sum=0;

    public static void findRangeSumBST(TreeNode root, int low, int high){
        if(root==null){
            return;
        }
        if(low<=root.val && root.val<=high){
            sum+=root.val;
            findRangeSumBST(root.left, low, root.val);
            findRangeSumBST(root.right, root.val, high);
        }else if(low>root.val){
            findRangeSumBST(root.right, low, high);
        }else {
            findRangeSumBST(root.left, low, high);
        }
    }

    public static int rangeSumBST(TreeNode root, int low, int high) {
        sum=0;
        findRangeSumBST(root, low, high);
        return sum;
    }

    public static int rangeSumBSTRefactor(TreeNode root, int L, int R) {
        if (root == null) return 0;
        if (root.val <= L) return rangeSumBST(root.right, L, R) + (root.val == L ? root.val : 0);
        if (root.val >= R) return rangeSumBST(root.left, L, R) + (root.val == R ? root.val : 0);
        return rangeSumBST(root.left, L, R) + root.val + rangeSumBST(root.right, L, R);
    }

    public static void main(String[] args) {
        Integer[] arr =new Integer[]{10,5,15,3,7,null,18};
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
        rangeSumBST(root, 7, 15);
        //Refactor
        rangeSumBSTRefactor(root, 7, 15);
        System.out.println(sum);
        //
        //** Đề bài
        //-
        //
        //** Bài này tư duy như sau:
        //1,
        //1.1, Cách xây dựng array list node dạng array
        //- Tạo List TreeNode
        //- Loop n-1 --> 0 (Gán cho mọi node)
        //+ i : left(2*i+1), right(2*i+2)
        //- Cần phải check null --> Cho cả 2 node (left/ right)
        //
    }
}
