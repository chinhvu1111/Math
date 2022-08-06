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
public class E4_WildcardMatching_hard {

    public static boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();
        boolean dp[][] = new boolean[n + 1][m + 1];

        dp[0][0] = true;
        for (int i = 1; i <= m; i++) {
            if (p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-1];
            }else{
                break;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1)
                        || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - 1] || dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String s = "zacabz";
        String p = "*a?b*";
        System.out.println(isMatch(s, p));
    }
}
