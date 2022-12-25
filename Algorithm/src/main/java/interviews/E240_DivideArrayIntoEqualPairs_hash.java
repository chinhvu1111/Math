package interviews;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class E240_DivideArrayIntoEqualPairs_hash {

    public static boolean divideArrayUsingArray(int[] nums) {
        int max= Arrays.stream(nums).max().getAsInt();
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int[] count=new int[max+1];
        int n=nums.length;

        for(int i=0;i<n;i++){
//            Integer currentCount=mapCount.get(nums[i]);
//            if(currentCount==null){
//                currentCount=1;
//            }else{
//                currentCount++;
//            }
//            mapCount.put(nums[i], currentCount);
            count[nums[i]]++;
        }
        for(int i=0;i<n;i++){
            if(count[nums[i]]%2==1){
                return false;
            }
        }
        return true;
    }

    public static boolean divideArrayTreeSet(int[] nums) {
        Set<Integer> set=new HashSet<>();
        int n=nums.length;

        for(int i=0;i<n;i++){
            if(set.contains(nums[i])){
                set.remove(nums[i]);
            }else{
                set.add(nums[i]);
            }
        }
        return set.isEmpty();
    }

    public static void main(String[] args) {
        int[] arr=new int[]{3,2,3,2,2,2};
        System.out.println(divideArrayTreeSet(arr));
        //
        //** Đề bài:
        //- Liệu có thể chia array thành các cặp giống nhau được không
        //VD: (3,3), (2,2),...
        //
        //** Tư duy như sau:
        //1,
        //1.1, Dùng hashmap xe các số có số lần xuất hiện chẵn hay không (%2==0)
        //--> Có 1 số có số lần xuất hiện lẻ %2==1
        //==> return false
        //1.2, Cách 2 là có thể dùng treeset để add + remove các phần tử
        //==> Cuối cùng check set có empty hay không
        //+ Empty
        //+ Not empty : Không thể chia được.
    }
}
