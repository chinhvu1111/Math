package algorithm;

import java.util.Arrays;
import java.util.List;

public class ShortestLength {

    public static int optimalSumOperations(List<Integer> arr) {
        // Write your code here
        int n=arr.size();
        int prefixSum[]=new int[n];
        int dp[]=new int[n];
        int maxValue[]=new int[n];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=arr.get(i);
            prefixSum[i]=sum;
        }
        dp[0]=1;
        maxValue[0]=arr.get(0);
        for(int i=1;i<n;i++){
            maxValue[i]=arr.get(i);

            for(int j=i-1;j>=0;j--){
                if(prefixSum[i]-prefixSum[j]>=maxValue[j]&&dp[j]+1>dp[i]){
                    // System.out.println("Test: "+dp[i]);
                    dp[i]=Math.max(dp[j]+1, dp[i]);
//                    System.out.println("Test: "+maxValue[i]);
                    maxValue[i]=prefixSum[i]-prefixSum[j];
                }

            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        Integer arr[]=new Integer[]{1,2,3,1,2,3,4};
        System.out.println(optimalSumOperations(Arrays.asList(arr)));
    }
}
