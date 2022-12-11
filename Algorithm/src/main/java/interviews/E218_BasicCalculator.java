package interviews;

import java.util.Deque;
import java.util.LinkedList;

public class E218_BasicCalculator {

    public static int calculate(String s) {
        Deque<Character> operatorStacks=new LinkedList<>();
        Deque<Integer> numberStacks=new LinkedList<>();
        int i=0;
        s=s.trim();
        int n=s.length();

        if(s.charAt(0)=='-'){
            numberStacks.add(0);
        }

        for(i=0;i<n;){
            if(s.charAt(i)==' '){
                i++;
                continue;
            }
            int currentValue=0;
            boolean isNumber=false;
            boolean isCurrentNegative=false;

            if(s.charAt(i)=='-'&&(!operatorStacks.isEmpty()&&operatorStacks.peekLast()=='(')){
                isCurrentNegative=true;
                i++;
            }
            while (i<n&&s.charAt(i)>='0'&&s.charAt(i)<='9'){
                isNumber=true;
                currentValue=currentValue*10 + s.charAt(i)-'0';
                i++;
            }
            if(isCurrentNegative){
                currentValue=currentValue*-1;
            }
            while (i<n&&s.charAt(i)==' ') i++;
            int newValue=currentValue;
            if(i<n&&s.charAt(i)==')'){

                //2-4-(8+2-6+(8+4-(1)+8-10))
                while (i<n&&s.charAt(i)==')'){
                    while (!operatorStacks.isEmpty()&&operatorStacks.peekLast()!='('){
                        int prevNumber=numberStacks.removeLast();
                        Character operator=operatorStacks.removeLast();

                        if(operator=='-'){
                            newValue=newValue*-1;
                            operator='+';
                        }
                        if(!operatorStacks.isEmpty()&&operatorStacks.peekLast()=='-'){
                            prevNumber=prevNumber*-1;
                        }
                        newValue=calculate(prevNumber, newValue, operator);
                    }
                    operatorStacks.removeLast();
                    i++;
                }
                numberStacks.add(newValue);
            }else if(isNumber){
                numberStacks.add(currentValue);
            }
            if(i<n){
                operatorStacks.add(s.charAt(i));
                i++;
            }
        }
        System.out.println(operatorStacks);
        System.out.println(numberStacks);
        if(numberStacks.size()==1){
            return numberStacks.peek();
        }
        while (!operatorStacks.isEmpty()){
            int firstNumber=numberStacks.removeFirst();
            int secondNumber=numberStacks.removeFirst();
            char operator=operatorStacks.removeFirst();
            int newNumber=calculate(firstNumber, secondNumber, operator);
            numberStacks.addFirst(newNumber);
        }
        return numberStacks.peek();
    }

    public static boolean isOperator(char c){
        return c=='+'||c=='-'||c=='*'||c=='/';
    }

    public static int calculate(int number1, int number2, char operator){
        if(operator=='*'){
            return number1*number2;
        }
        if(operator=='/'){
            return number1/number2;
        }
        if(operator=='+'){
            return number1+number2;
        }
        if(operator=='-'){
            return number1-number2;
        }
        return 0;
    }

    public static void main(String[] args) {
//        String expression="(1+(4+5+2)-3)+(6+8)";
//        String expression="(1+(4+5+2)-3)*15/(6+8)";
//        String expression="1 + 1";
//        String expression=" 2-1 + 2 ";
//        String expression="  (3 ) ";
        //Case : Liên quan đến dấu đằng trước số
//        String expression="1-(  5   -2)";
//        String expression="-(   2  -5)";
//        String expression="-2+ 1";
//        String expression="- (3 + (4 + 5))";
        String expression="2-4-(8+2-6+(8+4-(1)+8-10))";
        System.out.println(calculate(expression));
    }

}
