package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E323_LongestUnequalAdjacentGroupsSubsequenceI {

    public static List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n=words.length;
        int[] dp=new int[n];
        List<String>[] indices=new List[n];
        List<String> rsWord=new ArrayList<>();
        if(n==0){
            return rsWord;
        }
        rsWord.add(words[0]);
        int rs=0;

        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            indices[i]=new ArrayList<>();
            indices[i].add(words[i]);
        }

        for (int i = 0; i < n; i++) {
            int max=Integer.MIN_VALUE;
            int index=-1;
            for(int j=0;j<i;j++){
                if((words[i].equals(words[j])==(groups[i]==groups[j]))&&max<dp[j]){
                    max=dp[j];
                    index=j;
                }
            }
            if(index!=-1){
                dp[i]=max+1;
                indices[i].addAll(0, indices[index]);
            }
            if(rs<max+1){
                rs=max;
                rsWord=indices[i];
            }
        }
        return rsWord;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a string array words) and (a binary array groups both of length n).
        //- A subsequence of words is alternating if for any two consecutive strings in the sequence,
        // their corresponding elements at the same indices in groups are different (that is, there cannot be consecutive 0 or 1).
        //- Your task is to select the longest alternating subsequence from words.
        //* Return the selected subsequence. If there are multiple answers, return any of them.
        //- Note: The elements in words are distinct.
        //
        //Example 1:
        //
        //Input: words = ["e","a","b"], groups = [0,0,1]
        //
        //Output: ["e","b"]
        //
        //Explanation:
        //- A subsequence that can be selected is ["e","b"] because groups[0] != groups[2].
        // Another subsequence that can be selected is ["a","b"] because groups[1] != groups[2].
        //- It can be demonstrated that the length of the longest subsequence of indices that satisfies the condition is 2.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        String[] words = {"e","a","b"};
        int[] groups = {0,0,1};
        System.out.println(getLongestSubsequence(words, groups));
        //
        //#Reference:
        //2911. Minimum Changes to Make K Semi-palindromes - HARD
        //2335. Minimum Amount of Time to Fill Cups
        //1418. Display Table of Food Orders in a Restaurant
    }
}
