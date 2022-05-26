/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

import java.util.HashMap;

/**
 *
 * @author chinhvu
 */
public class HouseRobber_17 {

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
    
    public static HashMap<TreeNode, Integer> map=new HashMap<>();

    //
    public static Integer solution(TreeNode root, int thiefRob) {
        if (root == null) {
            return thiefRob;
        }
        if(map.get(root)!=null){
            return map.get(root);
        }

        int rsLeft = 0;
        int rsRight = 0;
        int rs = 0;

        if (root.left != null) {
            rsLeft = solution(root.left, thiefRob);
        }
        if (root.right != null) {
            rsRight = solution(root.right, thiefRob);
        }
        rs = rsLeft + rsRight;
        
        rsLeft=0;
        rsRight=0;
        if (root.left != null) {
            rsLeft = solution(root.left.left, thiefRob)
                    + solution(root.left.right, thiefRob);
        }
        if (root.right != null) {
            rsRight = solution(root.right.left, thiefRob)
                    + solution(root.right.right, thiefRob);
        }
        rs = Math.max(rs, rsLeft + rsRight+ root.val);
        map.put(root, rs);
        return rs;
    }

    public static int rob(TreeNode root) {
        int rs = solution(root, 0);
        System.out.println(rs);
        return rs;
    }

    public static TreeNode structTree(Integer root[]) {
        int n = root.length;
        TreeNode nodes[] = new TreeNode[n];

        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) {
                nodes[i] = new TreeNode(root[i]);
            }
            if (2 * i + 1 < n) {
                if (root[2 * i + 1] != null) {
                    nodes[2 * i + 1] = new TreeNode(root[2 * i + 1]);
                } else {
                    nodes[2 * i + 1] = new TreeNode();
                }
                nodes[i].left = nodes[2 * i + 1];
            }
            if (2 * i + 2 < n) {
                if (root[2 * i + 2] != null) {
                    nodes[2 * i + 2] = new TreeNode(root[2 * i + 2]);
                } else {
                    nodes[2 * i + 2] = new TreeNode();
                }
                nodes[i].right = nodes[2 * i + 2];
            }
        }
        return nodes[0];
    }

    public static void main(String[] args) {
        Integer roor[] = new Integer[]{3, 4, 5, 1, 3, null, 1};

        TreeNode node = structTree(roor);

        System.out.println(rob(node));
    }
}
