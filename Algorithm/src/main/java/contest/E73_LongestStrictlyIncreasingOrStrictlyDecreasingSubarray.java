package contest;

public class E73_LongestStrictlyIncreasingOrStrictlyDecreasingSubarray {

    public static int longestMonotonicSubarray(int[] nums) {
        int n=nums.length;
        if(n==0){
            return 0;
        }
        int incre=1;
        int decre=1;

        for(int i=1;i<n;i++){
            if(nums[i]>nums[i-1]){
                incre++;
            }else{
                incre=1;
            }
            if(nums[i]<nums[i-1]){
                decre++;
            }else{
                decre=1;
            }
        }
        return Math.max(incre, decre);
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array of integers nums.
        //* Return the length of the longest subarray of nums which is either strictly increasing or strictly decreasing.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //- Brainstorm
    }
}
