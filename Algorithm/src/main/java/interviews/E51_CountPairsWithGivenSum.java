package interviews;

import java.util.Arrays;
import java.util.HashMap;

public class E51_CountPairsWithGivenSum {

    public static int getPairsCount(int arr[], int k){
        int n=arr.length;
        int max= Arrays.stream(arr).max().getAsInt();
        HashMap<Integer, Integer> countMap=new HashMap<>();
        //1,Bài này chú ý các cases sau:
        //1.1, 1,1,1,1 : rs= 3 + 2 + 1, khi k=2 --> các số đối xứng nên RS đơn thuần là tổng của (1 --> count-1)
        //1.2, 1,1,2,3,3 :
        //1.3, 1,3,3 : rs= 1 + 1
        //2, Bài này có cần sort hay không?
        //- Sort chỉ để việc value bù đằng sau chưa xuất hiện đằng trước.
        //VD: arr[i] < arr[j] ==> arr[j] chưa xuất hiện đằng trước.
        //==> Với dạng bài này không cần sort.
        //VD:
        int rs=0;

        for(int i=0;i<n;i++){
            int currentValue=arr[i];
            int subtract=k-currentValue;
            Integer value=0;

            if(subtract>=0){
                value=countMap.get(subtract);
                if(value==null){
                    value=0;
                }
            }
            rs+=value;
            Integer prevCount=countMap.get(currentValue);

            if(prevCount==null){
                prevCount=1;
            }else{
                prevCount++;
            }
            countMap.put(currentValue, prevCount);
        }
        return rs;
    }

    public static int getPairsCount2(int arr[], int k){
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int n=arr.length;

        for(int i=0;i<n;i++){
            int currentValue=arr[i];

            if(mapCount.containsKey(currentValue)){
                continue;
            }
            mapCount.put(currentValue, mapCount.get(currentValue)+1);
        }
        int rs=0;

        for(int i=0;i<n;i++){
            int currentVal=arr[i];
            Integer val=mapCount.get(k-currentVal);

            if(val!=null){
                rs+=val;
            }
            if(k-arr[i]==arr[i]){
                rs--;
            }
        }
        return rs/2;
    }

    public static void main(String[] args) {
        int arr[] = { 1, 5, 7, -1, 5 };
        //Ta tư duy như sau:
        //Ở đây ta không cần sort :
        //Vì:
        //1, Ta áp dụng tư duy liên quan đến PHÉP LỰA CHỌN:
        //VD: 1,3,1,3
        //count : 1,1,2,2
        //Rs    : 0,1 (0+1) ,2 (1+1) ,4 (2+2)
        //Step 1:
        //Get 1 ta không có phép chọn nào --> rs+0
        //Get 3 ta có 1 phép chọn cho chữ số 1 --> rs++
        //Get 1 ta có 1 phép chọn cho chữ số 3 --> rs++ (Số phép chọn là số lượng số chữ số 3)
        System.out.println(getPairsCount(arr,6));
    }
}
