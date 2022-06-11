package interviews;

public class E16_RemoveDuplicatesFromSortedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode root=new ListNode(head.val);
        ListNode temp=root;

        while (head!=null){
            head=head.next;

            if(head!=null&&temp.val!= head.val){
                temp.next=new ListNode(head.val);
                temp=temp.next;
            }
        }
        return root;
    }

    public static void main(String[] args) {

    }
}
