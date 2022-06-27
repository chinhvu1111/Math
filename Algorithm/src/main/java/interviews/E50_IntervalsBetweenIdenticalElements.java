package interviews;

import java.util.HashMap;

public class E50_IntervalsBetweenIdenticalElements {

    public static long[] getDistances(int[] arr) {
        int n=arr.length;
        long sumPrefixLeft[]=new long[n];
        long sumPrefixedRight[]=new long[n];
        long countPrefixLeft[]=new long[n];
        long countPrefixedRight[]=new long[n];
        HashMap<Integer, Long> valueIndexSumLeft=new HashMap<>();
        HashMap<Integer, Long> valueIndexSumRight=new HashMap<>();
        HashMap<Integer, Integer> countValueLeft=new HashMap<>();
        HashMap<Integer, Integer> countValueRight=new HashMap<>();

        for(int i=0;i<n;i++){
            int currentValueleft=arr[i];
            int currentValueRight=arr[n-i-1];
            Long prevSumLeft=valueIndexSumLeft.get(currentValueleft);
            Long prevSumRight=valueIndexSumRight.get(currentValueRight);
            Integer currentCountLeft=countValueLeft.get(currentValueleft);
            Integer currentCountRight=countValueRight.get(currentValueRight);

            if(prevSumLeft!=null){
                prevSumLeft+=i;
                currentCountLeft++;
            }else{
                prevSumLeft= (long) i;
                currentCountLeft=1;
            }
            if(prevSumRight!=null){
                prevSumRight+=n-i-1;
                currentCountRight++;
            }else {
                prevSumRight= (long) (n - i - 1);
                currentCountRight=1;
            }
            valueIndexSumLeft.put(currentValueleft, prevSumLeft);
            countValueLeft.put(currentValueleft, currentCountLeft);
            sumPrefixLeft[i]=prevSumLeft;
            countPrefixLeft[i]=currentCountLeft;

            valueIndexSumRight.put(currentValueRight, prevSumRight);
            countValueRight.put(currentValueRight, currentCountRight);
            sumPrefixedRight[n-i-1]=prevSumRight;
            countPrefixedRight[n-i-1]=currentCountRight;
        }
        long rs[]=new long[n];

        for(int i=0;i<n;i++){
//            int currentValue=arr[i];
            rs[i]+=Math.abs(sumPrefixLeft[i]- countPrefixLeft[i]*i);
            rs[i]+=Math.abs(sumPrefixedRight[i]- countPrefixedRight[i]*i);
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{2,1,3,1,2,3,3};
        int arr[]=new int[]{10,5,10,10};
        //Trong bài toán này sẽ bị âm nếu:
        //i-j --> + k ==> Kết quả có thể +/ >0 ==> Dẫn đến sai số
        getDistances(arr);
    }
}
