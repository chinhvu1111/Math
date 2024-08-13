package E1_daily;

import java.util.*;

public class E54_NumberOfGoodLeafNodesPairs {

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

    public static void solution(TreeNode node, List<TreeNode> leafNodes, int height){
        if(node==null){
            return;
        }
        if(node.left==null&&node.right==null){
            leafNodes.add(node);
        }else{
            solution(node.left, leafNodes, height+1);
            solution(node.right, leafNodes, height+1);
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode node, TreeNode startNode, TreeNode destNode){
        if(node==null||node==startNode||node==destNode){
            return node;
        }
        TreeNode left=lowestCommonAncestor(node.left, startNode, destNode);
        TreeNode right=lowestCommonAncestor(node.right, startNode, destNode);
        if(left!=null&&right!=null){
            return node;
        }
        return left!=null?left:right;
    }

    public static int getDistance(TreeNode destNode, TreeNode node, int height, int dist){
        if(node==null||dist<height){
            return -1;
        }
        if(node==destNode){
            return height;
        }
        int curHeight=getDistance(destNode, node.left, height+1, dist);
        if(curHeight==-1){
            curHeight=getDistance(destNode, node.right, height+1, dist);
        }
        return curHeight;
    }

    public static int countPairsTimeout(TreeNode root, int distance) {
        List<TreeNode> leafNodes=new ArrayList<>();
        solution(root, leafNodes, 0);
        //-3,-2,0,1,4,5,10,15
        //distance=11
        int m=leafNodes.size();
        int rs=0;

        for(int i=0;i<m;i++){
            for(int j=i+1;j<m;j++){
                TreeNode node1=leafNodes.get(i);
                TreeNode node2=leafNodes.get(j);
                TreeNode lowestCommonAncestor=lowestCommonAncestor(root, node1, node2);
                int h1=getDistance(node1, lowestCommonAncestor, 0, distance);
                int h2=getDistance(node2, lowestCommonAncestor, 0, distance-h1);

                if(h1==-1||h2==-1){
                    continue;
                }
//                System.out.printf("Node1: %s, node2: %s, lowestCommonAncestor: %s, h1: %s, h2: %s\n",
//                        node1.val, node2.val, lowestCommonAncestor.val, h1, h2);
                if(h1+h2<=distance){
//                    System.out.printf("Node1: %s, node2: %s, lowestCommonAncestor: %s, h1: %s, h2: %s\n",
//                            node1.val, node2.val, lowestCommonAncestor.val, h1, h2);
                    rs++;
                }
            }
        }
        return rs;
    }

    //Space: O(h)
    //Time: (n*100)
    public static int[] solution(TreeNode node, int distance){
        int[] curCount = new int[12];

        if(node==null){
            return curCount;
        }else if(node.left==null&&node.right==null){
            curCount[0]=1;
            return curCount;
        }
        int[] left = solution(node.left, distance);
        int[] right = solution(node.right, distance);

        for(int i=0;i<=10;i++){
            curCount[i+1]=left[i]+right[i];
        }
        curCount[11]=left[11]+right[11];

        for(int i=0;i<=distance;i++){
            for(int j=0;j<=distance;j++){
                if (i+j+2<=distance) {
                    curCount[11]+=left[i]*right[j];
                }
            }
        }
        return curCount;
    }

    public static int countPairs(TreeNode root, int distance) {
        return solution(root, distance)[11];
    }

    public static int countPairsBFSConversion(TreeNode root, int distance) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        Set<TreeNode> leafNodes = new HashSet<>();

        traverseTree(root, null, graph, leafNodes);

        int ans = 0;

        for (TreeNode leaf : leafNodes) {
            Queue<TreeNode> bfsQueue = new LinkedList<>();
            Set<TreeNode> seen = new HashSet<>();
            bfsQueue.add(leaf);
            seen.add(leaf);
            // Go through all nodes that are within the given distance of
            // the current leaf node
            for (int i = 0; i <= distance; i++) {
                // Clear all nodes in the queue (distance i away from leaf node)
                // Add the nodes' neighbors (distance i+1 away from leaf node)
                int size = bfsQueue.size();
                for (int j = 0; j < size; j++) {
                    // If current node is a new leaf node, add the found pair to our count
                    TreeNode currNode = bfsQueue.remove();
                    if (leafNodes.contains(currNode) && currNode != leaf) {
                        ans++;
                    }
                    if (graph.containsKey(currNode)) {
                        for (TreeNode neighbor : graph.get(currNode)) {
                            if (!seen.contains(neighbor)) {
                                bfsQueue.add(neighbor);
                                seen.add(neighbor);
                            }
                        }
                    }
                }
            }
        }
        return ans / 2;
    }

