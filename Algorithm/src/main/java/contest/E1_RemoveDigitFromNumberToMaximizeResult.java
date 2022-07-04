package contest;

public class E1_RemoveDigitFromNumberToMaximizeResult {

    public static String removeDigit(String number, char digit) {
        int n=number.length();
        String rs="";

        for(int i=0;i<n;i++){
            if(digit!=number.charAt(i)){
                continue;
            }
            String s=number.substring(0,i)+number.substring(i+1,n);

            if(compare(rs, s)<0){
                rs=s;
            }
        }
        return rs;
    }

    public static int compare(String s, String t){
        if(s.length()>t.length()){
            return 1;
        }
        if(s.length()<t.length()){
            return -1;
        }
        if(s.equals(t)){
            return 0;
        }
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)<t.charAt(i)){
                return -1;
            }
            if(s.charAt(i)>t.charAt(i)){
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
//        String s="1231";
//        String digit="1";
        String s="133235";
        String digit="3";
        System.out.println(removeDigit(s, digit.charAt(0)));
    }
}
