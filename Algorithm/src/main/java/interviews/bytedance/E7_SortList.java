package interviews.bytedance;

public class E7_SortList {

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

    public static ListNode sortList(ListNode head) {
        ListNode temp=head;
        ListNode newHead=null;
        ListNode newLast=null;
        ListNode prevNode=null;

        while (temp!=null){
            if(count++%50000==0){
                System.out.println(temp.val);
            }
            ListNode node=temp.next;
            if(newHead==null){
                newHead=temp;
                temp.next=null;
            }else{
                if(newHead.val>=temp.val){
                    temp.next=newHead;
                    newHead=temp;

                }else if(newLast!=null&&newLast.val<=temp.val){
                    newLast.next=temp;
                    temp.next=null;
                    newLast=temp;
                }else {
                    ListNode startNode=newHead;
                    if(temp.val> prevNode.val){
                        startNode=prevNode;
                    }
                    ListNode afterNode=search(startNode, temp.val);
//                System.out.println(afterNode.val);
                    if(afterNode!=null&&afterNode.equals(newHead)&&temp.val<=afterNode.val){
                        temp.next=afterNode;
                        newHead=temp;
                    }else{
                        ListNode beforeNode=null;

                        if(afterNode!=null){
                            beforeNode=afterNode.next;
                        }
                        if(afterNode!=null){
                            afterNode.next=temp;
                        }
                        temp.next=beforeNode;
                    }
                }
                //Update new last
                if(newLast==null||newLast.val<temp.val){
                    newLast=findLastNode(newHead);
                }
//                System.out.println(newLast.val);
            }
            prevNode=temp;
            temp=node;
        }
        return newHead;
    }

    public static int count=0;

    public static ListNode search(ListNode head, int value){
        ListNode start=head;
        ListNode last=null;
        ListNode middleNode=null;
        ListNode prevNode=null;

        //1,1,3,6
        //- Tìm số lớn >4 nhưng mà min nhất
        //<=> Tìm số <4 nhưng max nhất ==> Kiểu này có vẻ tư duy dễ hơn.
        //Case 1:
        //- insert 4
        //- mid_value=3
        //+ 4> mid_value --> (6,6)
        //+
        //Case 2:
        //- Insert 2:
        //- mid_value=3
        //+ 2 < 3 --> (1,3)
        //
        //Chú ý:
        //- Với dạng bài này phải chọn chiến lược cho phù hợp.
        //==> Ở đây ta chỉ có thể tiến sang right 1 đơn vị
        //
        //Insert(4): 4<6 ==> return 6
        while (start!=null&&!start.equals(last)&&!(start.next!=null&&start.next.equals(last))){
            middleNode=findMiddleNode(start, last);
//            if(count++%10000==0){
//                assert last != null;
//                System.out.printf("%s %s %s",start.val, middleNode.val, last.val);
//            }
            if(middleNode!=null&&middleNode.val>value){
                //middleNode.val>value
                last=middleNode;
            }else{
                //Phần này ta sẽ dịch sang bên right
                //VD:
                //5,7,10
                //- Insert(6)
                //==> Ta sẽ lưu lại prevValue
                prevNode=middleNode;
                //middleNode.val<=value
                if(middleNode!=null){
                    start=middleNode.next;
                }
            }
        }
        if(prevNode==null){
            return start;
        }else if(start!=null&&prevNode.val<= start.val&&start.val<=value){
            return start;
        }
        return prevNode;
    }

    public static ListNode findMiddleNode(ListNode head, ListNode last){
        //1(node),2(nodeNext),5,7
        ListNode node=head;
        ListNode nodeNext=null;
        if(node!=null){
            nodeNext=node.next;
        }
        while (nodeNext!=null&&nodeNext!=last){
            node=node.next;
            if(nodeNext.next!=last){
                nodeNext=nodeNext.next.next;
            }else{
                break;
            }
        }
        return node;
    }

    public static ListNode findLastNode(ListNode head){
        ListNode temp=head;
        ListNode prevNode=null;

        while (temp!=null){
            prevNode=temp;
            temp=temp.next;
        }
        return prevNode;
    }

    public static void println(ListNode head){
        while (head!=null){
            System.out.printf("%s, ",head.val);
            head=head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nodes=new int[]{4,2,1,3};
//        int[] nodes=new int[]{-1,5,3,4,0};
//        int[] nodes=new int[]{4,19,14,5,-3,1,8,5,11,15};
//        int[] nodes=new int[]{4,19,14,5,-3,1,8,5,11,15};
//        int[] nodes=new int[]{2,3,4,5,6,7,8,9,10,11};
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
        listNode=sortList(listNode);
        println(listNode);
//        System.out.println(search(listNode, 5).val);
        //
        //Case 1:
        //1,2,3,4...10000
        //==> Việc tìm kiếm sẽ bị slow
        //- Case này cần tối ưu:
        //+ Tìm last node ít nhất có thể (Ở đây đã tối ưu chỉ last node ==null mới được tính lại lastnode)
        //Case 2:
        //100000,1,2,3,4,5
        //==> Tìm kiếm cũng bị slow --> Do cần tính middle liên tục
        //- Case này cần tối ưu:
        //- Start node lớn nhất về phía right
        //
        //#Reference :
        //Merge Two Sorted Lists
        //Sort Colors
        //149. Max Points on a Line
        //147. Insertion Sort List
        //2046. Sort Linked List Already Sorted Using Absolute Values
    }
}
