
package interviews.paypay;

import java.util.Stack;

public class StreamingExpressionAdvanceLeetcode {

    //Giá trị của số trước đó/ của toàn bộ expression trước đó
    public int currentNumber=0;
    public Stack<Integer> stackNums;
    public Stack<Character> operators;
    public char berforeChar='+';

    public StreamingExpressionAdvanceLeetcode() {
        stackNums=new Stack<>();
        operators=new Stack<>();
    }

    public boolean addCharacter(char c)  {
        if(c==' '){
            return true;
        }
        if(c=='?'){
            Stack<Character> newStackOps=new Stack<>();
            newStackOps.addAll(operators);
            Stack<Integer> newsStackNums=new Stack<>();
            newsStackNums.addAll(stackNums);
            if(berforeChar!=')'){
                newsStackNums.add(currentNumber);
            }
            while (!newStackOps.isEmpty()){
                newsStackNums.add(calulation(newsStackNums.pop(), newsStackNums.pop(), newStackOps.pop()));
            }
            System.out.println(newsStackNums.pop());
            newsStackNums.clear();
            newStackOps.clear();
            return true;
        }else if(c>='0'&&c<='9'){
            currentNumber=currentNumber*10+(c-'0');
        }else if(c=='('){
            operators.add(c);
        }else if(c==')'){
            if(berforeChar!=')'){
                stackNums.add(currentNumber);
                currentNumber=0;
            }
            while (operators.peek()!='('){
                stackNums.add(calulation(stackNums.pop(), stackNums.pop(), operators.pop()));
            }
            operators.pop();
        }else if(c == '+' || c == '-' || c == '*' || c == '/'){
            if(berforeChar!=')'){
                stackNums.add(currentNumber);
//                System.out.println(currentNumber);
                currentNumber=0;
            }
            while (!operators.isEmpty()&&isSameType(c, operators.peek())){
                //c=='+'/ '-' thì ta có thể rút gọn phép toán trước đó được
                stackNums.add(calulation(stackNums.pop(), stackNums.pop(), operators.pop()));
            }
            operators.add(c);
        }
        berforeChar=c;
        return true;
    }

    public int getCurrentResult(){
        if(berforeChar!=')'){
            stackNums.add(currentNumber);
        }
        while (!operators.isEmpty()){
            stackNums.add(calulation(stackNums.pop(), stackNums.pop(), operators.pop()));
        }
        int result=stackNums.pop();
        stackNums.clear();
        operators.clear();
        return result;
    }

    public boolean isSameType(char c1, char c2){
        if(c2=='('||c2==')'){
            return false;
        }
        if((c1=='*'||c1=='/')&&(c2=='+'||c2=='-')){
            return false;
        }
        return true;
    }

    private int calulation(int num1, int num2, char op) {
        switch (op) {
            case '+': return num2 + num1;
            case '-': return num2 - num1;
            case '*': return num2 * num1;
            case '/': return num2 / num1;
        }
        return 0;
    }

    public static int calculate(String s) {
        StreamingExpressionAdvanceLeetcode expressAdleetCode=new StreamingExpressionAdvanceLeetcode();

        for(int i=0;i<s.length();i++){
            expressAdleetCode.addCharacter(s.charAt(i));
        }
        return expressAdleetCode.getCurrentResult();
    }

    public static void main(String[] args) {
        StreamingExpressionAdvanceLeetcode expStream=new StreamingExpressionAdvanceLeetcode();
//        String expression="2+3*3?";
//        String expression="2+3/3?";
//        String expression="2+3/3*5?";
//        String expression="2+3/3*5+4?";
//        String expression="2+22*6/2?";
//        String expression="2423 + 2+22*6/2?";
//        String expression="2423 + 2+22*6/2 + 332 / 2?";
//        String expression="11 * 21? + 3 * 7?";
//        String expression="12-3?*4?";
//        String expression="2423 + 2+22*6/2? + 332 / 2 - 23?";
//        String expression="1+(11*21+3)?*7+879?";
//        String expression="3 * ( 2 + 5 )+ ?";
//        String expression="3 * ( 2 + 5 )++ ?";
        String expression="(3-(5-(8)-(2+(9-(0-(8-(2))))-(4))-(4)))";
//        String expression="(3-(5))";
        boolean isGood;
        for(int i=0;i<expression.length();i++){
            isGood=expStream.addCharacter(expression.charAt(i));
            if(!isGood){
                return;
            }
        }
        System.out.println(expStream.getCurrentResult());
    }
}
