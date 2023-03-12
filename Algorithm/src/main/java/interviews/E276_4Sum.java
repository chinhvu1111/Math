package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E276_4Sum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int n=nums.length;
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(nums);
//        System.out.println(nums[n-1]);

        for(int i=0;i<n;i++){
            if(target<0&&target<nums[i]&&nums[i]>0){
                break;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            long currentTarget=target-nums[i];
//            System.out.println(currentTarget);
            threeSum(i, nums, currentTarget, result);

        }
        return result;
    }

    public static void threeSum(int index, int[] nums, long target, List<List<Integer>> result){
        if(nums.length<3){
            return;
        }
        int n=nums.length-1;

        for(int i=index+1;i<n-1;i++){
            if((i>index+1&&nums[i]==nums[i-1])){
                continue;
            }
            int j=i+1;
            int k=nums.length-1;

            while (j<k){
                long currentSum=nums[i]+nums[k];
//                System.out.printf("%s %s\n",currentSum, target-nums[j]);
                if(currentSum==target-nums[j]){
                    List<Integer> currentRs = new ArrayList<>();
                    currentRs.add(nums[index]);
                    currentRs.add(nums[i]);
                    currentRs.add(nums[j]);
                    currentRs.add(nums[k]);
                    result.add(currentRs);
//                    System.out.printf("%s(%s) %s(%s) %s(%s) %s(%s)\n", index, nums[index], i, nums[i], j, nums[j], k, nums[k]);
                }

                if(currentSum>target-nums[j]){
                    do{
                        k--;
                    }while (j<k&&nums[k]==nums[k+1]);
                }else if(currentSum<target-nums[j]){
                    do{
                        j++;
                    }while (j<k&&nums[j]==nums[j-1]);
                }else if(nums[j]>0){
                    do{
                        k--;
                    }while (j<k&&nums[k]==nums[k+1]);
                }else{
                    do{
                        j++;
                    }while (j<k&&nums[j]==nums[j-1]);
                }
            }

        }
    }

    public static void main(String[] args) {
        //-2,-1,0,0,1,2
//        int[] nums=new int[]{1,0,-1,0,-2,2};
//        int target=0;
        //Case 2:
        //- Case này do nums[i]==num[index] (Index là index của số thứ 1) ==> số thứ nhất thì ta có thể giống số thứ 2 được (4 số)
        //--> Ta có thể bỏ qua case đó (i>index+1) là được.
//        int[] nums=new int[]{2,2,2,2,2};
//        int target=8;
        //Case 3:
        //- target<0 --> nums[i]>0 : Chắc chắn sẽ không tìm được tiếp
        //- target>0 --> nums[i]
//        int[] nums=new int[]{1000000000,1000000000,1000000000,1000000000};
//        int target=-294967296;
        int[] nums=new int[]{-1000000000,-1000000000,1000000000,-1000000000,-1000000000};
        int target=294967296;
//        int[] nums=new int[]{-2,0,0,3,3,-1};
//        int target=5;
        System.out.println(fourSum(nums, target));
        //** Đề bài:
        //- Tìm danh sách các array sao cho:
        //0 <= a, b, c, d < n
        //a, b, c, and d are distinct.
        //nums[a] + nums[b] + nums[c] + nums[d] == target
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //- Xuất phát từ 3sum --> Ta chỉ cần for thêm 1 loop + Sau đó tìm các phần tử (a,b,c) sao cho:
        //+ a+b+c=target-d
        //- Chú ý refactor lại các mục sum bên trong.
    }
}
