package contest;

public class E284_InterleavingString_classic {

    public static boolean isInterleave(String s1, String s2, String s3) {
        int n=s1.length();
        int m=s2.length();
        int l=s3.length();

        if(n+m!=l){
            return false;
        }
        if(n==0&&m==0&&l==0){
            return true;
        }
        boolean[][][] dp=new boolean[l+1][n+1][m+1];
        dp[0][0][0]=true;
//        boolean rs=false;

        for(int i=1;i<=l;i++){
//            System.out.printf("len: %s\n",i);
            for(int j=0;j<=n;j++){
                int k = i-j;
                if(k<0||k>m){
                    continue;
                }
//                System.out.printf("i: %s, j: %s, k: %s, dp[i-1][j][k]: %s\n", i, j, k, dp[i-1][j][k]);
                if(j>=1&&s3.charAt(i-1)==s1.charAt(j-1)&&dp[i-1][j-1][k]){
                    dp[i][j][k] = dp[i-1][j-1][k];
//                    System.out.printf("s1: %s %s %s\n", i, j, k);
                    if(i==l){
                        return true;
                    }
                }
                if(k>=1&&s3.charAt(i-1)==s2.charAt(k-1)&&dp[i-1][j][k-1]){
                    dp[i][j][k] = dp[i-1][j][k-1];
//                    System.out.printf("s2: %s %s %s\n", i, j, k);
                    if(i==l){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean isInterleaveOptimization(String s1, String s2, String s3) {
        int n=s1.length();
        int m=s2.length();
        int l=s3.length();

        if(n+m!=l){
            return false;
        }
        if(n==0&&m==0&&l==0){
            return true;
        }
        boolean[][][] dp=new boolean[2][n+1][m+1];
        dp[0][0][0]=true;
//        boolean rs=false;

        for(int i=1;i<=l;i++){
//            System.out.printf("len: %s\n",i);
            for(int j=0;j<=n;j++){
                int k = i-j;
                if(k<0||k>m){
                    continue;
                }
//                System.out.printf("i: %s, j: %s, k: %s, dp[i-1][j][k]: %s\n", i, j, k, dp[i-1][j][k]);
                if(j>=1&&s3.charAt(i-1)==s1.charAt(j-1)&&dp[(i-1)%2][j-1][k]){
                    dp[i%2][j][k] = dp[(i-1)%2][j-1][k];
//                    System.out.printf("s1: %s %s %s\n", i, j, k);
                    if(i==l){
                        return true;
                    }
                }
                if(k>=1&&s3.charAt(i-1)==s2.charAt(k-1)&&dp[(i-1)%2][j][k-1]){
                    dp[i%2][j][k] = dp[(i-1)%2][j][k-1];
//                    System.out.printf("s2: %s %s %s\n", i, j, k);
                    if(i==l){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
        //An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:
        //  + s = s1 + s2 + ... + sn
        //  + t = t1 + t2 + ... + tm
        //  + |n - m| <= 1
        //* (The interleaving) is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
        //* Note: a + b is the concatenation of strings a and b.
        //
        //Example 1:
        //
        //Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
        //Output: true
        //Explanation: One way to obtain s3 is:
        //Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
        //Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
        //Since s3 can be obtained by interleaving s1 and s2, we return true.
        //
        // Idea
        //1. Binary search
        //1.0,
        //- Method-1:
        //+ Constraints:
        //0 <= s1.length, s2.length <= 100
        //0 <= s3.length <= 200
        //s1, s2, and s3 consist of lowercase English letters.
        //  + lower case: O(26)
        //  + Length <= 100,200 ==> Time: O(n*m)
        //
        //- Brainstorm
        //- We don't need to care about the sole behaviour:
        //  + Because (multiple chars) are in the same string that could be (grouped) into 1 element
        //- We can use count chars?
        //  + We can not do that because of order of chars in s1, s2, s3
        //- The characteristic of order:
        //  ==> Two pointers?
        //Ex:
        //s1 = aab
        //s2 = ac
        //s3 = acab
        //- s1 and s2 have same prefix as "a"
        //  + In this case, we need to choose "a" from s2 because after the "a" character is "c"
        //  ==> Getting "a" from s2 = "c" ==> We can get that
        //* For 2 pointers:
        //  + We can not choose the "a" from both of strings
        //  ==> We can use 2 pointers
        //- For this one, we can use dynamic programming:
        //- dp[i][j]?
        //Ex:
        //s1 = aab
        //s2 = ac
        //s3 = acab
        //- Each index from s3 we can choose:
        //  + From s1
        //  + From s2
        //- How to solve this problem using bruce force?
        //- Index:
        //  + s1: i
        //  + s2: j
        //  + s3: k
        //
        //dp[i][j][k] means:
        //  + It is possible to reconstruct string s3[k:l] from s1[i:n] and s2[j:m]
        //- dp[i][j][k] = dp[i-1][j][k-1] | dp[i][j-1][k-1]
        //  + i-1/j-1: Because we are following (the order) of (s1,s2)
        //
        //Ex:
        //s1 = aab
        //s2 = ac
        //s3 = acab
        //dp[0][0][0]=true
        //- l=1:
        //  + i:0, j=1
        //  + i:1, j=0
        //  if: s2[1-1]=='s3[1-1] && dp[1][0][0]:
        //      dp[1][0][1]= dp[1][0][0]
        //  if: s1[1-1]=='s3[1-1] && dp[1][0][0]:
        //      dp[1][1][0]= dp[1][0][0]
        //
        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        //
        //aa: s1
        //d: s2
        //dp[3][2][1] = dp[2][2][0]
        //  + k = (i-1-j)
        //
        //1.1, Cases
        //1.2, Optimization
        //1.3, Complexity
        //- Space: O(n*m*k) ==> O(n*m)
        //- Time: O(k*m)
        //
        System.out.println(isInterleave(s1, s2, s3));
        System.out.println(isInterleaveOptimization(s1, s2, s3));
        //
        //#Reference:
        //1960. Maximum Product of the Length of Two Palindromic Substrings - HARD
        //2552. Count Increasing Quadruplets - HARD
        //2484. Count Palindromic Subsequences - HARD
    }
}
