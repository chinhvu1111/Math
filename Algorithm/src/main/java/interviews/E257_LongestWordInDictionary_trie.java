package interviews;

import java.util.*;

public class E257_LongestWordInDictionary_trie {

    public static class TrieNode{
        public HashMap<Character, TrieNode> children;
        public boolean finished;

        public TrieNode() {
            children=new HashMap<>();
        }
    }

    /*
    1,
    1.1, Insert word để làm sao nó chạy đến được 2 lần --> Nó mới được coi là max nhất.
     */
    public static int insertWord(TrieNode root,String word, TreeSet<String> setStr, int length){
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
            if(i==n-2&& !node.finished){
                return length;
            }
        }
        node.finished=true;
        if(length==word.length()){
            setStr.add(word);
        }else{
            setStr.clear();
            setStr.add(word);
        }
        length=word.length();
//        if(!node.finished&&length<=word.length()){
//            if(length==word.length()){
//                setStr.add(word);
//            }else{
//                setStr.clear();
//                setStr.add(word);
//            }
//            length=word.length();
//        }
        return length;
    }

    public static String longestWord(String[] words) {
        TrieNode root=new TrieNode();
        TreeSet<String> rsWords=new TreeSet<>();
        int rsLen=0;
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });

        for (String word : words) {
            rsLen=insertWord(root, word, rsWords, rsLen);
        }
        String rs="";
        Iterator<String> iter = rsWords.iterator();

        if (iter.hasNext()){
            rs=iter.next();
        }
        return rs;
    }
    public static void main(String[] args) {
//        String[] words=new String[]{"w","wo","wor","worl","world"};
        String[] words=new String[]{"a","banana","app","appl","ap","apply","apple"};
        System.out.println(longestWord(words));
        //
        //
        //#Reference
        //721. Accounts Merge
        //524. Longest Word in Dictionary through Deleting
        //676. Implement Magic Dictionary
        //1858. Longest Word With All Prefixes
    }
}
