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
        HashMap<String, HashSet<String>> wordToPreviousNode=new HashMap<>();

        //Lưu tất cả các words trước word hiện tại
        //VD:
        //  A   B   C   D
        //   \  |   |   |
        //     E      F
        while (!nextNodes.isEmpty()){
            boolean isMatch=false;
            HashSet<String> nextWords=new HashSet<>();
            HashSet<String> traverse=new HashSet<>();

            while (!nextNodes.isEmpty()){
                String currentWord=nextNodes.poll();
                int m=currentWord.length();
                visited.add(currentWord);

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
                        HashSet<String> prevNodes=wordToPreviousNode.get(s);
                        if(prevNodes==null) prevNodes=new HashSet<>();
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
//            System.out.printf("Next words: %s\n", nextWords);
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
//        System.out.println(wordToPreviousNode);
//        System.out.println(patternToNodes);
//        if(true){
//            return null;
//        }

        while (!nodes.isEmpty()){
            List<String> prevNodes=new ArrayList<>();

            while (!nodes.isEmpty()){
                String currentWord=nodes.poll();
                HashSet<String> tmpPrevNode=wordToPreviousNode.get(currentWord);
                HashSet<List<String>> prevPath=wordToPaths.get(currentWord);
//                System.out.printf("Prev nodes: %s %s\n", currentWord, tmpPrevNode);
//                System.out.printf("Prev paths: %s\n", prevPath);
//
                if(tmpPrevNode==null){
                    continue;
                }
                //A     B      C      D
                //\   /         \   /
                // E              H
                //  \           /
                //        Z
                //Xét currentWord='E'
                //prevPath= E,Z
                //tmpPrevNode= A, B (A và B có thể được đến cùng từ E và H ==> Sẽ tính old paths ở đây)
                //--> Tính path cho A và B
                List<String> prevWords=new ArrayList<>();
                for(String prevNode:tmpPrevNode){
                    if(prevNode.equals(currentWord)){
                        continue;
                    }
                    prevWords.add(prevNode);
                    HashSet<List<String>> oldPaths=wordToPaths.get(prevNode);
                    if(oldPaths==null) oldPaths=new HashSet<>();

                    for(List<String> prePath:prevPath){
//                        System.out.printf("%s : %s\n",prePath, prevPath);
                        List<String> currentPath=new ArrayList<>();
                        currentPath.add(prevNode);
                        currentPath.addAll(prePath);
                        oldPaths.add(currentPath);
//                        System.out.printf("%s\n", prePath);
                    }
//                    System.out.printf("Old path: %s\n",oldPaths);
                    wordToPaths.put(prevNode, oldPaths);
                }
                prevNodes.addAll(prevWords);
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
        //- Case này bị trường hợp (a --> a)
        //a --> * --> a ==> Lặp vô tận
        //+ Cần ignore đi case này ==> Nếu prevWord==currentWord: Ignore đi.
//        String beginWord = "a", endWord = "c";
//        String[] wordList = {"a","b","c"};
        //- Case liên quan đến việc các prev lặp lại nhiều lần --> Chuyển tất cả sang SET (Để tránh lặp lại)
        String beginWord = "aaaaa", endWord = "ggggg";
        String[] wordList = {"aaaaa","caaaa","cbaaa","daaaa","dbaaa","eaaaa","ebaaa","faaaa","fbaaa","gaaaa","gbaaa",
                "haaaa","hbaaa","iaaaa","ibaaa","jaaaa","jbaaa","kaaaa","kbaaa","laaaa","lbaaa",
                "maaaa","mbaaa","naaaa","nbaaa","oaaaa","obaaa","paaaa","pbaaa","bbaaa","bbcaa",
                "bbcba","bbdaa","bbdba","bbeaa","bbeba","bbfaa","bbfba","bbgaa","bbgba","bbhaa",
                "bbhba","bbiaa","bbiba","bbjaa","bbjba","bbkaa","bbkba","bblaa","bblba","bbmaa",
                "bbmba","bbnaa","bbnba","bboaa","bboba","bbpaa","bbpba","bbbba","abbba","acbba",
                "dbbba","dcbba","ebbba","ecbba","fbbba","fcbba","gbbba","gcbba","hbbba","hcbba",
                "ibbba","icbba","jbbba","jcbba","kbbba","kcbba","lbbba","lcbba","mbbba","mcbba",
                "nbbba","ncbba","obbba","ocbba","pbbba","pcbba","ccbba","ccaba","ccaca","ccdba",
                "ccdca","cceba","cceca","ccfba","ccfca","ccgba","ccgca","cchba","cchca","cciba",
                "ccica","ccjba","ccjca","cckba","cckca","cclba","cclca","ccmba","ccmca","ccnba",
                "ccnca","ccoba","ccoca","ccpba","ccpca","cccca","accca","adcca","bccca","bdcca",
                "eccca","edcca","fccca","fdcca","gccca","gdcca","hccca","hdcca","iccca","idcca",
                "jccca","jdcca","kccca","kdcca","lccca","ldcca","mccca","mdcca","nccca","ndcca",
                "occca","odcca","pccca","pdcca","ddcca","ddaca","ddada","ddbca","ddbda","ddeca",
                "ddeda","ddfca","ddfda","ddgca","ddgda","ddhca","ddhda","ddica","ddida","ddjca",
                "ddjda","ddkca","ddkda","ddlca","ddlda","ddmca","ddmda","ddnca","ddnda","ddoca",
                "ddoda","ddpca","ddpda","dddda","addda","aedda","bddda","bedda","cddda","cedda",
                "fddda","fedda","gddda","gedda","hddda","hedda","iddda","iedda","jddda","jedda",
                "kddda","kedda","lddda","ledda","mddda","medda","nddda","nedda","oddda","oedda",
                "pddda","pedda","eedda","eeada","eeaea","eebda","eebea","eecda","eecea","eefda",
                "eefea","eegda","eegea","eehda","eehea","eeida","eeiea","eejda","eejea","eekda",
                "eekea","eelda","eelea","eemda","eemea","eenda","eenea","eeoda","eeoea","eepda",
                "eepea","eeeea","ggggg","agggg","ahggg","bgggg","bhggg","cgggg","chggg","dgggg",
                "dhggg","egggg","ehggg","fgggg","fhggg","igggg","ihggg","jgggg","jhggg","kgggg",
                "khggg","lgggg","lhggg","mgggg","mhggg","ngggg","nhggg","ogggg","ohggg","pgggg",
                "phggg","hhggg","hhagg","hhahg","hhbgg","hhbhg","hhcgg","hhchg","hhdgg","hhdhg",
                "hhegg","hhehg","hhfgg","hhfhg","hhigg","hhihg","hhjgg","hhjhg","hhkgg","hhkhg",
                "hhlgg","hhlhg","hhmgg","hhmhg","hhngg","hhnhg","hhogg","hhohg","hhpgg","hhphg",
                "hhhhg","ahhhg","aihhg","bhhhg","bihhg","chhhg","cihhg","dhhhg","dihhg","ehhhg",
                "eihhg","fhhhg","fihhg","ghhhg","gihhg","jhhhg","jihhg","khhhg","kihhg","lhhhg",
                "lihhg","mhhhg","mihhg","nhhhg","nihhg","ohhhg","oihhg","phhhg","pihhg","iihhg",
                "iiahg","iiaig","iibhg","iibig","iichg","iicig","iidhg","iidig","iiehg","iieig",
                "iifhg","iifig","iighg","iigig","iijhg","iijig","iikhg","iikig","iilhg","iilig",
                "iimhg","iimig","iinhg","iinig","iiohg","iioig","iiphg","iipig","iiiig","aiiig",
                "ajiig","biiig","bjiig","ciiig","cjiig","diiig","djiig","eiiig","ejiig","fiiig",
                "fjiig","giiig","gjiig","hiiig","hjiig","kiiig","kjiig","liiig","ljiig","miiig",
                "mjiig","niiig","njiig","oiiig","ojiig","piiig","pjiig","jjiig","jjaig","jjajg",
                "jjbig","jjbjg","jjcig","jjcjg","jjdig","jjdjg","jjeig","jjejg","jjfig","jjfjg",
                "jjgig","jjgjg","jjhig","jjhjg","jjkig","jjkjg","jjlig","jjljg","jjmig","jjmjg",
                "jjnig","jjnjg","jjoig","jjojg","jjpig","jjpjg","jjjjg","ajjjg","akjjg","bjjjg",
                "bkjjg","cjjjg","ckjjg","djjjg","dkjjg","ejjjg","ekjjg","fjjjg","fkjjg","gjjjg",
                "gkjjg","hjjjg","hkjjg","ijjjg","ikjjg","ljjjg","lkjjg","mjjjg","mkjjg","njjjg",
                "nkjjg","ojjjg","okjjg","pjjjg","pkjjg","kkjjg","kkajg","kkakg","kkbjg","kkbkg",
                "kkcjg","kkckg","kkdjg","kkdkg","kkejg","kkekg","kkfjg","kkfkg","kkgjg","kkgkg",
                "kkhjg","kkhkg","kkijg","kkikg","kkljg","kklkg","kkmjg","kkmkg","kknjg","kknkg",
                "kkojg","kkokg","kkpjg","kkpkg","kkkkg","ggggx","gggxx","ggxxx","gxxxx","xxxxx",
                "xxxxy","xxxyy","xxyyy","xyyyy","yyyyy","yyyyw","yyyww","yywww","ywwww","wwwww",
                "wwvww","wvvww","vvvww","vvvwz","avvwz","aavwz","aaawz","aaaaz"};
        System.out.println(findLadders(beginWord, endWord, Arrays.asList(wordList)));
        //#Reference:
        //1. Two Sum
        //1153. String Transforms Into Another String
        //1347. Minimum Number of Steps to Make Two Strings Anagram
        //1213. Intersection of Three Sorted Arrays
    }
}
