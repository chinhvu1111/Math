package interviews.bytedance;

public class E34_1_SumOfLeftLeaves {

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

    public static int subSumLeftRoot(TreeNode node, boolean isLeft){
        if(isLeft &&node!=null&&node.left==null&&node.right==null){
            return node.val;
        }
        if(node==null){
            return 0;
        }
        int value=0;
        value+=subSumLeftRoot(node.left, true);
        value+=subSumLeftRoot(node.right, false);

        return value;
    }

    public static int sumOfLeftLeaves(TreeNode root) {
        return subSumLeftRoot(root, false);
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- Tính các leaf node nằm ở bên trái node nào đó + node có left và right==null
        //
        //** Tư duy như sau:
        //1.
        //1.1,
        //- Wrong idea liên quan đến cộng tất cả node nằm bên trái (Kể cả trung gian)
        //+ Nằm ở bên trái --> Tức là chỉ ở bên trái ta mới cộng gía trị thêm vào.
        //==> Nếu nằm bên right --> Ta sẽ truyền 0 trong trường hợp recursion ()
        //- suball +=subCon
        //
        //- Idea như sau:
        //+ Dùng 1 flag để đánh dấu là bên left, right
        //+ Sau đó check (left==null&& right==null && isLeft==true) ==> return value
        //1.2, Complexity
        //- Time complexity : O(n)
        //- Space complexity : O(height)
        //+ N là số node
        //+ height là chiều cao của tree
        //
        //#Reference:
        //405. Convert a Number to Hexadecimal
        //701. Insert into a Binary Search Tree
        //1448. Count Good Nodes in Binary Tree
        //2328. Number of Increasing Paths in a Grid
    }
}
