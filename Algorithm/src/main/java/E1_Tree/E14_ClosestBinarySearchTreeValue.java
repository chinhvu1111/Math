package E1_Tree;

public class E14_ClosestBinarySearchTreeValue {

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

    public static double subtraction;
    public static double result;
    public static void dfs(TreeNode node, double target){
        if(node==null){
            return;
        }
        if(subtraction>=Math.abs(node.val-target)){
            if(result>node.val&&subtraction==Math.abs(node.val-target)){
                result=node.val;
                subtraction=Math.abs(node.val-target);
            }else if(subtraction>Math.abs(node.val-target)){
                result=node.val;
                subtraction=Math.abs(node.val-target);
            }
        }
        // System.out.printf("%s %s\n",result, subtraction);
        if(target>node.val){
            dfs(node.right, target);
        }else {
            dfs(node.left, target);
        }
    }

    public static int closestValue(TreeNode root, double target) {
        subtraction=Integer.MAX_VALUE;
        result=Integer.MAX_VALUE;
        dfs(root, target);
        return (int) result;
    }
    
    public static void main(String[] args) {
        //** Requirement
        //- Cho 1 binary tree + target (double) --> Tìm điểm closest to target
        //- Closest tức là số gần target nhất
        //VD: 3, 3.7, 4 ==> 3.7 cách 4 (0.3) nên nó là closest.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //
        //- Brainstorm
        //- Đơn giản là ta sẽ traverse tree:
        //+ Xét hiệu của từng số
        //
        //1.1, Optimization
        //
        //1.2, Complexity:
        //+ N is the number of node
        //- Time complexity : O(N)
        //- Space complexity : O(N)
        //
        //#Reference:
        //272. Closest Binary Search Tree Value II
        //700. Search in a Binary Search Tree
        //2476. Closest Nodes Queries in a Binary Search Tree
    }
}
