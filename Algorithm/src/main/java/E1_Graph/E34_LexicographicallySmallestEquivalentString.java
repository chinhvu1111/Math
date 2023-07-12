package E1_Graph;

public class E34_LexicographicallySmallestEquivalentString {
    public static char[] parent;

    public static char findParent(char currentChar){
        while (currentChar!=parent[currentChar-'a']){
            currentChar=parent[currentChar-'a'];
        }
        return currentChar;
    }

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
        for(int i=0;i<length;i++){
            unionFind(s1.charAt(i), s2.charAt(i));
        }
        StringBuilder rs=new StringBuilder();
        for(int i=0;i<baseStr.length();i++){
            char root=findParent(baseStr.charAt(i));
            rs.append(root);
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
        System.out.println(smallestEquivalentString(s1, s2, baseStr));
        //#Reference:
        //839. Similar String Groups
        //1190. Reverse Substrings Between Each Pair of Parentheses
        //205. Isomorphic Strings
        //1528. Shuffle String
    }
}
