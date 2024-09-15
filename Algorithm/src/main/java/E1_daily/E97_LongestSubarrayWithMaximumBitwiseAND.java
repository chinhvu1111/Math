package E1_daily;

import java.util.Arrays;
import java.util.HashMap;

public class E97_LongestSubarrayWithMaximumBitwiseAND {

    public static int longestSubarray(int[] nums) {
        int max= 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int rs=1;
        int count=0;
        for (int num : nums) {
            if (num != max) {
                count = 0;
                continue;
            }
            count++;
            rs = Math.max(rs, count);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array nums of size n).
        //- Consider (a non-empty subarray) from nums that has (the maximum possible bitwise AND).
        //- In other words, (let k be the maximum value) of (the bitwise AND of any subarray of nums).
        // Then, only subarrays with (a bitwise AND equal to k) should be considered.
        //* Return (the length of the longest such subarray).
        //- The bitwise AND of an array is the bitwise AND of all the numbers in it.
        //- A subarray is (a contiguous sequence of elements) within an array.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^6
        //  + Length khá lớn ==> Time: O(n)
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: nums = [1,2,3,3,2,2]
        //Output: 2
        //Explanation:
        //The maximum possible bitwise AND of a subarray is 3.
        //The longest subarray with that value is [3,3], so we return 2.
        //- Đầu tiên phải tìm (MAX AND):
        //  + Thường khi AND thì sẽ ra smaller value
        //      + Khi AND với số < MAX
        //      ==> Sẽ có 1 số chữ số 0 mà ta thiếu --> Sẽ làm small result
        //  ==> Max thì sẽ max val của all elements == MAX (Toàn bộ phải == MAX)
        //
        //- Để 1 tập hợp có AND = k:
        //  + Tập hợp đó có 2 đầu == MAX để chắc chắn thu được kết quả == MAX
        //- Bài toán trỏ thành:
        //  + Tìm khoảng cách lớn nhất các số max liên tiếp giống nhau.
        //
        int[] nums = {1,2,3,3,2,2};
        System.out.println(longestSubarray(nums));
        //- Kinh nghiệm:
        //+ Stream array is slower than the traditional loop
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        //#Reference:
        //2038. Remove Colored Pieces if Both Neighbors are the Same Color
        //2044. Count Number of Maximum Bitwise-OR Subsets
        //2411. Smallest Subarrays With Maximum Bitwise OR
    }
}
