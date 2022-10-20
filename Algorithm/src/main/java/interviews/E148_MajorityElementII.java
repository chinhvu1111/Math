package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class E148_MajorityElementII {

    public static Random random=new Random();
    public static List<Integer> rs;

    public static List<Integer> majorityElement(int[] nums) {
        rs=new ArrayList<>();
        findMajorElement(nums, 0, nums.length-1);
        return rs;
    }

    public static void findMajorElement(int[] nums, int left, int right){
//        System.out.println(left+" "+right);
//        if(left==right){
//            if(1>nums.length/3){
//                rs.add(nums[left]);
//            }
//            return;
//        }
//        if(left==right-1){
//            if(nums[left]==nums[right]&&2>nums.length/3){
//                rs.add(nums[left]);
//            }else if(1>nums.length/3){
//                rs.add(nums[left]);
//            }
////            return;
//        }
//        System.out.printf("%s %s, ", left, right);
        int pivot=left + random.nextInt(right - left + 1);
        swap(nums, pivot, right);
        int n=nums.length;

        pivot=right;
        int currentIndex=left;
        int currentValue=nums[pivot];

        for(int i=left;i<right;i++){
            if(nums[i]==nums[pivot]){
                swap(nums, currentIndex++, i);
            }
        }
        swap(nums, currentIndex, right);
        if(currentIndex-left+1>n/3){
            rs.add(currentValue);
        }
        if(currentIndex>=n-1){
            return;
        }
        findMajorElement(nums, currentIndex+1, right);
    }

    public static void swap(int[] nums, int i, int j){
        int tem=nums[i];
        nums[i]=nums[j];
        nums[j]=tem;
    }

    public static void main(String[] args) {
        //2,1 ==> Số =2 --> Count =1
        //
//        int[] nums=new int[]{1,2};
//        int[] nums=new int[]{3,2,3};
        int[] nums=new int[]{1};
//        int[] nums=new int[]{2,2};
        System.out.println(majorityElement(nums));
        //
        //** Đê bài:
        //- Tìm tất cả các phần tử có số lần xuất hiện lớn hơn n/3
        //
        //** Bài này tư duy như sau:
        //Cách 1:
        //- Quick select:
        //1,
        //1.1,
        //- pivot= right
        //- nums[pivot] chính là gốc để so sánh với các phần tử bên trái
        //==> Ta đế sẽ chia array thành 2 phần
        // (số lớn == nums[pivot]) (Số != nums[pivot]
        //1.2, Ta sẽ chia ra tìm count của từng số 1 + (Cut đi việc traverse) bằng cách replace all (các phần tử đang xét ==)
        //==> Về phía đầu array:
        //VD: 2,2,2,2,left=(currentIndex), (!=2) (right)
        //- Sau đó chọn gốc là cuối --> traverse tiếp cho đến khi hết Array
        //1.3, Điều kiện dừng
        //- currentIndex >= n-1 <=> currentIndex+1 >n-1
        //1.4, Điều kiện lấy result
        //- currentIndex - left + 1 > n/3 (Chính là kết quả cần tìm)
        //
        //1.5, Lỗi sai ==> Ở điều kiện dừng, với dạng bài này không cần xét việc
        //- left == right-1 ==> Đều loạn và sai
        //- luôn (i=left, i<right) ==> recursion tiếp = (currentIndex + 1) ==> Vì tính cả số nums[pivot] == chính nó = nums[currentIndex+1]
        //==> Nên cần bỏ vị trí currentIndex không xét nữa --> Tất nhiên còn tùy bài.
        //Cách 2:
        //2, Boyer-Moore Majority Vote
        //??? --> Cần xem thêm
        //
    }
}
