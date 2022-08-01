package interviews;

public class E100_SplitArrayLargestSum {
    public static int splitArrayDynamic(int[] nums, int m) {
        int n=nums.length;
        int dp[][]=new int[n][m+1];
        int prefixSum[]=new int[n];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i]=sum;
            dp[i][1]=sum;
        }

        for(int i=2;i<=m;i++){

            for(int j=i-1;j<n;j++){
                int min=Integer.MAX_VALUE;

                for(int h=j-1;h>=i-2;h--){
//                    int left=0;
//
//                    if(h>=1){
//                        left=prefixSum[h-1];
//                    }
                    min=Math.min(min, Math.max(prefixSum[j]-prefixSum[h], dp[h][i-1]));
                }
                dp[j][i]=min;
            }
        }

        return dp[n-1][m];
    }

    public int splitArrayBinarySearch(int[] nums, int m) {
        int n=nums.length;
        int right=1_000_000 *50;
        int left=0;

        while(left<right){

        }

        return 1;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{7,2,5,10,8};
        int m=2;
//        int arr[]=new int[]{1,2,3,4,5};
//        int m=2;
//        int arr[]=new int[]{1,4,4};
//        int m=3;
//        int arr[]=new int[]{2,3,1,2,4,3};
//        int m=5;
        System.out.println(splitArrayDynamic(arr, m));
    }
}
