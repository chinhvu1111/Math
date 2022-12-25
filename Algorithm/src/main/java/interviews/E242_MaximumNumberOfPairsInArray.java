package interviews;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class E242_MaximumNumberOfPairsInArray {

    public static int[] numberOfPairsTreeSet(int[] nums) {
        TreeSet<Integer> disticts=new TreeSet<>();
        int n= nums.length;
        int rsCount=0;

        for(int i=0;i<n;i++){
            if(disticts.contains(nums[i])){
                disticts.remove(nums[i]);
                rsCount++;
            }else {
                disticts.add(nums[i]);
            }
        }
        int[] rs=new int[2];
        rs[0]=rsCount;
        rs[1]=disticts.size();

        return rs;
    }

    public static int[] numberOfPairs(int[] nums) {
        int max= Arrays.stream(nums).max().getAsInt();
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int[] count=new int[max+1];
        int n=nums.length;

//        for (int num : nums) {
//            count[num]++;
//        }
        int numberPairs=0;
        for (int num : nums) {
            if (count[num] == 0) {
                count[num]++;
            } else {
                numberPairs++;
                count[num]--;
            }
        }
        int numberLeft=0;

        for (int num : nums) {
            if (count[num] != 0) {
                numberLeft++;
                count[num]=0;
            }
        }
        return new int[]{numberPairs, numberLeft};
    }

    public static int[] numberOfPairsRefactor(int[] nums) {
        int max= Arrays.stream(nums).max().getAsInt();
        int[] count=new int[max+1];

        for (int num : nums) {
            count[num]++;
        }
        int numberPairs=0;
        int numberLeft=0;
        for (int num : nums) {
            numberPairs+=count[num]/2;
            if (count[num] %2!=0) {
                count[num]++;
                numberLeft++;
            }
            count[num]=0;
        }
        return new int[]{numberPairs, numberLeft};
    }

    public static void main(String[] args) {
        //#Reference:
        //- Sort Characters By Frequency
        //- Top K Frequent Words
    }
}
