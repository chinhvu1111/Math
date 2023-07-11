package mock;

public class Test13_amazone {

    public static int twoSumLessThanK(int[] nums, int k) {
        int n=nums.length;
        int rs=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int currentSum=nums[i]+nums[j];
                if(currentSum<k){
                    rs=Math.max(rs, currentSum);
                }
            }
        }
        return rs==Integer.MIN_VALUE?-1:rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Cho k tìm max sum sao cho:
        //+ i<j
        //+ nums[i] + nums[j]=sum
        //+ sum <k
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //+ 1 <= nums.length <= 100
        //+ 1 <= nums[i] <= 1000
        //+ 1 <= k <= 2000
        //
        //-
    }
}
