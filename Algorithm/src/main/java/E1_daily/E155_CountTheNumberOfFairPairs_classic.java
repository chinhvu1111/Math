package E1_daily;

import java.util.Arrays;

public class E155_CountTheNumberOfFairPairs_classic {

    //Search >= expectedValue with min index
    // expectedValue <= min
    //- Because we need to find the range (min, max) such that:
    //  + Expected value in (min, max)
    //  => min>= expectedValue
    public static int lowerBoundSearchIndex(int index, int[] nums, int expectedVal){
        int low = index, high=nums.length-1;
        int rs=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]>=expectedVal){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    //Search <= expectedValue with min index
    // expectedValue >= max
    //- Because we need to find the range (min, max) such that:
    //  + Expected value in (min, max)
    //  => max<= expectedValue
    public static int upperBoundSearchIndex(int index, int[] nums, int expectedVal){
        int low = index, high=nums.length-1;
        int rs=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(nums[mid]<=expectedVal){
                rs=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n=nums.length;
        long rs=0;

        for(int i=0;i<n;i++){
            int lowerIndex = lowerBoundSearchIndex(i+1, nums, lower-nums[i]);
            if(lowerIndex==-1){
                continue;
            }
            int upperIndex = upperBoundSearchIndex(i+1, nums, upper-nums[i]);
            if(upperIndex==-1){
                continue;
            }
            rs+=upperIndex-lowerIndex+1;
//            System.out.printf("val: %s, %s %s\n", nums[i], lowerIndex, upperIndex);
        }
        return rs;
    }

    public static long countFairPairsTwoPointers(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        return lowerBound(nums, upper+1)-lowerBound(nums, lower);
    }

    // Calculate (the number of pairs) with sum (less than) `value`.
    public static long lowerBound(int [] nums, int value){
        int left=0, right=nums.length-1;
        long rs=0;

        while(left<right){
            //nums =        [1,7,9,2,5]
            //sorted nums = [1,2,5,7,9]
            //sum = 12
            //(1+9)<12
            int sum = nums[left]+nums[right];
            if(sum<value){
                rs+=right-left;
                left++;
            }else{
                right--;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given (a 0-indexed integer array nums of size n) and (two integers (lower) and (upper)),
        //* return the number of fair pairs.
        //A pair (i, j) is fair if:
        //  + 0 <= i < j < n, and
        //  + lower <= nums[i] + nums[j] <= upper
        //
        // Idea
        //1. Binary search
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //nums.length == n
        //-10^9 <= nums[i] <= 10^9
        //-10^9 <= lower <= upper <= 10^9
        //  + Time: O(n) or O(n*log(n))
        //
        //- Brainstorm
        //- A pair (i, j) is fair if:
        //  + 0 <= i < j < n, and
        //  + lower <= nums[i] + nums[j] <= upper
        //
        //- i,j are indices of the array
        //
        //Example 1:
        //
        //Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
        //Output: 6
        //Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
        //+ sort(nums) = [0,1,4,4,5,7]
        //+ [1,3],[1,4],[1,5]
        //- i<j:
        //  + we only need to loop from (right -> left)
        //nums =    [0,1,7,4,4,5]
        //indices = [0,1,2,3,4,5]
        //1 -> [2,5]
        //  + 2,4,4,5 => count = 3
        //- It is difficult that we cannot get (size of window time) [min,max]:
        //  + treeSet is not suitable for (the range of window time)
        //
        //- i<j hay j<i ==> Không quan trọng vì mình tìm (count of all of pairs) là được
        //Ex:
        //nums =    [0,1,7,4,4,5]
        //indices = [0,1,2,3,4,5]
        //- 1 -> [2,5]
        //  + index range = [2,5]
        //      + (Smallest index), (biggest index)
        //
        //- Special case:
        //
        //Ex:
        //nums =    [0,1,2,2,7,4,4,5]
        //indices = [0,1,2,2,4,4,5,7]
        //  + 2 -> [2,5] (low = 4, high=7)
        //- How to remove the duplication cases:
        //  + nums =    [2,2,2,2,3,3,4]
        //  + indices = [0,1,2,3,4,5,6]
        //  + 2 -> [2,3]
        //      + [0,1],...,[0,5]
        //  + 2 -> [2,3]
        //      + [1,2],...,[1,5]
        //- Find the lower bound with (index >=i+1)
        //  + Binary search with (i+1, high)
        //
        //- For lower bound:
        //Search >= expectedValue with min index
        // expectedValue <= min
        //- Because we need to find the range (min, max) such that:
        //  + Expected value in (min, max)
        //  => min>= expectedValue
        //
        //- For upper bound:
        //Search <= expectedValue with min index
        // expectedValue >= max
        //- Because we need to find the range (min, max) such that:
        //  + Expected value in (min, max)
        //  => max<= expectedValue
        //
//        int[] nums = {0,1,7,4,4,5};
        //0,1,4,4,5,7
//        int lower = 3, upper = 6;
        int[] nums = {1,7,9,2,5};
        //0,1,4,4,5,7
        int lower = 11, upper = 11;
        System.out.println(countFairPairs(nums, lower, upper));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(log(n))
        //- Time: O(n*log(n))
        //
        //2. Two pointers
        //2.0,
        //- Calculate (the number of pairs) with sum less than `value`.
        //- Formula:
        //  + The number of pairs between (min, max):
        //  = lower_bound(upper+1) - lower_bound(lower)
        //==> Dùng phương án loại trừ ( số cases (< max+1) - số case (< min))
        //* Main point:
        //- (nums[left] + nums[right]) < value
        //  + It means (nums[left] + nums[right-k]) < value
        //  Ex: [1,3,4] => [1,3],[1,4]
        //  + The size of the window can be calculated with the formula [right - (left + 1) + 1], which simplifies to right - left
        //  ==> rs+= right-left
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(log(n))
        //- Time: O(n*log(n))
        //
        System.out.println(countFairPairsTwoPointers(nums, lower, upper));
        //#Reference:
        //327. Count of Range Sum
        //1865. Finding Pairs With a Certain Sum
        //2006. Count Number of Pairs With Absolute Difference K
    }
}
