package E1_leetcode_medium_dynamic;

import java.util.HashMap;

public class E119_BinaryTreeMaximumPathSum {

    public class TreeNode {
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

    public static HashMap<TreeNode, Integer> memo;

    public static int solutionTopDown(TreeNode node, int[] rs){
        if(node==null){
            return 0;
        }
        if(memo.containsKey(node)){
            return memo.get(node);
        }
        int left=0, right=0;
        left=Math.max(left, solutionTopDown(node.left, rs));
        right=Math.max(right, solutionTopDown(node.right, rs));
        int curVal=node.val;
        rs[0]=Math.max(rs[0], left+right+curVal);
        int maxBranch=Math.max(left, right)+curVal;
        memo.put(node, maxBranch);
        return maxBranch;
    }

    public static int maxPathSum(TreeNode root) {
        if(root==null){
            return 0;
        }
        memo=new HashMap<>();
        int[] rs=new int[1];
        rs[0]=root.val;
        solutionTopDown(root, rs);
        return rs[0];
    }

    public static void main(String[] args) {
        //** Requirement:
        //- A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
        // A node can only appear in the sequence (at most once).
        //- Note that the path (does not need) to pass through the (root).
        //The path sum of a path is the sum of the node's values in the path.
        //Given the root of a binary tree,
        //* Return the maximum path sum of (any non-empty path).
        //--> Return max sum của all path dù không qua root
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //The number of nodes in the tree is in the range [1, 3 * 10^4].
        //-1000 <= Node.val <= 1000
        //
        //- Brainstorm
        //Ex:
        //       10
        //     /    \
        //    9     20
        //  /     /    \
        //2     10      7
        //==> Max path có thể là : (2 - 9 - 10 - 20 - 10)
        //- Bài này có thể làm bottom up approach
        //- Binary tree
        //Node1 chỉ có thể:
        //  + Go left
        //  + Go right
        //- Nếu không đi left ==> Không thể đi tiếp được.
        //Ex:
        //       1
        //     /    \
        //    1    -1000
        //  /     /    \
        //2     10      7
        //         \
        //          25
        //         /
        //       4
        //=> [10] = 10 + 25 + 4 = 39
        //- Tức là mỗi node --> lưu kết quả tại node đó nếu path đi qua node đó.
        //- Top down :
        //  + Ta sẽ tính dần dần sum node từ dưới lên.
        //
        //Ex:
        //          5
        //        /
        //      4
        //    /  \
        //  11    2
        //--> 5 sẽ được tính theo branch nào (11/2)
        //- Ta hiểu sai ==> Tính theo (left + right)+ curVal
        //  + Cái này chỉ đúng khi
        //          1
        //        /   \
        //      2      3 => rs=6
        //- Bài này sẽ chuyển thành việc tính max left, right của từng node.
        //Ex:
        //Ex:
        //          5
        //        /    \
        //      4       9
        //    /  \        \
        //  11    2       10
        //- max left(5) = 5 + 4 + 11 = 20
        //- max right(5) = 5 + 9 + 10 = 24
        //- max left(4) = 4 + 11
        //- max right(4) = 4 + 2
        //==> max left(5) = max(max right(4), max left(4), 5)
        //- Với idea này thì code method sẽ là:
        //  + return thì là max(left, right)
        //  + rs sẽ được check liên tục (left + right + val)
        //
        //1.2, Optimization
        //- Ở đây không cần dùng dp vì ta không cần re-traverse lại các node cũ
        //
        //1.3, Complexity
        //- Space : O(n)
        //- Time : O(n)
        //
        //#Reference:
        //129. Sum Root to Leaf Numbers
        //666. Path Sum IV
        //687. Longest Univalue Path
        //
    }
}
