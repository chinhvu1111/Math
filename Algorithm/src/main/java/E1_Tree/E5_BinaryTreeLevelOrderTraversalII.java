package E1_Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E5_BinaryTreeLevelOrderTraversalII {

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

    public static List<List<Integer>> rs=null;

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        rs=new ArrayList<>();
        solution(root, 0);
        Collections.reverse(rs);
        return rs;
    }

    public static void solution(TreeNode node, int level){
        if(node==null){
            return;
        }
        if(rs.size()<=level){
            rs.add(new ArrayList<>());
        }
        List<Integer> listNode=rs.get(level);
        listNode.add(node.val);
        solution(node.left, level+1);
        solution(node.right, level+1);
    }

    public static void main(String[] args) {
        //** Đề bài
        //- Trả lại danh sách các nodes từng level (Từ dưới lên) (bottom up)
        //
        //** Tư duy
        //1.
        //1.0, Idea
        //VD:
        //            3
        //          /   \
        //        9      20
        //             /    \
        //           15      7
        //- Ta có thể reverse là xong
        //
        //#Reference:
        //108. Convert Sorted Array to Binary Search Tree
        //637. Average of Levels in Binary Tree
    }
}
