package E1_daily;

import java.util.*;

public class E71_FindKthSmallestPairDistance {

    public static int smallestDistancePairWrong(int[] nums, int k) {
        Arrays.sort(nums);
        int n=nums.length;
        HashMap<Integer, Integer> countLessThanVal=new HashMap<>();
        HashMap<Integer, Integer> countGreaterThanVal=new HashMap<>();
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        TreeSet<Integer> set=new TreeSet<>();
        //Ex:
        //1,1,1,2,2,3,4
        //0,0,0,3,3,5,6
        int count=0;
        for(int i=0;i<n;i++){
            //i=0
            //nextCount=1
            int nextCount=count+1;
            countLessThanVal.put(nums[i], count);
            set.add(nums[i]);
            //Count
            mapCount.put(nums[i], mapCount.getOrDefault(nums[i], 0)+1);
            while(i+1<n&&nums[i]==nums[i+1]){
                nextCount++;
                i++;
                countLessThanVal.put(nums[i], count);
                //Count
                mapCount.put(nums[i], mapCount.getOrDefault(nums[i], 0)+1);
                set.add(nums[i]);
            }
            count=nextCount;
        }
        count=0;
        for(int i=n-1;i>=0;i--){
            int nextCount=count+1;
            countGreaterThanVal.put(nums[i], count);
            while(i-1>=0&&nums[i]==nums[i-1]){
                nextCount++;
                i--;
                countGreaterThanVal.put(nums[i], count);
            }
            count=nextCount;
        }
        System.out.println(countLessThanVal);
        System.out.println(countGreaterThanVal);
        System.out.println(mapCount);
        int low=0, high=20_000_001;

        while(low<=high){
            // Số absolute càng lớn:
            //  +
            int mid = low+(high-low)/2;
            int countCase=0;
            int countEqual=0;
            boolean isValid=true;
            boolean isExists=false;

            for(int i=0;i<n;i++){
                if(countCase>k){
                    isValid=false;
                    break;
                }
                if(nums[i]-mid>=0){
                    if(!isExists){
                        isExists=mapCount.containsKey((int)(nums[i]-mid));
                    }
                    //b1>=b
                    //
                    Integer greaterVal=set.ceiling(nums[i]-mid-1);
                    if(greaterVal!=null){
                        if(greaterVal!=nums[i]-mid){
                            countCase+=mapCount.get(greaterVal);
                        }else{
                            countEqual+=mapCount.get(greaterVal);
                        }
                        //count element > greaterVal
                        //  + But these elements neet to be less than nums[i]
                        countCase+=countGreaterThanVal.get(greaterVal)-countGreaterThanVal.get(nums[i]);
                    }
                }

                Integer lessVal = set.floor(nums[i]+mid-1);
                if(!isExists){
                    isExists=mapCount.containsKey((int) (nums[i]+mid));
                }
                if(lessVal!=null){
                    if(lessVal!=nums[i]+mid){
                        countCase+=mapCount.get(lessVal);
                    }else{
                        countEqual+=mapCount.get(lessVal);
                    }
                    //count element < lessVal
                    //  + But these elements neet to be greater than nums[i]
                    countCase+=countLessThanVal.get(lessVal)-countLessThanVal.get(nums[i]);
                }
            }
            System.out.printf("val: %s, countCase: %s, count equal: %s\n", mid, countCase, countEqual);
            if(isValid&&(countCase==k-1||countCase+countEqual>=k)){
                if(isExists){
                    return mid;
                }
                low=mid+1;
//                high=mid-1;
            }else{
//                low=mid+1;
                high=mid;
            }
        }

        return -1;
    }

