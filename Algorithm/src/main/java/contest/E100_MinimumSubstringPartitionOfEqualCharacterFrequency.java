package contest;

import java.util.Arrays;

public class E100_MinimumSubstringPartitionOfEqualCharacterFrequency {

    public static boolean check(int[] count){
        int val=-1;
        for(int i=0;i<26;i++){
            if(count[i]!=-1){
                val=count[i];
            }
        }
        for(int i=0;i<26;i++){
            if(count[i]!=-1&&count[i]!=val){
                return false;
            }
        }
        return true;
    }

    public static int minimumSubstringsInPartition(String s) {
        int n = s.length();
        boolean[][] bl=new boolean[n][n];

        for(int i=0;i<n;i++){
            int[] count=new int[26];
            Arrays.fill(count, -1);
            int distinct=0;
//            if(count[s.charAt(i)-'a']==-1){
//                count[s.charAt(i)-'a']=1;
//            }else{
//                count[s.charAt(i)-'a']++;
//            }

            for(int j=i;j<n;j++){
                if(count[s.charAt(j)-'a']==-1){
                    count[s.charAt(j)-'a']=1;
                    distinct++;
                }else{
                    count[s.charAt(j)-'a']++;
                }
//                System.out.printf("i=%s, j=%s, val=%s\n", i, j, count[s.charAt(j)-'a']);
                bl[i][j]=check(count);
            }
        }
//        System.out.println(bl[3][6]);
//        System.out.println(bl[4][6]);
//        System.out.println(bl[2][6]);
        int[] dp=new int[n];
        dp[0]=1;
        for(int i=1;i<n;i++){
            if(bl[0][i]){
                dp[i]=1;
                continue;
            }
            int curMin=Integer.MAX_VALUE;

            for(int j=i-1;j>=0;j--){
                if(bl[j+1][i]){
                    curMin=Math.min(curMin, dp[j]);
                }
            }
            dp[i]=curMin+1;
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given a string s, you need to partition it into (one or more) balanced substrings.
        // For example, if s == "ababcc" then ("abab", "c", "c"), ("ab", "abc", "c"), and ("ababcc") are (all valid partitions),
        // but ("a", "bab", "cc"), ("aba", "bc", "c"), and ("ab", "abcc") are not.
        //  + The unbalanced substrings are bolded.
        //* Return (the minimum number of substrings) that you can partition s into.
        //Note:
        //- (A balanced string) is a string where (each character) in the string occurs (the same number of times).
        //  + Tức là string balance --> count phải = nhau hết.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 1000
        //s consists only of English lowercase letters.
        //+ Length không lớn lắm ==> O(n^2) được
        //
        //- Brainstorm
        //Example 1:
        //Input: s = "fabccddg"
        //Output: 3
        //- Partition ntn để có kết quả?
        //- Partition ntn để có số lượng partitions MIN?
        //+ fabccddg : fabc,ccdd,g
        //Thay đổi 1 chút để fab không lấy c nữa:
        //+ fabcccdddggg = fab,cccdddggg : =2
        //
        //- Bài này có thể làm dynamic được:
        //- Check blanced string trong (i,j) ntn?
        //  + dp[i][j]: trả lại việc khoảng giữa (i,j) có là blanced string hay không
        //Ex:
        //fabccddg
        //
        //
//        String s = "fabccddg";
//        String s = "ccccccg";
//        String s = "cccccc";
        String s = "abbcabaa";
        //abbcabaa = ab|bc|ab|aa
        //0 = 1 : a
        //1 = 1 : a|b
        //2 = 2 : a|bb
        //3 = 2 : ab|bc
        //4 = 2 : abbca = ab|bca
        //5 = 3 : abbcab = ab|b|cab
        //6 =   : abbcaba = ab|bc|a|ba
        //  + abbca + ba
        //  a + bbcaba
        System.out.println(minimumSubstringsInPartition(s));
    }
}
