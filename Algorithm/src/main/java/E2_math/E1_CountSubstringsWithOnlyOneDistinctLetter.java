package E2_math;

public class E1_CountSubstringsWithOnlyOneDistinctLetter {

    public static int countLetters(String s) {
        if(s.length()==0){
            return 0;
        }
        char prevChar=s.charAt(0);
        int rs=1;
        int count=1;

        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==prevChar){
                count++;
            }else{
                prevChar=s.charAt(i);
                count=1;
            }
            rs+=count;
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- Count số lượng substring mà chỉ có distinct 1 character
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //
        //
        //- Brainstorm
        //- Bài này dùng công thức toán là được
        //Ex:
        //"aaa" occurs 1 time.
        //"aa" occurs 2 times.
        //"a" occurs 3 times.
        //sum= 1+2+3=7
        //=> Count base on the length of string
        String s="aaaba";
        System.out.println(countLetters(s));
        //#Reference:
        //2060. Check if an Original String Exists Given Two Encoded Strings
        //2278. Percentage of Letter in String
        //43. Multiply Strings
    }
}
