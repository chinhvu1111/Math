package E1_leetcode_medium_dynamic;

public class E139_PalindromePartitioningIV {

    public static boolean checkPartitioning(String s) {
        int n=s.length();
        boolean[][] dp=new boolean[n+1][3];
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
        for(int i=1;i<=n;i++){
            dp[i][0]=isPalindrome[0][i-1];
        }
        for(int i=2;i<=n;i++){
            for(int j=i-1;j>=0;j--){
                for(int l=1;l<=2;l++){
                    if(dp[j][l-1]&&isPalindrome[j][i-1]){
                        dp[i][l]=true;
//                        System.out.printf("i=%s, l=%s, %s\n", i, l, dp[i][l]);
                    }
                }
            }
        }
        return dp[n][2];
    }

    public static void main(String[] args) {
        //** Requirement:
        //* Given a string s, return true
        // if it is possible to split the string s into (three) (non-empty palindromic) substrings.
        // Otherwise, return false.
        //A string is said to be palindrome if it the same string when reversed.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //3 <= s.length <= 2000
        //s consists only of lowercase English letters.
        //
        //- Brainstorm
        //- Cần chia string thành 3 phần:
        //- dp[i][4] => 3 không phải 4 vì là 3 phần --> cut 2 lần thôi:
        //  + dp[i][j] = True: Thể hiện string đến vị trí index=i có thể chia thành (j) phần (Mà tất cả các string là palindrome)
        //- dp[i][j]=true:
        //  + Xuất hiện dp[k][j-1] = true (k: 0 -> i-1)
        //  + Init:
        //  + dp[i][0] = isPalindrome[0][i]
        //
//        String s = "abcbdd";
        String s = "bcbddxy";
        System.out.println(checkPartitioning(s));
        //#Reference:
        //1278. Palindrome Partitioning III
        //2472. Maximum Number of Non-overlapping Palindrome Substrings
    }
}
