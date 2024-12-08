package E1_daily;

import java.util.Arrays;

public class E162_TakeKOfEachCharacterFromLeftAndRight {

    public static int takeCharacters(String s, int k) {
        if(k==0){
            return 0;
        }
        int n=s.length();
        int[] count=new int[3];
        int right;

        for(right=n-1;right>=0;right--){
            count[s.charAt(right)-'a']++;
            if(count[0]>=k&&count[1]>=k&&count[2]>=k){
                break;
            }
        }
        if(right==n-1||right==-1){
            return -1;
        }
//        System.out.println(right);
        int left;
        //0,1,2
        int rs=n-right;

        for(left=0;left<n;left++){
            while(right<=left){
                count[s.charAt(right)-'a']--;
                right++;
            }
            count[s.charAt(left)-'a']++;
            while(right<n&&count[s.charAt(right)-'a']>k){
                count[s.charAt(right)-'a']--;
                right++;
            }
            if(count[0]>=k&&count[1]>=k&&count[2]>=k){
                rs=Math.min(left+1+n-right, rs);
            }
        }
        return rs;
    }

    public static int takeCharactersSlidingWindow(String s, int k) {
        int[] count = new int[3];
        int n = s.length();

        // Count total occurrences
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Check if we have enough characters
        for (int i = 0; i < 3; i++) {
            if (count[i] < k) return -1;
        }

        int[] window = new int[3];
        int left = 0, maxWindow = 0;

        // Find the longest window that leaves k of each character outside
        for (int right = 0; right < n; right++) {
            window[s.charAt(right) - 'a']++;

            // Shrink window if we take too many characters
            //- Trừ count ở giữa
            while (
                    left <= right &&
                            (count[0] - window[0] < k ||
                                    count[1] - window[1] < k ||
                                    count[2] - window[2] < k)
            ) {
                window[s.charAt(left) - 'a']--;
                left++;
            }

            maxWindow = Math.max(maxWindow, right - left + 1);
        }

        return n - maxWindow;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given a string s consisting of the characters ('a', 'b', and 'c') and a (non-negative) integer k.
        //- Each minute,
        //  + you may take either (the leftmost character) of s, or (the rightmost character) of s.
        //* Return (the minimum number of minutes) needed for you to take (at least) (k of each character), or
        //- return -1 if it is (not possible) to take (k of (each character)).
        //
        //Example 1:
        //Input: s = "(aab)aaaa(caabc)", k = 2 ==> aabbcc
        //Output: 8
        //Explanation:
        //Take (three characters) from the left of s. You now have two 'a' characters, and one 'b' character.
        //Take (five characters) from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
        //A total of 3 + 5 = 8 minutes is needed.
        //It can be proven that 8 is the minimum number of minutes needed.
        //* Get k character for each type of character
        //
        // Idea
        //1. Two pointers
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 10^5
        //s consists of only the letters 'a', 'b', and 'c'.
        //0 <= k <= s.length
        //  + Length <= 10^5 ==> Time: O(n) or O(n*k)
        //
        //- Brainstorm
        //Example 1:
        //Input: s = "(aab)aaaa(caabc)", k = 2 ==> aabbcc
        //- For ith in s:
        //  + Prefix count for [a,b,c]
        //  + Suffix count for [a,b,c]
        //- We have 3 characters [a,b,c]
        //  ==> Binary search for each i in left site is not valid because:
        //      + We don't know how to sort the suffix_count[]
        //s = "aa(baaaacaabc)", k = 2 ==> aabbcc
        //left=-1, right = 2
        //left=0, right = 2
        //left=1, right = 2
        //left=2, right = 7
        //  s = "(aab)aaaa(caabc)"
        //left=3, right = 7
        //....
        //- Increase right when:
        //===== left>=right
//        while(right<=left){
//            count[s.charAt(right)-'a']--;
//            right++;
//        }
        //====
        //==== count[s.charAt[right]]>k:
        //+ right++
        //====
        //1.1, Optimization
        //1.1, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
//        String s = "aabaaaacaabc";
//        int k = 2;
        String s = "a";
        int k = 1;
        System.out.println(takeCharacters(s, k));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        //2. Slice window
        //* Reverse idea:
        //- Find (the longest window) that leaves k of each character (outside)
        //
        //** Kinh nghiệm:
        //- Bài toán nào mà (chọn 2 đầu) ==> Đều có thể suy ra 1 bài toán tương ứng là:
        //  + Tìm (liên tiếp ở giữa)
        //
        System.out.println(takeCharactersSlidingWindow(s, k));
        //
        //#Reference:
        //1065. Index Pairs of a String
        //1893. Check if All the Integers in a Range Are Covered
        //1682. Longest Palindromic Subsequence II
    }
}
