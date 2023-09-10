package E1_Tree;

import java.util.*;

public class E20_CorrectABinaryTree {

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

    public static void solution(TreeNode node){
        Queue<TreeNode> nodes=new LinkedList<>();
        nodes.add(node);
        HashMap<TreeNode, TreeNode> parentNode=new HashMap<>();

        //Time : O(n)
        while(!nodes.isEmpty()){
            Queue<TreeNode> nextNodes=new LinkedList<>();
            //Space : O(n)
            HashMap<TreeNode, Integer> nodeToPos=new HashMap<>();
            List<TreeNode> listCurrentNodes=new ArrayList<>();
            int index=0;

            while(!nodes.isEmpty()){
                TreeNode currentNode=nodes.poll();
                 if(currentNode.left!=null){
                     nodeToPos.put(currentNode.left, index++);
                     parentNode.put(currentNode.left, currentNode);
                     listCurrentNodes.add(currentNode.left);
                 }
                 if(currentNode.right!=null){
                     nodeToPos.put(currentNode.right, index++);
                     parentNode.put(currentNode.right, currentNode);
                     listCurrentNodes.add(currentNode.right);
                 }
//                nodeToPos.put(currentNode, index++);
            }
            for(int i=0;i<listCurrentNodes.size();i++){
                TreeNode curNode=listCurrentNodes.get(i);
                // System.out.printf("%s, ", curNode.val);

                if(parentNode.containsKey(curNode.right)&&nodeToPos.get(curNode.right)>i){
                    TreeNode parent=parentNode.get(curNode);
                    if(Objects.equals(parent.left, curNode)){
                        parent.left=null;
                    }
                    if(Objects.equals(parent.right, curNode)){
                        parent.right=null;
                    }
                    System.out.printf("Deleted node: %s\n", curNode.val);
                    return;
                }
                nextNodes.add(curNode);
            }
            nodes=nextNodes;
            System.out.printf("\n");
            System.out.printf("The number of currents nodes: %s, the number of next valid nodes: %s\n", nextNodes.size(), nodes.size());
        }
    }

    public TreeNode correctBinaryTree(TreeNode root) {
        solution(root);
        return root;
    }

    public static HashSet<Integer> visited;

    public static TreeNode solutionDFS(TreeNode root){
        if(root==null){
            return null;
        }
        //              1
        //            /   \
        //          2 ---> 3
        if(root.right!=null&&visited.contains(root.right.val)){
            return null;
        }
        visited.add(root.val);
        root.right=solutionDFS(root.right);
        root.left=solutionDFS(root.left);
        return root;
    }

    public TreeNode correctBinaryTreeDFS(TreeNode root) {
        visited=new HashSet<>();
        return solutionDFS(root);
    }

    public static void main(String[] args) {
        // Requirement
        //- Given root of binary tree
        //- We have a node point to the right of node at the same level + (right) of the invalid node
        //* Remove incorrect node and all of subnode of this node
        //- return root node
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //The number of nodes in the tree is in the range [3, 10^4].
        //-10^9 <= Node.val <= 10^9
        //All Node.val are unique.
        //fromNode != toNode
        //fromNode and toNode will exist in the tree and will be on the same depth.
        //toNode is to the right of fromNode.
        //fromNode.right is null in the initial tree from the test data.
        //
        //- Brainstorm
        //- Bài này dùng bfs
        //- Traverse theo multiple nodes at the same level
        //+ Ở mỗi level ta sẽ lưu hash table để check xem nó có connect với nhau hay không là được.
        //+ Dùng index để thứ tự node từ left to right
        //+ Nếu node.right có trong map + index của phần tử đó index>i ==> Xoá node hiện tại đi
        //+ Cần lưu lại parent node nữa để có thể xoá được node.
        //
        //1.1, Optimization
        //- Ta có thể dùng DFS để handle
        //- Ta sẽ recursion:
        //+ solution(root.right) --> Vì right sẽ được ưu tiên trước
        //+ solution(root.left)
        //+ if(visited.contains(right)&&visited.contains(root.right.val)) return null
        //  + cut đi bằng cách
        //      + root.right=solution(root.right)
        //      + root.left=solution(root.left)
        //
        //1.2, Complexity
        //+ N is the number of node
        //- Space: O(n)
        //- Time : O(n)

        TreeNode root=new TreeNode(1);
        TreeNode node=new TreeNode(2);
        root.left=node;
        TreeNode node1=new TreeNode(3);
        root.right=node1;
        node.right=node1;
        solution(root);
    }
}
