package interviews;

public class E14_LinkedListCycleII {

    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

    public static ListNode detectCycle(ListNode head) {
        if(head==null){
            return null;
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
                return slow;
            }
        }
        return null;
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

        //Bài này tư duy như sau:
        //1, Đây là dạng bài toán rùa và thỏ
        //- Thỏ sẽ chạy trước --> nếu có cycle thật thì --> Thỏ vẫn có khả năng đuổi kịp rùa.
        //- Vì thỏ nhảy [x2 giá trị đường đi] --> Việc có (cycle max lắm thì cũng chỉ đi x2)
        //===> TỆ NHẤT : Thỏ vẫn có thể đuổi kịp rùa ở điểm đích cuổi.
        //**NOTE: Với bài dạng này bắt buộc phải:
        // slow=head
        // fast=head.next
        //Ngay đầu vì nếu khởi tạo nó như nhau --> Khi đếm số quãng đường chạy thì fast sẽ không thể đuổi kịp slow
        //Trong trường hợp circle dạng vòng trong (tail --> head)
        //Ex: 3,2,0,-4 (-4 --> 3 )
        //2, Ta muốn thỏ đuổi kịp rùa tại chính điểm diễn ra cycle thì sao --> Câu trả lời khá khó
        System.out.println(detectCycle(listNode));
    }
}
