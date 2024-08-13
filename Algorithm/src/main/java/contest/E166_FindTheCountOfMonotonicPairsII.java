package contest;

public class E166_FindTheCountOfMonotonicPairsII {
    public static int countOfPairs(int[] nums) {
        int n=nums.length;
        int maxNum=0;

        for(int i=0;i<n;i++){
            maxNum=Math.max(maxNum, nums[i]);
        }
        long[][] dp=new long[n][maxNum+1];

        for(int i=0;i<=nums[0];i++){
            dp[0][i]=1;
        }
        //2000
        for(int i=1;i<n;i++){
            //Chọn
            //3 = 2+1
            //==> 2 ==> Gắn với cái gì đằng trước
            //- a,b
            //+ a tăng dần
            //+ b giảm dần
            //a == j ==> a đằng trước <= a hiện tại
            //1000
            long[] prefix=new long[maxNum+1];
            long sum=0;
            for(int j=maxNum;j>=0;j--){
                if(nums[i-1]>=j){
                    prefix[j]=(sum+dp[i-1][nums[i-1]-j])%1_000_000_007;
                }
//                prefix[j]=(sum+dp[i-1][j])%1_000_000_007;
            }
            long curRs1=0;
            for(int j=0;j<=nums[i];j++){
                long curRs=0;
                for(int k=0;k<=j;k++){
                    if(nums[i-1]-k>=nums[i]-j){
                        curRs=(curRs+dp[i-1][k])%1_000_000_007;
                    }
                }
//                curRs1=(prefix[nums[i]-j])%1_000_000_007;
                curRs1=(prefix[nums[i]-j])%1_000_000_007;
                dp[i][j]=curRs;
            }
//            for(int j=0;j<=nums[i];j++){
//                //- j=x
//                //  + k: 0 --> x
//                //      + nums[i-1]-k >= nums[i]-j
//                //          + Tìm k có cần không:
//                //              + Cần vì ta cần (k <=j)
//                curRs=(curRs+dp[i-1][j])%1_000_000_007;
//                dp[i][j]=curRs;
//            }
        }
        int rs=0;
        for(int i=0;i<=nums[n-1];i++){
            rs= (int) ((rs+dp[n-1][i]%1_000_000_007)%1_000_000_007);
        }
        return rs;
    }

//    public static int countMonotonicPairsWrongGpt(int[] nums) {
//        int n = nums.length;
//        int mod = 1_000_000_007;
//
//        // Find the maximum value in nums to define the range
//        int maxNum = 0;
//        for (int num : nums) {
//            maxNum = Math.max(maxNum, num);
//        }
//        long[][] dp = new long[n][maxNum + 1];
//        for (int i = 0; i <= nums[0]; i++) {
//            dp[0][i] = 1; // arr1[0] can be any value from 0 to nums[0]
//        }
//        for (int i = 1; i < n; i++) {
//            long[] prefixSum = new long[maxNum + 1];
//            prefixSum[0] = dp[i - 1][0];
//            for (int j = 1; j <= maxNum; j++) {
//                prefixSum[j] = (prefixSum[j - 1] + dp[i - 1][j]) % mod;
//            }
//
//            for (int j = 0; j <= nums[i]; j++) {
//                int arr2 = nums[i] - j;
//                if (arr2 >= 0 && arr2 <= maxNum) {
//                    dp[i][j] = prefixSum[j]; // Sum of all dp[i-1][k] where k <= j
//                }
//            }
//        }
//        // Sum up all valid pairs for the last element
//        int rs = 0;
//        for (int i = 0; i <= maxNum; i++) {
//            rs = (int) ((rs + dp[n - 1][i]) % mod);
//        }
//
//        return (int) rs;
//    }

    public static int countOfPairsOptimization(int[] nums) {
        int n=nums.length;
        long[][] dp=new long[n][1001];
        long[] pref=new long[1002];
        int mod=1_000_000_007;

        for(int i=n-1;i>=0;i--){
            long[] temp=new long[1002];

            for(int j=0;j<=nums[i];j++){
                if(i==n-1){
                    dp[i][j]=1;
                }else{
                    if(j>nums[i+1]){
                        temp[j+1]=temp[j]+dp[i][j];
                        continue;
                    }
                    int l = Math.max(j, nums[i+1]-(nums[i]-j));
                    dp[i][j]=(dp[i][j]+pref[nums[i+1]+1]-pref[l])%mod;
                }
                temp[j+1]=temp[j]+dp[i][j];
            }
            pref=temp;
        }
        int rs=0;

        for(int i=0;i<=nums[0];i++){
            rs= (int) ((rs+dp[0][i])%mod);
        }
        return rs;
    }
    public static void main(String[] args) {
//        int[]  nums = {5,5,5,5};
        int[]  nums = {2,3,2};
//        System.out.println(countMonotonicPairsWrongGpt(nums));
        System.out.println(countOfPairs(nums));
        System.out.println(countOfPairsOptimization(nums));
        //
        //* Requirement
        //
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //** Brainstorm
        //* Definition:
        //- dp[i][j] is the number of valid array pairs starting from index i, and arr1[i] = j
        //Transition:
        //- dp[i][j] = sum(dp[i+1][k]) for all valid arr1[i+1] = k given arr1[i] = j
        //
        //#Reference:
        //187. Repeated DNA Sequences
        //2792. Count Nodes That Are Great Enough
        //1339. Maximum Product of Splitted Binary Tree
    }
}
