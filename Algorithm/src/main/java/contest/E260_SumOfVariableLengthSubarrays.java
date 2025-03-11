package contest;

public class E260_SumOfVariableLengthSubarrays {

    public static int subarraySum(int[] nums) {
        int n=nums.length;
        int rs=0;

        for (int i = 0; i < n; i++) {
            int start=Math.max(0, i-nums[i]);
            for(int j=start;j<=i;j++){
                rs+=nums[j];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //You are given an integer array nums of size n. For each index i where 0 <= i < n,
        // define a subarray nums[start ... i] where start = max(0, i - nums[i]).
        //Return the total sum of all elements from the subarray defined for each index in the array.
        //A subarray is a contiguous non-empty sequence of elements within an array.
    }
}
