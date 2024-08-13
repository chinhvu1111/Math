package E1_daily;

import javafx.util.Pair;

import java.util.*;

public class E51_StepByStepDirectionsFromABinaryTreeNodeToAnother {

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

    public static String findShortestPath(TreeNode node, TreeNode prevNode, int destValue, HashMap<Integer, TreeNode> mapParent, String path){
        if(node==null){
            return null;
        }
        if(node.val==destValue){
            return path;
        }
        String curPath= findShortestPath(node.left, node, destValue, mapParent, path+"L");
        if(curPath==null){
            curPath= findShortestPath(node.right, node, destValue, mapParent, path+"R");
        }
        //a -left-> b
        //a -right-> c
        if(curPath==null&&mapParent.containsKey(node.val)){
            TreeNode curParent = mapParent.get(node.val);
            if(curParent!=prevNode){
                curPath= findShortestPath(curParent, node, destValue, mapParent, path+"U");
            }
        }
        return curPath;
    }

    public static String findShortestPathOptimization(TreeNode node, TreeNode prevNode, int destValue, HashMap<Integer, TreeNode> mapParent, StringBuilder path){
        if(node==null){
            return null;
        }
        if(node.val==destValue){
            return path.toString();
        }
        path.append("L");
        String curPath= findShortestPathOptimization(node.left, node, destValue, mapParent, path);
        path.deleteCharAt(path.length()-1);
        if(curPath==null){
            path.append("R");
            curPath= findShortestPathOptimization(node.right, node, destValue, mapParent, path);
            path.deleteCharAt(path.length()-1);
        }
        //a -left-> b
        //a -right-> c
        if(curPath==null&&mapParent.containsKey(node.val)){
            TreeNode curParent = mapParent.get(node.val);
            if(curParent!=prevNode){
                path.append("U");
                curPath= findShortestPathOptimization(curParent, node, destValue, mapParent, path);
                path.deleteCharAt(path.length()-1);
            }
        }
        return curPath;
    }

    public static String getDirections(TreeNode root, int startValue, int destValue) {
        //Space: O(n)
        HashMap<Integer, TreeNode> mapParent=new HashMap<>();
        Queue<TreeNode> nodes=new LinkedList<>();
        nodes.add(root);
        TreeNode startNode = null;

        //Time: O(n)
        while (!nodes.isEmpty()){
            TreeNode node=nodes.poll();
            if(node.val==startValue){
                startNode=node;
            }
            if(node.left!=null){
                nodes.add(node.left);
                mapParent.put(node.left.val, node);
            }
            if(node.right!=null){
                nodes.add(node.right);
                mapParent.put(node.right.val, node);
            }
        }
//        System.out.println(startNode.val);
        //Space: O(h+n)
        //Time: O(n)
        return findShortestPathOptimization(startNode, null, destValue, mapParent, new StringBuilder());
    }

    public static String getDirectionsReference(TreeNode root, int startValue, int destValue) {
        // Map to store parent nodes
        Map<Integer, TreeNode> parentMap = new HashMap<>();

        // Find the start node and populate parent map
        TreeNode startNode = findStartNode(root, startValue);
        populateParentMap(root, parentMap);

        // Perform BFS to find the path
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(startNode);
        Set<TreeNode> visitedNodes = new HashSet<>();
        // Key: next node, Value: <current node, direction>
        Map<TreeNode, Pair<TreeNode, String>> pathTracker = new HashMap<>();
        visitedNodes.add(startNode);

        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();

            // If destination is reached, return the path
            if (currentNode.val == destValue) {
                return backtrackPath(currentNode, pathTracker);
            }

            // Check and add parent node
            if (parentMap.containsKey(currentNode.val)) {
                TreeNode parentNode = parentMap.get(currentNode.val);
                if (!visitedNodes.contains(parentNode)) {
                    queue.add(parentNode);
                    pathTracker.put(parentNode, new Pair<>(currentNode, "U"));
                    visitedNodes.add(parentNode);
                }
            }

            // Check and add left child
            if (
                    currentNode.left != null &&
                            !visitedNodes.contains(currentNode.left)
            ) {
                queue.add(currentNode.left);
                pathTracker.put(currentNode.left, new Pair<>(currentNode, "L"));
                visitedNodes.add(currentNode.left);
            }

            // Check and add right child
            if (
                    currentNode.right != null &&
                            !visitedNodes.contains(currentNode.right)
            ) {
                queue.add(currentNode.right);
                pathTracker.put(currentNode.right, new Pair<>(currentNode, "R"));
                visitedNodes.add(currentNode.right);
            }
        }

