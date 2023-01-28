package interviews.paypay;

import java.util.Stack;

public class StreamingExpressionAdvance {
    public int currentNumber=0;
    public Stack<Integer> stackNums;
    public Stack<Character> operators;
    public char berforeChar='+';

    public StreamingExpressionAdvance() {
        stackNums=new Stack<>();
        operators=new Stack<>();
    }

    /**
     * Adding character to current instance
     * @param c current character to be added
     * @return the information on whether this expression is valid or not
     * @Des : Additionally, it will print the current value of the expression when meeting character question mark(?).
     */
    public boolean addCharacter(char c)  {
        if(c==' '){
            return true;
        }
        if((berforeChar=='('&&(c=='+'||c=='-'||c=='*'||c=='/'))
                ||((berforeChar=='+'||berforeChar=='-'||berforeChar=='*'||berforeChar=='/')&&(c=='+'||c=='-'||c=='*'||c=='/'))){
            System.out.println("ERROR");
            return false;
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

    /**
     * Deleting all cache to re-use
     */
    public void resetCache(){
        currentNumber=0;
        if(stackNums!=null){
            stackNums.clear();
        }
        if(operators!=null){
            operators.clear();
        }
        berforeChar='+';
    }

    public int getCurrentResult(){
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
        int rs=newsStackNums.pop();
        newsStackNums.clear();
        newStackOps.clear();
        return rs;
    }

    //Testing
    public static void main(String[] args) {
        StreamingExpressionAdvance expStream=new StreamingExpressionAdvance();
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
//        String expression="(3-(5-(8)-(2+(9-(0-(8-(2))))-(4))-(4)))";
        String expression="(11*2)?*1+3?*7?++879?";
        boolean isGood;
        for(int i=0;i<expression.length();i++){
            isGood=expStream.addCharacter(expression.charAt(i));
            if(!isGood){
                break;
            }
        }
        //Clear cache to re-use instance
        expStream.resetCache();
        String expression1="(11*2)?";
        for(int i=0;i<expression1.length();i++){
            isGood=expStream.addCharacter(expression.charAt(i));
            if(!isGood){
                break;
            }
        }
    }
}
