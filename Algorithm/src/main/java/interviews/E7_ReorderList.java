package interviews;

import java.util.ArrayList;

public class E7_ReorderList {

    public static class ListNode {
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

    public static void reorderList1(ListNode head) {
        ListNode start = head;
        ArrayList<ListNode> arr = new ArrayList<>();
        arr.add(start);

        while (start.next != null) {
            ListNode temp=start.next;
            start.next=null;
            start=temp;
            arr.add(start);
        }
        int n = arr.size();
        ListNode temp = null;
        int rep=(n%2!=0)?n/2:(n-1)/2;

        for (int i = 0; i <= rep; i++) {
            ListNode node = arr.get(i);
            if (temp != null) temp.next = node;
            ListNode node1 = arr.get(n - 1 - i);
            if(i!=n-i-1){
                node.next = node1;
            }
            temp = node1;
        }
    }

    public static void reorderListOptimize(ListNode head) {
        ListNode start = head;
        ArrayList<ListNode> arr = new ArrayList<>();
        arr.add(start);

        while (start.next != null) {
            ListNode temp=start.next;
            start.next=null;
            start=temp;
            arr.add(start);
        }
        int n = arr.size();
        ListNode temp = null;
        int rep=(n%2!=0)?n/2:(n-1)/2;

        for (int i = 0; i <= rep; i++) {
            ListNode node = arr.get(i);
            if (temp != null) temp.next = node;
            ListNode node1 = arr.get(n - 1 - i);

            if(i!=n-i-1){
                node.next = node1;
            }
            temp = node1;
        }
    }

//    public static ListNode middleNode(ListNode node){
//
//    }

    public static ListNode reverseList(ListNode node){
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
        ListNode node=new ListNode(2);
        ListNode node1=new ListNode(3);
        ListNode node2=new ListNode(4);
        ListNode node3=new ListNode(1);
        node.next=node1;
        node1.next=node2;
        node2.next=node3;
        reverseList(node);
        reorderList1(node);
        //Đề bài là biến đổi list --> (0, n), (1, n-1) ....,(n-1, 1), (n, 0) ==> Chuỗi này dạng đối xứng
        //0,
        //- Tư duy là add các node vào array list --> Ta sẽ xác định được vị trí (0 --> (n-1)/2 | n/2)
        //- Sau đó ta nối theo pair (i, n-i)
        //Cách làm tối ưu như sau:
        //1, Tìm middle node trước:
        //VD: 1->2->3->(4)->5->6->null
        //--> Reverse second part:
        //VD:
        //- 1->2->3
        //- null<-(4)<-5<-6
        //2. Sau đó nối lần lượt.

    }
}
