package E1_Tree;

import java.util.LinkedList;
import java.util.Queue;

public class E43_PopulatingNextRightPointersInEachNodeII {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public static void solution(Node node){
        if(node==null){
            return;
        }
        Queue<Node> nodes=new LinkedList<>();
        nodes.add(node);

        while (!nodes.isEmpty()){
            int size=nodes.size();
            Node prevNode=null;

            for(int i=0;i<size;i++){
                Node nextNode = nodes.poll();
                if(prevNode!=null){
                    prevNode.next=nextNode;
                }
                prevNode=nextNode;
                if(nextNode.left!=null){
                    nodes.add(nextNode.left);
                }
                if(nextNode.right!=null){
                    nodes.add(nextNode.right);
                }
            }
        }
    }

    public static Node connect(Node root) {
        solution(root);
        return root;
    }

    public static Node connectOptimization(Node root) {
        return root;
    }

    public static void processChild(Node childNode) {
        if(childNode==null){
            return;
        }
        if(prev!=null){
            prev.next=childNode;
        }else{
            leftMost=childNode;
        }
        prev=childNode;
    }

    static Node leftMost, prev;

    public Node connectRefer(Node root) {
        if (root == null) {
            return root;
        }
        leftMost = root;
        Node cur= leftMost;

        while(leftMost!=null){
            prev = null;
            cur=leftMost;
            //Nếu không có next level ==> Break
            leftMost=null;

            while (cur!=null){
                processChild(cur.left);
                processChild(cur.right);
                cur=cur.next;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Populate (each next pointer) to point to (its next right node).
        //- If there is (no next right node), (the next pointer) should be set to (NULL).
        //- Initially, all next pointers are set to NULL.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //The number of nodes in the tree is in the range [0, 6000].
        //-100 <= Node.val <= 100
        //  ==> Node khá nhỏ Time: O(n^2)
        //
        //- Brainstorm
        //        x
        //    /      \
        //  left -> right
        //+ Gán sau khi traverse ==> Duy trì được structure
        //+ Cùng layer --> Nối nhau
        //- Level traversal:
        //  + Dùng queue là được.
        //
        //1.1, Optimization
        //- Có thể tối ưu space => O(constant)
        //           1
        //         /   \
        //       2  ->  3
        //     /   \      \
        //   4  ->  5  ->  7
        // /  \    / \      \
        //1    8 9    10     11
        //- Làm sao dùng recursion mà cùng layer mà có thể point được với nhau:
        //
        //- We only move on to (the level N+1) when we are done establishing the next pointers for the level N.
        // So, since we have access to all the nodes on a particular level via the next pointers,
        // we can use these next pointers to establish the connections for the next level or the level containing their children.
        //
        //- Cách này dạng giống queue nhưng ta sẽ dùng pointers thay cho queue:
        //  + Left most : Node bắt đầu layer mới
        //  + Prev: node trước của current node đang xét của layer mới
        //- Mục đích của nó là:
        //  + Traverse all các node của layer hiện tại
        //      ** MAIN POINT:
        //          + Các nodes ở layer hiện tại đã kết nối với nhau bằng (prevNode.next = node)
        //          ==> Loop đi ngang khá dễ (TÍNH CHẤT NÀY TA PHẢI MẶC ĐỊNH CÓ)
        //          + Mỗi childnode như thế ==> Ta lại tìm các nối (left node và right node) của nó với nhau
        //  + Đồng thời lưu leftMost mới:
        //      + Để có thể gán (current_node = leftMost) khi xét các node trong (new layer)
        //
        //1.2, Complexity
        //- Space: O(n) => O(constant)
        //- Time: O(n)
        //
        Integer[] nodes=new Integer[]{19,3,20,2,10,null,null,1,null,5,15,null,null,4,6};
        Integer[] queries=new Integer[]{143265,20,19,172253,562096,330190,474166,460360,929962,2};

        Node root=new Node(nodes[0]);
        Queue<Node> queueNodes=new LinkedList<>();
        queueNodes.add(root);
        int i=0;
        for(;i<nodes.length;i++){
            Node currentNode=queueNodes.poll();
            Node next=null;
            Node next1=null;
            if(i*2+1<nodes.length&&nodes[i*2+1]!=null){
                next=new Node(nodes[i*2+1]);
                queueNodes.add(next);
            }
            if(i*2+2<nodes.length&&nodes[i*2+2]!=null){
                next1=new Node(nodes[i*2+2]);
                queueNodes.add(next1);
            }
            if(currentNode!=null){
                currentNode.left=next;
                currentNode.right=next1;
            }
        }
        //#Reference:
        //116. Populating Next Right Pointers in Each Node
    }
}
