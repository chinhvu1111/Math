package E1_daily;

import java.util.*;

public class E52_DeleteNodesAndReturnForest {

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

    public static List<TreeNode> roots;

    public static void solution(TreeNode node, TreeNode prevNode, boolean isLeft, HashSet<Integer> deletedNodes){
        if(node==null){
            return;
        }
        if(deletedNodes.contains(node.val)){
            if(node.left!=null){
                if(!deletedNodes.contains(node.left.val)){
                    roots.add(node.left);
                }
                solution(node.left, node, true, deletedNodes);
            }
            if(node.right!=null){
                if(!deletedNodes.contains(node.right.val)){
                    roots.add(node.right);
                }
                solution(node.right, node, false, deletedNodes);
            }
            if(isLeft&&prevNode!=null){
                prevNode.left=null;
            }
            if(!isLeft&&prevNode!=null){
                prevNode.right=null;
            }
        }else{
            solution(node.left, node, true, deletedNodes);
            solution(node.right, node, false, deletedNodes);
        }
    }

    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        HashSet<Integer> deletedNodes=new HashSet<>();
        roots=new ArrayList<>();

        for(int node: to_delete){
            deletedNodes.add(node);
        }
        if(root!=null&&!deletedNodes.contains(root.val)){
            roots.add(root);
        }
        solution(root, null, true, deletedNodes);
        return roots;
    }

    public static void println(TreeNode node){
        if(node==null){
            return;
        }
        System.out.printf("%s, ",node.val);
        println(node.left);
        println(node.right);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the root of a binary tree, (each node) in the tree has (a distinct value).
        //- After deleting all nodes with a value in (to_delete), we are left with (a forest) (a disjoint union of trees).
        //* Return (the roots) of the trees (in the remaining forest). You may return the result in any order.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //+ The number of nodes in the given tree is at most 1000.
        //+ Each node has a distinct value between 1 and 1000.
        //+ to_delete.length <= 1000
        //+ to_delete contains distinct values between 1 and 1000.
        //
        //- Brainstorm
        //- Delete node nào
        //  + Add các left, right của node đó vào root là được.
        //
        //- Special cases:
        //        1
        //      /
        //    2
        //  /  \
        //4     3
        //+ to_delete = [2, 3]
        //
        //-
        //      1
        //    /  \
        //  2     3
        //         \
        //          4
        //+ to_delete = [2,1]
        //+ Case này chỉ việc từ root ta chỉ được phép traverse 1 lần:
        //  + solution(root, false) ==> nếu chạy cả solution(root, true)
        //  ==> Thì ta sẽ bị duplicated roots.
        //
        //-     1
        //        \
        //         2
        //       /
        //      3
        //     /
        //    4
        //   /
        //  5
        //+ to_delete = [5, 3]
        //+ Thì việc add(4) và roots là chưa đủ ==> Ta cần phải đi deeper để delete node(5)
        //  ==> Cả 2 delete/ không delete(current node) thì ta cũng vẫn cần traverse.
        //  + Nếu left!=null
        //  + Nếu right!=null
        //
        //1.1, Optimization
        //1.2, Complexity
        //- n is the number of distinct number in the tree.
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //2049. Count Nodes With the Highest Score
        //
        Integer[] root = {1,2,3,null,null,null,4};
        TreeNode rootNode=new TreeNode(root[0]);
        int n = root.length;
        Deque<TreeNode> listNode=new LinkedList<>();
        listNode.add(rootNode);
        int index=0;

        while (!listNode.isEmpty()){
            TreeNode firstNode=listNode.removeFirst();
            int indexLeft=index*2+1;
            int indexRight=index*2+2;

            if(indexLeft<n&&root[indexLeft]!=null){
                firstNode.left=new TreeNode(root[indexLeft]);
                listNode.addLast(firstNode.left);
            }
            if(indexRight<n&&root[indexRight]!=null){
                firstNode.right=new TreeNode(root[indexRight]);
                listNode.addLast(firstNode.right);
            }
            index++;
        }
        int[] to_delete={2, 1};
        delNodes(rootNode, to_delete);
        for (TreeNode node: roots){
            println(node);
            System.out.println();
        }
    }
}
