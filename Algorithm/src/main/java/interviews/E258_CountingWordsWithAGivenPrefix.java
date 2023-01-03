package interviews;

import java.util.HashMap;
import java.util.TreeSet;

public class E258_CountingWordsWithAGivenPrefix {
    public static class TrieNode{
        public HashMap<Character, TrieNode> children;
        public boolean finished;

        public TrieNode() {
            children=new HashMap<>();
        }
    }

    public static void insertWord(TrieNode root, String word){
        TrieNode node=root;
        int n=word.length();

        for(int i=0;i<n;i++){
            char ch=word.charAt(i);
            TrieNode children=node.children.get(ch);

            if(children==null){
                children=new TrieNode();
                node.children.put(ch, children);
            }
            node=children;
        }
        node.finished=true;
    }

    public static int prefixCount(String[] words, String pref) {
        int rs=0;
        for(String s: words){
            if(pref.length()>s.length()){
                continue;
            }
            int i=0;

            while (i<pref.length()&&pref.charAt(i)==s.charAt(i)){
                i++;
            }
            if(i==pref.length()){
                rs++;
            }
        }
        return rs;
    }
    public static int getNumberSearchingWord(TrieNode root, String word){
        TrieNode node=root;
        int n=word.length();
        int currentRs=0;

        for(int i=0;i<n;i++){
            char ch=word.charAt(i);
            TrieNode childrend=node.children.get(ch);

            if(childrend==null){
                return currentRs;
            }
        }

        return currentRs;
    }

    public static void main(String[] args) {
        String[] s=new String[]{"pay","attention","practice","attend"};
        String pref = "at";
        System.out.println(prefixCount(s, pref));
        //#Reference
        //2186. Minimum Number of Steps to Make Two Strings Anagram II
        //2255. Count Prefixes of a Given String
    }
}
