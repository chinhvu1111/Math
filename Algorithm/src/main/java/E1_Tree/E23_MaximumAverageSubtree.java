package E1_Tree;

public class E23_MaximumAverageSubtree {

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

    public static double[] solution(TreeNode node, double[] count){
        if(node==null){
            return new double[]{0, 0};
        }
        double[] left=new double[]{0, 0};

        if(node.left!=null){
            left=solution(node.left, count);
        }
        double[] right=new double[]{0, 0};

        if(node.right!=null){
            right=solution(node.right, count);
        }
        double sum=left[0]+right[0]+node.val;
        double numNode=left[1]+right[1]+1;
        count[0]=Math.max(count[0], sum/numNode);
        return new double[]{sum, numNode};
    }

    public static double maximumAverageSubtree(TreeNode root) {
        double[] count=new double[1];
        solution(root, count);
        return count[0];
    }

    public static void main(String[] args) {
        // Requirement
        //- Given root of binary tree
        // return the (maximum average value) of a subtree of that tree. Answers within 10-5 of the actual answer will be accepted.
        //- A subtree of a tree is any node of that tree (plus all its descendants).
        //- The (average value of a tree) is the sum of its values, divided by the number of nodes.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is in the range [1, 104].
        //0 <= Node.val <= 10^5
        //
        //- Brainstorm
        //- Bài này cơ bản là ta sẽ tính max của all node trong subtree --> Compare với parent node
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- N is the number of node
        //- H is height of tree
        //- Space : O(H)
        //- Time : O(N)
        //#Reference:
        //1973. Count Nodes Equal to Sum of Descendants
        //2265. Count Nodes Equal to Average of Subtree
    }
}
