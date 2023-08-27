package E1_slide_window;

public class E3_IsSubsequence {

    public static boolean isSubsequence(String s, String t) {
        int p=0;
        int p1=0;
        int n=s.length();
        int m=t.length();

        while(p<n&&p1<m){
            while(p1<m&&t.charAt(p1)!=s.charAt(p)){
                p1++;
            }
            if(p1<m&&t.charAt(p1)==s.charAt(p)){
                p++;
                p1++;
            }
        }
        return p==s.length();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given s, t
        //* return true if s is a subsequence of t
        //Ex:
        //"ace" is a subsequence of "abcde"
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //0 <= s.length <= 100
        //0 <= t.length <= 10^4
        //s and t consist only of lowercase English letters.
        //
        //- Brainstorm
        //Ex:
        //s=ace, t= abcde
        //p1=0 ==> p2=0
        //p1=1 ==> p2=2
        //p1=2 ==> p2=4
        //
        //1.1, Optimization
        //- Refactor code
        //
        //1.2, Complexity:
        //- Space:
        //- Time
        //
        //2. Dynamic programming
        //Dp makes sense if the string "ace" is an infinite byte stream, if your s is changing, then dp makes sense.
        // Two pointer method isn't optimal as you can't build on the previous results.
        // I guess DP was shared keeping in mind the follow-up question.
        //
        //
        //#Reference:
        //11. Container With Most Water
        //792. Number of Matching Subsequences
        //1055. Shortest Way to Form String
        //2486. Append Characters to String to Make Subsequence
    }
}
