package contest;

public class E80_FindTheNumberOfSubarraysWhereBoundaryElementsAreMaximum {

    public static long numberOfSubarrays(int[] nums) {
        int rs=0;
        int n=nums.length;
        int start=0,end=0;
        int curMax=nums[0];

        for(int i=1;i<n;i++){

        }
        return 0L;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array of (positive) integers nums.
        //* Return the number of subarrays of nums,
        // where the (first) and the (last) elements of the subarray are equal to (the largest element) in the subarray.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //
        //- Brainstorm
        //Ex:
        //nums = [1,4,3,3,2]
        //- Có cases:
        // + a == c
        // + c == b
        // (a,...,c,...,b)
        // (3,1,2,3,0,2,3)
        //  + Nó thành tìm các số giống nhau + là max của 1 collection
        //Ex:
        //nums = [7,5,4,(3),1,2,(3),0,2,(3),7,8]
        //-
        //
    }
}
