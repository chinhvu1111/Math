package interviews;

public class E112_CheckIfAParenthesesStringCanBeValid {

    public static boolean canBeValidWrong(String s, String locked) {
        int[] stack=new int[s.length()];
        int top=0;
        int n=s.length();

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                stack[top++]=i;
            }else if(top!=0){
                if(s.charAt(stack[top-1])=='('&&s.charAt(i)==')'){
                    top--;
                }else{
                    stack[top++]=i;
                }
            }else{
                stack[top++]=i;
            }
        }
        for(int i=0;i<top;i++){
            System.out.printf(String.valueOf(s.charAt(stack[i])));
        }
        System.out.println();
        for(int i=0;i<top;i++){
            System.out.printf(String.valueOf(locked.charAt(stack[i])));
        }
        System.out.println("\n-------------------");
        for(int i=top-1;i>=1;i-=2){
            if((s.charAt(stack[i])=='('&&s.charAt(stack[i-1])=='('&&locked.charAt(stack[i])=='0')
            ||(s.charAt(stack[i])==')'&&s.charAt(stack[i-1])==')'&&locked.charAt(stack[i-1])=='0')
            ||(s.charAt(stack[i])=='('&&s.charAt(stack[i-1])==')'&&locked.charAt(stack[i])=='0'&&locked.charAt(stack[i-1])=='0')){
                top-=2;
            }else{
                return false;
            }
        }

        return top<=0;
    }

    public static boolean canBeValidWrong1(String s, String locked) {
        int n=s.length();
        char[] stack=new char[n];
        char[] stackChar=new char[n];
        int topChar=0;
//        int closeBracket[]=new int[n];
//        int openBracket[]=new int[n];
        int top=0;
        int closeBracketCount=0;
        int openBracketCount=0;

        for(int i=0;i<n;i++){
//            openBracket[i]=openBracketCount;
            if(s.charAt(i)=='('){
                openBracketCount++;
            }
//            closeBracket[n-i-1]=closeBracketCount;
            if(s.charAt(n-i-1)==')'){
                closeBracketCount++;
            }
        }

        for(int i=0;i<n;i++){
            if(s.charAt(i)==')'&&openBracketCount<closeBracketCount&&locked.charAt(i)=='0'){
                stackChar[topChar++]='(';
                openBracketCount++;
                closeBracketCount--;
            }else if(s.charAt(i)=='('&&openBracketCount>closeBracketCount&&locked.charAt(i)=='0'){
                stackChar[topChar++]=')';
                openBracketCount--;
                closeBracketCount++;
            }else {
                stackChar[topChar++]=s.charAt(i);
            }
        }
        for(int i=0;i<n;i++){
            if(top!=0){
                if(stack[top-1]=='('&&stackChar[i]==')'){
                    top--;
                }else{
                    stack[top++]=stackChar[i];
                }
            }else{
                stack[top++]=stackChar[i];
            }
        }

//        for(int i=0;i<top;i++){
//            System.out.printf(String.valueOf(s.charAt(stack[i])));
//        }
//        System.out.println();
//        for(int i=0;i<top;i++){
//            System.out.printf(String.valueOf(locked.charAt(stack[i])));
//        }
//        System.out.println();
//        for(int i=top-1;i>=1;i-=2){
//            if((s.charAt(stack[i])=='('&&s.charAt(stack[i-1])=='('&&locked.charAt(stack[i])=='0')
//                    ||(s.charAt(stack[i])==')'&&s.charAt(stack[i-1])==')'&&locked.charAt(stack[i-1])=='0')
//                    ||(s.charAt(stack[i])=='('&&s.charAt(stack[i-1])==')'&&locked.charAt(stack[i])=='0'&&locked.charAt(stack[i-1])=='0')){
//                top-=2;
//            }else{
//                return false;
//            }
//        }

        return top<=0;
    }

    public static boolean canBeValid(String s, String locked) {
        int n=s.length();
        int closeBracketCount=0;
        int openBracketCount=0;
        int replaceBracket=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='('){
                openBracketCount++;
            }
            if(s.charAt(n-i-1)==')'){
                closeBracketCount++;
            }
            if(locked.charAt(i)=='0'){
                replaceBracket++;
            }
        }
        int[] stack=new int[n];
        int top=0;

        for(int i=0;i<n;i++){
            if(top!=0){
                if(((s.charAt(i)==')'&&s.charAt(stack[top-1])=='(')
                        || s.charAt(i)=='('&&s.charAt(stack[top-1])=='('&&locked.charAt(i)=='0')
                        ||(s.charAt(i)==')'&&s.charAt(stack[top-1])==')'&&locked.charAt(stack[top-1])=='0')
                        ||(s.charAt(i)=='('&&s.charAt(stack[top-1])==')'&&locked.charAt(i)=='0'&&locked.charAt(stack[top-1])=='0')){
                    top--;
                }else{
                    stack[top++]=i;
                }
            }else{
                stack[top++]=i;
            }
        }
        boolean first=(top!=0&&s.charAt(stack[0])==')')||Math.abs(openBracketCount-closeBracketCount)>replaceBracket;

        top=0;
        stack[0]=0;
        for(int i=n-1;i>=0;i--){
            if(top!=0){
                if((s.charAt(i)=='('&&s.charAt(stack[top-1])==')'
                        || s.charAt(i)=='('&&s.charAt(stack[top-1])=='('&&locked.charAt(stack[top-1])=='0')
                        ||(s.charAt(i)==')'&&s.charAt(stack[top-1])==')'&&locked.charAt(i)=='0')
                        ||(s.charAt(i)==')'&&s.charAt(stack[top-1])=='('&&locked.charAt(i)=='0'&&locked.charAt(stack[top-1])=='0')){
                    top--;
                }else{
                    stack[top++]=i;
                }
            }else{
                stack[top++]=i;
            }
        }
        boolean second=(top!=0&&s.charAt(stack[0])=='(')||Math.abs(openBracketCount-closeBracketCount)>replaceBracket;

        if(first&&second){
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        //case 1:
        //))())) --> ()()()
//        String s="))()))";
//        String locked="010100";
//        String s=")";
//        String locked="0";
//        String s=")(";
//        String locked="00";
        String s="())()))()(()(((())(()()))))((((()())(())";
        //Case 2:
        //Nếu làm 2 chiều thì vẫn lỗi thôi
        //left :
        //)()))()(()(((())(()()))))((((()())(())
        //==> ) xuất hiện đều string --> false
        //11101100010001001011000000110010100101
        //
        //right :
        //())()))()(()(((())(()()))))((((()())((
        //==> ( xuất hiện cuối tring --> false
        //10111011000100010010110000001100101001
        String locked="1011101100010001001011000000110010100101";
//        String s="()))";
//        String locked="0011";
//        String s="))))(())((()))))((()((((((())())((()))((((())()()))(()";
//        String locked="101100101111110000000101000101001010110001110000000101";
//        System.out.println(canBeValidWrong(s, locked));
        System.out.println(canBeValid(s, locked));
//        System.out.println(canBeValid(new StringBuilder(s).reverse().toString(), new StringBuilder(locked).reverse().toString()));
    }
}
