package E1_daily;

public class E141_FindTheNumberOfSubarraysWhereBoundaryElementsAreMaximum {

    public static long numberOfSubarrays(int[] nums) {
        int n = nums.length;

        return 0L;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array of (positive integers nums).
        //* Return (the number of subarrays of nums),
        // where the (first and the last elements) of the subarray are equal to ((the largest element) in the subarray).
        //- We get the new subarray by using the (first, last) of the subarray
        //
        // Idea
        //1.
        //1.0, HashMap
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //  + n<=10^5 ==> Time: O(n)
        //  + nums[i] <=10^9 ==> Long
        //
        //- Brainstorm
        //Example 1:
        //Input: nums = [1,4,3,3,2]
        //Output: 6
        //Explanation:
        //
        //There are 6 subarrays which have the first and the last elements equal to the largest element of the subarray:
        //subarray [1,4,3,3,2], with its largest element 1. The first element is 1 and the last element is also 1.
        //subarray [1,4,3,3,2], with its largest element 4. The first element is 4 and the last element is also 4.
        //subarray [1,4,3,3,2], with its largest element 3. The first element is 3 and the last element is also 3.
        //subarray [1,4,3,3,2], with its largest element 3. The first element is 3 and the last element is also 3.
        //subarray [1,4,3,3,2], with its largest element 2. The first element is 2 and the last element is also 2.
        //subarray [1,4,3,3,2], with its largest element 3. The first element is 3 and the last element is also 3.
        //Hence, we return 6.
        //
        //- How to figure out (the largest value) between (first, last)
        //nums = [1,4,3,3,2]
        //[1,2,3,3,4]
        //- Xét đến vị trí (i):
        //  + Nếu đến vị trí (i) mà mình sort sẵn
        //  ==> nums[i] là largest number
        //  + Nhưng nếu sort thì sẽ bị mất order
        //
    }
}
