package E1_Tree;

import java.util.*;

public class E12_SmallestSubtreeWithAllTheDeepestNodes {
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

    public static TreeNode subtreeWithAllDeepestIterative(TreeNode root) {
        //Constraint
        if(root==null){
            return null;
        }
        return solutionIterative(root);
    }

    public static TreeNode solutionIterative(TreeNode node){
        Queue<TreeNode> currentListNode=new LinkedList<>();
        HashMap<TreeNode, TreeNode> mapNodeParentNodes=new HashMap<>();
        currentListNode.add(node);
        List<TreeNode> preVTmpNode=new ArrayList<>();

        while (!currentListNode.isEmpty()){
            List<TreeNode> tmpNode=new ArrayList<>();
            int numNewNode=0;

            while (!currentListNode.isEmpty()){
                TreeNode currentNode=currentListNode.remove();
                if(currentNode.left!=null){
                    mapNodeParentNodes.put(currentNode.left, currentNode);
                    tmpNode.add(currentNode.left);
                    numNewNode++;
                }
                if(currentNode.right!=null){
                    mapNodeParentNodes.put(currentNode.right, currentNode);
                    tmpNode.add(currentNode.right);
                    numNewNode++;
                }
            }
            currentListNode.addAll(tmpNode);
            if(numNewNode==0){
                break;
            }
            preVTmpNode=tmpNode;
        }
//        println(preVTmpNode);
        if(preVTmpNode.isEmpty()){
            preVTmpNode.add(node);
        }
//        Deque<TreeNode> result=new LinkedList<>(preVTmpNode);
        HashSet<TreeNode> nextNodes = new HashSet<>(preVTmpNode);
        Deque<TreeNode> currentNodes = new LinkedList<>(preVTmpNode);

        while (nextNodes.size()!=1){
            nextNodes.clear();
            while (!currentNodes.isEmpty()){
                TreeNode currentNode=currentNodes.removeFirst();
                TreeNode parentNode=mapNodeParentNodes.get(currentNode);
                nextNodes.add(parentNode);
//                result.addFirst(parentNode);
            }
//            System.out.println(nextNodes.size());
            currentNodes.addAll(nextNodes);
        }
//        System.out.println(currentNodes.peek().val);
        return currentNodes.removeFirst();
    }

    public static int maxDepth;
    public static TreeNode rootSubTree=null;
    public static TreeNode subtreeWithAllDeepest(TreeNode root) {
        maxDepth=Integer.MIN_VALUE;
        //Constraint
        if(root==null){
            return null;
        }
        solution(root, 0);
        return rootSubTree;
    }

    public static int solution(TreeNode node, int level){
        if(node==null){
            return level;
        }

        int leftLevel=solution(node.left, level+1);
        int rightLevel=solution(node.right, level+1);
        int currentDepth=Math.max(rightLevel, leftLevel);
        maxDepth=Math.max(maxDepth, currentDepth);

        if(leftLevel==rightLevel&&maxDepth==leftLevel){
            rootSubTree=node;
        }
        return currentDepth;
    }

    public static void println(List<TreeNode> currentList){
        for (int i = 0; i < currentList.size(); i++) {
            System.out.printf("%s, ", currentList.get(i).val);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        //** Requirement:
        //- Building the tree contains all (the deepest nodes)
        //- Deepest nodes:
        //+ The node has the largest depth possible among any node in entire tree.
        //+ Subtree of a node is a tree consisting of that node + the set of all descendants of that node.
        //
        //+ Smallest subtree --> Min height.
        //
        //** Idea
        //
        //* Method-1
        //1.0, Idea
        //- Binary tree : non rule about value of each node.
        //- The number of nodes <=500
        //- Deepest nodes : List all of them
        //+ All Deepest leaf nodes --> Same level (Not related) (Not has any parent and child relationship)
        //- The problem is to find the node which nearest all deepest leaf nodes (Lấy ra các deepest node)
        //+ Traverse reverse level
        //==> Hash<Node, ParentNode> : 1 child node has only 1 parent node
        //
        //- Rest of solution related to queue.
        //VD:
        //0,1,3,null,2
        //              0
        //           /     \
        //         1        3
        //      /     \
        //    4         2
        //
//        TreeNode root=new TreeNode(0);
//        TreeNode node=new TreeNode(1);
//        TreeNode node1=new TreeNode(3);
//        TreeNode node2=new TreeNode(4);
//        TreeNode node3=new TreeNode(2);
//        root.left=node;
//        root.right=node1;
//        node.left=node2;
//        node.right=node3;
        //
        //1.1, Complexity
        //- Time complexity : O(n)
        //- Space complexity : O(n)
        //
        //* Method-1
        //2.
        //2.1, Idea
        //Post order và inorder traversal (Khác nhau duy nhất ở chỗ print(root.val)
        //+ Postorder print sau : recursive(left/right)
        //+ Inorder traversal : print giữa recursive(left) và recursive(right)
        //
        //- Use PostOrder traverse idea.
        //          1
        //       /     \
        //     2        3
        //   /   \
        // 4      5
        //+ Áp dụng tư duy của PostOrder traversal để dựa trên đó check depth của left và right sau recursive(left) và recursive(right)
        //- Subtree phải chứa hết các deepest node,
        //* Tính chất của binary Tree thì càng lên cao:
        //+ Các node sẽ càng là parent của nhiều parent của các nodes khác
        //+ PostOrder, Inorder đều run từ bottom --> up
        //- Nếu compare (the depth of left) == (the depth of  right) thì ta chỉ tìm được 1 root duy nhất nếu dùng postOrder traverse.
        //
        //- Bài toán yêu cầu 1 node root là root gần nhất của all deepest nodes trong Tree
        //--> Lên trên (up) nhiều hơn --> Nếu tìm được kết quả thì ta sẽ update mới ==> Có thể nó tìm được deepest node mới.
        //
        TreeNode root=new TreeNode(0);
        System.out.println(subtreeWithAllDeepest(root).val);
        //#Reference:
        //866. Prime Palindrome
        //336. Palindrome Pairs
        //291. Word Pattern II
        //815. Bus Routes
        //866. Prime Palindrome
        //1305. All Elements in Two Binary Search Trees
        //1319. Number of Operations to Make Network Connected
        //1072. Flip Columns For Maximum Number of Equal Rows
    }
}
