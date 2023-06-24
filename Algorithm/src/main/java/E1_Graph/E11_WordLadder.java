package E1_Graph;

import java.util.*;

public class E11_WordLadder {

    public static class Pair{
        String key;
        Integer value;
        public Pair(String key, Integer value){
            this.key=key;
            this.value=value;
        }

        public String getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }
    }

    public static int solution(String beginWord, String endWord, List<String> wordList){
        if(beginWord.equals(endWord)){
            return 1;
        }
        int n=wordList.size();
        List<int[]>[] indexAdjNodes=new ArrayList[n];
        int endIndexWord=-1;

        for(int i=0;i<n;i++){
            if(wordList.get(i).equals(endWord)){
                endIndexWord=i;
            }
            List<int[]> adjNodes=indexAdjNodes[i];
            if(adjNodes==null){
                adjNodes=new ArrayList<>();
            }
            for(int j=0;j<n;j++){
                if(getDiffBwnTwoStrings(wordList.get(i), wordList.get(j))==1){
                    adjNodes.add(new int[]{j, 1});
//                    System.out.printf("%s %s\n", i, j);
                }
            }
//            System.out.printf("%s %s\n", i, adjNodes);
            indexAdjNodes[i]=adjNodes;
        }
        if(endIndexWord==-1){
            return 0;
        }

        Queue<int[]> nodeIndexes=new LinkedList<>();
        boolean[] visited=new boolean[n];

        for(int i=0;i<wordList.size();i++){
            if(getDiffBwnTwoStrings(beginWord, wordList.get(i))==1){
                if(i==endIndexWord){
                    return 2;
                }
                nodeIndexes.add(new int[]{i, 2});
                visited[i]=true;
            }
        }

        while (!nodeIndexes.isEmpty()){
            int[] currentWord=nodeIndexes.poll();
            int currentIndex=currentWord[0];
            List<int[]> adjNodes=indexAdjNodes[currentIndex];

            if(adjNodes!=null){
                int nextValue=currentWord[1]+1;
                for(int[] node: adjNodes){
                    if(visited[node[0]]){
                       continue;
                    }
                    if(node[0]==endIndexWord){
                        return nextValue;
                    }
                    node[1]=nextValue;
//                    System.out.printf("%s %s\n", currentIndex, node[0]);
                    nodeIndexes.add(new int[]{node[0], nextValue});
                    visited[node[0]]=true;
                }
            }
        }
        return 0;
    }

    public static int ladderLengthMethod1(String beginWord, String endWord, List<String> wordList) {
        return solution(beginWord, endWord, wordList);
    }

    public static int solutionMethod2(String beginWord, String endWord, List<String> wordList){
        if(beginWord.equals(endWord)){
            return 1;
        }
        int length=beginWord.length();
        HashMap<String, List<String>> patternMapWords=new HashMap<>();

        for(String word:wordList){
            for(int i=0;i<length;i++){
                String currentPattern=word.substring(0, i)+"*"+word.substring(i+1);
                List<String> currentAdjWords=patternMapWords.get(currentPattern);

                if(currentAdjWords==null){
                    currentAdjWords=new ArrayList<>();
                }
                currentAdjWords.add(word);
                patternMapWords.put(currentPattern, currentAdjWords);
            }
        }
        Queue<Pair> queueNodes=new LinkedList<>();
        queueNodes.add(new Pair(beginWord, 1));
        HashSet<String> visited=new HashSet<>();
        visited.add(beginWord);

        while (!queueNodes.isEmpty()){
            Pair currentNode=queueNodes.poll();
            for(int i=0;i<length;i++){
                String currentPattern=currentNode.getKey().substring(0, i)+"*"+currentNode.getKey().substring(i+1);
                List<String> nextNodes=patternMapWords.get(currentPattern);

                if(nextNodes!=null){
                    for(String nextNode:nextNodes){
                        if(visited.contains(nextNode)){
                            continue;
                        }
                        if(nextNode.equals(endWord)){
                            return currentNode.getValue()+1;
                        }
                        queueNodes.add(new Pair(nextNode, currentNode.getValue()+1));
                        visited.add(nextNode);
                    }
                }
            }
        }
        return 0;
    }

    public static int ladderLengthMethod2(String beginWord, String endWord, List<String> wordList) {
        return solutionMethod2(beginWord, endWord, wordList);
    }

    public static int getDiffBwnTwoStrings(String s, String s1){
        int numDiff=0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=s1.charAt(i)){
                numDiff++;
            }
        }
        return numDiff;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Bài này return số lượng min transformation nếu đi từ (startWord --> endWord).
        //- Mỗi transformation khác nhau 1 character.
        //- startWord không cần phải trong wordlist ==> Các intermediate còn lại phải trong word list.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint:
        //+ 1 <= beginWord.length <= 10
        //+ endWord.length == beginWord.length
        //+ 1 <= wordList.length <= 5000
        //
        //- Dùng BFS
        //+ 1 node có thể đi được các node xung quanh khi (diff character =1)
        //1.1, Implementation
        //- Step 1 ta sẽ add các node có diff character với node hiện tại =1
        //- Ta sẽ cần lưu danh sách adjacent nodes của từng nodes một.
        //
        //1.2, Complexity
        //- Time complexity:
        //+ O(N^2*M) : Do cần tìm diff giữa 2 words bất kỳ
        //
        //- Space complexity:
        //+ O(N^2*M) : Vì lưu thông tin danh sách các adj nodes của từng node.
        //
        //Method 2:
        //2.
        //2.0, Tối ưu dựa trên length của word
        //- Tư duy dạng này sẽ không phải tìm all cặp với (diff character =1) [ các ký tự khác nhau = 1]
        //+VD:
        //abc --> : *bc, a*c, ab*
        //dbc --> : *bc, d*c, db*
        //- Ta sẽ tạo ra hashmap('word_star', list_nodes)
        //+ Tức là danh sách các nodes có thể đi đến
        //VD: abc --> for( 0--> len(abc)) ==> Lấy ra all cách để replace
        //+ Khi tìm được all cases có thể replace được rồi --> Ta sẽ for để tìm nodes có thể đến tiếp theo.
        //
        //2.1, Complexity
        //- Time complexity : O(M^2*N)
        //+ M là chiều dài của word
        //+ N là số words có trong words list
        //+ For word list O(N) + [ for (0 --> length) + substring() ] (O(M^2) ==> O(M^2*N)
        //
        //- Space complexity : O(M^2*N)
        //- Mỗi word có M khả năng --> M word
        //+ Cận trên là N words thì có (M*N) khả năng
        //--> SUY LUẬN KIỂU NÀY KHÁ KHÓ.
        //* Mỗi word có M intermediate --> ta cần lưu M intermediate của nó + M word đó được duplicate lên
        //--> M (số lượng intermediate) * M (Chiều dài word) = M*M (mỗi word)
        //+ Ta có N words --> space = M*M*N = O(M^2*N)
        //
        //Method 3: Bidirectional Breadth First Search
        //- Fastest.
        String beginWord = "hit", endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
//        String beginWord = "hit", endWord = "cog";
//        String[] wordList = {"hot","dot","dog","lot","log"};
        //
//        String beginWord = "hit", endWord = "git";
//        String[] wordList = {"hit","git","dog","lot","log"};
//        String beginWord = "git", endWord = "git";
//        String[] wordList = {"hit","git","dog","lot","log"};
        List<String> list = new ArrayList<>(Arrays.asList(wordList));
        System.out.println(ladderLengthMethod1(beginWord, endWord, list));
        System.out.println(ladderLengthMethod2(beginWord, endWord, list));
        //#Reference:
        //1. Two Sum
        //126. Word Ladder II
        //2452. Words Within Two Edits of Dictionary
    }
}
