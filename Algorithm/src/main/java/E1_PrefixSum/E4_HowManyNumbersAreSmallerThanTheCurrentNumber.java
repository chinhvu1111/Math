package E1_PrefixSum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class E4_HowManyNumbersAreSmallerThanTheCurrentNumber {

    public static class Node{
        int value;
        int index;
        public Node(int index, int value){
            this.index=index;
            this.value=value;
        }
        public String toString() {
            return index +" : "+value;
        }
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n=nums.length;
        List<Integer> sortList=new ArrayList<>();
        for(int e: nums){
            sortList.add(e);
        }
        Collections.sort(sortList);
        int[] result=new int[n];

        for(int i=0;i<n;i++){
            int index=findIndexGreaterValue(sortList, nums[i], 0, n);
            // System.out.printf("Index: %s, search value: %s\n", index, nums[i]);
            if(index!=-1){
                result[i]=index+1;
            }
        }
        return result;
    }

    public static int findIndexGreaterValue(List<Integer> sortList, int value, int low, int high){
        if(low>=high-1){
            if(high<sortList.size()&&sortList.get(high)<value){
                return high;
            }
            if(low<sortList.size()&&sortList.get(low)<value){
                return low;
            }
            return -1;
        }
        int mid=low + (high-low)/2;
        int rs=-1;

        if(sortList.get(mid)>=value){
            high=mid-1;
        }else{
            rs=mid;
            low=mid+1;
        }
        int temp=findIndexGreaterValue(sortList, value, low, high);
        if(temp!=-1){
            return temp;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- nums[]
        //* return result[i] là:
        //- Số lượng nums[j]<nums[i]
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
        //
        //#Reference:
        //315. Count of Smaller Numbers After Self
    }
}
