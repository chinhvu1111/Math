/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package E1_leetcode_medium_dynamic;

import java.util.Arrays;

/**
 *
 * @author chinhvu
 */
public class E2_JumpGameII {

    public static int jump(int[] nums) {
        int n = nums.length;
        int dp[] = new int[n];

        //Cần tối ưu code
        Arrays.fill(dp, 100000);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < n) {
                    dp[i + j] = Math.min(dp[i] + 1, dp[i + j]);
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int arr[] = new int[]{2, 3, 1, 1, 4};
        System.out.println(jump(arr));
    }
}
