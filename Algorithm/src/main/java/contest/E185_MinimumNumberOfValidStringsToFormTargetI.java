package contest;

import E1_trie_topic.E7_BoldWordsInString;

import java.util.Arrays;
import java.util.HashMap;

public class E185_MinimumNumberOfValidStringsToFormTargetI {

    public static class TrieNode{
        HashMap<Character, TrieNode> child;
        boolean finish;
        public TrieNode(){
            child=new HashMap<>();
        }
    }

    public static void addWord(String word, TrieNode node){
        int n=word.length();

        for(int i=0;i<n;i++){
            char c=word.charAt(i);
            TrieNode child=node.child.get(c);
            if(child==null){
                child=new TrieNode();
            }
            node.child.put(c, child);
            node=child;
        }
        node.finish=true;
    }
    
    public static int minValidStrings(String[] words, String target) {
        TrieNode root=new TrieNode();

        for(String word: words){
            addWord(word, root);
        }
        int n=target.length();
        int[] dp=new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for(int i=n-1;i>=0;i--){
            TrieNode node=root;
            int minVal=Integer.MAX_VALUE;
            int j=i;
            for(;j<n;j++){
                char c=target.charAt(j);
                TrieNode child=node.child.get(c);
                if(child==null){
                    break;
                }
                if(j+1<n&&dp[j+1]!=Integer.MAX_VALUE){
                    minVal=Math.min(minVal, dp[j+1]+1);
                }
                node=child;
            }
            if(j==n){
                dp[i]=1;
            }else{
                dp[i]=minVal;
            }
        }
        return dp[0]==Integer.MAX_VALUE?-1:dp[0];
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (an array of strings words) and (a string target).
        //- A string x is called (valid) if x is (a prefix of any string) in words.
        //* Return (the minimum number of valid strings) that can be concatenated (to form target).
        //  + If it is not possible to form target, return -1.
        //- A prefix of a string is a substring that starts from the beginning of the string and extends to any point within it.
        //
        //Example 1:
        //Input: words = ["abc","aaaaa","bcdef"], target = "aabcdabc"
        //Output: 3
        //Explanation:
        //The target string can be formed by concatenating:
        //Prefix of length 2 of words[1], i.e. "aa".
        //Prefix of length 3 of words[2], i.e. "bcd".
        //Prefix of length 3 of words[0], i.e. "abc".
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= words.length <= 100
        //1 <= words[i].length <= 5 * 10^3
        //The input is generated such that sum(words[i].length) <= 10^5.
        //words[i] consists only of lowercase English letters.
        //1 <= target.length <= 5 * 10^3
        //- target consists only of lowercase English letters.
        //  + length(char) = 26: Không lớn
        //  + length words không lớn : 100
        //  + word[i] có length <= 5*10^3 ==> khá lớn
        //      + time: O(n*m) = 5*10^5
        //  + Length của target khá lớn:
        //      + = 5*10^3
        //      ==> Time: O(n*k)
        //
        //** Brainstorm
        //- Prefix --> Trie?
        //- Tức là ta build trie từ target?
        //- Làm bình thường thì ta sẽ search theo target:
        //Example 1:
        //Input: words = ["abc","aaaaa","bcdef"], target = "aabcdabc"
        //- Search(abc) in aabcd(abc):
        //  + dp[i=5] = 1
        //- Search(bcdabc) in aa(bcd(abc)):
        //  + dp[i=3] = dp[5]+1
        //
        //=> Time:
//        String[] words = {"abc","aaaaa","bcdef"};
//        String target = "aabcdabc";
//        String[] words = {"abababab","ab"};
//        String target = "ababaababa";
//        String[] words = {"abcdef"};
//        String target = "xyz";
        String[] words = {"adaeabcabdcaabbeceeadeaebcdddeadcbceeeadddabdc","a"};
        String target = "beeea";
        System.out.println(minValidStrings(words, target));
    }
}
