package interviews;

import java.util.HashSet;

public class E12_LinkedListCycle {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> listNodes=new HashSet<>();
        listNodes.add(head);

        while(head!=null){
            head=head.next;
            if(listNodes.contains(head)){
                return true;
            }
            listNodes.add(head);
        }
        return false;
    }

    public static boolean hasCycleOptimize(ListNode head) {
        if(head==null){
            return false;
        }

        ListNode slow=head;
        ListNode fast=head.next;

        while(slow!=null&&fast!=null){
            slow=slow.next;
            fast=fast.next;
            if(fast!=null){
                fast=fast.next;
            }
            if(fast==slow){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode listNode=new ListNode(3);
        ListNode listNode1=new ListNode(2);
        ListNode listNode2=new ListNode(0);
        ListNode listNode3=new ListNode(-4);
        listNode3.next=listNode1;
        listNode.next=listNode1;
        listNode1.next=listNode2;
        listNode2.next=listNode3;
//        listNode1.next=listNode;

        System.out.println(hasCycle(listNode));
        System.out.println(hasCycleOptimize(listNode));
        //Đề bài:
        //- Tìm xem list hiện tại có cycle hay không.
        //Bài này tư duy như sau:
        //Cách 1:
        //1,
        //1.1, Sử dụng hashSet để lưu thông tin từng node --> Sau đó check dần dần các nodes đằng sau xem có xuất hiện chưa!
        //
        //Cách 2:
        //1,
        //1.1, Phương pháp slow, fast tức là :
        //+ Sẽ luôn có 1 node fast đi trước (Đi nhanh x2 lần so với slow) sau đó --> Nếu node đó là node dạng cycle thì
        //node đó sẽ đuổi kịp slow (Khi vòng lại sớm)
        //--> Khi đuổi kịp ==> có cycle
        //1.2, Tại sao có quy luật như thế : Ta sẽ tìm hiểu những bài sau đó.
    }
}
