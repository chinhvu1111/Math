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
public class E6_DecodeWays {
    
    public static int numDecodings(String s) {
        int n = s.length();
        int dp[] = new int[n + 1];
        dp[0] = 1;
        if (s.charAt(0) >= '1') {
            dp[1] = 1;
        } else {
            return 0;
        }

        for (int i = 2; i <= n; i++) {
            if (s.charAt(i - 1) >= '1') {
                dp[i] += dp[i - 1];
            }
            if (i > 1
                    && ((s.charAt(i - 1) - '0') + (s.charAt(i - 2) - '0') * 10 <= 26)
                    && s.charAt(i - 2) >= '1') {
                dp[i] += dp[i - 2];
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

    public static int memo[];

    //- Đoạn sét biên hoàn toàn có thể xét:
    //+ index==s.length() thay vì [ index==s.length()-1 ]
    //==> Để có thể bỏ đi đoạn check [ index+2<s.length() ]
    //+ Để tránh index >= n
    public static int bottomUpDp(int index, String s){
//        System.out.println(index);
        if(index==s.length()-1){
            if(s.charAt(index)!='0'){
                return 1;
            }else {
                return 0;
            }
        }
        if(memo[index]!=-1){
            return memo[index];
        }
        int currentValue=0;
        if(s.charAt(index)!='0'){
            currentValue+=bottomUpDp(index+1, s);
        }
        if((s.charAt(index)=='1'&&index+1<s.length()&&s.charAt(index+1)<='9')
        ||(s.charAt(index)=='2'&&index+1<s.length()&&s.charAt(index+1)<='6')){
            if(index+2<s.length()){
                currentValue+=bottomUpDp(index+2, s);
            }else{
                currentValue+=1;
            }
        }
        return memo[index]=currentValue;
    }

    public static int numDecodingsTopDown(String s) {
        if("0".equals(s)){
            return 0;
        }
        memo=new int[s.length()];
        Arrays.fill(memo, -1);
        return bottomUpDp(0, s);
    }
    
    public static void main(String[] args) {
//        String s="12";
//        String s="226";
//        String s="06";
//        String s="10";
        //Phần số thêm xét <=6&&<=2 chưa 
        String s="2101";
//        String s="2611055971756562";
//        String s="0";
//        String s="10";
        System.out.println(numDecodings(s));
        System.out.println(numDecodingsTopDown(s));
        //
        //1. Bottom up DP
        //1.1,
        //1.2, Complexity:
        //- Time complexity : O(n)
        //- Space complextiy : O(n)
        //1.3, Optimize:
        //- Cách này có thể tối ưu xuống O(1)
        //
        //2, Top down dp
        //2.1.
        //- Time complexity : O(n)
        //- Space complexity : O(n)
        //
        //#Reference:
        //92. Reverse Linked List II
        //639. Decode Ways II
        //1977. Number of Ways to Separate Numbers
        //2266. Count Number of Texts
    }
}
