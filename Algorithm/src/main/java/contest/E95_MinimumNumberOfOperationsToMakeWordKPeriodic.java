package contest;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class E95_MinimumNumberOfOperationsToMakeWordKPeriodic {

    public static int minimumOperationsToMakeKPeriodic(String word, int k) {
        int n=word.length();
        Map<String, Integer> mapCount=new HashMap<>();
        int maxCount=0;

        for(int i=0;i<n;i+=k){
            String curStr=word.substring(i, i+k);
            int newCount = mapCount.getOrDefault(curStr, 0)+1;
            mapCount.put(curStr, newCount);
            maxCount=Math.max(maxCount, newCount);
        }
        return word.length()/k-maxCount;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string word of size n, and an integer k such that (k divides n).
        //- In one operation, you can pick any two indices (i and j), that are (divisible by k),
        // then replace (the substring of length k) starting at (i) with (the substring of length k) starting at (j).
        // + That is, replace the substring word[i..i + k - 1] with the substring word[j..j + k - 1].
        //* Return the minimum number of operations required to make word k-periodic.
        //- We say that word is k-periodic if there is some (string s of length k)
        // + such that word can be obtained by (concatenating s an arbitrary number of times).
        // For example, if word == “ababab”, then word is 2-periodic for s = "ab".
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n == word.length <= 10^5
        //1 <= k <= word.length
        //k divides word.length.
        //word consists only of lowercase English letters.
        //n, k khá lớn
        //==> Time : O(n)
        //
        //- Brainstorm
        //Example:
        //
        //Input: word = "leetcoleet", k = 2
        //
        //Output: 3
        //
        //Explanation:
        //We can obtain a 2-periodic string by applying the operations in the table below.
        //i	j	word
        //0	2	etetcoleet : le <=> et
        //4	0	etetetleet : co <=> et
        //6	0	etetetetet : le <=> et
        //
//        String word = "leetcodeleet";
//        int k = 4;
//        String word = "leetcoleet";
//        int k = 2;
//        String word = "ll";
//        int k = 1;
//        String word = "lala";
//        int k = 2;
        String word = "lala";
        int k = 1;
        System.out.println(minimumOperationsToMakeKPeriodic(word, k));
    }
}
