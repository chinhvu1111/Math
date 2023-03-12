package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class E280_3Sum_hashset {

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        List<List<Integer>> result=new ArrayList<>();

        for(int i=0;i<n;i++){
            if(nums[i]>0){
                break;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            twoSum(nums, i, -nums[i], result);
        }
        return result;
    }

    //Tìm các số có sum(a,b)= target.
    //- Dùng hashset thì sẽ bị cases đặc biệt:
    //arr={[4],1,2,2,2,3}, target=4
    //arr={0,1,2,3,4,5}, target=4
    //- Trong trường hợp này thì có những cases như sau:
    //+ 4,1,3
    //+ 4,(2)2,(3)2
    //+ 4,(3)2,(4)2
    //+ 4,(3)2,(5)2
    //==> 4,2,2 sẽ có 3 cases giống nhau.
    //==> Vì 3sum là chỉ cần distict nên ta làm làm kiểu dạng hashset thì vẫn có thể thu được kết quả.
    public static void twoSum(int[] nums, int index, int target, List<List<Integer>> result){
        HashSet<Integer> hashSet=new HashSet<>();
        HashSet<String> unique=new HashSet<>();

        for(int j=index+1;j<nums.length;j++){
//            System.out.printf("%s %s %s\n", target, nums[j], target-nums[j]);
            if(hashSet.contains(target-nums[j])&&!unique.contains((target-nums[j])+"_"+nums[j])){
                List<Integer> integers=new ArrayList<>(3);
                integers.add(nums[index]);
                integers.add(target-nums[j]);
                integers.add(nums[j]);
                result.add(integers);
                unique.add((target-nums[j])+"_"+nums[j]);
            }
            hashSet.add(nums[j]);
        }
    }

    public static void main(String[] args) {
        //-4,-1,-1,0,1,2
        int[] arr=new int[]{-1,0,1,2,-1,-4};
        System.out.println(threeSum(arr));
    }

}
