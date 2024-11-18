package E1_daily;

import java.util.*;

public class E147_StringCompressionII_classic {

    public static int[][] memo;
    public static int solution(int index, List<Integer> listChars, int[] prefixLength, int curK){
        if(index==listChars.size()){
            return 0;
        }
        if(curK<=0){
            return prefixLength[index];
        }
        if(memo[index][curK]!=Integer.MAX_VALUE){
            return memo[index][curK];
        }
        int curCount = listChars.get(index);
        //100 -> 99
        //100 -> 9
        //100 -> 1
        //100 -> 0
        int curMinLen=Integer.MAX_VALUE;
        int newLen = String.valueOf(curCount).length()+1;
        if(curCount>99&&curCount-99<=curK){
            curMinLen=Math.min(curMinLen, solution(index+1, listChars, prefixLength, curK-(curCount-99))+newLen-1);
        }
        if(curCount>9&&curCount-9<=curK){
            if(curCount>=100){
                curMinLen=Math.min(curMinLen, solution(index+1, listChars, prefixLength, curK-(curCount-9))+newLen-2);
            }else{
                curMinLen=Math.min(curMinLen, solution(index+1, listChars, prefixLength, curK-(curCount-9))+newLen-1);
            }
        }
        if(curCount>1&&curCount-1<=curK){
            if(curCount>=100){
                curMinLen=Math.min(curMinLen, solution(index+1, listChars, prefixLength, curK-(curCount-1))+newLen-3);
            }else if(curCount>=10){
                curMinLen=Math.min(curMinLen, solution(index+1, listChars, prefixLength, curK-(curCount-1))+newLen-2);
            }else{
                curMinLen=Math.min(curMinLen, solution(index+1, listChars, prefixLength, curK-(curCount-1))+newLen-1);
            }
        }
        if(curCount<=curK){
            if(curCount>=100){
                curMinLen=Math.min(curMinLen, solution(index+1, listChars, prefixLength, curK-curCount)+newLen-4);
            }else if(curCount>=10){
                curMinLen=Math.min(curMinLen, solution(index+1, listChars, prefixLength, curK-curCount)+newLen-3);
            }else{
                curMinLen=Math.min(curMinLen, solution(index+1, listChars, prefixLength, curK-curCount)+newLen-2);
            }
        }
        curMinLen=Math.min(curMinLen, solution(index+1, listChars, prefixLength, curK)+newLen);
        return memo[index][curK]=curMinLen;
    }

