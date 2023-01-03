package interviews;

public class E247_StringToInteger_string {

    public static int myAtoi(String s) {
        s=s.trim();
        if("".equals(s)){
            return 0;
        }
        int n=s.length();
        int number=0;
        int sign=1;
        int i=0;

        if(s.charAt(0)=='-'){
            sign=-1;
            i++;
        }else if(s.charAt(0)=='+'){
            i++;
        }
        for(;i<n;i++){
            if('0'<=s.charAt(i)&&s.charAt(i)<='9'){
                if(number*sign<Integer.MIN_VALUE/10){
                    return Integer.MIN_VALUE;
                }else if(number*sign>Integer.MAX_VALUE/10){
                    return Integer.MAX_VALUE;
                }
                number=number*10+(s.charAt(i)-'0');
//                System.out.println(number);
//                System.out.println((Integer.MIN_VALUE-(s.charAt(i)-'0')/10));
            }else{
                break;
            }
        }
         if(sign==1&&(number*sign-10>Integer.MAX_VALUE-10)){
            return Integer.MAX_VALUE;
        }else if(sign==-1&&(number*sign+10<Integer.MIN_VALUE+10)){
            return Integer.MIN_VALUE;
        }
        return number*sign;
    }

    public static void main(String[] args) {
//        String s="-91283472332";
//        String s="";
//        String s="+21474";
//        String s="21474836460";
//        String s="-21474836460";
//        String s="-2147483649";
        String s="2147483646";
        System.out.println(myAtoi(s));
        //
        //
        //#Reference
        //9. Palindrome Number
        //7. Reverse Integer
        //65. Valid Number
        //2042. Check if Numbers Are Ascending in a Sentence
    }
}
