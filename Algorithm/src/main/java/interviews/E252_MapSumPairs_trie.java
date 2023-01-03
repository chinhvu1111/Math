package interviews;

import java.util.HashMap;

public class E252_MapSumPairs_trie {

    public static class TrieNode{
        public HashMap<Character, TrieNode> child;
        public int sum;
        public boolean finished;

        public TrieNode() {
            child=new HashMap<>();
        }

        public HashMap<Character, TrieNode> getChild() {
            return child;
        }

        public int getSum() {
            return sum;
        }

        public boolean isFinished() {
            return finished;
        }

    }
    public TrieNode root;

    public HashMap<String, Integer> valueMap;

    public E252_MapSumPairs_trie() {
        root=new TrieNode();
        valueMap=new HashMap<>();
    }

    public void insert(String key, int val) {
        int n=key.length();
        TrieNode node=root;
        int offset=0;

        if(valueMap.containsKey(key)){
           offset=val-valueMap.get(key);
        }else {
            offset=val;
        }
        valueMap.put(key, val);

        for(int i=0;i<n;i++){
            char ch=key.charAt(i);
            TrieNode children=node.child.get(ch);

            if(children!=null){
                children.sum+=offset;
            }else{
                children=new TrieNode();
                children.sum=offset;
            }
            node.getChild().put(ch, children);
            node=children;
        }
    }

    public int sum(String prefix) {
        int n=prefix.length();
        TrieNode node=root;

        for (int i = 0; i < n; i++) {
            char ch=prefix.charAt(i);
            TrieNode children= node.getChild().get(ch);

            if(children!=null){
                node=children;
            }else{
                return 0;
            }
        }
        return node.sum;
    }

    public static void main(String[] args) {
//        E252_MapSumPairs mapSumPairs=new E252_MapSumPairs();
//        mapSumPairs.insert("apple", 3);
//        System.out.println(mapSumPairs.sum("app"));
//        mapSumPairs.insert("app", 2);
//        System.out.println(mapSumPairs.sum("app"));

        //Case 1:
        //- Case liên quan đến cộng tổng vào:
        //VD:
        //apple :
        // (x) --a--> (x) --p--> (x) --p--> (x : 2) --l--> (x) --e--> (x : 3)
        E252_MapSumPairs_trie mapSumPairs=new E252_MapSumPairs_trie();
        mapSumPairs.insert("apple", 3);
        System.out.println(mapSumPairs.sum("app"));
        mapSumPairs.insert("app", 2);
        mapSumPairs.insert("apple", 2);
        System.out.println(mapSumPairs.sum("app"));
        //
        //
        //#Reference
        //2191. Sort the Jumbled Numbers
        //2416. Sum of Prefix Scores of Strings
    }
}
