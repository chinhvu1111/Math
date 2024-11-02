package E1_daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class E119_RankTransformOfAnArray {

    public static int[] arrayRankTransform(int[] arr) {
        if(arr.length==0){
            return new int[]{};
        }
        int rs=1;
        int n=arr.length;
        int[][] arrWithIndex=new int[n][3];
        for (int i = 0; i < n; i++) {
            arrWithIndex[i][0]=arr[i];
            arrWithIndex[i][1]=i;
        }
        Arrays.sort(arrWithIndex, (o1, o2) -> o1[0] - o2[0]);
        arrWithIndex[0][2]=1;
        for(int i=1;i<n;i++){
            if(arrWithIndex[i][0]>arrWithIndex[i-1][0]){
                rs++;
            }
            arrWithIndex[i][2]=rs;
        }
        Arrays.sort(arrWithIndex, (o1, o2) -> o1[1] - o2[1]);
        int[] rsArr=new int[n];

        for(int i=0;i<n;i++){
            rsArr[i]=arrWithIndex[i][2];
        }
        return rsArr;
    }

    public static int[] arrayRankTransformOptimization(int[] arr) {
        if(arr.length==0){
            return new int[]{};
        }
        int rs=1;
        int n=arr.length;
        int[] arrWithIndex=Arrays.copyOf(arr, n);
        HashMap<Integer, Integer> valToRank=new HashMap<>();
        Arrays.sort(arr);
        valToRank.put(arr[0], 1);
        for(int i=1;i<n;i++){
            if(arr[i]>arr[i-1]){
                rs++;
                valToRank.put(arr[i], rs);
            }
        }
        int[] rsArr=new int[n];

        for(int i=0;i<n;i++){
            rsArr[i]=valToRank.get(arrWithIndex[i]);
        }
        return rsArr;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an array of integers arr, replace (each element) with (its rank).
        //- The rank represents (how large) the element is.
        //- The rank has the following rules:
        //  + Rank is an integer (starting from 1).
        //  + The larger the element, the larger the rank.
        //      + If (two elements) are equal, their rank must be (the same).
        //  + Rank should be (as small as) possible.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //0 <= arr.length <= 10^5
        //-10^9 <= arr[i] <= 10^9
        //
        //- Brainstorm
        //Example 1:
        //Input: arr = [40,10,20,30]
        //Output: [4,1,2,3]
        //Explanation: 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
        //- Sort là được
        //+ Tạo array with index (Để sau còn sort lại)
        //+ Sort lần thứ nhất theo value:
        //  + Gán rank
        //+ Sau đó sort lại theo index ==> Assign to the new array
        //  + Return rs
        //
        //1.1, Optimization
        //- Đoạn này có thể dùng hash map để lưu rank của value
        //  + Khỏi phải sort 2 lần.
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
        int[] arr = {40,10,20,30};
//        int[] rs= arrayRankTransform(arr);
        int[] rs= arrayRankTransformOptimization(arr);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
        System.out.println();
        //#Reference:
        //1632. Rank Transform of a Matrix
        //2089. Find Target Indices After Sorting Array
    }
}
