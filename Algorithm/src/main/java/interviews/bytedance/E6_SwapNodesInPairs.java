package interviews.bytedance;

public class E6_SwapNodesInPairs {

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

    public static ListNode swapPairs(ListNode head) {
        //null->1->2->3->4
        //null->1<-2->3<-4
        //temp=1, tempNext=2
        //tempNext.next==3
        //
        //2->1->4->3
        ListNode temp=head;
        if(temp==null){
            return head;
        }
        ListNode tempNext=temp.next;
        ListNode prevTempNext=null;
        ListNode prevTemp=null;

        if(tempNext!=null){
            head=tempNext;
        }

        while (tempNext != null){
            //old: 1
            prevTemp=temp;
            //new: 3
            temp=tempNext.next;
            //old: 2
            prevTempNext=tempNext;
            //new: 4
            if(temp!=null){
                tempNext=temp.next;
            }else{
                tempNext=null;
            }
            //3->4
            //+ 4->3
            //+ (1)->4
            prevTempNext.next = prevTemp;
            prevTemp.next = tempNext;
        }
        if(temp!=null&&prevTemp!=null){
            prevTemp.next=temp;
        }
        return head;
    }

    public static void println(ListNode head){
        while (head!=null){
            System.out.printf("%s, ",head.val);
            head=head.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
//        int[] nodes=new int[]{1,2,3,4};
        //Case 1: Không có next node
//        int[] nodes=new int[]{1};
        //Case 2: Số node lẻ
        int[] nodes=new int[]{1,2,3};
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
//        listNode=removeNthFromEnd(listNode, 2);
        listNode=swapPairs(listNode);
        println(listNode);
        //#Reference
        //25. Reverse Nodes in k-Group
        //25. Reverse Nodes in k-Group
        //1721. Swapping Nodes in a Linked List
    }
}
