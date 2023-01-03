package interviews;

import java.util.HashMap;
import java.util.HashSet;

public class E255_CheckIfAWordOccursAsAPrefixOfAnyWordInASentence {

    public static class TrieNode{
//        public HashMap<Character, TrieNode> children;
        public TrieNode[] children;
        public int startIndex;
        public boolean finished;

        public TrieNode() {
            children=new TrieNode[26];
        }
    }

    public static void insertWord(TrieNode root, String s, int startIndex){
        TrieNode node=root;
        root.startIndex=startIndex;
        int n=s.length();

        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            TrieNode children=node.children[ch-'a'];

            if(children==null){
                children=new TrieNode();
                node.children[ch-'a']= children;
                children.startIndex=startIndex;
            }
            node=children;
        }
        node.finished=true;
    }

    public static int searchIndex(TrieNode root, String s){
        TrieNode node=root;
        int i;

        for(i=0;i<s.length();i++){
            char ch=s.charAt(i);
            TrieNode children=node.children[ch-'a'];

            if(children==null){
                break;
            }
            node=children;
        }
        if(i!=s.length()){
            return -1;
        }
        return node.startIndex;
    }

    public static int isPrefixOfWord(String sentence, String searchWord) {
        String[] subSentences=sentence.split(" ");
        TrieNode root=new TrieNode();

        for(int i=0;i<subSentences.length;i++){
            insertWord(root, subSentences[i], i+1);
        }

        return searchIndex(root, searchWord);
    }
    public static int isPrefixOfWordMethod2(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for(int i=0; i<words.length; i++) {
            if(words[i].length() >= searchWord.length())
                if(words[i].substring(0, searchWord.length()).equals(searchWord))
                    return i + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        String sentence = "i love eating burger", searchWord = "burg";
        System.out.println(isPrefixOfWord(sentence, searchWord));
        System.out.println(isPrefixOfWordMethod2(sentence, searchWord));
        //
        //Cách 1:
        //1.
        //1.1, Build Trie để thực hiện thao tác prefix search
        //- Build 1 cách bình thường sau đó thực hiện thao tác search trên trên từng word + pass index vào
        //Cách 2:
        //2.
        //2.1, Thực hiện subString(0, length(keyword) + check equal trên từng từ là xong.
        //
        //#Reference
        //1456. Maximum Number of Vowels in a Substring of Given Length
        //2185. Counting Words With a Given Prefix
        //2255. Count Prefixes of a Given String
    }
}
