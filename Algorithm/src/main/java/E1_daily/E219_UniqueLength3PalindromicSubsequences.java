package E1_daily;

import java.util.HashSet;
import java.util.Set;

public class E219_UniqueLength3PalindromicSubsequences {

    public static int countPalindromicSubsequenceWrong(String s) {
        int n=s.length();
        //Cache for 2 characters combination
        HashSet<String> cacheStr=new HashSet<>();
        int[] count2Combination=new int[26];
        int[] count=new int[26];
        int rs=0;

        for(int i=0;i<n;i++){
            //a,b,c
            //a,d,a
            //==> ....
            //
            //Calculate the subsequence with 3 characters
            for(int j=0;j<26;j++){

            }
            for(int j=0;j<26;j++){
                if(count[j]!=0){
                    String newCombination = ""+(char)(j+'a')+s.charAt(i);
                    if(!cacheStr.contains(newCombination)){
                        cacheStr.add(newCombination);
                        count2Combination[j]++;
                    }
                }
            }
        }
        return 1;
    }

    public static int countPalindromicSubsequence(String s) {
        int n=s.length();
        int[][] prefixCount=new int[n][26];
        int[][] suffixCount=new int[n][26];
        HashSet<String> combinationStr=new HashSet<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<26&&i>0;j++){
                prefixCount[i][j]=prefixCount[i-1][j];
            }
            prefixCount[i][s.charAt(i)-'a']++;
        }
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<26&&i<n-1;j++){
                suffixCount[i][j]=suffixCount[i+1][j];
            }
            suffixCount[i][s.charAt(i)-'a']++;
        }

        for(int i=1;i<n-1;i++){
            for(int j=0;j<26;j++){
                if(prefixCount[i-1][j]!=0&&suffixCount[i+1][j]!=0){
                    combinationStr.add(""+(char)(j+'a')+s.charAt(i)+(char)(j+'a'));
                }
            }
        }
//        System.out.println(combinationStr);
        return combinationStr.size();
    }

    public static int countPalindromicSubsequenceOptimization(String s) {
        int n=s.length();
        boolean[][] prefixCount=new boolean[n][26];
        boolean[][] suffixCount=new boolean[n][26];
        HashSet<Integer> combinationNumber=new HashSet<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<26&&i>0;j++){
                prefixCount[i][j]=prefixCount[i-1][j];
            }
            prefixCount[i][s.charAt(i)-'a']=true;
        }
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<26&&i<n-1;j++){
                suffixCount[i][j]=suffixCount[i+1][j];
            }
            suffixCount[i][s.charAt(i)-'a']=true;
        }

        for(int i=1;i<n-1;i++){
            for(int j=0;j<26;j++){
                if(prefixCount[i-1][j]&&suffixCount[i+1][j]){
                    combinationNumber.add((j+'a')*10+s.charAt(i)*100+(j+'a')*1000);
                }
            }
        }
//        System.out.println(combinationNumber);
        return combinationNumber.size();
    }

    public static int countPalindromicSubsequenceRefer(String s) {
        //abceddd
        int n=s.length();
        Set<Character> uniqueChars=new HashSet<>();
        Set<Character> charAtBoundary=new HashSet<>();
//        System.out.println(combinationNumber);
        for (int i = 0; i < n; i++) {
            if(uniqueChars.contains(s.charAt(i))){
                charAtBoundary.add(s.charAt(i));
            }else{
                uniqueChars.add(s.charAt(i));
            }
        }
        int rs=0;
        for(char c: charAtBoundary){
            int i;
            int j;
            for(i=0;i<n;i++){
                if(c==s.charAt(i)){
                    break;
                }
            }
            for(j=n-1;j>=0;j--){
                if(c==s.charAt(j)){
                    break;
                }
            }
            i++;
            j--;
            boolean[] distinctChar=new boolean[26];
            int curCount=0;
            while(i<=j){
                if(!distinctChar[s.charAt(i)-'a']){
                    curCount++;
                }
                distinctChar[s.charAt(i)-'a']=true;
                i++;
            }
            rs+=curCount;
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a string s, return (the number of unique palindromes) of (length three) that are (a subsequence of s).
        //* Note that even if there are multiple ways to obtain the same subsequence, it is still (only counted once).
        //- A palindrome is a string that reads the (same forwards and backwards).
        //- A subsequence of a string is a new string generated from the original string with some characters (can be none)
        // deleted without changing the relative order of the remaining characters.
        //- For example, "ace" is a subsequence of "abcde".
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //3 <= s.length <= 10^5
        //s consists of only lowercase English letters.
        //  + Length<=10^5 => Time: O(n)
        //
        //- Brainstorm
        //- palindrome string:
        //- Nhưng chỉ có 3 characters:
        //  + It is easier
        //- We need to check palindrome:
        //- If we stand at (index=i):
        //  + Ex:
        //  s[i] = c
        //      + c(a->z)c
        //- ca + (a,b,c...,z)
        //- dp[i][j] (j: 0 -> 25)
        //  + Means, how many string after (index=i) having character = j?
        //- aabc[b]a
        //  + [a,b,c],b
        //- How to cache the result?
        //  + aba: 010
        //- stand at (i), we can:
        //  + Make the combination for 3 chars
        //      + Calculate by using the result from 2 characters
        //  + Make the combination for 2 chars
        //      + Calculate later
        //==> It is WRONG
        //
        //- Solution:
        //+ For each index=i, we will check on the (left and right):
        //  + Chars from (0 -> 26), they exists or not:
        //      + a,(b),a:
        //          + b check whether 'a' exists on the (left, right)
        //      + cache.add(str)
        //
        //1.1, Optimization
        //- There may be many occurrences of a given letter in s.
        // Which ones should we choose? We should choose the first occurrence of letter in s to be the first character in our palindrome,
        // and the last occurrence of letter in s to be the last character in our palindrome
        //- For each unique character:
        //  + We get index of first, last in the string s
        //* Because,we don't care about the middle characters:
        //  + We just need to count the unique characters between (first and last)
        //
        //1.2, Complexity
        //- Space: O(n*26)
        //- Time: O(n*26)
        //
//        String s = "aabca";
//        String s = "adc";
        String s = "bbcbaba";
        System.out.println(countPalindromicSubsequence(s));
        System.out.println(countPalindromicSubsequenceOptimization(s));
        System.out.println(countPalindromicSubsequenceRefer(s));
        //#Reference:
        //2484. Count Palindromic Subsequences
        //
    }
}
