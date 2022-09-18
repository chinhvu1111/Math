package interviews;

public class E14_LinkedListCycleIIWrong_linkedList {

    static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val+
                    '}';
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

        //** Đề bài:
        //- Cho 1 root node --> Tìm node mà bắt đầu cycle (Nếu có) <> return null
        //
        //============================================= Đây là 1 tư duy sai khi làm bài dạng kiểu slow, fast
        //Bài này tư duy như sau:
        //1, Đây là dạng bài toán rùa và thỏ
        //- Thỏ sẽ chạy trước --> nếu có cycle thật thì --> Thỏ vẫn có khả năng đuổi kịp rùa.
        //- Vì thỏ nhảy [x2 giá trị đường đi] --> Việc có (cycle max lắm thì cũng chỉ đi x2)
        //===> TỆ NHẤT : Thỏ vẫn có thể đuổi kịp rùa ở (điểm đích cuổi).
        //
        //** NOTE: Với bài dạng này bắt buộc phải:
        // + slow=head
        // + fast=head.next (SAI) ===================> SAI
        //
        //Ngay đầu vì nếu khởi tạo nó như nhau --> Khi đếm số quãng đường chạy thì fast sẽ không thể đuổi kịp slow
        //Trong trường hợp circle dạng vòng trong (tail --> head)
        //Ex: 3,2,0,-4 (-4 --> 3 )
        //==> 0,1,2,3,(0) (Cycle)
        //===============================================GIẢI THÍCH CÁCH TRÊN SAI NHƯ SAU
        //
        //+ slow và fast cần găp nhau ở đỉnh có cycle : slow, fast (Đều có thể đi vòng vòng ---> Dài hơn độ dài thực tế khi không có cycle (length))
        //Explain: Nếu slow=root, fast=root.
        //VD-1:
        //0,1,2,3,4,(2)(start) : Phải return 2
        //
        //+ slow=root, fast=root
        //slow : 0,1,2,(3),4,2,3,4
        //fast : 0,2,4,(3),2,4,3,2
        //===>
        //+ slow=root, fast=root.next
        //slow : 0,1,(2),3,4,2,3,4
        //fast : 1,3,(2),4,3,2,4,3
        //==> Đã gặp ở điểm bắt đầu cycle (2)
        //
        //VD-1:
        //0,1,2,3,4,(0)(start)  : Phải return 0
        //
        //+ slow=root, fast=root
        //slow : 0,1,2,3,4,(0),1,2
        //fast : 0,2,4,1,3,(0),2,4
        //--> return 0
        //+ slow=root, fast=root.next
        //slow : 0,1,2,3,(4),0,1,2
        //fast : 1,3,0,2,(4),1,3,0
        //==> Đã gặp ở điểm bắt đầu cycle (2)
        //--> return 4
        //VD-1/2 ===> Không có cách chọn (fast=root/ fast=root.next) cho ra kết quả đúng
        //==> Luôn luôn thiếu case.
        //
        //+ Ở đây ta muốn rằng
        //
        //*** KINH NGHIỆM:
        //- Với những dạng bài theo kiểu "Quãng đường" của slow, fast ==> Nên mô tả các node nó đi trên nhiều dòng r COMPARE với nhau
        //====> Kinh nghiệm Compare những khía cạnh chung của bài toán.
        //2, Ta muốn thỏ đuổi kịp rùa tại chính điểm diễn ra cycle thì sao --> Câu trả lời khá khó
        System.out.println(detectCycle(listNode));
    }
}
