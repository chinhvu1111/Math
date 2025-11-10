package contest;

import E1_daily.E106_DesignAddAndSearchWordsDataStructure;
import E1_daily.E29_ReplaceWords;

import java.util.HashMap;

public class E313_LongestPalindromeAfterSubstringConcatenationI {

    public static class TrieNode{
        HashMap<Character, TrieNode> child;
        boolean finish=false;
        public TrieNode(){
            child=new HashMap<>();
        }
    }

    public static boolean addWord(String word, TrieNode node){
        int n=word.length();
        boolean isExists=true;

        //Time: O(n)
        for(int i=0;i<n;i++){
            TrieNode children=node.child.get(word.charAt(i));

            if(children==null){
                children=new TrieNode();
                isExists=false;
            }
//            System.out.println(isExists);
            if(word.charAt(i)!='.'){
                node.child.put(word.charAt(i), children);
            }else{
                //Time: O(26)
                for(int j='a';j<='z';j++){
                    node.child.put((char)j, children);
                }
            }
            node=children;
        }
        node.finish=true;
        return isExists;
    }

    public static boolean searchPrefix(TrieNode root, String word){
        TrieNode node=root;
        int n=word.length();

        for(int i=0;i<n;i++){
            char curChar=word.charAt(i);
            TrieNode child=node.child.get(curChar);
            if(child==null){
                return false;
            }
            node.child.put(curChar, child);
            node=child;
        }
        return true;
    }

    private static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static int longestPalindromeWrong(String s, String t) {
        int n=s.length();
        int m=t.length();

        TrieNode rootT=new TrieNode();

        for (int i = 0; i < m; i++) {
            String curSubstring = t.substring(i);
            addWord(curSubstring, rootT);
        }
        int rs=-1;
        for (int i = n; i >=1; i--) {
            for(int j=0;j+i-1<n;j++){
                String curSubstring = s.substring(j, i+j);
//                System.out.println(curSubstring);
                if(isPalindrome(curSubstring)){
                    rs=i;
                    break;
                }
            }
            if(rs!=-1){
                break;
            }
        }
        for (int i = m; i >=1; i--) {
            boolean isValid=false;
            if(i<=rs){
                break;
            }
            for(int j=0;j+i-1<m;j++){
                String curSubstring = t.substring(j, i+j);
//                System.out.println(curSubstring);
                if(isPalindrome(curSubstring)){
                    rs=i;
                    isValid=true;
                    break;
                }
            }
            if(isValid){
                break;
            }
        }
        int combinationRs=0;
        for (int i = n; i>=1; i--) {
//            StringBuilder str=new StringBuilder();
//            StringBuilder strLack=new StringBuilder();
            for(int j=0;j+i-1<n;j++){
                String curSubstring = s.substring(j, i+j);
                //abcd
                //==> dcba
                //abc
                //==> cba
                StringBuilder reverseS=new StringBuilder(curSubstring);
                reverseS.reverse();
                String reversedStr = reverseS.toString();
                boolean isFound;
                //2*i+1
                for (int k = 0; k < 26; k++) {
                    String newStr = (char)(k+'a') + reversedStr;
                    isFound = searchPrefix(rootT, newStr);
                    if(isFound){
//                        System.out.println(newStr);
                        combinationRs=Math.max(combinationRs, 2*i+1);
                        break;
                    }
                }
                //x*2
                isFound = searchPrefix(rootT, reversedStr);
                if (isFound) {
//                    System.out.printf("Len: %s, str: %s\n", i, curSubstring);
                    combinationRs=Math.max(combinationRs, i*2);
                }
                isFound = searchPrefix(rootT, reverseS.substring(1, reverseS.length()));
                if (isFound) {
//                    System.out.printf("Len: %s, str: %s\n", i, curSubstring);
                    combinationRs=Math.max(combinationRs, 2*i-1);
                }
            }
            if(combinationRs!=0){
                break;
            }
        }
        return Math.max(rs, combinationRs);
    }

    public static int longestPalindrome(String s, String t) {
        int rs = 0;
        int n=s.length();
        int m=t.length();

        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                String subS = s.substring(i, j);
                for (int k = 0; k <= m; k++) {
                    for (int l = k; l <= m; l++) {
                        String subT = t.substring(k, l);
                        String finalStr = subS + subT;
                        if (isPalindrome(finalStr)) {
                            rs = Math.max(rs, finalStr.length());
                        }
                    }
                }
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two strings, s and t.
        //- You can create (a new string) by selecting (a substring) from s (possibly empty)
        // and a substring from t (possibly empty),
        //  + then (concatenating them) in order.
        //* Return the length of the (longest palindrome) that can be formed this way.
        //- A substring is (a contiguous sequence of characters) within a string.
        //- A palindrome is a string that reads the same forward and backward.
        //
        //Example 4:
        //
        //Input: s = "abcde", t = "ecdba"
        //Output: 5
        //Explanation:
        //Concatenating "abc" from s and "ba" from t results in "abcba", which is a palindrome of length 5.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= s.length, t.length <= 30
        //s and t consist of lowercase English letters.
        //  + Length<=30 ==> Time: O(n^3)
        //
        //- Brainstorm
        //
        //Example 4:
        //
        //Input: s = "abcde", t = "ecdba"
        //Output: 5
        //abc(ba)
        //- abcd
        //  + find dcba
        //  + find cba
        //- abc
        //  + find cba
        //  + find ba
        //
//        String s = "abcde", t = "ecdba";
//        String s = "a", t = "a";
        String s = "b", t = "aaaa";
//        String s = "kz", t = "z";
        //nln
//        String s = "vn", t = "ln";
        //
//        String s = "f", t = "zzvz";
//        Output: 3
        //"hc"
        //"jooh"
//        String s = "hc", t = "jooh";
        //Wrong: 3
        //Output: 4
        System.out.println(longestPalindrome(s, t));
    }
}
