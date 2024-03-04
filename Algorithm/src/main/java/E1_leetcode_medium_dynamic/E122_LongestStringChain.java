package E1_leetcode_medium_dynamic;

import java.util.*;

public class E122_LongestStringChain {

    public static boolean checkChainStr(String s1, String s2){
        if(s1.length()-s2.length()!=-1){
            return false;
        }
        //abc
        //aebc
        int p=0,p1=0;
        int n=s1.length();
        int m=s2.length();
        int count=0;

        while(p<n&&p1<m){
            if(s1.charAt(p)!=s2.charAt(p1)){
                p1++;
                count++;
                if(count>=2){
                    return false;
                }
            }else{
                p++;
                p1++;
            }
        }
        return true;
    }

    public static int longestStrChainBottomUp(String[] words) {
        int n=words.length;
        Arrays.sort(words, (s1, s2) -> {
            if(s1.length()!=s2.length()){
                return s1.length()-s2.length();
            }
            return s1.compareTo(s2);
        });
        int[] dp=new int[n];
        int rs=0;

        for(int i=0;i<n;i++){
            String curStr=words[i];
            int curLen=curStr.length();
            int max=0;

            for(int j=i-1;j>=0;j--){
                if(curLen-words[j].length()!=1){
                    continue;
                }
                if(checkChainStr(words[j], curStr)){
//                    System.out.printf("%s %s, \n", words[j], curStr);
                    max=Math.max(max, dp[j]+1);
                }
            }
            dp[i]=max;
            rs=Math.max(rs, max);
        }
        return rs+1;
    }

    public static int dfs(HashSet<String> words, HashMap<String, Integer> memo, String currentWord){
        if(memo.containsKey(currentWord)){
            return memo.get(currentWord);
        }
        int maxLength=1;
        StringBuilder sb=new StringBuilder(currentWord);

        for(int i=0;i<currentWord.length();i++){
            sb.deleteCharAt(i);
            String newWord=sb.toString();

            if(words.contains(newWord)){
                int currentLength=1+dfs(words, memo, newWord);
                maxLength=Math.max(maxLength, currentLength);
            }
            sb.insert(i, currentWord.charAt(i));
        }
        memo.put(currentWord, maxLength);
        return maxLength;
    }

    public static int longestStrChainTopDown(String[] words) {
        int n=words.length;
        HashSet<String> wordSet=new HashSet<>();
        Collections.addAll(wordSet, words);
        HashMap<String, Integer> memo=new HashMap<>();
        int rs=0;

        for(String word: words){
            rs=Math.max(rs, dfs(wordSet, memo, word));
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.
        //- For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
        //- A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1,
        // where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.
        //* Return the length of the longest possible word chain with words chosen from the given list of words.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= words.length <= 1000
        //  + Có thể xử lý O(n^2)
        //1 <= words[i].length <= 16
        //words[i] only consists of lowercase English letters.
        //  + Không quá lớn
        //
        //- Brainstorm
        //Ex:
        //Input: words = ["a","b","ba","bca","bda","bdca"]
        //Output: 4
        //Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
        //- Check two strings are chained
        //- Ý tưởng là sort theo length tăng dần
        //- length(i) --> Sẽ tính theo max(length(i-1 -> 0)) +1
        //
        //- Top down approach:
        //  + String để thao tác add/ remove char:
        //      + Xoá char: StringBuilder.deleteCharAt(i)
        //      + Add char: StringBuilder.insertAt(i, c)
        //
        //1.2, Optimization
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n^2*16+n*log(n))
        //
        //1.1,
        String[] words={"a","b","ba","bca","bda","bdca"};
        System.out.println(longestStrChainBottomUp(words));
        System.out.println(longestStrChainTopDown(words));

    }
}
