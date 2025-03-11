package E1_daily;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class E262_RecoverATreeFromPreorderTraversal {

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

    public static TreeNode recoverFromPreorder(String traversal) {
        Stack<Pair<Integer, TreeNode>> stack = new Stack<>();
        int n = traversal.length();
        int rootVal = 0;
        int i = 0;
        while (i < n && traversal.charAt(i) != '-') {
            rootVal = rootVal * 10 + traversal.charAt(i) - '0';
            i++;
        }
        TreeNode root = new TreeNode(rootVal);
        stack.add(new Pair<>(0, root));

        for (; i < n; i++) {
            int curDepth = 0;
            while (i < n && traversal.charAt(i) == '-') {
                curDepth++;
                i++;
            }
            while (!stack.isEmpty() && stack.peek().getKey() >= curDepth) {
                stack.pop();
            }
            int curVal = 0;
            while (i < n && traversal.charAt(i) != '-') {
                curVal = curVal * 10 + traversal.charAt(i) - '0';
                i++;
            }
            i--;
            if (stack.isEmpty()) {
                break;
            }
            TreeNode parentNode = stack.peek().getValue();
            TreeNode curNode = new TreeNode(curVal);
            Pair<Integer, TreeNode> nextPair = new Pair<>(curDepth, curNode);
            if (parentNode.left == null) {
                parentNode.left = curNode;
            } else if (parentNode.right == null) {
                parentNode.right = curNode;
            }
            stack.add(nextPair);
        }
        return root;
    }

    public static TreeNode recoverFromPreorderOptimization(String traversal) {
        Stack<Pair<Integer, TreeNode>> stack = new Stack<>();
        int n = traversal.length();
        //1--2---3

        for (int i = 0; i < n; i++) {
            int curDepth = 0;
            while (i < n && traversal.charAt(i) == '-') {
                curDepth++;
                i++;
            }
            int curVal = 0;
            while (i < n && traversal.charAt(i) != '-') {
                curVal = curVal * 10 + traversal.charAt(i) - '0';
                i++;
            }
            while (!stack.isEmpty() && stack.peek().getKey() >= curDepth) {
                stack.pop();
            }
            i--;
            TreeNode curNode = new TreeNode(curVal);
            Pair<Integer, TreeNode> nextPair = new Pair<>(curDepth, curNode);
            if (stack.isEmpty()) {
                stack.add(nextPair);
                continue;
            }
            TreeNode parentNode = stack.peek().getValue();
            if (parentNode.left == null) {
                parentNode.left = curNode;
            } else if (parentNode.right == null) {
                parentNode.right = curNode;
            }
            stack.add(nextPair);
        }
        return stack.firstElement().getValue();
    }

    public static TreeNode recoverFromPreorderList(String traversal) {
        List<TreeNode> levels = new ArrayList<>();
        int index = 0, n = traversal.length();

        while (index < n) {
            // Count depth (number of dashes)
            int depth = 0;
            while (index < n && traversal.charAt(index) == '-') {
                depth++;
                index++;
            }

            // Extract node value
            int value = 0;
            while (index < n && Character.isDigit(traversal.charAt(index))) {
                value = value * 10 + (traversal.charAt(index) - '0');
                index++;
            }

            // Create the new node
            TreeNode node = new TreeNode(value);

            // Adjust levels list to match the current depth
            if (depth < levels.size()) {
                levels.set(depth, node);
            } else {
                levels.add(node);
            }

            // Attach the node to its parent
            if (depth > 0) {
                TreeNode parent = levels.get(depth - 1);
                if (parent.left == null) {
                    parent.left = node;
                } else {
                    parent.right = node;
                }
            }
        }

        // The root node is always at index 0
        return levels.get(0);
    }

    public static void println(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        println(node.left);
        println(node.right);
    }

    public static void main(String[] args) {
        //** Requirement
        //- We run (a preorder depth-first search (DFS)) on the root of a binary tree.
        //- At (each node) in this traversal, we output D dashes (where D is the depth of this node),
        //  then we output the value of this node.
        //- If the depth of a node is D,
        //  + the depth of its (immediate child) is D + 1.
        //  + The depth of the root node is 0.
        //- If a node has only (one child), that child is guaranteed to be (the left child).
        //- Given the output traversal of this traversal,
        //* recover the tree and return its root.
        //Ex
        //                   1
        //                /     \
        //              2        5
        //            /   \     /  \
        //          3      4  6     7
        //
        //Ex:
        //Input: traversal = "1-2--3--4-5--6--7"
        //Output: [1,2,5,3,4,6,7]
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //The number of nodes in the original tree is in the range [1, 1000].
        //1 <= Node.val <= 10^9
        //  + Time: O(n^2)
        //
        //* Brainstorm:
        //depth_to_node[depth]:
        //                   1
        //                /     \
        //              2        5
        //            /   \     /  \
        //          3      4  6     7
        //
        //Ex:
        //Input: traversal = "1-2--3--4-5--6--7"
        //depth_to_node[0]: 1
        //depth_to_node[1]: 2, 5
        //depth_to_node[2]: 3, 4, 6, 7
        //
        //- Convert this information to a binary tree
        //
        //- However, we have this case:
        //                  1
        //                /   \
        //              2      5
        //            /       /
        //          3        6
        //        /        /
        //       4       7
        //
        //Input: traversal = "1-2--3---4-5--6---7"
        //Output: [1,2,5,3,null,6,null,4,null,7]
        //* So 6 is not (the right node) of (2)
        //==> Using stack
        //
        //1.1, Special cases
//        String traversal = "1-401--349---90--88";
        //- The number has multiple digits
        //
        //1.2, Optimization
        //- For the root node ==> count('-') = zero
        //  + Put (the initialization segment) for root into (the loop)
        //- Stack:
        //  + First element of stack = root
        //  + Last element of stack = last added element
        //
        //- Iterative Approach with List
        //  + Use the levels as list to get the parent from that:
        //      + To establish the relationship, we get (the previous list) with (the list node) of (the previous level)
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        String traversal = "1-2--3---4-5--6---7";
//        TreeNode root = recoverFromPreorder(traversal);
//        TreeNode root = recoverFromPreorderOptimization(traversal);
        TreeNode root = recoverFromPreorderList(traversal);
        println(root);
        //#Reference:
        //1932. Merge BSTs to Create Single BST
        //1443. Minimum Time to Collect All Apples in a Tree
        //676. Implement Magic Dictionary
        //
        //691. Stickers to Spell Word
        //2792. Count Nodes That Are Great Enough
        //3335. Total Characters in String After Transformations I
    }
}
