package E1_daily;

import java.util.*;

public class E32_CountPairsInTwoArrays {

    public static int search(List<Long> list, int i, int j, long value){
        if(i==list.size()){
            return -1;
        }
        int low=i, high=Math.max(i, j);
        int rs=-1;

        while (low<=high) {
            int mid=low+(high-low)/2;
            if(list.get(mid)<value){
                low=mid+1;
            }else{
                rs=mid;
                high=mid-1;
            }
        }
        return rs;
    }

    public static long countPairs(int[] nums1, int[] nums2) {
        int n=nums1.length;
        //Space: O(n)
        HashMap<Long, Integer> mapCount=new HashMap<>();
        List<Long> listUniqueSum = new ArrayList<>();

        //Time: O(n)
        for(int i=0;i<n;i++){
            long curSum = nums1[i]-nums2[i];
            if(!mapCount.containsKey(curSum)){
                listUniqueSum.add(curSum);
            }
            mapCount.put(curSum, mapCount.getOrDefault(curSum, 0)+1);
        }
        //Time: O(n*log(n)) ==> O(m*log(m))
        Collections.sort(listUniqueSum);
//        System.out.println(listUniqueSum);
//        System.out.println(mapCount);
        long rs=0L;
        int m=listUniqueSum.size();
        //Time: O(m)
        //Space: O(m)
        int[] prefixCount=new int[m];
        int sumCount=0;

        //Time: O(m)
        for(int i=m-1;i>=0;i--){
            sumCount+=mapCount.get(listUniqueSum.get(i));
            prefixCount[i]=sumCount;
        }
        int prevIndexRight=m-1;

        //Time: O(n) => O(k)
        for(int i=0;i<listUniqueSum.size();i++){
            long curVal = listUniqueSum.get(i);
            long minRight = -1*curVal+1;
            //Right>=min right
            //Time: O(log(k))
            int indexRight = search(listUniqueSum, i+1, prevIndexRight, minRight);
            int curCount = mapCount.get(curVal);
            //Nếu số hiện tại > 0
            //- Số case thêm vào là C2(count)=count*(count-1) = (số 1 có count cách chọn) * (số 2 có count-1 cách chọn)
            // = 3*2/2 = 3 <> (3+2+1=3*(3+1)/2)
            if(listUniqueSum.get(i)>0){
                rs+= (long) curCount *(curCount-1)/2;
            }
            if(indexRight==-1){
                continue;
            }else {
                //Update right
                prevIndexRight=indexRight;
            }
//            System.out.printf("Index: %s\n", indexRight);
            //- Tính cho các cases kết hợp:
            //  + Nếu tính theo right ==> sum all count của right * current count
            rs+= (long) prefixCount[indexRight] * curCount;
        }

        return rs;
    }

