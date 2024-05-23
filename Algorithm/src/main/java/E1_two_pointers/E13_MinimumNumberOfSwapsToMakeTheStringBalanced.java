package E1_two_pointers;

import java.util.ArrayList;
import java.util.Stack;

public class E13_MinimumNumberOfSwapsToMakeTheStringBalanced {

    public static int minSwaps(String s) {
        Stack<Character> stack=new Stack<>();
        int n=s.length();

        for(int i=0;i<n;i++){
            char curChar=s.charAt(i);

            if(stack.isEmpty()||stack.peek()==']'||curChar=='['){
                stack.add(curChar);
            }else if (!stack.isEmpty()&&stack.peek()=='['&&curChar==']'){
                stack.pop();
            }
//            ArrayList<Character> list = new ArrayList<>(stack);
//            System.out.printf("Index=%s, stack:\n", i);
//            list.forEach(System.out::println);
        }
//        System.out.println(stack);
        return stack.size()/4 + stack.size()%4/2;
    }

    public static int minSwapsOptimization(String s) {
        int countOpen=0;
        int n=s.length();

        for(int i=0;i<n;i++){
            char curChar=s.charAt(i);
            if(curChar=='['){
                countOpen++;
            }else{
                if(countOpen>0){
                    countOpen--;
                }
            }
        }
//        System.out.println(stack);
        return (countOpen+1)/2;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.
        //A string is called balanced if and only if:
        // - It is the empty string, or
        // - It can be written as AB, where both A and B are balanced strings, or
        // - It can be written as [C], where C is a balanced string.
        //You may swap the brackets at any two indices any number of times.
        //* Return (the minimum number of swaps) to make s balanced.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //n == s.length
        //2 <= n <= 10^6
        //- n is even.
        //- s[i] is either '[' or ']'.
        //The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.
        //
        //- Brainstorm
        //Input: s = "][]["
        //Output: 1
        //Explanation: You can make the string balanced by swapping index 0 with index 3.
        //The resulting string is "[[]]".
        //
        //- Swap 2 bracks để:
        //  + Đổi open thành closed
        //- Để đơn giản ta sẽ giản lược đi những brackets valid
        //  => Còn bao nhiêu thì ta swap là được.
        //  Ex:
        //  s = "[]][[]]["
        //  -> "][" -> swap 1 lần
        //
//        String s="][][";
//        String s="]]][[[";
        String s="[[[]]]][][]][[]]][[[";
        //-> ]]][[[
        //-> []][[]
        //-> [][][]: rs=2
        //
        //- Ra final stack --> Sau đó ta sẽ swap ntn?
        //- Khi có trong stack cuối:
        //  + Nó không thể triệt tiêu được.
        //  Ex:
        //  [ : left phải là ], right phải là : [
        //  => ][[ : hoặc là left, right trống.
        //  --> Stack cuối cùng phải có ít nhất 2 ký tự:
        //  Ex: ][, ]][[
        //- Mỗi lần swap sẽ xoá đi được:
        //  + 4 characters
        //- Nhưng có thể sẽ còn dư 2 characters cho đến cuối:
        //=> rs = (stack_size/4) + (stack_size%4/2)
        //
        //1.1, Optimization
        //- Có cách nào để giảm space không?
        //Ex:
        // ]]][[][]][[[
        // + Nếu làm theo cách cũ:
        // -> shorten : ]]][[[ : rs = 2
        //- Ở đây ta thấy [ gặp ] ở đâu thì cũng triệt tiêu được:
        //  + Count số char [ ==> Sau đó check ] ==> Giảm đi (count--)
        //  + Nếu toàn ]] thì sao:
        //  Ex:
        //  [[(]]]])[[
        //  + count=2 với 2 ký tự cuối
        //  + Kiểu gì cũng có 2 ký tự ]] ở đầu (Do số lượng ký tự [] đều nhau) nên không cần xét ]]  làm gì.
        //  => rs=(count+1)/2 là được : Áp dụng công thức bên trên.
        //
        //1.2, Complexity
        //- Space : O(1)
        //- Complexity : O(n)
        //
        System.out.println(minSwaps(s));
        System.out.println(minSwapsOptimization(s));
        //#Reference:
        //301. Remove Invalid Parentheses
        //921. Minimum Add to Make Parentheses Valid
        //1541. Minimum Insertions to Balance a Parentheses String
    }
}
