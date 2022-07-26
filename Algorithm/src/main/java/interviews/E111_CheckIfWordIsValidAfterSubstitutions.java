package interviews;

public class E111_CheckIfWordIsValidAfterSubstitutions {

    public static boolean isValid(String s) {
        int n=s.length();
        char[] stack=new char[s.length()];
        int top=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='c'&&isValid(stack, top)){
                top-=2;
            }else{
                stack[top++]=s.charAt(i);
            }
        }
        return top==0;
    }

    public static boolean isValid(char[] stack, int top){
        if(top<2){
            return false;
        }
        if(stack[top-2]=='a'&&stack[top-1]=='b'){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String s="abcabcababcc";
//        String s="aabcbc";
//        String s="abccba";
        System.out.println(isValid(s));
        //Bài này tư duy như sau:
        //1, t1,(abc)t2
        //--> Về cơ bản khi bỏ abc đi --> cũng vẫn phải là (abc) đúng cạnh nhau
        //--> Dùng stack là xong.
        //1.1, Việc khó nhất là tìm abc thì đã có sẵn r.
    }
}
