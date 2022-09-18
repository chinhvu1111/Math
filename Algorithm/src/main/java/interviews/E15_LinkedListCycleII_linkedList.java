package interviews;

import java.util.ArrayList;
import java.util.List;

public class E15_LinkedListCycleII_linkedList {

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
                    "val=" + val +
                    '}';
        }
    }
    
    public static ListNode detectCycle(ListNode head) {
        if(head==null){
            return null;
        }
        int lengthCycle=getLengthMove(head);

        if(lengthCycle==0){
            return null;
        }
        ListNode slow=head;
        ListNode fast=head;
        int move=0;

        while (slow!=null&&move!=lengthCycle){
            slow=slow.next;
            move++;
        }
        while (slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }

        return slow;
    }

    public static int getLengthMove(ListNode head){
        ListNode slow=head;
        ListNode fast=head.next;
        int lengthCycle=0;

        while (slow!=null&&fast!=null){
            slow=slow.next;
            fast=fast.next;

            if(fast!=null){
                fast=fast.next;
            }

            if(slow==fast){
                ListNode temp=slow;

                do {
                    temp=temp.next;
                    lengthCycle++;
                }while (temp!=fast);
                break;
            }
        }
        return lengthCycle;
    }

    public static ListNode detectCycleOptimize(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode fast=head;
        ListNode slow=head;

        while (slow!=null&&fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;

            if(slow==fast){
                ListNode slow1=head;

                //Fast chắc chắn gặp đc slow (Do đk bên trên, có tồn tại cycle)
                //Còn khác thì còn chạy --> Vì chắc chắn có cycle nên kiểu gì cũng thấy
                while (slow1!=slow){
                    slow1=slow1.next;
                    slow=slow.next;
                }
                return slow;
            }
        }

        return null;
    }

    public static void main(String[] args) {
//        ListNode listNode=new ListNode(3);
//        ListNode listNode1=new ListNode(2);
//        ListNode listNode2=new ListNode(0);
//        ListNode listNode3=new ListNode(-4);
//        listNode3.next=listNode1;
//        listNode.next=listNode1;
//        listNode1.next=listNode2;
//        listNode2.next=listNode3;

//        ListNode listNode=new ListNode(1);

        ListNode listNode=new ListNode(1);
        ListNode listNode1=new ListNode(2);
        listNode.next=listNode1;
        listNode1.next=listNode;

        int arr[]={-21,10,17,8,4,26,5,35,33,-7,-16,27,-12,6,29,-12,5,9,20,14,14,2,13,-24,21,23,-21,5};
        List<ListNode> listNodeList=new ArrayList<>();

        for(int i=0;i<arr.length;i++){
            ListNode listNode2=new ListNode(i);
            if(i!=0){
                listNodeList.get(i-1).next=listNode2;
            }
            listNodeList.add(listNode2);
        }
        listNodeList.get(27).next=listNodeList.get(24);

        //** Đề bài:
        //- Cho 1 root node --> Tìm node mà bắt đầu cycle (Nếu có) <> return null
        //
        //Bài này tư duy như sau:
        //0, Để có thể làm được bài này --> Cần tip, tricks?
        //0.1, Cần tìm độ dài cycle của list.
        //- Nếu muốn tìm độ dài cycle ---> Cần để 1 node nào đó vào cycle
        //+ Cần xác định khi nào vào cycle
        //VD: Trong trường hợp này vào cycle khi slow gặp fast --> Khi fast gặp slow từ là fast đã đuổi kịp (Mặc dù slow đi trước)
        //+ Cần xác định 2 điểm mốc start --> end để đo độ dài cycle.
        //VD: Ở đây là 2 mock là 2 lần gặp nhau của slow và fast.
        //
        //Key tư duy ở đây là:
        //1, Ta sẽ tiếp tục duyệt để check xem length của cycle là bao nhiêu?
        //cycleLength=getLengthCycle()
        //
        //2, Khi có cycle rồi thì có 1 điều ta cần lưu ý:
        //- Số nodes mà fast chạy nhiều hơn số node mà slow chạy chính là (x*lengthCycle) .
        //- Nên nếu mà slow chạy trước:
        // số nodes = độ dài cycle
        // ===> fast sẽ đuổi kịp slow ở ngay vị trí node mà xảy ra cycle (Tất nhiên đó sẽ là điểm giao)
        //2.1, Explain (Đoạn này chỉ dùng cho cách 2):
        //- Khoảng cách từ root --> the point which at beginning of cycle : a
        //- Slow đi được 1 đoạn từ node (at beginning of cycle : A) đến điểm meet (B) có độ dài : b
        //- slow (traverse) : a + b
        //- fast đi tương tự nhưng cần đi 1 đoạn c từ điểm gặp (B: tức là chưa gặp) --> Đến điểm (A: điểm bắt đầu cycle)
        //- fast (traverse) : a + b*2 + c
        //--> Do fast đi nhanh gấp đôi slow:
        //- fast (traverse) : 2*(a+b)
        // a + b*2 + c = 2*(a+b)
        //===> a==c
        //2.2, Giải thích 1 cách tổng quát hơn cho bài toán này : Vì tư duy phía trên không được clear
        //- Fast giả sử đi qua k chu kỳ N mới gặp được slow
        //- Slow lúc đó đã đi được 2 đoạn a,b
        //==> Ta hướng tư duy về chỉ Fast để so sánh độ dài đoạn đường fast đi:
        //2* (a + b) = a + b + k * N : (slow đi được (a+b), fast đi được (a+b + k*N)) (Slow có đi thêm thì hiệu K-H vẫn thế)
        //a + b = K * N.
        //2.3, Thực ra cốt lõi của tư duy là dùng 2 pointers cùng tốc độ để xác định điểm bắt đầu của cycle.
        //- Mình bị nhầm tư duy khi ---> Bám vào fast --> Để tìm point cycle (SAI HƯỚNG).
        //+ Fast node ở đây chỉ phục vụ cho việc tìm cycle thôi.
        //- slow-1, slow-2.
        //+ slow-2 sẽ đi nhiều hơn slow-1 độ dài = cycle = N
        //- Nếu muốn slow-1 và slow-2 gặp cùng ở point (giáp cycle)
        //===> Vì slow-2 đi nhiều hơn + tốc độ như nhau ==> Cần phải đi trước N (cycle độ dài của bài).
        //2.4, Từ đó hướng đến việc tìm length cycle --> Đi slow-2 trước length of cycle.
        //----> Tư duy cách 1 tìm cycle.
        //Cách 2:
        //1,
        //1.1, Tư duy fast gặp slow :
        //- Khoảng cách từ root --> the point which at beginning of cycle : a
        //- Slow đi được 1 đoạn từ node (at beginning of cycle : A) đến điểm meet (B) có độ dài : b
        //- slow (traverse) : a + b
        //- fast đi tương tự nhưng cần đi 1 đoạn c từ điểm gặp (B: tức là chưa gặp) --> Đến điểm (A: điểm bắt đầu cycle)
        //- fast (traverse) : a + b*2 + c
        //--> Do fast đi nhanh gấp đôi slow:
        //- fast (traverse) : 2*(a+b)
        // a + b*2 + c = 2*(a+b)
        //===> a==c
        //Ta thấy răng nếu đặt (slow-2 đi đoạn c) == thời gian (slow-1 đi đoạn a)
        //* Mà đoạn a chính là khoảng cách đết nút cycle --> Ta không cần tìm cycle --> Chỉ cần xác định điểm (gặp fast và slow_
        //==> Sau đó tạo slow-1 là được.
        //- Mô phỏng:
        // Root-----a------A------b------B(meet)==========c=========(A)
        //1.2, Nếu không tư duy dạng bên trên thì từ việc dùng slow, fast
        //- Từ những quãng đường đó ta cũng có thể suy ra được --> nếu dùng slow-1, slow-2
        //---> Cắt quãng đường slow-2 cần đi + quy định gặp nhau vẫn ở đỉnh của fast.
        //
        //===> Lúc đó ta sẽ suy luận tiếp
        //
        //1.3, Cách này có thể implement nối tiếp lúc traverse (fast and slow)
        //
        //*** KINH NGHIỆM:
        //- Bài dạng này --> Nên vẽ trực quan
        //- Nên so sánh giữa (slow-1, slow-2), (slow, fast) ===> Để xác định các điểm gốc.
        //- Dùng phương pháp quy nạp suy luận.
        //Fast chắc chắn gặp đc slow (Do đk bên trên, có tồn tại cycle)
        //Còn khác thì còn chạy --> Vì chắc chắn có cycle nên kiểu gì cũng thấy
        //========================CODE
        //while (slow1!=slow){
        //    slow1=slow1.next;
        //    slow=slow.next;
        //}
        //return slow;
        //========================
        System.out.println(detectCycle(listNodeList.get(0)));
        System.out.println(detectCycleOptimize(listNodeList.get(0)));
    }
}
