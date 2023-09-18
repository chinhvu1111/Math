package E1_binary_search_topic;

import java.util.HashMap;

public class E7_LongestZigZagPathInABinaryTree {

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

    public static HashMap<TreeNode, Integer> depthLeft;
    public static HashMap<TreeNode, Integer> depthRight;

    public static int solution(TreeNode node, int direction){
        if(direction==1&&depthLeft.containsKey(node)){
            return depthLeft.get(node);
        }else if(direction==2&&depthRight.containsKey(node)){
            return depthRight.get(node);
        }
        if(node==null){
            return 0;
        }
        int rs;
        if(direction==1){
            rs=solution(node.right, 2)+1;
            rs=Math.max(rs, solution(node.left, 1));
        }else{
            rs=solution(node.left, 1)+1;
            rs=Math.max(rs, solution(node.right, 2));
        }
        if(direction==1){
            depthLeft.put(node, rs);
        }else{
            depthRight.put(node, rs);
        }
        return rs;
    }

    public static int longestZigZagWrong(TreeNode root) {
        depthLeft=new HashMap<>();
        depthRight=new HashMap<>();
        //Time : O(n)
        int left=solution(root.left, 1);
        int right=solution(root.right, 2);
        return Math.max(left, right);
    }

    public static int result;

    public static void solution(TreeNode node, int direction, int value){
        if(node==null){
            return;
        }
        result=Math.max(result, value);
        if(direction==1){
            //Time : O(n)
            solution(node.right, 2, value+1);
            solution(node.left, 1, 1);
        }else{
            //Time : O(n)
            solution(node.left, 1, value+1);
            solution(node.right, 2, 1);
        }
    }

    public static int longestZigZag(TreeNode root) {
        result=0;
        //Time : O(n)
        solution(root, 1, 0);
        solution(root, 2, 0);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        longestZigZag(root);
        longestZigZagWrong(root);
        // Đề bài:
        //- Ta có lấy path zigzag từ bất cứ node nào có trong tree
        //* Return độ dài dài nhất theo hình zigzag của binary tree
        //
        // Tư duy
        //1.
        //1.1, Idea
        //+ The number of nodes in the tree is in the range [1, 5 * 10^4].
        //+ 1 <= Node.val <= 100
        //
        //- Brainstorm
        //* Idea sai:
        //- Khi ta cố gắng dùng memorize recursion để lưu thông tin depth ==> Phức tạp ra
        //--> Ta có thể đổi bằng cách put value vào input của recursion + (global static variable) ==> Để update result
        //- Ở đây ta sẽ gọi 4 recursion function
        //=======
        //if(direction==1){
        //    solution(node.right, 2, value+1);
        //    solution(node.left, 1, 1);
        //}else{
        //    solution(node.left, 1, value+1);
        //    solution(node.right, 2, 1);
        //}
        //=======
        //- if(node==null) return null không được tính vào length của result
        //--> Ta sẽ check max sau đó (Vì node cuối không có (null))
        //1.1, Optimization
        //1.2, Complexity:
        //- Space: O(n)
        //- Time : O(n)
        //
        //#Reference:
        //2484. Count Palindromic Subsequences
        //1530. Number of Good Leaf Nodes Pairs
        //1335. Minimum Difficulty of a Job Schedule
    }
}
