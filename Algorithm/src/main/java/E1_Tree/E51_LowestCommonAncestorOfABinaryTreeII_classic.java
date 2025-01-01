package E1_Tree;

public class E51_LowestCommonAncestorOfABinaryTreeII_classic {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static boolean[] solution(TreeNode node, TreeNode p, TreeNode q, TreeNode[] rs){
        if(node==null){
            return new boolean[]{false, false};
        }
        boolean[] existsOnLeft = solution(node.left, p, q, rs);
        boolean[] existsOnRight = solution(node.right, p, q, rs);
        boolean[] curResult = new boolean[2];
        if(node==p){
            curResult[0]=true;
        }else if(node==q){
            curResult[1]=true;
        }
        curResult[0]=curResult[0]||existsOnLeft[0]||existsOnRight[0];
        curResult[1]=curResult[1]||existsOnRight[1]||existsOnLeft[1];
        if(rs[0]==null&&curResult[0]&&curResult[1]){
            rs[0]=node;
        }
        return curResult;
    }

    public static TreeNode lowestCommonAncestorUnoptimize(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode[] rs=new TreeNode[1];
        solution(root, p, q, rs);
        return rs[0];
    }

    public TreeNode lowestCommonAncestor(
            TreeNode root,
            TreeNode p,
            TreeNode q
    ) {
        // Step 1: Find the lowest common ancestor of nodes p and q
        TreeNode ans = LCA(root, p, q);

        // Step 2: Check if the LCA is p, meaning q must be in p's subtree
        if (ans == p) {
            // Verify if q is in the subtree of p
            return dfs(p, q) ? p : null;
        }
        // Step 3: Check if the LCA is q, meaning p must be in q's subtree
        else if (ans == q) {
            // Verify if p is in the subtree of q
            return dfs(q, p) ? q : null;
        }

        // Step 4: If neither p nor q is the ancestor of the other, return the LCA
        return ans;
    }

    private static TreeNode LCA(TreeNode node, TreeNode p, TreeNode q) {
        // Base case: if the current node is null, p, or q, return the current node
        if (node == null || node == p || node == q) return node;

        // Recursive case: find LCA in left and right subtrees
        TreeNode left = LCA(node.left, p, q);
        TreeNode right = LCA(node.right, p, q);

        // If p and q are found in different subtrees, current node is their LCA
        if (left != null && right != null) return node;
            // Otherwise, return the non-null result (either left or right)
        else if (left != null) return left;
        else return right;
    }

    private static boolean dfs(TreeNode node, TreeNode target) {
        // Base case: target found
        if (node == target) return true;

        // Base case: reached null, target not found
        if (node == null) return false;

        // Recursive case: search target in left or right subtree
        return dfs(node.left, target) || dfs(node.right, target);
    }

    static boolean nodesFound = false;

    public static TreeNode lowestCommonAncestorOptimization(
            TreeNode root,
            TreeNode p,
            TreeNode q
    ) {
        // Start DFS traversal to find the lowest common ancestor
        TreeNode ans = dfs1(root, p, q);
        // Return the result only if both nodes are found
        return nodesFound ? ans : null;
    }

    private static TreeNode dfs1(TreeNode node, TreeNode p, TreeNode q) {
        // Base case: If the node is null, return null
        if (node == null) return null;

        // Recursively search the left and right subtrees
        TreeNode left = dfs1(node.left, p, q);
        TreeNode right = dfs1(node.right, p, q);

        // Check conditions for current node being part of the solution
        int conditions = 0;
        if (node == p || node == q) conditions++;
        if (left != null) conditions++;
        if (right != null) conditions++;
        if (conditions == 2) nodesFound = true; // Mark that both nodes are found

        // Determine if current node is the lowest common ancestor
        if (
                (left != null && right != null) || node == p || node == q
        ) return node;

        // Return the non-null child, if any
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the root of a binary tree,
        //* return the lowest common ancestor (LCA) of two given nodes, p and q.
        //- If either node p or q does not exist in the tree, return null.
        //- All values of the nodes in the tree are unique.
        //- According to the definition of (LCA) on Wikipedia: "(The lowest common ancestor) of two nodes p and q
        // in a binary tree T is the lowest node that has (both p and q as descendants)
        // (where we allow a node to be a descendant of itself)".
        //- A descendant of (a node x) is (a node y) that is on the path from (node x) to (some leaf node).
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //- Brainstorm
        //- Nếu dùng cách:
        //  + If node!=null or p!=null or q!=null:
        //      return p
        //- If we do this way, it can not handle the p,q don't exist in the binary tree
        //  + It will return p or q if one of them exists
        //  ==> We need to return null rather than p or q
        //- We need to use DFS to check whether p and q exist or not
        //
        //1.1, Optimization
        //- conditions is count the number of node
        //  + if (conditions == 2) nodesFound = true; // Mark that both nodes are found
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //235. Lowest Common Ancestor of a Binary Search Tree
        //1650. Lowest Common Ancestor of a Binary Tree III
        //1676. Lowest Common Ancestor of a Binary Tree IV
    }
}
