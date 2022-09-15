package interviews;

public class E6_ReverseLinkedList {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode node=head;
        ListNode temp = null;
        ListNode prevTemp = null;

        while (node.next!=null){
            temp=node;
            node=node.next;
            temp.next=prevTemp;
            prevTemp=temp;
        }
        node.next=temp;
        return node;
    }
    public static ListNode reverseListOptimize(ListNode node){
        if(node==null){
            return null;
        }
        ListNode start=node;
        ListNode prevNode=null;

        while(start.next!=null){
            ListNode nextNode=start.next;
            start.next=prevNode;
            prevNode=start;
            start=nextNode;
        }
        if(start!=null){
            start.next=prevNode;
        }
        return start;
    }

    public static void main(String[] args) {
        System.out.println(reverseList(null));
        System.out.println(reverseListOptimize(null));
        //Bài này tư duy như sau:
        //1, Điều ta cần làm là tạo nhiều Temp Variables --> Để tiện cho việc swap
        //VD:
        //a -> b -> c
        //--> a <-b / (c)
        //--> a1 <- (a) <- b (cut)(phải lưu lại pointer cũ) c (Nhưng pointer tiếp phải được trỏ tới đây)
        //+ nextNode = a.next (b) (SAVED)
        //+ a.next = prevNode (node trước đó a1) ==> (Trỏ ngược lại : <--)
        //+ prevNode=start (a)
        //** Tư duy chính như sau:
        //a --> (b) --> c
        //- Lưu lại node trỏ đến (c) : (b) ==> Phục vụ việc next
        //+ ListNode nextNode=start.next;
        //- Trỏ (b) --> a.
        //+ start(b).next=prevNode(a);
        //+ Gán tiếp prevNode = (b) (SAVED) --> Để c trỏ lại
    }
}
