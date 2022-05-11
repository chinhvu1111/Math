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
public class DecodeWays_6 {
    
    public static int numDecodings(String s) {
        int n=s.length();
        int dp[]=new int[n+1];
        dp[0]=1;
        if(s.charAt(0)>='1'){
            dp[1]=1;
        }else{
           return 0;
       }
        
        for(int i=2;i<=n;i++){
            if(s.charAt(i-1)>='1'){
                dp[i]+=dp[i-1];
            }
            if(i>1
                    &&((s.charAt(i-1)-'0')+(s.charAt(i-2)-'0')*10<=26)
                    &&s.charAt(i-2)>='1'){
                dp[i]+=dp[i-2];
            }
//            if(i>1
//                    &&(s.charAt(i-1)<='6'&&s.charAt(i-2)=='2'
//                    ||s.charAt(i-2)<'2')
//                    &&s.charAt(i-2)>='1'){
//                dp[i]+=dp[i-2];
//            }
        }
        return dp[n];
    }
    
    public static void main(String[] args) {
//        String s="12";
//        String s="226";
//        String s="06";
//        String s="10";
        //Phần số thêm xét <=6&&<=2 chưa 
//        String s="2101";
        String s="2611055971756562";
        System.out.println(numDecodings(s));
    }
}
