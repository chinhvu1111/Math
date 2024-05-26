package E1_two_pointers;

import java.util.Deque;
import java.util.LinkedList;

public class E16_RotateArray {

    public static void rotateWrong(int[] nums, int k) {
        int n=nums.length;
        k=k%n;
        int left=n-1;
        int right=0;

        for(int i=0;i<k;i++){
            right = (left+k)%n;

            int temp = nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
            left--;
        }
        for(int i=left;i<n-1;i++){
            int temp = nums[i+1];
            nums[i+1]=nums[i];
            nums[i]=temp;
        }
    }

    public static void rotate(int[] nums, int k) {
        int n=nums.length;
        k=k%n;

        //1,2,3,4,5,6,7, k = 3
        int left=0, right=n-k-1;

        while(left<right){
            int temp=nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
            left++;
            right--;
        }
        left=n-k;
        right=n-1;
        while(left<right){
            int temp=nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
            left++;
            right--;
        }
        left=0;
        right=n-1;
        while(left<right){
            int temp=nums[left];
            nums[left]=nums[right];
            nums[right]=temp;
            left++;
            right--;
        }
    }

    public static void rotateQueue(int[] nums, int k) {
        int n=nums.length;
        k=k%n;
        //1,2,3,4,5,6,7
        //7,1,2,3,4,5,6
        Deque<Integer> list=new LinkedList<>();
        for(int e: nums){
            list.add(e);
        }
        for(int i=0;i<k;i++){
            list.addFirst(list.removeLast());
        }
        for(int i=0;i<n;i++){
            nums[i]=list.removeFirst();
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (an integer array nums), rotate the array to the right by (k steps), where k is (non-negative).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //-2^31 <= nums[i] <= 2^31 - 1
        //0 <= k <= 10^5
        //
        //- Brainstorm
        //- k%n
        //Ex:
        //Input: nums = [(1),2,3,4,5,6,7], k = 3
        //Output: [5,6,7,(1),2,3,4]
        //Explanation:
        //rotate 1 steps to the right: [7,1,2,3,4,5,6]
        //rotate 2 steps to the right: [6,7,1,2,3,4,5]
        //rotate 3 steps to the right: [5,6,7,1,2,3,4]
        //==> k=7 thì keep
        //- k=k%n
        //
        //- Dùng 2 pointers
        //==> Space sẽ là O(1)
        //Ex:
        //Input: nums = [(1),2,3,4,5,6,7], k = 3
        //Output: [5,6,7,(1),2,3,4]
        //- Gán ntn?
        //+ (1),2,3,4,5,6,7
        //+ 7,(1),2,3,4,5,6
        //+ 6,7,(1),2,3,4,5
        //+ 5,6,7,(1),2,3,4
        //==> Ta thấy là 1 <=> 5
        //=> (1),2,3,4,(5),6,7
        //
        //(1),2,3,4,5,6,7
        // (4),2,3,(1),5,6,7
        // 4,(5),3,1,(2),6,7
        // 4,5,(6),1,2,(3),7
        //==> replace kiểu này không đúng
        //
        //1,2,3,4,5,6,7
        //1,2,(7),4,5,6,(3)
        //1,(6),7,4,5,(2),3
        //(5),6,7,4,(1),2,3
        //5,6,7,(4),1,2,(3)
        //  ==> Đển đây là dừng vì move k ==> chỉ k phần tử được chuyển lên đầu
        //  <> Nếu làm tiếp thì sẽ sai: 5,6,7,(3),1,2,(4)
        //  ==> Đến đây đơn giản là swap đôi một là được
        //  4,1,2,3
        //  + 4 <=> 1
        //      + 1,4,2,3
        //  + 4 <=> 2
        //      + 1,2,4,3
        //  + 4 <=> 3
        //      + 1,2,3,4
        //==> OK
        //
        //* Reference:
        //Let the array be - 123456789 and k = 4
        //- Tận dụng cơ chế reverse:
        //  ==> Size của array ở cuối + reverse => Quay lại ban đầu
        //- Reverse 2 đoạn (0,n-k-1),(n-k,n-1)
        //- Reverse all 1 đoạn (0,n-1)
        //+ Step 1 - 12345 6789 ---> 54321 6789
        //+ Step 2 - 54321 6789 ---> 54321 9876
        //+ Step 3 - 543219876 ---> 678912345
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the size of the array.
        //- Space : O(1)
        //- Time : O(n)
        //
        //#Reference:
        //2607. Make K-Subarray Sums Equal
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
//        int[] nums = {3,-1,-100,99};
//        int k = 2;
//        rotateWrong(nums, k);
        rotate(nums, k);
//        rotateQueue(nums, k);
        for (int i = 0; i < nums.length; i++) {
            System.out.printf("%s, ",nums[i]);
        }
        System.out.println();
    }
}
