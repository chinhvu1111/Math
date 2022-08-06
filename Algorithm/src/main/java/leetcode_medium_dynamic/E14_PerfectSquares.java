/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

import java.util.Arrays;

/**
 *
 * @author chinhvu
 */
public class E14_PerfectSquares {
//    public static int numSquares(int n) {
//        int dp[]=new int[n+1];
//        Arrays.fill(dp, 1000);
//        dp[0]=0;
//        dp[1]=1;
//        
//        
//        
//        for(int i=2;i<=n;i++){
//            for(int j=1;j<=Math.sqrt(i);j++){
//                dp[i]=(int) Math.min(dp[i], dp[(int)(i-Math.pow(j, 2))]+1);
//                //Càng for phức tạp --> Càng không nên đặt nhiều commands vào
//                //Vì command đó sẽ được thực hiện vô số lần --> Ảnh hưởng đến tốc độ
////                if(dp[dp[(int)(i-Math.pow(j, 2))]]<=1){
////                    break;
////                }
//            }
//        }
//        return dp[n];
//    }
    
    public static int numSquares(int n) {
        int dp[]=new int[n+1];
        Arrays.fill(dp, 1000);
        dp[0]=0;
        dp[1]=1;
        
        //Lệnh sqrt --> Ảnh hưởng đến tốc độ ==> Nếu chạy quá nhiều for
        //Lệnh cast --> Ảnh hướng đến tốc ==> Nếu chạy quá nhiều vòng for ngoai
        
        for(int i=2;i<=n;i++){
            int min=Integer.MAX_VALUE;
//            for(int j=1;j*j<=i;j++){
//                min=Math.min(min, dp[i-j*j]);
////                dp[i]=Math.min(dp[i], dp[i-j*j]+1);
//                //Càng for phức tạp --> Càng không nên đặt nhiều commands vào
//                //Vì command đó sẽ được thực hiện vô số lần --> Ảnh hưởng đến tốc độ
////                if(dp[dp[(int)(i-Math.pow(j, 2))]]<=1){
////                    break;
////                }
//            }
            int low=1;
            int high=(int) Math.sqrt(i);
            
            while(low<=high){
                min=Math.min(min, Math.min(dp[i-low*low], dp[i-high*high]));
                low++;
                high--;
            }
            dp[i]=min+1;
        }
        return dp[n];
    }
    
    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }
}
