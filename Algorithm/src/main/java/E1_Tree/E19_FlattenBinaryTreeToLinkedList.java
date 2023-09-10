package E1_Tree;

import java.util.ArrayList;
import java.util.List;

public class E19_FlattenBinaryTreeToLinkedList {

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

    public static List<TreeNode> list;

    public static void dfs(TreeNode node){
        if(node==null){
            return;
        }
        list.add(node);
        dfs(node.left);
        dfs(node.right);
    }

    public static TreeNode currentNode;

    public void flatten(TreeNode root) {
        dfsOptimization(root);
    }

    public static TreeNode currentNodeOptimization;

    public static void dfsOptimization(TreeNode node){
        if(node==null){
            return;
        }
        TreeNode left=node.left;
        TreeNode right=node.right;
        if(currentNodeOptimization==null){
            currentNodeOptimization=node;
        }else{
            // System.out.printf("Current node: %s, %s, left: %s\n", currentNode.val, node.val, node.left);
            currentNodeOptimization.right=node;
            currentNodeOptimization.left=null;
            currentNodeOptimization=node;
        }
        dfsOptimization(left);
        dfsOptimization(right);
    }

    public void flattenOptimization(TreeNode root) {
        dfsOptimization(root);
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
        //The number of nodes in the tree is in the range [0, 2000].
        //-100 <= Node.val <= 100
        //
        //- Brainstorm
        //- Dùng list để lưu all nodes
        //- Sau đó traverse lại all list để nối các node với nhau
        //
        //1.1, Optimization
        //- Ta có thể tối ưu bằng cách chỉ lưu 1 node cuối cùng thay vì cả list
        //+ Để phục vụ việc nối node tiếp theo.
        //Ex:
        //              1
        //            /   \
        //          2      5
        //        /   \     \
        //      3      4     6
        //1.right -> 2.right=3
        //* Chú ý:
        //- Khi gán
        //+ currentNode.right=node
        //+ currentNode=node ==> Vì currentNode là static variable nên có khả năng bị thay đổi trong tương lai
        //--> Nên cần cache lại (node.left, node.right) [Ngay từ đầu] --> pass method(left/right) sau
        //
        //1.2, Complexity
        //- Space : O(n) --> O(1 + log(n)(stack))
        //- Time : O(n)
        //
        //#Reference:
        //430. Flatten a Multilevel Doubly Linked List
        //1660. Correct a Binary Tree
    }
}
