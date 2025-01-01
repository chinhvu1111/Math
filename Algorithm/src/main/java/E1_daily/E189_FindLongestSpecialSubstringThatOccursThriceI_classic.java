package E1_daily;

public class E189_FindLongestSpecialSubstringThatOccursThriceI_classic {

    public static int maximumLength(String s) {
        int n=s.length();
        int[][] dp=new int[26][n];
        int i=0;

        while(i<n){
            char c=s.charAt(i);
            int count=0;
            int j=i;
            while(j<n&&s.charAt(j)==c){
                dp[c-'a'][count]++;
                count++;
                j++;
            }
            i++;
        }
        int rs=-1;
        for(i=0;i<n;i++){
            for(int j=0;j<26;j++){
                if(dp[j][i]>=3){
                    rs=Math.max(rs, i+1);
                }
            }
        }
        return rs;
    }

    //Check whether max length=value is valid or not
    public static boolean isLengthValid(String s, int n, int value){
        int[] count=new int[26];
        //Ex:
        //s = "aaaa"
        //aa,aa,aa
        //s = "a(i=0)aaa(p==3)"
        //  + i=1,2
        //  ==> count['a']++
        //      + p-i+1>=2
        int p=0;
        for(int i=0;i<n;i++){
            while(p<n&&s.charAt(i)==s.charAt(p)){
                p++;
            }
            if(p-i+1>value){
                count[s.charAt(i)-'a']++;
            }
            if(count[s.charAt(i)-'a']>2){
                return true;
            }
        }
        return false;
    }

    public static int maximumLengthBinarySearch(String s) {
        int n=s.length();
        int low=1, high=n;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(isLengthValid(s, n, mid)){
                rs=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s that consists of lowercase English letters.
        //- A string is called special if it is made up of only a single character.
        //  + For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.
        //* Return the length of the longest special substring of s which occurs at least thrice,
        // or -1 if no special substring occurs at least thrice.
        //- A substring is a contiguous non-empty sequence of characters within a string.
        //
        //Example 3:
        //
        //Input: s = "abcaba"
        //Output: 1
        //Explanation: The longest special substring which occurs thrice is "a": substrings "abcaba", "abcaba", and "abcaba".
        //It can be shown that the maximum length achievable is 1.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //3 <= s.length <= 50
        //s consists of only lowercase English letters.
        //  + length<=50 ==> Time: O(n^3)
        //
        //- Brainstorm
        //s = "abcaba"
        //rs=1
        //- count[26][n]
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n^2)
        //
        //2. Binary search
        //2.0,
        //- The goal is to identify the largest ( x ) such that a special substring of length ( x ) exists at least three times.
        //- Why binary search?
        //  + Binary search is effective because the problem exhibits monotonic behavior:
        //      + If ( x ) is valid (i.e., there exists a special substring of length ( x )), then all smaller lengths ( < x ) are also valid.
        //      + Conversely, if ( x ) is invalid, then all larger lengths ( > x ) are also invalid.
        //  + We search in the range ( [1, n] ), where ( n ) is the length of the string:
        //      + Start with ( l = 1 ) (minimum substring length).
        //      + End with ( r = n ) (maximum substring length).
        //
        //* Điều kiện để dùng binary search:
        //  + x is valid ==> <=x is valid
        //  + <> is invalid
        //
        //Example 1:
        //Input: s = "aabccc"
        //( x = 1 ): Valid (all single-character substrings).
        //( x = 3 ): Valid (substring "ccc" occurs 3 times).
        //( x = 4 ): Invalid (no substring of length 4 occurs 3 times).
        //Output: ( 3 ).
        //
        //- Algorithm to check the length is valid or not
        //Ex:
        //s = "aaaa"
        //aa,aa,aa
        //s = "a(i=0)aaa(p==3)"
        //  + i=1,2
        //  ==> count['a']++
        //      + p-i+1>=2
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(1)
        //- Time: O(n*log(n))
        //
        String s = "aaaa";
        //(a,a),(a,a),(a,a)
//        String s = "abcaba";
        System.out.println(maximumLength(s));
        System.out.println(maximumLengthBinarySearch(s));
        //#Reference:
        //395. Longest Substring with At Least K Repeating Characters
        //
    }
}
