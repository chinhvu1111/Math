package mock;

import java.util.HashMap;

public class Test_1_microsoft {
    //1.
    //1.0, Idea
    //- Các yếu tố đề bài
    //+ Length của string <10^4
    //+ Vì chiều dài lớn --> Ta chỉ có thể tìm cách append string
    //- a/b ==> a/b=0
    //VD:
    //4/333
    //+ 4/333=0 : 0. ==> chỉ thêm . 1 lần
    //+ 40/333=0
    //+ 400/333=1 dư 67
    //+ dư*10/333=2 --> dư 4 ==> Quay trở lại việc lấy 4 chia.
    //--> Để đỡ phức tạp mình sẽ tìm kết quả trước --> Sau đó tính tiếp
    //
    //0(4).0(40)1(400)2
    public static String fractionToDecimal(int numerator, int denominator) {
        int i=10000;
        if(numerator==0){
            return "0";
        }
        StringBuilder rs=new StringBuilder();
        boolean isDot=false;
        int index=0;
        long newNumerator=numerator;
        long newDenominator=denominator;

        if(numerator<0&&denominator>=0||numerator>=0&&denominator<0){
            rs.append("-");
            index++;
            if(numerator<0){
                newNumerator=newNumerator*-1;
            }
            if(denominator<0){
                newDenominator=newDenominator*-1;
            }
        }
        HashMap<Long, Integer> mapPos=new HashMap<>();

        while (i>=0){
            long division=newNumerator/newDenominator;
            long residual=newNumerator%newDenominator;
            newNumerator=residual*10;
            rs.append(division);

            if(mapPos.containsKey(newNumerator)){
                rs.insert(mapPos.get(newNumerator), "(");
                rs.append(")");
                break;
            }
            if(!isDot&&residual!=0){
                rs.append(".");
                isDot=true;
                index++;
            }else if(residual==0){
                break;
            }
            i--;
            //Phần này để lưu vị trí string có thể lặp lại
            index+=String.valueOf(division).length();
            mapPos.put(newNumerator, index);
        }
        return rs.toString();
    }

    public static void main(String[] args) {
//        int numerator = 4, denominator = 333;
//        int numerator = 1, denominator = 2;
//        int numerator = 2, denominator = 1;
//        int numerator = -50, denominator = 8;
        //-0.58(3)
//        int numerator = -7, denominator = 12;
        int numerator = -1, denominator = -2147483648;
//        int numerator = 2147483647, denominator = 37;
        System.out.println(fractionToDecimal(numerator, denominator));
    }
}
