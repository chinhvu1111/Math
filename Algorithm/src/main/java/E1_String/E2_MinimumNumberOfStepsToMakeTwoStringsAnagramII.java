package E1_String;

public class E2_MinimumNumberOfStepsToMakeTwoStringsAnagramII {

    public static int minSteps(String s, String t) {
        int[] count=new int[26];
        int n=s.length();
        int m=t.length();
        int rs=0;

        for(int i=0;i<n;i++){
            count[s.charAt(i)-'a']++;
        }
        for(int i=0;i<m;i++){
            count[t.charAt(i)-'a']--;
        }
        for(int i=0;i<26;i++){
            rs+=Math.abs(count[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given s and t
        //- anagram of string is a string that contains the same character with a different (or the same) ordering
        //* Return minimum number of steps to make s and t (anagram of each other)
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //1 <= s.length, t.length <= 2 * 105
        //s and t consist of lowercase English letters.
        //
        //- Brainstorm
        //- Cái này chỉ cần dùng phương pháp counting character là xong
        //Ex:
        //count[26]
        //s="leetcode", t = "coats"
        //- s
        //count['l']=1
        //count['e']=3
        //count['t']=1
        //count['c']=1
        //count['o']=1
        //count['d']=1
        //- t : ta sẽ trừ đi mỗi character ==> sau đó lấy giá trị tuyệt đối cộng vào là được
        //count['c']-- =0
        //count['o']-- =0
        //count['a']-- =-1
        //count['t']-- =0
        //count['s']-- =-1
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Time complexity : O(N+M)
        //- Space complexity : O(1)
        //
        //#Reference:
        //1347. Minimum Number of Steps to Make Two Strings Anagram
    }
}
