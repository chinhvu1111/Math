package E1_Topological_sort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class E2_AlienDictionaryFix {

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
        for(int j=0;j<s1.length();j++){
            existsChar[s1.charAt(j)-'a']=1;
        }
        for(int j=0;j<s2.length();j++){
            existsChar[s2.charAt(j)-'a']=1;
        }

        for(i=0;i<minLength;i++){
            if(s1.charAt(i)!=s2.charAt(i)){
                return new char[]{s1.charAt(i), s2.charAt(i)};
            }
        }
//        if(s1.equals(s2)){
//            return new char[]{'0', '0'};
//        }
        return new char[]{};
    }
    public static String rs;
    public static int[] existsChar;

    public static void findDistinctSortedLetters(Node node, boolean[] visited, List<Character> listChar){
        Set<Node> adjNodes=node.adjChars;
        if(adjNodes.size()==0){
            if(rs.length()<listChar.size()){
                StringBuilder finalRs=new StringBuilder();

                for(char s1: listChar){
                    if(existsChar[s1-'a']!=-1){
                        finalRs.append(s1);
                    }
                }
                rs=finalRs.toString();
//                System.out.println(listChar);
            }
            return;
        }
//        System.out.println(adjNodes);
        for(Node adj:adjNodes){
            visited[adj.currentChar]=true;
            listChar.add((char)(adj.currentChar+'a'));
            findDistinctSortedLetters(adj, visited, listChar);
            listChar.remove(listChar.size()-1);
            visited[adj.currentChar]=false;
        }
    }

    public static boolean isCycle(Node node, boolean[] stack, boolean[] visited){
        if(stack[node.currentChar]){
            return true;
        }
        if(visited[node.currentChar]){
            return false;
        }
        Set<Node> adjNodes=node.adjChars;
        if(!adjNodes.isEmpty()){
            stack[node.currentChar]=true;
            visited[node.currentChar]=true;
            for(Node nextNode: adjNodes){
                if(isCycle(nextNode, stack, visited)){
                    return true;
                }
            }
        }
        stack[node.currentChar]=false;
        return false;
    }

    public static String alienOrder(String[] words) {
        rs="";
        int n=words.length;
        Node[] nodes=new Node[26];
        existsChar=new int[26];

        for(int i=0;i<26;i++){
            nodes[i]=new Node(i);
        }
        boolean[] hashParent=new boolean[26];
//        HashSet<String> distinct=new HashSet<>();

        for(int i=0;i<n-1;i++){
            char[] pairChars=findPairNode(words[i], words[i+1]);
//            distinct.add(words[i]);
//            distinct.add(words[i+1]);

            if(pairChars.length==0){
                continue;
            }
            char char1=pairChars[0];
            char char2=pairChars[1];

//            System.out.printf("%s %s\n",nodes[char1-'a'].currentChar, nodes[char2-'a'].currentChar);
            nodes[char1-'a'].adjChars.add(nodes[char2-'a']);
            hashParent[char2-'a']=true;
        }
//        if(distinct.size()==1){
//            return distinct.iterator().next();
//        }
        boolean[] visited=new boolean[26];
        boolean[] stack=new boolean[26];
        //              1
        //           /     \
        //         3        4
        //           \    /
        //              5
        //==> visited[] sẽ reset

        //Checking the cycle
        for(int i=0;i<26;i++){
            if(visited[i]){
                continue;
            }
            if(isCycle(nodes[i], stack, visited)){
                return "";
            }
        }
//        for(int i=0;i<26;i++){
//            System.out.printf("%s %s\n", (char)(i+'a'), existsChar[i]);
//        }

        for(int i=0;i<26;i++){
//            System.out.printf("%s %s\n", (char)(i+'a'), hashParent[i]);
            if(!hashParent[i]&&nodes[i].adjChars.size()!=0){
//                System.out.println(i);
                List<Character> listChar=new ArrayList<>();
                listChar.add((char)(i+'a'));
                findDistinctSortedLetters(nodes[i], visited, listChar);
//                visited=new boolean[26];
//                listRoots.add(nodes[i]);

            }
        }
        System.out.println(rs);
        for(char c:rs.toCharArray()){
            existsChar[c-'a']=-1;
        }
        String tempRs=rs;
        rs="";
        for(int i=0;i<26;i++){
            if(existsChar[i]==1&&!nodes[i].adjChars.isEmpty()){
//                System.out.printf("%s %s\n", (char)(i+'a'), existsChar[i]);
                List<Character> listChar=new ArrayList<>();
                listChar.add((char)(i+'a'));
                findDistinctSortedLetters(nodes[i], visited, listChar);
                tempRs+=rs;
                existsChar[i]=-1;
            }
//            System.out.println(rs);
        }
        for(char c:rs.toCharArray()){
            existsChar[c-'a']=-1;
        }
        for(int i=0;i<26;i++){
            if(existsChar[i]!=1){
                continue;
            }
            tempRs+=(char)(i+'a');
            existsChar[i]=-1;
        }
//        tempRs+=rs;
        return tempRs;
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
        //- Nó có những trường hợp như sau:
        //- length(a) == length(b)
        //+ Ký tự đầu tiên != nhau nếu ký tự nào nhỏ hơn --> Nhỏ hơn
        //
        //- length(a) != length(b)
        //+ Nếu trong đoạn min(length(a), length(b)) mà không tìm được ký tự khác nhau ==> Cái nào shorter --> smaller.
        //==> Với tư duy như bên trên thì ta có thể bỏ đi dấu (>=/ <=)
        //+ wrt < wrf ==> t<f
        //+ wrf < er ==> w < e
        //+ er < ett ==> t>r
        //+ ett < rftt ==> r>e
        //
        //- Các predicates có thể có:
        //+ e>t, e>r, r>t ==> Cần sắp xếp (t<r<e)
        //+ Ngoài ra còn tư duy thứ tự:
        //VD: e>t, g>h, t>g, (i>t)
        // e -> t -> g -> h
        // i -> t
        // ==> i lúc này để đâu:
        //+ Chỉ có cách là tạm thời mở kết nối (i.next=t)
        //* Note:
        //- Để tránh ghi theo chiều ngược lại --> Ta nên lưu all root nodes ==> Để có thể traverse từ trên xuống --> Tạo string.
        //
        // e -> l -> k -> t -> g -> h
        // i -> m -> n -> t
        //
        //1.1, Implementation
        //
        //VD:
        // e>h, t>a, e>a, h>t
        // e -> h, t -> a
        // e -> h -> t -> a
        // e -> a ==> Cái này thừa để đâu.
        //--> Vẫn để nguyên ==> Ta sẽ implement việc ( 1 node --> N nodes)
        //
        //- Tìm all character sort --> Đi từ all root ==> Dưới cùng ==> Dùng DFS nếu đường nào mà thu được max số character --> return rs.
        //+ Tìm root như thế nào ==> Tìm node không có parent là được.
//        String[] words = {"wrt","wrf","er","ett","rftt"};
//        String[] words = {"z","x"};
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
        String[] words = {"ac","ab","b"};
        // c<b, a<b
        System.out.println(alienOrder(words));
    }
}
