package interviews;

import sun.reflect.generics.tree.Tree;

import java.util.Stack;

public class E193_ConstructBinarySearchTreeFromPreorderTraversal {

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

    public static TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root=null;

        int n=preorder.length;
        if(n!=0){
            root=new TreeNode(preorder[0]);
        }
        Stack<TreeNode> stack=new Stack<>();
        stack.add(root);
        TreeNode tmp=null;

        for(int i=1;i<n;i++){
            while (!stack.isEmpty()){
                if(stack.peek().val<preorder[i]){
                    tmp=stack.pop();

                }else{

                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //
        //** Đề bài
        //- Chuyển 1 array dạng preorder trarversal --> binary tree
        //-, Return lại root
        //
        //- Inorder : Trái > Cha > Phải : DFS : ABCDEFGHI
        //- Preorder : Cha > Trái > Phải : DFS : FBADCEGIH
        //- Postorder : Trái > Phải > Cha : DFS : ACEDBHIGF
        //- Level-order : Từ trên xuống dưới, từ trái sang phải : DFS : FBGADICEH
        //
        //             8
        //         5       10
        //     1   (7)  (9)   12
        //  0   2
        //# : 8, 5, 1, 0, 2, 7, 10, 12
        //[7] >5 : Tìm như thế nào
        //===> Số (max nhất - chỉ khi left) mà [7] > cho đến lúc traverse đến (7)
        //[9] <10 : Tìm như thế nào
        //===> Số (max nhất - chỉ khi right) mà [9]< cho đến lúc traverse đến 9
        //VD:
        //         8
        //       5   10
        //     1   (7:Nếu điền 7)
        //   0   2
        //        (3:Nếu điền 3)
        //==> Cần phải lưu lại thông tin các (value trung gian)
        //*** ==> Bài này chỉ cần thay đổi hướng tuy duy không dùng loop array
        //---> Mà dùng stack --> Được ngay
    }
}
