package interviews;

public class E112_CheckIfAParenthesesStringCanBeValid {

    public static boolean canBeValid(String s, String locked) {
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

    public static void main(String[] args) {
        String s="))()))";
        String locked="010100";
//        String s=")";
//        String locked="0";
//        String s=")(";
//        String locked="00";
        canBeValid(s, locked);
        System.out.println(canBeValid(s, locked));
    }
}
