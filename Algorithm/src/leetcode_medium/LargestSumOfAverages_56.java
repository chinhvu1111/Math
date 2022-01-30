package leetcode_medium;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LargestSumOfAverages_56 {

    public static double largestSumOfAverages(int[] nums, int k) {
        int n=nums.length;
        double dp[][]=new double[n][k+1];
//        double arr[][]=new double[n][n];
        double sum[]=new double[n];
        double s=0;

        for(int i=0;i<n;i++){
            s+=nums[i];
            sum[i]=s;
            dp[i][1]=s/(i+1);
        }
        //Tối ưu code
//        for(int i=0;i<n;i++){
//            for(int j=i;j<n;j++){
//                if(i==j){
//                    arr[i][j]=nums[i];
//                    continue;
//                }
//                double subtraction=0;
//                if(i-1>=0){
//                    subtraction=sum[i-1];
//                }
//                arr[j][i]=arr[i][j]=(sum[j]-subtraction)/(j-i+1);
//            }
//        }
        for(int i=2;i<=k;i++){
            for(int j=i-1;j<n;j++){
                double max=0;

                for(int h=j-1;h>=0;h--){
//                    arr[h+1][j]
                    max=Math.max(max, dp[h][i-1]+(sum[j]-sum[h])/(j-h));
                }
//                if(j==0){
//                    max=nums[j];
//                }
                dp[j][i]=max;
            }
        }
        //--> Chú ý không xảy cần reverse array
        return dp[n-1][k];
    }

    public static void main(String[] args) {
        //Tư duy ở đây đơn giản:
        //1, Là mỗi vị trí sẽ lưu thêm 1 chiều số subset
        //dp[i: (0:n-1) ][j: (1:k)]
        //---> k=i được tính theo (i+1)
        //1.1, Ở đây ta chỉ cần tính tổng đến vị trí thứ [i]
        //---> Là ta có thể tính được trung bình cộng giữa 2 vị trí (i, j)
        //(sum[j]-sum[i])/ (i-j+1) <--> Thay vì tính arr[i][j]

//        int arr[]=new int[]{9,1,2,3,9};
        int arr[]=new int[]{1,2,3,4,5,6,7};
//        System.out.println(largestSumOfAverages(arr, 3));
        System.out.println(largestSumOfAverages(arr, 4));
//        System.out.println(largestSumOfAverages(reverse(arr, arr.length), 4));
    }

    static double [][] dp;
    static int [] psa;
    static int n;
    public static double largestSumOfAverages1(int[] nums, int k) {
        n = nums.length; psa = new int[n+1];
        dp = new double[n+1][k+1];
        for (double [] nxt : dp) Arrays.fill(nxt, -1.0);
        for (int i = 1; i<=n; i++) psa[i] = psa[i-1]+nums[i-1];
        return recursion(k-1, 1, nums);
    }
    public static double recursion(int k, int idx, int[] nums) {
        if (idx==n+1) return 0.0;
        if (k<0) return -1;
        if (dp[idx][k]!=-1.0) return dp[idx][k];
        double ret = 0;
        for (int size = 1; idx+size-1<=n; size++) {
            int end = idx+size-1;
            double res = recursion(k-1, idx+size, nums);
            if (res==-1) continue;
            ret = Math.max(ret, res+((double) psa[end]-psa[idx-1])/size);
        }
        dp[idx][k] = ret;
        return ret;
    }
}
