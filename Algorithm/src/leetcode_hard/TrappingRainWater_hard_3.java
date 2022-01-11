/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_hard;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author chinhvu
 */
public class TrappingRainWater_hard_3 {

    public static int solution(int[] height) {
        int n = height.length;
        int sum[] = new int[n];
        int temp = 0;
        int rs = 0;

        if (height.length == 0) {
            return 0;
        }

        for (int i = 0; i < n; i++) {
            temp += height[i];
            sum[i] = temp;
        }

        int firstIndex = 0;
        int value = height[0];
        int rsTemp = 0;

        for (int i = 1; i < n; i++) {

            if (height[i] >= value) {
                int tempRs = (i - firstIndex - 1) * (Math.min(height[firstIndex], height[i]))
                        - (sum[i - 1] - sum[firstIndex]) + rs;
                rs = Math.max(tempRs, rsTemp);
                rsTemp = 0;
                firstIndex = i;
                value = height[i];
            } else if (height[i] != 0) {
                int t = (i - firstIndex - 1) * (Math.min(height[firstIndex], height[i]))
                        - (sum[i - 1] - sum[firstIndex]) + rs;
                rsTemp = Math.max(rsTemp, t);
            }
        }

        rs = Math.max(rs, rsTemp);
        return rs;
    }

//    public static int trap(int[] height) {
//        int rs = solution(height);
//        int n = height.length;
//
//        for(int i=0;i<n/2;i++){
//            int temp=height[i];
//            height[i]=height[n-1-i];
//            height[n-1-i]=temp;
//        }
////        int[] reversed = new int[height.length];
////        for (int i = 0; i < height.length; i++) {
////            reversed[i] = height[height.length - 1 - i];
////        }
//        rs = Math.max(rs, solution(height));
//        return rs;
//    }
    public static int trap(int[] height) {
        int n = height.length;
        int maxRight[] = new int[n];
        int maxLeft[] = new int[n];
        int rs = 0;

        if (n == 0) {
            return 0;
        }
        maxLeft[0] = height[0];
        maxRight[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
            maxRight[n - i - 1] = Math.max(maxRight[n - i], height[n - i - 1]);
        }

//        for(int i=0;i<n;i++){
//            rs+=Math.min(maxLeft[i], maxRight[i])-height[i];
//        }
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            rs += Math.min(maxLeft[low], maxRight[low]) - height[low];
            low++;
            if (low > high) {
                break;
            }
            rs += Math.min(maxLeft[high], maxRight[high]) - height[high];
            high--;
        }
        return rs;
    }

    public static void trapStack(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();

    }

    public static int trapTwoPoints(int[] height) {
        int low = 0;
        int n = height.length;
        int high = n-1;
        int leftMax=0;
        int rightMax=0;
        int rs=0;

        while (low < high) {
            if(height[low]<height[high]){
                if(leftMax<=height[low]){
                    leftMax=height[low];
                }else{
                    rs+=leftMax-height[low];
                }
                low++;
            }else{
                if(rightMax<=height[high]){
                    rightMax=height[high];
                }else{
                    rs+=rightMax-height[high];
                }
                high--;
            }
        }
        return rs;
    }

    static boolean strongPassword(String pwd) {
        if (pwd.length() > 30 && pwd.length() < 8) {
            return false;
        }
        int countN = 0;
        int countUp = 0;
        int countD = 0;
        int countS = 0;
        HashMap<Character, Boolean> specials = new HashMap<>();
        String specialstr = "!@#$%ˆ&*()[]{},./<>?";
        for (int i = 0; i < specialstr.length(); i++) {
            specials.put(specialstr.charAt(i), true);
        }

        for (int i = 0; i < pwd.length(); i++) {
            Character c = pwd.charAt(i);
            boolean isAdapt = false;

            if (c >= 'a' && c <= 'z') {
                countN = (countN == 0) ? countN + 1 : countN;
                isAdapt = true;
            }
            if (c >= 'A' && c <= 'Z') {
                countUp = (countUp == 0) ? countUp + 1 : countUp;
                isAdapt = true;
            }
            if (c >= '0' && c <= '9') {
                countD = (countD == 0) ? countD + 1 : countD;
                isAdapt = true;
            }
            if (specials.get(c) != null) {
                countS = (countS == 0) ? countS + 1 : countS;
                isAdapt = true;
            }
            if (!isAdapt) {
                return false;
            }
        }
        if (countN + countUp + countS + countD >= 3) {
            return true;
        }
        return false;

    }

