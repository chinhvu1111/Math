package E1_backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E4_BraceExpansion {

    public static void solution(int index, String prevRs, String s, List<String> rs){
        if(index>=s.length()){
            rs.add(prevRs);
            return;
        }
        boolean isExpr=s.charAt(index)=='{';

        if(!isExpr){
            solution(index+1, prevRs+s.charAt(index), s, rs);
        }else{
            int indexNextCurly=index+1;

            while(s.charAt(indexNextCurly)!='}'){
                indexNextCurly++;
            }
            //Ignore {
            index++;
            while(s.charAt(index)!='}'){
                if(s.charAt(index)!=','){
                    solution(indexNextCurly+1, prevRs+s.charAt(index), s, rs);
                }
                index++;
            }
        }
    }

    public static String[] expand(String s) {
        List<String> rs=new ArrayList<>();
        solution(0, "", s, rs);
        Collections.sort(rs);
        return rs.toArray(new String[0]);
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given a string s representing (a list of words). (Each letter) in the word has (one or more options).
        //+ If there is one option, the letter is represented as is.
        //+ If there is more than one option, then curly braces delimit the options. For example, "{a,b,c}" represents options ["a", "b", "c"].
        //- For example, if s = "a{b,c}", the first character is always 'a',
        // but the second character can be 'b' or 'c'.
        // The original list is ["ab", "ac"].
        //- Return all words that can be formed in this manner, (sorted in lexicographical order).
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= s.length <= 50
        //+ s consists of curly brackets '{}', commas ',', and lowercase English letters.
        //+ s is guaranteed to be a valid input.
        //+ There are (no nested) curly brackets.
        //+ All characters inside a pair of consecutive (opening and ending) curly brackets are different.
        //
        //- Brainstorm
        //- Ở đây đơn giản là ta sẽ loop tất cả các character trong {a,b}
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(X^K + N)
        //  + Tạo string mới mất O(N) space mỗi lần tạo
        //  + O(X^K) số strings in result
        //- Time :
        //  + At most N recursion calls : O(N)
        //          {a,b}c{d,e}f
        //list={a,b}
        //list={ac,bc}
        //list={ac,bc} *{d,e} = {acd,ace,bcd,bce}
        //list={ac,bc} *{f} = {acdf,acef,bcdf,bcef}
        //- Nếu chúng ta có chỉ số K trong mỗi từ, thì số chuỗi sẽ bằng tích của số ký tự có thể có cho mỗi chỉ số K này.
        //  + Số đoạn characters {} or (,a,)
        //- If the number of possible characters for each index is the same, say X, then the number of words produced would be O(X^K)
        //  + Số character mỗi stage
        //Ex:
        //X=3, {a,b,c},{d,e,f} n=14 ==> stage=(14/7)
        //+ Time : O(3^N/7) * N (Do concat string mất O(N))
        //+ Sort time : N*Log(N)
        //+ K is the number of stage
        //+ X is the average of the number of each stage
        //==> O(X^K + N)
        //
        //* CONCAT STRING, TIME COMPLEXITY:
        //- [Java] How is the running time of string concatenation O(n^2)?
        //  + Đây là average time : concat n strings ==> 1+2+3+...+n=n*(n+1)/2
        //- In Java, the complexity of s1. concat(s2) or s1 + s2 is O(M1 + M2) where M1 and M2 are the respective String lengths.
        // Turning that into the complexity of a sequence of concatenations is difficult in general.
        // However, if you assume N concatenations of Strings of length M, then the complexity is indeed O(M * N * N) which matches what you said in the Question.
        //
        //#Reference:
        //784. Letter Case Permutation
        //1096. Brace Expansion II
        String s = "{a,b}c{d,e}f";
        System.out.println(Arrays.toString(expand(s)));
    }
}