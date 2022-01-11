/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_hard;

import java.util.Stack;

/**
 *
 * @author chinhvu
 */
public class LongestValidParentheses_hard_2 {
    
    public static int longestValidParentheses(String s) {
        Stack<Character> stack=new Stack<Character>();
        int rs=0;
        
        for(int i=0;i<s.length();i++){
            Character c=s.charAt(i);
//            if(c=='('){
//                stack.add(c);
//            }
            if(c==')'
                    &&!stack.isEmpty()
                    &&stack.peek()=='('){
                stack.pop();
                rs++;
                continue;
            }
            stack.add(s.charAt(i));
        }
        return rs*2;
    }
    
    public static void main(String[] args) {
        System.out.println(longestValidParentheses(")()())"));
    }
}
