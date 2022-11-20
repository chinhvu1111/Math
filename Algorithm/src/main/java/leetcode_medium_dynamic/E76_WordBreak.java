package leetcode_medium_dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class E76_WordBreak {

    public static boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordsSet=new HashSet<>();

        wordsSet.addAll(wordDict);
        int n=s.length();
        boolean[] validS=new boolean[n];

        if(wordsSet.contains(s)){
            return true;
        }
        for(int i=0;i<n;i++){
            boolean currentValue=false;

            for(int j=i-1;j>=-1;j--){
                if(((j!=-1&&validS[j])
                        ||wordsSet.contains(s.substring(0, j+1))) &&
                        wordsSet.contains(s.substring(j+1, i+1))){
                    currentValue=true;
                    break;
                }
            }
            validS[i]=currentValue;
        }
        return validS[n-1];
    }

    public static boolean wordBreakRefactor1(String s, List<String> wordDict) {
        HashSet<String> wordsSet=new HashSet<>();

        wordsSet.addAll(wordDict);
        int n=s.length();
        boolean[] validS=new boolean[n];

        if(wordsSet.contains(s)){
            return true;
        }
        for(int i=0;i<n;i++){
            for(int j=i-1;j>=-1;j--){
                if( (j == -1 || validS[j])
                        &&wordsSet.contains(s.substring(j+1, i+1))){
                    validS[i]=true;
                    break;
                }
            }
        }
        return validS[n-1];
    }

    public static boolean wordBreakRefactor2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        int minLen = Integer.MAX_VALUE;
        int maxLen = Integer.MIN_VALUE;
        for (String word : wordDict) {
            minLen = Math.min(minLen, word.length());
            maxLen = Math.max(maxLen, word.length());
        }

        dp[0] = true;
        for (int i = minLen; i <= s.length(); i++) {
            int j = i - maxLen < 0 ? 0 : i-maxLen;
            for (; j <= i-minLen; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    public static void main(String[] args) {
//        String s="pineapplepenapple";
//        String[] wordDict=new String[]{"apple","pen","applepen","pine","pineapple"};
//
//        String s="pineapplepenapple";
//        String[] wordDict=new String[]{"pineapplepenapple"};
//        String s="a";
//        String[] wordDict=new String[]{"a"};
//
        String s="aaaaaaa";
        String[] wordDict=new String[]{"aaaa","aa"};
        List<String> list= Arrays.asList(wordDict);

        System.out.println(wordBreak(s, list));
        System.out.println(wordBreakRefactor1(s, list));
        System.out.println(wordBreakRefactor2(s, list));
        //
        //** Đề bài
        //- Kiểm tra xem 1 letter có được tạo thành từ 1 array các letters khác hay không
        //
        //** Bài này tư duy như sau:
        //1,
        //1.1, Ta không cần thiết
        //wordsSet.contains(s.substring(0, j+1))) &&
        //wordsSet.contains(s.substring(j+1, i+1))
        //--> Chạy cả 2 phía substring
        //==> Ta chỉ cần check:
        //- wordsSet.contains(s.substring(j+1, i+1))
        //- valid[j] --> j sẽ được kết hợp với (j+1)
        //
        //1.2,
        //Cần quan tâm đến range của j: n-1 --> -1
        //==> subString(j+1, i+1) ==> j+1=0 --> j=-1 có thể thỏa mãn điều kiện.
        //1.3, Refactor
        //- Đâu đó có thể run nhanh hơn dựa trên (min_length) và (max_length) của all letter bên trong array
        //
        //2,
        //2.1,
        //- Time complexity : O(N^3)
        //- Space complexity : O(N)

    }
}
