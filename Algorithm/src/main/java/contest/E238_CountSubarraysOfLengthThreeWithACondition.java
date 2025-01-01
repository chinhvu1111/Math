package contest;

public class E238_CountSubarraysOfLengthThreeWithACondition {

    public static int countSubarrays(int[] nums) {
        int n=nums.length;
        int rs=0;

        for(int i=0;i+2<n;i++){
            if((nums[i]+nums[i+2])*2==nums[i+1]){
                rs++;
            }
        }
        return rs;
    }
    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array nums,
        //* return the number of subarrays of length 3 such that the sum of the first and
        // third numbers equals exactly half of the second number.
        //* A subarray is a contiguous non-empty sequence of elements within an array.
        //
        //
        // Idea
        //1. Rolling hash
        //1.0,
        // Method-1:
        //- Constraint
        //
        //
        //- Brainstorm
        //
        //
        //nums = [-1,-4,-1,4]
        //output: 2
        //rs: 1
    }
}
