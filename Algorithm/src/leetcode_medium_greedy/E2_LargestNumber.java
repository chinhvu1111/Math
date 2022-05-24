package leetcode_medium_greedy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class E2_LargestNumber {

    public static String largestNumber(int[] nums) {
        List<Integer> numsList=Arrays.stream(nums).boxed().collect(Collectors.toList());
        numsList.sort((t1, t2) -> {
            int length1 = (int) (Math.log10(t1) + 1);
            int length2 = (int) (Math.log10(t2) + 1);
            int number1= (int) (t1*Math.pow(10,length2)+t2);
            int number2= (int) (t2*Math.pow(10, length1)+t1);

            if (length1 >= length2) {
//                if((length1 - length2)*10 * t2 - t1>=0 && number2-number1>=0){
//                    return 1;
//                }else{
//                    return -1;
//                }
                if((length1 - length2)*10 * t2 - t1<0 || number2-number1<=0){
                    return -1;
                }else{
                    return 1;
                }
//                return (length1 - length2)*10 * t2 - t1>=0 && number2-number1>=0;
            }
            if((length2 - length1) *10 * t1 -t2 >0 || number1-number2>=0){
                return -1;
            }else{
                return 1;
            }
//            return t2 - (length2 - length1) *10 * t1;
        });
        StringBuilder rs=new StringBuilder();
        for(Integer v: numsList){
            rs.append(v);
        }
        return rs.toString();
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{3,30,34,5,9};
        //Case 1:
        //[111311, 1113]
//        int arr[]=new int[]{111311, 1113};
        //Output: "111311|1113"
        //Expect: "1113|111311"
        //Case 2: khi ta cần so sánh sự kết hợp giữa >= 2 elements trở lên.
        //VD: (830-8300 < 8308-830)
//        int arr[]=new int[]{10,2};
//        int arr[]=new int[]{8308,8308,830};
        //Case 3: Sai case {3, 30}
        //Wrong : 303
        //Expect : 330
        //--> cần sửa lại equal condition.
//        int arr[]=new int[]{3,30,34,5,9};
        //Case 4: {111311, 1113}
        //Sai case cùng khi nhân lần *10 > nhưng bù vào số sau sẽ bị sai.
//        int arr[]=new int[]{111311, 1113};
        int arr[]=new int[]{2,1};
        System.out.println(largestNumber(arr));
    }
}
