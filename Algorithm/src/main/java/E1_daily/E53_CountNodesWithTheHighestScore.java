package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class E53_CountNodesWithTheHighestScore {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val=val;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    public static int countHighestScoreNodesNonOptimization(int[] parents) {
        int n=parents.length;
        //Space: O(n)
        HashMap<Integer, TreeNode> tree=new HashMap<>();
        TreeNode root=new TreeNode(0);
        tree.put(0, root);

        //Time: O(n)
        for(int i=1;i<n;i++){
            TreeNode parentNode = tree.getOrDefault(parents[i], new TreeNode(parents[i]));
            TreeNode childNode = tree.getOrDefault(i, new TreeNode(i));
            if(parentNode.left==null){
                parentNode.left=childNode;
            }else{
                parentNode.right=childNode;
            }
            tree.put(parents[i], parentNode);
            tree.put(i, childNode);
        }
//        println(root);
//        System.out.println();
        HashMap<TreeNode, Integer> nodeToNumNode=new HashMap<>();
        //Time: O(n)
        countNumberNode(root, nodeToNumNode);
//        System.out.println(nodeToNumNode);
        long rs=0;
        long countRoot=nodeToNumNode.get(root);
        //Space: O(n)
        HashMap<Long, Integer> mapCount=new HashMap<>();

        //Time: O(n)
        for(int i=0;i<n;i++){
            TreeNode curNode = tree.get(i);
            TreeNode parentNode = tree.get(parents[i]);
            long curCount=nodeToNumNode.get(curNode);
            long leftCount=nodeToNumNode.getOrDefault(curNode.left, 1);
            long rightCount=nodeToNumNode.getOrDefault(curNode.right, 1);
            long countParent=1;
//            System.out.printf("Current root: %s, Left count: %s, right count: %s, current count: %s\n", i, leftCount, rightCount, curCount);
            if(nodeToNumNode.containsKey(parentNode)){
//                System.out.printf("Before: %s, count parent: %s\n",countParent, nodeToNumNode.get(parentNode));
                countParent=countRoot-curCount;
//                System.out.printf("After: %s\n",countParent);
            }
            rs = Math.max(rs, leftCount*rightCount*countParent);
            mapCount.put(leftCount*rightCount*countParent, mapCount.getOrDefault(leftCount*rightCount*countParent, 0)+1);
        }
        return mapCount.get(rs);
    }

    public static void println(TreeNode node){
        if(node==null){
            return;
        }
        System.out.println(node.val);
        println(node.left);
        println(node.right);
    }

    public static int countNumberNode(TreeNode node, HashMap<TreeNode, Integer> nodeToNumNode){
        if(node==null){
            return 0;
        }
        int curCount=countNumberNode(node.left, nodeToNumNode);
        curCount+=countNumberNode(node.right, nodeToNumNode);
        nodeToNumNode.put(node, curCount+1);
        return curCount+1;
    }

    public static long dfs(List<List<Integer>> graph, long[] numNodes, int node){
        long prod=1, sum=1;

        for(int nextNode: graph.get(node)){
            long count=dfs(graph, numNodes, nextNode);
            prod*=count;
            sum+=count;
        }
        numNodes[node]=prod*(Math.max(1, graph.size()-sum));
        return sum;
    }

    public static int countHighestScoreNodes(int[] parents) {
        int n=parents.length;
        List<List<Integer>> graph=new ArrayList<>();

        for(int i=0;i<n;i++){
            graph.add(new ArrayList<>());
        }
        for(int i=1;i<n;i++){
            graph.get(parents[i]).add(i);
        }
        long[] numNodes=new long[n];
        dfs(graph, numNodes, 0);
        long maxVal= Arrays.stream(numNodes).max().getAsLong();
        int count=0;

        for(int i=0;i<n;i++){
            if(numNodes[i]==maxVal){
                count++;
            }
        }
        //Space: O(n)
        return count;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is (a binary tree) rooted at (0 consisting of n nodes).
        //- The nodes are labeled from (0 to n - 1).
        // You are given a 0-indexed (integer array parents) representing the tree,
        //- where parents[i] is (the parent of node i).
        //  + Since node 0 is the root, (parents[0] == -1).
        //- Each node has a score.
        //- To find the score of a node, consider if the node and the edges connected to it were removed.
        //- The tree would become (one or more) non-empty subtrees.
        //- The size of a subtree is the number of the nodes in it.
        //- The score of the node is the product of the sizes of all those subtrees.
        //* Return the number of nodes that have (the highest score).
        //* Return max score bằng cách remove từng node:
        //  + Thì score lấy được bằng product of all of the subtree nodes.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //n == parents.length
        //2 <= n <= 10^5
        //parents[0] == -1
        //0 <= parents[i] <= (n - 1) for i != 0
        //parents represents a valid binary tree.
        //
        //
        //- Brainstorm
        //- Build binary tree từ parent
        //  + Không rõ left hay right
        //      + Để left hay right đều được.
        //- Tính số lượng nodes của mỗi subtree với mỗi node được coi là root.
        //- Ta cần phải * số bù của root:
        //  + Nếu có parent thì ta cần * với phần bù (count root) - current count
        //- Count root == n vì có n nodes.
        //
        //1.1, Optimization
        //- Không cần build tree
        //- Dùng DFS trực tiếp
        //- DFS ==> Và tính luôn result.
        //* Code ntn thì sẽ faster.
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        int[] parents = {-1,2,0,2,0};
        //      0
        //    /   \
        //   2     4
        // /   \
        //1     3
        //
        //#Reference:
        //1339. Maximum Product of Splitted Binary Tree
        System.out.println(countHighestScoreNodesNonOptimization(parents));
        System.out.println(countHighestScoreNodes(parents));
    }
}
