package E1_Tree;

import java.util.*;

public class E16_ClosestBinarySearchTreeValueII {

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
        public Node(int value){
            this.value=value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> stackNode=new Stack<>();
        //Time : KLog(K)
        //Space : O(K)
        PriorityQueue<Node> sortedNode=new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int) (Math.abs(o2.value-target) -Math.abs(o1.value-target));
            }
        });
        boolean isValid=false;

        //Time : O(N)
        //Space : O(N)
        while (!stackNode.isEmpty()||root!=null){
            while (root!=null){
                stackNode.add(root);
                root=root.left;
            }
            TreeNode currentNode=stackNode.pop();
//            System.out.println(currentNode.val);
            Node curNode=new Node(currentNode.val);
            if(sortedNode.size()<k){
                sortedNode.add(curNode);
            }else if(!isValid){
                if(!sortedNode.isEmpty()&&Math.abs(sortedNode.peek().value-target)>Math.abs(curNode.value-target)){
                    sortedNode.poll();
                    sortedNode.add(curNode);
                }else {
                    isValid=true;
                }
            }else {
                break;
            }
//            System.out.println(sortedNode);
            root=currentNode.right;
        }
        List<Integer> rs=new ArrayList<>();

        for(Node node:sortedNode){
            rs.add(node.value);
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //-
        //* K nodes nearest to target
        //
        // Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= k <= n <= 10^4.
        //0 <= Node.val <= 10^9
        //-10^9 <= target <= 10^9
        //
        //- Brainstorm
        //- Duyệt theo tăng dần dùng priority queue ==> Sắp xếp theo hiệu của điểm đó với target
        //+ Nếu chưa quá size --> Add new node
        //+ Nếu quá size --> so sánh điểm tiếp với điểm peek() (nếu điểm sắp tới tốt hơn peek)
        // + ==>  pop() + add new node
        // + <> Sẽ add thêm cho đến khi đủ.
        //
        //1.1, Implementation
        //- isValid=false : Để dùng xác định là chuỗi hiện tại chỉ có tệ đi.
        //==> Khi queue đủ elements --> return.
        //
        //1.2, Optimization
        //-
        //
        //1.3, Complexity
        //- Time : O(N + KLog(K))
        //- Space : O(N + K)
        //
        double target = 3.714286;
        int k = 2;

        Integer[] nodes=new Integer[]{4,2,5,1,3};
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
        System.out.println(closestKValues(root, target, k));
        //#Reference:
        //116. Populating Next Right Pointers in Each Node
        //2322. Minimum Score After Removals on a Tree
        //226. Invert Binary Tree
    }
}
