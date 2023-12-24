package E1_Tree;

public class E22_CountUnivalueSubtrees {

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

    public static int rs;

    public static boolean solution(TreeNode node){
        if(node==null){
            return true;
        }
        boolean leftIsUni=solution(node.left);
        boolean rightIsUni=solution(node.right);

        if(!leftIsUni||!rightIsUni){
            return false;
        }
        if(node.left!=null&&node.left.val!=node.val){
            return false;
        }
        if(node.right!=null&&node.right.val!=node.val){
            return false;
        }
        rs++;
        return true;
    }

    public static boolean solutionRefactor(TreeNode node, int[] count){
        if(node==null){
            return true;
        }
        boolean leftIsUni=solutionRefactor(node.left, count);
        boolean rightIsUni=solutionRefactor(node.right, count);

        if(!leftIsUni||!rightIsUni){
            return false;
        }
        if(node.left!=null&&node.left.val!=node.val){
            return false;
        }
        if(node.right!=null&&node.right.val!=node.val){
            return false;
        }
        count[0]++;
        return true;
    }

    public static int countUnivalSubtrees(TreeNode root) {
        rs=0;
        //Space : O(H)
        //Time : O(n)
        solution(root);
        return rs;
    }

    public static int countUnivalSubtreesRefactor(TreeNode root) {
        int[] count=new int[1];
        //Space : O(H)
        //Time : O(n)
        solutionRefactor(root, count);
        return count[0];
    }

    public static void main(String[] args) {
        // Requirement
        //- A subtree of treeName is a tree consisting of a node in treeName and all of its descendants.
        //- (A uni-value) subtree means all nodes of the subtree have the same value.
        //  + Tức là subtree nhưng có tất cr các nodes có cùng value.
        //* return lại số (uni-value subtrees)
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of the node in the tree will be in the range [0, 1000].
        //-1000 <= Node.val <= 1000
        //
        //- Brainstorm
        //Ex:
        //root=[5,5,5,5,5,null,5]
        //          5
        //       /    \
        //     5       5
        //  /    \       \
        // 5      5       5
        //rs=6
        //- 1 tree được gọi là uni nếu all nodes có cùng value ==> Check value
        //  + rs là global value được.
        //- Nếu node hiện tại có left/ right không phải là uni --> Nó chắc chắn không phải uni
        //
        //1.1, Optimization
        //- Ta làm bài này mà không sử dụng global variable
        //==> Pass array hoặc object vào là được.
        //
        //1.2, Complexity
        //+ H is the height of the tree
        //+ N is the number of nodes
        //- Space : O(H)
        //- Time : O(N)
        //
        //#Reference:
        //687. Longest Univalue Path
        //
        countUnivalSubtrees(null);
        countUnivalSubtreesRefactor(null);
    }
}
