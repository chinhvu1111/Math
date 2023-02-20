package interviews.bytedance;

import java.util.Stack;

public class E30_ScoreOfParentheses {

    public static int scoreOfParenthesesUndone(String s) {
        Stack<Character> stackParentheses=new Stack<>();
        Stack<Integer> stackNum=new Stack<>();
        int n=s.length();
        int prevNum=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                stackNum.add(prevNum);
                prevNum=0;
                stackParentheses.add('(');
            }else if(s.charAt(i)==')'){
                while (i<n&&!stackParentheses.isEmpty()&&stackParentheses.peek()=='('&&s.charAt(i)==')'){
                    if(!stackNum.isEmpty()){
                        if(prevNum!=0){
                            prevNum=prevNum*2;
                        }else{
                            prevNum=stackNum.pop()+2;
                        }
                    }
                    stackParentheses.pop();
                    i++;
                }
                i--;
            }
        }
        return 1;
    }

    public static int scoreOfParentheses(String s) {
        Stack<Character> stackParentheses=new Stack<>();
        int n=s.length();

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                stackParentheses.add('(');
            }else{
                int prevVal=0;

                while (!stackParentheses.isEmpty()&&stackParentheses.peek()!='('){
                    prevVal+=stackParentheses.pop()-'0';
                }
                if(!stackParentheses.isEmpty()){
                    stackParentheses.pop();
                }
                if(prevVal==0){
                    stackParentheses.add('1');
                }else{
                    stackParentheses.add((char)(prevVal*2+'0'));
                }
            }
        }
        int rs=0;

        while (!stackParentheses.isEmpty()){
            rs+=stackParentheses.pop()-'0';
        }
        return rs;
    }

    public static int scoreOfParenthesesSeparate(String s) {
        int n=s.length();
        int d=0;
        int rs=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                d++;
            }else{
                d--;
                if(s.charAt(i-1)=='('){
                    rs+=Math.pow(2, d);
                }
            }
        }

        return rs;
    }

    public static int scoreOfParenthesesOptimize(String s) {
        int n=s.length();
        int bracket=0;
        int rs=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                bracket*=2;
            }
            if(bracket==0){
                bracket=1;
            }
            if(s.charAt(i)==')'&&s.charAt(i-1)=='('){
                rs+=bracket;
            }
            if(s.charAt(i)==')'){
                bracket/=2;
            }
        }

        return rs;
    }

    public static void main(String[] args) {
        //Result : 20
        String s="(()(()()))";
        //#Result: 8
//        String s="(()())";
        //(()(()()))
        //( 1 + (1+1)*2 ) * 2
        System.out.println(scoreOfParentheses(s));
        System.out.println(scoreOfParenthesesSeparate(s));
        System.out.println(scoreOfParenthesesOptimize(s));
        //
        //** Đề bài
        //- Bài này value của expression dựa trên quy luật:
        //Example 1:
        //
        //Input: s = "()"
        //Output: 1
        //Example 2:
        //
        //Input: s = "(())"
        //Output: 2
        //Example 3:
        //
        //Input: s = "()()"
        //Output: 2
        //
        //** Bài này tư duy như sau:
        //1, Cách tư duy sai :
        //1.1, Thứ tự push sẽ như thế nào?
        //(()(()()))
        //+ Mình có thể convert thành các expression liền nhau —> Sau đó cộng dồn vào.
        //+ Không thì mình sẽ suy nghĩ các push + pop cho phù hợp
        //
        //Chia ra 2 stack để tư duy:
        //+ Cần làm sao đó để bỏ được case liên quan đến order của expression.
        //// Nếu ta tư duy theo cách thông thường
        //1,
        //Stack char : (,(
        //Stack number :
        //
        //2,
        //Stack char : (,(,(
        //Stack number : 1
        //3,
        //Stack char : (,(,(,
        //Stack number : 2,2
        //4,
        //Stack char : (,(,
        //Stack number : 2,2,2
        //5,
        //Stack char : (,(,
        //Stack number : 2,2,2
        //
        //+ Để không dùng phương pháp để chung vào cùng 1 stack ==> ta sẽ dùng pp khác.
        //+ Bắt đầu ký tự ( nghĩa là bắt đầu expression mới
        //==> Với cách nghĩ này ta có thể dùng cách Add số 0 vào
        //VD: ( 0 (
        //==> Ở giữa sẽ xuất hiện số 0.
        //
        //+ Simple case:
        //
        //VD: (() ())
        //1,
        //Stack char : (,(
        //Stack num : 0,
        //2,
        //Stack char : (,()
        //Stack num : 0,
        //
        //Stack char : (,
        //Stack num : 2,
        //==> Nếu tư duy ntn thì giống với việc Add 0 bên trên rồi 0 <=> mà 2 là nằm “lồng bên trong” <> 0 nằm cạnh
        //
        //* Mấu chốt tư duy ở đây là khi gặp ký tự (
        //==> reset prevValue =0
        //* Gặp ) —>
        //if(prevValue==0)
        //preValue = pop + 2
        //Else
        //preValue = pop*prevValue + 2
        //
        //1,
        //Stack char : (,(
        //Stack num :
        //prevNum=0
        //2,
        //Stack char : (,()
        //Stack num :
        //prevNum=0;
        //prevNum= (0 + 2) *(empty)
        //
        //Stack char : (,
        //Stack num : 2,
        //prevNum=0
        //3, Stack char : (,)
        //Stack num : 2
        //prevNum= 0
        //value= 2*2
        //
        //VD:
        //(()(()())
        //
        //1,
        //Stack char : (,(
        //Stack num : 0,
        //prevNumber=0
        //2,
        //Stack char : (,
        //Stack num :
        //prevNumber=2
        //3,
        //Stack char : (,(
        //Stack num : 2
        //prevNumber=0
        //4,
        //Stack char : (,(,(
        //Stack num : 2, 0
        //prevNumber=0
        //5,
        //Stack char : (,(,(,)
        //Stack num : 2
        //prevNumber= 0 (pop) + 2
        //6,
        //Stack char : (,(,(
        //Stack num : 2, 2
        //prevNumber= 0
        //7,
        //Stack char : (,(,(,) —> (,(
        //Stack num : 2
        //prevNumber= 2(pop) +2 = 4
        //8,
        //Stack char : (,(,) -> (
        //Stack num : 2
        //prevNumber= 4 * 2 = 8
        //9,
        //Stack char : (,) -> “”
        //Stack num : 2
        //prevNumber= 4 * 2
        //
        //- Các số được Add vào Stack có ý nghĩa là các kết quả đằng trước, có các ý nghĩa như sau:
        //+ ()() : Nối liền số trước
        //+ (()) : Bao bởi nhau
        //
        //Cách 2:
        //2, Cách tư duy sử dụng stack
        //- Cách tư duy bình thường là ta sẽ gắn () --> thành character hết thì lúc đó ta sẽ xác định được Order của ()
        //2.1, Complextity:
        //- Time complexity : O(n)
        //- Space complexity : O(n)
        //
        //Cách 3:
        //3, Cách này là dùng dạng phân cấp của ( --> + thêm value vào
        //- Ta có thể cộng dồn vào dựa trên depth của (
        //- Ở đây ta có thể dùng pow để tính
        //VD:
        //(()(())
        //<=> ( 1 ( 1 ))
        //value = 2*1 + (2*1) * 2 = 6
        //
        //3.1, Complexity
        //- Time complexity : O(n) * O(sqrt(n)) (Không nhớ lắm)
        //- Space complexity : O(1)
        //
        //3.2, Tối ưu về time complexity:
        //- Ta sẽ dùng variable để lưu math(2,depth) ==> Tức là nhân dần lên
        //- Khi gặp ) --> Ta sẽ giảm depth đi ==> Tức là (var=var/2)
        //==> Làm như thế này thì ta sẽ không phải tính pow mọi lúc
        //
        //#Reference:
        //857. Minimum Cost to Hire K Workers
        //520. Detect Capital
        //844. Backspace String Compare
        //1208. Get Equal Substrings Within Budget
        //
        //843. Guess the Word
        //635. Design Log Storage System
        //1044. Longest Duplicate Substring
        //
    }
}
