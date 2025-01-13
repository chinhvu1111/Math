package contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E252_FindMirrorScoreOfAString {

    public static char getReverseChar(char c){
        return (char)('z'-(c-'a'));
    }

    public static long calculateScore(String s) {
        //[]list
        List<Integer>[] lastIndex=new ArrayList[26];
        int n=s.length();
        long rs=0;
        for(int i=0;i<26;i++){
            lastIndex[i]=new ArrayList<>();
        }

        for(int i=0;i<n;i++){
            char c= s.charAt(i);
            char reversedChar=getReverseChar(c);
            if(!lastIndex[reversedChar-'a'].isEmpty()){
                int size = lastIndex[reversedChar-'a'].size();
                rs+=i-lastIndex[reversedChar-'a'].get(size-1);
                lastIndex[reversedChar-'a'].remove(size-1);
            }else{
                lastIndex[c-'a'].add(i);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s.
        //- We define (the mirror of a letter) in the English alphabet as (its corresponding letter when the alphabet is reversed).
        // For example, the mirror of 'a' is 'z', and the mirror of 'y' is 'b'.
        //- Initially, all characters in the string s (are unmarked).
        //- You start with a score of 0, and you perform the following process on the string s:
        //  + Iterate through the string from left to right.
        //  + At (each index i), find the (closest unmarked index j) such that j < i and (s[j] is the mirror of s[i]).
        //  Then, mark both indices i and j, and add the (value i - j) to (the total score).
        //  + If (no such index j exists) for the index i, move on to (the next index) without making any changes.
        //* Return (the total score at the end) of the process.
        //
        //Example 1:
        //Input: s = "aczzx"
        //Output: 5
        //Explanation:
        //
        //i = 0. There is no index j that satisfies the conditions, so we skip.
        //i = 1. There is no index j that satisfies the conditions, so we skip.
        //i = 2. The closest index j that satisfies the conditions is j = 0, so we mark both indices 0 and 2, and then add 2 - 0 = 2 to the score.
        //i = 3. There is no index j that satisfies the conditions, so we skip.
        //i = 4. The closest index j that satisfies the conditions is j = 1, so we mark both indices 1 and 4, and then add 4 - 1 = 3 to the score.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 10^5
        //s consists only of lowercase English letters.
        //
        //- Brainstorm
        //
//        String s = "aczzx";
        String s = "zadavyayobbgqsexaabk";
        //output: 13
        //expect: 18
        System.out.println(calculateScore(s));
        //
    }
}
