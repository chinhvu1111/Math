package contest;

import interviews.bytedance.E23_LongestCommonPrefix;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class E302_LongestCommonPrefixOfKStringsAfterRemoval {

    public static TreeMap<Integer, HashSet<Integer>> lenToIndices;

    public static class TrieNode{
        public HashMap<Character, TrieNode> children;
        public int prevIndex=-1;

        public TrieNode() {
            children=new HashMap<>();
        }
    }

    public static String insertWord(TrieNode root, String s, int index){
        int n=s.length();
        TrieNode node=root;
        StringBuilder commonStr=new StringBuilder();
        int length=1;

        for(int i=0;i<n;i++){
            Character ch=s.charAt(i);
            TrieNode children=node.children.get(ch);

            if(children==null){
                children=new TrieNode();
                node.children.put(ch, children);
            }else {
                HashSet<Integer> curIndices=lenToIndices.get(length);
                if(curIndices==null){
                    curIndices=new HashSet<>();
                }
                curIndices.add(index);
                curIndices.add(children.prevIndex);
                lenToIndices.put(length, curIndices);
            }
            if(children.prevIndex==-1){
                children.prevIndex=index;
            }
            node=children;
            commonStr.append(s.charAt(i));
            length++;
        }
        node.children.clear();
        return commonStr.toString();
    }
    

    public static int[] longestCommonPrefix(String[] words, int k) {
        int n=words.length;
        TrieNode root=new TrieNode();
        lenToIndices=new TreeMap<>();

        for (int i=0;i<n;i++){
            insertWord(root, words[i], i);
        }
        int[] rs=new int[n];
        if(lenToIndices.size()==0){
            return rs;
        }
        for (int i = 0; i < n; i++) {
            int maxLength = lenToIndices.lastKey();
            HashSet<Integer> curIndices=lenToIndices.get(maxLength);
            if(curIndices.size()>2||!curIndices.contains(i)){
                rs[i]=maxLength;
            }else {
                Integer prevKey = lenToIndices.floorKey(maxLength-1);
                if(prevKey!=null){
                    rs[i]=prevKey;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array of strings words and an integer k.
        //- For each index i in the range [0, words.length - 1],
        // find (the length of the longest common prefix) among any k strings (selected at distinct indices)
        // from (the remaining array) after removing (the ith element).
        //
        //* Return an array answer, where answer[i] is the answer for (ith element).
        // If removing (the ith element) leaves the array with (fewer than k strings), answer[i] is 0.
        //- A prefix of a string is a substring that starts from the beginning of the string
        // and extends to any point within it.
        //- A substring is a contiguous sequence of characters within a string.
        //
        //Example 1:
        //
        //Input: words = ["jump","run","run","jump","run"], k = 2
        //Output: [3,4,4,3,4]
        //Explanation:
        //
        //Removing index 0 ("jump"):
        //words becomes: ["run", "run", "jump", "run"]. "run" occurs 3 times.
        // Choosing any two gives the longest common prefix "run" (length 3).
        //Removing index 1 ("run"):
        //words becomes: ["jump", "run", "jump", "run"]. "jump" occurs twice.
        // Choosing these two gives the longest common prefix "jump" (length 4).
        //Removing index 2 ("run"):
        //words becomes: ["jump", "run", "jump", "run"]. "jump" occurs twice.
        // Choosing these two gives the longest common prefix "jump" (length 4).
        //Removing index 3 ("jump"):
        //words becomes: ["jump", "run", "run", "run"]. "run" occurs 3 times.
        // Choosing any two gives the longest common prefix "run" (length 3).
        //Removing index 4 ("run"):
        //words becomes: ["jump", "run", "run", "jump"]. "jump" occurs twice.
        // Choosing these two gives the longest common prefix "jump" (length 4).
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= k <= words.length <= 10^5
        //1 <= words[i].length <= 10^4
        //words[i] consists of lowercase English letters.
        //The sum of words[i].length is smaller than or equal 10^5.
        //  + Length<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //- Do this normal:
        //  + ==> Trie
        //                5[3]
        //              /
        // 1 -> 2 -> 3[1] -> 4[2] -> 6[4]
        // 3 ->
        //- Remove[1] ==> 4
        //- Remove[4] ==> 3
        //- Remove[3] ==> 4
        //- Remove[2] ==> 3
        //
        //- How we store the intermediate prefix result?
        //- Common prefix ==> Run 2 times
        //- Add word + index of word
        //
        //
        //1.1, Cases
        //
        //
//        String[] words = {"jump","run","run","jump","run"};
//        int k = 2;
//        String[] words = {"dog","racer","car"};
//        int k = 2;
        String[] words = {"db","ca","ab","e","dff","b","afcff"};
        int k = 4;
        int[] rs= longestCommonPrefix(words, k);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
    }
}
