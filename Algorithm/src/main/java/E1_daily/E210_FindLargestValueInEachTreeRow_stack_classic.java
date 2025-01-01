package E1_daily;

import javafx.util.Pair;

import java.util.*;

public class E210_FindLargestValueInEachTreeRow_stack_classic {

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

    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> rs=new ArrayList<>();

        if(root==null){
            return rs;
        }
        Queue<TreeNode> nodes=new LinkedList<>();
        nodes.add(root);
        rs.add(root.val);

        while(!nodes.isEmpty()){
            int n=nodes.size();
            int max=Integer.MIN_VALUE;
            boolean isValid=false;

            for(int i=0;i<n;i++){
                TreeNode nextNode = nodes.poll();
                if(nextNode.left!=null){
                    nodes.add(nextNode.left);
                    max=Math.max(max, nextNode.left.val);
                    isValid=true;
                }
                if(nextNode.right!=null){
                    nodes.add(nextNode.right);
                    max=Math.max(max, nextNode.right.val);
                    isValid=true;
                }
            }
            if(max!=Integer.MIN_VALUE||isValid){
                rs.add(max);
            }
        }
        return rs;
    }

    public static List<Integer> largestValuesStack(TreeNode root) {
        List<Integer> rs=new ArrayList<>();

        if(root==null){
            return rs;
        }
        Stack<Pair<TreeNode, Integer>> stack=new Stack<>();
        stack.add(new Pair<>(root, 0));

        while(!stack.isEmpty()){
            Pair<TreeNode, Integer> pair=stack.pop();
            TreeNode node = pair.getKey();
            int depth=pair.getValue();

            if(depth==rs.size()){
                rs.add(node.val);
            }else{
                rs.set(depth, Math.max(rs.get(depth), node.val));
            }
            if(node.left!=null){
                stack.push(new Pair<>(node.left, depth+1));
            }
            if(node.right!=null){
                stack.push(new Pair<>(node.right, depth+1));
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //The number of nodes in the tree will be in the range [0, 10^4].
        //-2^31 <= Node.val <= 2^31 - 1
        //
        //- Brainstorm
        //- Dùng stack xem sao
        //          1
        //        /   \
        //      3      2
        //    /   \      \
        //  5      3      8
        //- size(rs) = depth
        //  + Tìm max bằng cách set lại depth cho mỗi element trong rs
        //
        //
        //#Reference:
        //737. Sentence Similarity II
        //2867. Count Valid Paths in a Tree
        //753. Cracking the Safe
        //
        //3331. Find Subtree Sizes After Changes
        //2316. Count Unreachable Pairs of Nodes in an Undirected Graph
        //2858. Minimum Edge Reversals So Every Node Is Reachable
    }
}
