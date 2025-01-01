package contest;

public class E244_SmallestSubstringWithIdenticalCharactersI_undone {

    public static int minLength(String s, int numOps) {
        int n=s.length();
        int low=1, high=n;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(isValid(s, mid, numOps)||isValid1(s, mid, numOps)){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static boolean isValid(String s, int minLength, int numsOps){
        char prevChar = s.charAt(0);
        int n=s.length();
        int count=1;

        for(int i=1;i<n;i++){
            if(s.charAt(i)==prevChar){
                count++;
                if(count>minLength){
                    if(numsOps>0){
                        numsOps--;
                        prevChar=s.charAt(i)=='1'?'0':'1';
                    }else{
                        return false;
                    }
                    count=1;
                }
            }else{
                prevChar=s.charAt(i);
                count=1;
            }
        }
        return true;
    }

    public static boolean isValid1(String s, int minLength, int numsOps){
        int n=s.length();
        char prevChar = s.charAt(n-1);
        int count=1;

        for(int i=n-2;i>=0;i--){
            if(s.charAt(i)==prevChar){
                count++;
                if(count>minLength){
                    if(numsOps>0){
                        numsOps--;
                        prevChar=s.charAt(i)=='1'?'0':'1';
                    }else{
                        return false;
                    }
                    count=1;
                }
            }else{
                prevChar=s.charAt(i);
                count=1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a binary string s of length n) and (an integer numOps).
        //- You are allowed to perform the following operation on s at most numOps times:
        //  + Select any index i (where 0 <= i < n) and flip s[i], i.e., if s[i] == '1', change s[i] to '0' and vice versa.
        //- You need to minimize (the length of the longest substring of s) such that (all the characters) in the substring (are identical).
        //* Return (the minimum length) after the operations.
        //- A substring is a contiguous non-empty sequence of characters within a string.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= n == s.length <= 1000
        //s consists only of '0' and '1'.
        //0 <= numOps <= n
        //
        //- Brainstorm
        //-
        //
//        String s = "000001";
//        int numOps = 1;
//        String s = "00";
//        int numOps = 0;
        //rs=2
//        String s = "1101";
//        int numOps = 1;
        String s = "00100";
        int numOps = 0;
        System.out.println(minLength(s, numOps));
    }
}
