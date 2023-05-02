package E1_Tree;

import java.util.ArrayList;
import java.util.List;

public class E6_AverageOfLevelsInBinaryTree {

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

    static class State{
        public double sum;
        public double num;

        public State(double sum, double num) {
            this.sum = sum;
            this.num = num;
        }
    }

    public static List<State> listState=null;
    public static List<Double> averageOfLevels(TreeNode root) {
        listState=new ArrayList<>();
        List<Double> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        solution(root, 0);
        for(State s: listState){
            // System.out.printf("%s %s\n", s.sum, s.num);
            rs.add(s.sum/s.num);
        }
        return rs;
    }

    public static void solution(TreeNode node, int level){
        if(node==null){
            return;
        }
        if(listState.size()<=level){
            listState.add(new State(0, 0));
        }
        State state=listState.get(level);
        state.sum+=node.val;
        state.num++;
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
        //- Dùng state để lưu sum của all nodes từng level và tổng số nodes của từng level
        //+ Sau đó tính rs bằng cách chia
        //
        //1.1, Complexity:
        //- Time complexity : O(n)
        //- Space complexity : O(h)
        //
        //#Reference:
        //638. Shopping Offers
        //998. Maximum Binary Tree II
        //417. Pacific Atlantic Water Flow
        //1315. Sum of Nodes with Even-Valued Grandparent
        //
    }
}
