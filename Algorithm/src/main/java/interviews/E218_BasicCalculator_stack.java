package interviews;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class E218_BasicCalculator_stack {

    public static int calculateWrongRedundant(String s) {
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
            while (i<n&&s.charAt(i)==' ') i++;
            //VD: a - b --> Làm thế này để biến đổi b --> số âm <0
            while (i<n&&s.charAt(i)>='0'&&s.charAt(i)<='9'){
                isNumber=true;
                currentValue=currentValue*10 + s.charAt(i)-'0';
                i++;
            }
            if(isCurrentNegative&&!isNumber){
                operatorStacks.add('-');
            }else if(isCurrentNegative){
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

                        //Case
                        //(3 - 9)
                        if(operator=='-'&&!isNumber){
                            prevNumber=prevNumber*-1;
//                            isNumber=true;
                        }else if(operator=='-'){
                            newValue=newValue*-1;
                            operator='+';
                        }
                        // -1 + 2 : Cần kiểm tra mark before number (-) --> x=x*-1
                        //--> Cần pop(-) và add(+) vào
                        if(!operatorStacks.isEmpty()&&operatorStacks.peekLast()=='-'){
                            prevNumber=prevNumber*-1;
                            operatorStacks.removeLast();
                            operatorStacks.addLast('+');
                        }
                        newValue=calculate(prevNumber, newValue, operator);
                    }
                    operatorStacks.removeLast();
                    i++;
                    while (i<n&&s.charAt(i)==' ')
                        i++;
                }
                numberStacks.add(newValue);
            }else if(isNumber){
                numberStacks.add(currentValue);
            }
            if(i<n){
                while (s.charAt(i)==' '){
                    i++;
                }
                if(s.charAt(i)!=')'){
                    operatorStacks.add(s.charAt(i));
                    i++;
                }
            }
        }
