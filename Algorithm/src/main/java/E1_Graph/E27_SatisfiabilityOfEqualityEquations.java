package E1_Graph;

import java.util.ArrayList;
import java.util.List;

public class E27_SatisfiabilityOfEqualityEquations {

    public static int findParent(int[] parents, int currentNode){
        while (currentNode!= parents[currentNode]){
            currentNode=parents[currentNode];
        }
        return currentNode;
    }

    public static boolean unionFind(int leftNode, int rightNode, int[] parents,
                                    int[] levels, int[][] visited, String operator){
        int leftRoot=findParent(parents, leftNode);
        int rightRoot=findParent(parents, rightNode);
        int operatorInt=operator.equals("==")?1:-1;

        //operator == '==' : before == '!=' : return false
        //operator == '!=' : before == '==' : return false
        if(visited[leftRoot][rightRoot]!=0&&visited[leftRoot][rightRoot]!=operatorInt){
            return false;
        }
        visited[leftRoot][rightRoot]=operatorInt;
        visited[rightRoot][leftRoot]=operatorInt;
        if(operatorInt==-1){
            return true;
        }
        if(leftRoot==rightRoot&&leftRoot==leftNode){
            parents[leftRoot]=rightRoot;
            levels[rightRoot]++;
        }else if(levels[leftRoot]>levels[rightRoot]){
            parents[rightRoot]=leftRoot;
            levels[leftRoot]++;
        }else{
            parents[leftRoot]=rightRoot;
            levels[rightRoot]++;
        }
//        System.out.printf("%s %s %s %s %s, parent: %s %s \n", (char)(leftRoot+'a'), (char)(rightRoot+'a'), operatorInt,
//                visited[leftRoot][rightRoot], operatorInt, parents[leftRoot], parents[rightRoot]);
        return true;
    }
    public static boolean equationsPossible(String[] equations) {
        int n=equations.length;
        if(n<=0){
            return true;
        }
        int[][] relations=new int[26][26];
        int[] levels=new int[26];
        int[] parents=new int[26];
        for(int i=0;i<=25;i++){
            parents[i]=i;
        }
        List<String> equalStrings=new ArrayList<>();
        List<String> nonEqualStrings=new ArrayList<>();
        for(String s:equations){
            if(s.contains("==")){
                equalStrings.add(s);
            }else{
                nonEqualStrings.add(s);
            }
        }

        for(String s:equalStrings){
            int var1= s.charAt(0)-'a';
            int var2= s.charAt(s.length()-1)-'a';
            String operator= s.substring(1, 3);
//            System.out.printf("%s %s\n", (char)(var1+'a'), (char)(var2+'a'));
            relations[var1][var1]=1;
            relations[var2][var2]=1;

            unionFind(var1, var2, parents, levels, relations, operator);
        }
        for(String s: nonEqualStrings){
            int var1= s.charAt(0)-'a';
            int var2= s.charAt(s.length()-1)-'a';
            String operator= s.substring(1, 3);
//            System.out.printf("%s %s\n", (char)(var1+'a'), (char)(var2+'a'));
            relations[var1][var1]=1;
            relations[var2][var2]=1;

            boolean currentRs=unionFind(var1, var2, parents, levels, relations, operator);
            if(!currentRs){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //-
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //+
        //- Brainstorm
        //- Với dạng bài này giống bài pair (a,b)
        //+ Ta cần define value quan hệ giữa (a và b)
        //VD: visited[a][b]:
        //+ 1 : equal
        //+ -1 : non equal
        //+ 0 : non init
        //- Nó bị 1 vấn đề liên quan đến :
        //+ ["a==b","b!=c","c==a"]
        //==> Các operation trung gian ==> Mình cần biết parent nó là ai.
        //
        //Các cases:
        //- b!=c, c!=a : chưa chắc b!=a
        //  + Các giá trị dạng != : Chỉ có tác dụng khi 1 tập hợp (a==b==c) và (c!=d) ==> Cả tập hợp khác d.
        //- Trong 1 tập hợp equal --> Ta chỉ cần check root của nó khác là được.
        //- Các cases riêng rẽ như sau:
        //+ Nếu (a==b) : check xem root của a và b khác nhau hay không
        //+ Nếu (a!=b) : check xem root của a và b có khác nhau hay không.
        //
        //- Test cases:
//        String[] equations = {"a==b","b!=a"};
//        String[] equations = {"a==b","b==a"};
//        String[] equations = {"a==b","b!=c","c==a"};
//        String[] equations = {"a!=a"};
        String[] equations = {"e==d","d!=b","a!=b","b==f","e==b","b==a"};
        //(e,d)(b,f)
        //(e,d,b,f)
        //(e,d,b,f,a)
        //(d!=b), (a!=b)
//        String[] equations = {"e==d","d!=b","d=h","b==e","e==d"};
        //Case này sẽ bị dạng chọn sai:
        //VD:
        //parent[b]=a
        //b!=c ==>[ cần cập nhập cả parent != c ]
        //c==a
        //
        //- a!=a : ==> n<=1 : return true
        //
        //- Case 2:
        //String[] equations = {"e==d","d!=b","d=h","b==e","e==d"};
        //==> Case này dạng not equal sẽ bị thiếu các mỗi quan hệ của (subnode != parent)
        //--> Nên xét riêng từng tập hợp.
        System.out.println(equationsPossible(equations));
        //1061. Lexicographically Smallest Equivalent String
        //1307. Verbal Arithmetic Puzzle
        //1850. Minimum Adjacent Swaps to Reach the Kth Smallest Number
        //2172. Maximum AND Sum of Array
    }
}
