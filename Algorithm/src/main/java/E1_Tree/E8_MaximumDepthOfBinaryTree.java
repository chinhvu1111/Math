package E1_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E8_MaximumDepthOfBinaryTree {

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

    public static int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return solution(root);
    }
    public static int solution(TreeNode node){
        Queue<TreeNode> listNodes=new LinkedList<>();
        listNodes.add(node);
        // System.out.printf("%s %s\n", node.left, node.right);
        int level=1;

        while (!listNodes.isEmpty()){
            // System.out.println(listNodes.size());
            List<TreeNode> currentNodeLevels=new ArrayList<>();

            while (!listNodes.isEmpty()){
                TreeNode currentNode=listNodes.poll();
                if(currentNode.left!=null){
                    // listNodes.add(currentNode.left);
                    currentNodeLevels.add(currentNode.left);
                }
                if(currentNode.right!=null){
                    // listNodes.add(currentNode.right);
                    currentNodeLevels.add(currentNode.right);
                }
            }
            // System.out.println(currentNodeLevels);
            listNodes.addAll(currentNodeLevels);
            level++;
        }
        return level;
    }
    public static void main(String[] args) {
        //** KINH NGHIỆM:
        //- Làm bài leetcode cần:
        //+ Clear requirement trước
        //+ Set up constraint trước
        //  + Type data
        //  + return type
        //+ Set các cases đặc biệt : Tự dựng test cases đặc biệt để tests
        //
        //#Reference:
        //105. Construct Binary Tree from Preorder and Inorder Traversal
        //110. Balanced Binary Tree
        //559. Maximum Depth of N-ary Tree
        //1376. Time Needed to Inform All Employees
    }
}
