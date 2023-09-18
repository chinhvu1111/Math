package E1_List;

public class E2_DeleteTheMiddleNodeOfALinkedList {

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

    public ListNode deleteMiddle(ListNode head) {
        ListNode slowNode=head;
        ListNode prevSlowNode=null;
        ListNode fastNode=head.next;

        //Ex:
        //+ 1 (slow) --> 2 (fast) --> 3 --> 4
        //+ 1 --> 2 (slow) --> 3 --> 4 (fast) : return 2
        //Ex:
        //+ 1 (slow) --> 2 (fast) --> 3
        //+ 1 (slow) --> 2 --> 3 --> fast : return 1
        while(fastNode!=null){
            prevSlowNode=slowNode;
            slowNode=slowNode.next;
            fastNode = fastNode.next;
            if(fastNode!=null){
                fastNode=fastNode.next;
            }
        }
        if(prevSlowNode!=null){
            // System.out.printf("%s %s\n", prevSlowNode.val, slowNode.val);
            prevSlowNode.next=slowNode.next;
        }else{
            return null;
        }
        return head;
    }

    public static void main(String[] args) {
        //** Đề bài:
        //* Delete the middle node
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //
        //- Brainstorm:
        //* Trong bài toán slow, fast pointer
        //- Fast sẽ luôn đi trước slow 1 step ==> Để có thể return ra node before (middle node)
        //Ex:
        //+ 1 (slow) --> 2 (fast) --> 3 --> 4
        //+ 1 --> 2 (slow) --> 3 --> 4 (fast) : return 2
        //Ex:
        //+ 1 (slow) --> 2 (fast) --> 3
        //+ 1 (slow) --> 2 --> 3 --> fast : return 1
    }
}
