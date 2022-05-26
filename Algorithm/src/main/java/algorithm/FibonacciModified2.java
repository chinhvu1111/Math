/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author chinhvu
 */
public class FibonacciModified2 {

    public static String fibonacciModified(int t1, int t2, int n) {
        // Write your code here
        String dp[] = new String[n];

        dp[0] = String.valueOf(t1);
        dp[1] = String.valueOf(t2);

        for (int i = 2; i < n; i++) {
//            dp[i] = dp[i - 2] + dp[i - 1] * dp[i - 1];
            dp[i] = findSum(dp[i - 2], multiply(dp[i - 1], dp[i - 1]));
        }
        return dp[n - 1];
    }

    static String findSum(String str1, String str2) {
        // Before proceeding further, make sure length
        // of str2 is larger.
        if (str1.length() > str2.length()) {
            String t = str1;
            str1 = str2;
            str2 = t;
        }

        // Take an empty String for storing result
        String str = "";

        // Calculate length of both String
        int n1 = str1.length(), n2 = str2.length();

        // Reverse both of Strings
        str1 = new StringBuilder(str1).reverse().toString();
        str2 = new StringBuilder(str2).reverse().toString();

        int carry = 0;
        for (int i = 0; i < n1; i++) {
            // Do school mathematics, compute sum of
            // current digits and carry
            int sum = ((int) (str1.charAt(i) - '0')
                    + (int) (str2.charAt(i) - '0') + carry);
            str += (char) (sum % 10 + '0');

            // Calculate carry for next step
            carry = sum / 10;
        }

        // Add remaining digits of larger number
        for (int i = n1; i < n2; i++) {
            int sum = ((int) (str2.charAt(i) - '0') + carry);
            str += (char) (sum % 10 + '0');
            carry = sum / 10;
        }

        // Add remaining carry
        if (carry > 0) {
            str += (char) (carry + '0');
        }

        // reverse resultant String
        str = new StringBuilder(str).reverse().toString();

        return str;
    }

    static String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 == 0 || len2 == 0) {
            return "0";
        }

        // will keep the result number in vector
        // in reverse order
        int result[] = new int[len1 + len2];

        // Below two indexes are used to
        // find positions in result.
        int i_n1 = 0;
        int i_n2 = 0;

        // Go from right to left in num1
        for (int i = len1 - 1; i >= 0; i--) {
            int carry = 0;
            int n1 = num1.charAt(i) - '0';

            // To shift position to left after every
            // multipliccharAtion of a digit in num2
            i_n2 = 0;

            // Go from right to left in num2            
            for (int j = len2 - 1; j >= 0; j--) {
                // Take current digit of second number
                int n2 = num2.charAt(j) - '0';

                // Multiply with current digit of first number
                // and add result to previously stored result
                // charAt current position.
                int sum = n1 * n2 + result[i_n1 + i_n2] + carry;

                // Carry for next itercharAtion
                carry = sum / 10;

                // Store result
                result[i_n1 + i_n2] = sum % 10;

                i_n2++;
            }

            // store carry in next cell
            if (carry > 0) {
                result[i_n1 + i_n2] += carry;
            }

            // To shift position to left after every
            // multipliccharAtion of a digit in num1.
            i_n1++;
        }

        // ignore '0's from the right
        int i = result.length - 1;
        while (i >= 0 && result[i] == 0) {
            i--;
        }

        // If all were '0's - means either both
        // or one of num1 or num2 were '0'
        if (i == -1) {
            return "0";
        }

        // genercharAte the result String
        String s = "";

        while (i >= 0) {
            s += (result[i--]);
        }

        return s;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/home/chinhvu/NetBeansProjects/Algorithm/src/algorithm/input"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int t1 = Integer.parseInt(firstMultipleInput[0]);

        int t2 = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        String result = fibonacciModified(t1, t2, n);
        System.out.println(result);

        bufferedReader.close();
    }
}
