package E1_daily;

import java.util.Arrays;

public class E19_SpecialArrayWithXElementsGreaterThanOrEqualX {

    public static int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;

        for(int i=0;i<n;i++){
            if(n-i<=nums[i]&&(i==0||nums[i-1]<n-i)){
                return n-i;
            }
        }
        return -1;
    }

    public static int specialArrayOptimization(int[] nums) {
        int n = nums.length;
        int[] freq=new int[n+1];

        for(int i=0;i<n;i++){
            freq[Math.min(n, nums[i])]++;
        }
        int sum=0;

        for(int i=n;i>=0;i--){
            sum+=freq[i];
            if(sum<=i&&sum>i-1){
                return sum;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array nums of (non-negative) integers.
        //- nums is considered special
        // if there exists (a number x) such that there are (exactly x numbers) in nums that are (greater than or equal to x).
        //- Notice that (x does not have) to be (an element) in nums.
        //* Return x if the array is special, otherwise, return -1.
        // It can be proven that if nums is special, the value for (x) is unique.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= nums.length <= 100
        //0 <= nums[i] <= 1000
        //- Kích thước số không quá lớn
        //
        //- Brainstorm
        //- (nums[i]>=x) + (số lượng số >=x) = x
        //  ==> return x
        //- How to find x?
        //Ex:
        //1,4,5,8,10
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(1)
        //- Time : O(n*log(n))
        //
        //2.
        //2.0, Xử lý trong O(n) time.
        //- Ta thấy:
        //  - (x<=nums[i]) + (số lượng nums[i]>=x) == x
        //      + Tức là x<=N
        //      ==> Số nào mà (nums[i]>=N) ==> Coi như là được count sẵn rồi.
        //  - 1 tip nữa:
        //      + Số lượng chữ số >= nums[i] chắc chắn sẽ <=N
        //- Point ở đây là mình không cần sort các elements
        //Ex:
        //nums = {3,6,7,7,8,0}
        //  x=4
        //+ 3:1
        //+ 6:4
        //+ 0:1
        //==> counting sort:
        //+ 6:4
        //+ 3:1
        //+ 0:1
        //==> Loop từ (last to first)
        //Ex:
        // nums= {0,4,3,0,4}
        //+ 4:2
        //+ 3:1
        //+ 0:2
        //
//        int[] nums=new int[]{3,5};
        int[] nums=new int[]{3,6,7,7,0};
        //+ 5:3
        //+ 3:1
        //+ 0:1
        //- sum+=freq[i]
        //- Sum sẽ cộng trước -> So sánh với i và (i-1)
        //  + sum<=i&&sum>i-1:
        //      + return sum
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n)
        //- Time : O(n)
        //
        //nums = {0,3,6,7,7}
//        int[] nums=new int[]{0,4,3,0,4};
        System.out.println(specialArray(nums));
        System.out.println(specialArrayOptimization(nums));
        //
        //#Reference:
        //2772. Apply Operations to Make All Array Elements Equal to Zero
        //2089. Find Target Indices After Sorting Array
        //581. Shortest Unsorted Continuous Subarray
        //2401. Longest Nice Subarray
        //2838. Maximum Coins Heroes Can Collect
        //2073. Time Needed to Buy Tickets
    }
}
