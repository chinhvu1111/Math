package E1_PrefixSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E3_LongestSubsequenceWithLimitedSum {
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

     public static int findMaxLengthMisLeading(int value, List<Node> sortArr, int n){
         int rs=0;

         if(n==0){
             return 0;
         }
         if(sortArr.get(0).value<=value){
             rs=1;
         }else{
             return 0;
         }
         // int initValIncre=sortArr.get(0).value;
         // int initValDecre=sortArr.get(0).value;
         // int maxLengthIncre=1;
         // int maxLengthDecre=1;

         for(int i=0;i<n;i++){
             int increLength=1;
             int decreLength=1;
             int initIncreSum=sortArr.get(i).value;
             int initDecreSum=sortArr.get(i).value;

             for(int j=i+1;j<n;j++){
                 if(sortArr.get(j).index<sortArr.get(i).index&&sortArr.get(j).value+ initIncreSum<=value){
                     increLength++;
                     initIncreSum+=sortArr.get(j).value;
                 }
                 if(sortArr.get(j).index>sortArr.get(i).index&&sortArr.get(j).value+ initDecreSum<=value){
                     decreLength++;
                     initDecreSum+=sortArr.get(j).value;
                 }
                 System.out.printf("Incre, j= %s, Sum=%s\n", j, initIncreSum);
                 System.out.printf("Decre, j= %s, Sum=%s\n", j, initDecreSum);
             }
             System.out.printf("Value %s, Incre: index: %s, length %s, sum: %s\n", value, i, increLength, initIncreSum);
             System.out.printf("Value %s, Decre: index: %s, length %s, sum: %s\n", value, i, decreLength, initDecreSum);
             rs=Math.max(rs, Math.max(increLength, decreLength));
         }
         return rs;
     }

    public static int findMaxLength(int value, int[] sortArr, int n){
        if(n==0){
            return 0;
        }
        if(sortArr[0]>value){
            return 0;
        }
        int result=1;
        int initSum=sortArr[0];

        for(int i=1;i<n;i++){
            if(sortArr[i]+initSum<=value){
                result++;
                initSum+=sortArr[i];
            }else{
                break;
            }
        }
        return result;
    }

    public int[] answerQueries(int[] nums, int[] queries) {
        int n=nums.length;
        int m=queries.length;
        Arrays.sort(nums);
        // System.out.println(sortedList);
        for(int i=0;i<m;i++){
            queries[i]=findMaxLength(queries[i], nums, n);
        }
        return queries;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given (nums) with length=n and (queries) with length=m
        //- Subsequence : delete several characters but  the order was still kept
        //* Return answer having length=m such that answer[i] is (the maximum size of a subsequence) that you can take from nums
        //+ Sum of its elements is less than or equal to queris[i]
        //Ex:
        //nums = [4,5,2,1]
        //queries = [3,10,21]
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
        //n == nums.length
        //m == queries.length
        //1 <= n, m <= 1000
        //1 <= nums[i], queries[i] <= 106
        //
        //- Brainstorm
        //- Cho x tìm maximum length của subsequence có sum <=x.
        //                  4
        //               /  \     \
        //            5     2(6)    1(5)
        //          /   \
        //       2       1
        //     /
        //   1
        //- Tree --> Traverse max depth
        //+ Cách này độ complexity is quite big
        //
        //Ex:
        //nums = [4,5,2,1]
        //queries = [3,10,21]
        //+ Tìm subsequence có maximum size
        //+ Size : n --> 1
        //- Brute force:
        //- Liệt kê all các subsequence có thể + sum của nó
        //Ex:
        //nums = [4,5,2,1]
        //queries = [3,10,21]
        //
        //nums = [4,5,2,1]
        //+ Length=2 : {4,5}, {4,2},{4,1}, {5,2}, {5,1}
        //==> Làm như thế này thì độ phức tạp khá lớn.
        //
        //Ex:
        //nums = [4,5,2,1]
        //queries = [3,10,21]
        //---> Bài này có thể dùng greedy được
        //+ nums = [4,5,2,1] --> [1,2,4,5]
        //+ value =3 --> Ưu tiên chọn (1,2) trước
        //==> Chọn index mà liên tiếp nhau lớn nhất + sum <3
        //+ nums = [4,5,2,1]
        // --> [1,2,4,5] <-> [3,2,0,1] : rs=3
        //+ nums = [4,5,1,2]
        // --> [1,2,4,5] <-> [2,3,0,1] : rs=3
        //==> Longest of increase or decrease.
        //
        //- Special cases:
        //nums = [4,5,2,1]
        //queries = [3,10,21]
        //
        //+ queries[2]=21
        //==> sorted : [1,2,4,5] <=> [i=3, i=2, i=0, i=1]
        //- Trở thành bài toán:
        //+ i --> Sum element(index< i) --> Đến khi > 3 ==> break
        //--> Ta có 1 case
        //- Với cách trên
        //+ queries[1]=10
        //+ sorted : [1,2,4,5] <=> [i=3, i=2, i=0, i=1]
        //
        //nums=     [736411,184882,914641,37925,214915]
        //queries=  [331244,273144,118983,118252,305688,718089,665450]
        //Sort:
        //[3 : 37925, 1 : 184882, 4 : 214915, 0 : 736411, 2 : 914641]
        //+ Result: [2,2,1,1,2,2,2]
        //+ Expect: [2,2,1,1,2,3,3]
        //
        //* Hiểu lầm:
        //- Không cần increase hay decrease index vì:
        //+ Nếu chọn ra các số nhỏ nhất --> Greedy nếu lấy ra được 1 collection ==> Mình sắp xếp tập đó thành (index increase hay decrease cũng được)
        //--> Chỉ cần for 1 vòng là xong
        //
        //1.1, Optimization
        //- Space: O(n) --> O(1) extra space ==> bằng cách dùng lại queries[] array
        //
        //1.2, Complexity:
        //- Space : O(1) - extra space
        //- Time : O(n*log(n))
        //
        //* Method 2:
        //- Approach 2: Prefix Sum + Binary Search
        //2.0,
        //Ex:
        //nums = [4,5,2,1]
        //queries = [3,10,21]
        //+ sorted arr =[1,2,4,5]
        //==> Tính prefix sum = [1,3,7,12]
        //--> Tương tự phương pháp bên trên greedy ==> Ta sẽ tìm sum phù hợp < value
        //1.1, Optimization
        //1.2, Complexity:
        //- Space : O(log(n)) - stack
        //- Time : O(n*log(n))
        //
        //#Reference:
        //1365. How Many Numbers Are Smaller Than the Current Number
        //2300. Successful Pairs of Spells and Potions
    }
}
