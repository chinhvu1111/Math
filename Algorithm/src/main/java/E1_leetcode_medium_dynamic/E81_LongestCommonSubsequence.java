package E1_leetcode_medium_dynamic;

public class E81_LongestCommonSubsequence {

    public static int longestCommonSubsequenceBottomUp(String text1, String text2) {
        int n=text1.length();
        int m=text2.length();
        int[][]dp=new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }

    public static int memo[][];

    public static int solTopDownDp(int i, int j, String text1, String text2){
        if(i<0||j<0){
            return 0;
        }
        if(memo[i][j]!=0){
            return memo[i][j];
        }
        int currentVal;
        if(text1.charAt(i)==text2.charAt(j)){
            currentVal=solTopDownDp(i-1, j-1, text1, text2)+1;
        }else{
            currentVal=Math.max(solTopDownDp(i-1, j, text1, text2), solTopDownDp(i, j-1, text1, text2));
        }
        return memo[i][j]=currentVal;
    }

    public static int longestCommonSubsequenceTopDownDp(String text1, String text2) {
        int n=text1.length();
        int m=text2.length();
        memo=new int[n][m];
        return solTopDownDp(text1.length()-1, text2.length()-1, text1, text2);
    }

    public static void main(String[] args) {
//        String text1 = "abcde", text2 = "ace";
//        String text1 = "bsbininm", text2 = "jmjkbkjkv";
        String text1 = "mhunuzqrkzsnidwbun", text2 = "szulspmhwpazoxijwbq";
        System.out.println(longestCommonSubsequenceBottomUp(text1, text2));
        System.out.println(longestCommonSubsequenceTopDownDp(text1, text2));
        //#Reference:
        //1147. Longest Chunked Palindrome Decomposition
        //516. Longest Palindromic Subsequence
        //1092. Shortest Common Supersequence
        //2207. Maximize Number of Subsequences in a String
    }
}
