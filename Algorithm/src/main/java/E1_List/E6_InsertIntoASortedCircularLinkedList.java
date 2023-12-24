package E1_List;

public class E6_InsertIntoASortedCircularLinkedList {

    public static class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    };

    public static Node findFirstAndLastAndTailNodes(Node head){
        Node node=head;
        Node firstNode=null;

        while(node!=null&&node.next!=head){
            if(firstNode==null||firstNode.val>=node.val){
                firstNode=node;
            }
            node=node.next;
        }
        // 1 -> 2 -> 5 -> (3) -> [3] -> 3 -> 3 -> 1
        // [3] -> 3 -> [3] -> 3
        if(node!=null&&(firstNode==null||firstNode.val>=node.val)){
            firstNode=node;
        }
        node=head;

        while(node!=null&&node.next!=head){
            if(node.val==firstNode.val){
                break;
            }
            node=node.next;
        }
        return node;
    }

    public static Node getLast(Node node){
        Node firstNode=node;
        while(node!=null&&node.next!=firstNode){
            node=node.next;
        }
        return node;
    }

    public static Node insert(Node head, int insertVal) {
        Node newNode=new Node(insertVal);
        Node firstNode=findFirstAndLastAndTailNodes(head);
        //- Phải là node đầu tiên có value=x
        //  + Nếu ta đi từ (left -> right)
        //- Head có thể điền vào tuỳ ý
//        Node lastNode=nodeInfor[1];
        // System.out.println(firstNode);
//        System.out.println(lastNode);
//        Node tailNode=nodeInfor[2];
        Node prevNode=null;
        Node node=firstNode;
        Node lastNode=getLast(firstNode);

        if(firstNode==null){
            firstNode=newNode;
            firstNode.next=firstNode;
            head=firstNode;
            return head;
        }else if(firstNode==lastNode){
            firstNode.next=newNode;
            newNode.next=firstNode;
            return head;
        }
        boolean firstTravese=false;
        boolean isValid=false;

        while(node!=null&&(node!=firstNode||!firstTravese)){
            firstTravese=true;
            if(prevNode!=null){
                if((prevNode.val<=insertVal&&insertVal<=node.val)||(prevNode.val>=insertVal&&insertVal<=node.val)){
                    // System.out.printf("Prev node: %s, node: %s\n", prevNode.val, node.val);

                    //(3), 4, (5)
                    //(3), (5), 6
                    //3,4,5,5,5,7
//                    int val=node.val;
//                    Node prev=node;
//
//                    while(node.val==val){
//                        prev=node;
//                        node=node.next;
//                    }
//                    //1,2,3,3, insert(4)
//                    //= 1,2,3,(4),3 sai
//                    Node nextNode=prev.next;
//                    prev.next=newNode;
//                    newNode.next=nextNode;
                    prevNode.next=newNode;
                    newNode.next=node;
                    isValid=true;
                    break;
                }
            }else if(node.val>=insertVal){
                lastNode.next=newNode;
                newNode.next=firstNode;
                isValid=true;
                break;
            }
            prevNode=node;
            node=node.next;
        }
        if(!isValid&&prevNode!=null&&prevNode.val<=insertVal){
            Node nextNode=prevNode.next;
            prevNode.next=newNode;
            newNode.next=nextNode;
        }
//        if(prevNode!=null){
//            if((prevNode.val<=insertVal&&insertVal<=node.val)||(prevNode.val>=insertVal&&insertVal<=node.val)){
//                System.out.printf("Prev node: %s, node: %s\n", prevNode.val, node.val);
//
//                prevNode.next=newNode;
//                newNode.next=node;
//            }
//        }else if(node.val>=insertVal){
//            lastNode.next=newNode;
//            newNode.next=firstNode;
//        }
//        if(prevNode!=null){
//            if((prevNode.val<=insertVal&&insertVal<=node.val)||(prevNode.val>=insertVal&&insertVal<=node.val)){
//                System.out.printf("Prev node: %s, node: %s\n", prevNode.val, node.val);
//                prevNode.next=newNode;
//                newNode.next=node;
//            }
//        }else if(node!=null&&node.val>=insertVal){
//            newNode.next=node;
//            lastNode.next=node;
//        }
        return head;
    }

    public static Node insertRefactor(Node head, int insertVal) {
        Node newNode=new Node(insertVal);
        //Time : O(N)
        Node firstNode=findFirstAndLastAndTailNodes(head);
        Node prevNode=null;
        Node node=firstNode;
        //Time : O(N)
        Node lastNode=getLast(firstNode);

        if(firstNode==null){
            firstNode=newNode;
            firstNode.next=firstNode;
            head=firstNode;
            return head;
        }else if(firstNode==lastNode){
            firstNode.next=newNode;
            newNode.next=firstNode;
            return head;
        }
        boolean firstTravese=false;
        boolean isValid=false;

        //Time : O(N)
        while(node!=null&&(node!=firstNode||!firstTravese)){
            firstTravese=true;
            if(prevNode!=null){
                if(insertVal <= node.val){
                    prevNode.next=newNode;
                    newNode.next=node;
                    isValid=true;
                    break;
                }
            }else if(node.val>=insertVal){
                lastNode.next=newNode;
                newNode.next=firstNode;
                isValid=true;
                break;
            }
            prevNode=node;
            node=node.next;
        }
        if(!isValid){
            Node nextNode=prevNode.next;
            prevNode.next=newNode;
            newNode.next=nextNode;
        }
        return head;
    }

    public static void println(Node node){
        Node head=node;
        while(node!=null&&node.next!=head){
            System.out.printf("%s, ", node.val);
            node=node.next;
        }
        if(node!=null) System.out.printf("%s, ", node.val);
        System.out.println();
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- Given a circular linked list node is non-descending order
        //Ex:
        //head = [3,4,1]
        //  + non-decrease từ min chứ không từ head.
        //  + Given head nhưng cần keep sorted list từ min
        //- Write a function to insert a value "insertVal" to the list such that it remains a sorted circular list.
        //  + Nếu có nhiều giá trị --> return 1 trong số chúng
        //- Nếu list empty --> Create new node.
        //* Return head node after inserting
        //
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraints:
        //The number of nodes in the list is in the range [0, 5 * 104].
        //-10^6 <= Node.val, insertVal <= 10^6
        //+ N <= 5*10^4:
        //  + Ta có thể giải quyết vấn đề này trong O(N) --> O(N*log(N))
        //
        //- Brainstorm:
        //Ex:
        //head = [3,4,1], insertVal = 2
        //==> Insert vào:
        //head=[3,4,1,2] / [2,3,4,1]
        //==> Ta có thể insert nhiều places.
        //
        //- Tìm vị trí để điền ntn?
        //+ Dãy ban đầu có thể:
        //  + Tăng + rollback
        //  Ex: 2,3,4,1
        //  + Chỉ tăng
        //
        //- Số điền vào có thể:
        //  + Min trong dãy
        //  + Max trong dãy
        //  + Mid:
        //      + Mid ở đầu
        //      + Mid ở cuối
        //      + Mid ở điểm giao
        //          + Max của đầu (Lặp lại)
        //          + Min của dãy (Lặp lại)
        //
        //- Mid ở đầu:
        //+ a<=x<=b ==> Không cần a:
        //Ex: head= [3,4,1], inserVal=2
        //head= 3,4,1,(2)
        //
        //- Mid ở cuối:
        //+ a<=x<=b
        //Ex: head= [5,8,1,3], inserVal=2
        //=> head= [5,8,1,(2),3]
        //
        //- Mid ở giao:
        //+ a<=x<=b
        //Ex: head= [5,6,1,3], inserVal=6
        //=> head= [5,6,(6),1,3]
        //
        //+ a=>x<=b
        //Ex: head= [5,6,3,4], inserVal=7
        //=> head= [5,6,(2),3,4]
        //
        //- Các tư duy bên trên sai do ở đây:
        //- Head là vị trí tuỳ chọn đề bài cho, không phải vị trí bắt đầu non-decrease
        //- Vị trí bắt đầu chính là vị trí bắt đầu non-decrease
        //
        //- Idea
        //- Tìm vị trí bắt đầu non-decrease trước
        //- Sau đó sẽ xét cụ thể các cases có thể có:
        //+ Cases:
        //+ Có duy nhất 1 val:
        //Ex:
        //int[] heads={1};
        //int insertVal=0;
        //==> Cần check
        //+ Phần này cần check node=3 gần nhất về phía bên trái để có thể append node vào sau
        //Ex:
        //int insertVal=0;
        //int[] heads={3,3,3};
        //=> head= 3,3,3,0 : head thì vẫn thế nhưng ta append node=0 ngay sau
        //
        //+ Ta sẽ append vào giữa khi thoả mãn điều kiện: prevNode=3 < x < node=5
        //Ex:
        //int[] heads={1,3,5};
        //int insertVal=4;
        //
        //+ Ở đây mặc dù 6 ở cuối --> Nhưng 5.nextNode=1 ==> Thế nên vẫn thoả mãn
        //+ Ta cần phải check đến head -> back to (head)
        //  + Thế nên cần sử dụng variable=false để check
        //int[] heads={1,3,5};
        //int insertVal=6;
        //
        //+ Ngay cả 3,3,5 ==> Áp dụng điều kiện trên vẫn đúng
        //int[] heads={3,3,5};
        //int insertVal=0;
        //
        //+
        //int[] heads={2,3,5};
        //int insertVal=1;
        //  + head=2 : Ta add trước head là được + ta vẫn giữ nguyên head
        //Result= {1,2,3,5}
        //
        //1.1, Optimization
        //+ Ta sẽ refactor để giản lượng predicate
        //
        //1.2, Complexity
        //- N is the number of value of the heads array
        //- Space: O(1)
        //- Time : O(N)
        //
        //#Reference:
        //147. Insertion Sort List
        //
//        int[] heads={3,4,1};
//        int insertVal=2;
//        int[] heads={1};
//        int insertVal=0;
//        int[] heads={3,3,3};
//        int insertVal=0;
//        int[] heads={1,3,5};
//        int insertVal=4;
        int[] heads={1,3,5};
        int insertVal=6;
//        int[] heads={1};
//        int insertVal=2;
//        int[] heads={3,3,5};
//        int insertVal=0;
        //
        //(3), 4, (5)
        //(3), (5), 6
        //3,4,5,5,5,7
        Node root=new Node(heads[0]);
        Node node=root;

        for(int i=1;i<heads.length&&heads.length>=2;i++){
            Node newNode=new Node(heads[i]);
            node.next=newNode;
            node=newNode;
        }
        node.next=root;
//        root=insert(root, insertVal);
        root=insertRefactor(root, insertVal);
        println(root);
    }
}
