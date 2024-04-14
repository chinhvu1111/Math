package contest;

public class E70_CountAlternatingSubarrays {

    public static long countAlternatingSubarrays(int[] nums) {
        int n=nums.length;
        if(n==0){
            return 0;
        }
        long rs=1L;
        long[] dp=new long[n];
        dp[0]=1;

        for(int i=1;i<n;i++){
            if(nums[i]!=nums[i-1]){
                dp[i]=dp[i-1]+1;
                rs+=dp[i];
            }else{
                dp[i]=1;
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (a binary array nums).
        //We call a subarray alternating if (no two adjacent elements) in the subarray have (the same value).
        //* Return the number of alternating subarrays in nums.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //nums[i] is either 0 or 1.
        //
        //- Brainstorm
        //- Count số lượng subarray mà các phần tử cạnh nhau khác nhau
        //Ex:
        //nums = [1,0,1,0]
        //1+2+3+4
        //i=0: len=1
        //==> Tìm dần length ==> Cộng dồn lên thôi
        //
//        int[] nums = {0,1,1,1};
        int[] nums = {0};
        System.out.println(countAlternatingSubarrays(nums));
    }
}
