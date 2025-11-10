package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;

public class E311_CountSubarraysWhereMaxElementAppearsAtLeastKTimes {

    public static long countSubarrays(int[] nums, int k) {
        long rs=0;
        int start=0;
        int n=nums.length;
        int max= Integer.MIN_VALUE;
        int countMaxElement=0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        for(int i=0;i<n;i++){
            if(nums[i]==max){
                countMaxElement++;
            }
            while(countMaxElement==k){
                if(nums[start]==max){
                    countMaxElement--;
                }
                start++;
            }
            rs+=start;
        }
        return rs;
    }

    public static long countSubarraysReference(int[] nums, int k) {
        // Finding the maximum element in the array

        int maxElement = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maxElement) {
                maxElement = nums[i];
            }
        }

        ArrayList<Integer> indexesOfMaxElements = new ArrayList<>();
        long ans = 0;

        // Iterating through the array

        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == maxElement) {
                indexesOfMaxElements.add(index);
            }

            int freq = indexesOfMaxElements.size();
            if (freq >= k) {
                ans += indexesOfMaxElements.get(freq - k) + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array nums) and (a positive integer k).
        //* Return the number of subarrays where (the maximum element of nums) appears (at least k times) in that subarray.
        //- A subarray is a contiguous sequence of elements within an array.
        //  + It is hard if we don't use (the maximum element of nums)
        //
        //Example 1:
        //
        //Input: nums = [1,3,2,3,3], k = 2
        //Output: 6
        //Explanation: The subarrays that contain the element 3 at least 2 times are:
        // [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^6
        //1 <= k <= 10^5
        //  + Time: nums.length <= 10^5 ==> O(n)
        //
        //* Brainstorm:
        //
        //Ex:
        //Input: nums = [1,3,2,3,3], k = 2
        //Output: 6
        //- For each (index=i):
        //  + Find the max left index such that:
        //      + (left_index, i) is the valid subarray
        //
        //Input: nums = [1,3,2,3,3,6,7,9], k = 2
        //nums = [(1,3,2,3),3,6,7,9]
        //  + nums = [(1,3,2,3,3),6,7,9]
        //nums = [(1,3,2,3,3),6,7,9]
        //  + nums = [1,3,2,(3,3),6,7,9]
        //- a...(b...c)...d
        //  + (b...c) is valid ==> (a...d) is valid
        //- a...(b...c...d)...e
        //  + (b...d) is valid
        //      + add(e) ==> (c...e) will be valid if we cut (b...c)
        //==> WRONG
        //- Just count(max)
        //- Slide window
        //
        //1.1, Case
        //
        //
        //* Main point:
        //
        //
        //1.2, Optimization
        //- Other ways, we can use the frequence list
        //  + To get the index
        //
        //1.3, Complexity
        //
        //
        int[] nums = {1,3,2,3,3};
        int k = 2;
        System.out.println(countSubarrays(nums, k));
        System.out.println(countSubarraysReference(nums, k));
    }
}
