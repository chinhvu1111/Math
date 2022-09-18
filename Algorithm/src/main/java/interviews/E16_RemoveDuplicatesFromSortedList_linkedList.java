package interviews;

public class E16_RemoveDuplicatesFromSortedList_linkedList {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if(head==null){
            return null;
        }
        ListNode root=new ListNode(head.val);
        ListNode temp=root;

        while (head!=null){
            head=head.next;

            if(head!=null&&temp.val!= head.val){
                temp.next=new ListNode(head.val);
                temp=temp.next;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        //** Đề bài
        //- Remove những phần tử duplicate từ list đã cho
        //{1,1,2} --> {1,2}
        //** Bài này tư duy như sau:
        //- Ta tạo 2 temp variable --> So sánh (before, after) --> after sẽ không nối thêm vào before nếu (after == before)
        //
        //#Reference
        //- Remove Duplicates from Sorted List II
    }
}
