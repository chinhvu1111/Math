package E1_daily;

import java.util.ArrayList;
import java.util.List;

public class E228_WordSubsets_classic {

    public static List<String> wordSubsets(String[] words1, String[] words2) {
        int n=words2.length;
        int[] count2=new int[26];

        for(int i=0;i<n;i++){
            int m=words2[i].length();
            int[] tmpCount=new int[26];

            for(int j=0;j<m;j++){
                tmpCount[words2[i].charAt(j)-'a']++;
            }

            for(int j=0;j<26;j++){
                count2[j]=Math.max(count2[j], tmpCount[j]);
            }
        }
        int l=words1.length;
        List<String> rs=new ArrayList<>();

        for(int i=0;i<l;i++){
            int m=words1[i].length();
            int[] tmpCount=new int[26];

            for(int j=0;j<m;j++){
                tmpCount[words1[i].charAt(j)-'a']++;
            }
            boolean isValid=true;
            for(int j=0;j<26;j++){
                if(tmpCount[j]<count2[j]){
                    isValid=false;
                    break;
                }
            }
            if(isValid){
                rs.add(words1[i]);
            }
        }
        return rs;
    }

    public static List<String> wordSubsetsRefer(String[] A, String[] B) {
        int[] bmax = count("");
        for (String b: B) {
            int[] bCount = count(b);
            for (int i = 0; i < 26; ++i)
                bmax[i] = Math.max(bmax[i], bCount[i]);
        }

        List<String> ans = new ArrayList();
        search: for (String a: A) {
            int[] aCount = count(a);
            for (int i = 0; i < 26; ++i)
                if (aCount[i] < bmax[i])
                    continue search;
            ans.add(a);
        }

        return ans;
    }

    public static int[] count(String S) {
        int[] ans = new int[26];
        for (char c: S.toCharArray())
            ans[c - 'a']++;
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two string arrays words1 and words2.
        //- A string b is (a subset of string a) if (every letter) in b occurs in a including (multiplicity).
        //  + For example, "wrr" is a subset of "warrior" but is not a subset of "world".
        //- A string a from words1 is ("universal") if for (every string b) in words2, b is (a subset of a).
        //* Return (an array of all the universal strings) in words1.
        //  + You may return the answer in any order.
        //
        //Example 1:
        //
        //Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
        //Output: ["facebook","google","leetcode"]
        //- All words in words2 are the subset of the specific string in words1
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= words1.length, words2.length <= 10^4
        //1 <= words1[i].length, words2[i].length <= 10
        //words1[i] and words2[i] consist only of lowercase English letters.
        //All the strings of words1 are unique.
        //
        //- Brainstorm
        //- Count[26] of the words2
        //  ==> We just need to get max count for each [char]
        //  + We just need to check max
        //- Count[26] for each string in words1
        //- We use the brute force to check the every string in words1
        //
        //1.1, Optimization
        //- Continue đến 1 điểm nào đấy
        //=========== CODE
        //search: for (String a: A) {
        //            int[] aCount = count(a);
        //            for (int i = 0; i < 26; ++i)
        //                if (aCount[i] < bmax[i])
        //                    continue search; // ==> Phần này có thể (go to search)
        //            ans.add(a);
        //        }
        //=========== CODE
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n+m)
        //
        String[] words1 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2 = {"e","o"};
        System.out.println(wordSubsets(words1, words2));
        System.out.println(wordSubsetsRefer(words1, words2));
        //
        //#Reference:
        //1541. Minimum Insertions to Balance a Parentheses String
        //1092. Shortest Common Supersequence
        //932. Beautiful Array
    }
}
