package contest;

public class E96_MinimumLengthOfAnagramConcatenation {

    public static int minAnagramLength(String s) {
        int[] countChar=new int[26];

        for(char c: s.toCharArray()){
            countChar[c-'a']++;
        }
        for(int i=s.length();i>=1;i--){
            boolean isValid=true;
            int count=0;
            for(int j=0;j<26;j++){
                if(countChar[j]!=0){
                    if(countChar[j]%i!=0){
                        isValid=false;
                        break;
                    }else{
                        count+=countChar[j];
                    }
                }
            }
            if(isValid&&count==s.length()){
                return s.length()/i;
            }
        }
        //2,4,6 ==> (1,2,3)
        return -1;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string s, which is known to be a (concatenation) of anagrams of (some string t).
        //* Return the minimum possible length of the string t.
        //- An anagram is a word or phrase formed by rearranging the letters of a word or phrase,
        // typically using all the original letters (exactly once).
        //- Tức là sắp xếp cái chuỗi s ==> chuỗi dạng lặp
        //  ==> Lấy cái lặp đó chính là t
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 10^5
        //s consist only of lowercase English letters.
        //==> O(n) time
        //
        //- Brainstorm
        //
//        String s = "abba";
//        String s = "aaaa";
//        String s = "aaa";
        String s = "aaac";
        System.out.println(minAnagramLength(s));
        //
    }
}
