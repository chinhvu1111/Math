package contest;

public class E268_FindSpecialSubstringOfLengthK {

    public static boolean hasSpecialSubstring(String s, int k) {
        int n=s.length();

        for(int i=0;i<n-k+1;i++){
            char c=s.charAt(i);
            boolean isValid=true;
            int j;
            for(j=0;i+j<n&&j<k;j++){
                if(s.charAt(i+j)!=c){
                    isValid=false;
                    break;
                }
            }
            if(!isValid){
                continue;
            }
            if((i==0||s.charAt(i-1)!=c)&&(i+j==n||s.charAt(i+j)!=c)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //You are given a string s and an integer k.
        //- Determine if there exists a substring of (length exactly k) in s that satisfies the following conditions:
        //
        //- The substring consists of (only one distinct character) (e.g., "aaa" or "bbb").
        //  + If there is a character immediately before the substring, it must be different from the character in the substring.
        //  + If there is a character immediately after the substring, it must also be different from the character in the substring.
        //* Return true if such a substring exists. Otherwise, return false.
        //- A substring is a contiguous non-empty sequence of characters within a string.
//        String  s = "aaabaaa";
//        int k = 3;
//        String  s = "abc";
//        int k = 2;
        String  s = "a";
        int k = 1;
//        String  s = "ccc";
//        int k = 2;
        System.out.println(hasSpecialSubstring(s, k));
    }
}
