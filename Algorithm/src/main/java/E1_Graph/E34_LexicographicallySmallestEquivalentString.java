package E1_Graph;

import java.util.ArrayList;
import java.util.List;

public class E34_LexicographicallySmallestEquivalentString {
    public static char[] parent;

    public static char findParent(char currentChar){
        while (currentChar!=parent[currentChar-'a']){
            currentChar=parent[currentChar-'a'];
        }
        return currentChar;
    }

    //O(1)
    public static void unionFind(char c1, char c2){
        char parentC1=findParent(c1);
        parent[c1-'a']=parentC1;
        char parentC2=findParent(c2);
        parent[c2-'a']=parentC2;

        if(parentC1==parentC2){
            return;
        }
        if(parentC1<=parentC2){
            parent[parentC2-'a']=parentC1;
            parent[c2-'a']=parentC1;
        }else{
            parent[parentC1-'a']=parentC2;
            parent[c1-'a']=parentC2;
        }
    }

    public static String smallestEquivalentString(String s1, String s2, String baseStr) {
        parent=new char[26];
        int length=s1.length();
        if(length==0){
            return baseStr;
        }
        for(int i=0;i<26;i++){
            parent[i]=(char)(i+'a');
        }
        //O(N)
        for(int i=0;i<length;i++){
            unionFind(s1.charAt(i), s2.charAt(i));
        }
        StringBuilder rs=new StringBuilder();
        //O(M)
        for(int i=0;i<baseStr.length();i++){
            char root=findParent(baseStr.charAt(i));
            rs.append(root);
        }
        return rs.toString();
    }

    public static int tempMinChar;

    public static void dfsToGetSmallestChar(int n, int u, int[][] connections, int[] visited, List<Integer> adjNode){
        visited[u]=1;

        for(int i=0;i<26;i++){
            if(visited[i]==1||connections[u][i]==0){
                continue;
            }
            tempMinChar=Math.min(tempMinChar, i);
            adjNode.add(i);
            dfsToGetSmallestChar(n, i, connections, visited, adjNode);
        }
    }

    public static String smallestEquivalentStringDFS(String s1, String s2, String baseStr) {
        int[][] connections=new int[26][26];
        int n=s1.length();
        //O(N)
        for(int i=0;i<n;i++){
            connections[s1.charAt(i)-'a'][s2.charAt(i)-'a']=1;
            connections[s2.charAt(i)-'a'][s1.charAt(i)-'a']=1;
        }
        int[] mappingChar=new int[26];
        int visited[]=new int[26];

        //O(26)
        for(int i=0;i<26;i++){
            mappingChar[i]=i;
        }
        //O(26) + O(N*N) : Số cạnh sẽ bằng chiều dài của s1 hoặc s2
        for(int i=0;i<26;i++){
            if(visited[i]==0){
                tempMinChar=i;
                List<Integer> adjNode=new ArrayList<>();
                dfsToGetSmallestChar(n, i, connections, visited, adjNode);
                mappingChar[i]=tempMinChar;
//                System.out.printf("%s %s\n", i, adjNode);
                for(int v: adjNode){
                    mappingChar[v]=tempMinChar;
                }
            }
        }
        StringBuilder rs=new StringBuilder();
        int m=baseStr.length();

        //O(M)
        for(int i=0;i<m;i++){
            char mapChar=(char)(mappingChar[baseStr.charAt(i)-'a']+'a');
            rs.append(mapChar);
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Cho s1 và s2 tương đương nhau
        //* return string nhở nhất của (baseStr) --> Sao cho ta biến đổi bằng các phép tương đương từ s1 và s2
        //- Ta có đầy đủ các tính chất sau đây:
        //+ Reflexivity: 'a' == 'a'.
        //+ Symmetry: 'a' == 'b' implies 'b' == 'a'.
        //+ Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
        //
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //+ 1 <= s1.length, s2.length, baseStr <= 1000
        //+ s1.length == s2.length
        //--> Có vẻ không brute force được
        //
        //- Brainstorm
        //- Từ s1 và s2 --> Ta sẽ tìm được với mỗi ký tự (char) --> Sẽ map ra được ký tự cho là equivalent --> Nhưng về lexicoraphically (Thứ tự chữ cái)
        //VD:
        //s1 = "parker", s2 = "morris", baseStr = "parser"
        //+ Ở đây ta dùng union find --> Chữ cái nào nhỏ hơn ==> chữ cái đó là parent thôi.
        //==> ở đây là tìm MIN (Chứ không cần sort)
        //
        //+ Sao khi có union --> Traverse 1 lần base là xong
        //
//        String s1 = "parker", s2 = "morris", baseStr = "parser";
//        String s1 = "hello", s2 = "world", baseStr = "hold";
        String s1 = "leetcode", s2 = "programs", baseStr = "sourcecode";
        //1.1, Complexity
        //- Time complexity : O((N+M)*D)
        //+ N is length of string s1 and s2
        //+ M is length of baseStr
        //+ D is the number of unique characters.
        //
        //- Space complexity : O(26) ~ O(1)
        //
        //* Method 2:
        //2.0, DFS
        //- Idea:
        //+ Tạo graph giữa các characters
        //+ Cần tạo map array của bài map(c -> a) (a là min + có kết nối với c)
        //+ Sau đó tìm global min của từng character từ (a -> z)/( 0 -> 25)
        //  + Tạo 1 list adj kề với (i) cần để có thể gán map hết cho các nodes đó.
        //
        //2.1, Complexity :
        //- Time complexity : O(N+M + N*N)
        //+ N điểm khác nhau --> có nhiều nhất N*N edges
        //- Space complexity : O(26*26) ~ constant
        //
        System.out.println(smallestEquivalentString(s1, s2, baseStr));
        System.out.println(smallestEquivalentStringDFS(s1, s2, baseStr));
        //#Reference:
        //839. Similar String Groups
        //1190. Reverse Substrings Between Each Pair of Parentheses
        //205. Isomorphic Strings
        //1528. Shuffle String
        //2514. Count Anagrams
        //1585. Check If String Is Transformable With Substring Sort Operations
        //1079. Letter Tile Possibilities
    }
}
