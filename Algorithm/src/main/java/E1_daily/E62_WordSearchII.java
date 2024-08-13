package E1_daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class E62_WordSearchII {

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static class TrieNode {
        public HashMap<Character, TrieNode> child;
        boolean finished=false;

        public TrieNode() {
            child = new HashMap<>();
        }
    }

    public static void dfs(TrieNode node, char[][] board, int x, int y, int n, int m, boolean[][] visited) {
        for (int i = 0; i < dx.length; i++) {
            int x1 = x + dx[i];
            int y1 = y + dy[i];

            if (x1 >= 0 && y1 >= 0 && x1 < n && y1 < m && !visited[x1][y1]) {
                char curLetter = board[x1][y1];
                TrieNode child = node.child.get(curLetter);
                if (child == null) {
                    child = new TrieNode();
                }
                node.child.put(curLetter, child);
                visited[x1][y1] = true;
                dfs(child, board, x1, y1, n, m, visited);
                visited[x1][y1] = false;
            }
        }
    }

    public static boolean checkMatch(String word, TrieNode root) {
        TrieNode node = root;
        int i = 0;

        while (node != null && i < word.length() && node.child.containsKey(word.charAt(i))) {
            TrieNode child = node.child.get(word.charAt(i));
            i++;
            node = child;
        }
        return i == word.length();
    }

    public static List<String> findWordsTLE(char[][] board, String[] words) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        TrieNode root = new TrieNode();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                TrieNode node = root.child.get(board[i][j]);
                if (node == null) {
                    node = new TrieNode();
                }
                root.child.put(board[i][j], node);
                visited[i][j] = true;
                dfs(node, board, i, j, n, m, visited);
                visited[i][j] = false;
            }
        }
        List<String> rs = new ArrayList<>();

        for (String word : words) {
            if (checkMatch(word, root)) {
                rs.add(word);
            }
        }
        return rs;
    }

    public static void addWord(TrieNode node, String word){
        for (int i = 0; i < word.length(); i++) {
            TrieNode child=node.child.get(word.charAt(i));
            if(child==null){
                child=new TrieNode();
            }
            node.child.put(word.charAt(i), child);
            node=child;
            if(i==word.length()-1){
                node.finished=true;
            }
        }
    }

    public static HashSet<String> rs;

    public static void dfsCheck(
            TrieNode node, char[][] board, int x, int y,
            int n, int m, boolean[][] visited, StringBuilder currentWord){
        if(node.finished){
            rs.add(currentWord.toString());
        }
        for (int i = 0; i < dx.length; i++) {
            int x1 = x + dx[i];
            int y1 = y + dy[i];

            if (x1 >= 0 && y1 >= 0 && x1 < n && y1 < m && !visited[x1][y1]&&node.child.containsKey(board[x1][y1])) {
                char curLetter = board[x1][y1];
                TrieNode child = node.child.get(curLetter);
                if (child == null) {
                    child = new TrieNode();
                }
                currentWord.append(board[x1][y1]);
                visited[x1][y1] = true;
                dfsCheck(child, board, x1, y1, n, m, visited, currentWord);
                visited[x1][y1] = false;
                currentWord.deleteCharAt(currentWord.length()-1);
            }
        }
    }

    public static List<String> findWords(char[][] board, String[] words) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[n][m];
        TrieNode root = new TrieNode();
        rs=new HashSet<>();

        for (String word : words) {
            addWord(root, word);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!root.child.containsKey(board[i][j])){
                    continue;
                }
                visited[i][j] = true;
                TrieNode node=root.child.get(board[i][j]);
                if(node.finished){
                    rs.add(board[i][j]+"");
                }
                dfsCheck(node, board, i, j, n, m, visited, new StringBuilder(board[i][j]+""));
                visited[i][j] = false;
            }
        }
        List<String> res = new ArrayList<>(rs);
        return res;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an m x n board of characters and (a list of strings words),
        //* Return (all words) on the board.
        //- (Each word) must be constructed from (letters of sequentially adjacent cells),
        // where (adjacent cells) are horizontally or vertically neighboring.
        //- The same letter cell may (not be used more than once) in a word.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //m == board.length
        //n == board[i].length
        //1 <= m, n <= 12
        //board[i][j] is a lowercase English letter.
        //1 <= words.length <= 3 * 10^4
        //1 <= words[i].length <= 10
        //words[i] consists of lowercase English letters.
        //All the strings of words are unique.
        //+ Size của matrix cũng không quá lớn
        //+ Size của word cũng thế
        //+ Char là các lower case characters
        //+ 1 word không thể đi qua 1 cell nhiều lần
        //+ Nhưng N>1 thì có thể đi qua cùng 1 cell
        //+ words của length >= 10^4:
        //  ==> Time: O(n*k)
        //
        //- Brainstorm
        //- Với dạng bài words có length lớn thì:
        //  + Thường ta sẽ build tree (dựa trên words/ dựa trên matrix)
        //      + Tuỳ thuộc vào (size của matrix or size của words)
        //- Size của words khá lớn:
        //  ==> Những n*len(words[i]) = 3*10^5 vẫn chưa lớn lắm
        //  + Có thể loop theo words:
        //      + Check từng word
        //  + Hoặc build trie từ words:
        //      + Check vào matrix
        //
        //- Vì size matrix bé ==> Ta có thể build trie từ matrix + loop(words) để check là được.
        //- Build trie từ matrix:
        //  + Từ (i,j) có thể đi qua 4 directions:
        //      + Vấn đề ở đây là nó có thể đến:
        //          + (same character)
        //          + different direction
        //      ==> Ảnh hướng đến Map không --> Trỏ đến child không?
        //      + Không ảnh hưởng do ta có thể coi các giá trị đó giống nhau:
        //          + a -> b(1,1)
        //          + a -> b(1,0) ==> Coi như là a,b->... ==> Ta coi là như nhau và chỉ khác các suffix đằng sau thôi.
        //- Ta có thể dùng DFS:
        //  + Traverse 1 lần nhưng mà có thể gán liên tục:
        //      + Root trỏ đến các intermediate node trên DFS path là được?
        //          * Đoạn này không làm được do:
        //              + Việc gán vào root khá khó nếu node và root ==> Có chung 1 path dài + nhiều layers (Nhiều cases)
        //      ==> Đoạn này ta cứ for all (O(n^2) là được)
        //
        //- Nó nhanh hơn bởi vì:
        //  + Khi traverse DFS --> Chỉ chọn 1 số node[i][j] bắt đầu để traverse
        //  + Khi đi 4 directions --> Chỉ chọn những node trong trie để traverse
        //- Chú ý:
        //  + StringBuidlder(char) ==> SAI (Cần cộng thêm empty string)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space:
        //- Time:
        //
//        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
//        String[] words = {"oath","pea","eat","rain"};
//        char[][] board = {{'a'}};
//        String[] words = {"b"};
//        char[][] board = {{'a','a'}};
//        String[] words = {"aaa"};
//        char[][] board = {
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'},
//                {'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a'}};
//        String[] words = {"a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa" };
        char[][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain","hklf", "hf"};
//        System.out.println(findWordsTLE(board, words));
        System.out.println(findWords(board, words));
        //
        //#Reference:
        //980. Unique Paths III
        //2227. Encrypt and Decrypt Strings
    }
}
