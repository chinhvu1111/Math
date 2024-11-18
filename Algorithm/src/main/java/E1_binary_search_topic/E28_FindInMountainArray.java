package E1_binary_search_topic;

public class E28_FindInMountainArray {

    static class MountainArray {
        int [] nums = {0,1,2,4,2,1};
        public int get(int index) {
            return nums[index];
        }

        public int length() {
            return nums.length;
        }
    }

    public static int getPeakIndex(int low, int high, MountainArray mountainArr){
        int rs=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(mountainArr.get(mid)>mountainArr.get(mid+1)){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int searchIncrease(int low, int high, int val, MountainArray mountainArr){
        //0,2
        //
//        while(low<=high){
//            int mid=low+(high-low)/2;
//            int curVal = mountainArr.get(mid);
//            if(curVal>val){
//                low=mid-1;
//            }else if(curVal<val){
//                high=mid;
//            }else{
//                return mid;
//            }
//        }
        while(low<=high){
            int mid=low+(high-low)/2;
            int curVal = mountainArr.get(mid);
            if(curVal>val){
                high=mid-1;
            }else if(curVal<val){
                low=mid+1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static int searchDecrease(int low, int high, int val, MountainArray mountainArr){
        while(low<=high){
            int mid=low+(high-low)/2;
            int curVal = mountainArr.get(mid);
            if(curVal>val){
                low=mid+1;
            }else if(curVal<val){
                high=mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }

    public static int findInMountainArray(int target, MountainArray mountainArr) {
        int len = mountainArr.length();
        int peakIndex=getPeakIndex(0, len-1, mountainArr);
        int peakVal=mountainArr.get(peakIndex);
        if(peakVal==target){
            return peakIndex;
        }
        int index=-1;
        index=searchIncrease(0, peakIndex-1, target, mountainArr);
        if(index!=-1){
            return index;
        }
        index=searchDecrease(peakIndex+1, len-1, target, mountainArr);
        return index;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You may recall that an array arr is a mountain array if and only if:
        //  + arr.length >= 3
        //  There exists some i with 0 < i < arr.length - 1 such that:
        //  + arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
        //  + arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
        //- Given (a mountain array mountainArr),
        //* return the minimum index such that mountainArr.get(index) == target.
        //  If such an index does not exist, return -1.
        //- You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
        //  + MountainArray.get(k) returns the element of the array at index k (0-indexed).
        //  + MountainArray.length() returns the length of the array.
        //- Submissions making more than 100 calls to MountainArray.get will be judged (Wrong Answer).
        // Also, any solutions that attempt to circumvent the judge will result in disqualification.
        //
        //Example 1:
        //
        //Input: mountainArr = [1,2,[3],4,5,3,1], target = 3
        //Output: 2
        //Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
        //
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //3 <= mountainArr.length() <= 10^4
        //0 <= target <= 10^9
        //0 <= mountainArr.get(index) <= 10^9
        //
        //- Brainstorm
        //
        //Example 1:
        //Input: mountainArr = [1,2,[3],4,5,3,1], target = 3
        //Output: 2
        //           5
        //         /   \
        //        4     \
        //       /       \
        //   (3-mid)     (3-mid)
        //     /           \
        //    2             \
        //  /                \
        // 1                  1
        //
        //- We need to the peek first
        //- a,b,c
        //  + a>b>c
        //  Ex:
        //       5
        //         \
        //           2
        //             \
        //              1
        //  + a<b<c
        //  Ex:
        //        5
        //      /
        //     3
        //   /
        //  2
        //  + a>b<c
        //  Ex:
        //          5
        //        /  \
        //       /    4
        //      /
        //     2
        //    /
        //   1
        //  + a<b>c
        //
        //- low=0, high=n-1
        //  + value>target
        //- Nếu suy luận như trên:
        //  + Không ra được
        //- Apply the idea of the previous solution:
        //  + We find the index of the peak value
        //- After that we find the index of the target in left or right that depends on the value of the peak
        //Ex:
        //low = 0, high=2
        //mid=1:
        //  + nums[mid]>val: high=mid-1 = 0
        //  + nums[mid]<val: low=mid+1 = 1
        //  ==> Good
        //- If we stuck at the infinitive loop, it is related to the (high, low) was calculated in (the incorrect way)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(log(n))
        //
        //#Reference:
        //843. Guess the Word
        //2383. Minimum Hours of Training to Win a Competition
        //3273. Minimum Amount of Damage Dealt to Bob
        //
        MountainArray mountainArray=new MountainArray();
        int target=3;
        System.out.println(findInMountainArray(target, mountainArray));
    }
}
