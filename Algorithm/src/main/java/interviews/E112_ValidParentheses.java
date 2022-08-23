package interviews;

import java.util.Stack;

public class E112_ValidParentheses {

    public static boolean isValid(String s) {
        Stack<Character> st=new Stack();

        for(int i=0;i<s.length();i++){
            if(!st.isEmpty()&& ((st.peek()=='('&&s.charAt(i)==')')
                            ||(st.peek()=='['&&s.charAt(i)==']')
                            ||(st.peek()=='{'&&s.charAt(i)=='}'))){
                st.pop();
            }else{
                st.push(s.charAt(i));
            }
        }
        return st.isEmpty();
    }

    public static boolean isValidOptimize(String s) {
        Stack<Character> st=new Stack();

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{'){
                st.push(s.charAt(i));
            }else if(!st.isEmpty()){
                if(st.peek()!='('&&s.charAt(i)==')'){
                   return false;
                }else if(st.peek()!='['&&s.charAt(i)==']'){
                    return false;
                }else if(st.peek()!='{'&&s.charAt(i)=='}'){
                    return false;
                }else st.pop();
            }else {
                return false;
            }
        }
        return st.isEmpty();
    }

    public static boolean isValidOptimizeArray(String s) {
        char arr[]=new char[s.length()];
        int top=0;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{'){
                arr[top++]=s.charAt(i);
            }else if(top!=0){
                if(arr[top-1]!='('&&s.charAt(i)==')'){
                    return false;
                }else if(arr[top-1]!='['&&s.charAt(i)==']'){
                    return false;
                }else if(arr[top-1]!='{'&&s.charAt(i)=='}'){
                    return false;
                }else top--;
            }else {
                return false;
            }
        }
        return top==0;
    }

    public static void main(String[] args) {
//        String s="()[]{}";
//        String s="(]";
//        String s="]";
//        String s="]";
        String s="(])";
        System.out.println(isValid(s));
        System.out.println(isValidOptimize(s));
        //Bài này tư duy như sau:
        //1, Tối ưu như sau:
        //1,1, Tối ưu thứ tự predicate:
        //+ Tách điều kiện s.charAt[i]=='('/'['/'[') --> push(c) riêng
        //+ Check điều kiện false thay vì check điều kiện đúng
        //VD: stack.isNotEmpty() && check(st.peek()!='('&&s.charAt(i)==')' ) --> Chỉ cần khác điều kiện trên return false
        //1.2, Các điều kiện còn lại return false --> Không push mà không phải là pop --> Chỉ có thể ( stack empty + chuỗi vẫn còn )
        //1.2, Dùng array để implement stack + top --> Speed.
        System.out.println(isValidOptimizeArray(s));
    }
}
