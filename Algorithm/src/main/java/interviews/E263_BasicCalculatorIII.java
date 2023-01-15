package interviews;

import java.util.Stack;

public class E263_BasicCalculatorIII {
    public static int calculate(String s) {
        Stack<Integer> stackNums=new Stack<>();
        Stack<Character> operators=new Stack<>();
        int n=s.length();
        int currentNumber=0;

        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            if(c==' '){
                continue;
            }
            if(c>='0'&&c<='9'){
                currentNumber=c-'0';
                while (i<n-1&&Character.isDigit(s.charAt(i+1))){
                    currentNumber=currentNumber*10+(s.charAt(i+1)-'0');
                    i++;
                }
                stackNums.add(currentNumber);
            }else if(c=='('){
                operators.add(c);
            }else if(c==')'){
                while (operators.peek()!='('){
                    stackNums.add(calulation(stackNums.pop(), stackNums.pop(), operators.pop()));
                }
                operators.pop();
            }else if(c == '+' || c == '-' || c == '*' || c == '/'){
                while (!operators.isEmpty()&&isSameType(c, operators.peek())){
                    //c=='+'/ '-' thì ta có thể rút gọn phép toán trước đó được
                    stackNums.add(calulation(stackNums.pop(), stackNums.pop(), operators.pop()));
                }
                operators.add(c);
            }
        }
        while (!operators.isEmpty()){
            stackNums.add(calulation(stackNums.pop(), stackNums.pop(), operators.pop()));
        }
        return stackNums.pop();
    }

    public static boolean isSameType(char c1, char c2){
        if(c2=='('||c2==')'){
            return false;
        }
        if((c1=='*'||c1=='/')&&(c2=='+'||c2=='-')){
            return false;
        }
        return true;
    }

    private static int calulation(int num1, int num2, char op) {
        switch (op) {
            case '+': return num2 + num1;
            case '-': return num2 - num1;
            case '*': return num2 * num1;
            case '/': return num2 / num1;
        }
        return 0;
    }
    private static boolean precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')'){
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
//        String expression="(6+2 * ( 7 + 2*(5+6) + 6 )";
//        String expression="6+2 * ( 7 + 2*(5+6) + 6 )";
//        String expression="6+2";
//        String expression="2*(5+5*2)/3+(6/2+8)";
//        String expression="(2+(9-(0-(8-(2))))-(4))";
//        String expression="5-(8)-(2+(9-(0-(8-(2))))-(4))-(4)";
//        String expression="(5-(8)-(2+(9-(0-(8-(2))))-(4)))";
//        String expression="(3-(5-(8)-(2+(9-(0-(8-(2))))-(4))-(4)))";
//        String expression="0-2147483648";
        String expression="1+(11*21+3)*7+879";
//        (5-(8)-(2+(9-(0-(8-(2))))-(4)))
//        (2+(9-(0-(8-(2))))-(4))
        System.out.println(calculate(expression));
    }
}
