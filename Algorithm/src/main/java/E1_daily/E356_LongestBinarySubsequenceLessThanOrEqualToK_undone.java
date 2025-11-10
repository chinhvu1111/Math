package E1_daily;

public class E356_LongestBinarySubsequenceLessThanOrEqualToK_undone {

    public static int longestSubsequenceWrong(String s, int k) {
        int n=s.length();
        int numOneBit = 0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='1'){
                numOneBit++;
            }
        }
        int mul=1;
        int count=numOneBit;
        while (count>0&&k-mul>=0){
            k-=mul;
            mul=mul*2;
            count--;
//            System.out.println(k);
        }
//        System.out.println(count);
        return n-numOneBit+(numOneBit-count);
    }

    public static int longestSubsequence(String s, int k) {
        int n=s.length();
        int count=0;
        int bits = (int) (Math.log(k) / Math.log(2)) + 1;
        int mul=0;

        for(int i=n-1;i>=0;i--){
            if(s.charAt(i)=='1'){
                int numBit = n-1-i;
                if(numBit<bits && mul+(1<<numBit)<=k){
                    mul+=1<<numBit;
                    count++;
                }
            }else{
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a binary string s and a positive integer k.
        //* Return (the length of the ("longest") subsequence of s) that makes up (a binary number) (less than or equal to k).
        //Note:
        //- The subsequence can contain (leading zeroes).
        //- (The empty string) is considered to be equal to 0.
        //- A subsequence is a string that can be derived from another string by deleting some
        // or no characters without changing the order of the remaining characters.
        //
        //Example 1:
        //
        //Input: s = "1001010", k = 5
        //Output: 5
        //Explanation: The longest subsequence of s that makes up a binary number less than
        // or equal to 5 is "00010", as this number is equal to 2 in decimal.
        //Note that "00100" and "00101" are also possible, which are equal to 4 and 5 in decimal, respectively.
        //The length of this subsequence is 5, so 5 is returned.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= s.length <= 1000
        //s[i] is either '0' or '1'.
        //1 <= k <= 10^9
        //  + s.length <= 1000 ==> Time: O(n^2)
        //  + k ==> Time: O(log(k))
        //
        //* Brainstorm:
        //- The number of one digit:
        //  + s = 1001010, k=5
        //  count(1) = 3
        //  count(0) = 3
        //  0 -> 5
        //  ==> Find
        //- We need to make the binary number (as small as) possible
        //  + (0=3,1=3)
        //      + (3,1)= 0001
        //      + (3,2)= 00011
        //      + (3,3)= 000111
        //  ==> WRONG
        //Ex:
        //s = "1001010"
        //k = 5
        //  + Reverse: 001010
        //* The main point:
        //- From (right->left) If we meet 1 ==> We need to add as soon as possible because:
        //  + If we continue to go to the left ==> 1 will be bigger and (add 0 Not impact)
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n*log(n))
        //- Space: O(1)
        //
        String s = "1001010";
        int k = 5;
        //0 -> 5
        //000011
        //
        System.out.println(longestSubsequenceWrong(s, k));
        System.out.println(longestSubsequence(s, k));
    }
}
