/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E1_leetcode_medium_dynamic;

/**
 *
 * @author chinhvu
 */
public class E8_UniqueBinarySearchTrees {
    
//    public static int solution(Integer left, Integer right){
//        
//    }
    
    public static int numTrees(int n) {
        int dp[]=new int[n];
//        int right[]=new int[n];
        
        dp[0]=1;
        if(n==1){
            return 1;
        }
        dp[1] = 2;
        if(n==2){
            return 2;
        }
        
        for(int i=2;i<n;i++){
            int temp=0;
            
            for(int j=0;j<=i;j++){
                int left=1;
                int right=1;
                
                if(j-1>=0){
                    left=dp[j-1];
                }
                if(i-j-1>=0){
                    right=dp[i-j-1];
                }
                temp+=left*right;
            }
            dp[i]=temp;
        }
        return dp[n-1];
    }
    
    public static void main(String[] args) {
        int n=4;
        System.out.println(numTrees(n));
    }
}
