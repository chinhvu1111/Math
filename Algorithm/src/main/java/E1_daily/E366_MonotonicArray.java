package E1_daily;

public class E366_MonotonicArray {

    public static boolean isMonotonic(int[] nums) {
        int n=nums.length;
        int max=Integer.MIN_VALUE;
        int max1=Integer.MIN_VALUE;
        boolean isIncrease=true;
        boolean isDecrease=true;

        for (int i = 0; i < n; i++) {
            if(nums[i]<max){
                isIncrease=false;
            }
            if(nums[n-i-1]<max1){
                isDecrease=false;
            }
            max=Math.max(max, nums[i]);
            max1=Math.max(max1, nums[n-i-1]);
        }
        return isIncrease||isDecrease;
    }

    public static void main(String[] args) {
        //** Requirement
        //- An array is monotonic if it is either monotone increasing or monotone decreasing.
        //- An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j].
        //- An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].
        //- Given an integer array nums, return true if the given array is monotonic, or false otherwise.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(n)
        //
        int[] nums = {1,2,2,3};
        System.out.println(isMonotonic(nums));
    }
}
