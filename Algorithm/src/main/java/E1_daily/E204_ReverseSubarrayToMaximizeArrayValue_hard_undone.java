package E1_daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class E204_ReverseSubarrayToMaximizeArrayValue_hard_undone {

    public static int maxValueAfterReverse(int[] nums) {
        int n= nums.length;
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        int sum=0;
        int rs=Integer.MIN_VALUE;

        for(int i=0;i<n-1;i++){
            sum+=Math.abs(nums[i]-nums[i+1]);
            rs=Math.max(rs, Math.abs(nums[0] - nums[i+1]) - Math.abs(nums[i] - nums[i+1]));
            rs=Math.max(rs, Math.abs(nums[n-1] - nums[i]) - Math.abs(nums[i] - nums[i+1]));
            min=Math.min(min, Math.max(nums[i], nums[i+1]));
            max=Math.max(max, Math.min(nums[i], nums[i+1]));
        }
        return sum+Math.max(rs, (max-min)*2);
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are (given an integer array nums).
        //- The value of this array is defined as (the sum of |nums[i] - nums[i + 1]|) for all (0 <= i < nums.length - 1).
        //- You are allowed to select (any subarray of the given array) and (reverse it).
        //- You can perform this operation (only once).
        //* Find (maximum possible value) of (the final array).
        //
        //Example 1:
        //
        //Input: nums = [2,3,1,5,4]
        //Output: 10
        //Explanation: By reversing the subarray [3,1,5] the array becomes [2,5,1,3,4] whose value is 10.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= nums.length <= 3 * 10^4
        //-10^5 <= nums[i] <= 10^5
        //The answer is guaranteed to fit in (a 32-bit integer).
        //
        //- Brainstorm
        //Ex:
        //nums = [2,3,1,5,4]
        //arr = [2,5,1,3,4]
        //= 3+4+2+1 = 10
        //rs = 10
        //- Reverse any subarray to get (max sum)
        //- prefixSum = [0,1,3,7,8]
        //- suffixSum = [10,7,3,1,0]
        //
        //- Sum between (i) and (j) that is not change (after reversing)
        //- The difference is:
        //  + a,b,c,d => a,c,b,d
        //      + |c-a|+|b-d|
        //      + |b-a|+|d-c|
        //
        //- Swap 2 numbers such that:
        //  + Sum is max
        //
        //nums = [2,(3),1,5,4]
        //- Find (the element on the right) such that:
        //  + |x-2|+|y-3| - |3-2| - |x-y|
        //  => MAX
        //
        //Ex:
        //a,b,c,d
        //  + a<=b, c>=d
        //      + sum = b-a+c-d
        //      + diff = |c-a|+|b-d| - b + a - c + d
        //          + b>=c
        //          diff = |c-a|+b-d - b + a - c + d
        //               = |c-a| + a-c
        //          + b<=c:
        //          diff = c-a+ |b-d| - b + a - c + d
        //               = |b-d| - b + d
        //  + a<=b, c<=d
        //      + sum = b-a+d-c
        //      + diff = |c-a|+|b-d| - b + a - c + d
        //          + a>=d:
        //          diff = |b-a| -b +d
        //          + a<=d:
        //          diff = |c-a| + a - c
        //  + a>=b, c<=d
        //      + sum = a-b+d-c
        //  + a>=b, c>=d
        //      + sum = a-b+c-d
        //
        //- |x-y|= max(y-x, x-y)
        //- a,b,c,d
        //  + added_val = max(a-c, c-a) + max(b-d, d-b) - max(a-b, b-a) - max(c-d,d-c)
        //- Cases:
        //  + a<=b<=c<=d
        //  + a<=b<=c>=d
        //  + a>=b<=c<=d
        //  + a>=b<=c>=d
        //  + a>=b>=c>=d
        //  + a<=b>=c>=d
        //  + a>=b>=c<=d
        //==> Chia cases lung tung không cần thiết
        //
        //- Ở đây mình cố chuyển phần đuôi thành constant:
        //- Nên mới assume điều kiện:
        //- a>=c, b>=d
        //  + added_val = a-c + b-d - constant (Ý là tính được)
        //- a>=c, b<=d
        //  + added_val = a-c + d-b - constant
        //- a<=c, b<=d
        //  + added_val = c-a + d-b - constant
        //- a<=c, b>=d
        //  + added_val = c-a + b-d - constant
        //==> Không ra quy luật gì nếu keep constant
        //
        //- Ở đây mình sẽ assume điều kiện để nó ra được công thức (ở vế SAU KHI CHANGE):
        //- a,b,c,d
        //  + added_val = max(a-c, c-a) + max(b-d, d-b) - max(a-b, b-a) - max(c-d,d-c)
        //==> Thay a,b,c,d bằng index
        //
        //+ a>=c, b>=d:
        //  + added_val = a-c + b-d - max(a-b, b-a) - max(c-d,d-c)
        //- CASE 1
        //+ a[L - 1] <= a[R] and a[L] <= a[R + 1]:
        //The sum equals,
        //S - abs(a[L] - a[L - 1]) - abs(a[R] - a[R + 1]) + (a[R] - a[L - 1]) + (a[R + 1] - a[L])
        //
        //It can be written as:
        //S + (-abs(a[L] - a[L - 1]) - a[L] - a[L - 1]) + (-abs(a[R] - a[R + 1]) + a[R] + a[R + 1])
        //
        //Let X = (-abs(a[L] - a[L - 1]) - a[L] - a[L - 1]) and Y = (-abs(a[R] - a[R + 1]) + a[R] + a[R + 1])
        //
        //- Then it can be written as:
        //  + S + X + Y
        //
        //* more optimization
        //+ for X If( a[L] >= a[L-1])
        //  => MIN nhưng chọn MAX(a[L-1, a[L])
        //X = - a[L] + a[L - 1] - a[L] - a[L - 1]
        //X = -2* a[L] where negative sign says it should be minimum
        //
        //- Same optimization can be applied for Y
        //  + finally X + Y = -2 * a[L] + 2 * a[R]
        //  => MAX nhưng chọn MIN(a[R, a[R+1])
        //
        //* Công thức trên sẽ không đúng khi:
        //  + L=1 ==> L-1 not exists
        //  + R=n-1 ==> R+1 not exists
        //
        //- If we reverse all element from A[0] to A[i],
        //  + we will improve abs(A[0] - b) - abs(a - b)
        //
        //- If we reverse all element from A[i+1] to A[n-1],
        //  + we will improve abs(A[n - 1] - a) - abs(a - b)
        //
        //1.1, Special cases:
        // num = [2,5,1,3,4]
        //output:   10
        //expected: 11
        // num = [3,1,5,2,4]
        //2+4+3+2 = 11
        //
//        int[] nums = {2,3,1,5,4};
        int[] nums = {2,5,1,3,4};
        //
        System.out.println(maxValueAfterReverse(nums));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        //#Reference:
        //1670. Design Front Middle Back Queue
        //929. Unique Email Addresses
        //1883. Minimum Skips to Arrive at Meeting On Time
    }
}
