package E1_List;

import java.util.HashMap;
import java.util.HashSet;

public class E11_LinkedListComponents {

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

    public static int numComponentsMisleading(ListNode head, int[] nums) {
        HashMap<Integer, Integer> valToIndex=new HashMap<>();
        int n=nums.length;

        for(int i=0;i<n;i++){
            valToIndex.put(nums[i], i);
        }
        int rs=0;
        ListNode node=head;
        int prevIndex=-1;
        int count=0;

        while(node!=null){
            int curCount=0;
//            System.out.println(node.val);

            while(node!=null&&valToIndex.containsKey(node.val)&&valToIndex.get(node.val)>prevIndex){
                curCount=1;
                prevIndex=valToIndex.get(node.val);
                node=node.next;
            }
            if(curCount!=0){
                count+=curCount;
            }
            rs=Math.max(rs, count);

            if(node!=null&&(!valToIndex.containsKey(node.val))){
                node=node.next;
//                continue;
            }
//            if(node!=null&&valToIndex.containsKey(node.val)&&valToIndex.get(node.val)<=prevIndex){
//                count=0;
//            }
            prevIndex=-1;
        }
        return rs;
    }

    public static int numComponents(ListNode head, int[] nums) {
        HashSet<Integer> setVals=new HashSet<>();
        int n=nums.length;
        if(n==0){
            return 0;
        }

        for(int i=0;i<n;i++){
            setVals.add(nums[i]);
        }
        int rs=0;
        ListNode node=head;

//        if(setVals.contains(node.val)){
//            rs=1;
//            while(node!=null&&setVals.contains(node.val)){
//                node=node.next;
//            }
//        }
        while(node!=null){
//            System.out.println(node.val);
            int curCount=0;

            while(node!=null&&setVals.contains(node.val)){
                curCount=1;
                node=node.next;
            }
            rs+=curCount;
            if(node!=null){
                node=node.next;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given the head of a linked list containing (unique integer values)
        // and (an integer array nums) that is (a subset of the linked list values).
        //* Return (the number of connected components) in nums where (two values) are connected if they appear consecutively in (the linked list).
        //- Connected component (MISLEADING):
        //  + These elements are consecutive in the "FIRST LIST"
        //  + These elements follow the same order as the "second list"
        //- Connected component (MISLEADING):
        //  + These elements are consecutive in the "FIRST LIST" : Chỉ cần exists là được
        //  + Nếu chúng separate bởi 1 element mới --> rs++
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //The number of nodes in the linked list is n.
        //1 <= n <= 10^4
        //0 <= Node.val < n
        //All the values Node.val are unique.
        //1 <= nums.length <= n
        //0 <= nums[i] < n
        //All the values of nums are unique.
        //+ All of the numbers are "unique"
        //
        //- Brainstorm
        //- Store the index for each element in second array --> Check in the first list
        //  + Do unique --> hashmap dc
        //- Nếu next element có index > prev index:
        //  + Ta vẫn nhận là cùng collection <> rs++
        //
        //Connected components are group of elements in nums which occur consecutively in the list. For example, take the test case:
        //head = [1,2,0,4,3]
        //nums = [3,4,0,2,1]
        //When you traverse the list you find that every element you traverse consecutively is present in nums, hence the answer is 1.
        //If 5 was added say in between 0 and 4:
        //head = [1,2,0,5,4,3]
        //1,2,0 appear consecutively and are present in nums but 5 separates 0 and 4.
        // So 0 and 4 are not consecutive this time.
        // But 4,3 are consecutive and are present in nums. So we have two consecutive components and hence our answer is 2.
        //
        //- Point ở đây là case trên:
        //  + Tìm số tập hợp liên tiếp rời rạc ==> Nếu rời rạc thì + dồn lên số lượng <> keep count
        //- Mỗi lần chạy nếu vào được loop ==> Tìm được new connected component:
        //  + curCount=1
        //  ==> Đến cuối rs+=curCount
        //- Nếu mà node!=null:
        //  + Node is separate node ==> Ignore : node=node.next
        //
        //1.1, Optimization
        //- Vì main point là điểm "breaking node"
        //  ==> Ta có thể dùng flag để check node đằng trước có match hay không
        //  + Nếu prev node match + current node hiện tại không match:
        //      + rs++
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time : O(n)
        //
//        int[] head= {0,1,2,3};
//        int[] nums = {0,1,3};
        int[] head= {0,1,2,3,4};
        int[] nums = {0,3,1,4};
        //Expect: 2
        //  + (0,1), (3,4)
//        int[] head= {1,2,0,4,3};
//        int[] nums = {3,4,0,2,1};
//        int[] head= {0,1,2};
//        int[] nums = {1,0};
        int n=head.length;
        ListNode root=new ListNode(head[0]);
        ListNode node=root;

        for(int i=1;i<n;i++){
            ListNode newNode=new ListNode(head[i]);
            node.next=newNode;
            node=newNode;
        }
        System.out.println(numComponentsMisleading(root, nums));
        System.out.println(numComponents(root, nums));
        //#Reference:
        //1053. Previous Permutation With One Swap
        //2242. Maximum Score of a Node Sequence
        //2335. Minimum Amount of Time to Fill Cups
    }
}
