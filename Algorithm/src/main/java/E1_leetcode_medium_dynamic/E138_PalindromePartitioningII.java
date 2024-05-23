package E1_leetcode_medium_dynamic;

public class E138_PalindromePartitioningII {

    public static int minCut(String s) {
        int n=s.length();

        if(n==1){
            return 0;
        }
        //Space : O(n^2)
        int[] dp=new int[n+1];
        boolean[][] isPalindrome=new boolean[n][n];

        for(int i=0;i<n;i++){
            isPalindrome[i][i]=true;
        }
        //Time : O(n^2)
        for(int i=2;i<=n;i++){
            for(int j=0;i+j-1<n;j++){
                int k=i+j-1;
                if(s.charAt(j)==s.charAt(k)&&(j==k-1||isPalindrome[j+1][k-1])){
                    isPalindrome[j][k]=true;
//                    System.out.printf("%s %s\n", j, k);
                }
            }
        }
        if(isPalindrome[0][n-1]){
            return 0;
        }
        //Time : O(n^2)
        dp[0]=-1;
        dp[1]=0;
        for(int i=2;i<=n;i++){
            int curMinWays=Integer.MAX_VALUE;
            for(int j=i-1;j>=0;j--){
                if(isPalindrome[j][i-1]){
                    curMinWays=Math.min(dp[j], curMinWays);
                }
            }
            dp[i]=curMinWays+1;
//            System.out.printf("%s %s\n", i, dp[i]);
        }
        return dp[n];
    }

    public static int minCutOptimization(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cut = new int[n];
        boolean[][] pal = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            int min = i;
            for(int j = 0; j <= i; j++) {
                if(c[j] == c[i] && (j + 1 > i - 1 || pal[j + 1][i - 1])) {
                    pal[j][i] = true;
                    min = j == 0 ? 0 : Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[n - 1];
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given a string s, partition s such that every substring of the partition is a palindrome
        //* Return (the minimum cuts) needed for a palindrome partitioning of s.
        //* Return số lần cut nhỏ nhất -> Để biến string thành toàn bộ là palindrome.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= s.length <= 2000
        //s consists of lowercase English letters only.
        //
        //- Brainstorm
        //- Giả sử dp[i] là số lần cut ít nhất đến index=i
        //   + dp[i] = min(dp[i-k))+1 với điều kiện:
        //      + s[i-k+1][i] là palindrome
        //      + Số lần cut cộng dần lên.
        //   + Mỗi i cần xét cả index=-1 : Tức là xét cả chuỗi string -> index=i xem có là palindrome hay không nữa.
        //- Ta sẽ xét i : 1 -> n vì gặp vấn đề index với (i==-1) ==> Thì sẽ xét full string.
        //
        String s = "aab";
//        String s = "bb";
//        String s = "leet";
        //* Vấn đề indexing:
        //- Có 2 cases cần đưa về 1 rule:
        //  + aa : dp[1]=dp[0]+1
        //  + le : dp[1]=dp[0]+1
        //  ==> Cần xét thêm cả đằng trước nữa
        //  ==> Thêm index
        //1.1, Optimization
        //- Ta có thể gộp 2 loops vào chung 1 lần
        //- Không dùng length để tìm isPalindrome nữa
        //  + Ta cũng có thể tìm dựa trên range index(i,j)
        //  ==> Để có thể tận dụng + gộp chung vào 1 lần loop.
        //
        //1.2, Complexity
        //- Space: O(n^2)
        //- Time: O(n^2)
        //
        //#Reference:
        //1745. Palindrome Partitioning IV
        //2472. Maximum Number of Non-overlapping Palindrome Substrings
        //2518. Number of Great Partitions
        System.out.println(minCut(s));
        System.out.println(minCutOptimization(s));
    }
}
