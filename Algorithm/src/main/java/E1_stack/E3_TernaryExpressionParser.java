package E1_stack;

import java.util.Stack;

public class E3_TernaryExpressionParser {

    public static String parseTernaryWrong(String expression) {
        Stack<Character> stack=new Stack<>();
        int n=expression.length();

        for(int i=0;i<n;i++){
            char c=expression.charAt(i);

            if(!stack.isEmpty()&&stack.peek()=='T'&&(c>='0'&&c<='9')){
                return String.valueOf(c);
            }
            while(i+1<n&&!stack.isEmpty()&&stack.peek()=='F'&&c!=':'){
                c=expression.charAt(++i);
            }
            if(!stack.isEmpty()&&c==':'){
                stack.pop();
            }else if(c=='T'||c=='F'){
                stack.add(c);
            }
//            System.out.println(stack);
        }
        return String.valueOf(stack.pop());
    }

    public static String parseTernaryWrong1(String expression) {
        Stack<Character> stack=new Stack<>();
        int n=expression.length();

        for(int i=0;i<n;i++){
            char c=expression.charAt(i);

            if(!stack.isEmpty()&&stack.peek()==':'){
                stack.pop();
                char oldVal=stack.pop();
                boolean isTrue=stack.pop()=='T';

                if(isTrue){
                    stack.add(oldVal);
                    if(stack.size()==1){
                        return String.valueOf(stack.pop());
                    }
                }else{
                    stack.add(c);
                }
            }else if(c!='?'){
                stack.add(c);
            }
            System.out.printf("char: %s, stack: %s\n", c, stack);
        }
        return String.valueOf(stack.pop());
    }

    public static String parseTernary(String expression) {
        Stack<Character> stack=new Stack<>();
        int n=expression.length();

        for(int i=n-1;i>=0;i--){
            char c=expression.charAt(i);

            if(c == ':'){
                continue;
            }
            if(stack.isEmpty() || stack.peek() != '?'){
                stack.add(c);
            }else if(!stack.isEmpty()&&stack.peek()=='?'){
                stack.pop();
                char first=stack.pop();
                char second=stack.pop();

                if(c=='T'){
                    stack.add(first);
                }else{
                    stack.add(second);
                }
            }
//            System.out.printf("char: %s, stack: %s\n", c, stack);
        }
        return String.valueOf(stack.pop());
    }

    public static String parseTernaryStackOpimization(String expression) {
        Stack<Character> stack=new Stack<>();
        int n=expression.length();

        for(int i=n-1;i>=0;i--){
            char c=expression.charAt(i);

            if(c == ':'){
                continue;
            }
            if(stack.isEmpty() || c != '?'){
                stack.add(c);
            }else {
                char first=stack.pop();
                char second=stack.pop();
                char nextChar=expression.charAt(--i);

                if(nextChar=='T'){
                    stack.add(first);
                }else{
                    stack.add(second);
                }
            }
//            System.out.printf("char: %s, stack: %s\n", c, stack);
        }
        return String.valueOf(stack.pop());
    }

