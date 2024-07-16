package E1_daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class E50_CreateBinaryTreeFromDescriptions {

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

    public static TreeNode createBinaryTree(int[][] descriptions) {
//        int n= descriptions.length;
        HashMap<Integer, TreeNode> map=new HashMap<>();
        HashMap<Integer, Integer> degree=new HashMap<>();

        for(int[] des: descriptions){
            int parent=des[0];
            int child=des[1];
            int isLeft=des[2];
            TreeNode parentNode=map.get(parent);
            TreeNode childNode=map.get(child);

            if(!map.containsKey(parent)){
                parentNode=new TreeNode(parent);
            }
            if(!map.containsKey(child)){
                childNode=new TreeNode(child);
            }
            degree.put(child, degree.getOrDefault(child, 0)+1);
            if(isLeft==1){
                parentNode.left=childNode;
            }else{
                parentNode.right=childNode;
            }
            map.put(parent, parentNode);
            map.put(child, childNode);
        }
        for(int node: map.keySet()){
            if(degree.getOrDefault(node, 0)==0){
                return map.get(node);
            }
        }
        return null;
    }

    public static TreeNode createBinaryTreeRefer(int[][] descriptions) {
        // Maps values to TreeNode pointers
        Map<Integer, TreeNode> nodeMap = new HashMap<>();

        // Stores values which are children in the descriptions
        Set<Integer> children = new HashSet<>();

        // Iterate through descriptions to create nodes and set up tree structure
        for (int[] description : descriptions) {
            // Extract parent value, child value, and whether it is a
            // left child (1) or right child (0)
            int parentValue = description[0];
            int childValue = description[1];
            boolean isLeft = description[2] == 1;

            // Create parent and child nodes if not already created
            if (!nodeMap.containsKey(parentValue)) {
                nodeMap.put(parentValue, new TreeNode(parentValue));
            }
            if (!nodeMap.containsKey(childValue)) {
                nodeMap.put(childValue, new TreeNode(childValue));
            }

            // Attach child node to parent's left or right branch
            if (isLeft) {
                nodeMap.get(parentValue).left = nodeMap.get(childValue);
            } else {
                nodeMap.get(parentValue).right = nodeMap.get(childValue);
            }

            // Mark child as a child in the set
            children.add(childValue);
        }

        // Find and return the root node
        for (TreeNode node : nodeMap.values()) {
            if (!children.contains(node.val)) {
                return node; // Root node found
            }
        }

        return null; // Should not occur according to problem statement
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti]
        // indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,
        //  + If isLefti == 1, then childi is the left child of parenti.
        //  + If isLefti == 0, then childi is the right child of parenti.
        //- Construct the binary tree described by descriptions and (return its root).
        //- The test cases will be generated such that (the binary tree is valid).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= descriptions.length <= 10^4
        //descriptions[i].length == 3
        //1 <= parenti, childi <= 10^5
        //0 <= isLefti <= 1
        //The binary tree described by descriptions is valid.
        //
        //- Brainstorm
        //- Unique nodes
        //  ==> Lưu value vào hash map là được:
        //  + Map (value -> node)
        //- Xác định là root hay không dùng degree value là được:
        //  + Root có degree value == 0
        //  + Hoặc là add vào set là childrens là được
        //  ==> Node không phải children chính là root node.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the number of node
        //- M is the number of descriptions.
        //- Space: O(n)
        //- Time: O(n+m)
        //
        //#Reference:
        //109. Convert Sorted List to Binary Search Tree
        //1719. Number Of Ways To Reconstruct A Tree
    }
}
