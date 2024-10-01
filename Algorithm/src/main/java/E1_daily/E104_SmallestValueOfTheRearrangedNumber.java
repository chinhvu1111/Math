package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E104_SmallestValueOfTheRearrangedNumber {

    public static long smallestNumber(long num) {
        boolean isNegativeNum = num<0;
        num=Math.abs(num);
        char[] nums=String.valueOf(num).toCharArray();
        int len = nums.length;
        Character[] tmpArr = new Character[len];
        for (int i = 0; i < len; i++) {
            tmpArr[i]=nums[i];
        }
        if(isNegativeNum){
            Arrays.sort(tmpArr, Collections.reverseOrder());
        }else{
            Arrays.sort(tmpArr);
        }
        if(len>1&&tmpArr[0]=='0'){
            int j=0;
            while (j<len&&tmpArr[j]=='0') j++;
            if(j<len){
                char tmp = tmpArr[0];
                tmpArr[0]=tmpArr[j];
                tmpArr[j]=tmp;
            }
        }
        long rs=0;

        for(int i=0;i<len;i++){
            rs=rs*10+tmpArr[i]-'0';
        }
        return isNegativeNum?rs*-1:rs;
    }

    public static long smallestNumberOptimization(long num) {
        boolean isNegativeNum = num<0;
        num=Math.abs(num);
        List<Integer> numList=new ArrayList<>();
        long tmp=num;
        int len = 0;

        while (tmp!=0){
            numList.add((int) (tmp % 10));
            tmp=tmp/10;
            len++;
        }

        if(isNegativeNum){
            numList.sort(Collections.reverseOrder());
        }else{
            Collections.sort(numList);
        }
        if(len>1&&numList.get(0)==0){
            int j=0;
            while (j<len&&numList.get(j)==0) j++;
            if(j<len){
                int temp = numList.get(0);
                numList.set(0, numList.get(j));
                numList.set(j, temp);
            }
        }
        long rs=0;

        for(int i=0;i<len;i++){
            rs=rs*10+numList.get(i);
        }
        return isNegativeNum?rs*-1:rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer num).
        //- Rearrange the digits of num such that its value is (minimized) and it (does not contain) (any leading zeros).
        //* Return the rearranged number (with minimal value).
        //- Note that (the sign of the number) does not change after (rearranging the digits).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //-10^15 <= num <= 10^15 <= MAX_LONG
        //
        //- Brainstorm
        //- Nếu sign:
        //  + : Tìm số min
        //  - : Tìm số max
        //- Rearrange to get minimize value:
        //- Không leading zero:
        //  + min nhưng !=0:
        //      + Sau đó ==> Sort tăng dần
        //
        //- Rearrange to get max value
        //- Swap 2 characters đầu tiên thì không đúng vì:
        //  + Có thể có nhiều chữ số 0
        //Ex:
        //10003
        //
        //1.1, Optimization
        //- Có cách nào dễ hơn không?
        //
        //
        //#Reference:
        //2225. Find Players With Zero or One Losses
        //1728. Cat and Mouse II
        //762. Prime Number of Set Bits in Binary Representation
//        long num = 310;
        long num = 4099;
        System.out.println(smallestNumber(num));
        System.out.println(smallestNumberOptimization(num));
    }
}
