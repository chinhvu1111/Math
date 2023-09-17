package E1_stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class E2_RemovingStarsFromAString {

    public String removeStars(String s) {
        Deque<Character> stack=new LinkedList<>();
        int n=s.length();

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='*'){
                if(stack.isEmpty()){
                    stack.add('*');
                    continue;
                }
                if(stack.peekLast() >= 'a' && stack.peekLast() <= 'z'){
                    stack.removeLast();
                }
            }else{
                stack.addLast(s.charAt(i));
            }
        }
        StringBuilder rs=new StringBuilder();

        while(!stack.isEmpty()){
            rs.append(stack.removeFirst());
        }
        return rs.toString();
    }
    public static void main(String[] args) {
        //#Reference:
        //2085. Count Common Words With One Occurrence
        //482. License Key Formatting
        //1700. Number of Students Unable to Eat Lunch
    }
}
