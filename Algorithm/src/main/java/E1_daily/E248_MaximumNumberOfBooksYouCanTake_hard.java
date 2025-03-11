package E1_daily;

public class E248_MaximumNumberOfBooksYouCanTake_hard {

    public static int countDistinctStrings(String s, int k) {
        int n=s.length();
        long rs=1;

        for(int i=0;i<=n-k;i++){
            rs=(rs*2)%1_000_000_007;
        }
        return (int) rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a binary string s) and (a positive integer k).
        //- You can apply the following operation on the string any number of times:
        //  + Choose (any substring of size k) from s and flip all its characters, that is, turn all 1's into 0's, and all 0's into 1's.
        //* Return (the number of distinct strings) you can obtain.
        //- Since the answer may be too large, return it modulo 10^9 + 7.
        //
        //* Note that:
        //  + A binary string is a string that consists only of the characters 0 and 1.
        //  + A substring is a contiguous part of a string.
        //
        //Example 1:
        //
        //Input: s = "1001", k = 3
        //Output: 4
        //Explanation: We can obtain the following strings:
        //- Applying no operation on the string gives s = "1001".
        //- Applying one operation on the substring starting at index 0 gives s = "0111".
        //- Applying one operation on the substring starting at index 1 gives s = "1110".
        //- Applying one operation on both the substrings starting at indices 0 and 1 gives s = "0000".
        //It can be shown that we cannot obtain any other string, so the answer is 4.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= k <= s.length <= 10^5
        //s[i] is either 0 or 1.
        //  + k<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //- If we flip one bit:
        //  + odd time: flip
        //  + even time: Keep the original value
        //
        //Ex:
        //10011001, k=4
        //10(0110)01
        //100(1100)1
        //= 10(1)11(1)01
        //- k=4, for indices = [0,1,2,3]
        //  + ==> We can flip = [0,3],[1,2]
        //Ex:
        //0101,k=4
        //  + 1010: flip all
        //001010,k=4
        //  + 1010: flip all
        //  + 0101: flip indices = [0,3]
        //  + 0101: flip indices = [0,1,2,3]
        //==> It seems quite complex
        //- Dynamic programming?
        //- Top down:
        //Ex:
        //1010|101
        //  + For the first part we only need to (change a little bit) + keep (the remaining part)
        //  ==> We have new string
        //- We start from (k) -> (i>k)
        //  + [0...k]
        //      [.....i]
        //  ==> For each bit we can choose to flip or not
        //- For each index:
        //  + We got 2 way to choose (index=i)
        //- Rs = pow(2,n-k+1)
        //
        //1.1, Special cases
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        String s = "1001";
        int k = 3;
        System.out.println(countDistinctStrings(s, k));
    }
}
