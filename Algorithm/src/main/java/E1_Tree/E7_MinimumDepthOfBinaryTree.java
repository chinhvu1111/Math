package E1_Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class E7_MinimumDepthOfBinaryTree {

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

    public static int minDepth(TreeNode root) {
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

                if(currentNode.left==null&&currentNode.right==null){
                    // System.out.printf("Exit %s %s\n", currentNode.left, currentNode.right);
                    return level;
                }
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
        //** Đề bài
        //- Trả lại số lượng nodes tối thiểu --> Để có thể đi đến leaf node
        //
        //** Tư duy
        //1.
        //1.0, Idea
        //- Dạng độ sâu này --> Dùng bfs sẽ là nhanh nhất
        //--> Nhưng ta cần tìm shortest path <=> Level của tree
    }
}
