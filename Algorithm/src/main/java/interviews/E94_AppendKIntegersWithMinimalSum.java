package interviews;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class E94_AppendKIntegersWithMinimalSum {

    public static long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long rs=0;
        int n=nums.length;
        long valueLeft=0;
        int count=0;

        for(int i=0;i<n&&count<k;i++){
            if(nums[i]==valueLeft){
                continue;
            }
            long leftV=valueLeft*(valueLeft+1)/2;
            long rightV;

            if(nums[i]-valueLeft-1<k-count){
                count+=nums[i]-valueLeft-1;
                rightV= (long) (nums[i] - 1) *nums[i]/2;
            }else {
                rightV=(valueLeft+k-count)*(valueLeft+k-count+1)/2;
                count=k;
            }
            rs+=rightV-leftV;
            //
            valueLeft=nums[i];
        }
        if(count<k){
            long leftValue= (long) nums[n - 1] *(nums[n-1]+1)/2;
            long rightValue= (long) (nums[n - 1] + k - count) *(nums[n-1]+k-count+1)/2;
            rs+=rightValue-leftValue;
        }
        return rs;
    }

    public static long minimalKSumOptimize(int[] nums, int k) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        long sum = 0;

        for (int num: nums) {
            if (!set.contains(num) && num <= k) {
                k++;
                sum += num;
            }
            set.add(num);
        }

        long res = (long)(1 + k) * k / 2 - sum;
        return res;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,4,25,10,25};
//        int arr[]=new int[]{5,6};
//        int k=2;
//        int k=6;
        //Case 1:
        int arr[]=new int[]{96,44,99,25,61,84,88,18,19,33,60,86,52,19,32,47,35,50,94,17,29,98,22,21,72,100,40,84};
        int k=35;
//        int arr[]=new int[]{1,5,5,5,6,8,10};
//        int k=5;
        System.out.println(minimalKSum(arr, k));
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Vì điền thêm unique số vào sao cho MIN SUM
        //--> Ta chỉ điền các số nhỏ nhất vào
        //
    }
}
