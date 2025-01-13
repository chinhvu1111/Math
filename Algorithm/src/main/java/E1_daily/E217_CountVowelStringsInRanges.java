package E1_daily;

import java.util.HashSet;

public class E217_CountVowelStringsInRanges {

    public static int[] vowelStrings(String[] words, int[][] queries) {
        int n=words.length;
        HashSet<Character> vowels=new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        int[] prefixSum=new int[n+1];
        int sum=0;

        for(int i=1;i<=n;i++){
            String word=words[i-1];
            if(vowels.contains(word.charAt(0))&&vowels.contains(word.charAt(word.length()-1))){
                sum++;
            }
            prefixSum[i]=sum;
        }
        int[] rs=new int[queries.length];

        for(int i=0;i<queries.length;i++){
            rs[i]=prefixSum[queries[i][1]+1]-prefixSum[queries[i][0]];
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed array of strings words) and a 2D array of integers queries.
        //- Each query queries[i] = [li, ri] asks us to find (the number of strings) present in the (range li to ri) (both inclusive) of words
        // that (start and end) (with a vowel).
        //* Return (an array ans of size) queries.length, where ans[i] is the answer to (the ith query).
        //- Note that (the vowel letters) are 'a', 'e', 'i', 'o', and 'u'.
        //
        //Example 1:
        //
        //Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
        //Output: [2,3,0]
        //Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
        //The answer to the query [0,2] is 2 (strings "aba" and "ece").
        //to query [1,4] is 3 (strings "ece", "aa", "e").
        //to query [1,1] is 0.
        //We return [2,3,0].
        //- ans[i] return the number of word from  (i -> j) (start and end) with a vowel.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= words.length <= 10^5
        //1 <= words[i].length <= 40
        //words[i] consists only of lowercase English letters.
        //sum(words[i].length) <= 3 * 10^5
        //1 <= queries.length <= 10^5
        //0 <= li <= ri < words.length
        //
        //- Brainstorm
        //- Prefix sum:
        //  + prefix[i]: the number of valid word at the (index=i)
        //
        //1.1, Optimization
        //
        //
        //1.2, Complexity
        //- Space: O(n+m)
        //- Time: O(n+m)
        //
        String[] words = {"aba","bcb","ece","aa","e"};
        int[][] queries = {{0,2},{1,4},{1,1}};
        int[] rs=vowelStrings(words, queries);

        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
        //
        //#Reference:
        //2646. Minimize the Total Price of the Trips
        //2456. Most Popular Video Creator
        //1480. Running Sum of 1d Array
        //
        //1163. Last Substring in Lexicographical Order
        //447. Number of Boomerangs
        //2402. Meeting Rooms III
    }
}
