package E1_weekly;

import java.util.Stack;

public class E4_SplitBST_classic {

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
    public static TreeNode[] solution(TreeNode node, int target){
        if(node==null){
            return new TreeNode[2];
        }
        //+ Target = 2
        //         4
        //      /    \
        //    (2)     6
        //   /  \   /  \
        //  1    3 5    7
        //+ return [2,3]
        //
        //+ target = 7
        //         5
        //      /    \
        //     3     (7) ==> [7,8] ==> [left, root] : root sẽ là node lớn hơn.
        //   /  \   /  \
        //  1   4  6   [8]
        //               \
        //                9
        //+ return [6,7]
        if(node.val>target){
            TreeNode[] leftInfor = solution(node.left, target);
            node.left= leftInfor[1];
            return new TreeNode[]{leftInfor[0], node};
        }else{
            TreeNode[] rightNodes = solution(node.right, target);
            node.right= rightNodes[0];
            return new TreeNode[]{node, rightNodes[1]};
        }
    }

    public static TreeNode[] splitBST(TreeNode root, int target) {
        return solution(root, target);
    }

    public static TreeNode[] splitBSTStack(TreeNode root, int target) {
        //+ Target = 2
        //         4
        //      /    \
        //    (2)      6
        //   /  \    /  \
        //  1    3  5    7
        //        \
        //        [x]
        //- Đoạn này ta sẽ đi ngược lên trên để tìm:
        //  + [left, root]
        //  + [root, right]
        //
        Stack<TreeNode> stack=new Stack<>();
        TreeNode[] ans = new TreeNode[2];

        if(root==null){
            return ans;
        }
        while(root!=null){
            stack.push(root);
            if(root.val>target){
                root=root.left;
            }else{
                root=root.right;
            }
        }
        //- [left, root]
        //  + Left <= target
        //  + root > target
        //- Từ dưới ta đi lên trên bằng cách gán liên tục ans[0/1]
        while (!stack.isEmpty()){
            TreeNode curNode = stack.pop();

            if(curNode.val>target){
                curNode.left=ans[1];
                ans[1]=curNode;
            }else{
                curNode.right=ans[0];
                ans[0]=curNode;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (the root of a binary search tree (BST)) and an integer (target),
        // split the tree into (two subtrees)
        //- where (the first subtree) has nodes that are (all smaller) or (equal) to (the target value),
        //- while (the second subtree) has all nodes that are greater than (the target value).
        //  + It is not necessarily the case that the tree contains a node with the (value target).
        //- Additionally, (most of the structure) of (the original tree) should remain.
        //- Formally, for (any child c) with (parent p) in the ("original") tree,
        //- if they are both in (the same subtree) after the split, then node c should still have (the parent p).
        //  + Nếu c có parent p sau khi split --> Trước đó ==> sẽ là parent p
        // Return (an array of the two roots) of (the two subtrees) in order.
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //The number of nodes in the tree is in the range [1, 50].
        //0 <= Node.val, target <= 1000
        //
        //- Brainstorm
        //Ex:
        //Input: root = [4,2,6,1,3,5,7], target = 2
        //Output: [[2,1],[4,3,6,null,null,5,7]]
        //+ Target = 2
        //         4
        //      /    \
        //    (2)     6
        //   /  \   /  \
        //  1    3 5    7
        //+ 3 -> 4 (Đoạn này có thể trả vể right) ==> Gán bằng parent.left = solution()
        //
        //- Tìm điểm (== target)
        //  + ==> right nối lên
        //  + return right
        //
        //+ Target = 2
        //         5
        //      /    \
        //     3     7
        //   /  \   /  \
        // (1)   4 6    8
        //        \
        //         x
        //
        // 3 ==> Không thể có case này vì ta đang tìm số <2 ==> Thứ tự của ta sẽ tìm smaller
        //  \
        //   4
        //- Tìm thấy điểm (<target)
        //  ==>
        //  + return parent
        //
        //- Nếu target nằm bên right thì sao?
        //+ target = 7
        //         5
        //      /    \
        //     3     (7)
        //   /  \   /  \
        //  1   4  6   [8]
        //               \
        //                9
        //+ 8
        //   \
        //    9
        //- Tìm theo range có được không?
        //- Nếu traverse:
        //  + left: == (value>target)
        //  + right: == (value<=target)
        //
        //- Nếu target nằm left:
        //  + root.left = inforLeft[0]
        //  + left = inforLeft[1]
        //  + return {left, root}
        //- Nếu target nằm right:
        //  + root.right = inforRight[1]
        //  + right = inforLeft[0]
        //  + return {right, root}
        //
        //** Main point ở đây:
        //+ Target = 2
        //         4
        //      /    \
        //    (2)     6
        //   /  \   /  \
        //  1    3 5    7
        //+ return [2,3]
        //
        //+ target = 7
        //         5
        //      /    \
        //     3     (7) ==> [7,8] ==> [left, root] : root sẽ là node lớn hơn.
        //   /  \   /  \
        //  1   4  6   [8]
        //               \
        //                9
        //+ return [6,7]
        //* NOTE:
        //- [left, root]
        //  + Left <= target
        //  + root > target
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(H)
        //- Time: O(V+E)
        //
        //2. Stack
        //2.0,
        //- [left, root]
        //  + Left <= target
        //  + root > target
        //
        //* IDEA:
        //- [left, root]
        //  + Left <= target
        //  + root > target
        //- Từ dưới ta đi lên trên bằng cách gán liên tục ans[0/1]
        //
        //2.1, Optimization
        //- Có thể optimize space --> O(1)
        //  ==> Khá khó để nghĩ ra lúc pv
        //- Xem idea thôi.
        //
        //2.2, Complexity
        //- Space: O(n)
        //- Time : O(E+V)
        //
        //#Reference:
        //1469. Find All The Lonely Nodes
        //1022. Sum of Root To Leaf Binary Numbers
        //513. Find Bottom Left Tree Value
        //
        //669. Trim a Binary Search Tree
        //2973. Find Number of Coins to Place in Tree Nodes
        //2005. Subtree Removal Game with Fibonacci Tree
    }
}
