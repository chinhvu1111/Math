package E1_String;

public class E3_MinimumNumberOfStepsToMakeTwoStringsAnagram {

    public int minSteps(String s, String t) {
        int n=s.length();
        int m=t.length();
        int[] count=new int[26];
        int remaingRight=0;

        for(int i=0;i<n;i++){
            count[s.charAt(i)-'a']++;
        }
        for(int i=0;i<m;i++){
            if(count[t.charAt(i)-'a']>0){
                count[t.charAt(i)-'a']--;
            }else{
                remaingRight++;
            }
        }
        return remaingRight;
    }

    public static void main(String[] args) {
        //* Requirement
        //- We can replace the character by other character
        //- An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.
        //* Return minimum number of steps to make (t) an anagram of (s)
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //1 <= s.length <= 5 * 10^4
        //s.length == t.length
        //s and t consist of (lowercase English letters only).
        //
        //- Brainstorm
        //Ex
        //- s = "leetcode", t = "practice"
        //- count của s và t có thể giao nhau ở 1 số ký tự
        //- 1 số ký tự ta có thể bù trừ cho nhau.
        //Ex:
        //s = "bab", t = "aba"
        //+ s
        //count['b']=2
        //count['a']=1
        //+ t
        //count['a']-- ==> count['a']=0
        //count['b']-- ==> count['b']=1
        //count['a']==0 ==> left=1
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Time complexity : O(N+M)
        //- Space complexity : O(1)
        //
    }
}
