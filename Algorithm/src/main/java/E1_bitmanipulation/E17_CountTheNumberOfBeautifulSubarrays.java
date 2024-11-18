package E1_bitmanipulation;

import java.util.HashMap;

public class E17_CountTheNumberOfBeautifulSubarrays {

    public static long beautifulSubarrays(int[] nums) {
        int n= nums.length;
        HashMap<Long, Integer> mapXorValCount=new HashMap<>();
        long xorVal=0;
        long rs=0;
        mapXorValCount.put(0L, 1);

        for(int i=0;i<n;i++){
            xorVal^=nums[i];
            int prevCount = mapXorValCount.getOrDefault(xorVal, 0);
            rs+=prevCount;
            mapXorValCount.put(xorVal, prevCount+1);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given (a 0-indexed integer array nums).
        //- In one operation, you can:
        //  + Choose (two different) (indices i and j) such that 0 <= i, j < nums.length.
        //  + Choose (a non-negative integer k) such that (the kth bit) (0-indexed) in the binary representation of (nums[i] and nums[j]) is 1.
        //  + (Subtract 2^k) from (nums[i] and nums[j]).
        //- A subarray is beautiful
        //  + if it is possible to make all of (its elements) equal to 0 after (applying the above operation) (any number of times).
        //* Return (the number of beautiful subarrays) in the array nums.
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        //- Choose (bit kth) of the nums[i] and nums[j]:
        //  + (bit val) == 1
        //  + (Subtract 2^k) from (nums[i] and nums[j]).
        //
        //Example 1:
        //
        //Input: nums = [4,3,1,2,4]
        //Output: 2
        //Explanation: There are 2 beautiful subarrays in nums: [4,(3,1,2),4] and [(4,3,1,2,4)].
        //- We can make all elements in the subarray [3,1,2] equal to 0 in the following way:
        //  - Choose [3, 1, 2] and k = 1. Subtract 21 from both numbers. The subarray becomes [1, 1, 0].
        //  - Choose [1, 1, 0] and k = 0. Subtract 20 from both numbers. The subarray becomes [0, 0, 0].
        //- We can make all elements in the subarray [4,3,1,2,4] equal to 0 in the following way:
        //  - Choose [4, 3, 1, 2, 4] and k = 2. Subtract 22 from both numbers. The subarray becomes [0, 3, 1, 2, 0].
        //  - Choose [0, 3, 1, 2, 0] and k = 0. Subtract 20 from both numbers. The subarray becomes [0, 2, 0, 2, 0].
        //  - Choose [0, 2, 0, 2, 0] and k = 1. Subtract 21 from both numbers. The subarray becomes [0, 0, 0, 0, 0].
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //0 <= nums[i] <= 10^6
        //  + Length <= 10^5 ==> Time: O(n) or O(n*log(n))
        //
        //- Brainstorm
        //- (Subtract 2^k) from (nums[i] and nums[j]).
        //  + Delete (kth) of the (nums[i], nums[j])
        //- We can delete kth bit of the any pair of nums[i] and nums[j]:
        //  + If only the number of kth bit is even
        //  ==> We just need to XOR (all of the elements)
        //  Xor (elements) == 0:
        //      + It is the beautiful array
        //
        //- We use prefix sum + (hashmap for count of xor val)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        int[] nums = {4,3,1,2,4};
        System.out.println(beautifulSubarrays(nums));
        //
        //#Reference:
        //2338. Count the Number of Ideal Arrays
    }
}
