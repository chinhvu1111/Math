package interviews.book;

import com.google.common.collect.Lists;

import java.util.*;

public class E1_TwoOutOfThree {

    public static List<Integer> listExistInArray(HashSet<Integer> currentSet, int[] nums){
        List<Integer> rs=new ArrayList<>();
        HashSet<Integer> set=new HashSet<>();
        for(Integer i: nums){
            set.add(i);
        }

        for(Integer num:set){
            if(currentSet.contains(num)){
                rs.add(num);
            }
            currentSet.add(num);
        }
        return rs;
    }

    public static List<Integer> twoOutOfThreeHashSet(int[] nums1, int[] nums2, int[] nums3) {
        HashSet<Integer> hashSet=new HashSet<>();
        HashSet<Integer> rs=new HashSet<>();

        rs.addAll(listExistInArray(hashSet, nums1));
        rs.addAll(listExistInArray(hashSet, nums2));
        rs.addAll(listExistInArray(hashSet, nums3));

        return new ArrayList<>(rs);
    }

    public static List<Integer> twoOutOfThreeBitMask(int[] nums1, int[] nums2, int[] nums3) {
        int[] count=new int[101];
        List<Integer> rs=new ArrayList<>();

        for(int num: nums1){
            count[num]|=1;
        }
        for(int num: nums2){
            count[num]|=1<<1;
        }
        for(int num: nums3){
            count[num]|=1<<2;
        }
        for(int i=1;i<=100;i++){
            if(count[i]==3||count[i]==5||count[i]==6||count[i]==7){
                rs.add(i);
            }
        }
        return rs;
    }
    public static void main(String[] args) {
        //#Reference:
        //2033. Minimum Operations to Make a Uni-Value Grid
        //2325. Decode the Message
        //1720. Decode XORed Array
        //271. Encode and Decode Strings
        //775. Global and Local Inversions
        //2251. Number of Flowers in Full Bloom
        //444. Sequence Reconstruction
        int[] nums1 = {1,1,3,2};
        int[] nums2 = {2,3}, nums3 = {3};
        System.out.println(1<<1);
        System.out.println(1<<2);
        System.out.println(twoOutOfThreeBitMask(nums1, nums2, nums3));
        //** Đề bài:
        //- Bài này list ra các phần tử xuất hiện ít nhất trong 2 array đã cho
        //- Chú ý phần tử xuất hiện nhiều lần trên 1 array
        //
        //** Bài này tư duy như sau:
        //Cách 1:
        //1.
        //1.1, Ta sẽ dùng hashset để tìm các
        //- Với mỗi nums --> ta sẽ tìm được các phần tử xuất hiện trong cả nums và hashset hiện tại.
        //+ Cứ mỗi phần tử như thế ta sẽ add vào result
        //** Chú ý: ta distinct từng Array một trước khi xử lý ==> Để tránh 1 phần tử xuất hiện nhiều lần trong 1 array.
        //
        //Cách 2:
        //2. Nhưng bài thường hữu hạn số array --> Bit mask
        //count [101] số
        //2.1,
        //- Với mỗi phần tử ta sẽ tình count dần lên
        //--> nếu count == (tập k) --> Nó xuất hiện nhiều hơn 2 array
        //- Để tránh cases xuất hiện trong cùng array ==> number add vào count[] phải khác nhau trên từng nums
        //- Xuất hiện 2 lần tức là
        //VD: add 1,2,4 lần lượt vào (nums1, nums2, nums3)
        //===> 2 lần array tức là in (3,5,7,6) ==> Làm sao để không trùng với xuất hiện 2 lần trong 1 array như
        //(2,4,8)
        //+ Giả dụ nếu chọn (1,2,3) --> 6 (1,2,3) ==> trùng với (3,3)
        //** Chú ý: quy luật trên
        //00001
        //00010
        //00011
        //2.2, Nếu bài toán thành n array thì sẽ làm như thế nào? Công thức chi tiết cho cách này là gì?
        //-
        //
        //
    }
}
