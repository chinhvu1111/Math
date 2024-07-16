package E1_weekly;

import java.util.HashMap;
import java.util.Map;

public class E6_FindDistanceInABinaryTree {

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

    public static Map<Integer, Boolean> mapQ;
    public static Map<Integer, Boolean> mapP;

    public static boolean getParent(TreeNode node, int v, Map<Integer, Boolean> map){
        if(node==null){
            return false;
        }
        if(node.val==v){
            map.put(node.val, true);
            return true;
        }
        boolean exists=getParent(node.left, v, map);
        if(!exists){
            exists=getParent(node.right, v, map);
        }
        if(exists){
            map.put(node.val, true);
        }
        return exists;
    }
    public static TreeNode commonRoot;

    public static void findCommonRoot(TreeNode node, int p, int q){
        if(node==null){
            return;
        }
        if(mapP.containsKey(node.val)&&mapQ.containsKey(node.val)){
            commonRoot=node;
        }
        findCommonRoot(node.left, p, q);
        findCommonRoot(node.right, p, q);
    }
    public static int getHeight(TreeNode node, int val){
        if(node==null){
            return Integer.MAX_VALUE;
        }
        if(node.val==val){
            return 0;
        }
        int curHeight=getHeight(node.right, val);
        if(curHeight==Integer.MAX_VALUE){
            curHeight=getHeight(node.left, val);
        }
        return curHeight==Integer.MAX_VALUE?curHeight:curHeight+1;
    }

    public static int findDistance(TreeNode root, int p, int q) {
        mapQ=new HashMap<>();
        mapP=new HashMap<>();
        mapP.put(root.val, true);
        mapQ.put(root.val, true);
        getParent(root, p, mapP);
        getParent(root, q, mapQ);
        // System.out.println(mapP);
        // System.out.println(mapQ);
        findCommonRoot(root, p, q);
        // System.out.println(commonRoot.val);
        int rootHeight=getHeight(root, commonRoot.val);
        int qHeight=getHeight(root, q);
        int pHeight=getHeight(root, p);
        // System.out.println(qHeight);
        // System.out.println(pHeight);
        return pHeight+qHeight-2*rootHeight;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the root of a binary tree and (two integers p and q),
        //* Return (the distance) between the nodes of value (p and value q) in the tree.
        //- The distance between two nodes is (the number of edges) on the path from (one to the other).
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //The number of nodes in the tree is in the range [1, 10^4].
        //0 <= Node.val <= 10^9
        //All Node.val are unique.
        //p and q are values in the tree.
        //
        //- Brainstorm
        //Ex:
        //           3
        //        /     \
        //     [5]       1
        //    /  \      /  \
        //   6    2  [0]    8
        //      /  \
        //    7     4
        //- Tìm root chung của 2 node q và p
        //  + distance = heigh(q) + height(p) - 2*height(root)
        //
        Integer[] root = {3,5,1,6,2,0,8,null,null,7,4};
        int p = 5, q = 0;
        TreeNode rootNode=new TreeNode(root[0]);

        for(int i=1;i<root.length;i++){

        }
        //
        //#Reference:
        //2096. Step-By-Step Directions From a Binary Tree Node to Another
    }
}