    public static int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n=nums.length;
//        HashMap<Integer, Integer> countLessThanVal=new HashMap<>();
//        HashMap<Integer, Integer> countGreaterThanVal=new HashMap<>();
//        HashMap<Integer, Integer> mapCount=new HashMap<>();
//        TreeSet<Integer> set=new TreeSet<>();
//        //Ex:
//        //1,1,1,2,2,3,4
//        //0,0,0,3,3,5,6
//        int count=0;
//        for(int i=0;i<n;i++){
//            //i=0
//            //nextCount=1
//            int nextCount=count+1;
//            countLessThanVal.put(nums[i], count);
//            set.add(nums[i]);
//            //Count
//            mapCount.put(nums[i], mapCount.getOrDefault(nums[i], 0)+1);
//            while(i+1<n&&nums[i]==nums[i+1]){
//                nextCount++;
//                i++;
//                countLessThanVal.put(nums[i], count);
//                //Count
//                mapCount.put(nums[i], mapCount.getOrDefault(nums[i], 0)+1);
//                set.add(nums[i]);
//            }
//            count=nextCount;
//        }
//        count=0;
//        for(int i=n-1;i>=0;i--){
//            int nextCount=count+1;
//            countGreaterThanVal.put(nums[i], count);
//            while(i-1>=0&&nums[i]==nums[i-1]){
//                nextCount++;
//                i--;
//                countGreaterThanVal.put(nums[i], count);
//            }
//            count=nextCount;
//        }
//        System.out.println(countLessThanVal);
//        System.out.println(countGreaterThanVal);
//        System.out.println(mapCount);
//        HashMap<Integer, Integer> mapCount=new HashMap<>();
//        for (int i = 0; i < n; i++) {
//            mapCount.put(nums[i], mapCount.getOrDefault(nums[i], 0)+1);
////            System.out.printf("%s, ", nums[i]);
//        }
//        System.out.println();
        int low=0, high=20_000_001;

