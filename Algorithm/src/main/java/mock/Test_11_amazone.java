package mock;

public class Test_11_amazone {

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

    //** Đề bài
    //- 2 list dạng reverse order của 1 số
    //- Tính tổng 2 số từ left --> right
    //** Tư duy
    //1.
    //1.1, Idea
    //- Dùng two pointer để tính p1,p2, số dư --> Đến cuối nếu dư thì nó sẽ số cuối/ Số sẽ được cộng thêm trung gian.

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root=null;
        int remainNum=0;
        int initVal=0;

        if(l1!=null){
            initVal+=l1.val;
            l1=l1.next;
        }
        if(l2!=null){
            initVal+=l2.val;
            l2=l2.next;
        }
        remainNum=initVal/10;
        initVal=initVal%10;
        root=new ListNode(initVal);
        ListNode tmp=root;

        while (l1!=null || l2!=null){
            int newVal=remainNum;

            if(l1!=null){
                newVal+=l1.val;
                l1=l1.next;
            }
            if(l2!=null){
                newVal+=l2.val;
                l2=l2.next;
            }
            remainNum=newVal/10;
            newVal=newVal%10;
            tmp.next=new ListNode(newVal);
            tmp=tmp.next;
        }
        if(remainNum!=0){
            tmp.next=new ListNode(remainNum);
        }
        return root;
    }

    public static void main(String[] args) {

    }
}
