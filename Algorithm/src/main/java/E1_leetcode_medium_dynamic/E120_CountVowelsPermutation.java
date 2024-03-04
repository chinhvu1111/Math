package E1_leetcode_medium_dynamic;

public class E120_CountVowelsPermutation {

    public static int countVowelPermutation(int n) {
        int[][] dp=new int[n+1][5];
        int mod=1_000_000_007;

        for(int i=0;i<5;i++){
            dp[1][i]=1;
        }
        for(int i=2;i<=n;i++){
            //+ a=0
            //+ e=1
            //+ i=2
            //+ o=3
            //+ u=4
            //
            //+ a(0) <- e (1)
            //+ e(1) <- a (0)
            //+ e(1) <- i (2)
            //+ i(2) <- all
            //+ o(3) <- i (2)
            //+ o(3) <- u (4)
            //+ u(4) <- a (0)
            dp[i][0]=(((dp[i][0] + dp[i-1][1])%mod+ dp[i-1][4])%mod + dp[i-1][2])%mod;
            dp[i][1]=((dp[i][1] + dp[i-1][0])%mod + dp[i-1][2])%mod;
            dp[i][2]=((dp[i][2] + dp[i-1][1])%mod + dp[i-1][3])%mod;
            dp[i][3]=(dp[i][3] + dp[i-1][2])%mod;
            dp[i][4]=((dp[i][4] + dp[i-1][3])%mod + dp[i-1][2])%mod;
        }
        int rs=0;

        for(int i=0;i<5;i++){
            rs=(rs + dp[n][i])%mod;
        }
        return rs;
    }

    public static int countVowelPermutationOptimization(int n) {
        int[][] dp=new int[3][5];
        int mod=1_000_000_007;

        for(int i=0;i<5;i++){
            dp[1][i]=1;
        }
        for(int i=2;i<=n;i++){
            //+ a=0
            //+ e=1
            //+ i=2
            //+ o=3
            //+ u=4
            //
            //+ a(0) <- e (1)
            //+ e(1) <- a (0)
            //+ e(1) <- i (2)
            //+ i(2) <- all
            //+ o(3) <- i (2)
            //+ o(3) <- u (4)
            //+ u(4) <- a (0)
            dp[2][0]=(((dp[2][0] + dp[1][1])%mod+ dp[1][4])%mod + dp[1][2])%mod;
            dp[2][1]=((dp[2][1] + dp[1][0])%mod + dp[1][2])%mod;
            dp[2][2]=((dp[2][2] + dp[1][1])%mod + dp[1][3])%mod;
            dp[2][3]=(dp[2][3] + dp[1][2])%mod;
            dp[2][4]=((dp[2][4] + dp[1][3])%mod + dp[1][2])%mod;
            dp[1][0]=dp[2][0];
            dp[1][1]=dp[2][1];
            dp[1][2]=dp[2][2];
            dp[1][3]=dp[2][3];
            dp[1][4]=dp[2][4];
            dp[2][0]=0;
            dp[2][1]=0;
            dp[2][2]=0;
            dp[2][3]=0;
            dp[2][4]=0;
        }
        int rs=0;

        for(int i=0;i<5;i++){
            rs=(rs + dp[1][i])%mod;
        }
        return rs;
    }

    public static int countVowelPermutationOptimization1(int n) {
        int[][] dp=new int[1][5];
        int mod=1_000_000_007;

        for(int i=0;i<5;i++){
            dp[0][i]=1;
        }
        for(int i=2;i<=n;i++){
            //+ a=0
            //+ e=1
            //+ i=2
            //+ o=3
            //+ u=4
            //
            //+ a(0) <- e (1)
            //+ e(1) <- a (0)
            //+ e(1) <- i (2)
            //+ i(2) <- all
            //+ o(3) <- i (2)
            //+ o(3) <- u (4)
            //+ u(4) <- a (0)
            int aCountNew=((dp[0][1]+ dp[0][4])%mod + dp[0][2])%mod;
            int eCountNew=(dp[0][0] + dp[0][2])%mod;
            int iCountNew=(dp[0][1] + dp[0][3])%mod;
            int oCountNew=dp[0][2];
            int uCountNew=(dp[0][3] + dp[0][2])%mod;
            dp[0][0]=aCountNew;
            dp[0][1]=eCountNew;
            dp[0][2]=iCountNew;
            dp[0][3]=oCountNew;
            dp[0][4]=uCountNew;
        }
        int rs=0;

        for(int i=0;i<5;i++){
            rs=(rs + dp[0][i])%mod;
        }
        return rs;
    }

    public static int countVowelPermutationOptimizationReference(int n) {
        long aCount = 1, eCount = 1, iCount = 1, oCount = 1, uCount = 1;
        int MOD = 1000000007;

        for (int i = 1; i < n; i++) {
            long aCountNew = (eCount + iCount + uCount) % MOD;
            long eCountNew = (aCount + iCount) % MOD;
            long iCountNew = (eCount + oCount) % MOD;
            long oCountNew = (iCount) % MOD;
            long uCountNew = (iCount + oCount) % MOD;
            aCount = aCountNew;
            eCount = eCountNew;
            iCount = iCountNew;
            oCount = oCountNew;
            uCount = uCountNew;
        }
        long result = (aCount + eCount + iCount + oCount + uCount)  % MOD;
        return (int)result;
    }

    public static void main(String[] args) {
        //** Requirement:
        //Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
        //- Each vowel 'a' may only be followed by an 'e'.
        //  + ae
        //- Each vowel 'e' may only be followed by an 'a' or an 'i'.
        //  + ea, ei
        //- Each vowel 'i' may not be followed by another 'i'.
        //  + 'i' -> all except for 'i'
        //- Each vowel 'o' may only be followed by an 'i' or a 'u'.
        //  + oi, ou
        //- Each vowel 'u' may only be followed by an 'a'.
        //  + ua
        //- Since the answer may be too large, return it modulo 10^9 + 7.
        //Given an integer n,
        //* Your task is to count (how many strings) of (length n) follow the rules above:
        //  - String được tạo hởi all vowels
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= n <= 2 * 10^4
        //==> Có thể xử lý trong O(n) or O(n*k)
        //
        //- Brainstorm
        //+ a <- e (1)
        //+ e <- a (0)
        //+ e <- i (2)
        //+ i <- all
        //+ o <- i (2)
        //+ o <- u (4)
        //+ u <- a (0)
        //
        //- Vowels = ['a', 'e', 'i', 'o', 'u']
        //+ a=0
        //+ e=1
        //+ i=2
        //+ o=3
        //+ u=4
        //- Bắt đầu từ length=1 -> n
        //
        //1.2, Optimization
        //- Integer --> Long đỡ phải modulo
        //- Optimize space
        //  + Ta chỉ cần 1 layer đằng trước là được.
        //  + int val=a+b+c
        //  + a=val
        //  ==> Suy ra là được.
        //
        //1.3, Complexity
        //- Space: O(n*5) ~ O(n)
        //- Time: O(n)
        //
        //#Reference:
        //2930. Number of Strings Which Can Be Rearranged to Contain Substring
//        int n=2;
//        int n=5;
        int n=144;
        System.out.println(countVowelPermutation(n));
        System.out.println(countVowelPermutationOptimization(n));
        System.out.println(countVowelPermutationOptimization1(n));
        System.out.println(countVowelPermutationOptimizationReference(n));
    }
}
