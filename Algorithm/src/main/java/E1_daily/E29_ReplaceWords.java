package E1_daily;

import java.util.*;

public class E29_ReplaceWords {

    public static String replaceWords(List<String> dictionary, String sentence) {
        //Space: O(n*m)
        HashSet<String> wordSet=new HashSet<>(dictionary);
        String[] words= sentence.split(" ");
        //Space: O(n*m)
        List<String> rs=new ArrayList<>();

        //Time: O(n)
        for(String word: words){
            StringBuilder curPrefix=new StringBuilder();
            int i;

            //Time : O(m)
            for(i=0;i<word.length();i++){
                curPrefix.append(word.charAt(i));
                if(wordSet.contains(curPrefix.toString())){
                    rs.add(curPrefix.toString());
                    break;
                }
            }
            if(i==word.length()){
                rs.add(word);
            }
        }
        return String.join(" ",rs);
    }

    public static class TrieNode{
        HashMap<Character, TrieNode> child;
        boolean isEnd;
        public TrieNode(){
            child=new HashMap<>();
        }
    }

    public static void addWord(TrieNode root, String word){
        TrieNode node=root;
        int n=word.length();

        for(int i=0;i<n;i++){
            char curChar=word.charAt(i);
            TrieNode child=node.child.get(curChar);
            if(child==null){
                child=new TrieNode();
            }
            node.child.put(curChar, child);
            node=child;
        }
        node.isEnd=true;
    }
    public static String searchPrefix(TrieNode root, String word){
        TrieNode node=root;
        int n=word.length();
        StringBuilder prefix=new StringBuilder();
        boolean isFound=false;

        for(int i=0;i<n;i++){
            char curChar=word.charAt(i);
            TrieNode child=node.child.get(curChar);
            if(child==null || node.isEnd){
                isFound=node.isEnd;
                break;
            }
            prefix.append(curChar);
            node.child.put(curChar, child);
            node=child;
        }
        if(prefix.toString().equals("") || !isFound){
            return word;
        }
        return prefix.toString();
    }

    public static String replaceWordsTrie(List<String> dictionary, String sentence) {
        //Space: O(n*m)
        TrieNode root=new TrieNode();

        for(String word: dictionary){
            addWord(root, word);
        }
        String[] words=sentence.split(" ");
        int n=words.length;
        StringBuilder rs=new StringBuilder();

        for(int i=0;i<n;i++){
            rs.append(searchPrefix(root, words[i]));
            if(i!=n-1){
                rs.append(" ");
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- In English, we have a concept called (root), which can be followed by some other word to form another longer word
        // - let's call this word (derivative).
        // For example, when the root "help" is followed by the word "ful", we can form a derivative "helpful".
        //- Given (a dictionary consisting of many roots) and a sentence consisting of words separated by spaces,
        // replace (all the derivatives) in the sentence with the root forming it.
        //- If a derivative can be replaced by more than one root, replace (it) with the (root) that has (the shortest length).
        //* Return (the sentence) after (the replacement).
        //
        //** Idea
        //1.
        //1.0, Hashtable
        //* Method-1:
        //- Constraint:
        //1 <= dictionary.length <= 1000
        //1 <= dictionary[i].length <= 100
        //dictionary[i] consists of only lower-case letters.
        //1 <= sentence.length <= 106
        //sentence consists of only lower-case letters and spaces.
        //The number of words in sentence is in the range [1, 1000]
        //The length of each word in sentence is in the range [1, 1000]
        //Every two consecutive words in sentence will be separated by exactly one space.
        //sentence does not have leading or trailing spaces.
        //==> Số lượng dictionary 1000 --> O(n^2) được.
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
        //Output: "the cat was rat by the bat"
        //==> Bài này có thể dùng hashtable
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N: Is the number of the words
        //- M: Is the average length of all of words
        //- Space: O(n*m)
        //- Time: O(n*m)
        //
        //2.
        //2.0, Trie
        //- Chỉ cần add từng character vào trie ==> Trống thì add all
        //  ==> Nếu có leaf node --> Stop.
        //Example 1:
        //Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
        //Output: "the cat was rat by the bat"
        // root -c-> node1 -a->
        // root -b-> node2 -a->
        //* Trie class:
        //  + HashMap<Char, TrieNode> adj
        //
        //- Special cases:
        //
        //- End of each dict:
        //Ex:
        //dict = "bat"
        //sentence = "by"
        //==> Làm sao để biết nó chạy hết dict ==> Vì dừng ở "b" thì không tìm được next char
        //
        //- Update lại dict với shorter dict:
        //Ex:
        //dict = ["catt","cat"]
        //==> Cần update lại (catt) thành (cat)
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n)
        //
        //#Reference
        //1021. Remove Outermost Parentheses
        //832. Flipping an Image
        //2534. Time Taken to Cross the Door
        //
//        String[] dictionary = {"cat","bat","rat"};
//        String sentence = "the cattle was rattled by the battery";
        String[] dictionary = {"catt","cat","bat","rat"};
        String sentence = "the cattle was rattled by the battery";
        System.out.println(replaceWordsTrie(Arrays.asList(dictionary), sentence));
        //#Reference:
        //2049. Count Nodes With the Highest Score
        //1345. Jump Game IV
        //1178. Number of Valid Words for Each Puzzle
    }
}
