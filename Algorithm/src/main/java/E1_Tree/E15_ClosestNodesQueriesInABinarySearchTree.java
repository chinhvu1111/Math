package E1_Tree;

import jdk.nashorn.internal.ir.TernaryNode;

import java.util.*;

public class E15_ClosestNodesQueriesInABinarySearchTree {
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

    public static class Node{
        int value;
        int order;

        public Node(int value) {
            this.value = value;
        }
        public Node(int value, int order) {
            this.value = value;
            this.order=order;
        }
    }

    public static List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        //Space : O(N)
        Stack<TreeNode> stackNodes=new Stack<>();
        List<Node> orderNodes=new ArrayList<>();
        //Space : O(M*2)
        HashMap<Node, Integer> orderMapIndex=new HashMap<>();

        //Time : O(M)
        for(int i=0;i<queries.size();i++){
            Node currentNode=new Node(queries.get(i));
            orderNodes.add(currentNode);
            orderMapIndex.put(currentNode, i);
        }
        //O(MLog(M))
        orderNodes.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.value - o2.value;
            }
        });
//        List<Integer> originalIndex=new ArrayList<>();
//
//        for(int i=0;i<orderNodes.size();i++){
//            originalIndex.add(orderMapIndex.get(orderNodes.get(i)));
//        }
        //Space : O(M)
        Queue<Node> sortList=new LinkedList<>(orderNodes);
        List<List<Integer>> rs=new ArrayList<>();

        int pred=-1;

        //Time : O(N)
        while (!stackNodes.isEmpty()||root!=null){
            while (root!=null){
                stackNodes.add(root);
                root=root.left;
            }
            root=stackNodes.pop();
            while (!sortList.isEmpty()&&root.val>=sortList.peek().value&&sortList.peek().value>pred){
                List<Integer> currentList=new ArrayList<>();

                if(root.val==sortList.peek().value){
                    currentList.add(root.val);
                }else{
                    currentList.add(pred);
                }
                currentList.add(root.val);
                currentList.add(orderMapIndex.get(sortList.peek()));
                System.out.println(currentList);
//                System.out.printf("%s %s\n", sortList.peek(), currentList);
                rs.add(currentList);
                sortList.poll();
            }
            pred=root.val;
            root=root.right;
        }
        while (!sortList.isEmpty()){
            List<Integer> currentRs=new ArrayList<>();
            if(sortList.peek().value>=pred){
                currentRs.add(pred);
            }else{
                currentRs.add(-1);
            }
            currentRs.add(-1);
            currentRs.add(orderMapIndex.get(sortList.poll()));
            rs.add(currentRs);
        }
//        System.out.println(rs);
        rs.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(2) - o2.get(2);
            }
        });
        List<List<Integer>> result=new ArrayList<>();

        for(List<Integer> currentRs:rs){
            currentRs.remove(currentRs.size()-1);
            result.add(currentRs);
        }
        return result;
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
        // Requirement
        //-
        //* return answer[i] = [min(i), max(i)]
        //+ Min(i) is largest value in the tree that is smaller or equal to queries[i] <> if value doesn't not exists : return -1
        //+ Max(i) is smallest value in the tree that is greater or equal to queries[i] <> if value doesn't not exists : return -1
        //
        // Idea
        //1.
        //1.0,
        //- Constraint
        //The number of nodes in the tree is in the range [2, 105].
        //1 <= Node.val <= 10^6
        //n == queries.length
        //1 <= n <= 10^5
        //1 <= queries[i] <= 10^6
        //--> Số lượng queries khá lớn ==> Chỉ có thể làm linear
        //
        //- Brainstorm
        //- Vì các queries range có quy luật đi từ small --> big
        // a<b ==> range(a) -> range(b)
        //+ Ta sẽ sắp xếp trước dùng priorityQueue ==> Sau đó pop dần ra.
        //
        //- Và dùng inorder traversal để duyệt range(x,y)
        //
        //- Cứ duyệt sort list đến hết --> Nếu hết queries ==> root==null
        //==> Các node còn lại của query sẽ không thể tìm được range phù hợp ==> [max(array), -1]
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Time complexity : O(M + MLog(M) + N)
        //- Space complexity : O(N+M*2)
        //
        //* Method-2:
        //- Dùng cách add all nodes vào TreeSet ==> Sau đó dùng (min, max)
        //--> Để tìm từng range một thay vì traverse như trên.
        //
        Integer[] nodes=new Integer[]{19,3,20,2,10,null,null,1,null,5,15,null,null,4,6};
        Integer[] queries=new Integer[]{143265,20,19,172253,562096,330190,474166,460360,929962,2};

        TreeNode root=new TreeNode(nodes[0]);
        Queue<TreeNode> queueNodes=new LinkedList<>();
        queueNodes.add(root);
        int i=0;
        for(;i<nodes.length;i++){
            TreeNode currentNode=queueNodes.poll();
            TreeNode next=null;
            TreeNode next1=null;
            if(i*2+1<nodes.length&&nodes[i*2+1]!=null){
                next=new TreeNode(nodes[i*2+1]);
                queueNodes.add(next);
            }
            if(i*2+2<nodes.length&&nodes[i*2+2]!=null){
                next1=new TreeNode(nodes[i*2+2]);
                queueNodes.add(next1);
            }
            if(currentNode!=null){
                currentNode.left=next;
                currentNode.right=next1;
            }
        }
//        println(root);
        closestNodes(root, Arrays.asList(queries));
        //#Reference
        //272. Closest Binary Search Tree Value II
        //700. Search in a Binary Search Tree
    }
}
