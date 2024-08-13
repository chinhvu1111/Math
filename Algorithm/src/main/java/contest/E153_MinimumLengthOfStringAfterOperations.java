package contest;

public class E153_MinimumLengthOfStringAfterOperations {

    public static int minimumLength(String s) {
        int[] count=new int[26];
        int n=s.length();
        int rs=n;

        for(int i=0;i<n;i++){
            count[s.charAt(i)-'a']++;
        }
        for(int i=0;i<26;i++){
            //4 -> 1 1 1 1
            //6 -> 1 1
            if(count[i]<=2){
                continue;
            }
            if(count[i]%2==0){
                rs-=count[i]-2;
            }else{
                rs-=count[i]-1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string s.
        //You can perform the following process on s (any number of times):
        //  + Choose (an index i) in the string such that there is ("at least")
        //  (one character) to (the left of index i) that is equal to s[i],
        //  and ("at least") one character to the right that is also equal to s[i].
        //  + Delete (the closest character) to the left of index i that is equal to s[i].
        //  + Delete (the closest character) to the right of index i that is equal to s[i].
        //* Return (the minimum length) of the final string s that you can achieve.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 2 * 10^5
        //s consists only of lowercase English letters.
        //==> Time: O(n)
        //
        //** Brainstorm
        //Example 1:
        //Input: s = "abaacbcbb"
        //Output: 5
        //Explanation:
        //We do the following operations:
        //
        //Choose index 2, then remove the characters at indices 0 and 3. The resulting string is s = "bacbcbb".
        //Choose index 3, then remove the characters at indices 0 and 5. The resulting string is s = "acbcb".
        //
        //- Tức là chỉ xoá được những character có số lần xuất hiện là số lẻ ==> 1 character
        String s = "abaacbcbb";
        System.out.println(minimumLength(s));
    }
}
