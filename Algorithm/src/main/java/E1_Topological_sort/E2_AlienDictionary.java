package E1_Topological_sort;

import java.util.*;

public class E2_AlienDictionary {

    public static class Node{
        int currentChar;
        HashSet<Node> adjChars;
        public Node(int currentChar){
            this.currentChar=currentChar;
            adjChars=new HashSet<>();
        }
    }

    public static char[] findPairNode(String s1, String s2){
        int minLength=Math.min(s1.length(), s2.length());
        int i;

        for(i=0;i<minLength;i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                return new char[]{s1.charAt(i), s2.charAt(i)};
            }
        }
//        if(s1.equals(s2)){
//            return new char[]{'0', '0'};
//        }
        if(s2.length()<s1.length()){
            return new char[]{'0'};
        }
        return new char[]{};
    }
    public static String rs;
    public static List<Integer>[] nodes;

    public static boolean dfs(int currentValue, boolean[] visited, boolean[] stack, HashMap<Integer, Integer> inDegree, StringBuilder rs){
        if(stack[currentValue]){
            return true;
        }
        if(visited[currentValue]){
            return false;
        }
        stack[currentValue]=true;
        visited[currentValue]=true;
//        System.out.println((char)(currentValue+'a'));
        rs.append((char)(currentValue+'a'));

        if(nodes[currentValue].size()!=0){
            for(Integer adj:nodes[currentValue]){
                inDegree.put(adj, inDegree.get(adj)-1);
            }
        }
//        System.out.println(inDegree);
        for(Map.Entry<Integer, Integer> entry: inDegree.entrySet()){
            if(entry.getValue()==0){
//                System.out.println((char)(entry.getKey()+'a'));
                dfs(entry.getKey(), visited, stack, inDegree, rs);
            }
        }
        stack[currentValue]=false;
        return false;
    }

    public static String alienOrder(String[] words) {
        //Space O(26)
        nodes=new ArrayList[26];
        //Space O(26)
        HashMap<Integer, Integer> inDegree=new HashMap<>();
        rs="";

        for(int i=0;i<26;i++){
            nodes[i]=new ArrayList<>();
        }
        //Time O(N)
        for(String word:words){
            //O(M)
            for(char c:word.toCharArray()){
                inDegree.put(c-'a', 0);
            }
        }
        //Time O(N)
        for(int i=0;i<words.length-1;i++){
            String s1=words[i];
            String s2=words[i+1];
            if(s1.length()>s2.length()&&s1.startsWith(s2)){
                return "";
            }
            //Time O(M)
            char[] currentPair=findPairNode(s1, s2);

            if(currentPair.length==1){
                return "";
            }
            if(currentPair.length==0){
                continue;
            }
            inDegree.put(currentPair[1]-'a', inDegree.getOrDefault(currentPair[1]-'a', 0)+1);
            nodes[currentPair[0]-'a'].add(currentPair[1]-'a');
        }
        boolean[] visited=new boolean[26];
        boolean[] stack=new boolean[26];
        StringBuilder tempRs=new StringBuilder();
//        System.out.println(inDegree);

        for(Map.Entry<Integer, Integer> entry: inDegree.entrySet()){
            if(entry.getValue()==0){
//                System.out.printf("%s\n", (char)(entry.getKey()+'a'));
//                tempRs.append((char)(entry.getKey()+'a'));
                //Time :O(E)
                //Space : O(E)
                dfs(entry.getKey(), visited, stack,inDegree, tempRs);
//                System.out.println(tempRs);
            }
        }
        //Time O(N)
        for(Map.Entry<Integer, Integer> entry: inDegree.entrySet()){
            if(!visited[entry.getKey()]){
                return "";
            }
        }

        return tempRs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Cho 1 words của người alien được sắp xếp tăng dần theo rules của họ
        //* Return lại string bao gồm các unique letters --> Được sắp xếp theo Increasing order bằng new language.
        //<> Nếu không có thì return "", có nhiều thì return 1 trong số chúng.
        //
        //VD:
        //words= ["wrt","wrf","er","ett","rftt"]
        //+ wrt < wrf ==> t<f
        //+ wrf < er ==> w < e
        //+ er < ett ==> t>=r
        //+ ett < rftt ==> r>e / (r>=e, f=t)
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //+ 1 <= words.length <= 100
        //+ 1 <= words[i].length <= 100
        //--> Không có gì đặc biệt cho lắm
        //
        //- Brainstorm
        //- Dùng HashMap tốt hơn array ở chỗ:
        //+ HashMap sẽ giảm được số space lưu trữ (visited[i][j] (i==j)
        //+ HashMap cũng sẽ chỉ lưu key có thể có trong bài --> nếu dùng array thì sẽ cần phải xét các cases (Ta cần visited[i][j]==0 (mà thực tế visited[][]==0 có thể là
        //không tồn tại/ là inDegress[i]==0 thật)
        //
        //- Trong bài này nếu ta gặp cycle --> return ""
        //VD:
        // z->x, x->a, a->z, b->z
        //           b
        //         /
        //      z
        //        \
        //          x
        //            \
        //             a -> z
        //+ Xuất hiện chu kỳ ==> Return ""
        //     B
        //      \
        //        Z --------- A
        //          \      /
        //             X
        //- Special test case:
        //- Test case 1:
        //VD: Nếu có predicate:
        // z -> a, a -> b, b -> z
        // z<a<b , b<z
        //--> Như trường hợp bên trên thì b<z bị sai dó trước đó ta đã có (z<b) rồi
        //==> Cái này để check một words có thoả mãn hay không --> Check xem nó (cycle) hay không.
        //
        //- Test case 2:
        //
        //
//        String[] words = {"z","x","z"};
//        String[] words = {"z","z"};
//        String[] words = {"a","z","z"};
        //- Test case 3:
        //+ Những từ không có kết nối ==> Cộng vào cuối
//        String[] words = {"zy","zx"};
        //
        //- Test case 4:
//        String[] words = {"ac","ab","zc","zb"};
        // c<b, a<z, (c<b)
        //+ Current result : azb ==> Đổi lại exists (Cộng vào cuối) ra :
        //  + azbc --> SAI
        //+ Final result : acbz
        //* Các ký tự còn lại cũng cần phải sắp xếp theo thứ tự tăng dần của dictionary.
        //==> azcb
//        String[] words = {"ab","adc"};
        // b<d
//        String[] words = {"ac","ab","b"};
        // c<b, a<b
//        String[] words = {"ac","ab","b"};
        //+ c<b, a<b
        //  + c -> b, a -> b
        //    c     a
        //     \   /
        //       b
        //Expected result : acb
//        String[] words = {"ac","ab","zc","zb"};
        //c<b, a<z, (c<b)
        //      c   a
        //    /      \
        //  b         z
        //
        //- Test case 5:
        String[] words = {"z","x","a","zb","zx"};
        // z->x, x->a, a->z, b->z
        //           b
        //         /
        //      z
        //        \
        //          x
        //            \
        //             a -> z
        //+ Xuất hiện chu kỳ ==> Return ""
        System.out.println(alienOrder(words));
        //
        //1.1, Complexity:
        //- Time complexity : O(N*M+E)
        //+ N is the number of words
        //+ M is the number of unique characters
        //+ E is the number of edges
        //- Space complexity : O(M)
        //
        //#Reference:
        //1203. Sort Items by Groups Respecting Dependencies
        //2274. Maximum Consecutive Floors Without Special Floors
        //777. Swap Adjacent in LR String
        //919. Complete Binary Tree Inserter
    }
}
