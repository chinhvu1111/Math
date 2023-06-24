package E1_Graph;

import java.util.*;

public class E13_WordLadderII {

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return new ArrayList<>();
        }
        HashMap<String, List<String>> patternToNodes=new HashMap<>();

        for(String word:wordList){
            int m=word.length();
            for(int i=0;i<m;i++){
                String currentPattern=word.substring(0,i) + "*" + word.substring(i+1);
                List<String> listString=patternToNodes.get(currentPattern);
                if(listString==null){
                    listString=new ArrayList<>();
                }
                listString.add(word);
                patternToNodes.put(currentPattern, listString);
//                System.out.printf("%s %s\n", currentPattern, listString);
            }
        }
        Queue<String> nextNodes=new LinkedList<>();
        nextNodes.add(beginWord);
        HashSet<String> visited=new HashSet<>();
        int level=1;
        HashMap<String, List<String>> wordToPreviousNode=new HashMap<>();

        while (!nextNodes.isEmpty()){
            boolean isMatch=false;
            List<String> nextWords=new ArrayList<>();
            HashSet<String> traverse=new HashSet<>();

            while (!nextNodes.isEmpty()){
                String currentWord=nextNodes.poll();
                int m=currentWord.length();

                for (int i = 0; i < m; i++) {
                    String currentPattern=currentWord.substring(0,i) + "*" + currentWord.substring(i+1);
//                    if(visited.contains(currentPattern)){
//                        continue;
//                    }
//                    visited.add(currentPattern);
                    List<String> mappingWords=patternToNodes.get(currentPattern);
//                    System.out.printf("%s %s %s\n", currentWord, currentPattern, mappingWords);

                    if(mappingWords==null){
                        continue;
                    }
                    for(String s:mappingWords){
                        if(visited.contains(s)){
                            continue;
                        }
                        if(s.equals(endWord)){
                            isMatch=true;
//                            System.out.printf("Match: %s %s %s %s\n", currentWord, currentPattern, s, level);
                        }
                        traverse.add(s);
                        nextWords.add(s);
                        List<String> prevNodes=wordToPreviousNode.get(s);
                        if(prevNodes==null) prevNodes=new ArrayList<>();
                        prevNodes.add(currentWord);
                        wordToPreviousNode.put(s, prevNodes);
                    }
                }
            }
            visited.addAll(traverse);
//            System.out.printf("Visited: %s \n",visited);
            if(isMatch){
                break;
            }
            nextNodes.addAll(nextWords);
            level++;
        }
//        System.out.println(level);
        List<List<String>> paths=new ArrayList<>();
//        List<String> prevNodeOfEndNode=wordToPreviousNode.get(endWord);
//        System.out.println(prevNodeOfEndNode);
//
//        if(prevNodeOfEndNode==null){
//            return paths;
//        }
        Queue<String> nodes = new LinkedList<>();
        List<String> init=new ArrayList<>();
        init.add(endWord);
        HashMap<String, HashSet<List<String>>> wordToPaths=new HashMap<>();
        HashSet<List<String>> initPath = new HashSet<>();
        initPath.add(init);
        wordToPaths.put(endWord, initPath);
        nodes.add(endWord);
//        System.out.printf("Init %s\n", wordToPaths);

        while (!nodes.isEmpty()){
            List<String> prevNodes=new ArrayList<>();

            while (!nodes.isEmpty()){
                String currentWord=nodes.poll();
                List<String> tmpPrevNode=wordToPreviousNode.get(currentWord);
                HashSet<List<String>> prevPath=wordToPaths.get(currentWord);
                System.out.printf("Prev nodes: %s %s\n", currentWord, tmpPrevNode);
                System.out.printf("Prev paths: %s\n", prevPath);

                if(tmpPrevNode==null){
                    continue;
                }
                for(String prevNode:tmpPrevNode){
                    HashSet<List<String>> oldPaths=wordToPaths.get(prevNode);
                    if(oldPaths==null) oldPaths=new HashSet<>();

                    for(List<String> prePath:prevPath){
                        List<String> currentPath=new ArrayList<>();
                        currentPath.add(prevNode);
                        currentPath.addAll(prePath);
                        oldPaths.add(currentPath);
                    }
//                    System.out.printf("Old path: %s\n",oldPaths);
                    wordToPaths.put(prevNode, oldPaths);
                }
                prevNodes.addAll(tmpPrevNode);
            }
            nodes.addAll(prevNodes);
        }
//        System.out.println(wordToPaths);
        HashSet<List<String>> resultSet=wordToPaths.get(beginWord);
        if(resultSet==null){
            return new ArrayList<>();
        }
        return new ArrayList<>(resultSet);
    }

    public static void main(String[] args) {
        //** Requirement
        //- Tìm tất cả đường (path) ngắn nhất có thể đi từ beginWord --> endWord
        //- Mỗi lần biến đổi thì khác nhau 1 character
        //
        //** Idea
        //1.
        //1.0, Brainstorm
        //- Constraint:
        //+ 1 <= beginWord.length <= 5
        //+ endWord.length == beginWord.length
        //+ 1 <= wordList.length <= 500
        //+ wordList[i].length == beginWord.length
        //
        //- Bài này dùng BFS/DFS
        //- Quan trọng in ra all paths như thế nào:
        //+ Nếu dùng DFS thì có thể sẽ dễ hơn khi chỉ cần cache các list<Node> trung gian
        //+ Nếu dùng BFS thì nên custom 1 chút:
        //  + Ta sẽ chạy từng level 1 --> Cho đến khi tìm được [ min(level) + tìm được word mong muốn ]
        //* Chú ý ở đây là ta sẽ dùng lại tư duy kiểu s --> có thể biến đối thành (common string) nào
        //VD: abc --> [ *bc, a*c, ab* ]
        //- Tư duy như sau:
        //A,(B,C,D)
        //+ (B,C,D) ra for từng thằng + nodes vào (E,F,G) vào level=2
        // abc --> dbc --> dec
        // abc --> abe --> abd
        // abc --> (dbc, abe)
        //
        //VD: abc --> [ *bc, a*c, ab* ]
        //- Ta sẽ dựng adjnode trước đôi với từng patterns:
        //+ *bc : abc,dbc,ebc...
        //+ a*c : ....
        //+ Sau đó khi dùng BFS thì ta sẽ for(string) --> (lấy ra các pattern có thể)
        //+ Sau đó từ pattern đó --> Lấy ra các string khác + add node vào queue
        //---> Sau đó tìm đến khi tìm được word --> lấy các path thoả mãn cùng level.
        //
        //1.1, Special test cases
        //- Không tìm được path:
//        String beginWord = "hit", endWord = "cog";
//        String[] wordList = {"hot","dot","dog","lot","log"};
        //- end không thuộc wordlist:
//        String beginWord = "hit", endWord = "cogx";
//        String[] wordList = {"hot","dot","dog","lot","log"};
//        String beginWord = "hit", endWord = "cog";
//        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        String beginWord = "a", endWord = "c";
        String[] wordList = {"a","b","c"};
        System.out.println(findLadders(beginWord, endWord, Arrays.asList(wordList)));
    }
}
