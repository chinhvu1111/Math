package E1_trie_topic;

import java.util.HashMap;

public class E9_SumOfPrefixScoresOfStrings {

    public static class TrieNode{
        TrieNode[] child;
        boolean finish;
        int count=0;
        public TrieNode(){
            child=new TrieNode[26];
        }
    }

    public static void addWord(TrieNode root, String word){
        int n=word.length();
        TrieNode node = root;

        for(int i=0;i<n;i++){
            char c=word.charAt(i);
            TrieNode child = node.child[c-'a'];
            if(child==null){
                child=new TrieNode();
            }
            node.child[c-'a']=child;
            node=child;
            node.count++;
        }
        node.finish = true;
    }

    public static int getMaxScore(String word, TrieNode root){
        TrieNode node = root;
        int rs=0;
        for(char c: word.toCharArray()){
            rs+=node.count;
            node = node.child[c-'a'];
        }
        rs+=node.count;
        return rs;
    }
    
    public static int[] sumPrefixScores(String[] words) {
        TrieNode root=new TrieNode();

        for(String word: words){
            addWord(root, word);
        }
        int n=words.length;
        int[] rs=new int[n];
        for(int i=0;i<words.length;i++){
            rs[i] = getMaxScore(words[i], root);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array words of (size n) consisting of (non-empty) strings.
        //- We define the score of a string word as the number of strings words[i] such that word is a prefix of words[i].
        //  + For example, if words = ["a", "ab", "abc", "cab"],
        // then the score of "ab" is 2, since "ab" is a prefix of both "ab" and "abc".
        //* Return (an array answer of size n) where answer[i] is (the sum of scores) of (every non-empty) (prefix of words[i]).
        //- Note that a string is considered as a prefix of itself.
        //- Đại loại là mỗi character trong words[i] ta cần count++
        //  + Cộng số lần xuất hiện của mỗi characters là xong
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= words.length <= 1000
        //1 <= words[i].length <= 1000
        //words[i] consists of lowercase English letters.
        //
        //- Brainstorm
        //- Đại loại là mỗi character trong words[i] ta cần count++
        //  + Cộng số lần xuất hiện của mỗi characters là xong
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
        String[] words = {"abc","ab","bc","b"};
        int[] rs = sumPrefixScores(words);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
        //#Reference:
        //811. Subdomain Visit Count
        //1974. Minimum Time to Type Word Using Special Typewriter
        //2380. Time Needed to Rearrange a Binary String
    }
}
