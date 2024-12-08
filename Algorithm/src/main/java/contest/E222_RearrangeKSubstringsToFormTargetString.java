package contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E222_RearrangeKSubstringsToFormTargetString {

    public static boolean isPossibleToRearrange(String s, String t, int k) {
        int size = s.length()/k;
        int n=s.length();
        StringBuilder strS=new StringBuilder();
        List<String> listS=new ArrayList<>();
        List<String> listT=new ArrayList<>();
        StringBuilder strT=new StringBuilder();
        int curSize=0;

        for(int i=0;i<n;i++){
            curSize++;
            strS.append(s.charAt(i));
            strT.append(t.charAt(i));
            if(curSize==size){
                listS.add(strS.toString());
                listT.add(strT.toString());
                strS=new StringBuilder();
                strT=new StringBuilder();
                curSize=0;
            }
        }
        Collections.sort(listS);
        Collections.sort(listT);
//        System.out.println(listS);
//        System.out.println(listT);
        for (int i = 0; i < listS.size(); i++) {
            if(!listS.get(i).equals(listT.get(i))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two strings (s and t), both of which are (anagrams of each other), and an integer k.
        //0 Your task is to determine whether it is possible to (split the string s) into (k equal-sized substrings),
        // (rearrange the substrings), and concatenate them in any order to (create a new string) that matches (the given string t).
        //* Return true if this is possible, otherwise, return false.
        //- An anagram is a word or phrase formed by (rearranging the letters) of a different word or phrase, using all the original letters (exactly once).
        //- A substring is a contiguous non-empty sequence of characters within a string.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length == t.length <= 2 * 10^5
        //1 <= k <= s.length
        //s.length is divisible by k.
        //s and t consist only of lowercase English letters.
        //The input is generated such that s and t are anagrams of each other.
        //  + length<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //
        //Example 2:
        //
        //Input: s = "aabbcc", t = "bbaacc", k = 3
        //Output: true
        //Explanation:
        //Split s into 3 substrings of length 2: ["aa", "bb", "cc"].
        //Rearranging these substrings as ["bb", "aa", "cc"], and then concatenating them results in "bbaacc", which matches t.
        //
        //s = "aabbcc", t = "bbaacc", k = 3
        //- indices = [1,3,5]
        //- (s and t) are (anagrams of each other)
        //- k equal-sized:
        //  + size bằng nhau ==> Check dễ thôi
        //
        //- How to check?
        //- t = bbaacc
        //- list_str = [aa,bb,cc] + sort
        //- list_strT = ... + sort
        //  ==> equal : return true <> false

//        String s = "abcd", t = "cdab";
//        int k = 2;
//        String s = "a", t = "a";
//        int k = 1;
        String s = "b", t = "a";
        int k = 1;
        System.out.println(isPossibleToRearrange(s, t, k));
    }
}
