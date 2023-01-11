package interviews;

import java.util.HashMap;

public class E262_ContiguousArray {

    /*
    - Chỉ toàn là số 0 và 1 thế nên ==> Ta cần tìm array có sum consecutively
    - sum = length/2
    VD:
    [1,1,1,0,0,0,1]
    [1,(1,0,1,0,0,1),0]
      (1,1,2,2,2,3)
    1,2,2,3,3,3,4
    - Ta đang đứng ở vị trí index=6, sum-1=4
    - Để số chữ số 0 = số chữ số 1, có thể có các case như sau:
    sum-0=sum-1=4 ==> 4*2 >6 loại
    sum-0=sum-1=3 ==> 3*2=6 --> 6-3*2=0 --> index=- ==> sum = prefix[6] - prefix[0]=3 ==> thoả mãn
    sum-0=sum-1=1 ==>
     */
    public static int findMaxLengthSlow(int[] nums) {
        int n=nums.length;
        int[] prefixSum=new int[n];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i]=sum;
        }
        int rs=0;
        //currentSum=4 (index=6) --> Loại
        //currentSum=3 (index=6) -->
        for(int i=0;i<n;i++){
            int currentSum=prefixSum[i];

            //Optimization 1
            if(currentSum*2>i+1){
                currentSum=(i+1)/2;
            }
            //Optimization 4
            while (currentSum>=0&&currentSum*2>=rs){
                //Optimization 3
//                if(currentSum*2>i+1){
//                    currentSum--;
//                    continue;
//                }
                int j=i-currentSum*2;
                int leftValue=j==-1?0:prefixSum[j];

                if(i>=j&&prefixSum[i]-leftValue==currentSum){
//                    System.out.println(currentSum);
                    rs=Math.max(rs, i-j);
                    //Optimization 2
                    break;
                }
                currentSum--;
            }
        }
        return rs;
    }

    public static int findMaxLengthWrong(int[] nums) {
        int n=nums.length;
        int sum=0;
        int rs=0;

        HashMap<Integer, Integer> sumToStartingPos=new HashMap<>();
        for(int i=0;i<n;i++){
            sum+=nums[i];
            if(!sumToStartingPos.containsKey(sum)){
                sumToStartingPos.put(sum, i);
                if(sum%2==0&&sumToStartingPos.containsKey(sum/2)){
                    rs=Math.max(rs, i-sumToStartingPos.get(sum/2)+1);
//                    System.out.printf("%s %s\n", rs, i-sumToStartingPos.get(sum/2)+1);
                }
            }
        }
        return rs;
    }


    public static int findMaxLength(int[] nums) {
        int n=nums.length;
        int sum=0;
        int rs=0;

        HashMap<Integer, Integer> sumToStartingPos=new HashMap<>();
        sumToStartingPos.put(0, -1);
        for(int i=0;i<n;i++){
            if(nums[i]==1){
                sum++;
            }else if(nums[i]==0){
                sum--;
            }
            if(sumToStartingPos.containsKey(sum)){
               rs=Math.max(rs, i-sumToStartingPos.get(sum));
            }else{
                sumToStartingPos.put(sum, i);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[] nums=new int[]{1,0,0,1,0};
        int[] nums=new int[]{0,1};

        /*
        int[] nums=new int[]{
                1,1,1,1,1,1,1,0,0,0,0,1,1,0,1,0,0,1,1,1,1,1,1,1,1,
                1,0,0,0,0,1,0,0,0,0,1,0,1,0,0,0,1,1,0,0,0,0,1,0,0,
                1,1,1,1,1,0,0,1,0,1,1,0,0,0,1,0,0,0,1,1,1,0,1,1,0,
                1,0,0,1,1,0,1,0,0,1,1,1,0,0,1,0,1,1,1,0,0,1,0,1,1};
         */
        System.out.println(findMaxLengthSlow(nums));
        System.out.println(findMaxLength(nums));
        //#Reference
        //526. Beautiful Arrangement
        //325. Maximum Size Subarray Sum Equals k
    }
}
