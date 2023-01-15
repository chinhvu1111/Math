package interviews;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class E263_BasicCalculatorIII_Refer {
    public static int calculate(String s) {
        // Write your code here
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' '){
                continue;
            }
            if (Character.isDigit(c)) {
                num = c - '0';
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                nums.push(num);
                num = 0;
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '('){
                    nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                }
                ops.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && precedence(c, ops.peek())){
                    nums.push(operation(ops.pop(), nums.pop(),nums.pop()));
                }
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    private static int operation(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
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
        String expression="(3-(5-(8)-(2+(9-(0-(8-(2))))-(4))-(4)))";
//        (5-(8)-(2+(9-(0-(8-(2))))-(4)))
//        (2+(9-(0-(8-(2))))-(4))
        System.out.println(calculate(expression));
    }
}
