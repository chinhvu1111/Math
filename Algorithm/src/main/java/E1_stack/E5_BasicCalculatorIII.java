package E1_stack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class E5_BasicCalculatorIII {

    public static int calculate(String s) {
        Stack<String> stack=new Stack<>();
        int n=s.length();

        //Ex:
        //2*(5+5*2)/3+(6/2+8)
        for(int i=0;i<n;i++){
//            System.out.println(stack);
            int prevIndex=i;
            int curNumber=0;

            while(i<n&&isNumber(s.charAt(i))){
                curNumber=curNumber*10+(s.charAt(i)-'0');
                i++;
            }
            //prevIndex!=i
            //
            if(!stack.isEmpty()&&prevIndex!=i&&(stack.peek().equals("*")||stack.peek().equals("/"))){
                String prevOperator=stack.pop();
                String prevNum=stack.pop();
                int newNum=0;

                if(prevOperator.equals("*")){
                    newNum=Integer.parseInt(prevNum)*curNumber;
                }else{
                    newNum=Integer.parseInt(prevNum)/curNumber;
                }
                //add(10)
                stack.add(String.valueOf(newNum));
            }else if(prevIndex!=i){
                //add(2)
                //add(5)
                //add(5)
                stack.add(String.valueOf(curNumber));
            }
            //add('*')
            //add('(')
            //add('+')
            //add('*')
            if(i<n){
                if(s.charAt(i)!=')'){
                    stack.add(String.valueOf(s.charAt(i)));
                }else{
                    int newValue;

                    //1-2+3-5
                    //1-2+-2
                    //=> Nếu tư duy ngược thì sẽ bị sai
                    //==> Chuyển hết thành + đi
                    if(!stack.isEmpty()&&!stack.peek().equals("(")){
                        //5
                        newValue= Integer.parseInt(stack.pop());
                        if(!stack.isEmpty()&&!stack.peek().equals("(")&&!stack.pop().equals("+")){
                            newValue=newValue*-1;
                        }
                        //newValue=-5
                    }else{
                        //()
                        //empty
                        continue;
                    }
                    while(!stack.isEmpty()&&!stack.peek().equals("(")){
                        //3,2,1
//                        System.out.printf("Prev: %s, i: %s, curNum: %s, newValue: %s\n",prevIndex, i, curNumber, newValue);
                        int prevVal= Integer.parseInt(stack.pop());
                        //+,-,empty->"+"
                        //Default val="+"
                        String curOperator="+";

                        if(!stack.isEmpty()&&!stack.peek().equals("(")){
                            curOperator=stack.pop();
                        }
                        if(curOperator.equals("+")){
                            //3-5=-2
                            //-2+-2=-4
                            //1+-4=-3
                            newValue=prevVal+newValue;
                        }else{
                            newValue=prevVal*-1+newValue;
                        }
                    }
                    //pop('(')
                    if(!stack.isEmpty()&&stack.peek().equals("(")){
                        stack.pop();
                        if(!stack.isEmpty()&&(stack.peek().equals("*")||stack.peek().equals("/"))){
                            String op=stack.pop();
                            String prevVal=stack.pop();
                            if(op.equals("*")){
                                newValue=Integer.parseInt(prevVal)*newValue;
                            }else{
                                newValue=Integer.parseInt(prevVal)/newValue;
                            }
                        }
                    }
                    //-3
                    stack.add(String.valueOf(newValue));
                }
            }
//            if(prevIndex!=i){
//                i--;
//            }
        }
        if(stack.isEmpty()){
           return 0;
        }
        int newValue= Integer.parseInt(stack.pop());
        if(!stack.isEmpty()&&!stack.peek().equals("(")&&!stack.pop().equals("+")){
            newValue=newValue*-1;
        }
        while(!stack.isEmpty()&&!stack.peek().equals("(")){
            //3,2,1
//            System.out.printf("Prev: %s, i: %s, curNum: %s, newValue: %s\n",prevIndex, i, curNumber, newValue);
            int prevVal= Integer.parseInt(stack.pop());
            //+,-,empty->"+"
            //Default val="+"
            String curOperator="+";

            if(!stack.isEmpty()&&!stack.peek().equals("(")){
                curOperator=stack.pop();
            }
            if(curOperator.equals("+")){
                //3-5=-2
                //-2+-2=-4
                //1+-4=-3
                newValue=prevVal+newValue;
            }else{
                newValue=prevVal*-1+newValue;
            }
        }
//        System.out.println(stack);
        return newValue;
    }

    public static boolean isNumber(char c){
        return c>='0'&&c<='9';
    }

    public static int calculateRefactor(String s) {
        Stack<String> stack=new Stack<>();
        int n=s.length();
        char sign='+';
        int prevVal=0;
        int rs=0;

        //2 + 3 * (4 + 3 - 6 / 2)
        //ch=2: prev=0, sign=+, new_prev=2, rs=0
        //ch=+: prev=2, sign=+, new_prev=2, rs=
        //
        //2*(5+5*2)/3+(6/2+8)
        //ch=2: prev=0, (sign=+), new_prev=2, rs=0, stack=[]
        //ch=*: prev=2, (sign=+), new_prev=2, rs=rs+new_prev=2, , stack=[]
        //ch=(: prev=2, (sign=*), new_prev=2, rs=2, , stack=[2, *, ]
        //
        //2 : prev operator=+
        //* : prev operator=+
        //- Nếu cùng 1 loop
        //( : prev operator=+
        //1+2*(5+5*2)/3+(6/2+8)
        for(int i=0;i<n;i++){
            System.out.printf("cur char: %s, Cur stack: %s, sign: %s, prevVal: %s, rs: %s\n", s.charAt(i), stack, sign, prevVal, rs);
            int newNumber=0;

            while(i<n&&Character.isDigit(s.charAt(i))){
                newNumber=newNumber*10+s.charAt(i)-'0';
                i++;
            }
            if(sign=='+'){
//                rs+=prevVal;
                prevVal=newNumber;
                stack.add(String.valueOf(prevVal));
            }else if(sign=='-'){
//                rs-=prevVal;
                prevVal=-1*newNumber;
                stack.add(String.valueOf(prevVal));
            }else if(sign=='*'){
                prevVal=Integer.parseInt(stack.pop())*newNumber;
                stack.add(String.valueOf(prevVal));
            }else if(sign=='/'){
                prevVal=Integer.parseInt(stack.pop())/newNumber;
                stack.add(String.valueOf(prevVal));
            }
            char curSign = '+';
            if(i<n&&s.charAt(i)!=')'){
                curSign=s.charAt(i);
            }else if(i<n){
                int newNum=0;

                while(!stack.isEmpty()&&!stack.peek().equals("(")){
                    newNum+=Integer.parseInt(stack.pop());
                }
                if(!stack.isEmpty()){
                    sign=stack.pop().charAt(0);
                }
                if(!stack.isEmpty()){
                    sign=stack.pop().charAt(0);
                    if(sign=='*'){
                        newNum=newNum*Integer.parseInt(stack.pop());
                        stack.add(String.valueOf(newNum));
                    }else if(sign=='/'){
                        newNum=Integer.parseInt(stack.pop())/newNum;
                        stack.add(String.valueOf(newNum));
                    }else if(sign=='+'){
                        newNum=newNum+Integer.parseInt(stack.pop());
                        stack.add(String.valueOf(newNum));
                    }else if(sign=='-'){
                        newNum=newNum-Integer.parseInt(stack.pop());
                        stack.add(String.valueOf(newNum));
                    }
                }else stack.add(String.valueOf(newNum));
                continue;
            }
            sign=curSign;
            if(i+1<n&&s.charAt(i+1)=='('){
//                rs+=prevVal;
//                stack.add(String.valueOf(rs));
                stack.add(String.valueOf(sign));
                stack.add("(");
                prevVal=0;
//                rs=0;
                sign='+';
                i++;
            }
//            else if(i+1<n&&s.charAt(i+1)==')'){
//                int newNum=0;
//
//                while(!stack.isEmpty()&&stack.peek().equals("(")){
//                    newNum+=Integer.parseInt(stack.pop());
//                }
//                if(!stack.isEmpty()){
//                    sign=stack.pop().charAt(0);
//                }
//                stack.add(String.valueOf(newNum));
//                i++;
//                continue;
//            }
        }
        System.out.println(stack);
        return 1;
    }

    public static String evaluate(String first, char operator, String second){
        int x=Integer.parseInt(first);
        int y=Integer.parseInt(second);
        int res=0;

        if(operator=='+'){
            res=x;
        }else if(operator=='-'){
            res=-x;
        }else if(operator=='*'){
            res=x*y;
        }else{
            res=x/y;
        }
        return String.valueOf(res);
    }

    public static int calculateReference(String s){
        Stack<String> stack=new Stack<>();
        String curr="";
        char prevOp='+';
        s+="@";
        Set<String> operators=new HashSet<>(Arrays.asList("+", "-", "*", "/"));

        for(char c: s.toCharArray()){
            if(Character.isDigit(c)){
                curr+=c;
            }else if(c=='('){
                stack.add(""+prevOp);
                prevOp='+';
            }else{
                if(prevOp=='*'||prevOp=='/'){
                    stack.add(evaluate(stack.pop(), prevOp, curr));
                }else{
                    stack.add(evaluate(curr, prevOp, "0"));
                }
                curr="";
                prevOp=c;
                if(c==')'){
                    int currentTerm=0;
                    while(!operators.contains(stack.peek())){
                        currentTerm+=Integer.parseInt(stack.pop());
                    }
                    curr=Integer.toString(currentTerm);
                    prevOp=stack.pop().charAt(0);
                }
            }
        }
        int rs=0;

        while(!stack.isEmpty()){
            rs+=Integer.parseInt(stack.pop());
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given expression string contains only non-negative integers and ('+', '-', '*', '/' operators) and open '(' and closing parentheses ')'.
        //- The integer division should truncate toward Zero.
        //- The expression is always valid. All intermediate results will be in range [-2^31, 2^31 - 1]
        //* Return the result
        //
        //** Idea
        //* Method 1:
        //1.
        //1.0,
        //- Constraint
        //1 <= s <= 10^4
        //s consists of digits, '+', '-', '*', '/', '(', and ')'.
        //s is a valid expression.
        //
        //- Brainstorm
        //- Thứ tự ưu tiên operators:
        //  + (,),*,/,+,-
        //Ex:
        //2*(5+5*2)/3+(6/2+8)
        //->
        //stack: 2,*,(5,+,5*2
        //stack: 2,*,(5,+,10)
        //stack: 2,*,15
        //stack: 30,+,(6,/,2
        //stack: 2,*,15,+,(6,/,2
        //stack: 2,*,15,+,(3,+,8)
        //stack: 2,*,15,+,11
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the length of the expression
        //- Space : O(N)
        //- Time : O(N)
        //
        //2.
        //2.0,
        //- Ta cần refer basic calculator II để lấy thông tin
        //- Ở đây ta sẽ không phân biệt sign +/- ==> Ta quy thành các số +/-
        //Ex:
        //2 + 3 * (4 + 3 - 6 / 2)
        //- Mỗi expression được đặt trong () sẽ là isolated expression
        //==> Ta sẽ tính nó 1 cách riêng rẽ ==> Theo từng cặp ()
        //- Các value cũ sẽ được push vào stack để tính sau
        //- Vẫn sẽ là tư duy giống bài basic calculate II + chỉ khác ở chỗ là deal with ()
        //
        //- Tư duy bài dạng này phải thật clear theo pattern:
        //Ex: number(+/-,*,/) ==> Lấy 1 số 1 operator trong 1 loop + Xét các previous sign và value
        //
        //- Nếu muốn làm như kiểu trên --> Không có dấu () thì được vì:
        //+ Các biểu thức sẽ được add theo quy luật loop rõ ràng
        //+ Nếu dùng () ==> nên dùng stack.pop() đế tính phép */ '/'
        //
        //#Reference:
        //770. Basic Calculator IV
        //1597. Build Binary Expression Tree From Infix Expression
        String express="2*(5+5*2)/3+(6/2+8)";
        System.out.println(calculate(express));
        System.out.println(calculateRefactor(express));
        System.out.println(calculateReference(express));
    }
}
