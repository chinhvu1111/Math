package E1_daily;

import java.util.*;

public class E47_ReverseSubstringsBetweenEachPairofParentheses {

    public static String reverseParentheses(String s) {
        int n=s.length();
        Deque<Character> dequeue=new LinkedList<>();
//        Stack<Integer> stackIndexBracket=new Stack<>();


        //Time: O(n)
        for(int i=0;i<n;i++){
            if(s.charAt(i)==')'){
                List<Character> reverse=new ArrayList<>();

                //Time: O(n)
                while (!dequeue.isEmpty()&&dequeue.getLast()!='('){
                    char curChar = dequeue.removeLast();
                    reverse.add(curChar);
                }
                if(!dequeue.isEmpty()){
                    dequeue.removeLast();
                }
                //Time: O(n)
                dequeue.addAll(reverse);
            }else{
                dequeue.add(s.charAt(i));
            }
        }
        StringBuilder rs=new StringBuilder();

        while (!dequeue.isEmpty()){
            rs.append(dequeue.removeFirst());
        }
        return rs.toString();
    }

    public static void reverse(StringBuilder s, int start, int end){
        while (start<=end){
            char startC = s.charAt(start);
            char endC = s.charAt(end);
            s.setCharAt(start, endC);
            s.setCharAt(end, startC);
            start++;
            end--;
        }
    }

    public static String reverseParenthesesOptimization(String s) {
        int n=s.length();
        Stack<Integer> stackIndex=new Stack<>();
        StringBuilder s1=new StringBuilder();

        //Time: O(n)
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                //Time: O(n)
                stackIndex.add(s1.length());
            }else if(s.charAt(i)==')'){
                int start=stackIndex.pop();
                reverse(s1, start, s1.length()-1);
            }else{
                s1.append(s.charAt(i));
            }
        }
        return s1.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s that consists of (lower case English letters) and (brackets).
        //* Reverse the strings in (each pair of matching parentheses), starting from (the innermost one).
        //- Your result should not contain any brackets.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= s.length <= 2000
        //s only contains lower case English characters and parentheses.
        //It is guaranteed that all parentheses are balanced.
        //==> Length không lớn lắm
        //
        //- Brainstorm
        //Example 2:
        //Input: s = "(u(love)i)"
        //Output: "iloveu"
        //Explanation: The substring "love" is reversed first, then the whole string is reversed.
        //
        //(u(love)i)
        //=>
        //(u(evol)i)
        //=>
        //(i(love)u)
        //- Khi gặp ) ==> Mới reverse
        //
        //(u(love)i)
        //+ stack : u
        //+ stack : ulove
        //  + Pop ra xong add lại vào lần lượt để reverse
        //  ==> stack : uevol
        //
        //- Reverse 1 string
        //  + abc => cba
        //
        //1.1, Optimization
        //- Bài này ta có thể lưu index ở stack
        //- Dùng range index + reverse bằng Stringbuilder
        //  + Dùng StringBuilder swap khá nhanh nên dễ ==> Không cần add list 2 lần.
        //* Idea
        //- Index sẽ được add vào stack
        //- String không có ( ==> rs
        //  + Ta sẽ reverse dựa trên index của (rs) thay vì (s)
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n^2)
        //
//        String s = "(u(love)i)";
//        String s = "(ed(et(oc))el)";
        String s = "a(bcdefghijkl(mno)p)q";
        System.out.println(reverseParentheses(s));
        System.out.println(reverseParenthesesOptimization(s));
        //#Reference:
        //2839. Check if Strings Can be Made Equal With Operations I
        //1181. Before and After Puzzle
        //1647. Minimum Deletions to Make Character Frequencies Unique
        //
    }
}
