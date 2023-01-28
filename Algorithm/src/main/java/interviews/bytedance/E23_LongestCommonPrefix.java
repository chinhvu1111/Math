package interviews.bytedance;

import java.util.HashMap;

public class E23_LongestCommonPrefix {

    public static class TrieNode{
        public HashMap<Character, TrieNode> children;

        public TrieNode() {
            children=new HashMap<>();
        }
    }

    public static String insertWord(TrieNode root, String s, boolean first){
        int n=s.length();
        TrieNode node=root;
        StringBuilder commonStr=new StringBuilder();

        for(int i=0;i<n;i++){
            Character ch=s.charAt(i);
            TrieNode children=node.children.get(ch);

            if(first&&children==null){
                children=new TrieNode();
                node.children.put(ch, children);
            }else if(children==null){
                break;
            }
            node=children;
            commonStr.append(s.charAt(i));
        }
        node.children.clear();
        return commonStr.toString();
    }

    public static String longestCommonPrefix(String[] strs) {
        int n=strs.length;
        if(n==0){
            return "";
        }
        TrieNode root=new TrieNode();
        String rs="";
        rs=insertWord(root, strs[0], true);

        for(int i=1;i<n;i++){
            rs=insertWord(root, strs[i], false);
        }
        return rs;
    }

    public static void main(String[] args) {
//        String[] strs = new String[]{"flower","flow","flight"};
//        String[] strs = new String[]{"aaa","aa","aaa"};
        String[] strs = new String[]{"c","acc","ccc"};
        System.out.println(longestCommonPrefix(strs));
        //#Reference:
        //15. 3Sum
        //588. Design In-Memory File System
        //2063. Vowels of All Substrings
        //880. Decoded String at Index
    }
}
