package E1_daily;

import java.util.Stack;

public class E126_MinimumAddToMakeParenthesesValid {

    public static int minAddToMakeValid(String s) {
        int n=s.length();
        Stack<Character> stack=new Stack<>();

        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            if(!stack.isEmpty()&&stack.peek()=='('&&c==')'){
                stack.pop();
            }else if(c==')'||c=='('){
                stack.add(c);
            }
        }
        //Ex:
        //s = )))
        //s = (((
        return stack.size();
    }

    public static int minAddToMakeValid1(String s) {
        int n=s.length();
        int countOpenBracket=0;
        int countCloseBracket=0;

        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            if(countOpenBracket!=0&&c==')'){
                countOpenBracket--;
            }else if(s.charAt(i)=='('){
                countOpenBracket++;
            }else{
                countCloseBracket++;
            }
        }
        return countOpenBracket+countCloseBracket;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string, message, and a positive integer, limit.
        //- You must split message into (one or more parts based) on limit.
        //- Each resulting part should have the suffix "<a/b>",
        // where "b" is to be replaced with (the total number of parts) and "a" is to be replaced with (the index of the part),
        // starting from 1 and going up to b. Additionally, the length of each resulting part (including its suffix) should be equal to limit,
        // except for the last part whose length can be at most limit.
        //- The resulting parts should be formed such that when their suffixes are removed and they are all concatenated in order,
        // they should be equal to message. Also, the result should contain as few parts as possible.
        //* Return the parts message would be split into as an array of strings.
        // If it is impossible to split message as required, return an empty array.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 1000
        //s[i] is either '(' or ')'.
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: s = "())"
        //Output: 1
        //- Ta dùng stack là được.
        //
        //- Dùng counter ( và ) để tính
        //
        //1.1, Optimization
        //
        //
        //1.2, Complexity
        //- Space: O(n) -> O(1)
        //- Time: O(n)
        //
        String s = "(((";
        System.out.println(minAddToMakeValid(s));
        System.out.println(minAddToMakeValid1(s));
        //#Reference:
        //1417. Reformat The String
        //736. Parse Lisp Expression
        //1663. Smallest String With A Given Numeric Value
        //2616. Minimize the Maximum Difference of Pairs
        //2311. Longest Binary Subsequence Less Than or Equal to K
        //395. Longest Substring with At Least K Repeating Characters
    }
}
