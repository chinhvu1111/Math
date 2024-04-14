package contest;

public class E62_MaximumLengthSubstringWithTwoOccurrences {

    public static boolean existAtMost2Times(String s){
        int n=s.length();
        char[] count=new char[26];

        for(int i=0;i<n;i++){
            count[s.charAt(i)-'a']++;
            if(count[s.charAt(i)-'a']>2){
                return false;
            }
        }
        return true;
    }

    public static int maximumLengthSubstring(String s) {
        int n=s.length();
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=i+1;j<=n;j++){
                String curStr=s.substring(i, j);
                if(existAtMost2Times(curStr)){
//                    System.out.printf("%s %s\n", i, j);
                    rs=Math.max(rs, curStr.length());
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //* Given a string s, return the (maximum length of a substring) such that it contains at most two occurrences of each character.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= s.length <= 100
        //s consists only of lowercase English letters.
        //
        //- Brainstorm
        //-
        String s = "bcbbbcba";
        System.out.println(maximumLengthSubstring(s));
    }
}
