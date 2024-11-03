package E1_weekly;

import java.util.Arrays;

public class E19_NumberOfSameEndSubstrings {

    public static int[] sameEndSubstringCount(String s, int[][] queries) {
        int n=s.length();
        //Space: O(n*26)
        int[][] prefixCount = new int[n+1][26];

        //Time: O(n*26)
        for(int i=1;i<=n;i++){
            for(int j=0;j<26;j++){
                prefixCount[i][j]=prefixCount[i-1][j];
            }
            prefixCount[i][s.charAt(i-1)-'a']++;
        }
        int m = queries.length;
        //Space: O(m)
        int[] rs=new int[m];
        //Time: O(m)
        for(int i=0;i<m;i++){
            int curRs=0;
            for(int j=0;j<26;j++){
                int curCount = prefixCount[queries[i][1]+1][j]-prefixCount[queries[i][0]][j];
                curRs+=curCount*(curCount+1)/2;
            }
            rs[i]=curRs;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed string s, and a 2D array of integers queries, where queries[i] = [li, ri]
        // indicates (a substring) of (s starting from the (index li) and ending at the (index ri)) (both inclusive), i.e. s[li..ri].
        //* Return an array ans where ans[i] is (the number of same-end substrings) of queries[i].
        //- (A 0-indexed string t) of length n is called (same-end)
        //  + if it has (the same character) at both of its ends,
        //      + i.e., t[0] == t[n - 1].
        //- A substring is a contiguous non-empty sequence of characters within a string.
        //
        //** Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= s.length <= 3 * 10^4
        //s consists only of lowercase English letters.
        //1 <= queries.length <= 3 * 10^4
        //queries[i] = [li, ri]
        //0 <= li <= ri < s.length
        //  + length <= 3*10^4:
        //      + Time: O(n*log(n)) or O(n*26)
        //  + Only lower case:
        //      + 26 characters
        //
        //- Brainstorm
        //- Bài toán trở thành given s, start-i, end-j:
        //  + Count the number of "same-end" substrings (t[0] == t[n - 1])
        //Ex:
        //s = "abcaab"
        //- s[0]==s[n-1]:
        //- Có chuyển thành số cặp giống nhau được không?
        //Ex:
        //s = "a(b)caa(b)ca(b)"
        //- s1 = (b)...(b) thì chỉ tính (b) khi string cover(s1)
        //- (b)...(b)...(b)...(b):
        //  + Với b sẽ có 3+2+1 = 6 = 4*(4-1)/2
        //  ==> Do tính cả single ==> rs = 4*(4+1)/2
        //- Ta chỉ cần tình count các lowercase char giữa (a và b) thôi
        //- Prefix sum:
        //  + prefix[i][26]
        //- Khi tính (i,j):
        //  + Trừ prefix[i][c] - prefix[j][c] để tính
        //
        //* KINH NGHIỆM:
        //- Nếu tổ hợp mà không tính single:
        //  + rs= x*(x-1)/2
        //- Có tính single:
        //  + rs= x*(x+1)/2
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*26+m)
        //- Time: O(n*26+m)
        //
        String s = "abcaab";
        int[][] queries = {{0,0},{1,4},{2,5},{0,5}};
        //
        int[] rs = sameEndSubstringCount(s, queries);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
        //#Reference:
        //2916. Subarrays Distinct Element Sum of Squares II
        //2586. Count the Number of Vowel Strings in Range
        //1726. Tuple with Same Product
    }
}
