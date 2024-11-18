package E1_binary_search_topic;

import java.util.Arrays;

public class E25_MinimumLimitOfBallsInABag {

    public static int getMaxOperation(int[] nums, int maxBall, int n){
        int rs=0;

        for(int i=0;i<n;i++){
            rs+=(nums[i]+maxBall-1)/maxBall-1;
        }
        return rs;
    }

    public static int minimumSize(int[] nums, int maxOperations) {
        int n=nums.length;
        int low=1, high = Arrays.stream(nums).max().getAsInt();
        int rs=-1;

        while(low<=high){
            int mid = low+(high-low)/2;
            int curOperations = getMaxOperation(nums, mid, n);

            //We need more operations to get the current max
            //  ==> We need to reduce the number of operations
            if(curOperations>maxOperations){
                low=mid+1;
            }else{
                rs=mid;
                high=mid-1;
            }
//            System.out.printf("mid: %s, low: %s, high: %s, count: %s\n", mid, low, high, curOperations);
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given an integer array nums where the ith bag contains nums[i] balls.
        //  + You are also given an integer (maxOperations).
        //- You can perform the following operation (at most) (maxOperations times):
        //  + Take (any bag of balls) and divide it into (two new bags) with (a positive number of balls).
        //  For example, a bag of 5 balls can become two new bags of (1 and 4 balls), or two new bags of (2 and 3 balls).
        //- Your penalty is (the maximum number of balls) in a bag.
        //  + You want to minimize your (penalty) (after the operations).
        //* Return the minimum (possible penalty) (after performing the operations).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //1 <= maxOperations, nums[i] <= 10^9
        //  + length<=10^5 => Time: O(n) or O(n*log(n))
        //  + nums[i] <= 10^9 ==> Long + binary search??
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: nums = [9], maxOperations = 2
        //Output: 3
        //Explanation:
        //- Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
        //- Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
        //The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.
        //- nums = [9], maxOperations = 2
        //  + If we split a half of 9 = 5+4
        //  => If we continue split 5 = 3+2
        //  => rs = max(4,2,3) = 4 (Not optimal)
        //
        //- Binary search:
        //  + max = x
        //  + How many way we need to split the array?
        //      + y>x:
        //          + low=mid+1
        //      + y<=x:
        //          + rs=y
        //          + high=max-1
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(log(n))
        //- Time: O(n*log(n))
        //
        int[] nums = {9};
        int maxOperations = 2;
        System.out.println(minimumSize(nums, maxOperations));
        //#Reference:
        //1820. Maximum Number of Accepted Invitations
        //2856. Minimum Array Length After Pair Removals
        //1733. Minimum Number of People to Teach
    }
}
