package E1_daily;

import java.util.Arrays;

public class E31_RelativeSortArray {

    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int n=arr1.length;
        int m=arr2.length;
        int maxSum = Arrays.stream(arr1).max().getAsInt();
        //Time: O(maxSum)
        //Space: O(maxSum)
        int[] count=new int[maxSum+1];
        int j=0;

        //Time: O(n)
        for(int i=0;i<n;i++){
            count[arr1[i]]++;
        }
        //Time: O(m)
        for(int i=0;i<m;i++){
            //Time: total = O(maxSum)
            while(count[arr2[i]]>0){
                arr1[j]=arr2[i];
                count[arr2[i]]--;
                j++;
            }
        }
        //Time: O(maxSum)
        for(int i=0;i<=maxSum;i++){
            //Time: O(n)
            while(count[i]>0){
                arr1[j]=i;
                count[i]--;
                j++;
            }
        }
        return arr1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given two arrays arr1 and arr2, the elements of arr2 (are distinct), and (all elements) in (arr2) are also in (arr1).
        //- Sort the elements of arr1 such that (the relative ordering) of items in (arr1) are the (same) as in (arr2).
        //  + Elements that do (not appear) in arr2 should be placed at (the end of arr1) in (ascending order).
        //- Sort arr1 sao cho:
        //+ Thứ tự các elements trong arr1 giống trong arr2
        //  ==> Nếu element nào trong arr1 không trong arr2 ==> append vào last của arr1
        //
        //Example 1:
        //Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
        //Output: [2,2,2,1,4,3,3,9,6,7,19]
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= arr1.length, arr2.length <= 1000
        //0 <= arr1[i], arr2[i] <= 1000
        //All the elements of arr2 are distinct.
        //Each arr2[i] is in arr1.
        //
        //- Brainstorm
        //Example 1:
        //Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
        //Output: [2,2,2,1,4,3,3,9,6,7,19]
        //- count số lượng của mỗi phần tử trong arr1
        //- Sau đó xét all elements trong arr2 + cùng lúc fill vào arr1
        //- Tìm phần tử xuất hiện trong arr1 nhưng không trong arr2
        //  + Xét 0 -> maxSum: vì có thể có các phần tử (small --> big) có trong arr1 nhưng không có trong arr2
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(maxSum)
        //- Time : O(n+m+maxSum)
        //
//        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
//        int[] arr2 = {2,1,4,3,9,6};
//        int[] arr1 = {28,6,22,8,44,17};
//        int[] arr2 = {22,28,8,6};
        int[] arr1 = {26,21,11,20,50,34,1,18};
        int[] arr2 = {21,11,26,20};
        int[] rs = relativeSortArray(arr1, arr2);

        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
        //#Reference:
        //2016. Maximum Difference Between Increasing Elements
        //849. Maximize Distance to Closest Person
        //3117. Minimum Sum of Values by Dividing Array
    }
}
