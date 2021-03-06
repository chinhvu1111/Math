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
        //T???i ??u nh?? sau:
        //B??i n??y ch??? h??n thua v???i tr?????ng h???p, ta kh??ng d??ng ArrayList --> (Kh??ng t???o array m???i + copy elements)
        //- Array hi???n t???i s??? ???????c update l???i value t???i array[i] do kh??ng c???n d??ng nhi???u l???n
        //==> Ch??? t???o array m???i khi s??? ph???n t??? v?????t h???n m???c c???a Array ???? --> +1
        //- compenstation=0 --> return ngay digits
        //
        plusOneOptimized(arr);
    }
}
