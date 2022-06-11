package interviews;

import java.util.ArrayList;
import java.util.List;

public class E15_LinkedListCycleII {

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

        //Key tư duy ở đây là:
        //1, Ta sẽ tiếp tục duyệt để check xem length của cycle là bao nhiêu?
        //cycleLength=getLengthCycle()
        //2, Khi có cycle rồi thì có 1 điều ta cần lưu ý:
        //- Số nodes mà fast chạy nhiều hơn số node mà slow chạy chính là (x*lengthCycle) .
        //- Nên nếu mà slow chạy trước:
        // số nodes = độ dài cycle
        // ===> fast sẽ đuổi kịp slow ở ngay vị trí node mà xảy ra cycle (Tất nhiên đó sẽ là điểm giao)
        System.out.println(detectCycle(listNodeList.get(0)));
    }
}