//        System.out.println(operatorStacks);
//        System.out.println(numberStacks);
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

    public static int calculate(String s) {
        Deque<String> stack=new LinkedList<>();
        s=s.replaceAll(" ","");
        int n=s.length();

        for(int i=0;i<n;i++){
            while (i<n&&s.charAt(i)==' ') i++;
            int currentValue=0;
            boolean isNumber=false;
            while (i<n&&s.charAt(i)>='0'&&s.charAt(i)<='9'){
                isNumber=true;
                currentValue=currentValue*10 + s.charAt(i)-'0';
                i++;
            }
            while (i<n&&s.charAt(i)==' ') i++;
            if(isNumber){

//                if(!stack.isEmpty()&&"-".equals(stack.peekLast())){
//                    stack.removeLast();
//                    if(!stack.isEmpty()&&isNumeric(stack.peekLast())){
//                        int prevNumber=Integer.parseInt(stack.removeLast());
//                        int newNumber=calculate(prevNumber, currentValue, '-');
//                        stack.add(String.valueOf(newNumber));
//                    }else if(stack.isEmpty()){
//                        stack.add(String.valueOf(currentValue*-1));
//                    }
//                }else if(!stack.isEmpty()&&"+".equals(stack.peekLast())){
//                    stack.removeLast();
//                    int prevNumber=Integer.parseInt(stack.removeLast());
//                    int newNumber=calculate(prevNumber, currentValue, '+');
//                    stack.add(String.valueOf(newNumber));
//                }else{
//                    stack.add(String.valueOf(currentValue));
//                }
                if(stack.size()>2&&isOperatorPriorityStr(stack.peekLast())){
                    String operator=stack.removeLast();
                    int number1=Integer.parseInt(stack.removeLast());
                    int number2=Integer.parseInt(stack.removeLast());
                    int newNumber=calculateStr(number2, number1, operator);

                    stack.addLast(String.valueOf(newNumber));
                }else if(!stack.isEmpty()&&"-".equals(stack.peekLast())){
                    stack.removeLast();
                    if(stack.isEmpty()){
                        stack.addLast("0");
                    }
                    stack.add("+");
                    stack.add(String.valueOf(currentValue*-1));
                }else{
                    stack.add(String.valueOf(currentValue));
                }
            }
//            List<String> list=new LinkedList(stack);
//            println(list);
            if(i>=n){
                break;
            }

            if(isOperatorPriority(s.charAt(i))){
//                String operator=s.charAt(i)+"";
//                i++;
//                currentValue=0;
//                while (i<n&&s.charAt(i)>='0'&&s.charAt(i)<='9'){
//                    currentValue=currentValue*10 + s.charAt(i)-'0';
//                    i++;
//                }
//                i--;
//                int prevNumber=Integer.parseInt(stack.removeLast());
//                int newNumber=calculateStr(prevNumber, currentValue, operator);
//                stack.addLast(String.valueOf(newNumber));
                stack.addLast(s.charAt(i)+"");
            }else if(isOperator(s.charAt(i))){
                stack.addLast(s.charAt(i)+"");
            }else if(s.charAt(i)=='('){
                stack.addLast(s.charAt(i)+"");
            }else if(s.charAt(i)==')'){
                int currentNumber=Integer.parseInt(stack.removeLast());

                while (stack.size()>1&&!"(".equals(stack.peekLast())){
                    String operator=stack.removeLast();
                    int preNumber=0;
                    if(!stack.isEmpty()&&!"(".equals(stack.peekLast())&&!isOperatorStr(stack.peekLast())){
                        preNumber=Integer.parseInt(stack.removeLast());
                    }else if("-".equals(operator)){
                        currentNumber=currentNumber*-1;
                        operator="+";
                    }

                    currentNumber=calculateStr(preNumber, currentNumber, operator);
                }
                if(!stack.isEmpty()){
                    stack.removeLast();
                }
                if(!stack.isEmpty()&&"-".equals(stack.peekLast())){
                    stack.removeLast();
                    if(stack.isEmpty()){
                        stack.addLast("0");
                    }
                    stack.addLast("+");
                    stack.addLast(String.valueOf(currentNumber*-1));
                }else{
                    stack.addLast(String.valueOf(currentNumber));
                }
            }
        }
        while (stack.size()>2){
            int num1=Integer.parseInt(stack.removeFirst());
            String operator=stack.removeFirst();
            int num2=Integer.parseInt(stack.removeFirst());
            int newNumer=calculateStr(num1, num2, operator);

            stack.addFirst(String.valueOf(newNumer));
        }
        if(stack!=null&&stack.size()>1&&"-".equals(stack.removeFirst())){
            return -1* (Integer.parseInt(stack.peekFirst()));
        }else{
            return Integer.parseInt(stack.peekFirst());
        }
    }

    public static boolean isNumeric(String s){
        return s.chars().allMatch( Character::isDigit );
    }

    public static void println(List<String> list){
        for(String c: list){
            if(isOperatorStr(c)){
                System.out.printf("%s ", c);
            }else{
                System.out.printf("%s ", c);
            }
        }
        System.out.println();
    }

    public static boolean isOperator(char c){
        return c=='+'||c=='-'||c=='*'||c=='/';
    }

    public static boolean isOperatorStr(String c){
        return "+".equals(c)||"-".equals(c)||"*".equals(c)||"/".equals(c);
    }

    public static boolean isOperatorPriority(char c){
        return c=='*'||c=='/';
    }

    public static boolean isOperatorPriorityStr(String c){
        return "*".equals(c)||"/".equals(c);
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

    public static int calculateStr(int number1, int number2, String operator){
        if("*".equals(operator)){
            return number1*number2;
        }
        if("/".equals(operator)){
            return number1/number2;
        }
        if("+".equals(operator)){
            return number1+number2;
        }
        if("-".equals(operator)){
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
//        String expression="2-4-(8+2-6+(8+4-(1)+8-10))";
//        String expression="(8+2-6+(8+4-(1)+8-10))";
//        String expression="(8+4-(1)+8-10)";
//        String expression="- (3 - (- (4 + 5) ) )";
        String expression="- 2  +1";
//        String expression="-(3+4)+5";
        System.out.println(calculate(expression));
        //
        //** Đề bài
        //- Tính gía trị của biểu thức thỏa mãn:
        //- Có ngoặc
        //- Cộng các số có nhiều chữ số
        //- Có thể có dạng -(2+3)
        //- Không thể dùng dạng (+)
        //
        //** Bài này tư duy như sau:
        //0,
        //0.1, Các tư duy sai lệch
        //- Dùng 2 stack 1 cái chưa số, 1 cái chứa dấu
        //+ Khó kiểm soát order giữa các numbers và dấu (+/-/*/ / )
        //+ Nếu dùng 2 stack riêng thì --> Không thể biết được (dấu nào) gắn với (số nào) một cách dễ dàng được
        //==> Chủ yếu
        //VD  3-4+(5+6)
        //stack operators: 3,4,5,6
        //stack numbers : -,+,+
        //--> Nếu muốn biết sự tương tự ---> Cần phải dùng index khá khó.
        //* Thêm nữa nếu muốn xác định số âm thì không xác định được:
        //VD:
        //2-3+(-5+6)
        //===> Phần chỗ -5 ==> Không xác định được trước (-) có number hay không
        //+ Check number_stack.peek() cũng không được (vẫn là số), check number_operator cũng không được --> Có thể là ký tự sau đó (
        //==> Dù operator_stack.peek() == '(' : Thì vẫn vậy thôi
        //VD: //2-3+(7-5+6) : Ở đây thì operator_stack.peek() == '(' vẫn thế
        //---> Khi dùng stack operators, numbers riêng
        //
        //0.2, Các lưu ý:
        //- Thường ở dạng bài này check s[i]==')' : Thì ta mới xử lý
        //- Với các case s[i]=' ' :
        //+ Đơn giản nhất là remove từ ban đầu : Clear code
        //+ Continue ở mọi lúc cứ khi nào i++ : Cần phải thêm 1 đoạn while(i<n&&s[i]==' ') i++;
        //
        //- Các dạng bài này thường ta sẽ cố biến đổi về 1 expression có thể tính được chỉ toàn phép (+/-)
        //==> Tính bằng cách only loop
        //- Ta chỉ xử lý với các phép */ / ==> Cần pop giá trị cũ ra --> Tính lần lượt + check (size>1)
        //
        //0.3, Vì xử lý đối với các expression dạng ( - A - B )
        //==> Vì ta tính từ cuối --> Nên ta sẽ tính sai
        //VD:
        // C - A - B ==> Ta tính thì sẽ bị sai
        //+ Vì bình thường ta sẽ tính A-B thay vì - A - B ==> Sai.
        //** : Cách tốt nhất là biến đổi về dạng expression : +/- ==> Tính sau.
        //
        //0.4, Với nhưng case dạng
        //VD: 2+3+(4-2+5)
        //- Thì ta sẽ biến đổi thành check xem đằng trước của nó có số hay không --> Đổi dấu (*-1)
        //--> 2+3 + (4 + -2 + 5) ==> Bằng cách đảo dấu các số
        //** ==> Xong CẦN TÍNH để bỏ dấu ( )
        //
        //0.5, Đến cuối cùng thì tính bình thường thôi --> loop để ra kết quả
        //

    }

}
