package interviews;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class E263_BasicCalculatorIII_Wrong {
    //Quy luật:
    //- Nghĩ thêm 1 chút + demo VD để clear cách làm ==> Tránh debug quá nhiều
    //- Có thể xuất phát từ sign (Trước đó/s.charAt(i)) hiện tại ==> Sau đó mới check tiếp
    //- Tư duy dạng kiểu có ngoặc --> () : Cần lưu lại các giá trị trung gian trước đó và dấy (sign) ngay sau nó.
    //VD: 3*2+x ==> thứ tự thực hiện (2+x) *3
    //- Ta có chỉ có thể tách ra 2 stack riêng lưu interger và number khi :
    //+ Ta tư duy dạng lưu lại kết quả trước như trên
    //<> thì sẽ bị sai (Do không biết dấu nào của giá trị nào ngay)
    public static int calculate(String s) {
        // Write your code here
        Stack<Integer> preRs=new Stack<>();
        Stack<Character> operators=new Stack<>();
        int n=s.length();
        int currentNumber=0;
        int prevValue=0;
        int rs=0;
        char sign='+';

        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            if(ch==' '){
                continue;
            }

            if(ch>='0'&&ch<='9'){
                currentNumber=currentNumber*10+(ch-'0');
                continue;
            }
            //+( or -( or *( or /(
            //VD:
            //5+3*(
            //5*(
            //5+(
            //VD:
            //4 * (2 + 5 + 3 * 2 * ( 2+1) )
            //Shorten : 4 * (7 (rs) + 6 (prevValue) * ( 2+1) )
            //+ stack 1 : 4, 7(Không push cả 2 và 5), 6(Không push 3)
            //+ stack 2 : *,+,*
            //==> Vẫn phải tính toán trong () để rút gọn biểu thức.
            //- Trả lời: Đẩy toàn bộ các phép tính vào trong stack
            //VD:
            //4 * 2 + 5
            if(ch!='('&&ch!=')'){
                if(sign=='+'){
                    rs+=prevValue;
                    prevValue=currentNumber;
                }else if(sign=='-'){
                    rs+=prevValue;
                    prevValue=-currentNumber;
                }else if(sign=='*'){
                    prevValue*=currentNumber;
                }else if(sign=='/'){
                    prevValue/=currentNumber;
                }
            }
//            if (i<n-1&&s.charAt(i)!=' ') i++;
//            ch=s.charAt(i);
            if(ch=='('){
//                if(rs!=0){
//                    preRs.add(rs);
//                    operators.add('+');
//                }
//                preRs.add(prevValue);
//                operators.add(sign);
                if(!preRs.isEmpty()){
                    int num= preRs.pop();
                    char op=operators.pop();
                    if(op=='+'){
                        num=num+rs;
                    }if(op=='*'){
                        num=num*rs;
                    }else if(op=='/'){
                        num=num/rs;
                    }else if(op=='+'){
                        num=num+rs;
                    }else if(op=='-'){
                        num=num-rs;
                    }
                    preRs.add(num);
                    operators.add('+');
                }
                preRs.add(prevValue);
                operators.add(sign);
                rs=0;
                prevValue=0;
                currentNumber=0;
            }else if(ch==')'){
                //(5(prevValue) + 3(currentValue) (*) 2 * 2)
                //**** Chỗ này hơi confuse đoạn prevValue tính ưu tiên phép * or / như thế nào?
                //- Trả lời : Dùng rs để cache lại giá trị trước đó ==> prev sẽ chỉ tính khi có 2 phần từ
                //- Giải thích:
                //+ Chỉ cần xét lúc đấu --> ta đã gán prevValue=5
                //==> Để ko quan tâm đến 5 ==> rs+=5
                //+ Xét * thứ 1 ==> gán prevValue=3 (CurrentValue) : Lúc này vẫn chưa tính phép *
                //+ Xét * thứ 2 ==> gán prevValue=prevValue * currentValue = 3*2 : Lúc này mới thực hiện việc tính toán tiếp
                //
                //Khi gặp ) --> Tức là ta cần pop từ stack1 và stack2 ra
                //VD:
                //(6+2 * (3+4+2*(5+6) + 6 (Chỗ này cần ví dụ để xét case run tiếp) ) * 2
                //
                //- Basic example (Để mô tả thao tác pop nó ra):
                //1+2*(2(rs)+3(prevValue)  [)] (khi xét đến ')'
                //
                //- (6+2 * ( 7 + 2*(5+6) + 6 ) (Chỗ này cần ví dụ để xét case run tiếp) ) * 2
                //
                if(sign=='+'){
                    rs+=prevValue+currentNumber;
                }else if(sign=='-'){
                    rs+=prevValue-currentNumber;
                }else if(sign=='*'){
                    prevValue*=currentNumber;
                    rs+=prevValue;
                }else if(sign=='/'){
                    prevValue/=currentNumber;
                    rs+=prevValue;
                }

                //Nếu ở đây mà lấy case =13 ==> Không có dấu thì sẽ sai
                prevValue=preRs.pop();
                currentNumber=rs;
                sign=operators.pop();

                if(sign=='*'){
                    prevValue=rs*prevValue;
                }else if(sign=='/'){
                    prevValue=prevValue/rs;
                }else if(sign=='+'){
                    prevValue=prevValue+rs;
                }else if(sign=='-'){
                    prevValue=prevValue-rs;
                }
                System.out.println(prevValue);
                //Chỗ này hiểu lầm --> Không phải kết hợp giá trị trong stack lúc nào cũng được
                //--> Chỉ được kết hợp khi gặp ký tự ')' mà thôi.
                rs=0;
                currentNumber=0;
            }else{
                currentNumber=0;
            }
            if(ch=='('||ch==')'){
                sign='+';
            }else{
                sign=ch;
            }
        }
        rs+=currentNumber+prevValue;
        Deque<Integer> newNum=new LinkedList<>(preRs);
        Deque<Character> dequeOperators=new LinkedList<>(operators);
        newNum.addLast(rs);
        dequeOperators.addLast(sign);
        int result=0;

        if(!newNum.isEmpty()){
            result=newNum.removeFirst();
        }
        while (!newNum.isEmpty()){

            //Nếu muốn sửa lại để nó tính cho đúng
            //VD: a*(b+c)/4
            //==> có b+c --> cần gán
            //+ a=prevValue
            //+ currentValue=b+c
//            if(operators.peek()=='*'){
//                rs=rs*preRs.pop();
//            }else if(operators.peek()=='/'){
//                rs=preRs.pop()/rs;
//            }else if(operators.peek()=='+'){
//                rs=preRs.pop()+rs;
//            }else if(operators.peek()=='-'){
//                rs=preRs.pop()-rs;
//            }
//            operators.pop();
            char op=dequeOperators.removeFirst();
            int num1=newNum.removeFirst();
            if(op=='*'){
                result=result*num1;
            }else if(op=='/'){
                result=result/num1;
            }else if(op=='+'){
                result=result+num1;
            }else if(op=='-'){
                result=result-num1;
            }
        }
        return result;
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
