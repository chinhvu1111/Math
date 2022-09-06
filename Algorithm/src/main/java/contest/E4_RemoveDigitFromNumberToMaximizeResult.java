package contest;

import java.util.ArrayList;

public class E4_RemoveDigitFromNumberToMaximizeResult {

    public static String removeDigit(String number, char digit) {
        StringBuilder rs=new StringBuilder();
        int n=number.length();
        int integersIncre=-1;
        int integersDecre=-1;

        if(number.charAt(n-1)==digit){
            integersDecre=n-1;
        }
        for(int i=n-2;i>=0;i--){
            if(number.charAt(i)!=digit){
                continue;
            }
            if(number.charAt(i)>=number.charAt(i+1)){
                if(integersDecre==-1){
                    integersDecre=i;
                }
            }else {
                integersIncre=i;
            }
        }
        if(integersIncre!=-1){

            for(int i=0;i<n;i++){
                if(i!=integersIncre){
                    rs.append(number.charAt(i));
                }
            }
            return rs.toString();
        }
        if(integersDecre!=-1){

            for(int i=0;i<n;i++){
                if(i!=integersDecre){
                    rs.append(number.charAt(i));
                }
            }
            return rs.toString();
        }

        return rs.toString();
    }

    public static void main(String[] args) {
//        String s="551";
//        char digit='5';
//        String s="123";
//        char digit='3';
//        String s="1231";
//        char digit='1';
//        String s="133235";
//        char digit='3';
        String s="145345";
        char digit='4';
        //Case 1:
        //Case 2: Xóa 3
        //133235
        //133>2, 3<5
        //Case 3: Xóa 4
        //144342
        //+ 14342
        //+ 14432
        //Case 4: Xóa 4
        //145345
        //+ 14342
        //+ 14432
        System.out.println(removeDigit(s, digit));
    }
}
