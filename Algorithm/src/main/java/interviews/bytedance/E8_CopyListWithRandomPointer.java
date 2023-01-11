package interviews.bytedance;

import java.util.Hashtable;

public class E8_CopyListWithRandomPointer {

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static Node copyRandomList(Node head) {
        Node node=null;
        Node newHead=null;
        Node temp=head;
        Hashtable<Node, Integer> nodeMapPos=new Hashtable<>();
        Hashtable<Integer, Node> posMapNewNode=new Hashtable<>();
        int i=0;

        while (temp!=null){
            Node currentNode=null;
            if(node==null){
                currentNode=new Node(temp.val);
                newHead=currentNode;
            }else{
                currentNode=new Node(temp.val);
                node.next=currentNode;
            }
            node=currentNode;
            posMapNewNode.put(i, node);
            nodeMapPos.put(temp, i);
            temp=temp.next;
            i++;
        }
        temp=head;
        Node newTemp=newHead;
        while (temp!=null){
            Node currentRandom=temp.random;

            if(currentRandom!=null){
                Integer pos=nodeMapPos.get(currentRandom);
                newTemp.random= posMapNewNode.get(pos);
            }
            newTemp=newTemp.next;
            temp=temp.next;
        }
        return newHead;
    }

    public static Node copyRandomListRefactor(Node head) {
        Node node=null;
        Node newHead=null;
        Node temp=head;
        Hashtable<Node, Node> nodeMapNewNode=new Hashtable<>();
        int i=0;

        while (temp!=null){
            Node currentNode=null;
            if(node==null){
                currentNode=new Node(temp.val);
                newHead=currentNode;
            }else{
                currentNode=new Node(temp.val);
                node.next=currentNode;
            }
            node=currentNode;
            nodeMapNewNode.put(temp, node);
            temp=temp.next;
            i++;
        }
        temp=head;
        Node newTemp=newHead;
        while (temp!=null){
            Node currentRandom=temp.random;

            if(currentRandom!=null){
                newTemp.random= nodeMapNewNode.get(currentRandom);
            }
            newTemp=newTemp.next;
            temp=temp.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        //
        //** Đề bài:
        //+ Clone lại 1 linked list mà ở đó mỗi node có 2 đặc điểm
        //+ val : Gía trị
        //+ next : node next
        //+ random : random trỏ đến node nào cũng được
        //
        //** Bài này tư duy như sau:
        //1. Tư duy ban đầu
        //1.1,
        //- Có các vấn đề cần xử lý như sau:
        //+ Node —> old node
        //+ Node —> new node
        //==> Để dễ nhất thì ta nên tạo các nodes trước (Chưa cần trỏ vội)
        //
        //- Sau đó ta đã có tư duy dựa trên postition:
        //+ Từ node.randome==node1
        //==> Ta sẽ lưu hashMap<node1, pos1>
        //==> Và list mới ta cũng lưu <pos1, newNode1>
        //--> Để khi ta xét đến node và node.random : Ta có thể lấy ngay new node tương ứng tại vị trí pos1 (Với list cũ)
        //1.2, Tối ưu:
        //- Ở đây ta có thể không cần dùng đến pos
        //==> Chỉ cần HashMap<node, newNode> là được.
        //1.3,
        //- Time complexity : O(n)
        //- Space complexity : O(n)
        //
        //#Reference:
        //139. Word Break
        //133. Clone Graph
        //1485. Clone Binary Tree With Random Pointer
        //1490. Clone N-ary Tree
    }
}
