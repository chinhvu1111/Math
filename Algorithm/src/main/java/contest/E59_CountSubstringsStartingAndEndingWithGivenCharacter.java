package contest;

public class E59_CountSubstringsStartingAndEndingWithGivenCharacter {

    public static long countSubstrings(String s, char c) {
        long[] count=new long[26];
        int n=s.length();
        long rs=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)==c){
                count[c-'a']++;
                rs+= count[c-'a'];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Count số lượng string start with (c) và end with (c)
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 105
        //s and c consist only of lowercase English letters.
        //-> Time : O(n)/ O(n*log(n))
        //
        //- Brainstorm
        //-
        //
        String s="abada";
        Character c='a';
        System.out.println(countSubstrings(s, c));
    }
}