    public static String parseTernaryBinaryTree(String expression) {
        Stack<Character> stack=new Stack<>();
        int n=expression.length();

        for(int i=n-1;i>=0;i--){
            char c=expression.charAt(i);

            if(c == ':'){
                continue;
            }
            if(stack.isEmpty() || c != '?'){
                stack.add(c);
            }else {
                char first=stack.pop();
                char second=stack.pop();
                char nextChar=expression.charAt(--i);

                if(nextChar=='T'){
                    stack.add(first);
                }else{
                    stack.add(second);
                }
            }
//            System.out.printf("char: %s, stack: %s\n", c, stack);
        }
        return String.valueOf(stack.pop());
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given the expression:
        //  + Bao gồm các character '?', ':', 'T', 'F'
        //  + Bao gồm các số chỉ có 1 chữ số [0,9]
        //* Return lại kết quả:
        //- Các expression có dạng:
        //Ex:
        //expression = "F?1:T?4:5"
        //
        //** Idea
        //* Method 1:
        //1.
        //1.0,
        //- Constraint
        //+ 5 <= expression.length <= 10^4
        //+ expression consists of digits, 'T', 'F', '?', and ':'.
        //+ It is guaranteed that expression is a valid ternary expression and that each number is a one-digit number.
        //
        //- Brainstorm
        //Ex:
        //expression = "F?1:T?4:5"
        //= F?(1:T?(4:5))
        //+ Ta thấy rằng kết quả của phép tính có thể là (T/ F) sẽ đứng ngay đằng trước của expression
        //- Ta thấy nếu = F/T thì nó đều có thể đi theo 2 directions
        //  + Mỗi hướng sẽ đều có () collections of predicates riêng
        //F?(1:T?(4:5))
        //- T=> left: F => right
        //- Chia ra trường hợp:
        //  + Ta gặp T --> ta sẽ lấu predicate bên left:
        //  Ex: T?T?1:2
        //  + Ta gặp F --> ta sẽ lấu predicate bên right:
        //  Ex: T?F?1:2
        //
        //- Bài này ta sẽ dùng stack
        //- Nếu top là F :
        //  + Thì ta sẽ remove nó đi nếu gặp (T, số,:)
        //Ex:
        //F?4:T?5:6
        //rs=5
        //--> Ta thấy rằng F ==> Chuyển qua T nếu (nó gặp ký tự :)
        //  --> Chuyển thành ký tự T : Tức là (remove F đi khi gặp :)
        //  <> ==> remove hết.
        //Ex:
        //expression = "T?T?F:5:3"
        //expression = "(T?(T?F:5):3)"
        //T T -> F:5
        //=> T F
        //T : F : 3
        //=> return F
        //
        //Ex:
        // expression = "(F?(1:(T?4:5))"
        //F
        //F 1
        //F 1 T -> 4:5
        //-> F 1 4
        //F -> 1 : 4
        //-> return 4
        //
        //- Ở đây ta sẽ add char
        //Ex:
        //expression = "(T?(T?F:5):3)"
        //T?T?F:5 ==> Đăng trước là :
        //-> T?F
        //T?F:
        //T?F: 3 ==>Pop 3 ký tự ra.
        //
        //rs=F
        //- Ta cần xét thêm dấu ?
        //==> Không cần.
        //
        //
        //- Nếu top là T:
        //  + Gặp số [0,9]: return
        //  + Gặp T thì không add.
        //
//        String expression = "F?1:T?4:5";
//        String expression = "T?T?F:5:3";
//        String expression = "T?2:3";
//        String expression = "T?T:F?T?1:2:F?3:4";
        //(T?T:F?T?1:2:F?3:4)
        //(T?(T:F?T?1:2:F?3:4))
        //(T?(T:F?T?1:2:F?3:4))
        //Ex:
        //T?T?T?T?T?F?1:2
//        String expression="T?T?T?T?T?F?1:F?3:4";
        String expression = "F?T?2:6:T?T?5:F?7:7:T?6:T?2:F?T:T?2:T?T?F?F?F?4:T?F?5:T?F:T?F?4:9:9:6:3:9:5:T?F?F?F?F?5:2:9:6:F?4:T?6:7:T?8:F?0:F?F?5:T?F:5:T?T?9:4:F?F?T?F?F?6:8:F:4:F?F?T?F:F?F?0:F?7:2:T?8:T?F?9:8:7:1:6:T?T?F?9:T?F?3:8:3:F:4";
//        String expression = "F?T?2:6:T?T?5:F?7:7:T?6:T";
        //F?T?2:6:T?T?5:F?7:7:T?6:T
        //F?(T?2:6:T?T?5:F?7:7:T?6:T)
        //F?(T?(2:6:T?(T?(5:F?(7:7:T?(6:T))))))
        //F?(T?(2:6:T?(T?(5:F?(7:7:6))))))
        //F?(T?(2:6:T?(T?5))))
        //F?(T?(2:6:5)))
        //==> 3 chữ số liền nhau => Phân tích sai.
        //F?(T?(2:6:T?(T?(5:F?(7:7:T?(6:T
        //F?(T?(2:6:T?(T?(5:F?(7:7:T?(6:T)
        //==> Quan trọng ở đây là phải phân tích ra đúng đã.
        //Ex:
        //T?F?1:2:(F?4:1)
        //->
        //T?(F?1:2):1
        //->
        //T?2:1
        //->
        //2
        //- Vẫn tư duy như trên áp dụng với:
        //expression = "T?T?F:5:3"
        //T?(T?F:5):3
        //T?F:3
        //F
        //Ex:
        //F?T?2:6:T?T?5:F?7:7:T?6:T
        //F?T?2:6:T?T?5:F?7:7:(T?6:T)
        //-> F?T?2:6:T?T?5:F?7:7:6
        //F?T?2:6:T?T?5:(F?7:7):6
        //-> F?T?2:6:T?T?5:7:6
        //F?T?2:6:T?(T?5:7):6
        //-> F?T?2:6:T?5:6
        //F?T?2:6:(T?5:6)
        //-> F?T?2:6:5
        //F?(T?2:6):5
        //-> F?2:5
        //F?2:5
        //-> 5
        //- Ta thấy ở đây gặp ? -> Tìm cách dồn vào 1 predicate
        //F?T?2:6:T?T?5:F?7:7:(T?6:T)
        //- Từ right --> left
        //- Vẫn có 1 số right không gộp trước được
        //Ex:
        //expression = "T?T?F:5:3"
        //expression = "(T?(T?F:5):3)"
        //+ 3 không gộp vào tính luôn được.
        //
        //F?T?2:6:T?T?5:F?7:7:(T?6:T)
        //reverse=
        //T:6:?T?T:7:7?F:5?T
        //* IDEA (Mấy tư duy phía trên sai):
        //- Ta nên reverse vì:
        //* Mấu chốt ở đây là khi gặp dấu ? từ right --> Left traverse ta gặp dấu ? nào thì tính cái đó:
        //  + Theo thứ tự right --> left.
        //- Vì reverse khi traverse mới thoả mãn:
        //  + Right site first
        //  + Calculate các số đã được add vào stack rồi
        //  Ex: T?6:T -> reverse : T:6?T
        //  Có 6,T trong stack thì mới tính được.
        //
        //- Traverse + process cụ thể sẽ ntn?
        //Ex:
        //expression = "T?T?F:5:3"
        //-> Reverse: 3:5:F?T?T
        //3:5:F?
        //3:5:F?T -> F (Gặp ? thì xét T/F để pop)
        //  + T: Thì pop 2 lần + add lại 1 char ở peek
        //  + F: Thì pop 2 lần + add lại 1 char ở prev peek
        //3:F?T -> F
        //  + Ta cần phải check 2 chars ? và T
        //  ==> Ta vẫn cứ add ? vào sau đó pop cùng luôn.
        //
        //F?(1:F?(2:T?(F?(3:4:5
        //F?(1:(F?(2:(T?(F?(3:4)):5))))
        //
        //** NOTE:
        //One must take care that the conditional expressions are right-to-left associative. This means that the expression T?F:T?4:5 should be
        //
        //✔️ evaluated as T?F:(T?4:5)
        //❌ and not as (T?F:T)?4:5.
        //1.1, Optimization
        //- Tối ưu bằng cách không add ? nữa mà chỉ xét next character thôi.
        //
        //1.2, Complexity:
        //- N is the length of expression
        //- Space : O(N)
        //- Time : O(N)
        //
        //2. Binary tree
        //2.0, Idea
        //-
        //
//        System.out.println(parseTernaryWrong(expression));
//        System.out.println(parseTernaryWrong1(expression));
//        System.out.println(parseTernary(expression));
        System.out.println(parseTernaryStackOpimization(expression));
        System.out.println(parseTernaryBinaryTree(expression));
        //#Reference:
        //385. Mini Parser
        //722. Remove Comments
        //736. Parse Lisp Expression
    }

}
