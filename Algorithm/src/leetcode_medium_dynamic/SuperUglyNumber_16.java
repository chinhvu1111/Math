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
public class SuperUglyNumber_16 {

    //Chú ý: Vị trí bắt đầu (i=1)
    //Bắt buộc vì index tiếp theo =1
    //VD: 
    public static int nthSuperUglyNumber(int n, int[] primes) {
        int len = primes.length;
        int indexs[] = new int[len];
        int dp[] = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;

            for (int j = 0; j < len; j++) {
                min = Math.min(min, dp[indexs[j]] * primes[j]);
            }
            for (int j = 0; j < len; j++) {
                if (min == dp[indexs[j]] * primes[j]) {
                    indexs[j]++;
                }
            }
            dp[i] = min;
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] primes = new int[]{2, 7, 13, 19};
        System.out.println(nthSuperUglyNumber(12, primes));
    }
}
