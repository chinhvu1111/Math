package E1_trie_topic;

import java.util.HashMap;

public class E7_BoldWordsInString {

    public static class TrieNode{
        HashMap<Character, TrieNode> child;
        boolean finish;
        public TrieNode(){
            child=new HashMap<>();
        }
    }

    public static void addWord(String word, TrieNode node){
        int n=word.length();

        for(int i=0;i<n;i++){
            char c=word.charAt(i);
            TrieNode child=node.child.get(c);
            if(child==null){
                child=new TrieNode();
            }
            node.child.put(c, child);
            node=child;
        }
        node.finish=true;
    }

    public static String boldWords(String[] words, String s) {
        TrieNode root=new TrieNode();
        for(String word: words){
            addWord(word, root);
        }
        int n=s.length();
        boolean[] visited=new boolean[n];

        for(int i=0;i<n;i++){
            TrieNode node=root;
            int j=i;
            for(;j<n;j++){
                char c=s.charAt(j);
                TrieNode child=node.child.get(c);
                if(child==null){
                    break;
                }
                if(child.finish){
                    int t=j;
                    while (t>=i&&!visited[t]){
                        visited[t]=true;
                        t--;
                    }
                }
                node=child;
            }
        }
        StringBuilder rs=new StringBuilder();
        for(int i=0;i<n;i++){
            if(visited[i]){
                rs.append("<b>");
                while (i<n&&visited[i]){
                    rs.append(s.charAt(i));
                    i++;
                }
                i--;
                rs.append("</b>");
            }else{
                rs.append(s.charAt(i));
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (an array of keywords words) and a string s, make (all appearances) of (all keywords words[i]) in s (bold).
        //- Any letters between <b> and </b> tags (become bold).
        //* Return s after adding the bold tags.
        //- The returned string should use (the least number of tags) possible,
        // and (the tags) should form (a valid combination).
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 500
        //0 <= words.length <= 50
        //1 <= words[i].length <= 10
        //s and words[i] consist of lowercase English letters.
        //+ Length của string không lớn
        //
        //- Brainstorm
        //- Bài này có thể dùng union find không?
        //Ex:
        //Input: words = ["ab","bc"], s = "aabcd"
        //Output: "a<b>abc</b>d"
        //Explanation: Note that returning "a<b>a<b>b</b>c</b>d" would use more tags, so it is incorrect.
        //- Nếu ab được bold ==> (a) or (b) đứng riêng rẽ chưa chắc được bold
        //- Những string được concat bởi các collections trong word sẽ được bold
        //  + Hoặc là được kết hợp bỏ đi
        //Ex
        //abc, bcd
        //==> abcd
        //- Tức liên quan đến việc (kết hợp các characters) thành (nhiều strings liên tiếp)
        //- Dùng trie để lưu all words
        //- Sau đó loop từ string con start index từ:
        //  + i: (0 -> n-1) để đánh dấu letter của string có thể lấy được
        //- visited[i]=true:
        //  + Sau đó ta sẽ đánh dấu các visited[i]==true liên tiếp là được
        //
        //
        String[] words = {"ab","bc"};
        String s = "aabcd";
        System.out.println(boldWords(words, s));
        //#Reference:
        //1540. Can Convert String in K Moves
        //842. Split Array into Fibonacci Sequence
        //2573. Find the String with LCP
    }
}
