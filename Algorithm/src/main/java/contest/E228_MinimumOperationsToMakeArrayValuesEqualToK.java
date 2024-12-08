package contest;

import java.util.Arrays;

public class E228_MinimumOperationsToMakeArrayValuesEqualToK {

    public static int minOperations(int[] nums, int k) {
        Arrays.sort(nums);
        if(nums[0]<k){
            return -1;
        }
        int n=nums.length;
        int rs=0;
        int prevVal=nums[n-1];
        if(prevVal>k){
            rs=1;
        }

        for(int i=n-2;i>=0&&nums[i]>k;i--){
            if(prevVal!=nums[i]){
                rs++;
            }
            prevVal=nums[i];
        }
        return rs;
    }

    public static void main(String[] args) {
        //You are given an integer array nums and an integer k.
        //An integer h is called valid if all values in the array that are strictly greater than h are identical.
        //For example, if nums = [10, 8, 10, 8], a valid integer is h = 9 because all nums[i] > 9 are equal to 10, but 5 is not a valid integer.
        //- You are allowed to perform the following operation on nums:
        //  + Select an integer h that is valid for the current values in nums.
        //  + For each index i where nums[i] > h, set nums[i] to h.
        //Return the minimum number of operations required to make every element in nums equal to k.
        // If it is impossible to make all elements equal to k, return -1.
        //
        //Example 1:
        //
        //Input: nums = [5,2,5,4,5], k = 2
        //Output: 2
        //Explanation:
        //The operations can be performed in order using valid integers 4 and then 2.
        //5,2,5,4,5
        //=> h=4
        //(4),2,(4,4,4)
        //=> h=2
        //2,2,2,2,2
        //
        //sort(5,2,5,4,5)
        //= 2,4,5,5,5
        //
        //nums = [2,1,2], k=2
        //sort = 1,2,2
        //
        //k=2
        //2,2,2,2
//        int[] nums = {5,2,5,4,5};
//        int k = 2;
//        int[] nums = {9,7,5,3};
//        int k = 1;
        int[] nums = {2};
        int k = 1;
        System.out.println(minOperations(nums, k));
    }
}
