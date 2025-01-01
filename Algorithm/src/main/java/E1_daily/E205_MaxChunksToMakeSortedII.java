package E1_daily;

import java.util.*;

public class E205_MaxChunksToMakeSortedII {

    public static int maxChunksToSorted(int[] nums) {
        int n=nums.length;
        int[] sortNums= Arrays.copyOfRange(nums, 0, n);
        HashMap<Integer, TreeSet<Integer>> valToIndex=new HashMap<>();
        //Time: O(n*log(n))
        Arrays.sort(sortNums);

        //Time: O(n)
        for(int i=0;i<n;i++){
            TreeSet<Integer> curSetIndices=valToIndex.getOrDefault(sortNums[i], new TreeSet<>());
            //Time: O(log(n))
            curSetIndices.add(i);
            valToIndex.put(sortNums[i], curSetIndices);
        }
        int sumIndices=0;
        int rs=0;

        //Time: O(n)
        for(int i=0;i<n;i++){
            //Time: O(log(n))
            int curIndex=valToIndex.get(nums[i]).pollFirst();
            sumIndices+=curIndex;
            if(sumIndices==i*(i+1)/2){
                rs++;
            }
        }
        return rs;
    }

    public static int maxChunksToSortedSlideWindow(int[] nums) {
        int n=nums.length;
        Map<Integer, Integer> count=new HashMap<>();
        int rs=0, nonZero=0;
        int[] expect=nums.clone();
        Arrays.sort(expect);

        for(int i=0;i<n;i++){
            int x=nums[i], y=expect[i];
            count.put(x, count.getOrDefault(x,0)+1);
            if(count.get(x)==0){
                nonZero--;
            }
            if(count.get(x)==1){
                nonZero++;
            }
            count.put(y, count.getOrDefault(y,0)-1);
            if(count.get(y)==0){
                nonZero--;
            }
            if(count.get(y)==-1){
                nonZero++;
            }
            if(nonZero==0){
                rs++;
            }
        }
        return rs;
    }

    public static int maxChunksToSortedRefer(int[] arr) {
        int[] minSuffix = new int[arr.length];
        int[] maxPrefix = new int[arr.length];

        minSuffix[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            minSuffix[i] = Math.min(arr[i], minSuffix[i + 1]);
        }
        maxPrefix[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxPrefix[i] = Math.max(arr[i], maxPrefix[i-1]);
        }

        int cnt = 1;
        //Ex:
        //[2,1,3,4,4]
        for (int i = 1; i < arr.length; i++) {
            if (minSuffix[i] >= maxPrefix[i - 1]) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array arr).
        //- We split arr into some number of chunks (i.e., partitions), and individually (sort each chunk).
        //- After concatenating them, the result should equal (the sorted array).
        //* Return (the largest number of chunks) we can make to (sort the array).
        //
        //Example 2:
        //
        //Input: arr = [2,1,3,4,4]
        //Output: 4
        //Explanation:
        //We can split into two chunks, such as [2, 1], [3, 4, 4].
        //However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= arr.length <= 2000
        //0 <= arr[i] <= 10^8
        //  + Length<=2000 ==> O(n^2)
        //
        //- Brainstorm
        //Ex:
        //arr =         [2,1,3,4,4]
        //sort(arr) =   [1,2,3,4,4]
        //
        //
        int[] arr = {2,1,3,4,4};
//        int[] arr = {1,1,0,0,1};
        //Ex:
        //arr       = 2,1,3,4,4
        //sort(arr) = 1,2,3,4,4
        //indices   = 0,1,2,3,4
        //  + [1]=0
        //  + [2]=1
        //  + [3]=2
        //  + [4]=[3,4]
        //arr       = 2,1,3,4,4
        //indices   = 1,0,2,[3,4],[3,4]
        //  + For 4,4 ==> We get the index incrementally
        //  ==> It will be better
        //
        //- sort array:
        //  + Get map from val to index
        //- Index=i:
        //  + Sum of all of the previous index == i*(i+1):
        //      + rs++
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
        //2. Slide window
        //2.1,
        //- Lưu ý rằng nếu a1 ,a2,…,am là một đoạn và a1,a2,…,an là một đoạn (m<n),
        // thì a(m+1),a(m+2),…,an cũng là một đoạn.
        //- Điều này cho thấy cách tiếp cận tham lam tạo ra số lượng khối cao nhất.
        //- Chúng ta biết mảng arr sẽ có kết thúc như mong đợi = được sắp xếp (arr).
        //- Nếu số lượng phần tử k đầu tiên trừ đi số phần tử đó bằng 0 ở mọi nơi thì k phần tử đầu tiên tạo thành một đoạn hợp lệ.
        //- Chúng tôi liên tục thực hiện quá trình này.
        //- Chúng ta có thể sử dụng một (biến khác 0) để (đếm số chữ cái) trong đó (số đếm hiện tại khác 0).
        //
        //2.2, Optimization
        //2.3, Complexity
        //- Space: O(1)
        //- Time: O(n*log(n))
        //
        //3, Special rule:
        //- minSuffix[i] >= maxPrefix[i - 1]
        //(chunk1)(chunk2)
        //  + minSuffix[i]=maxPrefix[i-1]
        //Ex:
        //arr = [(1),2,|(3),4,5]
        //  ==> Sort not change
        //3.1, Optimization
        //3.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        System.out.println(maxChunksToSorted(arr));
        System.out.println(maxChunksToSortedSlideWindow(arr));
        System.out.println(maxChunksToSortedRefer(arr));
        //#Reference:
        //2056. Number of Valid Move Combinations On Chessboard
        //1776. Car Fleet II
        //1773. Count Items Matching a Rule
    }
}
