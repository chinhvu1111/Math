package E1_Graph.E1_BFS;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class E4_MaximumLevelSumOfABinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int maxLevelSum(TreeNode root) {
        if(root==null){
            return 0;
        }
        Deque<TreeNode> nodes=new LinkedList<>();
        nodes.add(root);
        int max=root.val;
        int level=1;
        int currentLevel=1;

        while(!nodes.isEmpty()){
            Deque<TreeNode> nextNodes=new LinkedList<>();
            int currentSum=0;
            currentLevel++;

            while(!nodes.isEmpty()){
                TreeNode currentNode=nodes.poll();

                if(currentNode.left!=null){
                    nextNodes.add(currentNode.left);
                    currentSum+=currentNode.left.val;
                }
                if(currentNode.right!=null){
                    nextNodes.add(currentNode.right);
                    currentSum+=currentNode.right.val;
                }
            }
            if(!nextNodes.isEmpty()&&max<currentSum){
                level=currentLevel;
                max=currentSum;
            }
            // System.out.printf("%s, %s\n", currentSum, nextNodes);
            nodes=nextNodes;
        }
        return level;
    }

    public static int maxLevelSumOptimization(TreeNode root) {
        if(root==null){
            return 0;
        }
        Deque<TreeNode> nodes=new LinkedList<>();
        nodes.add(root);
        int max=root.val;
        int level=1;
        int currentLevel=1;

        while(!nodes.isEmpty()){
            int currentSum=0;
            currentLevel++;
            int size=nodes.size();

            // System.out.printf("Init: %s, %s\n", nodes, nodes.size());
            for(int i=0;i<size;i++){
                TreeNode currentNode=nodes.removeFirst();
                // System.out.printf("%s, ", currentNode.val);

                if(currentNode.left!=null){
                    nodes.addLast(currentNode.left);
                    currentSum+=currentNode.left.val;
                }
                if(currentNode.right!=null){
                    nodes.addLast(currentNode.right);
                    currentSum+=currentNode.right.val;
                }
            }
            // System.out.printf("\n");
            // System.out.printf("%s, %s, %s\n", currentLevel, nodes, currentSum);
            if(!nodes.isEmpty()&&max<currentSum){
                level=currentLevel;
                max=currentSum;
            }
        }
        return level;
    }

    public static void main(String[] args) {
        //**Requirement
        //- Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
        //* Return the smallest level x such that the sum of all the values of nodes at level x is maximal.
        //
        //**Idea
        //1.
        //1.0,
        //- Constraint
        //
        //- Brainstorm
        //
        //1.1, Optimization
        //- Bài này không cần tạo temp list bên trong cũng vẫn đúng
        //- Vì mỗi layer ta sẽ add thêm các nodes vào last không liên quan đến các điểm poll ra từ first
        //** NOTE: Với dạng bài này thay vì:
        //+ Tạo (local temp list) + reassign
        //+ Ta sẽ (for + poll) với số lần bằng size ban đầu của queue
        //- Nếu trong 1 loop size của List thay đổi --> Không nên dùng for(i++, i<list.size();i++)
        //==> Vì list.size() sẽ được thực hiện mỗi lần loop ==> Nên nó sẽ chạy uncontrol
        //
        //1.2, Complexity
        //+ K is the maximum number per each layer of the tree
        //+ n is the number of nodes of the tree
        //- Space : O(k)
        //- Time : O(n)
        //#Reference:
        //Cousins in Binary Tree II
        //2583. Kth Largest Sum in a Binary Tree
        System.out.println(maxLevelSum(null));
        System.out.println(maxLevelSumOptimization(null));
    }
}
