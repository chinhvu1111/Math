package E1_Tree;

public class E3_SymmetricTree {

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

    public static boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        return solution(root.left, root.right);
    }

    public static boolean solution(TreeNode node1, TreeNode node2){
        if(node1==null&&node2==null){
            return true;
        }
        if(node1 == null || node2 == null){
            return false;
        }
//        System.out.printf("%s %s\n", node1.val, node2.val);
        if(node1.val!=node2.val){
            return false;
        }
        return solution(node1.left, node2.right)&&solution(node1.right, node2.left);
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- Check xem liệu cây hiện tại có đối xứng hay không
        //- Tức là tính từ root thì left branch <=> right branch (Nếu lộn ngược lại)
        //
        //** Tư duy:
        //1.
        //1.1, Idea
        //- Dùng phương pháp recursive
        //                1
        //          /          \
        //        2               2
        //    /      \         /     \
        //  (3)        4     (3)       4
        //+ Ta cần so sánh 2 nodes ở 2 level khác nhau.
        //- Tư tưởng là pass 2 nodes đầu vào method --> Sau đó traverse song song
        //+ Xét nếu có duy nhất 2 nodes nào khác nhau --> return false.
        //- Clear idea:
        //+ solution(node1, node2)
        //+ node1.val != node2.val --> return false
        //<> solution(node1.left, node2.left) --> phải cùng là true
        //+ đến lúc right : solution(node1.right, node2.right)
        //* left, true là như nhau --> Ta sẽ check node1!= node2 lúc vừa vào method.
        //
        //
        //#Reference:
        //102. Binary Tree Level Order Traversal
        //572. Subtree of Another Tree
        //513. Find Bottom Left Tree Value
        //1430. Check If a String Is a Valid Sequence from Root to Leaves Path in a Binary Tree
    }
}
