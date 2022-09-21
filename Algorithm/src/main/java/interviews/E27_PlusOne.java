package interviews;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;

public class E27_PlusOne {

    public static int[] plusOne(int[] digits) {
        int n=digits.length;
        List<Integer> rs=new ArrayList<>();
        int compenstation=1;

        for(int i=n-1;i>=0;i--){
            int currentDigit=(digits[i]+compenstation)%10;
            compenstation= Math.max(digits[i] + compenstation - 9, 0);
            rs.add(currentDigit);
            if(compenstation==0){
                break;
            }
        }
        int compenstationNumber=0;

        if(compenstation>0){
            rs.add(compenstation);
            compenstationNumber=1;
        }
        int rsArray[]=new int[n+compenstationNumber];
        int index=n+compenstationNumber-1;

        for(int i=0;i<rs.size();i++){
            rsArray[n+compenstationNumber-i-1]=rs.get(i);
            index=n+compenstationNumber-i-1;
        }

        for(int i=index-1;i>=0;i--){
            rsArray[i]=digits[i];
        }
        return rsArray;
    }

    public static int[] plusOneOptimized(int[] digits) {
        int n=digits.length;
        int compenstation=1;

        for(int i=n-1;i>=0;i--){
            int currentDigit=(digits[i]+compenstation)%10;
            compenstation= Math.max(digits[i] + compenstation - 9, 0);
            digits[i]=currentDigit;
            if(compenstation==0){
                return digits;
            }
        }
        int rsArray[]=null;
        int compenstationNumber=0;

        if(compenstation>0){
            rsArray=new int[digits.length+1];
            rsArray[0]=compenstation;
            compenstationNumber=1;
        }else{
            return digits;
        }
        for(int i=0;i<n;i++){
            rsArray[i+compenstationNumber]=digits[i];
        }
        return rsArray;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{4,3,2,1};
//        int arr[]=new int[]{9};
//        int arr[]=new int[]{0};
//        int arr[]=new int[]{1,2,3};
//        int arr[]=new int[]{9,9,9,9};
//        int arr[]=new int[]{};
//        plusOne(arr);
        //** Đề bài:
        //- Có 1 array biểu diễn 1 số + 1 vào --> return kết quả arr[]
        //
        //** Bài này tư duy như sau:
        //
        //Tối ưu như sau:
        //Bài này chỉ hơn thua với trường hợp, ta không dùng ArrayList --> (Không tạo array mới + copy elements)
        //- Array hiện tại sẽ được update lại value tại array[i] do không cần dùng nhiều lần
        //==> Chỉ tạo array mới khi số phần tử vượt hạn mức của Array đó --> +1
        //- compenstation=0 --> return ngay digits
        //
        plusOneOptimized(arr);
    }
}
