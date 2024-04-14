package contest;

public class E77_ScoreOfAString {

    public static int scoreOfString(String s) {
        int n = s.length();
        if(n==0){
            return 0;
        }
        char curC=s.charAt(0);
        int rs=0;
        for(int i=1;i<n;i++){
            rs+=Math.abs(s.charAt(i)-curC);
            curC=s.charAt(i);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //-
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= s.length <= 100
        //s consists only of lowercase English letters.
        //
        //- Brainstorm
        //
        //
    }
}
