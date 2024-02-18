package E1_leetcode_medium_dynamic;

public class E106_LongestPalindromicSubsequence {

    public static int longestPalindromeSubseq(String s) {
        int n=s.length();
        int[][] dp=new int[n][n];

        for(int i=0;i<n;i++){
            dp[i][i]=1;
        }

        for(int i=2;i<=n;i++){
            for(int j=0;i+j-1<n;j++){
                //j=0, k=1
                int k=i+j-1;
//                System.out.println(k);

                if(s.charAt(j)==s.charAt(k)){
                    //j=0, k=1 => j+1=2, k=0
                    dp[j][k]=dp[j+1][k-1]+2;
                }else{
                    //j=0, k=1:
                    //+ j+1=2, k=0
                    //+ j=0, k=0
                    dp[j][k]=Math.max(dp[j+1][k], dp[j][k-1]);
                }
            }
        }
        return dp[0][n-1];
    }

    public static int longestPalindromeSubseqOptimization(String s) {
        int n=s.length();
        int[] dp=new int[n];
        int[] prevDp=new int[n];

        //Ex:
        //String s = "bbbab";
        //- Ta sẽ loop khác đi 1 chút:
        //  + i : n-1 -> 0
        //  + j : i+1 -> n-1
        //- i=n-1
        //  + j=n ==>ignore
        //- i=n-2
        //  + j=n-1 ==>[n-2,n-1]
        //- i=n-3
        //  + j=n-2 ==>[n-3,n-2] => based on [n-2, n-3]
        //  + j=n-1 ==>[n-3,n-1] => based on [n-2, n-2]
        //- i=n-4
        //  + j=n-3 ==>[n-4,n-3] => based on [n-3, n-4]
        //  + j=n-2 ==>[n-4,n-2] => based on [n-3, n-1] ==> j=n-1
        //  + j=n-1 ==>[n-4,n-1] => based on [n-3, n-2] ==> j=n-2
        //** Rule:
        //- Khi i giảm đi --> Tương ứng với việc bù thêm 1 giá trị i nữa
        //- Và giá trị (i,j) sẽ vẫn được tính bằng (i+1,j-1) như bình thường do:
        //  + i=x --> all(j=i+1) đã được tính rồi ==> Ta có thể dùng lại được.
        //
        //- Dp role:
        //+ dp[i] : max length của palindrome string với (j=i)
        //  + Length thì all length cho đến thời điểm xét.
        //
        //Ex:
        //String s = "cacab";
        //dp=[1,1,3,3,4]
        //

        for(int i=n-1;i>=0;i--){
            dp[i]=1;
            for(int j=i+1;j<n;j++){
                if(s.charAt(j)==s.charAt(i)){
                    dp[j]=prevDp[j-1]+2;
                }else{
                    dp[j]=Math.max(prevDp[j], dp[j-1]);
                }
            }
            prevDp=dp.clone();
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given a string s, find the longest palindromic subsequence's length in s.
        //* A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
        //- Tức là tìm subsequence string có độ dài lớn nhất + đối xứng.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= s.length <= 1000
        //s consists only of lowercase English letters.
        //
        //- Brainstorm
        //Ex:
        //Input: s = "bbbab"
        //Output: 4
        //Explanation: One possible longest palindromic subsequence is "bbbb".
        //
        //- dp[i][j] store thông max length của palindrome subsequences that we can find in (i,j)
        //###### code
//        if(s.charAt(j)==s.charAt(k)){
//            //j=0, k=1 => j+1=2, k=0
//            dp[j][k]=dp[j+1][k-1]+2;
//        }else{
//            //j=0, k=1:
//            //+ j+1=2, k=0
//            //+ j=0, k=0
//            dp[j][k]=Math.max(dp[j+1][k], dp[j][k-1]);
//        }
        //######
        //- init:
        //+ dp[i][i]=1
        //Ex:
        //s='aca'
        //- return 3
        //1.2, Optimization
        //
        //Ex:
        //String s = "bbbab";
        //- Ta sẽ loop khác đi 1 chút:
        //  + i : n-1 -> 0
        //  + j : i+1 -> n-1
        //- i=n-1
        //  + j=n ==>ignore
        //- i=n-2
        //  + j=n-1 ==>[n-2,n-1]
        //- i=n-3
        //  + j=n-2 ==>[n-3,n-2] => based on [n-2, n-3]
        //  + j=n-1 ==>[n-3,n-1] => based on [n-2, n-2]
        //- i=n-4
        //  + j=n-3 ==>[n-4,n-3] => based on [n-3, n-4]
        //  + j=n-2 ==>[n-4,n-2] => based on [n-3, n-1] ==> j=n-1
        //  + j=n-1 ==>[n-4,n-1] => based on [n-3, n-2] ==> j=n-2
        //* Formula:
        //- s[i]==s[j] : dp[j]=prev[j-1]+2
        //- s[i]!=s[j] : dp[j]=Max(prev[j-1], dp[j-1]) ==> vừa tính xong (n-3,n-2) --> Có thể tính theo (n-3,n-1)
        //  + (i,j) -> (i+1,j-1)
        //
        //** Rule:
        //- Khi i giảm đi --> Tương ứng với việc bù thêm 1 giá trị i nữa
        //- Và giá trị (i,j) sẽ vẫn được tính bằng (i+1,j-1) như bình thường do:
        //  + i=x --> all(j=i+1) đã được tính rồi ==> Ta có thể dùng lại được.
        //
        //- Dp role:
        //+ dp[i] : max length của palindrome string với (j=i)
        //  + Length thì all length cho đến thời điểm xét.
        //
        //Ex:
        //String s = "cacab";
        //dp=[1,1,3,3,4]
        //
        //1.3, Complexity
        //- N is the length of the string
        //- Space:
        //  + Pre-optimization: O(n^2)
        //  + After-optimization : O(n)
        //- Time: O(n^2)
        //
//        String s = "bbbab";
//        String s = "cbbd";
//        String s = "c";
        String s = "aca";
        System.out.println(longestPalindromeSubseq(s));
        System.out.println(longestPalindromeSubseqOptimization(s));
        //#Reference:
        //730. Count Different Palindromic Subsequences
        //1682. Longest Palindromic Subsequence II
        //1771. Maximize Palindrome Length From Subsequences
    }
}
