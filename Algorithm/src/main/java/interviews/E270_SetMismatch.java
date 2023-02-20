package interviews;

import java.util.Arrays;
import java.util.HashMap;

public class E270_SetMismatch {

    public static int[] findErrorNums(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        int sum=nums[0];
        int valueWrong=0;

        for(int i=1;i<n;i++){
            if(nums[i]==nums[i-1]){
                valueWrong=nums[i];
//                break;
            }
            sum+=nums[i];
        }
        int correctVal=n*(n+1)/2-sum+valueWrong;

//        for(int i=0;i<n;i++){
//            if(i+1!=nums[i]){
//                return new int[]{valueWrong, i+1};
//            }
//        }

        return new int[]{valueWrong, correctVal};
    }

    public static int[] findErrorNumsHashMap(int[] nums) {
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for(int c: nums){
            mapCount.put(c, mapCount.getOrDefault(c, 0)+1);
        }
        int missingNumber=0;
        int dupNumber=0;
        for(int i=1;i<=nums.length;i++){
            if(mapCount.containsKey(i)){
                if(mapCount.get(i)==2){
                    dupNumber=i;
                }
            }else{
                missingNumber=i;
            }
        }
        return new int[]{dupNumber, missingNumber};
    }

    public static int[] findErrorNumsConstanSpace(int[] nums) {
        int missingNum=0;
        int dupNum=0;

        for(int c:nums){
            if(nums[Math.abs(c)-1]>=0){
                nums[Math.abs(c)-1]*=-1;
            }else{
                dupNum=Math.abs(c);
            }
        }
        for(int i=1;i<=nums.length;i++){
            if(nums[i-1]>0){
                missingNum=i;
            }
        }
        return new int[]{dupNum, missingNum};
    }

    public static void main(String[] args) {
        //
//        int[] arr=new int[]{1,2,2,4};
//        int[] arr=new int[]{1,1};
//        int[] arr=new int[]{2,2};
        int[] arr=new int[]{3,2,2};
//        int[] arr=new int[]{1,2,3,3,5};
//        int[] arr=new int[]{1,5,3,2,2,7,6,4,8,9};
        int[] rs=findErrorNums(arr);
        System.out.println(rs[0]+" "+rs[1]);

        int[] rs1=findErrorNumsHashMap(arr);
        System.out.println(rs1[0]+" "+rs1[1]);

        int[] rs2=findErrorNumsConstanSpace(arr);
        System.out.println(rs2[0]+" "+rs2[1]);
    }
}
