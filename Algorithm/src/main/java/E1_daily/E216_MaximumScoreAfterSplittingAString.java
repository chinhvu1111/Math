package E1_daily;

public class E216_MaximumScoreAfterSplittingAString {

    public static int maxScore(String s) {
        int n=s.length();
        int numOne=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)!='0'){
                numOne++;
            }
        }
        int rs=0;
        int curNumZero=0;
        int curNumOne=0;

        for(int i=0;i<n-1;i++){
            if(s.charAt(i)=='0'){
                curNumZero++;
            }else{
                curNumOne++;
            }
            rs=Math.max(rs, curNumZero + numOne-curNumOne);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a string s of (zeros and ones),
        //* return (the maximum score) after splitting the string into two non-empty substrings (i.e. left substring and right substring).
        //- The score after splitting a string is (the number of zeros) in (the left substring) (plus)
        //  (+) (the number of ones) in (the right substring).
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= s.length <= 500
        //The string s consists of characters '0' and '1' only.
        //
        //- Brainstorm
        //-
        //1.1, Optimization
        //- Sum the number of zero
        //- For each index=i, we will count the number of 0 and 1:
        //  + Base on the information above, we will get the result correspond to the index
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
//        String s = "011101";
        String s = "00";
        System.out.println(maxScore(s));
        //
        //#Reference:
        //418. Sentence Screen Fitting
        //3361. Shift Distance Between Two Strings
        //1796. Second Largest Digit in a String
    }
}
