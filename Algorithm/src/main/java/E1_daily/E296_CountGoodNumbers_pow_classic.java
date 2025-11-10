package E1_daily;

import java.util.HashMap;

public class E296_CountGoodNumbers_pow_classic {

    public static Long pow(int x, long n, HashMap<Long, Long> dp, int mod){
        if(n==0){
            return 1L;
        }
        if(dp.containsKey(n)){
            return dp.get(n);
        }
        if(n==1){
            return (long) x;
        }
        long curRs;
        //n=3
        //x^(n/2)
        if(n%2==1){
            curRs = ((pow(x, (n - 1), dp, mod) * x) % mod);
        }else{
            //n=4
            curRs = (pow(x, n / 2, dp, mod) % mod);
            curRs=(curRs*curRs)%mod;
        }
        dp.put(n, curRs);
        return curRs;
    }

    public static Long pow1(int x, long n, HashMap<Long, Long> dp, int mod){
        if(n==0){
            return 1L;
        }
        if(n==1){
            return (long) x;
        }
        if(dp.containsKey(n)){
            return dp.get(n);
        }
        Long curRs = (pow1(x, n/2, dp, mod)%mod*pow1(x, n-(n/2), dp, mod)%mod)%mod;
        dp.put(n, curRs);
        return curRs;
    }

    public static double myPowFinal(double x, long n, int mod) {
        long exponent=n;

        if(n<0){
            exponent=-(long)n;
            x=1/x;
        }
        double rs=1;

        while(exponent!=0){
            if(exponent%2==1){
                rs=(rs*x)%mod;
            }
            x=(x*x)%mod;
            exponent/=2;
        }
        return rs;
    }

    public static long quickmul(int x, long y, int mod) {
        long ret = 1;
        long mul = x;
        while (y > 0) {
            if (y % 2 == 1) {
                ret = (ret * mul) % mod;
            }
            mul = (mul * mul) % mod;
            y /= 2;
        }

        return ret;
    }

    public static int countGoodNumbers(long n) {
        int mod = 1_000_000_007;
//        double x=1;
//        for (int i = 0; i < n; i++) {
//            if(i%2==0){
//                x=x*5;
//            }else{
//                x=x*4;
//            }
//            x=x%mod;
////            System.out.println(x);
//        }
        HashMap<Long, Long> dp=new HashMap<>();
        long rs = pow(5, (n+1) / 2, dp, mod)%mod;
        dp=new HashMap<>();
        rs= ((rs * pow(4, n / 2, dp, mod)) % mod);
        //
//        long rs = (long) (myPowFinal(5, (n+1) / 2, mod) * myPowFinal(4, n / 2, mod))%mod;
//        long rs = (long) myPowFinal(20, n / 2, mod);
        //5,4,5,4
//        if(n%2==1){
//            rs=(rs*5)%mod;
//        }
        //
        //(5*4)^((n+1)/2)
//        long rs = n%2==1? (long) (Math.pow(5, n / 2+1)%mod):(long) (Math.pow(5, n / 2)%mod);
//        System.out.println(rs);
//        rs= (long) ((rs * Math.pow(4, n / 2))%mod);
        //
        //* Final solution
//        long rs = (quickmul(5, (n+1) / 2, mod) * quickmul(4, n / 2, mod)) %mod;
        return (int) rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- (A digit string) is good
        //  + if the digits (0-indexed) at (even indices) are (even) and
        //  + the digits at (odd indices) are prime (2, 3, 5, or 7).
        //For example, "2582" is good because the digits (2 and 8) at (even positions) are (even)
        // and the digits (5 and 2) at odd positions are prime.
        //- However, "3245" is not good because 3 is at an even index but is not even.
        //- Given an integer n,
        //* return (the total number of (good digit strings)) of (length n).
        //- Since the answer may be large, return it modulo 10^9 + 7.
        //- A digit string is a string consisting of digits 0 through 9 that may contain leading zeros.
        //
        //Example 1:
        //
        //Input: n = 1
        //Output: 5
        //Explanation: The good numbers of length 1 are "0", "2", "4", "6", "8".
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n <= 10^15
        //  + Time: O(Log(n))
        //
        //* Brainstorm:
        //
        //Example 1:
        //
        //Input: n = 1
        //Output: 5
        //Explanation: The good numbers of length 1 are "0", "2", "4", "6", "8".
        //
        //n=4
        //x = abcd
        //- The number of even: 0,2,4,6,8 (5)
        //- The number of prime: 2, 3, 5, or 7 (4)
        //==> The number of good digit string: 5*4*5*4 = 400
        //n=3
        //x = abc
        //Result = 5*4*5
        //==> Result = 5^((n+1)/2)*4^(n/2)
        //
        //- Vấn đề ở đây là nếu:
        //  + x*5/4 lần lượt thì ok
        //  ==> Nhưng nếu xét riêng chỉ pow(5) và pow(4) ==> Sẽ bị sai số
        //
        //* Problem:
        //- Nếu dùng double ==> khi module cho MOD
        //  + Có thể sẽ sai
        //  ==> Nếu dùng pow sẽ sai.
        //- (double size) >= (long size) (Lớn hơn rất nhiều)
        //- Doubles are precise up to 15 decimal places, whereas longs store integers that are accurate up to 2^63 numbers.
        //
        //** Vậy double hay long lớn hơn?
        //  + Về phạm vi giá trị có thể biểu diễn: double có phạm vi lớn hơn nhiều so với long.
        //  + Double.MAX_VALUE ≈ 1.7976931348623157 × 10^308
        //  + Long.MAX_VALUE = 9,223,372,036,854,775,807 (khoảng 9 × 10^18)
        //
        //- Tính pow có thể dùng (iteration hoặc memo)
        //- Dùng iteration:
        //  + Ta sẽ phân theo (n%2==0 và n%2==1)
        //  ==> Tính kết quả từ (x,x^2,x^4,...,x^2*k)
        //* Main point:
        //- Nếu (exponent%2==1):
        //  + rs=rs*x (Nhân lẻ ra là được)
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(log(n)) => O(1)
        //- Time: O(log(n))
        //
        int n=50;
        //Expected: 564908303
//        long n=806166225460393L;
        //Expected: 643535977
        System.out.println(countGoodNumbers(n));
    }
}
