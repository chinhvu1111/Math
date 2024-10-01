package E1_daily;

import java.util.Arrays;
import java.util.HashSet;

public class E110_ExtraCharactersInAString_classic {

    public static class TrieNode{
        TrieNode[] child;
        boolean finish;
        public TrieNode(){
            child=new TrieNode[26];
        }
    }

    public static void addWord(TrieNode root, String word){
        TrieNode node = root;
        int n=word.length();

        for(int i=n-1;i>=0;i--){
            TrieNode child=node.child[word.charAt(i)-'a'];
            if(child==null){
                child=new TrieNode();
            }
            node.child[word.charAt(i)-'a']=child;
            node=child;
        }
        node.finish=true;
    }

    public static int minExtraChar(String s, String[] dictionary) {
        HashSet<String> setDict = new HashSet<>(Arrays.asList(dictionary));
        int n = s.length();
        int[] dp=new int[n+1];

        for(int i=0;i<n;i++){
            StringBuilder curSubStr=new StringBuilder();
            int curRs=Integer.MAX_VALUE;

            for(int j=i;j>=0;j--){
                curSubStr.insert(0, s.charAt(j));
                if(setDict.contains(curSubStr.toString())){
                    //do[j-1]+(j->i)
                    curRs=Math.min(curRs, dp[j]);
                }else{
                    curRs=Math.min(curRs, dp[j]+(i-j+1));
                }
            }
            dp[i+1]=curRs;
        }
        return dp[n];
    }

    public static int minExtraCharOptimization(String s, String[] dictionary) {
        //Space: O(m*n)
        HashSet<String> setDict = new HashSet<>();

        for(String str: dictionary){
            setDict.add(new StringBuilder(str).reverse().toString());
        }
        int n = s.length();
        int[] dp=new int[n+1];
        int min=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            StringBuilder curSubStr=new StringBuilder();
            int curRs=Integer.MAX_VALUE;

            //Làm ntn để có 1 string:
            //+ Độ dài tăng dần insert vào first
            //abc:
            //  + add(i=4)
            //  + add(i=3)
            //  + add(i=2)
            //  ==> Add reverse của dictionary là được.
            for(int j=i;j>=0;j--){
                curSubStr.append(s.charAt(j));
                if(setDict.contains(curSubStr.toString())){
                    //do[j-1]+(j->i)
                    curRs=Math.min(curRs, dp[j]);
                }else{
                    curRs=Math.min(curRs, dp[j]+(i-j+1));
                }
            }
            dp[i+1]=curRs;
            min=Math.min(curRs, min);
        }
        return dp[n];
    }

    public static int minExtraCharOptimization1(String s, String[] dictionary) {
        TrieNode root=new TrieNode();
        //Time: O(m*k)
        for(String str: dictionary){
            addWord(root, str);
        }
        int n = s.length();
        //Space: O(n)
        int[] dp=new int[n+1];

        //Time: O(n)
        for(int i=0;i<n;i++){
            int curRs=Integer.MAX_VALUE;
            TrieNode node = root;
            //Time: O(m)
            for(int j=i;j>=0;j--){
                node = node.child[s.charAt(j)-'a'];
                if(node==null){
                    curRs=Math.min(curRs, dp[j]+(i-j+1));
                    break;
                }else{
                    if(node.finish){
                        curRs=Math.min(curRs, dp[j]);
                    }else{
                        curRs=Math.min(curRs, dp[j]+(i-j+1));
                    }
                }
            }
            dp[i+1]=curRs;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed string s) and (a dictionary of words dictionary).
        //- You have to break (s) into (one or more) (non-overlapping substrings)
        //  such that (each substring) is present in dictionary.
        //- There may be (some extra characters) in s which are (not present in) any of the substrings.
        //* Return (the minimum number of extra characters) left over if you break up s optimally.
        //- Trả lại (min) số lượng thừa
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 50
        //1 <= dictionary.length <= 50
        //1 <= dictionary[i].length <= 50
        //dictionary[i] and s consists of only lowercase English letters
        //dictionary contains distinct words
        //  + Length của word trong dictionary không quá lớn ==> Time: O(n^3)
        //
        //- Brainstorm
        //
        //Example 1:
        //
        //Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
        //Output: 1
        //Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8.
        // There is only 1 unused character (at index 4), so we return 1.
        //
        //- Giả sử dp[i] số lượng ký tự còn lại sau khi matching
        //  + j = i-1 -> 0:
        //      + Nếu s[j:i] không thuộc dictionary:
        //          + dp[i] = dp[j-1] + (i-j)
        //      + Nếu s[j:i] thuộc dictionary:
        //          + dp[i] = dp[j-1]
        //
        //1.1, Optimization
        //- Sửa đoạn insert(0, c):
        //  + Add reverse(dictionary)
        //  + Append thay vì insert.
        //- Dùng trie ==> Reduce time
        //=> Đỡ phải subtring
        //  + Ta có thể check từng characer từ (right -> left)
        //** Kinh nghiệm:
        //  + Để check xem word từ vị trí (i -> j) có tồn tại trong list of words hay không?
        //  ==> Ta nên dùng trie để xử lý (Break khi không match word cho nhanh)
        //  Ex: prefix ab ==> Không xuất hiện trong dict
        //  ==> Break luôn khỏi xét tiếp (Vì kiểu gì cũng không thấy nữa đâu)
        //
        //1.2, Complexity
        //- n is the length of s
        //- m is average length of word in dict
        //- k is the number of word in dict.
        //- Space: O(m*n+n)
        //- Time: O(n^3) ==> O(m*k+m*n)
        //
        String s = "leetscode";
        String[] dictionary = {"leet","code","leetcode"};
        System.out.println(minExtraChar(s, dictionary));
        System.out.println(minExtraCharOptimization(s, dictionary));
        System.out.println(minExtraCharOptimization1(s, dictionary));
        //#Reference:
        //2350. Shortest Impossible Sequence of Rolls
        //1260. Shift 2D Grid
        //2174. Remove All Ones With Row and Column Flips II
        //2850. Minimum Moves to Spread Stones Over Grid
        //3276. Select Cells in Grid With Maximum Score
        //761. Special Binary String
    }
}
