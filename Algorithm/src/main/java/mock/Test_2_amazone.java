package mock;

public class Test_2_amazone {

      public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public static ListNode reverseList(ListNode head) {
          ListNode node=head;
          ListNode prevNode=null;

          while (node!=null){
              ListNode tmp=node.next;
              node.next=prevNode;
              prevNode=node;
              node=tmp;
          }
          return prevNode;
    }

    public static void main(String[] args) {
    }
}
