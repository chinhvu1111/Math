package interviews;

public class E193_ConstructBinarySearchTreeFromPreorderTraversal {

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

    public static TreeNode bstFromPreorder(int[] preorder) {
        return null;
    }

    public static void main(String[] args) {
        //
        //- Inorder : Trái > Cha > Phải : DFS : ABCDEFGHI
        //- Preorder : Cha > Trái > Phải : DFS : FBADCEGIH
        //- Postorder : Trái > Phải > Cha : DFS : ACEDBHIGF
        //- Level-order : Từ trên xuống dưới, từ trái sang phải : DFS : FBGADICEH
        //
    }
}
