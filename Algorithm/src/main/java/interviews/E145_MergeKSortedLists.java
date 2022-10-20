package interviews;

public class E145_MergeKSortedLists {


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

    public static ListNode mergeTwoList(ListNode left, ListNode right){
        ListNode first=left;
        ListNode second=right;
        ListNode tempNode=null;
        ListNode root=null;

        while (first!=null&&second!=null){
            ListNode nextNode = null;

            if(first.val<second.val){
                ListNode tmp=first.next;
                first.next=null;
                nextNode=first;
                first=tmp;
            }else if(first.val>second.val){
                ListNode tmp=second.next;
                second.next=null;
                nextNode=second;
                second=tmp;
            }else{
                ListNode firstNode1=first.next;
                ListNode secondNode1=second.next;
                first.next=null;
                second.next=null;

                if(tempNode!=null){
                    tempNode.next=first;
                    tempNode=first;
                }else{
                    tempNode=first;
                    root=tempNode;
                }
                tempNode.next=second;
                tempNode=second;
                first=firstNode1;
                second=secondNode1;
                continue;
            }
            if(tempNode==null){
                tempNode=nextNode;
                root=tempNode;
            }else {
                tempNode.next=nextNode;
                tempNode=nextNode;
            }
        }
        if(first!=null){
            while (first!=null){
                if(tempNode!=null){
                    tempNode.next=first;
                    tempNode=first;
                }else {
                    tempNode=first;
                    root=tempNode;
                }
                first=first.next;
            }
        }
        if(second!=null){
            while (second!=null){
                if(tempNode!=null){
                    tempNode.next=second;
                    tempNode=second;
                }else {
                    tempNode=second;
                    root=tempNode;
                }
                second=second.next;
            }
        }
        return root;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        int n=lists.length;
        ListNode listNode=null;

        if(lists.length!=0){
            listNode=lists[0];
        }

        for (int i=1;i<n;i++){
            listNode=mergeTwoList(listNode, lists[i]);
        }
        print(listNode);
        return listNode;
    }

    public static void print(ListNode list){
        ListNode tempPrint=list;

        while (tempPrint!=null){
            System.out.printf("%s ", tempPrint.val);
            tempPrint=tempPrint.next;
        }
    }

    public static void main(String[] args) {
        //Case 1
        ListNode listNode=new ListNode(1);
        ListNode listNode1=new ListNode(4);
        ListNode listNode2=new ListNode(5);
        listNode.next=listNode1;
        listNode1.next=listNode2;

        ListNode listNode3=new ListNode(1);
        ListNode listNode4=new ListNode(3);
        ListNode listNode5=new ListNode(4);
        listNode3.next=listNode4;
        listNode4.next=listNode5;

        //Case 2:
//        ListNode listNode=new ListNode(1);
//        ListNode listNode1=new ListNode(4);
//        listNode.next=listNode1;
//
//        ListNode listNode3=new ListNode(1);
//        ListNode listNode4=new ListNode(3);
//        listNode3.next=listNode4;

        //Case 3:
        //ListNode listNode=new ListNode(1);
        //ListNode listNode3=new ListNode(2);

        //Case 4: Case 1 bên null --> cần phải gán lại root
//        ListNode listNode=null;
//        ListNode listNode3=new ListNode(2);

        ListNode[] lists=new ListNode[]{listNode, listNode3};

        mergeKLists(lists);
        //
        //** Đề bài
        //- Merge k list (Đã được sắp xếp tăng dần) lại thành 1 list --> Tăng dần.
        //
        //** Ta tư duy như sau:
        //1,
        //VD:
        //1(first),4,5
        //1(second),3,4
        //
        //1==1
        //List 1: 1,4(first),5
        //List 2: 1,3,4(second)
        //Result: 1->1(first)->3
        //
    }
}