    public static long countPairsOptimization(int[] nums1, int[] nums2) {
        int N = nums1.length;  // nums2 is the same length

        // Difference[i] stores nums1[i] - nums2[i]
        long[] difference = new long[N];
        for (int i = 0; i < N; i++) {
            difference[i] = nums1[i] - nums2[i];
        }
        Arrays.sort(difference);

        // Count the number of valid pairs
        long result = 0;
        for (int i = 0; i < N; i++) {
            // All indices j following i make a valid pair
            if (difference[i] > 0) {
                result += N - i - 1;
            } else {
                // Binary search to find the first index j
                // that makes a valid pair with i
                int left = i + 1;
                int right = N - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    // If difference[mid] is a valid pair, search in left half
                    if (difference[i] + difference[mid] > 0) {
                        right = mid - 1;
                        // If difference[mid] does not make a valid pair, search in right half
                    } else {
                        left = mid + 1;
                    }
                }
                // After the search left points to the first index j that makes
                // a valid pair with i so we count that and all following indices
                result += N - left;
            }
        }
        return result;
    }

    public static long countPairsRefer(int[] nums1, int[] nums2) {
        int n=nums1.length;
        //Space : O(n)
        //Time: O(n)
        int[] diff=new int[n];

        //Time: O(n)
        for(int i=0;i<n;i++){
            diff[i]=nums1[i]-nums2[i];
        }
        //Time: O(n*log(n))
        Arrays.sort(diff);
        long rs=0;

        int left=0;
        int right = n-1;
        //Time: O(n)
        while(left<right){
            if(diff[left]+diff[right]>0){
                rs+=right-left;
                right--;
            }else{
                left++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given two integer arrays (nums1) and (nums2) of length n,
        // count the pairs of indices (i, j) such that (i < j) and
        // nums1[i] + nums1[j] > nums2[i] + nums2[j].
        //* Return the number of pairs satisfying the condition.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //n == nums1.length == nums2.length
        //1 <= n <= 10^5
        //1 <= nums1[i], nums2[i] <= 10^5
        //+ N khá lớn ==> Có thể xử lý O(n*k)
        //+ Length của 2 arrays bằng nhau
        //
        //- Brainstorm
        //Example 1:
        //Input:
        //nums1 = [2,1,2,1],
        //nums2 = [1,2,1,2]
        //Output: 1
        //Explanation: The pairs satisfying the condition are:
        //- (0, 2) where 2 + 2 > 1 + 1.
        //- i<j
        //==> Thứ tự sao cũng được vì (i,j) có thể get pair of (i,j)
        //- Sort theo sum(nums1[i] + nums2[i])
        //  ==> Sau đó ta sẽ tính cặp (i, j) bất kỳ là được vì (i > j/ i < j)
        //Example 2:
        //Input: nums1 = [1,10,6,2], nums2 = [1,4,1,5]
        //Output: 5
        //
        //=> sort
        //nums1 = [1,10,6,2]
        //nums2 = [1,4,1,5]
        //==> sort theo nums1[i] + nums2[i]
        //nums1 = [1,6,2,10]
        //nums2 = [1,1,5,4]
        //==> ta tính sum là được
        // sums =      [2,7,7,14]
        //unique sums =[2,7,14]
        //count[sum]=  [1,2,1]
        //==> Prefix sum, ta có thể sum - từng số là được.
        //** HIỂU SAI ĐỂ
        //nums1[i] + nums1[j] ==> Cùng 1 array không phải là khác array.
        //- ĐK:
        //nums1[i] + nums1[j] > nums2[i] + nums2[j].
        //nums1[i] - nums2[i] + nums1[j] - nums2[j] > 0
        //
        //-> sort (nums1[i] - nums2[j]) ==> Tăng dần
        //- Sau đó sẽ xét từ (min -> max)
        //  + Với mỗi element trong khoảng (min, max): tìm phần tử element đằng sau sao cho (current element + next element > 0)
        //- Vì ta đi từ (min -> max):
        //  + Kết quả index phần tử có thể ghép -> Giảm dần vì (số left tăng dần lên)
        //      + Ta không cần thằng right để bù vào thằng left nữa.
        //
        //Input:
        //nums1 = [2,1,2,1],
        //nums2 = [1,2,1,2]
        //[-1, 1]
        //{-1=2, 1=2}
        //Ex:
        //-6,-5,-2,-1,0,1,5,8,9
        //==> Cố định 1 đầu ==> Tìm min mà (sum[i] + sum[j] >0)
        //  + rs+= n-j
        //  + i++
        //
        //- Lấy prefix sum các unique number từ (right -> left)
        //  + rs+= current count * PrefixSum[right] thoả mãn:
        //      + current num + num[right] > 0
        //
        //1.1, Optimization
        //- Right có thể giảm dần ==> Ta sẽ có thể chặn cận trên (right)
        //  + Sẽ có case element[i] ==> Tìm (i+1, prevIndex) mà thậm chí prevIndex<i+1
        //  ==> Thành ra không đủ case
        //  Ex:
        //  nums1 = {1,10,6,2}
        //  nums2 = {1,4,1,5};
        //  subtract=[-3,0,5,6]
        //  + Đứng ở (index=0) --> tìm (index=2)
        //  + Đứng ở (index=2) --> tìm (i=1+1, 2) ==> 2==2 ==> sẽ return -1
        //      + Sửa lại high=max(low,j)
        //
        //- Làm cách khác dùng binary search:
        //  + left --> tìm right
        //      + rs+=n-rightIndex
        //      + Không cần count vì ta có thể search cùng 1 value nhiều lần + sum dần sẽ cho kết quả giống nhau:
        //      --> Giống công thức:
        //          + prefixCount[indexRight] * curCount
        //
        //1.2, Complexity
        //- Space: O(n+m)
        //- Time: O(n+m*log(m))
        //
        //2. Two pointers
        //- Brainstorm
        //Ex:
        //nums1 = {1,10,6,2}
        //nums2 = {1,4,1,5};
        //subtract=[-3,0,5,6]
        //- Nếu không dùng prefixCount ta có thể thay bằng cách khác được không:
        //+ unique nums =[-3,-3,0,5,6]
        //+ subtract    =[-3,0,5,6]
        //+ count       =[2,1,1,1]
        //+ prefixCount =[5,3,2,1]
        //- Ta thấy:
        //  + Nếu subtract[left] + subtract[right] > 0:
        //  ==> subtract[left --> right] + subtract[right] >0
        //      + Vì subtract[left+k] > subtract[left]
        //      + rs+=right-left
        //      + right--
        //  + Nếu subtract[left] + subtract[right] <= 0:
        //      + right không tăng được --> left++
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n+log(n))
        //- Time: O(n*log(n))
        //
        int[] nums1 = {1,10,6,2}, nums2 = {1,4,1,5};
//        int[] nums1 = {2,1,2,1}, nums2 = {1,2,1,2};
//        int[] nums1 = {5,1,1,15,3,14,19,1,9,12,6,8,2,4,19,17,19,5}, nums2 = {1,16,5,3,7,9,19,3,7,2,13,4,4,17,13,12,19,16};
        System.out.println(countPairs(nums1, nums2));
        System.out.println(countPairsOptimization(nums1, nums2));
        System.out.println(countPairsRefer(nums1, nums2));
        //#Reference:
        //1499. Max Value of Equation
    }
}
