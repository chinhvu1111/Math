package interviews;

import java.util.Deque;
import java.util.LinkedList;

public class E217_BasicCalculatorII_stack_undone {

    public static int calculate(String s) {
        Deque<String> stack=new LinkedList<>();
//        s=s.replaceAll("\\s*", "");
        int n=s.length();

        StringBuilder currentElement=new StringBuilder();

        for(int i=0;i<n;i++){
            if(s.charAt(i)==' '){
                continue;
            }
            if(isOperator(s.charAt(i))){
                if(!stack.isEmpty()){
                    if(isOperatorPriority(stack.peekLast())){
                        String prevOperator=stack.removeLast();
                        String prevNumber=stack.removeLast();
                        String newNumber = String.valueOf(calculateNumbers(prevNumber, currentElement.toString(), prevOperator));

                        stack.add(newNumber);
                    }else{
                        stack.add(currentElement.toString());
                    }
                }else{
                    stack.add(currentElement.toString());
                }
                stack.add(String.valueOf(s.charAt(i)));
                currentElement=new StringBuilder();
            }else{
                currentElement.append(s.charAt(i));
            }
//            System.out.println(currentElement.toString());
        }
        if(!stack.isEmpty()){
            String prevOperator=stack.peekLast();

            if(isOperatorPriority(prevOperator)){
                stack.removeLast();
                String prevNumber=stack.removeLast();
                String newNumber = String.valueOf(calculateNumbers(prevNumber, currentElement.toString(), prevOperator));

                stack.add(newNumber);
            }else{
                stack.add(currentElement.toString());
            }
        }else{
            stack.add(currentElement.toString());
        }

        while (stack.size()>1){
            String number1=stack.removeFirst();
            String operator=stack.removeFirst();
            String number2=stack.removeFirst();
            int newNumber=calculateNumbers(number1, number2, operator);
            stack.addFirst(String.valueOf(newNumber));
        }
        return Integer.parseInt(stack.peekLast());
    }

    public static int calculateNumbers(String s, String s1, char operator){
        if('*'==operator){
            return Integer.parseInt(s)*Integer.parseInt(s1);
        }
        if('/'==operator){
            return Integer.parseInt(s) / Integer.parseInt(s1);
        }
        if('+'==operator){
            return Integer.parseInt(s) + Integer.parseInt(s1);
        }
        if('-'==operator){
            return Integer.parseInt(s) - Integer.parseInt(s1);
        }
        return Integer.parseInt(s)/Integer.parseInt(s1);
    }

    public static int calculateNumbers(String s, String s1, String operator){
        if("*".equals(operator)){
            return Integer.parseInt(s)*Integer.parseInt(s1);
        }
        if("/".equals(operator)){
            return Integer.parseInt(s) / Integer.parseInt(s1);
        }
        if("+".equals(operator)){
            return Integer.parseInt(s) + Integer.parseInt(s1);
        }
        if("-".equals(operator)){
            return Integer.parseInt(s) - Integer.parseInt(s1);
        }
        return Integer.parseInt(s)/Integer.parseInt(s1);
    }

    public static int calculateNumbers(char c, char c1, char operator){
        if(operator=='/'){
            return (c-'0')/(c1-'0');
        }
        if(operator=='*'){
            return (c-'0')*(c1-'0');
        }
        if(operator=='+'){
            return (c-'0')+(c1-'0');
        }
        if(operator=='-'){
            return (c-'0')-(c1-'0');
        }
        return 0;
    }

    public static boolean isOperatorPriority(char c){
        return c=='/'||c=='*';
    }

    public static boolean isOperatorPriority(String c){
        return "/".equals(c)||"*".equals(c);
    }

    public static boolean isOperator(char c){
        return c=='/'||c=='*'||c=='+'||c=='-';
    }

