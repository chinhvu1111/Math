package contest;

public class E178_ConvertDateToBinary {

    public static String getBinary(String s){
        Integer num=Integer.parseInt(s);
        StringBuilder rs=new StringBuilder();
        while(num!=0){
            rs.append(num%2);
            num=num/2;
        }
        return rs.reverse().toString();
    }

    public static String convertDateToBinary(String date) {
        String[] s=date.split("-");
        return getBinary(s[0])+"-"+getBinary(s[1])+"-"+getBinary(s[2]);
    }

    public static void main(String[] args) {
        String date="1900-01-01";
        System.out.println(convertDateToBinary(date));
    }
}
