package E1_Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class E48_ClosestLeafInABinaryTree_classic {

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

    public static void dfs(TreeNode node, HashMap<Integer, HashSet<Integer>> graph){
        HashSet<Integer> adj=graph.getOrDefault(node.val, new HashSet<>());
        if(node.left!=null){
            HashSet<Integer> adjLeft=graph.getOrDefault(node.left.val, new HashSet<>());
            adjLeft.add(node.val);
            graph.put(node.left.val, adjLeft);
            adj.add(node.left.val);
            dfs(node.left, graph);
        }
        if(node.right!=null){
            HashSet<Integer> adjRight=graph.getOrDefault(node.right.val, new HashSet<>());
            adjRight.add(node.val);
            graph.put(node.right.val, adjRight);
            adj.add(node.right.val);
            dfs(node.right, graph);
        }
        graph.put(node.val, adj);
    }

    public static int findClosestLeaf(TreeNode root, int k) {
        HashMap<Integer, HashSet<Integer>> graph=new HashMap<>();
        dfs(root, graph);
        int n=graph.size();
        Queue<int[]> nodes=new LinkedList<>();
        
        for(int i=1;i<=n;i++){
            if(graph.containsKey(i)&&graph.get(i).size()==1&&i!=root.val){
                if(i==k){
                    return k;
                }
                nodes.add(new int[]{i, i});
            }
        }
        boolean[] visited=new boolean[n+1];

        while (!nodes.isEmpty()){
            int[] curNode=nodes.poll();
            HashSet<Integer> adj=graph.get(curNode[0]);
            visited[curNode[0]]=true;

            for(int nextNode: adj){
                if(!visited[nextNode]){
                    if(nextNode==k){
                        return curNode[1];
                    }
                    nodes.add(new int[]{nextNode, curNode[1]});
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (the root of a binary tree) where every node has (a unique value) and (a target integer k),
        //* return (the value of the nearest leaf node) to (the target k in the tree).
        //- Nearest to (a leaf) means (the least number of edges) traveled on the binary tree to reach (any leaf of the tree).
        // Also, a node is called a leaf if it has no children.
        //  + Find (the leaf node) is nearest (the k node)
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //The number of nodes in the tree is in the range [1, 1000].
        //1 <= Node.val <= 1000
        //All the values of the tree are unique.
        //There exist some node in the tree where Node.val == k.
        //  + num_nodes <=1000 ==> Time: O(n^2)
        //
        //- Brainstorm
        //          1
        //        /   \
        //      2     (5)
        //    /  \      \
        //  3     4      6
        //                \
        //                 7
        //                /
        //               8
        //          1
        //        /   \
        //      2      5
        //    /  \      \
        //  3    (4)     6
        //         \      \
        //          5      7
        //                /
        //               8
        //- Tìm khoảng cách giữa 2 nodes
        //- Find the all of the distances between (leaf nodes) and (k target)
        //
        //* KINH NGHIỆM:
        //- For the tree:
        //  + To determine the distance:
        //      + If we need to find the shortest distance from multiple nodes
        //      ==> Convert tree to graph (BFS)
        //      + If we find the distance between two nodes:
        //          + ==> Use diemeter approach
        //
//        Integer[] nodes = {1,3,2};
//        int k = 1;
        Integer[] nodes = {1,2,3,4,null,null,null,5,null,6};
        int k = 2;
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
        System.out.println(findClosestLeaf(root, k));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //652. Find Duplicate Subtrees
        //1676. Lowest Common Ancestor of a Binary Tree IV
        //1305. All Elements in Two Binary Search Trees
        //814. Binary Tree Pruning
        //2322. Minimum Score After Removals on a Tree
        //2773. Height of Special Binary Tree
    }
}
