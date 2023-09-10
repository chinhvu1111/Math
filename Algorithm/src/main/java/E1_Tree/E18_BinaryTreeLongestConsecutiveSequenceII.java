package E1_Tree;

import java.util.HashMap;

public class E18_BinaryTreeLongestConsecutiveSequenceII {

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

    public static int rs = 0;
    public static HashMap<TreeNode, Integer> maxDepthLeftIncrease;
    public static HashMap<TreeNode, Integer> maxDepthLeftDecrease;
    public static HashMap<TreeNode, Integer> maxDepthRightIncrease;
    public static HashMap<TreeNode, Integer> maxDepthRightDecrease;

    public static int dfs(TreeNode node, boolean isIncrease) {
        if(node==null){
            return 0;
        }
        int unit=isIncrease?1:-1;
        int left=0, right=0;

        if(node.left!=null){
            if(node.val-node.left.val==unit){
                left=dfs(node.left, isIncrease);
            }else{
                dfs(node.left, isIncrease);
            }
        }
        if(node.right!=null){
            if(node.val-node.right.val==unit){
                right=dfs(node.right, isIncrease);
            }else{
                dfs(node.right, isIncrease);
            }
        }
        if(isIncrease){
            maxDepthLeftIncrease.put(node, left);
            maxDepthRightIncrease.put(node, right);
        }else{
            maxDepthLeftDecrease.put(node, left);
            maxDepthRightDecrease.put(node, right);
        }
        return Math.max(left, right)+1;
    }

    public static void dfsRs(TreeNode node){
        if(node==null){
            return;
        }
        Integer maxLeftIncrease=maxDepthLeftIncrease.get(node);
        Integer maxLeftDecrease=maxDepthLeftDecrease.get(node);
        Integer maxRightIncrease=maxDepthRightIncrease.get(node);
        Integer maxRightDecrease=maxDepthRightDecrease.get(node);

        maxLeftIncrease=maxLeftIncrease==null?0:maxLeftIncrease;
        maxLeftDecrease=maxLeftDecrease==null?0:maxLeftDecrease;
        maxRightIncrease=maxRightIncrease==null?0:maxRightIncrease;
        maxRightDecrease=maxRightDecrease==null?0:maxRightDecrease;

        rs=Math.max(rs, maxLeftIncrease+maxRightDecrease+1);
        rs=Math.max(rs, maxLeftDecrease+maxRightIncrease+1);
    }

    public static void dfsOnePath(TreeNode node, int currentNode, boolean isIncrease) {
        rs = Math.max(rs, currentNode);
        if (node == null) {
            return;
        }
        int increase = isIncrease ? 1 : -1;
        if (node.left != null) {
            if (node.left.val == node.val - increase) {
                dfsOnePath(node.left, currentNode + 1, isIncrease);
            } else {
                dfsOnePath(node.left, 1, isIncrease);
            }
        }
        if (node.right != null) {
            if (node.right.val == node.val - increase) {
                dfsOnePath(node.right, currentNode + 1, isIncrease);
            } else {
                dfsOnePath(node.right, 1, isIncrease);
            }
        }
    }

    public int longestConsecutive(TreeNode root) {
        // maxDepth=new HashMap<>();
        maxDepthLeftIncrease=new HashMap<>();
        maxDepthLeftDecrease=new HashMap<>();
        maxDepthRightIncrease=new HashMap<>();
        maxDepthRightDecrease=new HashMap<>();
        rs = 0;
        if (root == null) {
            return 0;
        }
        // dfs(root,  true, -1);
        //Time : O(n)
        //Space : O(log(n))
        dfs(root, false);
        dfs(root, true);
        dfsRs(root);
        dfsOnePath(root, 1, false);
        dfsOnePath(root, 1, true);
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- 1 dãy thoả mãn khi chúng toàn bộ (decrease/ increase) cách nhau 1 đơn vị
        //- Path có thể là (parent, children)/ (children, parent, children)
        //* Return độ dài dài nhất của các dãy hơn kém nhau 1
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is in the range [1, 3  10^4].
        //-3  10^4 <= Node.val <= 3  10^4
        //+ Số node khá lớn.
        //
        //- Brainstorm
        //- Ta có thể xét duy nhất 1 case là (children, parent, children)
        //- Ta sẽ cần traverse như thế nào?
        //Ex:
        //          3
        //       /     \
        //     2        4
        //  /   \
        //4       1
        //+ rs=4
        //+ Thứ tự  1 -> 2 -> 3 -> 4
        //+ result = left + right (increase/decrease)
        //--> Ta chỉ cần duyệt 2 lần increase hoặc decrease tại mỗi node
        //- Mỗi node sẽ được cache lại:
        //+ dp[node]= Max(left, right)
        //==> parent sẽ tính theo dp[node] đó
        //+ rs=Math.max(rs, left+right)
        //- Increase và decrease nên được tính tách nhau ra.
        //==> Tư duy này sai :
        //Ex: left, parent, right
        //parent --> left: Decrease
        //parent --> right: Increase
        // Ta sẽ còn nhiều cases nữa:
        //* Với các cases parent - children
        //Ex:
        //          1
        //            \
        //              2
        //            /   \
        //          1       3
        //==> current right (value=1)= max(left increase, right increase) +1
        //Ex:
        //          1 (increase=true)
        //            \
        //              2
        //            /   \
        //          3      3
        //- Nếu tính dựa trên value = 1
        //==> current right (increase) (value=1)= max(left increase, right increase) +1
        //- Nếu tính dựa trên value=2
        //Ex:
        //          1 (increase=true)
        //            \
        //              2 (increase=true)
        //            /   \
        //          1      3
        //        /
        //      0
        //- Như ta thấy thì với value =2 thì ta cần phải xét thêm trường hợp decrease nữa.
        //
        //Ex:
        //                  3
        //                /
        //              2
        //            /   \
        //          1       1
        //==> current left (decrease) (value=3)= max(left decrease, right decrease) +1
        //
        //* Với các cases (children - parent - children)
        //
        //Ex:
        //          4
        //       /     \
        //     3        5
        //            /
        //          6
        //+ 3 - 4 - 5 - 6
        //+ current value=4:
        //  + value = left decrease + right increase + 1
        //Ex:
        //          2
        //       /    \
        //     1       3
        //Ex:
        //          2
        //       /    \
        //     3       1
        //+ 3 - 2 - 1
        //+ current value=2:
        //  + value = left increase + right decrease + 1
        //  + value = left decrease + right increase + 1
        //* Nếu tính tại 1 node ta có các cases như sau:
        //- (increase + decrease+1) / (decrease + increase + 1)
        //- increase / decrease được tính bằng max(increase, decrease) của sub-nodes
        //
        //- Làm 1 cách đơn giản ta sẽ lưu lại max (increase/ decrease) tại left và right tại mỗi nodes.
        //--> Ta sẽ chọn làm cách này trước
        //
        //              4
        //           /     \
        //         3        5 (increase, decrease)
        //       /   \     /  \
        //     2      4  4     6
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(log(n) + n)
        //
        //#Reference:
        //114. Flatten Binary Tree to Linked List
        //1161. Maximum Level Sum of a Binary Tree
        //430. Flatten a Multilevel Doubly Linked List
    }
}
