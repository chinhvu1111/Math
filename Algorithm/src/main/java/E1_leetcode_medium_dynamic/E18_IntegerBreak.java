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
public class E18_IntegerBreak {

    public static int integerBreak(int n) {
        int dp[] = new int[n + 1];
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int temp = 0;

            for (int j = 1; j <= i / 2; j++) {
                int l = Math.max(dp[j], j);
                int r = Math.max(dp[i - j], i - j);
                temp = Math.max(temp, l * r);
            }
            dp[i] = temp;
        }
        return dp[n];
    }

    public int integerBreak1(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }

        int rem = n % 3;
        if (rem == 0) {
            return (int) Math.pow(3, n / 3);
        } else if (rem == 2) {
            return 2 * ((int) Math.pow(3, n / 3));
        } else {
            return 4 * ((int) Math.pow(3, (n - 4) / 3));
        }
    }

    
    //Ý tưởng bài này như sau
    //Tất cả các số >=3 đều có thể tạch được thành "tích" của số (2*3)
    //VD: 5 lớn nhất khi được tách thành (2*3)
    //6 tách thành (3*3)
    //Vì từ các sub node đã được tách thành (2*3)
    //--> Large number cũng có thể tách thành như thế
    //VD: 7=5*2 --> (5 tách thành (2*3) là ok nhất)
    //VD: 7=4*3
    //Ta sẽ so sánh (4*3) và (5*2)
    //VD: 8=5*3
    //8=4*4
    //--> Vì kiểu gì cũng tách thành (3,2)
    //--> Ta sẽ lấy (3)*(i-3) là to nhất vì khi thêm (1 đơn vị) tức là ta sẽ thêm vào (2) ==> (3)
    public static int integerBreak2(int n) {
        int dp[] = new int[n + 1];
        int diff = Integer.MIN_VALUE;
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        if (n == 4) {
            return 4;
        }
        if (n == 5) {
            return 6;
        }
        if (n == 6) {
            return 9;
        }
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 4;
        dp[5] = 6;
        dp[6] = 9;
        for (int i = 7; i <= n; i++) {
            dp[i] = dp[i - 3] * 3;
        }
        return dp[n];

    }

    public static void main(String[] args) {
        System.out.println(integerBreak(9));
    }
}