    public static int getLengthOfOptimalCompressionWrong(String s, int k) {
        int n= s.length();

        int curLen=0;
        int i=n-1;
        //Chỉ cần lưu list count là được
        //
        List<Integer> listCount=new ArrayList<>();
        while (i>=0){
            char curChar = s.charAt(i);
            i--;
            int len=1;
            while(i>=0&&s.charAt(i)==curChar){
                i--;
                len++;
            }
            listCount.add(len);
//            System.out.printf("i: %s, prefix: %s\n", i, prefixLength[i]);
        }
        int[] prefixLength=new int[listCount.size()];
        for (int j = listCount.size()-1; j >=0; j--) {
            int len = listCount.get(j)==1?0:String.valueOf(listCount.get(j)).length();
            curLen+=len+1;
            prefixLength[j]=curLen;
        }
//        System.out.println(listCount);
//        System.out.println(setChars);
//        System.out.println(curLen);
        memo=new int[n][k+1];
        for(i=0;i<n;i++){
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        return solution(0, listCount, prefixLength, k);
//        return 1;
    }

    public static Set<Integer> add;
    public static HashMap<Integer, Integer> memo1;

    private static int genKey(int index, char lastCh, int countOfLastCh, int k) {
        return (index << 24) | (lastCh << 16) | (countOfLastCh << 8) | k;
    }

    public static int solutionMemo(String s, int index, char lastChar, int lastCharCount, int k){
        if(k<0){
            return Integer.MAX_VALUE/2;
        }
        if(index==s.length()){
            return 0;
        }
        int key= index*101*27*101 + (lastChar-'a')*101*101 + lastCharCount*101+k;
        //Key = combination of (index, lastChar, lastCharCount, k)
        //- Index: 0 -> 100
        //- lastChar: 0 -> 25
        //- lastCharCount: 0 -> 100
        //- k: 0 -> 100
        //k + lastCharCount ==> need to exceed max(lastCharCount)
        //  + >= 101
        //+ lastCharCount*101+k:
        //  + >=101
        //lastChar + k + lastCharCount
        //  + Max(lastChar) = 27
        //  ==> *27 next
        //- Max(index)=101
        //  ==> *101 next
        if(memo1.containsKey(key)){
            return memo1.get(key);
        }
        int deleteCharRs = solutionMemo(s, index+1, lastChar, lastCharCount, k-1);
        int keepCharRs;
        if(s.charAt(index)==lastChar){
            keepCharRs=solutionMemo(s, index+1, lastChar, lastCharCount+1, k) + (add.contains(lastCharCount)?1:0);
        }else{
            keepCharRs=solutionMemo(s, index+1, s.charAt(index), 1, k)+1;
        }
        int rs=Math.min(keepCharRs, deleteCharRs);
        memo1.put(key, rs);
        return rs;
    }

    public static int getLengthOfOptimalCompression(String s, int k) {
        //Space: O(n*k^2)
        memo1=new HashMap<>();
        add=new HashSet<>();
        add.add(1);
        add.add(9);
        add.add(99);
        return solutionMemo(s, 0, (char) ('a' + 26), 0, k);
    }

    public static int getLengthOfOptimalCompressionBottomUp(String s, int k) {
        int n = s.length();
        // Use a 2D array for dynamic programming
        int[][] dp = new int[n + 1][k + 1];

        // Initialize the dp array with large values
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = 9999;
            }
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                int cnt = 0, del = 0;
                for (int l = i; l >= 1; l--) {
                    if (s.charAt(l - 1) == s.charAt(i - 1)) {
                        cnt++;
                    } else {
                        del++;
                    }

                    if (j - del >= 0) {
                        int addition = (cnt >= 100) ? 3 : (cnt >= 10) ? 2 : (cnt >= 2) ? 1 : 0;
                        dp[i][j] = Math.min(dp[i][j], dp[l - 1][j - del] + 1 + addition);
                    }
                }
                if (j > 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        //** Requirement:
        //- (Run-length encoding) is a string compression method that works by replacing (consecutive identical characters) (repeated 2 or more times)
        // with (the concatenation of the character) and (the number marking the count) of (the characters) (length of the run).
        //  + For example, to compress the string "aabccc" we replace "aa" by "a2" and replace "ccc" by "c3".
        // Thus the compressed string becomes "a2bc3".
        //* Notice that in this problem, we are (not adding '1') after (single characters).
        //- Given a string s and an integer k.
        //- You need to delete at most (k characters) from s such that (the run-length encoded version) of s has (minimum length).
        //- Find (the minimum length) of (the run-length encoded version of s) after deleting at most (k characters).
        //* Find the min length after deleting at most (k characters)
        //  + Delete before transforming.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 100
        //0 <= k <= s.length
        //s contains only lowercase English letters.
        //  + Length <= 100 ==> Time: O(n^3)
        //
        //- Brainstorm
        //Input: s = "aaabcccd", k = 2
        //Output: 4
        //The optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.
        //- How we choose the way to delete (at most k characters) in the (optimal way)
        //- If we want to reduce length of s by 1:
        //  + Delete the character with count = 1
        //- If we want to reduce length of s by 2:
        //  + Delete the character with count = x
        //- Detele from (the min count -> max count)
        //  + Delete in order
        //* The number of character will be more than 1 digit
        //Ex:
        //a10b3, k=1
        //- Delete a ==> a9a3
        //
        //Ex:
        //a10b2, k=2
        //+ Delete(a,b): a9b (len = 3)
        //+ Delete(b): a10 (len = 3)
        //- Delete a ==> a9a3
        //
        //- count char trước
        //Ex:
        //a10b3
        //count[a] = 10
        //count[b] = 3
        //
        //Ex:
        //a: 10, b: 3, c:5, k= 4
        //- Memo được không
        //- dp[i][remainingK]
        //- Mỗi chars ta có thể delete (at most k) chars
        //  + Time: O(n^3) ==> May be TLE
        //- For each chars, we can delete following this rule:
        //  + Skip
        //  + Delete from 2 digits -> 1 digits
        //  + Delete from count=2 -> count = 1
        //  + Delete from count=1 -> count = 0
        //
        //* ISSUE:
        //- Nếu count char:
        //  ==> s có char có thể lặp lại:
        //Ex:
        //String s = "bababbaba";
        //int k = 1;
        //
        //- Nó còn vấn đề liên quan đến việc delete at the middle:
        //  ==> bab ==> delete(a)
        //  = bb = b2
        //
        //- Bài dùng được min heap không?
        //  ==> Không dùng được
        //
        //- Bài này phức tạp ở đoạn delete nên ta lựa chọn traverse index++ following this rule:
        //- ở index ta có lựa chọn:
        //  + Delete s[index]
        //  + Non delete s[index]
        //- To know the action can make impact on the result we need to:
        //  + Store the last character
        //                  ,3
        //                /     \
        //              /(keep)  \(delete)
        //           (a,3)       (,2)
        //            /          \
        //         (ab,3)       (a,2)
        //         /    \        /    \
        //      (abb,3) (ab,2) (ab,2)  (a,1)
        //- Otherwise, we need to care about (special values):
        //  + 1, 9, 99
        //** KINH NGHIỆM:
        //- Generate key for multiple variables:
        //Key = combination of (index, lastChar, lastCharCount, k)
        //- Index: 0 -> 100
        //- lastChar: 0 -> 25
        //- lastCharCount: 0 -> 100
        //- k: 0 -> 100
        //k + lastCharCount ==> need to exceed max(lastCharCount)
        //  + >= 101
        //+ lastCharCount*101+k:
        //  + >=101
        //lastChar + k + lastCharCount
        //  + Max(lastChar) = 27
        //  ==> *27 next
        //- Max(index)=101
        //  ==> *101 next
        //
        //- Hoặc ta có thể chọn generate theo bit
        //key = (index << 24) | (lastCh << 16) | (countOfLastCh << 8) | k;
        //k: 101
        //countOfLastCh: 101 >k < 2^8 = 256
        //lastCh: > 101*101 < 2^16 = 65536
        //==> Nch cách này cũng là dựa trên idea trên thôi.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- lastCountChar <= n
        //- Each DP state is defined as (idx, last, cnt, k), so we can calculate
        // the number of DP states as the product of the number of possible values for each parameter.
        // There will be n possible values for idx, A possible values for last, where A=26 is the size of the alphabet,
        // n possible values for cnt, and k possible values for k. Thus, there are O(A*n^2*k)
        // k) possible DP states.
        //- However we can tighten our upper bound and get rid of A in our complexity.
        // Let us look at pairs (last, cnt) and check how many of them we have. Consider the case aaababbbcc.
        // Then for letter a we can have states (a, 1), (a, 2), (a, 3) and (a, 4),
        // because we have only 4 letters a in our string and after deletions we can not have more.
        // We have states (b, 1), (b, 2), (b, 3), (b, 4), (c, 1), (c, 2).
        //* Notice that some of these states can not be reached,
        // because we (do not have enough deletions).
        // But what we know for sure is that the total number of pairs (last, cnt) is not more than n.
        // Now we can adjust our analysis and we have:
        //  + When we consider our states, we have n options to choose index
        //  + We have n options in total to choose a pair (l, lc), because for each letter the maximum length is the frequency of this letter.
        //  + We have k+1 options to choose (how many elements) we need to delete:
        //      + it can be (0, ..., k).
        //Also we have at most 2 transitions from (one state) to another and it makes total time complexity O(nk^2).
        //
        //- Mỗi char chỉ delete cùng lắm k turns.
        //
        //- Space: O(n*n^2)
        //- Time: O(n*n^2)
        //
//        String s = "aaabcccd";
        //a3bc3d
//        int k = 2;
//        String s = "aabbaa";
        //a3bc3d
//        int k = 2;
//        String s = "aaaaaaaaaaa";
        //a3bc3d
//        int k = 0;
//        String s = "aaaaaaaaaabbb";
        //a3bc3d
//        int k = 1;
//        String s = "aaaaaaaaaabb";
        //a3bc3d
//        int k = 2;
        String s = "bababbaba";
        //[1, 1, 1, 2, 1, 1, 1, 1]
        //babab2aba
        int k = 1;
        //
        //
        //
        System.out.println(getLengthOfOptimalCompressionWrong(s, k));
        System.out.println(getLengthOfOptimalCompression(s, k));
        System.out.println(getLengthOfOptimalCompressionBottomUp(s, k));
    }
}
