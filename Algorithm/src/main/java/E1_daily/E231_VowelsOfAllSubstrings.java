package E1_daily;

import java.util.HashSet;

public class E231_VowelsOfAllSubstrings {

    public static long countVowels(String word) {
        int n=word.length();
        long[] prefixSum=new long[n];
        long sum=0;
        boolean[] vowels=new boolean[26];
        vowels[0]=true;
        vowels['e'-'a']=true;
        vowels['i'-'a']=true;
        vowels['o'-'a']=true;
        vowels['u'-'a']=true;

        for(int i=0;i<n;i++){
            if(vowels[word.charAt(i)-'a']){
                sum++;
            }
            prefixSum[i]=sum;
//            System.out.printf("%s %s,", i, prefixSum[i]);
        }
//        System.out.println();
        long prevPrefix=0;
        long rs=0;

        for(int i=0;i<n;i++){
            rs+=prefixSum[i]*(i+1)-prevPrefix;
            prevPrefix+=prefixSum[i];
        }
        return rs;
    }

    public static long countVowelsOptimization(String s) {
        long res = 0, n = s.length();
        for (int i = 0; i < n; ++i)
            if ("aeiou".indexOf(s.charAt(i)) >= 0)
                res += (i + 1) * (n - i);
        return res;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a string word,
        //* return the sum of the number of vowels ('a', 'e', 'i', 'o', and 'u') in (every substring of word).
        //- A substring is a contiguous (non-empty) sequence of characters within a string.
        //* Note:
        //  + Due to the large constraints, the answer may (not fit) in (a signed) (32-bit integer). Please be careful during the calculations.
        //
        //Example 1:
        //
        //Input: word = "aba"
        //Output: 6
        //Explanation:
        //All possible substrings are: "a", "ab", "aba", "b", "ba", and "a".
        //- "b" has 0 vowels in it
        //- "a", "ab", "ba", and "a" have 1 vowel each
        //- "aba" has 2 vowels in it
        //Hence, the total sum of vowels = 0 + 1 + 1 + 1 + 1 + 2 = 6.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= word.length <= 10^5
        //word consists of lowercase English letters.
        //  + n<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //
        //Example 1:
        //
        //Input: word = "aba"
        //Output: 6
        //Explanation:
        //All possible substrings are: "a", "ab", "aba", "b", "ba", and "a".
        //- "b" has 0 vowels in it
        //- "a", "ab", "ba", and "a" have 1 vowel each
        //- "aba" has 2 vowels in it
        //Hence, the total sum of vowels = 0 + 1 + 1 + 1 + 1 + 2 = 6.
        //
        //- Every substring:
        //- Brute force:
        //  + Find substrings with length from (1 to n)
        //
        //Input: word = "aba"
        //a,ab,a,ba,aba,b
        //               a,b,a,c
        //count vowels = 1,0,1,0
        //sum vowels   = 1,1,2,2
        //- Use dynamic programming to solve this problem
        //- dp[i]:
        //  + total of sum of vowels
        //- If we add the chars[i] at the end of array, we get the combinations above:
        //  + 0,1,2,3,i
        //  [0,1,2,3,i] ==> prefix_sum[i]
        //  [1,2,3,i] ==> prefix_sum[i] - prefix_sum[0]
        //  [2,3,i]  ==> prefix_sum[i] - prefix_sum[1]
        //  [3,i] ==> prefix_sum[i] - prefix_sum[2]
        //  [i] =>  ==> prefix_sum[i] - prefix_sum[3]
        //  ==> (i+1)*prefix_sum[i] - (prefix_sum[0] + prefix_sum[1] +  prefix_sum[2] + prefix_sum[3])
        //               a,b,a,c
        //sum vowels   = 1,1,2,2
        //- Calculate prefix_sum
        //- Calculate prefix_sum of the prefix_sum
        //
        //1.1, Optimization
        //- For each vowels s[i],
        //- it could be in the substring (starting at s[x]) and (ending at s[y]),
        //where 0 <= x <= i and i <= y < n,
        //that is (i + 1) choices for x and (n - i) choices for y.
        //
        //So there are (i + 1) * (n - i) substrings containing s[i].
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        String word = "aba";
        System.out.println(countVowels(word));
        System.out.println(countVowelsOptimization(word));
        //
        //#Reference:
        //1358. Number of Substrings Containing All Three Characters
        //2262. Total Appeal of A String
    }
}
