package interviews;

import java.util.HashMap;

public class E259_CountPrefixesOfAGivenString {

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
    }
    public static int countPrefixes(String[] words, String s) {
        TrieNode root=new TrieNode();
//        for(int i=0;i<s.length();i++){
//            insertWord(root, s.substring(i));
//        }
        insertWord(root, s);
        TrieNode node=root;
        int rs=0;

        for(String word:words){
            node=root;
            int i;
            for(i=0;i<word.length();i++){
                char ch=word.charAt(i);
                TrieNode children=node.children.get(ch);

                if(children==null){
                    break;
                }else if(i==word.length()-1){
                    rs++;
                }
                node=children;
            }
        }
        return rs;
    }
    public static void main(String[] args) {
        String[] words=new String[]{"a","b","c","ab","bc","abc"};
        String s="abc";
        System.out.println(countPrefixes(words, s));
        //#Reference
        //2256. Minimum Average Difference
        //1961. Check If String Is a Prefix of Array
    }
}
