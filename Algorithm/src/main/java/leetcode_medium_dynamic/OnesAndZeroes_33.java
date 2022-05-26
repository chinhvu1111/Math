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
public class OnesAndZeroes_33 {

    public static int countingZero(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                count++;
            }
        }
        return count;
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int dp[][] = new int[m + 1][n + 1];
        int len = strs.length;
//        int numberZero[] = new int[len];
//
//        for (int i = 0; i < len; i++) {
//            numberZero[i] = countingZero(strs[i]);
//        }
        int rs=0;

        for (int k = 0; k < len; k++) {
            int currentLength = strs[k].length();
            int currentNumberZero=countingZero(strs[k]);
            
            //1,Không đảo ngược thứ tự --> Sai
            //Nó sẽ bị case:
            //thẳng nhỏ tính theo x
            //Thằng lớn hơn tính theo (Thằng nhỏ) và cũng theo x --> X dùng 2 lần --> Sai
            //2, Với những bài như thế này --> Không viết If(i - numberZero[k] < 0) continue;
            //--> Nên viết theo kiểu trong for (i=m;i>=numberZero[k];i--)
            
            //3, Phần nào mà có thể bỏ đi không khai báo dp[]---> Thì có thể cải thiện 1 chút về tốc độ
            //Dấu hiệu:
            //Có thể tính lại 1 lần duy nhất ở vòng ( for ngoài cùng ) của 1 vòng for khác --> Đối với mảng 1 chiều
            //Mảng nhiều chiều thì tương tự --> vòng for thử n
            //VD: int numberZero[] = new int[len]; --> Bỏ
            //= cách (tính trức tiếp) + (Dùng trực tiếp) trong vòng (FOR khác)
            
            //4, Beat 100%
            //https://leetcode.com/problems/ones-and-zeroes/discuss/95828/Yet-another-Java-DP-solution-(18ms-beats-100)
            for (int i = m; i >=currentNumberZero; i--) {
                for (int j = n; j >=currentLength-currentNumberZero; j--) {
                    
//                    if (i - numberZero[k] < 0 || j - currentLength + numberZero[k] < 0) {
//                        continue;
//                    }
                    //Không có max là sai
                    dp[i][j] = Math.max(dp[i][j], 
                            dp[i - currentNumberZero][j - currentLength + currentNumberZero]+ 1);
                    rs=Math.max(rs, dp[i][j]);
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        String s[] = new String[]{"10", "0001", "111001", "1", "0"};
        String s[] = new String[]{"10","0","1"};
        System.out.println(findMaxForm(s, 1, 1));
    }
}
