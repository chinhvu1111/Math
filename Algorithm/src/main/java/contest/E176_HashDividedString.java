package contest;

public class E176_HashDividedString {

    public static String stringHash(String s, int k) {
        int curSum=0;
        int n=s.length();
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<n;i++){
            curSum+=s.charAt(i)-'a';
            if((i+1)%k==0){
                curSum=curSum%26;
                char newChar = (char)(curSum+'a');
                rs.append(newChar);
                curSum=0;
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string s of length n and an integer k, where n is (a multiple of k).
        //- Your task is to hash the string s into a new string called result, which has (a length of n / k).
        //- First, divide s into (n / k) substrings, each with (a length of k).
        //  + Then, (initialize result) as an (empty string).
        //- For each substring (in order) from the beginning:
        //- The hash value of a character is the index of that character in the English alphabet
        //  + (e.g., 'a' → 0, 'b' → 1, ..., 'z' → 25).
        //- Calculate (the sum of all the hash values) of (the characters) in the substring.
        //- Find (the remainder of this sum) when (divided by 26),
        //  + which is called hashedChar.
        //- Identify (the character in the English ("lowercase") alphabet) that corresponds to (hashedChar).
        //- Append that (character) to (the end of result).
        //* Return result.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= k <= 100
        //k <= s.length <= 1000
        //s.length is divisible by k.
        //s consists only of lowercase English letters
        //  + N không lớn lắm ==> time = O(n^2) / O(n*k)
        //
        //** Brainstorm
        //- Tính lần lượt là được
        //  ==> Map từ integer ==> Char ==> + 'a' là được.
        //
        String s = "abcd";
        int k = 2;
        System.out.println(stringHash(s, k));
    }
}
