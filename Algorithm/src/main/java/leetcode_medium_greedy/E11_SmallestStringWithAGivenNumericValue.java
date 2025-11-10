package leetcode_medium_greedy;

public class E11_SmallestStringWithAGivenNumericValue {

    public static String getSmallestString(int n, int k) {
//        char[] indexToChar=new char[26];
//
//        for (int i = 0; i < 26; i++) {
//            indexToChar[i]=(char)('a'+i);
//        }
        int remainingVal=k;
        StringBuilder rs=new StringBuilder();

        //a = 1
        //b = 2
        //...
        //z = 26
        for(int i=n-1;i>=0;i--){
            int curVal = Math.min('z'-'a'+1, remainingVal - i);
            rs.append((char)(curVal+'a'-1));
//            System.out.printf("%s %s\n", curVal-1, rs);
            remainingVal-=curVal;
        }
        rs.reverse();
        return rs.toString();
    }


    public static String getSmallestStringRefer(int n, int k) {
//        char[] indexToChar=new char[26];
//
//        for (int i = 0; i < 26; i++) {
//            indexToChar[i]=(char)('a'+i);
//        }
        int remainingVal=k;
        char[] s=new char[n];

        //a = 1
        //b = 2
        //...
        //z = 26
        int index=n-1;
        for(int i=n-1;i>=0;i--){
            int curVal = Math.min('z'-'a'+1, remainingVal - i);
            s[index--]=((char)(curVal+'a'-1));
//            System.out.printf("%s %s\n", curVal-1, rs);
            remainingVal-=curVal;
        }
        return String.valueOf(s);
    }

    public static void main(String[] args) {
        //** Requirement
        //- (The numeric value of a lowercase character) is defined as (its position) (1-indexed) (in the alphabet),
        // so
        //  + (the numeric value of a) is 1, (the numeric value of b) is 2, (the numeric value of c) is 3, and so on.
        //- (The numeric value of a string) consisting of (lowercase characters) is defined as (the sum of its characters' numeric values).
        //  + For example, the numeric value of the string "abe" is equal to 1 + 2 + 5 = 8.
        //- You are given (two integers n and k).
        //
        //* Return (the lexicographically smallest string) with (length equal to n) and ("numeric value" equal to k).
        //
        //- Note that (a string x) is (lexicographically smaller than) (string y) if x comes (before) y (in dictionary order),
        // that is, either (x) is (a prefix) of y, or if (i) is (the first position)
        // such that (x[i] != y[i]), then (x[i] comes before y[i]) in alphabetic order.
        //
        //Example 1:
        //
        //Input: n = 3, k = 27
        //Output: "aay"
        //Explanation: The numeric value of the string is 1 + 1 + 25 = 27,
        // and it is the smallest string with such a value and length equal to 3.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n <= 10^5
        //n <= k <= 26 * n
        //  + Time: O(n*k)
        //
        //* Brainstorm:
        //
        //Input: n = 3, k = 27
        //Output: "aay"
        //
        //- k<n ==> We don't have any string
        //- If we want to have (a smallest string)
        //  + We need to put (the biggest one) to the (last of the string)
        //Ex:
        //n=3, k=27
        //ith=3 ==> (k-2)=25 ==> 'y'=25
        //  ==> xxy
        //ith=2 ==> 2 ==>
        //- One loop
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- Dùng stringbuilder is (not efficent) like using (array of chars) with (fixed length)
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        int n = 3, k = 27;
        System.out.println(getSmallestString(n, k));
        System.out.println(getSmallestStringRefer(n, k));
        //
        //#Reference:
        //2478. Number of Beautiful Partitions - HARD
        //3413. Maximum Coins From K Consecutive Bags
        //1881. Maximum Value after Insertion
    }
}
