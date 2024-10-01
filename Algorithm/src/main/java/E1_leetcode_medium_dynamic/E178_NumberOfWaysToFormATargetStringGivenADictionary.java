package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.List;

public class E178_NumberOfWaysToFormATargetStringGivenADictionary {

    public static int numWaysRefer(String[] words, String target) {
        final int ALPHABET_SIZE = 26;
        final int MOD = 1_000_000_007;
        int targetLength = target.length();
        int wordLength = words[0].length();

        int[][] charOccurrences = new int[ALPHABET_SIZE][wordLength];
        for (int col = 0; col < wordLength; col++) {
            for (String word : words) {
                charOccurrences[word.charAt(col) - 'a'][col]++;
            }
        }

        long[][] dp = new long[targetLength + 1][wordLength + 1];
        dp[0][0] = 1;

        for (int length = 0; length <= targetLength; length++) {
            for (int col = 0; col < wordLength; col++) {
                if (length < targetLength) {
                    dp[length + 1][col + 1] += charOccurrences[target.charAt(length) - 'a'][col] * dp[length][col];
                    dp[length + 1][col + 1] %= MOD;
                }
                dp[length][col + 1] += dp[length][col];
                dp[length][col + 1] %= MOD;
            }
        }

        return (int) dp[targetLength][wordLength];
    }


    public static int numWays(String[] words, String target) {
        int mod = 1_000_000_007;
//        int n=words.length;
        int m=words[0].length();
//        String[] charOptions=new String[m];
        long[][] charOptions=new long[m][26];
        int l=target.length();

        for(String word: words){
            for(int j=0;j<m;j++){
//                charOptions[j]+=word.charAt(j);
                charOptions[j][word.charAt(j)-'a']++;
            }
        }
        long[][] dp=new long[l][26];
        long rs=0;

        //Current option
        for(int i=0;i<m;i++){
            //acb: choose character
            //Chosen index from the target string
            long[][] dp1=new long[l][26];
            for(int j=1;j<l&&j<=i;j++) {
                int curChar = target.charAt(j) - 'a';
                dp1[j][curChar]=dp[j][curChar];
            }
            for(int j=1;j<l&&j<=i;j++){
                int curChar = target.charAt(j)-'a';
                int prevChar = target.charAt(j-1)-'a';
//                dp[j][curChar]=dp[j-1][prevChar]%mod;
                dp1[j][curChar]=(dp1[j][curChar]+dp[j-1][prevChar]*charOptions[i][curChar])%mod;
            }
            for(int j=1;j<l&&j<=i;j++) {
                int curChar = target.charAt(j) - 'a';
                dp[j][curChar]=dp1[j][curChar];
            }
            if(charOptions[i][target.charAt(0)-'a']!=0){
                dp[0][target.charAt(0)-'a']+=charOptions[i][target.charAt(0)-'a'];
            }
        }
        for(int i=0;i<26;i++){
            rs=(rs+dp[l-1][i])%mod;
        }
        return (int) rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a list of strings) of the ("same") (length words) and (a string target).
        //- Your task is to (form target) using (the given words) under the following rules:
        //  + target should be formed from (left to right).
        //  + To form the (ith character) (0-indexed) of target, you can choose (the kth character) of (the jth string) in words
        //      + if target[i] = words[j][k].
        //  + Once you use (the kth character) of the (jth string) of words,
        //      + you can (no longer use) the (xth character) of (any string in words) where (x <= k).
        //  <=> In other words, all characters to the left of or at index k become unusuable for every string.
        //  + Repeat the process until you form (the string target).
        //- Notice that you can use (multiple characters) from the (same) string in words provided (the conditions) above are met.
        //* Return (the number of ways) to form (target) from words.
        //- Since the answer may be too large, return it modulo 10^9 + 7.
        //
        //Example 1:
        //
        //Input: words = ["acca","bbbb","caca"], target = "aba"
        //Output: 6
        //Explanation: There are 6 ways to form target.
        //"aba" -> index 0 ("(a)cca"), index 1 ("b(b)bb"), index 3 ("cac(a)")
        //"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
        //"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
        //"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
        //"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
        //"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
        //- Tức là form target string bằng cách điền lần lượt index của từng word trong words array
        //  + Index điền phải luôn tăng dần ==> Của mọi words
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= words.length <= 1000
        //1 <= words[i].length <= 1000
        //All strings in words have the same length.
        //1 <= target.length <= 1000
        //words[i] and target contain only lowercase English letters.
        //  + Length words array và length của word[i] khá nhỏ:
        //      + Time: O(n^2)
        //
        //- Brainstorm
        //- Mỗi index ta sẽ cần chọn char từ một trong những word trong words array
        //Example 1:
        //
        //Input: words = ["acca","bbbb","caca"], target = "aba"
        //Output: 6
        //Explanation: There are 6 ways to form target.
        //"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
        //
        //- Cùng lắm thì ta sẽ brute force
        //- dp[i] là xét đến vị trí (i)
        //  + dp[i] cần tính theo dp[i-1]
        //- Thấy thiếu ta sẽ thêm 1 chiều nữa:
        //  + dp[i][j]: số lần điền cho đến index=i + chọn lấy char index=i từ words[j]
        //      + Đoạn này mỗi lần ta cần loop toàn bộ words
        //- Issue:
        //  + Ta có thể chọn từ (k!=i) > 0 ngau trong words[j]
        //      + Tức là length(words[j]) != length(target)
        //- Chọn theo index:
        //Ex:
        //words = ["acca","bbbb","caca"]
        //  + Nếu để nguyên ntn thứ tự chọn gần như là random
        //      + Ta có thể lấy index=0 ở words[0] + index=1 ở words[2]
        //      ==> Khá là lung tung khó giải quyết
        //convert to (Chọn gép các char tại index=i tại all of words[j] lại)
        //words = ["abc","cba","cbc","aba"]
        //  + Index = [0,1,2,3] ==> Chọn nhảy cóc cũng được
        //* Bài toán thành dạng có thể dùng dynamic được
        //words = ["abc","cba","cbc","aba"], target = "aba"
        //  + Mỗi words có thể nhận vào m index ==> Vị trí chọn
        //  + dp[i][j]:
        //      + i là vị trí word
        //      + j là vị trí index của target đang xét:
        //          + target[j] = c
        //          Trước đó là c1
        //          + target[j] cần kết hợp với dp[i-k][j-1] + lần cuối xét phải là c1 char
        //a,b,...,a
        //a...,b,a
        //
        //- Vậy nếu chùng tính chất lower case làm thì sao:
        //- dp[i][c]
        //  - i là vị trí chọn từ target
        //      + c1= target[i-1]
        //      => target[i][c]+=dp[i-1][c1]
        //  ==> Complexity đỡ hơn.
        //
//        String[] words = {"acca","bbbb","caca"};
//        String target = "aba";
        String[] words = {"abba","baab"};
        //choose = [ab,ba,ba,ab]
        String target = "bab";
        System.out.println(numWays(words, target));
        System.out.println(numWaysRefer(words, target));
        //
        //#Reference:
        //187. Repeated DNA Sequences
        //3256. Maximum Value Sum by Placing Three Rooks I
        //2846. Minimum Edge Weight Equilibrium Queries in a Tree
    }
}
