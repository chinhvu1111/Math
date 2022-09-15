package interviews;

import java.util.HashSet;

public class E133_SumOfSquareNumbers_2_pointers {

    public static boolean judgeSquareSum(int c) {
        int low=0;
        int high= (int) Math.sqrt(c);

        while (low<=high){
            if(high*high<c-low*low){
                low++;
            }else if(high*high>c-low*low){
                high--;
            }else return true;
        }
        System.out.printf("%s %s,", low, high);
        return false;
    }

    public static boolean judgeSquareSumHashSet(int c) {
        int high= (int) Math.sqrt(c);
        HashSet<Integer> hashSet=new HashSet<>();

        for(int i=0;i<=high;i++){
            hashSet.add(i*i);
        }
        for(int i=0;i<=high;i++){
            if(hashSet.contains(c-i*i)){
                return true;
            }
        }
        return false;
    }

    public static boolean judgeSquareSumJump(int c) {
        int high= (int) Math.sqrt(c);

        for(int i=0;i<=high;i++){
            double remainder=  Math.sqrt((c-i*i));

            if(remainder==(int)remainder){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        int n=5;
//        int n=3;
//        int n=4;
//        int n=2;
        int n=2147483600;
        //fast (binary search tận dụng tìm kiếm i*i </ >= target)
        System.out.println(judgeSquareSum(n));
        //more and more slower
        //Tận dụng cast [ Math.sqrt((c-i*i))= (int)Math.sqrt((c-i*i)) ]
        System.out.println(judgeSquareSumJump(n));
        //more slower
        //Lưu vào hashet (1 --> sqrt(n))
        //hashSet.add(i*i) ==> for(i*i) --> Check hashSet có tồn tại hay chưa.
        System.out.println(judgeSquareSumHashSet(n));
    }
}
