package contest;

import java.util.*;

public class E154_MinimumArrayChangesToMakeDifferencesEqual {

    //Target >=0
    //|x-y|=target
    //+ x-y==target
    //+ y-x==target
    public static int replaceCount(int x, int y, int k, int target){
        if(Math.abs(x-y)==target){
            return 0;
        }
        //4-2
        if(x-y>=target){
            //Decrease X
            int replacedXVal=y+target;
            if(0<=replacedXVal&&replacedXVal<=k){
                return 1;
            }
            //Increase Y
            int replacedYVal=x-target;
            if(0<=replacedYVal&&replacedYVal<=k){
                return 1;
            }
            return 2;
        }else{
            //Decrease Y
            int replacedYVal=x-target;
            if(0<=replacedYVal&&replacedYVal<=k){
                return 1;
            }
            //Increase X
            int replacedXVal=y+target;
            if(0<=replacedXVal&&replacedXVal<=k){
                return 1;
            }
            return 2;
        }
    }

    public static int minChangesWrong(int[] nums, int k) {
        int n=nums.length;
        int low=0, high=n-1;
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        while (low<high){
            int curSub=Math.abs(nums[low]-nums[high]);
            mapCount.put(curSub, mapCount.getOrDefault(curSub, 0)+1);
            low++;
            high--;
        }
        int maxCountSubTract=Integer.MIN_VALUE;
        int maxSubTract = 0;
//        System.out.println(mapCount);

        for(Map.Entry<Integer, Integer> e: mapCount.entrySet()){
            if(maxCountSubTract<e.getValue()){
                maxSubTract=e.getKey();
                maxCountSubTract=e.getValue();
            }
        }
        List<Integer> changedCount=new ArrayList<>();
        for(Map.Entry<Integer, Integer> e: mapCount.entrySet()){
            if(maxCountSubTract==e.getValue()){
                changedCount.add(e.getKey());
            }
        }
//        System.out.println(maxSubTract);
        int rs=Integer.MAX_VALUE;

        for (int curCount: changedCount){
            low=0;
            high=n-1;
            int curRs=0;
            while (low<high){
                if(Math.abs(nums[low]-nums[high])!=curCount){
                    curRs+=Math.min(replaceCount(nums[low], nums[high], k, curCount), replaceCount(nums[high], nums[low], k, curCount));
                }
                low++;
                high--;
            }
            rs=Math.min(rs, curRs);
        }
        return rs;
    }

    public static int minChanges(int[] nums, int k) {
        int n=nums.length;
        int low=0, high=n-1;
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        List<int[]> listSubtraction=new ArrayList<>();

        while (low<high){
            int curSub=Math.abs(nums[low]-nums[high]);
            mapCount.put(curSub, mapCount.getOrDefault(curSub, 0)+1);
            low++;
            high--;
        }
        int maxCountSubTract=Integer.MIN_VALUE;
        int maxSubTract = 0;
//        System.out.println(mapCount);

        for(Map.Entry<Integer, Integer> e: mapCount.entrySet()){
            listSubtraction.add(new int[]{e.getKey(), e.getValue()});
        }
        Collections.sort(listSubtraction, (a, b) -> b[1]-a[1]);

//        System.out.println(maxSubTract);
        int rs=Integer.MAX_VALUE;

        for (int[] e: listSubtraction){
            int curCount=e[0];
            low=0;
            high=n-1;
            int curRs=0;
            while (low<high){
                if(Math.abs(nums[low]-nums[high])!=curCount){
                    curRs+=Math.min(replaceCount(nums[low], nums[high], k, curCount), replaceCount(nums[high], nums[low], k, curCount));
                }
                low++;
                high--;
            }
            if(curRs>rs){
                break;
            }
            rs= curRs;
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums of size n where (n is even), and (an integer k).
        //- You can perform some changes on the array, where in one change you can replace (any element)
        // in the array with (any integer) in the range from
        //  + (0 to k).
        //- You need to perform some changes (possibly none) such that the final array satisfies the following condition:
        //  + There exists an integer X such that abs(a[i] - a[n - i - 1]) = X for all (0 <= i < n).
        //  + (Hiệu 2 số bất kỳ == X) (All)
        //  Ex:
        //  0,(1),2,(1),4
        //  arr[1]=1
        //  arr[n-1-1] = arr[5-1-1] = arr[3]
        //  + first và last thôi.
        //  ==> Subtraction khi đi từ left -> <- right
        //  ==> All là == nhau.
        //* Return (the minimum number of changes) required to satisfy the above condition.
        //==> Mảng đối xứng hiệu.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= n == nums.length <= 10^5
        //n is even.
        //0 <= nums[i] <= k <= 10^5
        //==> Time: O(n)
        //
        //** Brainstorm
        //Example 1:
        //
        //Input: nums = [1,0,1,2,4,3], k = 4
        //Output: 2
        //
        //Explanation:
        //We can perform the following changes:
        //Replace nums[1] by 2. The resulting array is nums = [1,2,1,2,4,3].
        //Replace nums[3] by 3. The resulting array is nums = [1,2,1,3,4,3].
        //The integer X will be 2.
        //
        //- Cùng lắm là replace hết về 1 thằng có count lớn nhất
        //  ==> X= số thằng khác count đó
        //Ex:
        //nums = [1,0,1,2,4,3], k = 4
        //3-1=2
        //4-0=4
        //2-1=1
        //==> Mỗi đôi chỉ replace 1 lần thôi
        //
        //Ex:
        //nums = [0,1,2,3,3,6,5,4], k = 6
        //4-0=4
        //5-1=4
        //6-2=4
        //3-3=0
        //- Khi replace ==> 3 không thể replace cho 7 được
        //  + 7>6
        //- Bài toán con là replace 1 cặp x,y sao cho:
        //  x-y=z
        //  x<=6, y<=6
        //
        //3-1=2
        //4-0=4
        //2-1=1
        //k=4
        //+ x=1
        //
//        int[] nums = {1,0,1,2,4,3};
//        int k = 4;
//        int[] nums = {0,1,2,3,3,6,5,4};
//        int k = 6;
        //
//        int[] nums = {5000,1,2,5005};
//        int k = 100;
//        int[] nums = {1,2};
//        int k = 100;
//        int[] nums = {1,2,1,10};
//        int k = 2;
//        int[] nums = {1,10};
//        int k = 2;
        int[] nums = {1,1,1,1,
                0,0,0,5,4,3,19,17,16,15,15,15,
                19,19,19,19};
        int k = 20;
        //Output: 8
        //Ex: 7
        //[1,1,1,1,0,0,0,5,4,3,19,17,16,15,15,15,19,19,19,19]
        //20
        System.out.println(minChanges(nums, k));
//        System.out.println(minChangesWrong(nums, k));
//        System.out.println(replaceCount(3,3,4,6));
//        System.out.println(replaceCount(3,1,4,1));
//        System.out.println(replaceCount(4,0,4,1));
    }
}
