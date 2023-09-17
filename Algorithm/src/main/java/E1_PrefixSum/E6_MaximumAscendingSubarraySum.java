package E1_PrefixSum;

public class E6_MaximumAscendingSubarraySum {

    public static int maxAscendingSum(int[] nums) {
        //10,20,30,5,10,50
        //return 65
        int n=nums.length;

        if(n==0){
            return 0;
        }
        int rs=0;
        int sum=nums[0];

        for(int i=1;i<n;i++){
            if(nums[i]>nums[i-1]){
                sum+=nums[i];
                rs=Math.max(rs, sum);
            }else{
                sum=nums[i];
                rs=Math.max(rs, sum);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
    }
}
