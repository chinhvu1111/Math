package interviews.paypay;

import java.util.Stack;

public class StreamingExpressionLeetCode {

    //Giá trị của số trước đó/ của toàn bộ expression trước đó
    public int preValue;
    public int sign;
    public Stack<String> stack;
    public int currentNumber=0;

    public StreamingExpressionLeetCode() {
        stack=new Stack<>();
        sign=1;
        preValue=0;
    }

    public void addCharacter(char ch){
        if(ch==' '){
            return;
        }
        if(ch>='0'&&ch<='9'){
            currentNumber=currentNumber*10+(ch-'0');
        }
        //Nếu đến dấu + --> 3-1(+)
        else if(ch=='+'||ch=='-'){
            //Ta đang đến dấu + /- nhưng đằng trước nó có thể là :
            //+ + or -
            //+ * or /
            if(!stack.isEmpty()&&"+".equals(stack.peek())){
                stack.pop();
                int prePrevValue=0;
                if(!stack.isEmpty()){
                    prePrevValue= Integer.parseInt(stack.pop());
                }
                int newValue=prePrevValue+currentNumber*sign;
                stack.add(String.valueOf(newValue));
            }else if(!stack.isEmpty()){
                boolean isMulEx= "*".equals(stack.peek());
                boolean isDivEx="/".equals(stack.peek());

                if(isMulEx||isDivEx){
                    stack.pop();
                    int prePrevValue=0;
                    if(!stack.isEmpty()){
                        prePrevValue= Integer.parseInt(stack.pop());
                    }
                    int newValue;
                    if(isMulEx){
                        newValue=prePrevValue*currentNumber;
                    }else{
                        newValue=prePrevValue/currentNumber;
                    }
                    stack.add(String.valueOf(newValue*sign));
                }
            }else{
                stack.add(String.valueOf(currentNumber*sign));
            }
            stack.add("+");

            if(ch=='+'){
                sign=1;
            }else{
                sign=-1;
            }
            currentNumber=0;
        }else{
            //123 - 4*5+6
            //- Ở đây số - (âm) được tính dựa trên cả 1 expression phía sau
            // Ở vị trí *
            //Ex : 2*3*4 --> Ta vẫn phải thực hiện tính dần :
            //2*3=6
            //6*4=24
            if(ch=='*'||ch=='/'){
                boolean isMulEx= !stack.isEmpty() && "*".equals(stack.peek());
                boolean isDivEx= !stack.isEmpty() && "/".equals(stack.peek());

                if(!stack.isEmpty()&&isMulEx||isDivEx){
                    stack.pop();
                    int prePrevValue=0;
                    if(!stack.isEmpty()){
                        prePrevValue= Integer.parseInt(stack.pop());
                    }
                    int newValue;
                    if(isMulEx){
                        newValue=prePrevValue*currentNumber;
                    }else{
                        newValue=prePrevValue/currentNumber;
                    }
                    stack.add(String.valueOf(newValue));
                }else{
                    stack.add(String.valueOf(currentNumber));
                }
                stack.add(String.valueOf(ch));
            }else{
                stack.add(String.valueOf(currentNumber));
            }
            currentNumber=0;
        }
        //2423 + ( (2+22*6/2) + 332 ) / 2 - 23
    }

    public static int calculate(String expression){
        StreamingExpressionLeetCode expStream=new StreamingExpressionLeetCode();
//        String expression="11 * 21 + 3 * 7?";
        for(int i=0;i<expression.length();i++){
            expStream.addCharacter(expression.charAt(i));
        }

        boolean isMulEx=!expStream.stack.isEmpty() && "*".equals(expStream.stack.peek());
        boolean isDivEx= !expStream.stack.isEmpty() && "/".equals(expStream.stack.peek());
        Stack<String> newStack=expStream.stack;
        if(!expStream.stack.isEmpty()){
            expStream.stack.pop();
        }
        int currentValue;

        if (!expStream.stack.isEmpty()&&(isMulEx||isDivEx)){
            String prevValue=expStream.stack.pop();
            if(isMulEx){
                currentValue=Integer.parseInt(prevValue)*expStream.currentNumber;
            }else{
                currentValue=Integer.parseInt(prevValue)/expStream.currentNumber;
            }
        }else{
            currentValue=expStream.currentNumber*expStream.sign;
            if(!expStream.stack.isEmpty()){
                String prevValue=expStream.stack.pop();
                currentValue=currentValue+Integer.parseInt(prevValue);
            }
        }
        while (!expStream.stack.isEmpty()){
            //Ignore operator (Chỉ có thể là +/-)
            expStream.stack.pop();
            String prevNumber = expStream.stack.pop();
            currentValue=currentValue*expStream.sign+Integer.parseInt(prevNumber);
            if(expStream.sign==-1){
                expStream.sign=1;
            }
        }
        return currentValue;
    }

    public static void main(String[] args) {
        //2+3*3?
        //- Không có 2 ký tự *,/ được dùng liên tiếp nhau
        //- Không có 2 ký tự +/- được dùng liên tiếp nhau
        //
        //+ 2+3*3 (Đang điền dở) --> Cần in
        //+ 2+3/3 (Đang điền dở) --> Cần in
        //+ 2+4? (Đang điền dở) --> Cần in
        //2+3+?
        String s="1*2-3/4+5*6-7*8+9/10";
        System.out.println(calculate(s));
    }
}
