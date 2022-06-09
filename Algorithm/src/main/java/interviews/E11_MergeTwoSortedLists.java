package interviews;

public class E11_MergeTwoSortedLists {

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
    
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode point;
        ListNode root;

        if(list1==null&&list2!=null){
            return list2;
        }
        if(list2==null&&list1!=null){
            return list1;
        }
        if(list2 == null){
            return null;
        }

        if(list1.val>list2.val){
            root=new ListNode(list2.val);
            list2=list2.next;
            point=root;
        }else{
            root=new ListNode(list1.val);
            list1=list1.next;
            point=root;
        }

        while(list1!=null&&list2!=null){
            ListNode newNode;

            if(list1.val<list2.val){
                newNode=new ListNode(list1.val);
                list1=list1.next;
            }else{
                newNode=new ListNode(list2.val);
                list2=list2.next;
            }
            point.next=newNode;
            point=newNode;
        }
        if(list1!=null){
            point.next=list1;
        }else {
            point.next=list2;
        }
        return root;
    }

    public static void main(String[] args) {
        ListNode left=new ListNode(1);
        ListNode left1=new ListNode(2);
        ListNode left2=new ListNode(4);
        left.next=left1;
        left1.next=left2;

        ListNode right=new ListNode(1);
        ListNode right1=new ListNode(3);
        ListNode right2=new ListNode(4);

        right.next=right1;
        right1.next=right2;

        ListNode root=mergeTwoLists(left, right);
        while (root!=null){
            root=root.next;
            System.out.println(root.val);
        }
    }
}
