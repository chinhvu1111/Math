package interviews.bytedance;

public class E1_DeleteCharactersToMakeFancyString {

    public static String makeFancyString(String s) {
        if("".equals(s)){
            return s;
        }
        StringBuilder rsStr=new StringBuilder();
        int n=s.length();
        char prevChar=s.charAt(0);
//        rsStr.append(prevChar);
        int count=1;

        //a(1)a(2)(prev)a(3)baac
        for(int i=1;i<n;i++){
            if(s.charAt(i)==prevChar){
                count++;
            }else{
                if(count<3){
                    rsStr.append(prevChar);
                }
                count=1;
                prevChar=s.charAt(i);
                continue;
            }
            if(count<=3){
                rsStr.append(prevChar);
            }
            prevChar=s.charAt(i);
        }
        if(count<3){
            rsStr.append(prevChar);
        }
        return rsStr.toString();
    }

    public static void main(String[] args) {
//        String s="leeetcode";
//        String s="aaabaaaa";
//        String s="aaaaaaa";
        String s="aaa";
        System.out.println(makeFancyString(s));
    }
}
