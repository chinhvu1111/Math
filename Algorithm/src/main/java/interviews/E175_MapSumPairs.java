package interviews;

import java.util.HashMap;
import java.util.Map;

public class E175_MapSumPairs {

    static class MapSum {

        public static class TrieNode{
            public boolean finished;
            public TrieNode root;
            public Map<Character, TrieNode> children;

            public TrieNode() {
                children=new HashMap<>();
            }
        }

        Map<String ,Integer> valueMap;
        TrieNode root;

        public MapSum() {
            root=new TrieNode();
            valueMap=new HashMap<>();
        }

        public void insert(String key, int val) {

        }

//        public int sum(String prefix) {
//
//        }
    }

    public static void main(String[] args) {
        //##Complexity:
        //+ Mỗi lần insert/search/startWith (mất O(k)) : k là độ dài của node
        //##Space
        //O(m*n) : m độ dài là số ký tự khác nhau, m là số lượng nodes
    }
}
