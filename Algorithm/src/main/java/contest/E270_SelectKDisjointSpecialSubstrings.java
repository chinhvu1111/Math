package contest;

public class E270_SelectKDisjointSpecialSubstrings {

    public static boolean maxSubstringLength(String s, int k) {
        int n=s.length();
        boolean[][] dp=new boolean[n+1][26];
        int[][] prefixSum=new int[n+1][26];

        dp[0][0]=true;
//        System.out.println(n);

        for(int i=1;i<=n;i++){
            for(int j=0;j<26;j++){
                prefixSum[i][j]=prefixSum[i-1][j];
            }
//            System.out.println(i);
            prefixSum[i][s.charAt(i-1)-'a']++;
        }

        for (int i = 0; i < n; i++) {
            if(i+1<k){
                continue;
            }
            //a,b,c
            //c,[a,b,c],k=3
            //prefix[x] == count[x]
            //==> Ok
            boolean isValid=true;
            for(int j=0;j<26;j++){
                if(prefixSum[i+1][j]-prefixSum[i-k+1][j]!=prefixSum[i+1][j]){
                    isValid=false;
                    break;
                }
            }
            if(!isValid){
                continue;
            }
            for(int j=0;j<k;j++){
                if(!dp[i-k+1][j]){
                    continue;
                }
                dp[i+1][j+1]=dp[i-k+1][j];
                if(dp[i+1][j+1]&&j+1==k){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a string s of length n and an integer k, determine whether
        //  + it is possible to select (k disjoint special substrings).
        //- (A special substring) is a substring where:
        //  + Any character present inside the substring (should not appear) outside it in the string.
        //  + The substring is (not) (the entire string s).
        //* Note that all k substrings must be disjoint, meaning they cannot overlap.
        //* Return true if it is possible to select k such disjoint special substrings; otherwise, return false.
        //- A substring is a contiguous non-empty sequence of characters within a string.
        //
        //* Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= n == s.length <= 5 * 10^4
        //0 <= k <= 26
        //s consists only of lowercase English letters.
        //  + length<=5*10^4 ==> Time: O(n*k)
        //
        //- Brainstorm
        //- dp[i][h]:
        //  + We can select h substring from [0->i]
        //
        //
        String s = "abcdbaefab";
        int k = 2;
        System.out.println(maxSubstringLength(s, k));
    }
}
