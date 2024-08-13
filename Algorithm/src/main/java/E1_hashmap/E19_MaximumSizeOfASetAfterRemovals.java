package E1_hashmap;

import java.util.HashMap;
import java.util.HashSet;

public class E19_MaximumSizeOfASetAfterRemovals {

    public static int maximumSetSize(int[] nums1, int[] nums2) {
        int n=nums1.length;
        HashMap<Integer, Integer> map1=new HashMap<>();
        HashSet<Integer> set1=new HashSet<>();
        HashSet<Integer> set2=new HashSet<>();
        HashMap<Integer, Integer> map2=new HashMap<>();
        HashSet<Integer> rs1=new HashSet<>();
        HashSet<Integer> rs2=new HashSet<>();

        for(int i=0;i<n;i++){
            map1.put(nums1[i], map1.getOrDefault(nums1[i], 0)+1);
            set1.add(nums1[i]);
            map2.put(nums2[i], map2.getOrDefault(nums2[i], 0)+1);
            set2.add(nums2[i]);
        }
        int remainingLength1=0;
        int remainingLength2=0;
        int rs=0;

        for(int i=0;i<n;i++){
            if(!map2.containsKey(nums1[i])&&remainingLength1<n/2&&!rs1.contains(nums1[i])){
//                if(map1.get(nums1[i])==1){
//                    map1.remove(nums1[i]);
//                }else{
//                    map1.put(nums1[i], map1.get(nums1[i])-1);
//                }
                rs1.add(nums1[i]);
                set1.remove(nums1[i]);
                remainingLength1++;
            }
            if(!map1.containsKey(nums2[i])&&remainingLength2<n/2&&!rs2.contains(nums2[i])){
//                if(map2.get(nums2[i])==1){
//                    map2.remove(nums2[i]);
//                }else{
//                    map2.put(nums2[i], map2.get(nums2[i])-1);
//                }
                set2.remove(nums2[i]);
                rs2.add(nums2[i]);
                remainingLength2++;
            }
        }
        rs+=remainingLength1+remainingLength2;
        if(remainingLength1==n/2&&remainingLength2==n/2){
            return rs;
        }
        if(set1.size()+remainingLength1<=n/2){
            for(int e: set1){
                if(!rs2.contains(e)){
                    rs++;
                    rs2.add(e);
                }
            }
            //==> Ntn là thêm all rồi
            //Vì đã all set1 ==> Các phần tử của set2 là include trong set1 ==> không cần thêm
        }else if(set2.size()+remainingLength2<=n/2){
            for(int e: set2){
                if(!rs1.contains(e)){
                    rs++;
                    rs1.add(e);
                }
            }
        }else{
            //Ex:
            //set1: 1,4,5,6
            //set1: 6,4,5,1
            //* Chắc chắn len(set1) == len(set2)
            //  + Vì 2 thằng contains lẫn nhau.
            //Thêm để đủ n/2
            //- Ta sẽ ưu tiên chọn distinct
            //  + Sao cho đủ ==> cùng lắm bằng size(set1/set2)
            if(n/2-remainingLength1+n/2-remainingLength2>=set1.size()){
                for(int e: set1){
                    if(!rs2.contains(e)){
                        rs++;
                        rs2.add(e);
                    }
                }
            }else{
                for(int e: set1){
                    if(!rs1.contains(e)&&!rs2.contains(e)&&remainingLength1<n/2){
                        rs++;
                        rs1.add(e);
                        remainingLength1++;
                    }
                }
                for(int e: set2){
                    if(!rs1.contains(e)&&!rs2.contains(e)&&remainingLength2<n/2){
                        rs++;
                        rs2.add(e);
                        remainingLength2++;
                    }
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two 0-indexed integer arrays (nums1) and (nums2) of ("even" length n).
        //- You must remove ((n / 2) elements from nums1) and ((n / 2) elements from nums2).
        //- After the removals, you insert (the remaining elements) of (nums1) and (nums2) into (a set s).
        //* Return (the maximum possible size) of the (set s).
        //  + Set ==> distinct
        //* Tức là tìm size lớn nhất khi add 1/2 (array nums1 và nums2)
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //n == nums1.length == nums2.length
        //1 <= n <= 2 * 10^4
        //  + n khá lớn ==> Time: O(n*k)
        //n is even.
        //1 <= nums1[i], nums2[i] <= 10^9
        //  + Số khá lớn ==> Long
        //  + Ta cũng có thể dùng binary search.
        //
        //- Brainstorm
        //Example 1:
        //
        //Input: nums1 = [1,2,1,2], nums2 = [1,1,1,1]
        //Output: 2
        //- Cut 1/2 length của nums1
        //  + Tức là ta có thể cut tuỳ ý theo tỉ lệ nào cũng được, ngay cả case 1 xuất hiện 5 lần ==> Nhưng ta chỉ lấy 3 lần
        //- Combine 2 thằng thành 2 hashset
        //- Sort nums1 và nums2 theo:
        //  + Count của từng character ==> sort giảm dần
        //- Chọn 1 trong 2:
        //  + Vì đã distinct rồi thì ==> chọn 1 trong 2 thì ==> Đằng sau sẽ không xuất hiện nữa.
        //  + Count càng nhỏ ==> Ưu tiên lấy trước
        //      + ????
        //
        //- Ưu tiên những thằng (không giao nhau trước)
        //  ==> xuất hiện trong set1 ==> không có trong set2
        //- Sau đó còn thừa bao nhiêu thì mỗi set1 và set2 sẽ tự tính:
        //  + Sau đó ta sẽ ưu tiên các remaining elements ==> Để tăng số lượng phần tử distincts
        //  + Nếu remaining phần tử không đủ ==> Ta sẽ quay sang các phần tử mà distinct đã tính lần đầu tiên bù vào.
        //
        //Ex:
        //Input: nums1 = [1,2,1,2], nums2 = [1,1,1,1]
        //set1 = [1,2]
        //set2 = [1]
        //- Với mỗi elements của set1 / set2
        //  + Chỉ lấy 1 phần tử ==> Cho đến khi đủ n/2
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
//        int[] nums1 = {1,2,3,4,5,6}, nums2 = {2,3,2,3,2,3};
//        int[] nums1 = {1,2,1,2}, nums2 = {1,1,1,1};
        int[] nums1 = {10,8,7,9}, nums2 = {7,9,9,5};
        System.out.println(maximumSetSize(nums1, nums2));
        //
        //#Reference:
        //2718. Sum of Matrix After Queries
        //2559. Count Vowel Strings in Ranges
        //896. Monotonic Array
    }
}
