package contest;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class E174_FinalArrayStateAfterKMultiplicationOperationsII_tight_medium {
    static int mod=1_000_000_007;

    public static int[] getFinalStateSub(int[] nums, int k, int multiplier) {
        TreeSet<int[]> set=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]!=o2[1]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });
        int n=nums.length;

        for(int i=0;i<n;i++){
            set.add(new int[]{i, nums[i]});
        }
        for(int i=0;i<k;i++){
            int[] curMinE=set.first();
            set.remove(curMinE);
            long newVal = ((long) curMinE[1] * multiplier)%mod;
            curMinE[1]= (int) newVal;
            set.add(curMinE);
        }
        Iterator<int[]> iter = set.iterator();

        while (iter.hasNext()){
            int[] curE=iter.next();
            nums[curE[0]]=curE[1];
        }
        for (int i = 0; i < n; i++) {
            System.out.printf("%s ", nums[i]);
        }
        System.out.println();
        return nums;
    }

    public static int[] getFinalState(int[] nums, int k, int multiplier) {
        int n= nums.length;
        TreeSet<int[]> set=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]!=o2[1]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });

        for(int i=0;i<n;i++){
            set.add(new int[]{i, nums[i]});
        }
        int[] maxElement = set.last();
        int cycleK=-1;
        boolean isBack=false;
        for(int i=0;i<k;i++){
            int[] curMinE=set.first();
            set.remove(curMinE);
            long newVal = ((long) curMinE[1] * multiplier);
            curMinE[1]= (int) newVal;
            set.add(curMinE);
            //Max của cái array cũ sẽ là min nhất của cái mới
            //  + Cái này chưa chắc đã đúng vì có cases:
            //      + Giá trị max ban đầu nó di chuyển dần về phía left ==> Nếu nhân thêm multi nó sẽ không di chuyển về phía Max nhất (last index)
            //      ==> Nó sẽ không đúng.
            if(curMinE[0]==maxElement[0]&!isBack){
                isBack=true;
            }
            if(isBack&&set.last()[0]==maxElement[0]){
                cycleK=i+1;
            }
//            Iterator<int[]> iter = set.iterator();
//            while (iter.hasNext()){
//                int[] curE=iter.next();
//                System.out.printf("%s, ", curE[1]);
//            }
//            System.out.println();
        }
        int numMulti=0;
        int numK=0;
        if(cycleK!=-1){
            k-=cycleK;
            numK=k%cycleK;
            numMulti = (k-numK)/cycleK;
        }
        for(int i=0;i<n;i++){
            for(int j=1;j<=numMulti;j++){
                long newVal = ((long) nums[i] *multiplier)%mod;
                nums[i]= (int) newVal;
            }
        }
        Iterator<int[]> iter = set.iterator();

        while (iter.hasNext()){
            int[] curE=iter.next();
            nums[curE[0]]=curE[1]%mod;
        }
        //k = x*n + y
        for (int i = 0; i < n; i++) {
            System.out.printf("%s ", nums[i]);
        }
        System.out.println();
        return nums;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums, an integer k, and an integer multiplier.
        //- You need to perform k operations on nums. In each operation:
        //- Find the minimum value x in nums. If there are multiple occurrences of the minimum value, select the one that appears first.
        //- Replace the selected minimum value x with x * multiplier.
        //- After the k operations, apply modulo 109 + 7 to every value in nums.
        //* Return an integer array denoting the final state of nums after performing all k operations and then applying the modulo.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^4
        //1 <= nums[i] <= 10^9
        //1 <= k <= 10^9
        //1 <= multiplier <= 10^6
        //
        //** Brainstorm
        //- Array sẽ quay vòng
        //  + Giảm size của k đi là được.
        //
//        int[] nums = {2,1,3,5,6};
//        int k = 5, multiplier = 2;
//        int[] nums = {100000,2000};
//        int k = 2, multiplier = 1000000;
        //
        //- Special cases:
        //  + Nếu dùng phép tính (%10^9+7) thì:
        //      + Việc sort sẽ không còn đúng nữa ==> Số nào lớn hơn>=10^9+7 khi modulo sẽ đi về phía đầu.
        //
        //
        System.out.println(589039035L*641725L);
        int[] nums = {66307295,441787703,589039035,322281864};
        int k = 10, multiplier = 641725;
        //rs: 664480092,763599523,886046925,47878852
        getFinalState(nums, k, multiplier);
//        getFinalStateSub(nums, k, multiplier);
    }
}
