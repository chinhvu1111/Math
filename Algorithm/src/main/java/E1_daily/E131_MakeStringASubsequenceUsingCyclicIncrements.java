package E1_daily;

public class E131_MakeStringASubsequenceUsingCyclicIncrements {

    public static boolean canMakeSubsequence(String str1, String str2) {
        int n = str2.length();
        int m= str1.length();
        int i=0,j=0;

        while(i<n&&j<m){
//            char c = (char)((str2.charAt(j)-'a'+1)%26+'a');
//            System.out.println(c);
            if(str2.charAt(i)==(str1.charAt(j))
                    ||str2.charAt(i)==(char)((str1.charAt(j)-'a'+1)%26+'a')){
                i++;
            }
            j++;
        }
        return i==n;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two 0-indexed strings str1 and str2.
        //- In (an operation), you select (a set of indices) in str1, and for (each index i) in the set,
        //  + increment str1[i] to (the "next" character cyclically).
        //  + That is ('a' becomes 'b', 'b' becomes 'c', and so on, and 'z' becomes 'a').
        //* Return true if it is possible to make str2 (a subsequence) of str1 by performing the operation (at most once), and false otherwise.
        //- Note: A subsequence of a string is a new string that is formed from the original string by deleting some (possibly none) of the characters
        // without disturbing the relative positions of the remaining characters.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= str1.length <= 10^5
        //1 <= str2.length <= 10^5
        //str1 and str2 consist of only lowercase English letters.
        //  + n khá lớn => Time: O(n)
        //
        //- Brainstorm
        //
        //Example 2:
        //Input: str1 = "zc", str2 = "ad"
        //Output: true
        //Explanation: Select indices 0 and 1 in str1.
        //Increment str1[0] to become 'a'.
        //Increment str1[1] to become 'd'.
        //Hence, str1 becomes "ad" and str2 is now a subsequence. Therefore, true is returned.
        //
        //- 1 chuỗi s là subsequence của s1 ta dùng two pointers là check được.
        //- We just do (at most once)
        //
        //- Increment 1 character => (x+1)%(z-'a')
        //- Vì matching string s2 vs s1:
        //  + Nên ta có thể giết nhầm còn hơn bỏ sót
        //Ex:
        //zac, ad
        //- ở đây ta có thể increment(z) hoặc không
        //  + Nhưng increment(c) sẽ là bắt buộc
        //  ==> (z xuất hiện trước) (match trước) sẽ luôn được (ưu tiên increment)
        //- Cứ match dần dần là được
        //* Công thức để tính cycle char:
        //  + char ch = (str1[i] == 'z') ? 'a' : str1[i]+1;
        //  + (char)((str2.charAt(j)-'a'+1)%26+'a')
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n+m)
        //
        String str1 = "zc", str2 = "ad";
        System.out.println('z'-'a'+1);
        char c = (char)('z'-'a'+1)%26+'a';
        System.out.println(c);
        char c1 = (char)('c'-'a'+1)%26+'a';
        System.out.println(c1);
//        System.out.println((char)(('z'+1)%26+'a'));
        System.out.println(canMakeSubsequence(str1, str2));
        //
        //#Reference:
        //2330. Valid Palindrome IV
        //1750. Minimum Length of String After Deleting Similar Ends
        //2844. Minimum Operations to Make a Special Number
    }
}
