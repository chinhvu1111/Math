package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E117_MinimumInsertionStepsToMakeAStringPalindrome {
    public static int[][] dp;

    public static int solution(int low, int high, String s) {
        if (low >= high) {
            return 0;
        }
        if (dp[low][high] != -1) {
            return dp[low][high];
        }
        int curRs = Integer.MAX_VALUE;
        if (s.charAt(low) == s.charAt(high)) {
            curRs = solution(low + 1, high - 1, s);
        }
        curRs = Math.min(curRs, solution(low + 1, high, s) + 1);
        curRs = Math.min(curRs, solution(low, high - 1, s) + 1);
        return dp[low][high] = curRs;
    }

    public static int minInsertionsTopDown(String s) {
        int n = s.length();
        dp = new int[n][n];

        for (int[] e : dp) {
            Arrays.fill(e, -1);
        }
        return solution(0, n - 1, s);
    }

    public static int minInsertionsBottomUp(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 2; i <= n; i++) {
            for (int j = 1; i + j - 1 <= n; j++) {
                int k = i + j - 1;

                if (s.charAt(j - 1) == s.charAt(k - 1)) {
                    dp[j][k] = dp[j + 1][k - 1];
                } else {
                    dp[j][k] = Math.min(dp[j + 1][k], dp[j][k - 1]) + 1;
                }
            }
        }
        return dp[1][n];
    }

    public static int maxLengthCommonChar(String s, String sReverse, int n){
        int[] dp=new int[n+1];
        int[] dpPrev=new int[n+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(s.charAt(i-1)==sReverse.charAt(j-1)){
                    dp[j]=1+dpPrev[j-1];
                }else{
                    dp[j]=Math.max(dpPrev[j], dp[j-1]);
                }
            }
            dpPrev=dp.clone();
        }
        return dp[n];
    }

    public static int minInsertionsBottomUpSpaceOptimization(String s) {
        int n = s.length();
        StringBuilder sReverse=new StringBuilder(s);
        sReverse.reverse();
        return n- maxLengthCommonChar(s, sReverse.toString(), n);
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given a string s. In one step you can insert any character at any index of the string.
        //* Return the minimum number of steps to make s palindrome.
        //  - Return số lượng steps (Bước insert) để make s thành palindrome.
        //A Palindrome String is one that reads the same backward as well as forward.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= s.length <= 500
        //s consists of lowercase English letters.
        //
        //- Brainstorm
        //Ex:
        //s= mbadm
        //- Ta chỉ có thể insert
        // + Với string bên trên thì ta chỉ có thể biến (b) <-> (d) tương đương nhau bằng cách:
        // + dbbd ==> Palindrome
        //- Với string = bd, ta có thể để nó thành
        //+ (bdb)
        //+ ([b]d[d]b)
        //Ex:
        //s= mbdadm
        //+ Ở đây thì ta chỉ cần thêm "b" vào sau "d"
        //s => mbdad(b)m
        //
        //- Mục tiêu là return lại chuỗi palindrome dài nhất
        //--> Ta so sánh từng cặp ==> match nhất có thể là được
        //  + 1 char dùng trước hay dùng sau thì vai trò tác động đến result là như nhau.
        //
        //Ex:
        //s= mdada
        //substring:
        //  + [mdad]a
        //  + m[dad]a
        //  + m[dada]
        //Ex:
        //s= mdama ==> ta nên xét [mdam]a
        //s= mabca ==> ta nên xét m[abma]
        //
        //- Ta có thể làm top-down được dễ hơn
        //- Bottom up approach
        //- Giả sử:
        //- Ta sẽ scan length 1 ==> n
        //Ex:
        //String s="mba";
        //- Length=2
        //- mb :
        //  mb -> mbm
        // + dp[0][1] = 1
        //- ba :
        //  ba -> bab
        // + dp[1][2] = 1
        //- Length=3
        //- mba :
        //  mba -> mbam -> mbabm
        //  + dp[0][2] = 2
        // + (i,j,k)
        // + Nếu s[i]==s[k] => dp[i][j]= dp[i+1][k-1]
        // + Nếu s[i]!=s[k] => dp[i][j]= min(dp[i+1][k],min(dp[i][k-1]) + 1
        //
        //1.1, Optimization
        //- Ta có quy luật sau:
        //- Ta thấy rằng length sẽ chỉ được tính theo (length-1)
        // + (i,j,k)
        // + Nếu s[i]==s[k] => dp[i][j]= dp[i+1][k-1]
        // + Nếu s[i]!=s[k] => dp[i][j]= min(dp[i+1][k],min(dp[i][k-1]) + 1
        //- Đoạn này ta cần dùng (loop order) để tận dụng việc tính toán trước
        //** New solution:
        //- Min insert = Max điểm chung giữa (s, s_reverse)
        //
        //1.2 Complexity
        //- Space:
        //  + Pre-optimiztion : O(n*m)
        //  + Optimization : O(n)
        //- Time : O(m*n)
        //
        //- Min insert = Max điểm chung giữa (s, s_reverse)
        //
        //
        String s = "mbadm";
        System.out.println(minInsertionsTopDown(s));
        System.out.println(minInsertionsBottomUp(s));
        System.out.println(minInsertionsBottomUpSpaceOptimization(s));
        //#Reference:
        //2193. Minimum Number of Moves to Make Palindrome
    }
}
