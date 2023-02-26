package interviews;

public class E273_PrefixAndSuffixSearchTrie_ref {

    class TrieNode {
        TrieNode[] children;
        int weight;
        public TrieNode() {
            children = new TrieNode[27]; // 'a' - 'z' and '{'. 'z' and '{' are neighbours in ASCII table
            weight = 0;
        }
    }

    TrieNode root;

    public E273_PrefixAndSuffixSearchTrie_ref(String[] words){
        root = new TrieNode();
        for (int weight = 0; weight < words.length; weight++) {
            String word = words[weight] + "{";
            for (int i = 0; i < word.length(); i++) {
                TrieNode cur = root;
                cur.weight = weight;
                // add "apple{apple", "pple{apple", "ple{apple", "le{apple", "e{apple", "{apple" into the Trie Tree
                for (int j = i; j < 2 * word.length() - 1; j++) {
                    System.out.print(word.charAt(j % word.length()));
                    int k = word.charAt(j % word.length()) - 'a';
                    if (cur.children[k] == null)
                        cur.children[k] = new TrieNode();
                    cur = cur.children[k];
                    cur.weight = weight;
                }
                System.out.println();
            }
        }
    }

    public int f(String prefix, String suffix) {
        TrieNode cur = root;
        for (char c: (suffix + '{' + prefix).toCharArray()) {
            if (cur.children[c - 'a'] == null) {
                return -1;
            }
            cur = cur.children[c - 'a'];
        }
        return cur.weight;
    }

    public static void main(String[] args) {
        String[] words={"apple"};
        E273_PrefixAndSuffixSearchTrie_ref paf=new E273_PrefixAndSuffixSearchTrie_ref(words);
        System.out.println(paf.f("ap", "le"));
    }

}
