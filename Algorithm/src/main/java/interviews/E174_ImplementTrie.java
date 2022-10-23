package interviews;

import java.util.Map;

public class E174_ImplementTrie {

    public static class TrieNode{
        public boolean finished;
        public Map<Character, TrieNode> children;
        public TrieNode root;

        public TrieNode() {
            root=new TrieNode();
        }

        public void insert(String word) {
            TrieNode node=root;

            for(int i=0;i<word.length();i++){
                char ch=word.charAt(i);

                TrieNode child=node.children.get(ch);

                if(child==null){
                    child=new TrieNode();

                }else{

                }
            }
        }

//        public boolean search(String word) {
//
//        }
//
//        public boolean startsWith(String prefix) {
//
//        }
    }

    public static void main(String[] args) {

    }
}