//    static int[][] deleteRowsColumns(int[][] inputMatrix, int[] deleteRows, int[] deleteColumns) {
//        int n = inputMatrix.length - deleteRows.length;
//        int m = inputMatrix[0].length - deleteColumns.length;
//        int rs[][] = new int[n][m];
//        HashMap<Integer, Boolean> rowsMap = new HashMap<>();
//        HashMap<Integer, Boolean> columnsMap = new HashMap<>();
//
//        for (int i = 0; i < deleteRows.length; i++) {
//            rowsMap.put(deleteRows[i], true);
//        }
//        for (int i = 0; i < deleteColumns.length; i++) {
//            columnsMap.put(deleteColumns[i], true);
//        }
//        int indexRow = 0;
//
//        for (int i = 0; i < inputMatrix.length; i++) {
//            int indexColumn = 0;
//            if (rowsMap.get(i + 1) != null) {
//                continue;
//            }
//            for (int j = 0; j < inputMatrix[0].length; j++) {
//                if (columnsMap.get(j + 1) != null) {
//                    continue;
//                }
//                System.out.println(indexRow+" "+indexColumn);
//                rs[indexRow][indexColumn] = inputMatrix[i][j];
//                indexColumn++;
//            }
//            indexRow++;
//        }
//        return rs;
//    }
//    static int[] botBanking_(int[] accounts, String[] requests) {
//        int n = requests.length;
//        boolean isAdapt = false;
//
//        for (int i = 0; i < requests.length; i++) {
//            String req = requests[i];
//
//            if (req.charAt(0) == 'w') {
//                int index = Integer.valueOf(req.split(" ")[1]);
//                int depositM = Integer.valueOf(req.split(" ")[2]);
//                if (index - 1 >= accounts.length) {
//                    break;
//                }
//                if (accounts[index - 1] - depositM < 0) {
//                    break;
//                }
//                accounts[index - 1] -= depositM;
//            }
//            if (req.charAt(0) == 't') {
//                int index = Integer.valueOf(req.split(" ")[1]);
//                int indexNext = Integer.valueOf(req.split(" ")[2]);
//                int tranferM = Integer.valueOf(req.split(" ")[3]);
//                if (indexNext - 1 >= accounts.length || index - 1 >= accounts.length) {
//                    break;
//                }
//                if (accounts[index - 1] - tranferM < 0) {
//                    break;
//                }
//                accounts[indexNext - 1] += tranferM;
//                accounts[index - 1] -= tranferM;
//            }
//            if (req.charAt(0) == 'd') {
//                int index = Integer.valueOf(req.split(" ")[1]);
//                int withDrawM = Integer.valueOf(req.split(" ")[2]);
//                if (index - 1 >= accounts.length) {
//                    break;
//                }
////            if(accounts[index-1]-withDrawM<0){
////                break;
////            }
//                accounts[index - 1] += withDrawM;
//            }
//        }
//        return accounts;
//    }
//    static long digitSum_(int n, int k) {
//        long dp[][] = new long[n + 1][k + 1];
//        int m = Math.min(k, 9);
//
//        for (int i = 0; i <= m; i++) {
//            dp[1][i] = 1;
//        }
//        for (int i = 2; i <= n; i++) {
//            for (int j = 0; j <= k; j++) {
////                System.out.println(dp[i - 1][k - j]);
//               for(int l=0;l<=9;l++){
//                   if(j - l<0){
//                       continue;
//                   }
//                   dp[i][j] += dp[i - 1][j - l];
//               }
//            }
//        }
//        return dp[n][k];
//    }
    public static void main(String[] args) {
//        int heights[]=new int[]{4,2,0,3,2,5};
//        
//        System.out.println(trap(heights));

//        int heights[] = new int[]{0, 2, 5, 0, 6, 9, 8, 7, 4, 4, 5, 6};
        int heights[] = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(heights));
        System.out.println(strongPassword("1 2 3 a@ A"));

//        int inputMatrix[][] = new int[][]{{0, 1, 2, 3, 4},
//        {4, 3, 2, 1, 0},
//        {5, 6, 7, 8, 9}};
//        int deleteRows[] = new int[]{2};
//        int deleteColumns[] = new int[]{2, 5};
//        int rs[][] = deleteRowsColumns(inputMatrix, deleteRows, deleteColumns);
//        System.out.println(rs);
//
//        int arr[] = new int[]{10, 100, 20, 50, 30};
//        String str[] = new String[]{"withdraw 2 10",
//            "transfer 5 1 20",
//            "deposit 5 20",
//            "transfer 3 4 15"};
//
//        System.out.println(botBanking_(arr, str));
//        System.out.println(digitSum_(100, 300));

        System.out.println(trapTwoPoints(heights));
    }
}
