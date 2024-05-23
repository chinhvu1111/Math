package E1_Tree;

import java.util.Stack;

public class E37_DeleteLeavesWithAGivenValue {

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

        @Override
        public String toString() {
            return String.valueOf(this.val);
        }
    }

    public static TreeNode solution(TreeNode node, int target){
        if(node.left!=null){
            node.left=solution(node.left, target);
        }
        if (node.right!=null) {
            node.right=solution(node.right, target);
        }
        if(node.right==null&&node.left==null&&node.val==target){
            return null;
        }
        return node;
    }

    public static TreeNode removeLeafNodes(TreeNode root, int target) {
        return solution(root, target);
    }

    public static TreeNode removeLeafNodesPostOrder(TreeNode root, int target) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node=root;

        while(node!=null){
            while(node!=null){
                stack.add(node);
                node=node.left;
            }
            if(!stack.isEmpty()){
                TreeNode curNode = stack.pop();

                if(curNode.val==target&&curNode.left==null&&curNode.right==null){
                    if(stack.isEmpty()){
                        return null;
                    }
                    TreeNode parentNode = stack.peek();
                    if(parentNode.left==curNode){
                        parentNode.left=null;
                    }
                    if(parentNode.right==curNode){
                        parentNode.right=null;
                    }
                }
                while (node==null&&!stack.isEmpty()){
                    TreeNode temp = stack.pop();
                    if(temp.val==target&&temp.right==null){
                        if(stack.isEmpty()){
                            return null;
                        }else{
                            if(stack.peek().left==temp){
                                stack.peek().left=null;
                            }
                            if(stack.peek().right==temp){
                                stack.peek().right=null;
                            }
                        }
                    }
                    node=temp.right;
                }
            }
//            System.out.println(stack);
        }
        return root;
    }

    public static void println(TreeNode node){
        if(node==null){
            return;
        }
        System.out.println(node.val);
        println(node.left);
        println(node.right);
    }

    public static void main(String[] args) {
        //**Requirement
        //- Given (a binary tree root) and an integer target, (delete all the leaf nodes) with (value target).
        //* Note that once (you delete a leaf node) with (value target),
        //- if (its parent node) becomes (a leaf node) and has (the value target),
        // it should also be (deleted) (you need to continue doing that until you cannot).
        //Ex:
        //Input: root = [1,2,3,2,null,2,4], target = 2
        //Output: [1,null,3,null,4]
        //Explanation: Leaf nodes in green with value (target = 2) are removed (Picture in left).
        //After removing, new nodes become leaf nodes with value (target = 2) (Picture in center).
        //
        //**Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is in the range [1, 3000].
        //1 <= Node.val, target <= 1000
        //
        //- Brainstorm
        //       1
        //     /   \
        //    2     3
        //  /     /   \
        // 2     2     4
        //- Ta sẽ delete từ ngoài vào
        //        1
        //       /
        //      2
        //     /
        //    2
        //  /
        //null
        //
        // 2.left = solution()
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- N is the number of node
        //- Space : O(H)
        //- Time : O(N)
        //
        //2.
        //2.0, Post order traversal
        //- Idea
        //          1
        //        /  \
        //      2     3
        //    /  \      \
        //   4    5      6
        //Path: 1 - 4 - 5 - 2 - 3 - 6
        //- Nếu đi theo cái này thì --> Delete được
        //  + Rõ ràng là ta đi từ leaf node.
        //- Dùng stack để traverse ntn?
        //- Ta sẽ đi với 1 ví dụ deeper:
        //Ex:
        //          1
        //        /  \
        //      2     3
        //    /  \      \
        //   4    5      6
        //      /  \
        //    7     8
        //
        //
        //#Reference:
        //419. Battleships in a Board
        //2583. Kth Largest Sum in a Binary Tree
        //1902. Depth of BST Given Insertion Order
//        TreeNode root=new TreeNode(1);
//        TreeNode node1=new TreeNode(2);
//        TreeNode node2=new TreeNode(3);
//        TreeNode node3=new TreeNode(4);
//        TreeNode node4=new TreeNode(5);
//        TreeNode node5=new TreeNode(6);
//        TreeNode node6=new TreeNode(7);
//        TreeNode node7=new TreeNode(8);
        //
//        root.left=node1;
//        root.right=node2;
//        node1.left=node3;
//        node1.right=node4;
//        node2.right=node5;
//        node4.left=node6;
//        node4.right=node7;
        //
        //       1
        //     /   \
        //   2      2
        // /      /   \
        //2     2      4
        //
        TreeNode root=new TreeNode(1);
        TreeNode node1=new TreeNode(2);
        TreeNode node2=new TreeNode(3);
        TreeNode node3=new TreeNode(2);
        TreeNode node4=new TreeNode(2);
        TreeNode node5=new TreeNode(4);
        //
        root.left=node1;
        root.right=node2;
        node1.left=node3;
        node2.left=node4;
        node2.right=node5;
        System.out.println(removeLeafNodesPostOrder(root, 2));
        println(root);
    }
}
