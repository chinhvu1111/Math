package E1_Tree;

import java.util.*;

public class E10_BinaryTreeZigzagLevelOrderTraversal {

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

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();

        if(root==null){
            return rs;
        }
        Deque<TreeNode> listNodes=new LinkedList<>();
        listNodes.add(root);
        rs.add(Collections.singletonList(root.val));
        boolean isLeftToRight=false;

        while (!listNodes.isEmpty()){
            List<TreeNode> nextLevelNodes=new ArrayList<>();
            List<Integer> nextNodes=new ArrayList<>();

            while (!listNodes.isEmpty()){
                TreeNode currentNode;
                currentNode=listNodes.removeFirst();

                if(currentNode.left!=null){
                    nextLevelNodes.add(currentNode.left);
                    nextNodes.add(currentNode.left.val);
                }
                if(currentNode.right!=null){
                    nextLevelNodes.add(currentNode.right);
                    nextNodes.add(currentNode.right.val);
                }
            }
            if(!isLeftToRight){
                Collections.reverse(nextNodes);
            }
            listNodes.addAll(nextLevelNodes);
            if(!nextNodes.isEmpty()){
                rs.add(nextNodes);
            }
            isLeftToRight=!isLeftToRight;
            System.out.println(nextNodes);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Đề bài
        //- Trả lại danh sách nodes của từng level theo kiểu zig zac:
        //VD: left to right --> level +1 --> right to left.
        //
        //** Tư duy
        //1.
        //1.0, Idea
        //- Ta có thể dùng iterative way để có thể kiểm soát kiểu loop
        //+ Ta sẽ lưu 1 bit variable để chuyển cách duyệt
        //+ Thay vì tư duy chuyển từ 0 --> n-1 ==> n-1 --> 0
        //==> Lúc add ta sẽ kiểm soát index lúc add vào list (i)
        //VD:
        //i: 0 -> n-1 : add(i+k)
        //- K có 2 case:
        //+ k=0
        //+ k=n-1
        //==> Nhầm bài này cần dùng deque
        //VD:
        //                  1
        //              /      \
        //            2          3
        //          /   \      /   \
        //        4      5    6     7
        //--> Do hơi lằng ngoằng --> Cuối cùng vẫn lên reverse
        //#Reference:
        //104. Maximum Depth of Binary Tree
        //1522. Diameter of N-Ary Tree
        //2101. Detonate the Maximum Bombs
        //1026. Maximum Difference Between Node and Ancestor
    }
}
