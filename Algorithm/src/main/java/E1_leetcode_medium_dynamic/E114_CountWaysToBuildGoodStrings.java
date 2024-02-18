package E1_leetcode_medium_dynamic;

public class E114_CountWaysToBuildGoodStrings {

    public static int countGoodStrings(int low, int high, int zero, int one) {
        long[][] dp = new long[high + 1][2];
        int mod=1_000_000_007;

        //zero=1, one=2
        //Length:      0,1,2,3,4,5
        //Zero value:  0,1,1,2,3,5
        //One value:   0,0,1,1,2,3
        for (int i = 1; i <= high; i++) {
            //Zero=2
            //0,1,2
            if(i==zero){
                dp[i][0]=1;
            }else if (i - zero >= 0) {
                //0,1,2,3
                dp[i][0] = (dp[i - zero ][0] + dp[i - zero ][1])%mod;
            }
            if(i==one){
                dp[i][1]=1;
            }else if (i - one >= 0) {
                //0,1,2,3
                dp[i][1] = (dp[i - one ][0] + dp[i - one ][1])%mod;
            }
        }
        int rs = 0;
        for (int i = low; i <= high; i++) {
            rs = (int) ((rs + dp[i][0]%mod)%mod);
            rs = (int) ((rs + dp[i][1]%mod)%mod);
//            System.out.printf("Length=%s : %s %s\n", i, dp[i][0], dp[i][1]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given the integers (zero, one, low, and high),
        // we can construct a string by starting with an empty string, and then at each step perform either of the following:
        //+ Append the character '0' zero times.
        //+ Append the character '1' one times.
        //- This can be performed any number of times.
        //- A good string is a string constructed by the above process having a (length) between (low and high) (inclusive).
        //
        //* Return (the number of different good strings) that can be constructed satisfying these properties.
        // Since the answer can be large, return it modulo 109 + 7.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= low <= high <= 10^5
        //1 <= zero, one <= low
        //
        //- Brainstorm
        //- Top down trước:
        //
        //
        //- Bottom up approach:
        //Ex:
        //low = 2, high = 3, zero = 1, one = 2
        //- Thay vì dùng string --> ta có thể quy về dp được không
        //+ dp[i][0] : Danh sách chuỗi có length=i và kết thúc bởi 0
        //+ dp[i][1] : Danh sách chuỗi có length=i và kết thúc bởi 1
        //- Formula:
        //- dp[i][0]=Math(dp[i-zero][0], dp[i-zero][1])
        //- dp[i][1]=Math(dp[i-one][0], dp[i-one][1])
        //
        //1.2, Optimization
        //
        //1.3, Complexity
        //- Time : O(high)
        //- Space : O(high)
        //
//        int low = 2, high = 3, zero = 1, one = 2;
//        int low = 3, high = 3, zero = 1, one = 1;
//        int low = 200, high = 200, zero = 10, one = 1;
        int low = 500, high = 500, zero = 5, one = 2;
        System.out.println(countGoodStrings(low, high, zero, one));
        //#Reference:
        //996. Number of Squareful Arrays
        //1092. Shortest Common Supersequence
        //2791. Count Paths That Can Form a Palindrome in a Tree
    }
}