        while(low<high){
            int mid= low+(high-low)/2;
            int left=0, i=1;
            int curGreaterNum=0;
//            for(;i<n;i++){
//                if(nums[i]-nums[left]>=mid){
//                    curGreaterNum+=(i-left)*(i-left-1)/2;
//                    System.out.printf("(%s %s)\n", left, i-1);
////                    while (left<n&&nums[i]-nums[left]>=mid){
////                        left++;
////                    }
//                    left=i;
//                }
//            }
//            curGreaterNum+=(i-left)*(i-left-1)/2;
            for(;i<n;i++){
                if(nums[i]-nums[left]>mid){
                    while (left<n&&nums[i]-nums[left]>mid){
                        left++;
                    }
//                    System.out.printf("(%s %s)\n", left, i-1);
                    curGreaterNum+=i-left;
                    //* Không cần assign cái này ==> Nếu assign dịch sẽ bị thiếu case ra 54 thay vì 36
//                    left=i;
                }else{
//                    System.out.printf("(%s %s)\n", left, i-1);
                    curGreaterNum+=i-left;
                }
            }
            //* Cái này là thừa case
//            curGreaterNum+=i-left;
            System.out.printf("low: %s, high: %s, mid: %s, count: %s\n", low, high, mid, curGreaterNum);
            if(curGreaterNum<k){
                low=mid+1;
            }else{
                high=mid;
            }
        }
        return low;
    }

    public static void getPair(int[] nums, int k){
        Arrays.sort(nums);
        int n=nums.length;
        List<Integer> pairs=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                pairs.add(nums[j]-nums[i]);
            }
        }
        Collections.sort(pairs);
        System.out.println(pairs.get(k-1));
        System.out.println(pairs);
    }

    public static void main(String[] args) {
        // Requirement
        //- (The distance of a pair of integers a and b) is defined as (the absolute difference) between a and b.
        //- (Given an integer array nums) and an integer k,
        //* return (the kth smallest distance) among (all the pairs)
        // - (nums[i] and nums[j]) where 0 <= i < j < nums.length.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //n == nums.length
        //2 <= n <= 10^4
        //0 <= nums[i] <= 10^6
        //1 <= k <= n * (n - 1) / 2
        //+ N khá lớn ==> Không thể O(n^2)
        //
        //- Brainstorm
        //- Tìm absolute (min -> max)
        //- Ta có thể sort trước:
        //
        //Example 1:
        //Input: nums = [1,3,1], k = 1
        //Output: 0
        //Explanation: Here are all the pairs:
        //(1,3) -> 2
        //(1,1) -> 0
        //(3,1) -> 2
        //Then the 1st smallest distance pair is (1,1), and its distance is 0.
        //- sort by unique values:
        //  + 1,3
        //- count của từng value:
        //  + 1: 2
        //  + 3: 1
        //- a < b < c:
        //  + |b-a|< |c-a|
        //==> Chỉ có thể xét all pairs nếu làm theo cách bình thường
        //- Hoặc là dùng binary search:
        //  + Tìm absolute min thứ k là được.
        //- X là absolute min thứ k khi:
        //  + X >= đúng (k-1) số đằng trước
        //- Mỗi số, lấy a làm mốc:
        //  + Ta có thể coi như 1 cặp:
        //  + |a - b| = x
        //      + b = a - x
        //          + b càng lớn ==> x càng nhỏ
        //          Mà ta xét x>x1
        //          ==> count số (b1 > b) + | count số (b1==b) - 1 |
        //      + b = a + x
        //          ==> count số (b1 <= b) + | count số (b1==b) - 1 |
        //
        //- Bài toán trở thành:
        //+ Count số lượng element < x
        //+ Count số lượng element > x
        //  + ==> sort là được
        //
        //- Xét x:
        //  + 3 số <x + 5 số == x
        //      ==> x có thể là số min nhất thứ (4 -> 8)
        //==> Sum tất cả cases < x:
        //  + ( Sum <k && tổng các cases có giá trị (|a-b|==x) là được ) >= k là được.
        //
        //- Làm cách trên vướng ở chỗ liên quan đến:
        //  | a - b| = x ==> Chọn shift (left or right)
        //
        //- Mấu chốt ở đây là count số pairs có (absolute <=k)
        //  + Nếu sort array thì phần count này sẽ tính ntn
        //Ex:
        //x
        //num = [1,1,1,2,2,3,4]
        //- Đoạn này có thể dùng two pointers để count:
        //  + Nhận (start, end), số cặp:
        //      + ct = n*(n-1)
        //- count số pair >=k:
        //  + Giảm x đi để số pair giảm xuống là được.
        //==> WRONG
        //- Vì số cặp có thể trùng nhau nếu (start, end) overlap:
        //Ex:
        //num = [1,1,1,2,2,3,4]
        //+ mid = 3
        //(1,3) và (2,4) sẽ overlap nhau
        // + Case: 2, 3 sẽ bị duplicated
        //
//        int[] nums=new int[]{1,1,1,2,2,3,4};
        //1-1
        //1-1
        //1-1
        //2-1: 3
//        int k=4;
//        int[] nums=new int[]{1,1,1,2,3,4};
        //1-1
        //1-1
        //1-1
        //2-1: 3
//        int k=4;
        int[] nums=new int[]{38,33,57,65,13,2,86,75,4,56};
        int k=26;
        //2, 4, 13, 33, 38, 56, 57, 65, 75, 86
        //- mid=29
        //- (2, 4, 13)
        //- (4, 13, 33)
        //-
        //2 = 13
        //3 = 33
        //4 = 38
        //-
        //3 = 33
        //4 = 38
        //5 = 56
        //6 = 57
        //-
        //4 = 38
        //5 = 56
        //6 = 57
        //7 = 65
        //-
        //5 = 56
        //6 = 57
        //7 = 65
        //8 = 75
        //-
        //6 = 57
        //7 = 65
        //8 = 75
        //9 = 86
        //
//        int[] nums=new int[]{62,100,4};
//        int k=2;
        getPair(nums, k);
//        int[] nums=new int[]{1,3,1};
//        int k=1;
        //(1,1) -> 0
        //(1,3) -> 2
        //(3,1) -> 2
//        int k=2;
        //1-1=0
        //==> 0
//        System.out.println(smallestDistancePairWrong(nums, k));
        System.out.println(smallestDistancePair(nums, k));
    }
}
