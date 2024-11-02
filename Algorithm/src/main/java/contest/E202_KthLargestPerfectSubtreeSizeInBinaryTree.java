package contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E202_KthLargestPerfectSubtreeSizeInBinaryTree {

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

    public static int kthLargestPerfectSubtree(TreeNode root, int k) {
        List<Integer> sizes = new ArrayList<>();
        findPerfectSubtrees(root, sizes);

        Collections.sort(sizes, Collections.reverseOrder());
        return k <= sizes.size() ? sizes.get(k - 1) : -1;
    }

    public static int[] findPerfectSubtrees(TreeNode node, List<Integer> sizes) {
        if (node == null) {
            return new int[]{1, 0}; // isPerfect, size
        }

        int[] left = findPerfectSubtrees(node.left, sizes);
        int[] right = findPerfectSubtrees(node.right, sizes);

        boolean isPerfect = left[0] == 1 && right[0] == 1 && left[1] == right[1];
        int size = 1 + left[1] + right[1];

        if (isPerfect) {
            sizes.add(size);
            return new int[]{1, size};
        } else {
            return new int[]{0, size};
        }
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given the root of a binary tree and an integer k.
        //* Return an integer denoting the size of the kth largest perfect binary subtree, or -1 if it doesn't exist.
        //- A perfect binary tree is a tree where all leaves are on (the same level), and every parent has (two children).
        //- A subtree of treeName is a tree consisting of a node in treeName and all of its descendants.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is in the range [1, 2000].
        //1 <= Node.val <= 2000
        //1 <= k <= 1024
        //  + Số node k lớn lắm
        //
        //** Brainstorm
        //- Check tree là perfect ntn?
        //  +
        //
    }
}
