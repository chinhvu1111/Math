package E1_daily;

import java.util.Arrays;

public class E2_LongestIdealSubsequence {

    public static int longestIdealString(String s, int k) {
        int n=s.length();
        int[] dp=new int[26];

        for(int i=0;i<n;i++){
            char curChar=s.charAt(i);
            int curCharNum = curChar -'a';
            int limitMax = Math.min(curCharNum+k+1, 26);
            int limitMin = Math.max(curCharNum-k, 0);
            int rs=0;
            for(int j=limitMin;j<limitMax;j++){
                rs=Math.max(rs, dp[j]);
            }
            dp[curCharNum]=Math.max(rs+1, dp[curCharNum]);
        }
        int rs=0;
        for(int i=0;i<26;i++){
            rs=Math.max(rs, dp[i]);
        }
        return rs;
    }

    public static int longestIdealStringTopDown(String s, int k) {
        int N = s.length();

        // Initialize all dp values to -1 to indicate non-visited states
        int[][] dp = new int[N][26];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Find the maximum dp[N-1][c] and return the result
        int res = 0;
        for (int c = 0; c < 26; c++) {
            res = Math.max(res, dfs(N - 1, c, dp, s, k));
        }
        return res;
    }

    public static int dfs(int i, int c, int[][] dp, String s, int k) {
        // Memoized value
        if (dp[i][c] != -1) {
            return dp[i][c];
        }

        // State is not visited yet
        dp[i][c] = 0;
        boolean match = c == (s.charAt(i) - 'a');
        if (match) {
            dp[i][c] = 1;
        }

        // Non base case handling
        if (i > 0) {
            dp[i][c] = dfs(i - 1, c, dp, s, k);
            if (match) {
                for (int p = 0; p < 26; p++) {
                    if (Math.abs(c - p) <= k) {
                        dp[i][c] = Math.max(dp[i][c], dfs(i - 1, p, dp, s, k) + 1);
                    }
                }
            }
        }
        return dp[i][c];
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the following conditions are satisfied:
        //  + t is a subsequence of the string s.
        //  + The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
        //* Return the length of the longest ideal string.
        //- A subsequence is a string that can be derived from another string by
        // deleting some or no characters without changing the order of the remaining characters.
        //- Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a' and 'z' is 25, not 1.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= s.length <= 10^5
        //0 <= k <= 25
        //s consists of lowercase English letters.
        //
        //- Brainstorm
        //- Subsequence -> Lấy new array bằng cách delete đi 1 vài ký tự
        //Ex:
        //Input: s = "acfgbd", k = 2
        //Output: 4
        //Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
        //Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.
        //- Tại index=i, tìm index=j <i sao cho:
        //  + dp[j] max
        //  ==> Loop 26 ký tự là được.
        //  + | s[j]-s[i] | <= k
        //  ==> Lấy ký tự có (absolute <=k) ra thôi.
        //
        //- dp[26] là lưu thông tin chuỗi subsequence dài nhất nếu kết thúc tại character là [i]
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(const)
        //- Time : O(n*26)
        //
        //2.
        //2.0, Top down approach
        //- Ở đây ta có thể làm theo hướng top-down
//        String s = "acfgbd";
//        int k = 2;
//        String s = "abcd";
//        int k = 3;
        String s = "a";
        int k = 1;
        System.out.println(longestIdealString(s, k));
        System.out.println(longestIdealStringTopDown(s, k));
        //#Reference
        //1976. Number of Ways to Arrive at Destination
        //2585. Number of Ways to Earn Points
        //2053. Kth Distinct String in an Array
    }
}
