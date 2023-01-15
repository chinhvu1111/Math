package interviews.paypay;

import java.util.Stack;

public class StreamingExpressionStack {

    //Giá trị của số trước đó/ của toàn bộ expression trước đó
    public int preValue;
    public int sign;
    public Stack<String> stack;
    public int currentNumber=0;

    public StreamingExpressionStack() {
        stack=new Stack<>();
        sign=1;
        preValue=0;
    }

    public void addCharacter(char ch){
        if(ch==' '){
            return;
        }
        //2+3*3?
        //- Không có 2 ký tự *,/ được dùng liên tiếp nhau
        //- Không có 2 ký tự +/- được dùng liên tiếp nhau
        //
        //+ 2+3*3 (Đang điền dở) --> Cần in
        //+ 2+3/3 (Đang điền dở) --> Cần in
        //+ 2+4? (Đang điền dở) --> Cần in
        //2+3+?
        if(ch=='?'){
            boolean isMulEx=!stack.isEmpty() && "*".equals(stack.peek());
            boolean isDivEx= !stack.isEmpty() && "/".equals(stack.peek());
            Stack<String> newStack=new Stack<>();
            newStack.addAll(stack);
            if(!stack.isEmpty()){
                stack.pop();
            }
            int currentValue=0;

            if (!stack.isEmpty()&&(isMulEx||isDivEx)){
                String prevValue=stack.pop();
                if(isMulEx){
                    currentValue=Integer.parseInt(prevValue)*currentNumber;
                }else{
                    currentValue=Integer.parseInt(prevValue)/currentNumber;
                }
            }else{
                currentValue=currentNumber*sign;
                if(!stack.isEmpty()){
                    String prevValue=stack.pop();
                    currentValue=currentValue+Integer.parseInt(prevValue);
                }
            }
            while (!stack.isEmpty()){
                //Ignore operator (Chỉ có thể là +/-)
                stack.pop();
                String prevNumber = stack.pop();
                currentValue=currentValue+Integer.parseInt(prevNumber)*sign;
                if(sign==-1){
                    sign=1;
                }
            }
            System.out.println(currentValue);
            stack.clear();
            stack=newStack;
        }else if(ch>='0'&&ch<='9'){
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

    public static void main(String[] args) {
        //+ Replace all ký tự " " --> Gặp thì không add vào expression
        //- Streaming liên tục thì có thể dùng instance + nhập số/ gen số liên tục random là xong
        //- Lưu lại ít nhất có thể ==> Đến khi gõ ? thì run method tính kết quả dựa trên phép (+-)
        //- Dù chạy ? Nhưng mà nếu thêm * / đằng sau thì thứ tự thực hiện lúc đó sẽ thay đổi
        //==> Ít nhất vẫn phải lưu thông tin về prevalue + (currentExpression)
        //==> Kết thúc 1 expression riêng rẽ ta không cần lưu lại các expression cũ nữa --> Chỉ lưu kết quả thôi.
        //- Phần dấu ngoặc thì xử lý như thế nào:
        //VD: ( 234+ (2*3)*(5*6) ) * 2 + 4
        //+ Nếu đến 1 dấy ngoặc dạng ( --> add vào expression
        //+ Nếu gặp 1 dấu ngoặc ) --> ta sẽ loop expression trước
        //Expression trước về cơ bản sẽ được thu gọn chỉ còn dạng  + + ==> Tính luôn cho nó
        StreamingExpressionStack expStream=new StreamingExpressionStack();
//        String expression="2+3*3?";
//        String expression="2+3/3?";
//        String expression="2+3/3*5?";
//        String expression="2+3/3*5+4?";
//        String expression="2+22*6/2?";
//        String expression="2423 + 2+22*6/2?";
//        String expression="2423 + 2+22*6/2 + 332 / 2?";
//        String expression="2423 + 2+22*6/2 + 332 / 2 - 23?";
//        String expression="11 * 21 + 3 * 7?";
//        String expression="12-3*4?";
        String expression="12-3?*4?";
        for(int i=0;i<expression.length();i++){
            expStream.addCharacter(expression.charAt(i));
        }
    }
}
