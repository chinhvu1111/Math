package E1_slide_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class E4_NumberOfMatchingSubsequences {

    public int numMatchingSubseqMethod1(String s, String[] words) {
        //Space: O(N)
        HashMap<Character, TreeSet<Integer>> valToIndexes=new HashMap<>();
        int n=s.length();

        //Time : O(N)
        for(int i=0;i<n;i++){
            TreeSet<Integer> currentSet=valToIndexes.get(s.charAt(i));
            if(!valToIndexes.containsKey(s.charAt(i))){
                currentSet=new TreeSet();
            }
            //Time : Log(N)
            currentSet.add(i);
            valToIndexes.put(s.charAt(i), currentSet);
        }
        //Space : O(M)
        List<String> result=new ArrayList<>();

        //Time : O(M)
        for(String word: words){
            int l=word.length();
            int prevIndex=-1;
            int i=0;

            //Time : O(K)
            //abbb
            for(i=0;i<l;i++){
                TreeSet<Integer> setIndexes=valToIndexes.get(word.charAt(i));
                if(setIndexes==null){
                    break;
                }
                //Log(N)
                Integer searchIndex=setIndexes.ceiling(prevIndex);
                if(searchIndex==null){
                    break;
                }
                prevIndex=searchIndex+1;
            }
            if(i==l){
                result.add(word);
            }
        }
        System.out.print(result);
        return result.size();
    }

    public static class Node{
        int index;
        String word;
        public Node(int index, String word){
            this.index=index;
            this.word=word;
        }
    }

    public int numMatchingSubseqMethod2(String s, String[] words) {
        int rs=0;
        //Space: O(M)
        List<Node>[] heads=new ArrayList[26];

        for(int i=0;i<26;i++){
            heads[i]=new ArrayList<>();
        }
        //Time : O(M)
        for(String word:words){
            heads[word.charAt(0)-'a'].add(new Node(0, word));
        }
        int n=s.length();

        //Time : O(N)
        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            List<Node> oldBucket=heads[c-'a'];
            heads[c-'a']=new ArrayList<>();

            //Time : O(M)
            for(Node node: oldBucket){
                node.index++;
                if(node.index==node.word.length()){
                    rs++;
                }else{
                    heads[node.word.charAt(node.index)-'a'].add(node);
                }
            }
            oldBucket.clear();
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //* return all word is subsequence of string s
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= s.length <= 5 * 10^4
        //1 <= words.length <= 5000
        //1 <= words[i].length <= 50
        //s and words[i] consist of only lowercase English letters.
        //
        //- Brainstorm
        //Ex:
        //s = "abcbde", words = ["a","bb","acd","ace"]
        //hashMap:
        //a: min(index)= 0, count=1
        //b: min(index)= 1,3, count=2
        //c: min(index)= 2, count=1
        //d: min(index)= 4, count=1
        //e: min(index)= 5, count=1
        //- "bb" : phân biệt index của b như thế nào
        //==> Add list index của b ==> Tăng dần ==> Binary search
        //
        //1.1, Optimization
        //- Chỉ có thể optimize về Algorithm thôi
        //
        //1.2, Complexity:
        //+ N is the length of s string
        //+ M is the number of words
        //+ K is the length of each word.
        //- Space : O(N+M)
        //- Time : O(NLog(N) + M*K*Log(N))
        //
        //2. Tư duy dạng prefix character
        //For example, if we have a string like S = 'dcaog':
        //words = ['dog', 'cat', 'cop']
        //
        //heads = 'c' : ('cat', 'cop'), 'd' : ('dog',) at beginning, xét char='d';
        //heads = 'c' : ('cat', 'cop'), 'o' : ('og',) after S[0] = 'd', xét char='c';
        //heads = 'a' : ('at',), 'o' : ('og', 'op') after S[0] = 'c', xét char='a';
        //heads = 'o' : ('og', 'op'), 't': ('t',) after S[0] = 'a', xét char='o';
        //heads = 'g' : ('g',), 'p': ('p',), 't': ('t',) after S[0] = 'o', xét char='g';
        //heads = 'p': ('p',), 't': ('t',) after S[0] = 'g';
        //- Với each character of s: Updated dần map của character + clear (list of each current character)
        //Ex: abc --> bc --> c <==> index==length(word)
        //- Xét về sau thì dùng lại vẫn đúng.
        //
        //2.1, Optimization
        //
        //2.2, Complexity
        //- N is the length of s
        //- M is the number of words
        //- Space : O(M)
        //- Time: O(M*N+2*M) ==> Nhiều cái mình có thể thu gọn được nữa = O(M+Sum(len(word[i])) : Do mình phải traverse qua hết length của từng word.
        //
        //#Reference:
        //1055. Shortest Way to Form String
        //2062. Count Vowel Substrings of a String
        TreeSet<Integer> set=new TreeSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        System.out.println(set.ceiling(4));
    }
}
