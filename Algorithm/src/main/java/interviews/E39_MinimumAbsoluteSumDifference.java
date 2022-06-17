package interviews;

import java.util.Arrays;
import java.util.TreeSet;

public class E39_MinimumAbsoluteSumDifference {

    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int arr[]=new int[n];

        for(int i=0;i<n;i++){
            arr[i]=nums1[i];
        }
        Arrays.sort(arr);
        int sumSubtraction=0;
        int min=Integer.MAX_VALUE;
        int indexRs=-1;
//
        for(int i=0;i<n;i++){
            sumSubtraction+=Math.abs(nums2[i]-nums1[i]);
        }
        for(int i=0;i<n;i++){
            int indexReplacementGreater=-1;
            int indexReplacementLess=-1;

            indexReplacementGreater=findElementGreaterThan(arr, nums2[i], 0, nums1.length-1);
            indexReplacementLess=findElementLessThan(arr, nums2[i], 0, nums1.length-1);

            if(indexReplacementLess!=-1
                    &&min>Math.abs(arr[indexReplacementLess]-nums2[i])-Math.abs(Math.abs(nums1[i]-nums2[i]))){
                indexRs=indexReplacementLess;
                min=Math.abs(arr[indexReplacementLess]-nums2[i])-Math.abs(Math.abs(nums1[i]-nums2[i]));
            }
            if(indexReplacementGreater!=-1&&
                    min>Math.abs(arr[indexReplacementGreater]-nums2[i])-Math.abs(Math.abs(nums1[i]-nums2[i]))){
                indexRs=indexReplacementGreater;
                min=Math.abs(arr[indexReplacementGreater]-nums2[i])-Math.abs(Math.abs(nums1[i]-nums2[i]));
            }
        }
        if(indexRs!=-1){
            return sumSubtraction+min;
        }
        return 0;
    }

    public static int minAbsoluteSumDiffOptimized(int[] nums1, int[] nums2) {
        int n=nums1.length;

        long sumSubtraction=0;
        long min=Integer.MAX_VALUE;
        long indexRs=-1;
        TreeSet<Integer> treeSet=new TreeSet<>();
//
        for(int i=0;i<n;i++){
            sumSubtraction=(sumSubtraction+Math.abs(nums2[i]-nums1[i]))%1_000_000_007;
            treeSet.add(nums1[i]);
        }
        for(int i=0;i<n;i++){
            Integer valReplacementGreater=-1;
            Integer valReplacementLess=-1;

            valReplacementGreater= treeSet.ceiling(nums2[i]);
            valReplacementLess=treeSet.floor(nums2[i]);

            if(valReplacementLess!=null
                    &&min>Math.abs(valReplacementLess-nums2[i])-Math.abs(Math.abs(nums1[i]-nums2[i]))){
                indexRs=valReplacementLess;
                min=Math.abs(valReplacementLess-nums2[i])-Math.abs(Math.abs(nums1[i]-nums2[i]));
            }
            if(valReplacementGreater!=null&&
                    min>Math.abs(valReplacementGreater-nums2[i])-Math.abs(Math.abs(nums1[i]-nums2[i]))){
                indexRs=valReplacementGreater;
                min=Math.abs(valReplacementGreater-nums2[i])-Math.abs(Math.abs(nums1[i]-nums2[i]));
            }
        }
        if(indexRs!=-1){
            return (int) ((sumSubtraction+min)%1_000_000_007);
        }
        return 0;
    }

    public static int findElementGreaterThan(int nums1[], int value, int low, int high){
        int mid=(low+high)/2;

        if(low>=high){
            return low;
        }
        if(value>nums1[mid]){
            low=mid+1;
        }else{
            low=0;
            high=mid;
        }
        return findElementGreaterThan(nums1, value, low, high);
    }

    public static int findElementLessThan(int nums1[], int value, int low, int high){
        int mid=(low+high)/2;

        if(low>=high){
            return low;
        }
        if(value>=nums1[mid]){
            low=mid;
        }else{
            low=0;
            high=mid;
        }
        return findElementGreaterThan(nums1, value, low, high);
    }

    public static void main(String[] args) {
//        int nums1[]=new int[]{1,7,5};
//        int nums2[]=new int[]{2,3,5};
//        int nums1[]=new int[]{10,4,1,4,2,7};
//        int nums2[]=new int[]{3,5,9,1,7,4};
//        int nums1[]=new int[]{2,4,6,8,10};
//        int nums2[]=new int[]{2,4,6,8,10};
//        int nums1[]=new int[]{2};
//        int nums2[]=new int[]{3};
        int nums1[]=new int[]{53,48,14,71,31,55,6,80,28,19,15,40,7,21,69,15,5,42,86,15,11,54,44,62,9,100,2,26,81,87,87,18,45,29,46,100,20,87,49,86,14,74,74,52,52,60,8,25,21,96,7,90,91,42,32,34,55,20,66,36,64,67,44,51,4,46,25,57,84,23,10,84,99,33,51,28,59,88,50,41,59,69,59,65,78,50,78,50,39,91,44,78,90,83,55,5,74,96,77,46};
        int nums2[]=new int[]{39,49,64,34,80,26,44,3,92,46,27,88,73,55,66,10,4,72,19,37,40,49,40,58,82,32,36,91,62,21,68,65,66,55,44,24,78,56,12,79,38,53,36,90,40,73,92,14,73,89,28,53,52,46,84,47,51,31,53,22,24,14,83,75,97,87,66,42,45,98,29,82,41,36,57,95,100,2,71,34,43,50,66,52,6,43,94,71,93,61,28,84,7,79,23,48,39,27,48,79};
//        int nums1[]=new int[]{10,4,1,4,2,9};
//        int nums2[]=new int[]{3,5,9,1,7,4};
//        System.out.println(minAbsoluteSumDiff(nums1, nums2));

        //Bài này tư duy như sau:
        //1, Đối với mỗi phần xét nums1[i]-nums2[i]
        //Đối với nums2[i] ==> Ta sẽ cần phải xác định số gần nhất mà:
        //+ Lớn hơn nums2[i] mà nó ở trong nums1[i] --> Để abs(nums2[i]-nums1[i]) MIN.
        //+ Nhỏ hơn nums2[i] mà nó ở trong nums1[i] --> Để abs(nums2[i]-nums1[i]) MIN.
        //==> Để có thể search trong nums1[] nhanh nhất ta có thể:
        //- Sort nums1
        //==> Ta cần dùng implement custom method để tìm điểm thỏa mãn.
        //- Add các phần tử nums1 vào TreeSet để sắp xếp trước
        //==> Ta có thể dùng ceil, floor method để tìm điểm thỏa mãn.
        System.out.println(minAbsoluteSumDiffOptimized(nums1, nums2));
    }
}
