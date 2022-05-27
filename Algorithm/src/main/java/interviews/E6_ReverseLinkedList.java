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

    public ListNode reverseList(ListNode head) {
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

    public static void main(String[] args) {

    }
}
