package E1_daily;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;

public class E226_CountPrefixAndSuffixPairsI {

    public static class TrieNode{
        HashMap<Character, TrieNode> child;
        public HashSet<String> setStr;
        public TrieNode(){
            child=new HashMap<>();
            setStr=new HashSet<>();
        }
    }

    public static void addWord(String word, TrieNode node){
        TrieNode root=node;
        int n= word.length();

        for(int i=0;i<n;i++){
            TrieNode nextNode = root.child.get(word.charAt(i));

            if(nextNode==null){
                nextNode=new TrieNode();
            }
            root.child.put(word.charAt(i), nextNode);
            root=nextNode;
        }
    }

    public static Pair<HashSet<String>, Boolean> searchWord(String word, TrieNode node){
        TrieNode root=node;
        int n= word.length();

        for(int i=0;i<n;i++){
            TrieNode nextNode = root.child.get(word.charAt(i));
            if(nextNode==null){
                return new Pair<>(null, false);
            }
            root.child.put(word.charAt(i), nextNode);
            root=nextNode;
        }
        return new Pair<>(root.setStr, false);
    }

    public static int countPrefixSuffixPairsBruteForce(String[] words) {
        int n=words.length;
        int rs=0;

        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                String word=words[i];
                String word1=words[j];

                if(word1.startsWith(word)&&word1.endsWith(word)){
                    rs++;
                }
            }
        }
        return rs;
    }

    static class Node {

        private Node[] links = new Node[26];

        // Check if the character is present in the current node
        public boolean contains(char c) {
            return links[c - 'a'] != null;
        }

        // Insert a new node for the character
        public void put(char c, Node node) {
            links[c - 'a'] = node;
        }

        // Get the next node for the character
        public Node next(char c) {
            return links[c - 'a'];
        }
    }

    static class Trie {

        private Node root;

        public Trie() {
            root = new Node();
        }

        // Insert a word into the Trie
        public void insert(String word) {
            Node node = root;
            for (char c : word.toCharArray()) {
                if (!node.contains(c)) {
                    node.put(c, new Node());
                }
                node = node.next(c);
            }
        }

        // Check if the Trie contains a given prefix
        public boolean startsWith(String prefix) {
            Node node = root;
            for (char c : prefix.toCharArray()) {
                if (!node.contains(c)) {
                    return false;
                }
                node = node.next(c);
            }
            return true;
        }
    }

    public static int countPrefixSuffixPairs(String[] words) {
        int n = words.length;
        int count = 0;

        // Step 1: Iterate over each word
        for (int i = 0; i < n; i++) {
            Trie prefixTrie = new Trie();
            Trie suffixTrie = new Trie();

            // Step 2: Insert the current word into the prefix Trie
            prefixTrie.insert(words[i]);

            // Step 3: Reverse the word and insert it into the suffix Trie
            String revWord = new StringBuilder(words[i]).reverse().toString();
            suffixTrie.insert(revWord);

            // Step 4: Iterate over all previous words
            for (int j = 0; j < i; j++) {
                // Step 5: Skip words[j] if it is longer than words[i]
                if (words[j].length() > words[i].length()) continue;

                // Step 6: Extract the prefix and reversed prefix of words[j]
                String prefixWord = words[j];
                String revPrefixWord = new StringBuilder(prefixWord)
                        .reverse()
                        .toString();

                // Step 7: Check if words[j] is both a prefix and suffix of words[i]
                if (
                        prefixTrie.startsWith(prefixWord) &&
                                suffixTrie.startsWith(revPrefixWord)
                ) {
                    count++;
                }
            }
        }

        // Step 8: Return the total count of valid pairs
        return count;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed string array words.
        //- Let's define a boolean function isPrefixAndSuffix that takes two strings, str1 and str2:
        //  + isPrefixAndSuffix(str1, str2) returns true if str1 is both a prefix and a suffix
        // of str2, and false otherwise.
        //- For example, isPrefixAndSuffix("aba", "ababa") is true because "aba" is a prefix of "ababa" and also a suffix,
        // but isPrefixAndSuffix("abc", "abcd") is false.
        //* Return (an integer) denoting (the number of index) pairs (i, j) such that i < j, and isPrefixAndSuffix(words[i], words[j]) is true.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= words.length <= 50
        //1 <= words[i].length <= 10
        //words[i] consists only of lowercase English letters.
        //
        //- Brainstorm
        //- startWith and endWith method
        //Ex:
        //words = ["a","aba","ababa","aa"]
        //- How to count the number of pairs?
        //- Check(s):
        //  + S is match with the prefix of (a)
        //  + S is match with the suffix of (a)
        //      + If s is match with both of them, this is valid
        //==> 2 trie data structure
        //- trie1:
        //  + s is match with prefix of (a,a1,a2)
        //- trie2:
        //  + s is match with prefix of (a1,a3)
        //==> rs=1
        //
        //- Mỗi (i) tạo ra 1 prefix or suffix sau đó vẫn duyệt ngược lại:
        //  ==> Nhanh hơn tý đoạn check startWith và endWith
        //  ==> Time complexity: O(n^2*m)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n*m)
        //- Time: O(n^2*m)
        //
        String[] words = {"a","aba","ababa","aa"};
        System.out.println(countPrefixSuffixPairs(words));
        //#Reference:
        //1030. Matrix Cells in Distance Order
        //1418. Display Table of Food Orders in a Restaurant
        //2479. Maximum XOR of Two Non-Overlapping Subtrees
    }
}
