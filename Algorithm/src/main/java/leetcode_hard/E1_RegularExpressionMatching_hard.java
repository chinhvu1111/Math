/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_hard;

/**
 *
 * @author chinhvu
 */
public class E1_RegularExpressionMatching_hard {

    //Bài này chạy nhanh hơn (87%) nhưng bộ nhớ chiếm quá nhiều
    public static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean dp[][] = new boolean[n + 1][m + 1];
        int countDup = 0;

        for (int i = 0; i <= n; i++) {

            if (i > 1 && s.charAt(i - 1) == s.charAt(i - 2)) {
                countDup++;
            } else {
                countDup = 0;
            }
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                }
                if (j == 0) {
                    continue;
                }

                if (i > 0
                        && s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }

                boolean v = false;

                if (i - countDup - 1 >= 0 && j - 2 >= 0) {
                    v = dp[i - countDup - 1][j - 2];
                }
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2];
                }
                if (p.charAt(j - 1) == '*'
                        && (i == 0 || p.charAt(j - 2) == s.charAt(i - 1))) {
                    dp[i][j] = v | dp[i][j];
                    if(j>=2&&i>=1) dp[i][j]=dp[i][j]|dp[i-1][j-2]; 
                    continue;
                }
                if (p.charAt(j - 1) == '*' && p.charAt(j - 2) == '.') {
                    dp[i][j] = dp[i-1][j]|dp[i-1][j-2]|dp[i][j-2];
                    continue;
                }
                if (i >= 1 && p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aaa", "ab*a*c*a*"));
        System.out.println(isMatch("aab", "b.*"));
        System.out.println(
                isMatch(
                        "aasdfasdfasdfasdfas", 
                        "aasdf.*asdf.*asdf.*asdf.*s"));
        System.out.println(
                isMatch(
                        "abcdef", 
                        "abc.*f.*"));
    }
}
