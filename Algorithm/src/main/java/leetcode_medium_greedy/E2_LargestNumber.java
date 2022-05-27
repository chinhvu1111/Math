package leetcode_medium_greedy;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class E2_LargestNumber {

    public static String largestNumber(int[] nums) {
        List<Integer> numsList=Arrays.stream(nums).boxed().collect(Collectors.toList());
        numsList.sort((t1, t2) -> {
            int length1 = t1==0?1:(int) (Math.log10(t1) + 1);
            int length2 = t2==0?1:(int) (Math.log10(t2) + 1);
            int number1= (int) (t1*Math.pow(10,length2)+t2);
            int number2= (int) (t2*Math.pow(10, length1)+t1);

            if (length1 >= length2) {
//                if((length1 - length2)*10 * t2 - t1>=0 && number2-number1>=0){
//                    return 1;
//                }else{
//                    return -1;
//                }
                if((Math.pow(10,length1 - length2) * t2 - t1<0) && number2-number1<=0){
                    return -1;
                }else{
                    return 1;
                }
//                return (length1 - length2)*10 * t2 - t1>=0 && number2-number1>=0;
            }
            if(Math.pow(10, length2 - length1) * t1 -t2 >0 || number1-number2>=0){
                return -1;
            }else{
                return 1;
            }
//            return t2 - (length2 - length1) *10 * t1;
        });
        int sumNum=0;
        StringBuilder rs=new StringBuilder();
        for(Integer v: numsList){
            rs.append(v);
            sumNum+=v;
        }

        return sumNum==0?"0":rs.toString();
    }

    public static String largestNumberOptimize(int[] nums) {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int oneLen = o1.length();
                int twoLen = o2.length();
                for (int i = 0; i < o1.length() + o2.length(); i++) {
                    char one = i < o1.length() ? o1.charAt(i) : o2.charAt(i % oneLen);
                    char two = i < o2.length() ? o2.charAt(i) : o1.charAt(i % twoLen);
                    if (one != two) return two - one;
                }
                return 0;
            }
        };
        String result = Arrays.stream(nums).mapToObj(String::valueOf).sorted(comparator).collect(Collectors.joining());
        if (result.charAt(0) == '0') return "0";
        return result;
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
        //NOTE:
        //Sort :
        // return 1 : Tức là sẽ t1>t2 --> Sắp xếp theo t2,t1
        // return -1 : Tức là sẽ t1<t2 --> Sắp xếp theo t1,t2
//        int arr[]=new int[]{2,1};
        //Case 5:
//        int arr[]=new int[]{1113,111311};
        //111300
        //111311
        //Mặc dù 111300 < 111311
        //==> Nhưng để 1113 trước --> Đúng.
        //Case 6:
        //[1,2,3,4,5,6,7,8,9,0]
        //Độ dài số: Math.log10(t1) + 1
        //==> Chỉ (đúng với sô !=0)
//        int arr[]=new int[]{1,2,3,4,5,6,7,8,9,0};
//        Integer[] sorted = Arrays.stream(arr).boxed().toArray(Integer[]::new);
//        Arrays.sort(sorted, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer t1, Integer t2) {
//                return t1 - t2;
//            }
//        });
        //Case 7:
        //Case này khác trick khi all number=0
        //--> Nếu concanate strings --> Lỗi.
        //Example: [0,0,0] --> return 0.
        int arr[]=new int[]{0,0};
        System.out.println(largestNumber(arr));
    }
}
