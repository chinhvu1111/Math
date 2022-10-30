package interviews;

public class E181_PathSum {

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

    public static boolean subHasPathSum(TreeNode root, TreeNode parentNode,  int targetSum) {
//        System.out.printf("Node %s, sum : %s \n", root, targetSum);

        //Test case 1:
        //1.1, Confuse trừ currentValue (trước khi duyệt / đang khi duyệt):
        //+ Nên trừ khi đang duyệt currentValue --> Để tránh việc null khi điểm cuối null --> trừ trước + truyền vào method
        //==> Có thể dẫn đến nullpointer + phải viết 1 đoạn check
        //
        //1.2, Check nốt leaf như thế nào, có 2 cách traverse:
        //- Duyệt đến điểm leaf
        //==> Lúc đó ==> Đoạn (sum - value) cần sửa lại.
        //
        //- Duyệt qua điểm leaf ==> Đến null luôn
        //==> Thì cần lưu lại parent của null --> Đến cuối rồi check (parent.left==null && parent.right)
        //
        if(targetSum==0&&root==null){
            if(parentNode.left==null&&parentNode.right==null){
                return true;
            }
            return false;
        }
        //Test case 2:
//        if(targetSum<0||root==null){
//            return false;
//        }
        if(root==null){
            return false;
        }
//        System.out.printf("Node : %s, sum : %s \n", root.val, targetSum);
        boolean isSumEqualLeft=subHasPathSum(root.left, root,targetSum-root.val);

        if(isSumEqualLeft){
            return true;
        }
        boolean isSumEqualRight=subHasPathSum(root.right, root, targetSum-root.val);

        if(isSumEqualRight){
            return true;
        }
        return false;
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null&&targetSum==0){
            return false;
        }
        return subHasPathSum(root,null, targetSum);
    }

    public static void main(String[] args) {
        TreeNode treeNode=new TreeNode(1);
        TreeNode treeNode1=new TreeNode(2);
        treeNode.right=treeNode1;
        System.out.println(hasPathSum(treeNode, 1));

        //
        //** Đề bài như sau:
        //- Tìm xem sum từ (root --> leaf) ==> Có tồn tại hay không
        //- Leaf là node không có (left, right)
        //
        //** Ta tư duy như sau:
        //1,
        //- Key tư duy chính là ta sẽ trừ dần vào sum chung --> Cho đến khi ==0 thì thôi
        //Tuy nhiên cần check các test case: sum==0 ngay từ đầu
        //- Cần phải xét cả left, right ==> nếu 1 trong 2 ==true ==> return true
        //
        //1.1, Confuse trừ currentValue (trước khi duyệt / đang khi duyệt):
        //+ Nên trừ khi đang duyệt currentValue --> Để tránh việc null khi điểm cuối null --> trừ trước + truyền vào method
        //==> Có thể dẫn đến nullpointer + phải viết 1 đoạn check
        //
        //1.2, Check nốt leaf như thế nào, có 2 cách traverse:
        //- Duyệt đến điểm leaf
        //==> Lúc đó ==> Đoạn (sum - value) cần sửa lại.
        //
        //- Duyệt qua điểm leaf ==> Đến null luôn
        //==> Thì cần lưu lại parent của null --> Đến cuối rồi check (parent.left==null && parent.right)
        //
        //1.3, Chú ý số âm:
        //- Nếu các số trong node có thể là số <0 ===> Không cut branch được.
        //
        //## Reference
        //- Path Sum II
        //- Binary Tree Maximum Path Sum
        //- Sum Root to Leaf Numbers
        //- Path Sum III
        //- Path Sum IV
    }
}
