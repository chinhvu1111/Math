package E1_Tree;

import java.util.ArrayList;
import java.util.List;

public class E24_FindLeavesOfBinaryTree {

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

    public static int solution(TreeNode node, List<List<Integer>> rs){
        if(node==null){
            return -1;
        }
        int indexLeft=solution(node.left, rs);
        int indexRight=solution(node.right, rs);
        int curIndex=Math.max(indexLeft, indexRight)+1;
        List<Integer> curNode=null;

        if(rs.size()<curIndex+1){
            curNode=new ArrayList<>();
            rs.add(curNode);
        }else{
            curNode=rs.get(curIndex);
        }
        curNode.add(node.val);
        return curIndex;
    }

    public static List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        solution(root, rs);
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- Given the root of a binary tree, collect a tree's nodes as if you were doing this:
        //+ Collect all the leaf nodes.
        //+ Remove all the leaf nodes.
        //+ Repeat until the tree is empty.
        //Ex:
        //              1
        //          /      \
        //        2         3
        //      /   \
        //    4      5
        //=> remove [4,5,3]
        //              1
        //            /
        //          2
        //=> remove [2]
        //              1
        //=> remove [1]
        //
        //rs=[[4,5,3],[2],[1]]
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //+ The number of nodes in the tree is in the range [1, 100].
        //+ -100 <= Node.val <= 100
        //
        //
        //- Brainstorm
        //- Cái này ta sẽ dùng phương pháp bottom-up
        //  + Số layer không đều
        //- Nói chung là algorithm là sẽ đi từ ngoài vào trong
        //  + Ta có thể làm theo (topological sort)
        //
        //- Không cần đùng topological sort
        //-> DFS là được
        //- Rs: list
        //- Index sẽ được gán từ leaf --> root
        //  + Size của rs chính là height của tree
        //- Nếu đứng từ
        //  + leaf --> list[0]
        //  + root --> list[height-1]
        //- Đến đâu nếu size của list nhỏ index của recursion method hơn cũ ==> ta tạo mới sub-list
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- N is the number of nodes
        //- H is the height of tree
        //- Space : O(H)
        //- Time : O(N)
    }
}
