package contest;

import java.util.Arrays;
import java.util.HashMap;

public class E186_MinimumNumberOfValidStringsToFormTargetII {

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
//        int prevMin=Integer.MAX_VALUE;

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
//                    if(dp[j+1]==prevMin){
//                        break;
//                    }
                }
                node=child;
            }
            if(j==n){
                dp[i]=1;
            }else{
                dp[i]=minVal;
            }
//            prevMin=Math.min(prevMin, dp[i]);
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
        //1 <= words[i].length <= 5 * 10^4
        //The input is generated such that sum(words[i].length) <= 10^5.
        //words[i] consists only of lowercase English letters.
        //1 <= target.length <= 5 * 10^4
        //target consists only of lowercase English letters.
        //  - Target khá lớn:
        //      ==> Mình cần tìm cách reduce đi.
        //- Nếu match target từ (i->j):
        //  + dp[i] = dp[j] + 1
        //  + Run tiếp đến k>j:
        //      + dp[i] = dp[k>j] + 1
        //- Nếu but branch bằng prev min:
        //  + Sẽ bị miss case match hoàn toàn string.
        //
        //** Brainstorm
        //- Prefix --> Trie?
        //
    }
}