    private static void traverseTree(
            TreeNode currNode,
            TreeNode prevNode,
            Map<TreeNode, List<TreeNode>> graph,
            Set<TreeNode> leafNodes
    ) {
        if (currNode == null) {
            return;
        }
        if (currNode.left == null && currNode.right == null) {
            leafNodes.add(currNode);
        }
        if (prevNode != null) {
            graph.computeIfAbsent(prevNode, k -> new ArrayList<TreeNode>());
            graph.get(prevNode).add(currNode);
            graph.computeIfAbsent(currNode, k -> new ArrayList<TreeNode>());
            graph.get(currNode).add(prevNode);
        }
        traverseTree(currNode.left, currNode, graph, leafNodes);
        traverseTree(currNode.right, currNode, graph, leafNodes);
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (the root of a binary tree) and (an integer distance).
        //- (A pair of two different leaf nodes) of a binary tree is said to be good
        // if the length of the shortest path between them is (less than or equal to distance).
        //* Return (the number of good leaf node pairs) in the tree.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //The number of nodes in the tree is in the range [1, 2^10].
        //1 <= Node.val <= 100
        //1 <= distance <= 10
        //
        //- Brainstorm
        //- Distance between (two leaf nodes) <= distance
        //  + Count the number of pairs
        //- Mình chỉ cần traverse all tree để tìm all of heigh của all of leaf nodes
        //  + Sau đó traverse đôi một để tìm ra danh sách các pairs phù hợp
        //
        //- Nếu làm cách mà list all các leaf nodes
        //  + Sau đó loop đôi một tìm LCA và tìm height của cả 2 nodes:
        //      + Kế cả tối ưu bằng việc cut branch với:
        //      max left distance=x
        //      max right distance= distance - x
        //      ==> Timeout
        //          1
        //        /   \
        //      2      9
        //    /  \
        //   5    7
        //- Mỗi node sẽ check thông tin left/ right, return lại:
        //  + Count số lượng các nodes có distance <= 10:
        //      + return 1 array left/right[10] --> left[i]= count số lượng leaf nodes cho đến (current node) mà
        //          + height=i (0 -> 10)
        //  + Càng go up lên thì cái array này sẽ shift sang phải với (height tăng dần)
        //- Tại current node có :
        //  + left, right[]
        //  ==> Ta tính tổng số leaf nodes có (height=i)
        //      + curCount[i+1] = left[i]+right[i]
        //- Tại mỗi current node ta cần tìm số lượng pair nên ta sẽ loop cho (left/ right) all cases:
        //  + Ta sẽ lưu curCount[11] ==> Số lượng pair
        //      + Nó sẽ bằng left[11]+ right[11] ==> Cộng dồn thôi
        //  + if(i+j+2<=distance):
        //      + +2 vì mỗi left, right đều go up 1 unit
        //      + curCount[11] = left[i]*right[j]
        //  => return curCount
        //==> rs= curCount[11]
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(h)
        //- Time: O(n*100)
        //
        //2. Graph conversion + BFS
        //- Convert binary tree to BFS
        //- Lấy danh sách leaf nodes ra
        //  + Traverse trên mỗi leaf nodes
        //      + Trong đó mỗi for ta traverse theo layer
        //          + 0 <= layer <= distance + find leaf nodes tương ứng là được.
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(N)
        //- Time: O(N^2)
        //
        Integer[] root = {1,2,3,4,5,6,7};
        int distance = 3;
//        Integer[] root = {11,99,88,77,null,null,66,55,null,null,44,33,null,null,22};
//        int distance = 4;
//        Integer[] root = {1,2,3,null,4};
//        int distance = 3;
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
        System.out.println(countPairsTimeout(rootNode, distance));
        System.out.println(countPairs(rootNode, distance));
        System.out.println(countPairsBFSConversion(rootNode, distance));
        //
        //#Reference:
        //652. Find Duplicate Subtrees
        //501. Find Mode in Binary Search Tree
        //386. Lexicographical Numbers
    }
}
