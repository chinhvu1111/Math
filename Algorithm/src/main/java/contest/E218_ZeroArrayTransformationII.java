package contest;

public class E218_ZeroArrayTransformationII {

    public static boolean isZeroArray(int[] nums, int[][] queries, int k) {
        int n=nums.length;
        int[] prefixSum=new int[n+1];
        int[] rs=new int[n+1];

        for(int i=0;i<=k;i++){
            int[] q=queries[i];
            prefixSum[q[0]]-=q[2];
            if(q[1]+1<n){
                prefixSum[q[1]+1]+=q[2];
            }
        }
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=prefixSum[i];
            rs[i]=nums[i]+sum;
            if(rs[i]<0){
                rs[i]=0;
            }
        }
        for(int i=0;i<n;i++){
            if(rs[i]!=0){
                return false;
            }
        }
        return true;
    }

    public static int minZeroArray(int[] nums, int[][] queries) {
        boolean isValid=true;
        //Special cases:
        for (int num : nums) {
            if (num != 0) {
                isValid = false;
            }
        }
        if(isValid){
            return 0;
        }
        int low=0, high=queries.length-1;
        int rs=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
//            System.out.printf("%s %s %s\n", low, mid, high);
            if(isZeroArray(nums, queries, mid)){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        if(rs!=-1){
            return rs+1;
        }
        return -1;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri, vali].
        //- Each queries[i] represents the following action on nums:
        //  + Decrement the value at each index in the range [li, ri] in nums by (at most) (val-i).
        //  + The amount by which each value is decremented can be (chosen independently) for (each index).
        //* A Zero Array is an array with all its elements equal to 0.
        //* Return the (minimum possible) (non-negative) (value of k),
        // such that after processing (the first k queries) in sequence, nums becomes (a Zero Array).
        // If no such k exists, return -1.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //0 <= nums[i] <= 5 * 10^5
        //1 <= queries.length <= 10^5
        //queries[i].length == 3
        //0 <= li <= ri < nums.length
        //1 <= vali <= 5
        //
        //** Brainstorm
        //
        //
//        int[] nums = {2,0,2};
//        int[][] queries = {{0,2,1},{0,2,1},{1,1,3}};
//        int[] nums = {5};
//        int[][] queries = {{0,0,5},{0,0,1},{0,0,3},{0,0,2}};
        int[] nums = {0};
        int[][] queries = {{0,0,2},{0,0,4},{0,0,4},{0,0,3},{0,0,5}};
        //0,2
        //+ mid = 1
        System.out.println(minZeroArray(nums, queries));
    }
}
