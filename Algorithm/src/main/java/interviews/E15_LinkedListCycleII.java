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

        //Key t?? duy ??? ????y l??:
        //1, Ta s??? ti???p t???c duy???t ????? check xem length c???a cycle l?? bao nhi??u?
        //cycleLength=getLengthCycle()
        //2, Khi c?? cycle r???i th?? c?? 1 ??i???u ta c???n l??u ??:
        //- S??? nodes m?? fast ch???y nhi???u h??n s??? node m?? slow ch???y ch??nh l?? (x*lengthCycle) .
        //- N??n n???u m?? slow ch???y tr?????c:
        // s??? nodes = ????? d??i cycle
        // ===> fast s??? ??u???i k???p slow ??? ngay v??? tr?? node m?? x???y ra cycle (T???t nhi??n ???? s??? l?? ??i???m giao)
        System.out.println(detectCycle(listNodeList.get(0)));
    }
}