    /*
    - Time complexity : O(N)
    - Time space : O(1)
     */
    public static int calculateWithoutStack(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
//        System.out.println(sWithoutSpace);
        int n=s.length();
        int prevValue=0;
        int i=0;
        char sign='+';
        int rs=0;

        while (i<n){
            int currentValue=0;
            if(s.charAt(i)==' '){
                i++;
                continue;
            }

            //122 + 2323 --> while hết characters để thành 2323
            while (i<n&&s.charAt(i)>='0'&&s.charAt(i)<='9'){
                if(s.charAt(i)==' '){
                    i++;
                    continue;
                }
                currentValue=currentValue*10 + (s.charAt(i)-'0');
                i++;
            }
//            System.out.printf("Current value: %s \n", currentValue);
            //123(rs)+(23)(prevValue) /(sign) 4*4 --> Lúc này sẽ thành 123 + (23/4)
            //123(rs)+(23)(prevValue) *(sign) 4*4
            //123(rs)+(23)(prevValue) +(sign) 4
            //123(rs)+(23)(prevValue) -(sign) 4
            //==> Cần lưu prev value
            if(sign=='+'){
                rs+=prevValue;
                prevValue=currentValue;
            }else if(sign=='-'){
                //Chú ý đoạn này
                //- Phép - chính là phép cộng nhưng preValue <0
                //--> Ta sẽ không phải xét các case riêng rẽ với -/+
                rs+=prevValue;
                prevValue=-currentValue;
            }else if(sign=='*'){
                prevValue*=currentValue;
            }else if(sign=='/'){
                prevValue/=currentValue;
            }
            if(i<n){
                sign=s.charAt(i);
                i++;
            }
        }
        rs+=prevValue;

        return rs;
    }

    public static void main(String[] args) {
//        String s="23+2*2";
//        String s="3+5 /2";
//        String s="3/2 ";
//        String s="34-2";
//        String s="1-2-1";
        //1, Case có 2 chữ số trở lên là case cần xử lý ở đây
        String s="14/3*2";
        System.out.println(calculate(s));
        System.out.println(calculateWithoutStack(s));
        //
        //** Đề bài:
        //- Cho biểu thức dạng : 34+4/3
        //+ Tính nó dựa trên quy tắc */ trước
        //+ Support số có nhiều chữ số
        //
        //** Bài này tư duy như sau:
        //Cách 1,
        //1, Using stack
        //1.1,
        //- Hướng đến việc chuyển 1 expression dạng 2+3*4-5
        //--> 2+12-5
        //- Sau đó dừng cách xử lý while() lần lượt để tính result
        //
        //1.2,
        //- Ở đây chúng ta sẽ đánh dấu s[i] = '*'/ '/' thì ta mới
        //+ Tính toán expression trước đó dạng (a) operator (b)
        //+ Cần lưu prevPrevValue (Giá trị trước của trước đó), prevOperator (operator trước đó), prevValue (giá trị trước đó)
        //--> Lưu vào stack
        //+ Add new value = expression của 3 giá trị trên
        //--> Khi lưu thì cần poll nó ra, tức là xóa khỏi stack + reset lại currentValue
        //--> Nếu mà operator trước đó không phải '*'/'/' : Ta sẽ lại add vào
        //
        //1.3, Vì stack có thể có 1 />1 phần tử
        //- Pop 2 lần có thể lỗi --> Nên cần check size >1
        //
        //1.4, Để tránh removeLast --> + add lại
        //- Nên dùng peekLast() để check
        //Cách 2:
        //2,
        //2.1, Với mỗi turn loop
        //- Ta sẽ loop để tìm ra số hiện tại gần nhất
        //VD : 122 + 2323 --> while hết i++ characters để thành 2323
        //
        //2.2, Sau đó ứng với mỗi cases ta sẽ tính toán dựa trên:
        //- 3 biến:
        //+ rs : kết quả bài toán (Cuối cùng mới return)
        //+ preValue : Kết quả trước đó (Ngay trước sign hiện tại)
        //+ sign : sign trước đó (trước s[i] là character)
        //+ currentValue : ứng với s[i] (current sign)
        //VD:
        //- 123(rs)+(23)(prevValue) /(sign) 4*4 --> Lúc này sẽ thành 123 + (23/4)
        //+ Lúc này không + 123 (rs) vì ta cần tính tiếp
        //+ preValue /= currentValue
        //- 123(rs)+(23)(prevValue) *(sign) 4*4

        //- 123(rs)+(23)(prevValue) +(sign) 4
        //+ Lúc này ta sẽ update rs += preValue
        //+ preValue lúc này sẽ = current = 4

        //- 123(rs)+(23)(prevValue) -(sign) 4
        //+ Lúc này ta sẽ update rs += preValue
        //+ preValue lúc này sẽ = -1 * current value = 4
        //==> Vì ta đang xét dấu -
        //** Rule :
        //- Phép - chính là phép cộng + (-1) *value
        //==> Nên quy chung về phép cộng để tính
        //
        //2.3, Đến cuối ta cần update lại prev Sign --> Tức là sign ta cần cache lại = s[i]
        //2.4, Nên nhớ sẽ có case
        //VD: 2*3/4 --> sẽ chỉ có preValue mà không rs+=prevalue
        //--> break while --> cần rs+=prevValue
        //
        //2.5, Tối ưu
        //- Không nên replace + tạo string mới
        //- s= s.replaceAll() : slow
        //--> Loop all character + continue
    }
}
