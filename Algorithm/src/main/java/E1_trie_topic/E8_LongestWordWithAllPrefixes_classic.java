package E1_trie_topic;

import java.util.HashMap;
import java.util.Map;

public class E8_LongestWordWithAllPrefixes_classic {

    public static class TrieNode{
        HashMap<Character, TrieNode> child;
        boolean finish;
        public TrieNode(){
            child=new HashMap<>();
        }
    }

    public static void addWord(TrieNode root, String word){
        int n=word.length();
        TrieNode node = root;

        for(int i=0;i<n;i++){
            char c=word.charAt(i);
            TrieNode child = node.child.get(c);
            if(child==null){
                child=new TrieNode();
            }
            node.child.put(c, child);
            node=child;
        }
        node.finish = true;
    }

    public static boolean hasAllPrefixes(String word, TrieNode root){
        TrieNode node = root;
        for(char c: word.toCharArray()){
            node = node.child.get(c);
            if(node==null||!node.finish){
                return false;
            }
        }
        return true;
    }

    public static String rs;

    public static void dfs(TrieNode node, StringBuilder curStr){
        if(!node.finish){
            return;
        }
//        if(node.child.isEmpty()){
//            if(rs==null){
//                rs=curStr.toString();
//            }else if(rs.length()<curStr.length()||(rs.length()==curStr.length()&&rs.compareTo(curStr.toString())>=0)){
//                rs=curStr.toString();
//            }
//            return;
//        }
        //Bản chất không cần gán ntn
        //  + Gán ntn sẽ thêm time.
        //
        if(rs==null){
            rs=curStr.toString();
        }else if(rs.length()<curStr.length()||(rs.length()==curStr.length()&&rs.compareTo(curStr.toString())>=0)){
            rs=curStr.toString();
        }
        for(Map.Entry<Character, TrieNode> nextNode: node.child.entrySet()){
            if(nextNode.getValue().finish){
                curStr.append(nextNode.getKey());
                dfs(nextNode.getValue(), curStr);
                curStr.deleteCharAt(curStr.length()-1);
            }
        }
    }

//    public static boolean dfs(TrieNode node, StringBuilder curStr){
//        if(!node.finish){
//            return node.finish;
//        }
////        if(node.child.isEmpty()){
////            if(rs==null){
////                rs=curStr.toString();
////            }else if(rs.length()<curStr.length()||(rs.length()==curStr.length()&&rs.compareTo(curStr.toString())>=0)){
////                rs=curStr.toString();
////            }
////            return;
////        }
//        //Bản chất không cần gán ntn
//        //  + Gán ntn sẽ thêm time.
//        //
////        if(rs==null){
////            rs=curStr.toString();
////        }else if(rs.length()<curStr.length()||(rs.length()==curStr.length()&&rs.compareTo(curStr.toString())>=0)){
////            rs=curStr.toString();
////        }
//        for(Map.Entry<Character, TrieNode> nextNode: node.child.entrySet()){
//            if(nextNode.getValue().finish){
//                curStr.append(nextNode.getKey());
//                boolean nextRs = dfs(nextNode.getValue(), curStr);
//                curStr.deleteCharAt(curStr.length()-1);
//                if(!nextRs){
//                    if(rs==null){
//                        rs=curStr.toString();
//                    }else if(rs.length()<curStr.length()||(rs.length()==curStr.length()&&rs.compareTo(curStr.toString())>=0)){
//                        rs=curStr.toString();
//                    }
//                }
//            }
//        }
//        if(rs==null){
//            rs=curStr.toString();
//        }else if(rs.length()<curStr.length()||(rs.length()==curStr.length()&&rs.compareTo(curStr.toString())>=0)){
//            rs=curStr.toString();
//        }
//        return true;
//    }

    public static String longestWord(String[] words) {
        TrieNode root=new TrieNode();
        // root -a-> node1 -b-> node2 -c-> node3

        for(String word: words){
            addWord(root, word);
        }
        rs=null;
        root.finish=true;
        dfs(root, new StringBuilder());
        return rs==null?"":rs;
    }

    public static String longestWordOptimization(String[] words) {
        TrieNode root=new TrieNode();
        // root -a-> node1 -b-> node2 -c-> node3

        for(String word: words){
            addWord(root, word);
        }
        rs=null;
        root.finish=true;
        for(String word: words){
            if(rs!=null&&(rs.length()>word.length()||(rs.length()==word.length()&&rs.compareTo(word)<0))){
                continue;
            }
            if(hasAllPrefixes(word, root)){
                rs=word;
            }
        }
        return rs==null?"":rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (an array of strings words), find (the longest string) in words such that (every prefix) of it is also in words.
        //- For example, let words = ["a", "app", "ap"].
        //  + The string "app" has prefixes "ap" and "a", all of which are in words.
        //* Return the string described above.
        //  + If there is more than one string with the same length,
        //  + return (the lexicographically smallest) one,
        // and if no string exists, return "".
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= words.length <= 10^5
        //1 <= words[i].length <= 10^5
        //1 <= sum(words[i].length) <= 10^5
        //  + The length of words khá lớn: Time: O(n)
        //  + words[i] có length khá lớn
        //
        //- Brainstorm
        //- Longest string chính là 1 trong số word có trong words:
        //  + Và prefix của nó phải có
        //  ==> Đơn giản là tìm word mà toàn bỗ characters của word nó có finished = true
        //
        //
//        String[] words = {"k","ki","kir","kira", "kiran"};
//        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
//        String[] words = {"abc", "bc", "ab", "qwe"};
//        String[] words = {"c","b","x","w","s","j","t","e","z","l","k","za","f","d","i","p","o","h","q","y","n","g","v","m","a","r","u"};
        String[] words = {
                "w","g","s","t","o","e","h","k","r","n","abcdefghijklmnopqrstuvwxyz","u","d","i","c","z","x","j",
                "b","v","a","p","l","m","q","f","y"};
        System.out.println(longestWord(words));
        System.out.println(longestWordOptimization(words));
        //
        //1.1, Optimization
        //- Define a function "hasAllPrefixes":
        //  + Check if (all prefixes) of (the word exist) in the trie
        //  ==> Làm ntn sẽ common hơn.
        //- Chuyển từ hashmap --> array to decrease the runtime:
        //  + 48 -> 25 ms
        //
        //1.2, Complexity
        //- n is the size of the words array
        //- m is the average length of word in the array
        //- Space: O(n+m)
        //- Time: O(n*m)
        //
        //#Reference:
        //652. Find Duplicate Subtrees
        //3093. Longest Common Suffix Queries
        //133. Clone Graph
        //2416. Sum of Prefix Scores of Strings
        //968. Binary Tree Cameras
        //1766. Tree of Coprimes
    }
}
