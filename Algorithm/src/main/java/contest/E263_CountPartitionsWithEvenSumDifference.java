package contest;

public class E263_CountPartitionsWithEvenSumDifference {

    public static int countPartitions(int[] nums) {
        int n=nums.length;
        int sum=0;
        for (int i = 0; i < n; i++) {
            sum+=nums[i];
        }
        int curSum=0;
        int rs=0;

        for (int i = 0; i < n-1; i++) {
            curSum+=nums[i];
            if((Math.abs(sum-2*curSum))%2==0){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //You are given an integer array nums of length n.
        //A partition is defined as an index i where 0 <= i < n - 1, splitting the array into two non-empty subarrays
        // such that:
        //  + Left subarray contains indices [0, i].
        //  + Right subarray contains indices [i + 1, n - 1].
        //* Return the number of partitions where the difference between the sum of the left and right subarrays is even.
        int[] nums = {10,10,3,7,6};
        System.out.println(countPartitions(nums));
    }
}
