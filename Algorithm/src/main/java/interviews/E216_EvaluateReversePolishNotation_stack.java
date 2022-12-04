package interviews;

import java.util.Deque;
import java.util.LinkedList;

public class E216_EvaluateReversePolishNotation_stack {

    public static int evalRPN(String[] tokens) {
        Deque<String> stack=new LinkedList<>();
        int n=tokens.length;
        stack.addLast(tokens[0]);

        for(int i=1;i<n;i++){
            if(isOperator(tokens[i])){
                String number=stack.removeLast();
                String number1=stack.removeLast();
                String newValue=String.valueOf(calculateNumbers(number1, number, tokens[i]));

                stack.addLast(newValue);
            }else{
                stack.add(tokens[i]);
            }
        }
        return Integer.parseInt(stack.peekLast());
    }

    public static int calculateNumbers(String s, String s1, String operator){
        if("*".equals(operator)){
            return Integer.parseInt(s)*Integer.parseInt(s1);
        }
        if("/".equals(operator)){
            return Integer.parseInt(s) / Integer.parseInt(s1);
        }
        if("+".equals(operator)){
            return Integer.parseInt(s) + Integer.parseInt(s1);
        }
        if("-".equals(operator)){
            return Integer.parseInt(s) - Integer.parseInt(s1);
        }
        return Integer.parseInt(s)/Integer.parseInt(s1);
    }

    public static int evalRPNOptimize(String[] tokens) {
        Deque<Integer> stack=new LinkedList<>();
        int n=tokens.length;
        stack.addLast(Integer.parseInt(tokens[0]));

        for(int i=1;i<n;i++){
            if(isOperator(tokens[i])){
                Integer number=stack.removeLast();
                Integer number1=stack.removeLast();
                Integer newValue=calculateNumbers(number1, number, tokens[i]);

                stack.addLast(newValue);
            }else{
                stack.add(Integer.parseInt(tokens[i]));
            }
        }
        return stack.peekLast();
    }

    public static int calculateNumbers(int s, int s1, String operator){
        if("*".equals(operator)){
            return s*s1;
        }
        if("/".equals(operator)){
            return s/s1;
        }
        if("+".equals(operator)){
            return s+s1;
        }
        if("-".equals(operator)){
            return s-s1;
        }
        return s+s1;
    }

    public static boolean isOperator(String c){
        return "+".equals(c)||"-".equals(c)||"*".equals(c)||"/".equals(c);
    }

    public static void main(String[] args) {
//        String[]tokens=new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        String[]tokens=new String[]{"2","1","+","3","*"};
//        String[]tokens=new String[]{"4","13","5","/","+"};
        System.out.println(evalRPN(tokens));
        System.out.println(evalRPNOptimize(tokens));
        //
        //
        //
        //
        //
        //## Reference
        //- Basic Calculator
        //- Expression Add Operators
    }
}
