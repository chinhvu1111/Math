package contest;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;

public class E92_FindTheIntegerAddedToArrayII {

    public static int minimumAddedInteger(int[] nums1, int[] nums2) {
        int n=nums1.length;
        TreeSet<Pair<Integer, Integer>> nums1Tree=new TreeSet<>((a, b) -> a.getKey()-b.getKey());
        TreeSet<Pair<Integer, Integer>> nums2Tree=new TreeSet<>((a, b) -> a.getKey()-b.getKey());
        HashMap<Integer, Integer> nums1Count=new HashMap<>();
        HashMap<Integer, Integer> nums2Count=new HashMap<>();

        for(int e:nums1){
            if(nums1Count.containsKey(e)){
                nums1Count.put(e, nums1Count.get(e)+1);
            }else{
                nums1Count.put(e, 1);
            }
        }
        for(int e:nums2){
            if(nums2Count.containsKey(e)){
                nums2Count.put(e, nums2Count.get(e)+1);
            }else{
                nums2Count.put(e, 1);
            }
        }
        for(int key: nums1Count.keySet()){
            nums1Tree.add(new Pair<>(key, nums1Count.get(key)));
        }
        for(int key: nums2Count.keySet()){
            nums2Tree.add(new Pair<>(key, nums2Count.get(key)));
        }
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                int removeFirst=nums1[i];
                int removeSecond=nums1[j];
                int curCountFirst=nums1Count.get(removeFirst);
                int curCountSecond=nums1Count.get(removeSecond);
                Pair<Integer, Integer> curFirstFreq=new Pair<>(removeFirst, curCountFirst);
                Pair<Integer, Integer> curSecondFreq=new Pair<>(removeSecond, curCountSecond);
                if(removeFirst!=removeSecond){
                    if(curCountFirst==1){
                        nums1Tree.remove(curFirstFreq);
                    }else{
                        nums1Tree.remove(new Pair<>(removeFirst, curCountFirst));
                        nums1Tree.add(new Pair<>(removeFirst, curCountFirst-1));
                    }
                    if(curCountSecond==1){
                        nums1Tree.remove(curSecondFreq);
                    }else{
                        nums1Tree.remove(new Pair<>(removeSecond, curCountSecond));
                        nums1Tree.add(new Pair<>(removeSecond, curCountSecond-1));
                    }
                }else{
                    if(curCountFirst==2){
                        nums1Tree.remove(curFirstFreq);
                    }else{
                        nums1Tree.remove(new Pair<>(removeFirst, curCountFirst));
                        nums1Tree.add(new Pair<>(removeFirst, curCountFirst-2));
                    }
                }
                Iterator<Pair<Integer, Integer>> iterator1 = nums1Tree.iterator();
                Iterator<Pair<Integer, Integer>> iterator2 = nums2Tree.iterator();
                Pair<Integer, Integer> element2=iterator2.next();
                Pair<Integer, Integer> element1=iterator1.next();
                int x=element2.getKey()-element1.getKey();
                if(!Objects.equals(element2.getValue(), element1.getValue())){
                    nums1Tree.remove(curFirstFreq);
                    nums1Tree.add(curFirstFreq);
                    nums1Tree.remove(curSecondFreq);
                    nums1Tree.add(curSecondFreq);
                    continue;
                }
                boolean isValid=true;
                while (iterator1.hasNext()&& iterator2.hasNext()){
                    Pair<Integer, Integer> curFreq1=iterator1.next();
                    Pair<Integer, Integer> curFreq2=iterator2.next();
                    if(curFreq2.getKey()-curFreq1.getKey()!=x || !Objects.equals(curFreq2.getValue(), curFreq1.getValue())){
                        isValid=false;
                        break;
                    }
                }
                if(isValid){
                    rs=Math.min(rs, x);
                }
                nums1Tree.remove(curFirstFreq);
                nums1Tree.add(curFirstFreq);
                nums1Tree.remove(curSecondFreq);
                nums1Tree.add(curSecondFreq);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //-
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //3 <= nums1.length <= 200
        //nums2.length == nums1.length - 2
        //0 <= nums1[i], nums2[i] <= 1000
        //The test cases are generated in a way that there is an integer x
        // such that nums1 can become equal to nums2 by removing two elements and adding x to each element of nums1.
        //==> Không quá lớn.
        //
        //- Brainstorm
        //- Nên remove 2 elements nào?
        //  ==> Scan all
        //- Làm sao để lấy được min x
        //
        //
//        int[] nums1 = {4,20,16,12,8}, nums2 = {14,18,10};
        int[] nums1 = {3,5,5,3}, nums2 = {7,7};
//        int[] nums1 = {7,2,6,8,7}, nums2 = {7,6,5};
        //7,2,6,8,7
        // => -1
        //6,1,5,7,6
        //==> (1,4)
        //7,6,5
        System.out.println(minimumAddedInteger(nums1, nums2));
    }
}
