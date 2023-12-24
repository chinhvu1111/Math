package E1_List;

public class E4_PlusOneLinkedList {

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static ListNode plusOne(ListNode head) {
        int addOn=1;
        ListNode newRoot=reverseNode(head);
        ListNode node=newRoot;
        // System.out.println(node.val);

        while(node!=null&&addOn!=0){
            int newVal=node.val+addOn;
            // System.out.println(newVal);
            node.val=newVal%10;
            addOn=newVal/10;
            node=node.next;
        }
        reverseNode(newRoot);
        if(addOn!=0){
            ListNode newNode=new ListNode(addOn);
            newNode.next=head;
            head=newNode;
        }
        return head;
    }

    public static ListNode reverseNode(ListNode head){
        ListNode node=head;
        //1 -> 2 -> 4 -> 2 -> 6 -> 9
        //1 <- 2 <- 4 <- 2 <- 6 <- 9
        //prev -> 1(node) -> 2
        //prev -> 1(node) -> 2
        ListNode prevNode=null;

        while(node!=null){
            ListNode nextNode=node.next;
            node.next=prevNode;
            prevNode=node;
            node=nextNode;
        }
        return prevNode;
    }

    public static ListNode plusOneOptimization(ListNode head) {
        ListNode node=head;
        ListNode sentinel=new ListNode(0);
        sentinel.next=head;
        ListNode nodeNotNine=sentinel;

        while(node!=null){
            if(node.val!=9){
                nodeNotNine=node;
            }
            node=node.next;
        }
        nodeNotNine.val++;
        nodeNotNine = nodeNotNine.next;

        while(nodeNotNine!=null){
            nodeNotNine.val=0;
            nodeNotNine=nodeNotNine.next;
        }
        return sentinel.val!=0?sentinel:sentinel.next;
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- Add 1 vào trong số đã cho được biểu diễn dạng list
        //* Return số sau khi add 1
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraints:
        //+ The number of nodes in the linked list is in the range [1, 100].
        //+ 0 <= Node.val <= 9
        //+ The number represented by the linked list does not contain leading zeros except for the zero itself.
        //
        //- Brainstorm:
        //- Reverse linkedList
        //  + Sau đó là add 1 unit --> Reverse lần 2
        //
        //1.1, Optimization
        //1.2, Complexity:
        //+ N is the length of digit
        //- Space : O(1)
        //- Time : O(n)
        //
        //2. Sentinel Head + Textbook Addition.
        //2.0, Idea
        //[ 2,3,9,2,9 ]
        //rs=2,3,9,3,0
        //- Right most != 9:
        //  + Điền tất cả các số đằng sau =0.
        //
        //#Reference:
        //2816. Double a Number Represented as a Linked List
    }
}
