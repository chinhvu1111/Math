package E1_daily;

import java.util.HashMap;

public class E305_BinarySubarraysWithSum_transform_problem {

    public static int numSubarraysWithSum(int[] nums, int goal) {
        HashMap<Integer, Integer> sumCount=new HashMap<>();
        int n=nums.length;
        int sum=0, rs=0;
        sumCount.put(0, 1);

        for(int i=0;i<n;i++){
            sum+=nums[i];
            int prevCount=sum-goal;
            if(sumCount.containsKey(prevCount)){
                rs+=sumCount.get(prevCount);
            }
            sumCount.put(sum, sumCount.getOrDefault(sum, 0)+1);
        }
        return rs;
    }

    private static int slidingWindowAtMost(int[] nums, int goal) {
        int start = 0, currentSum = 0, totalCount = 0;

        // Iterate through the array using a sliding window approach
        for (int end = 0; end < nums.length; end++) {
            currentSum += nums[end];

            // Adjust the window by moving the start pointer to the right
            // until the sum becomes less than or equal to the goal
            while (start <= end && currentSum > goal) {
                currentSum -= nums[start++];
            }

            // Update the total count by adding the length of the current subarray
            totalCount += end - start + 1;
        }
        return totalCount;
    }

    public static int numSubarraysWithSumRefer(int[] nums, int goal) {
        return slidingWindowAtMost(nums, goal) - slidingWindowAtMost(nums, goal - 1);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (a binary array nums) and (an integer goal),
        //* return (the number of non-empty subarrays) with (a sum goal).
        //- A subarray is a contiguous part of the array.
        //
        //Example 1:
        //
        //Input: nums = [1,0,1,0,1], goal = 2
        //Output: 4
        //Explanation: The 4 subarrays are bolded and underlined below:
        //[1,0,1,0,1]
        //[1,0,1,0,1]
        //[1,0,1,0,1]
        //[1,0,1,0,1]
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 3 * 10^4
        //nums[i] is either 0 or 1.
        //0 <= goal <= nums.length
        //
        //* Brainstorm:
        //Ex:
        //Input: nums = [1,0,1,0,1], goal = 2
        //Output: 4
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- slidingWindowAtMost(goal):
        //  + Nhiều nhất goal
        //  => rs+=end-start+1
        //- (score==goal) = slidingWindowAtMost(nums, goal) - slidingWindowAtMost(nums, goal - 1);
        //- nums[i] is 0 or 1
        //  ==> sum>=0
        //
        //1.3, Complexity
        //
        //
//        int[] nums = {1,0,1,0,1};
//        int goal = 2;
        int[] nums = {0,0,0,0,0};
        int goal = 0;
        System.out.println(numSubarraysWithSum(nums, goal));
        System.out.println(numSubarraysWithSumRefer(nums, goal));
        //
        //#Reference:
        //2302. Count Subarrays With Score Less Than K
        //2750. Ways to Split Array Into Good Subarrays
        //3129. Find All Possible Stable Binary Arrays I
    }
}
