package E1_daily;

import E1_Tree.E45_ReverseOddLevelsOfBinaryTree_classic;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

public class E287_LowestCommonAncestorOfDeepestLeaves {

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

    public static TreeNode[] getDeepestNodes(TreeNode node){
        Queue<TreeNode> nodes=new LinkedList<>();
        nodes.add(node);
        TreeNode firstNode=null, lastNode=null;

        while (!nodes.isEmpty()) {
            int size=nodes.size();
            firstNode=null;
            lastNode=null;

            for (int i = 0; i < size; i++) {
                TreeNode curNode = nodes.poll();
                if(curNode!=null){
                    if(firstNode==null){
                        firstNode=curNode;
                    }else{
                        lastNode=curNode;
                    }
                }
                if(curNode.left!=null){
                    nodes.add(curNode.left);
                }
                if(curNode.right!=null){
                    nodes.add(curNode.right);
                }
            }
        }
        return new TreeNode[]{firstNode, lastNode};
    }

    public static TreeNode LCA(TreeNode node, TreeNode p, TreeNode q){
        if(node==null||node==p||node==q){
            return node;
        }
        TreeNode left=LCA(node.left, p, q);
        TreeNode right=LCA(node.right, p, q);

        if(left!=null&&right!=null){
            return node;
        }else if(left!=null){
            return left;
        }
        return right;
    }

    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        TreeNode[] deepestNodes = getDeepestNodes(root);
//        System.out.printf("%s %s\n", deepestNodes[0].val, deepestNodes[1].val);
        return LCA(root, deepestNodes[0], deepestNodes[1]);
    }

    static int deepest = 0;
    static TreeNode lca;

    public static TreeNode lcaDeepestLeavesRefer(TreeNode root) {
        helper(root, 0);
        return lca;
    }

    private static int helper(TreeNode node, int depth) {
        deepest = Math.max(deepest, depth);
        if (node == null) {
            return depth;
        }
        int left = helper(node.left, depth + 1);
        int right = helper(node.right, depth + 1);
        if (left == deepest && right == deepest) {
            lca = node;
        }
        return Math.max(left, right);
    }

    static class Pair {
        TreeNode node;
        int d;
        Pair(TreeNode node, int d) {
            this.node = node;
            this.d = d;
        }
    }

    public static TreeNode lcaDeepestLeavesRefer1(TreeNode root) {
        Pair p = getLca(root, 0);
        return p.node;
    }
    private static Pair getLca(TreeNode root, int d) {
        if (root == null) return new Pair(null, d);
        Pair l = getLca(root.left, d + 1);
        Pair r = getLca(root.right, d + 1);
        if (l.d == r.d) {
            return new Pair(root, l.d);
        } else {
            return  l.d > r.d ? l : r;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the root of a binary tree,
        //* return (the lowest common ancestor) of (its deepest leaves).
        //- Recall that:
        //  + (The node of a binary tree) is a leaf if and only if (it has no children)
        //  + (The depth of the "root" of the tree) is 0. if the depth of a node is d,
        //  the depth of (each of its children) is (d + 1).
        //  + (The lowest common ancestor) of (a set S of nodes), is the node A with (the largest depth)
        //  such that (every node in S) is in the subtree with (root A).
        //
        //Input: root = [3,5,1,6,2,0,8,null,null,7,4]
        //Output: [2,7,4]
        //Explanation: We return the node with value 2, colored in yellow in the diagram.
        //The nodes coloured in blue are the deepest leaf-nodes of the tree.
        //Note that nodes 6, 0, and 8 are also leaf nodes,
        // but the depth of them is 2, but the depth of nodes 7 and 4 is 3.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //* Brainstorm:
        //- I assume I have N deepest nodes
        //  + So we need to find the common lowest parent of them
        //  ==> We need to get list of the deepest nodes.
        //==> We don't need to get all of (the deepest nodes)
        //  + From the last layer ==> We just need to get 2 nodes from (first, last) of this layer
        //- BFS + LCA
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- We use the max depth as the main solution:
        //  + We update the latest depth when we go deeper
        //- If left.depth == right.depth:
        //  + return Node(current_node, right.depth)
        //else:
        //  + Return the node with larger depth
        //
        //1.3, Complexity
        //- Space: O(h)
        //- Time: O(n)
        //
        Integer[] nodes=new Integer[]{3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root=new TreeNode(nodes[0]);
        Queue<TreeNode> queueNodes=new LinkedList<>();
        queueNodes.add(root);
        int i=0;
        for(;i<nodes.length;i++){
            TreeNode currentNode=queueNodes.poll();
            TreeNode next=null;
            TreeNode next1=null;
            if(i*2+1<nodes.length&&nodes[i*2+1]!=null){
                next=new TreeNode(nodes[i*2+1]);
                queueNodes.add(next);
            }
            if(i*2+2<nodes.length&&nodes[i*2+2]!=null){
                next1=new TreeNode(nodes[i*2+2]);
                queueNodes.add(next1);
            }
            if(currentNode!=null){
                currentNode.left=next;
                currentNode.right=next1;
            }
        }
//        TreeNode node = lcaDeepestLeaves(root);
//        TreeNode node = lcaDeepestLeavesRefer(root);
        TreeNode node = lcaDeepestLeavesRefer1(root);
        System.out.println(node.val);
    }
}
