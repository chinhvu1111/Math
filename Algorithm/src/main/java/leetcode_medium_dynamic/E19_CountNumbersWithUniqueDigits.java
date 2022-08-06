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
public class E19_CountNumbersWithUniqueDigits {
    
    public static int countNumbersWithUniqueDigits(int n) {
        if(n==0){
            return 1;
        }
        int dp[]=new int[n+1];
        
        dp[1]=9;
        int init=9;
        int rs=10;
        
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]*(init--);
            rs+=dp[i];
        }
        return rs;
    }
    
    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(2));
    }
}
