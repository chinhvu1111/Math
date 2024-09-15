package contest;

public class E175_FindTheKeyOfTheNumbers {

    public static int generateKey(int num1, int num2, int num3) {
        StringBuilder rs=new StringBuilder();
        int count=0;

        while (num1>0||num2>0||num3>0){
            int curMinDigit=Integer.MAX_VALUE;
            curMinDigit=Math.min(curMinDigit, num1%10);
            num1=num1/10;
            curMinDigit=Math.min(curMinDigit, num2%10);
            num2=num2/10;
            curMinDigit=Math.min(curMinDigit, num3%10);
            num3=num3/10;
            rs.append(curMinDigit);
            count++;
        }
        while (count<4){
            rs.append("0");
            count++;
        }
        rs.reverse();
        return Integer.parseInt(rs.toString());
    }

    public static void main(String[] args) {
//        int num1 = 1, num2 = 10, num3 = 1000;
//        int num1 = 987, num2 = 879, num3 = 798;
        int num1 = 1, num2 = 2, num3 = 3;
        System.out.println(generateKey(num1, num2, num3));
    }
}
