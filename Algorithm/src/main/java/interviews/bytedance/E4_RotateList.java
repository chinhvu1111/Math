package interviews.bytedance;

public class E4_RotateList {

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

    public static ListNode rotateRight(ListNode head, int k) {
        if(k==0){
            return head;
        }
        int length=0;
        ListNode temp=head;
        ListNode endNode=null;

        //1(1)->2(2)->3->5
        while (temp!=null){
            endNode=temp;
            temp=temp.next;
            length++;
        }
        k=k%length;
        if(k == 0){
            return head;
        }
        temp=head;
        int index=0;
        ListNode prevNode=null;
        while (temp!=null&&index<length-k){
            prevNode=temp;
            temp=temp.next;
            index++;
        }
        if(prevNode!=null){
            prevNode.next=null;
        }
        if(!endNode.equals(head)){
            endNode.next = head;
        }
        return temp==null?prevNode:temp;
    }

    public static void println(ListNode head){
        while (head!=null){
            System.out.printf("%s, ",head.val);
            head=head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //1,2,3,4,5
        //k=2
        //1->2->3->4->5
        //1->2->3->(4)->5
//        int[] nodes=new int[]{1,2,3,4,5};
//        int[] nodes=new int[]{0,1,2};
        //Case 0:
        //- Liên quan đến length=0 --> return head luôn
        //Case 1:
        //- Case này liên quan đến việc nó không có temp để return lại (temp==null)
        //- Cần phải check temp
//        int[] nodes=new int[]{1};
        //- Case này xảy ra cycle khi (k==length)
        //k=k%length (==0) --> Nối sẽ sinh ra cycle ngay
        int[] nodes=new int[]{1, 2};
        ListNode listNode=new ListNode(nodes[0]);
        ListNode temp=listNode;

        for(int i=1;i<nodes.length;i++){
            ListNode currentNode=new ListNode(nodes[i]);
            temp.next=currentNode;
            temp=currentNode;
        }
        println(listNode);
//        listNode=rotateRight(listNode, 2);
//        listNode=rotateRight(listNode, 4);
//        listNode=rotateRight(listNode, 1);
        listNode=rotateRight(listNode, 2);
        println(listNode);
        //#Reference
        //62. Unique Paths
        //189. Rotate Array
        //725. Split Linked List in Parts
    }
}
