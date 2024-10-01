package E1_daily;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class E112_LongestCommonSuffixQueries {

    public static class TrieNode{
        TrieNode[] child;
        boolean finish;
        int index;
        public TrieNode(){
            child=new TrieNode[26];
        }
    }

    public static void addWord(TrieNode root, String word, int index){
        TrieNode node = root;
        int n=word.length();

        for(int i=n-1;i>=0;i--){
            TrieNode child=node.child[word.charAt(i)-'a'];
            if(child==null){
                child=new TrieNode();
            }
            node.child[word.charAt(i)-'a']=child;
            node=child;
        }
        node.finish=true;
        node.index=index;
    }

    public static Pair<TrieNode, Integer> getLengthCommonString(TrieNode node, String s){
        int rs=0;

        for(int i=s.length()-1;i>=0;i--){
            char c=s.charAt(i);
            TrieNode child = node.child[c-'a'];
            if(child==null){
                break;
            }
            rs++;
            node=child;
        }
        return new Pair<>(node, rs);
    }

//    public static int[] dfs(TrieNode node, int depth, int[] curRs){
////        if(node==null){
////            return Integer.MAX_VALUE;
////        }
//        if(node.finish){
//            return new int[]{node.index, depth};
//        }
//        if(depth>curRs[1]){
//            return new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
//        }
//        int[] rs=new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
//        for(int i=0;i<26;i++){
//            if(node.child[i]!=null){
//                int[] nextRs = dfs(node.child[i], depth+1, rs);
//                if(rs[1]>nextRs[1]||(rs[1]==nextRs[1]&&rs[0]>nextRs[0])){
//                    rs=nextRs;
//                }
//            }
//        }
//        return rs;
//    }

    public static int bfs(TrieNode node){
//        if(node==null){
//            return Integer.MAX_VALUE;
//        }
        Queue<TrieNode> queue=new LinkedList<>();
        queue.add(node);
        int rsIndex=Integer.MAX_VALUE;

        while (!queue.isEmpty()&&rsIndex==Integer.MAX_VALUE){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TrieNode curNode = queue.poll();
                if(curNode.finish&&rsIndex>curNode.index){
                    rsIndex=curNode.index;
                }
                for(int j=0;j<26;j++){
                    if(curNode.child[j]!=null){
                        queue.add(curNode.child[j]);
                    }
                }
            }
        }
        return rsIndex;
    }

    public static int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root=new TrieNode();
        //Chỗ này sẽ nhận smaller index:
        //  + Sẽ có 2 word giống nhau nhưng index i<j:
        //      + Lấy i
        //      ==> Ta addWord từ i:(n-1 --> 0)
//        for(int i=0;i<wordsContainer.length;i++){
//            addWord(root, wordsContainer[i], i);
//        }
        //
        String minLengthStr=null;
        Integer minLengthIndex=null;
        for(int i=wordsContainer.length-1;i>=0;i--){
            addWord(root, wordsContainer[i], i);
            if(minLengthStr==null||minLengthStr.length()>=wordsContainer[i].length()){
                minLengthStr=wordsContainer[i];
                minLengthIndex=i;
            }
        }
        int m=wordsQuery.length;
        int[] rs=new int[m];
        int i=0;
        HashMap<TrieNode, Integer> cache=new HashMap<>();
        for(String query: wordsQuery){
            Pair<TrieNode, Integer> curRs = getLengthCommonString(root, query);
            if(curRs.getValue()!=0){
                if(!cache.containsKey(curRs.getKey())){
                    int indexLen=bfs(curRs.getKey());
                    cache.put(curRs.getKey(), indexLen);
                    rs[i]=indexLen;
                }else{
                    rs[i]=cache.get(curRs.getKey());
                }
            }else{
                rs[i]=minLengthIndex;
            }
            i++;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two arrays of strings (wordsContainer) and (wordsQuery).
        //- For each wordsQuery[i], you need to find a string from wordsContainer that has (the longest common suffix) with wordsQuery[i].
        //  + If there are two or more strings in wordsContainer that share (the longest common suffix), find the string that is (the smallest in length).
        //  + If there are two or more such strings that have (the same smallest length), find the one that (occurred earlier) in wordsContainer.
        //      + Nếu cùng longest common suffix ==> Ưu tiên thăng match có smallest length
        //      + Nếu cùng yếu tố trên ==> Ưu tiên thằng có smaller index.
        //* Return (an array of integers ans), where ans[i] is the index of the string in wordsContainer
        // that has (the longest common suffix) with wordsQuery[i].
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= wordsContainer.length, wordsQuery.length <= 10^4
        //1 <= wordsContainer[i].length <= 5 * 10^3
        //1 <= wordsQuery[i].length <= 5 * 10^3
        //wordsContainer[i] consists only of lowercase English letters.
        //wordsQuery[i] consists only of lowercase English letters.
        //Sum of wordsContainer[i].length is at most 5 * 10^5.
        //Sum of wordsQuery[i].length is at most 5 * 10^5.
        //  + Length của wordsContainer, wordQuery khá lớn ==> Time: O(n*k)
        //  + Sum của all of length of word[i] <= 5*10^5 == Dùng int được.
        //
        //- Brainstorm
        //
        //1.1, Optimization
        //- Nếu không cache lại đoạn BFS ==> trieNode map to index
        //  ==> TLE
        //- Nếu search theo min depth ==> Nên dùng BFS
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n+m*n)
        //
//        String[] wordsContainer = {"abcd","bcd","xbcd"}, wordsQuery = {"cd","bcd","xyz"};
//        String[] wordsContainer = {"abcd","bcda"}, wordsQuery = {"cdf","afa"};
        String[] wordsContainer = {"abcdefgh","poiuygh","ghghgh"}, wordsQuery = {"gh","acbfgh","acbfegh"};
        int[] rs = stringIndices(wordsContainer, wordsQuery);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
        System.out.println();
        //#Reference:
        //800. Similar RGB Color
        //2505. Bitwise OR of All Subsequence Sums
        //2124. Check if All A's Appears Before All B's
    }
}
