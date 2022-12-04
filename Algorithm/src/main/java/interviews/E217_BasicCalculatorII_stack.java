package interviews;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class E217_BasicCalculatorII_stack {

    public static int calculate(String s) {
        Deque<String> stack=new LinkedList<>();
//        s=s.replaceAll("\\s*", "");
        int n=s.length();

        StringBuilder currentElement=new StringBuilder();

        for(int i=0;i<n;i++){
            if(s.charAt(i)==' '){
                continue;
            }
            if(isOperator(s.charAt(i))){
                if(!stack.isEmpty()){
                    if(isOperatorPriority(stack.peekLast())){
                        String prevOperator=stack.removeLast();
                        String prevNumber=stack.removeLast();
                        String newNumber = String.valueOf(calculateNumbers(prevNumber, currentElement.toString(), prevOperator));

                        stack.add(newNumber);
                    }else{
                        stack.add(currentElement.toString());
                    }
                }else{
                    stack.add(currentElement.toString());
                }
                stack.add(String.valueOf(s.charAt(i)));
                currentElement=new StringBuilder();
            }else{
                currentElement.append(s.charAt(i));
            }
//            System.out.println(currentElement.toString());
        }
        if(!stack.isEmpty()){
            String prevOperator=stack.peekLast();

            if(isOperatorPriority(prevOperator)){
                stack.removeLast();
                String prevNumber=stack.removeLast();
                String newNumber = String.valueOf(calculateNumbers(prevNumber, currentElement.toString(), prevOperator));

                stack.add(newNumber);
            }else{
                stack.add(currentElement.toString());
            }
        }else{
            stack.add(currentElement.toString());
        }

        while (stack.size()>1){
            String number1=stack.removeFirst();
            String operator=stack.removeFirst();
            String number2=stack.removeFirst();
            int newNumber=calculateNumbers(number1, number2, operator);
            stack.addFirst(String.valueOf(newNumber));
        }
        return Integer.parseInt(stack.peekLast());
    }

    public static int calculateNumbers(String s, String s1, char operator){
        if('*'==operator){
            return Integer.parseInt(s)*Integer.parseInt(s1);
        }
        if('/'==operator){
            return Integer.parseInt(s) / Integer.parseInt(s1);
        }
        if('+'==operator){
            return Integer.parseInt(s) + Integer.parseInt(s1);
        }
        if('-'==operator){
            return Integer.parseInt(s) - Integer.parseInt(s1);
        }
        return Integer.parseInt(s)/Integer.parseInt(s1);
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

    public static int calculateNumbers(char c, char c1, char operator){
        if(operator=='/'){
            return (c-'0')/(c1-'0');
        }
        if(operator=='*'){
            return (c-'0')*(c1-'0');
        }
        if(operator=='+'){
            return (c-'0')+(c1-'0');
        }
        if(operator=='-'){
            return (c-'0')-(c1-'0');
        }
        return 0;
    }

    public static boolean isOperatorPriority(char c){
        return c=='/'||c=='*';
    }

    public static boolean isOperatorPriority(String c){
        return "/".equals(c)||"*".equals(c);
    }

    public static boolean isOperator(char c){
        return c=='/'||c=='*'||c=='+'||c=='-';
    }

    public static void main(String[] args) {
//        String s="23+2*2";
//        String s="3+5 /2";
//        String s="3/2 ";
//        String s="34-2";
//        String s="1-2-1";
        String s="14/3*2";
        System.out.println(calculate(s));
    }
}
