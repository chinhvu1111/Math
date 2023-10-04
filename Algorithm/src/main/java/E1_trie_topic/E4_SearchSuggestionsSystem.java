package E1_trie_topic;

import java.util.*;

public class E4_SearchSuggestionsSystem {

    public static class TrieNode{
        public TrieNode[] childNodes;
        public TreeSet<String> words;
        public TrieNode(){
            childNodes=new TrieNode[26];
            words=new TreeSet<>();
        }
    }

    public static void insertWord(String word, TrieNode root){
        int n=word.length();
        TrieNode curNode=root;

        //Time : O(m)
        for(int i=0;i<n;i++){
            char c=word.charAt(i);

            if(curNode.childNodes[c-'a']!=null){
                curNode=curNode.childNodes[c-'a'];
            }else{
//                System.out.printf("Add: %s\n", c);
                TrieNode tmp=new TrieNode();
                curNode.childNodes[c-'a']=tmp;
                curNode=tmp;
            }
            if(curNode.words.size()==3&&curNode.words.last().compareTo(word)>0){
                curNode.words.pollFirst();
            }
            //Time : O(log(n))
            curNode.words.add(word);
//            System.out.printf("Word: %s, char: %s, %s\n", word, c, curNode.words);
        }
    }

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        //Space : O(n(product)*m(chars) * n(words/ each trie))
        TrieNode root=new TrieNode();

        //Time : O(n)
        //Space : O(n*m)
        //Empty -> n -> m -> h
        //Empty -> n -> o -> e
        for (String product : products) {
            //Time : O(m*log(n))
            insertWord(product, root);
        }
        int m=searchWord.length();
//        System.out.printf("%s\n", root.childNodes['m'-'a'].words);
        //Space : O(n*k)
        List<List<String>> rs=new ArrayList<>();

        //Time : O(m)
        for(int j=0;j<m;j++){
            int c= searchWord.charAt(j)-'a';
//            System.out.printf("char: %s, list: %s\n", searchWord.charAt(j), root.childNodes[c].words);
            List<String> currentWords;

            if(root.childNodes[c]!=null){
                //                System.out.println(currentWords);
                //Time : O(n)
                currentWords = new ArrayList<>(root.childNodes[c].words);
//                tmp.addAll(currentWords);
                root=root.childNodes[c];
            }else{
                //Chỗ này nếu sai 1 case thì phần sau add hoàn toàn empty luôn
                while(j<m){
                    rs.add(new ArrayList<>());
                    j++;
                }
                break;
            }
            rs.add(currentWords);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given array products and searchWord
        //- List all top 3 product have the same prefix with searchWord after typing one time
        //- If we get more than 3 words, we will return top 3 minimum words based on the alphabet order
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= products.length <= 1000
        //1 <= products[i].length <= 3000
        //1 <= sum(products[i].length) <= 2 * 10^4
        //All the strings of products are unique.
        //products[i] consists of lowercase English letters.
        //1 <= searchWord.length <= 1000
        //searchWord consists of lowercase English letters.
        //- len(searchWord) <=1000 không quá lớn
        //- For each character we will return all the words that have the same prefix character from (current index to index=0) (i --> 0)
        //
        //- Brainstorm
        //Ex:
        //Input: products = ["mobile","mouse","moneypot","monitor","mousepad"],
        // searchWord = "mouse"
        //Output:
        //+ [["mobile","moneypot","monitor"],
        // ["mobile","moneypot","monitor"],
        // ["mouse","mousepad"],
        // ["mouse","mousepad"],
        // ["mouse","mousepad"]]
        //
        //Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
        //- Bài này dùng trie
        //- Insert từng word vào trong trie
        //- Ta cần implement sao cho có thể search theo prefix
        //Ex:
        //Insert mobile
        //m -> o -> b -> i -> l -> e
        //Insert moneypot
        //empty -> m -> o -> n -> e -> y -> p -> o -> t
        //+ TrieNode[26] adjNodes;
        //+ Cần var list các words đã trùng cho đến độ sâu hiện tại.
        //
        //- Words sẽ lưu ở đâu
        //empty -> m -> o -> n -> e -> y -> p -> o -> t
        //empty -> m -> b -> n -> e -> y -> p -> o -> t
        //empty -> n -> o -> n -> e -> y -> p -> o -> t
        //Ex: mo sẽ lưu ở đâu ==> Nó sẽ lưu ở m chứ không phải ở empty
        //
        //1.1, Optimization
        //
        //
        //1.2, Complexity
        //- Space : //Space : O(n(product)*m(chars) * n(words/ each trie) + n*m + n*k)
        //- Time: O(m*n*log(n))
        //
        //#Reference:
        //2194. Cells in a Range on an Excel Sheet
        //2049. Count Nodes With the Highest Score
        //249. Group Shifted Strings
//        String[] products = {"mobile","mouse","moneypot","monitor","mousepad"};
//        String searchWord = "mouse";
//        String[] products = {"havana"};
//        String searchWord = "havana";
        String[] products = {"havana"};
        String searchWord = "tatiana";
        System.out.println(suggestedProducts(products, searchWord));
    }
}