        // This line should never be reached if the tree is valid
        return "";
    }

    public static String backtrackPath(
            TreeNode node,
            Map<TreeNode, Pair<TreeNode, String>> pathTracker
    ) {
        StringBuilder path = new StringBuilder();

        // Construct the path
        while (pathTracker.containsKey(node)) {
            // Add the directions in reverse order and move on to the previous node
            path.append(pathTracker.get(node).getValue());
            node = pathTracker.get(node).getKey();
        }

        // Reverse the path
        path.reverse();

        return path.toString();
    }

    public static void populateParentMap(
            TreeNode node,
            Map<Integer, TreeNode> parentMap
    ) {
        if (node == null) return;

        // Add children to the map and recurse further
        if (node.left != null) {
            parentMap.put(node.left.val, node);
            populateParentMap(node.left, parentMap);
        }

        if (node.right != null) {
            parentMap.put(node.right.val, node);
            populateParentMap(node.right, parentMap);
        }
    }

    public static TreeNode findStartNode(TreeNode node, int startValue) {
        if (node == null) return null;

        if (node.val == startValue) return node;

        TreeNode leftResult = findStartNode(node.left, startValue);

        // If left subtree returns a node, it must be StartNode. Return it
        // Otherwise, return whatever is returned by right subtree.
        if (leftResult != null) return leftResult;
        return findStartNode(node.right, startValue);
    }

    public static TreeNode lowestCommonAncestor(TreeNode node, int p, int q){
        if(node==null||node.val==p||node.val==q){
            return node;
        }
        TreeNode left=lowestCommonAncestor(node.left, p, q);
        TreeNode right=lowestCommonAncestor(node.right, p, q);

        if(left!=null&&right!=null){
            return node;
        }
        return left!=null?left:right;
    }

    public static boolean getPathFromCommonAncestorNode(TreeNode node, int value, StringBuilder path){
        if(node==null){
            return false;
        }
        if(node.val==value){
            return true;
        }
        path.append("L");
        boolean leftExist=getPathFromCommonAncestorNode(node.left, value, path);
        if(leftExist){
            return true;
        }
        path.deleteCharAt(path.length()-1);
        boolean rightExist;
        path.append("R");
        rightExist=getPathFromCommonAncestorNode(node.right, value, path);
        if(rightExist){
            return true;
        }
        path.deleteCharAt(path.length()-1);
        return rightExist;
    }

    public static String getDirectionsLCAAndDfs(TreeNode root, int startValue, int destValue) {
        TreeNode commonAncestorNode= lowestCommonAncestor(root, startValue, destValue);
//        System.out.println(commonAncestorNode.val);
        StringBuilder pathToStart=new StringBuilder();
        StringBuilder pathToDest=new StringBuilder();
        getPathFromCommonAncestorNode(commonAncestorNode, startValue, pathToStart);
        getPathFromCommonAncestorNode(commonAncestorNode, destValue, pathToDest);
        StringBuilder path= new StringBuilder();
        for(int i=0;i<pathToStart.length();i++){
            path.append("U");
        }
        path.append(pathToDest);
        return path.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (the root of a binary tree) with n nodes.
        //- Each node is (uniquely) assigned a value from (1 to n).
        //- You are also given an integer (startValue) representing the value of (the start node s), and a different integer (destValue)
        // representing the value of (the destination node t).
        //- Find (the shortest path) starting from (node s) and ending at (node t).
        // Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'.
        // Each letter indicates a specific direction:
        //  + 'L' means to go from a node to its left child node.
        //  + 'R' means to go from a node to its right child node.
        //  + 'U' means to go from a node to its parent node.
        //* Return the step-by-step directions of (the shortest path) from node s to node t.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //The number of nodes in the tree is n.
        //2 <= n <= 10^5
        //1 <= Node.val <= n
        //All the values in the tree are unique.
        //1 <= startValue, destValue <= n
        //startValue != destValue
        //+ Số node khá lớn
        //
        //- Brainstorm
        //- Cần lưu parent node của mỗi node
        //  + Dùng BFS
        //- Dùng DFS để tìm destination node gần nhất.
        //
        //- Nếu mỗi step:
        //  + Ta init new string + "R"/"L"/"U"
        //  ==> Memory exceed
        //- Ta cần dùng StringBuilder.
        //  + append("L")
        //  + deleteAt(lastIndex)
        //
        //1.1, Optimization
        //- Dùng DFS để tìm MapParent
        //- Dùng BFS để tìm path:
        //  Example:
        //  A -left-> B (B: Map<B, Pair<A,"L">)
        //  A -right-> C (C: Map<C, Pair<A,"R">)
        //  A -up-> D (D: Map<D, Pair<D,"U">)
        //  ==> Để lúc gặp target:
        //      + Ta đi go back + reverse string để lấy đúng thứ tự là được.
        //
        //1.2, Complexity
        //- Space: O(h+n)
        //- Time: O(n)
        //
        //2. Using lowest common ancester of a binary trees (LCA) + DFS
        //- Tìm lowest common ancester (Có thể hiểu là parent chung gần nhất)
        //  + Short path = (start node -> Common parent) + (common parent -> dest node)
        //- Riêng đoạn từ:
        //  + start node -> common parent : Chuyển hết thành "U" do toàn đi ngược lên up
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //3. Tìm path từ root -> start/ end node
        //- Thay vì dùng LCA method
        //- Tìm độ dài common string của (pathStart) và (pathEnd)
        //  + Do root -> left: left path
        //  + Do root -> right: right path
        //      + Check dựa trên common string của 2 path đó
        //      Ex:
        //      + Nếu khác nhánh ==> common = 1
        //      + Nếu cùng nhánh thì ==> sẽ có chung 1 vài directions đầu:
        //          R -> R -> Left
        //          R -> R -> Right
        //          + Common length = 2
        //** Kinh nghiệm:
        //- Ta có thể dùng cái (directions) để có thể xác định (distance)
        //- Ta cũng có thể dùng map để lưu lại parent của node --> Traverse dùng DFS
        //
        //3.1, Optimization
        //3.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //- Build binary tree from the array
        //0,1,2,3,4,5,6
        //i=1:
        //  + left=1*2+1 = 3
        //  + right=1*2+2 = 4
        //
        Integer[] root = {5,1,2,3,null,6,4};
        int startValue = 3, destValue = 6;
        TreeNode rootNode=new TreeNode(root[0]);
        int n = root.length;
        Deque<TreeNode> listNode=new LinkedList<>();
        listNode.add(rootNode);
        int index=0;

        while (!listNode.isEmpty()){
            TreeNode firstNode=listNode.removeFirst();
            int indexLeft=index*2+1;
            int indexRight=index*2+2;

            if(indexLeft<n){
                firstNode.left=new TreeNode(root[indexLeft]);
                listNode.addLast(firstNode.left);
            }
            if(indexRight<n&&root[indexRight]!=null){
                firstNode.right=new TreeNode(root[indexRight]);
                listNode.addLast(firstNode.right);
            }
            index++;
        }
        System.out.println(rootNode.val);
        System.out.println(getDirections(rootNode, 3, 6));
        System.out.println(getDirectionsReference(rootNode, 3, 6));
        System.out.println(getDirectionsLCAAndDfs(rootNode, 3, 6));
        //
        //#Reference:
        //257. Binary Tree Paths
        //- Lowest Common Ancestor of a Binary Tree II
        //- Lowest Common Ancestor of a Binary Tree III
        //- Lowest Common Ancestor of a Binary Tree IV
    }
}
