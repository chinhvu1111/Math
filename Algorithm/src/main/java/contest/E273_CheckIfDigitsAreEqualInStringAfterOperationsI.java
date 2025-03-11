package contest;

public class E273_CheckIfDigitsAreEqualInStringAfterOperationsI {

    public static String solution(String s){
        if(s.length()==2){
            return s;
        }
        StringBuilder curS=new StringBuilder();
        for (int i = 0; i < s.length()-1; i++) {
            int c=(s.charAt(i)-'0'+s.charAt(i+1)-'0')%10;
            curS.append(c);
        }
        return solution(curS.toString());
    }

    public static boolean hasSameDigits(String s) {
        int n=s.length();
        String s1=solution(s);
        return s1.charAt(0)==s1.charAt(1);
    }

    public static void main(String[] args) {
        //You are given a string s consisting of digits. Perform the following operation repeatedly until the string has exactly (two digits):
        //- For each pair of consecutive digits in s, starting from the first digit, calculate a new digit as the sum of the two digits modulo 10.
        //Replace s with the sequence of newly calculated digits, maintaining the order in which they are computed.
        //Return true if the final two digits in s are the same; otherwise, return false.
//        String s = "3902";
        String  s = "34789";
        System.out.println(hasSameDigits(s));
    }
}
