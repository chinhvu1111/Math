package E1_daily;

import java.util.HashMap;

public class E106_DesignAddAndSearchWordsDataStructure {
    
    public static class TrieNode{
        HashMap<Character, TrieNode> child;
        boolean finish=false;
        public TrieNode(){
            child=new HashMap<>();
        }
    }
    
    public static boolean addWord(String word, TrieNode node){
        int n=word.length();
        boolean isExists=true;

        //Time: O(n)
        for(int i=0;i<n;i++){
            TrieNode children=node.child.get(word.charAt(i));

            if(children==null){
                children=new TrieNode();
                isExists=false;
            }
//            System.out.println(isExists);
            if(word.charAt(i)!='.'){
                node.child.put(word.charAt(i), children);
            }else{
                //Time: O(26)
                for(int j='a';j<='z';j++){
                    node.child.put((char)j, children);
                }
            }
            node=children;
        }
        node.finish=true;
        return isExists;
    }

    public static boolean searchWord(int index, StringBuilder word, TrieNode node){
        int n=word.length();
        if(index==n){
            return node!=null&&node.finish;
        }
        if(node==null){
            return false;
        }
        boolean curRs;
        if(word.charAt(index)=='.'){
            for(int j='a';j<='z';j++){
                TrieNode children=node.child.get((char)j);
                curRs=searchWord(index+1, word, children);
                if(curRs){
                    return true;
                }
            }
        }else{
            TrieNode children=node.child.get(word.charAt(index));
            curRs=searchWord(index+1, word, children);
            return curRs;
        }
        //node_1 -t-> node_2 -r-> node_3
        //node -a-> node1 -b-> node2
        return false;
    }

    public TrieNode root;

    public E106_DesignAddAndSearchWordsDataStructure() {
        root=new TrieNode();
    }

    //Time: O(m)
    public void addWord(String word) {
        addWord(word, root);
    }

    //Time: O(n*26^m)
    public boolean search(String word) {
        return searchWord(0, new StringBuilder(word), root);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Design a data structure that supports adding new words and finding if a string matches any previously added string.
        //Implement the WordDictionary class:
        //  + WordDictionary() Initializes the object.
        //  + void addWord(word) Adds word to the data structure, it can be matched later.
        //  + bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
        //* word may contain dots '.' where dots can be matched with any letter.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= word.length <= 25
        //word in addWord consists of lowercase English letters.
        //word in search consist of '.' or lowercase English letters.
        //There will be at most 2 dots in word for search queries.
        //At most 10^4 calls will be made to addWord and search.
        //  + word có 25 characters:
        //      + a.ab. : 2 dots thì sẽ điền 26*26 cases ==> Nên không thể điền all word được.
        //  + 10^4 calls:
        //      + Số lần call lớn: Time: O(n*k)
        //
        //- Brainstorm
        //Example:
        //
        //Input
        //["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
        //[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
        //Output
        //[null,null,null,null,false,true,true,true]
        //
        //Explanation
        //WordDictionary wordDictionary = new WordDictionary();
        //wordDictionary.addWord("bad");
        //wordDictionary.addWord("dad");
        //wordDictionary.addWord("mad");
        //wordDictionary.search("pad"); // return False
        //wordDictionary.search("bad"); // return True
        //wordDictionary.search(".ad"); // return True
        //wordDictionary.search("b.."); // return True
        //- Root -a-> child1 -b-> child2
        //                  \ . /
        //  + ./b phải cùng được trỏ đến child2
        //- Giả sử node.get(.)
        //  + node.put('a', trie)
        //  + node.put('b', trie)
        //  + ...
        //  + node.put('z', trie)
        //- Sẽ có case:
        //  + add words = [azbc,aebd]
        //  + a.bc
        //  + Nếu đi tìm theo aebd ==> Không tìm được (azbc)
        //      + Nếu tìm cái này thì chỉ có thể dùng DFS mà thôi
        //
        //1.1, Optimization
        //1.2, Complexity
        //- m is the average length of word
        //- n is the number of calls
        //- Space: O(m)
        //- Time: O(n*26^m)
        //
        E106_DesignAddAndSearchWordsDataStructure wordDictionary = new E106_DesignAddAndSearchWordsDataStructure();
//        wordDictionary.addWord("bad");
//        wordDictionary.addWord("dad");
//        wordDictionary.addWord("mad");
//        System.out.println(wordDictionary.search("pad")); // return False
//        System.out.println(wordDictionary.search("bad")); // return True
//        System.out.println(wordDictionary.search(".ad")); // return True
//        System.out.println(wordDictionary.search("b..")); // return True
        wordDictionary.addWord("search");
        System.out.println(wordDictionary.search("a"));
        //#Reference:
        //2301. Match Substring After Replacement
        //2416. Sum of Prefix Scores of Strings
        //3045. Count Prefix and Suffix Pairs II
    }
}
