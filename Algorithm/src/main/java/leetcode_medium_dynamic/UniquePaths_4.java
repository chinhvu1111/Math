/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

/**
 *
 * @author chinhvu
 */
public class UniquePaths_4 {
    
    public static int uniquePaths(int m, int n) {
        int dp[][]=new int[m][n];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0&&j==0){
                    dp[i][j]=1;
                    continue;
                }
                int up=0;
                int left=0;
                
                if(j>0){
                    left=dp[i][j-1];
                }
                if(i>0){
                    up=dp[i-1][j];
                }
                dp[i][j]=up+left;
            }
        }
        return dp[m-1][n-1];
    }
    
    public static void main(String[] args) {
        int m=3, n=7;
        System.out.println(uniquePaths(m, n));
    }
}
