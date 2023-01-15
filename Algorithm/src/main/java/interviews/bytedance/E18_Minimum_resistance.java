package interviews.bytedance;

import java.util.ArrayList;
import java.util.List;

public class E18_Minimum_resistance {

    public static int minimumResistence(List<List<Integer>> matrix) {
        // Write your code here
//        int n=matrix.size();
//        int m=matrix.get(0).size();
//        int[][] dp=new int[n][m];
//
//        for(int i=0;i<n;i++){
//            dp[i][0]=matrix.get(i).get(0);
//        }
//
//        for(int i=1;i<m;i++){
//            for(int j=0;j<n;j++){
//                int right=0;
//                int rsMin=Integer.MAX_VALUE;
//                right = dp[j][i - 1];
//                rsMin= right;
//                dp[j][i]=matrix.get(j).get(i)+rsMin;
//            }
//            for(int j=0;j<n;j++){
//                int rsMin=Integer.MAX_VALUE;
//
//                if(i+1<m){
//                    right=dp[j][i+1];
//                    rsMin=Math.min(dp[j][i+1], right);
//                }
//                if(j+1<n){
//                    rightBottom=dp[j+1][i];
//                    rsMin=Math.min(dp[j+1][i], rightBottom);
//                }
//                dp[j][i]=matrix.get(j).get(i)+rsMin;
//            }
//        }
////        if(i+1<m){
////            right=dp[j][i+1];
////            rsMin=Math.min(rsMin, right);
////        }
////        if(j+1<n){
////            rightBottom=dp[j+1][i];
////            rsMin=Math.min(rsMin, rightBottom);
////        }
//        int rs=Integer.MAX_VALUE;
//        for(int i=0;i<n;i++){
//            rs=Math.min(rs, dp[i][m-1]);
//        }
//        return rs;
        return 1;
    }

    public static void main(String[] args) {
        int arr[][]=new int[][]{
                {2,6,7},
                {1,5,8},
                {3,4,9}};
        List<List<Integer>> input=new ArrayList<>();
        for(int i=0;i<arr.length;i++){
            List<Integer> currentV=new ArrayList<>();
            for(int j=0;j<arr[0].length;j++){
                currentV.add(arr[i][j]);
            }
            input.add(currentV);
        }
        System.out.println(minimumResistence(input));
    }
}
