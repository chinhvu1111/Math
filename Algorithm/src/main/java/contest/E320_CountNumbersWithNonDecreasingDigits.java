package contest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E320_CountNumbersWithNonDecreasingDigits {
    static final int MOD = 1_000_000_007;

    static long getValidCount(List<Integer> digits, int base) {
        int n = digits.size();
        Long[][][][] memo = new Long[n + 1][base + 1][2][2];
        return topDownSolution(0, 0, 1, 1, digits, base, memo);
    }

    static long topDownSolution(int pos, int lastDigit, int tight, int leadingZero, List<Integer> digits, int base, Long[][][][] memo) {
        if (pos == digits.size()) return leadingZero == 1 ? 0 : 1;

        if (memo[pos][lastDigit][tight][leadingZero] != null) {
            return memo[pos][lastDigit][tight][leadingZero];
        }

        int limit = tight == 1 ? digits.get(pos) : base - 1;
        long res = 0;

        for (int i = 0; i <= limit; i++) {
            if (leadingZero == 1 || i >= lastDigit) {
                int newTight = (tight == 1 && i == limit) ? 1 : 0;
                int newLeadingZero = (leadingZero == 1 && i == 0) ? 1 : 0;
                res = (res + topDownSolution(pos + 1, newLeadingZero == 1 ? 0 : i, newTight, newLeadingZero, digits, base, memo)) % MOD;
            }
        }

        return memo[pos][lastDigit][tight][leadingZero] = res;
    }
    static List<Integer> convertToNumberBase(String num, int base) {
        List<Integer> rs = new ArrayList<>();
        BigInteger curNum = new BigInteger(num);
        BigInteger baseNum = BigInteger.valueOf(base);
        while (curNum.compareTo(BigInteger.ZERO) > 0) {
            rs.add(curNum.mod(baseNum).intValue());
            curNum = curNum.divide(baseNum);
        }
        Collections.reverse(rs);
        return rs.isEmpty() ? Collections.singletonList(0) : rs;
    }

    static String getPrevNum(String s) {
        StringBuilder rs = new StringBuilder(s);
        int i = rs.length() - 1;

        while (i >= 0) {
            if (rs.charAt(i) == '0') {
                rs.setCharAt(i, '9');
                i--;
            } else {
                rs.setCharAt(i, (char)(rs.charAt(i) - 1));
                break;
            }
        }
        while (rs.length() > 1 && rs.charAt(0) == '0') {
            rs.deleteCharAt(0);
        }

        return rs.toString();
    }

    public static int countNumbers(String l, String r, int base) {
        List<Integer> rDigits = convertToNumberBase(r, base);
        List<Integer> lDigits = convertToNumberBase(getPrevNum(l), base);

        long validCountLeft = getValidCount(rDigits, base);
        long validCountRight = getValidCount(lDigits, base);

        return (int) ((validCountLeft - validCountRight + MOD) % MOD);
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two integers, (l and r), represented as strings, and (an integer b).
        //* Return (the count of integers) in the inclusive range [l, r] whose digits are in (non-decreasing order)
        //  when represented in (base b).
        //- An integer is considered to have (non-decreasing digits) if,
        // when read from left to right (from the most significant digit to the least significant digit),
        //  + each digit is greater than or equal to the previous one.
        //- Since the answer may be too large, return it modulo (10^9 + 7).
        //
        //Example 1:
        //
        //Input: l = "23", r = "28", b = 8
        //Output: 3
        //Explanation:
        //The numbers from 23 to 28 in base 8 are: 27, 30, 31, 32, 33, and 34.
        //Out of these, 27, 33, and 34 have non-decreasing digits. Hence, the output is 3.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= l.length <= r.length <= 100
        //2 <= b <= 10
        //l and r consist only of digits.
        //The value represented by l is less than or equal to the value represented by r.
        //l and r do not contain leading zeros.
        //
        //- Brainstorm
        //
        System.out.println(Integer.toOctalString(28));
        //34
        //28%8 = 4
        //  + 28/8 = 3
        //3%8 = 3
        //==> 34 = 3*8^1 + 4*8^0
        //
        System.out.println(countNumbers("23", "28", 8));
        System.out.println(countNumbers("2", "7", 2));
    }
}
