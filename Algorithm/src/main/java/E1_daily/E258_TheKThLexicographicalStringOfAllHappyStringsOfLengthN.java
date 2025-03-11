package E1_daily;

import java.util.Stack;

public class E258_TheKThLexicographicalStringOfAllHappyStringsOfLengthN {

    public static String rs;

    public static void solution(int index, int prevIndex, char[] arrChar, String s, int[] count, int n, int k) {
        if (index == n) {
            count[0]++;
            if (count[0] == k) {
                rs = s;
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (i != prevIndex) {
                solution(index + 1, i, arrChar, s + arrChar[i], count, n, k);
            }
        }
    }

    public static String getHappyString(int n, int k) {
        char[] c = {'a', 'b', 'c'};
        rs = "";
        solution(0, -1, c, "", new int[1], n, k);
        return rs;
    }

    public static String getHappyStringStack(int n, int k) {
        Stack<String> stringStack = new Stack<>();
        stringStack.push("");
        int indexSortedList = 0;
        //a,b,a,b,d
        //==>

        while (!stringStack.isEmpty()) {
            String curString = stringStack.pop();
            if (curString.length() == n) {
                indexSortedList++;
                if (indexSortedList == k) {
                    return curString;
                }
                continue;
            }
            for (char c = 'c'; c >= 'a'; c--) {
                if (curString.length() == 0 || curString.charAt(curString.length() - 1) != c) {
                    stringStack.push(curString + c);
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        //** Requirement
        //- A happy string is a string that:
        //  + consists only of letters of the set ['a', 'b', 'c'].
        //  + s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
        //- For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
        //- Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
        //* Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
        //
        //Example 1:
        //
        //Input: n = 1, k = 3
        //Output: "c"
        //Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n <= 10
        //1 <= k <= 100
        //  + Time: 3^10 = 50000 ==> It is still ok
        //
        //* Brainstorm:
        //Ex:
        //n=4,k=3
        //a,b
        //==> Only way is the backtracking
        //a,b,a,b
        //a,b,a,c
        //a,b,b,a
        //
        //1.1, Special cases
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(2^n*n)
        //  + Concat(s, s1): O(n)
        //
        //2. Stack
        //2.0,
        //- We can implement this problem using stack
        //* Note:
        //- Every (recursion problem) could be done by using (stack)
        //
        //- Recursion <=> Backtrack
        //
        //* Dùng stack (Đặc điểm):
        //  + For each step, we need to store (the intermediate value) that is different from the recursion method
        //  ==> (Call recursively) represents the idea related to storing (the intermediate data)
        //- ab+(a/c)
        //  + stack = [aba,abc]
        //  ==> Get abc first to check first
        //  ==> We need to sort decrementally
        //  + stack = [abc, aba]
        //  ==> Get aba to increase
        //
        //2.1, Optimization
        //
        //2.2, Complexity
        //- Space: O(2^n)
        //- Time: O(n*2^n)
        //
//        int n = 1;
//        int k = 3;
        int n = 3;
        int k = 9;
        System.out.println(getHappyString(n, k));
        System.out.println(getHappyStringStack(n, k));
        //#Reference:
        //3329. Count Substrings With K-Frequency Characters II (HARD)
        //2067. Number of Equal Count Substrings
        //1927. Sum Game
        //3279. Maximum Total Area Occupied by Pistons (HARD)
        //1366. Rank Teams by Votes
        //1433. Check If a String Can Break Another String
    }
}
