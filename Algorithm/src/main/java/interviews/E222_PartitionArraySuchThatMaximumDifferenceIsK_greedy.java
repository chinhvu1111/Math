package interviews;

import java.util.Arrays;

public class E222_PartitionArraySuchThatMaximumDifferenceIsK_greedy {

    public static int partitionArray(int[] nums, int k) {
        if(nums.length==0){
            return 0;
        }
        Arrays.sort(nums);
        int n=nums.length;
        int currentValue=nums[0];
        int count=1;

        for(int i=1;i<n;i++){
            if(nums[i]-currentValue>k){
                count++;
                currentValue=nums[i];
            }
        }
        return count;
    }

    public static int partitionArrayRefactor(int[] nums, int k) {
        Arrays.sort(nums);
        int count=0, prev=0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]-nums[prev]<=k){
                continue;
            }
            count++;
            prev=i;
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{3,6,1,2,5};
//        int k=2;
        int[] arr=new int[]{2,2,4,5};
        int k=0;
        System.out.println(partitionArray(arr, k));
        System.out.println(partitionArrayRefactor(arr, k));
        //
        //** Đề bài
        //- Chia 1 array thành các subsequence arrays sao cho
        //- Minimum subsequences
        //- Max - min<=k
        //
        //** Bài này tư duy như sau:
        //1, Phần tử có 3 khả năng xảy ra:
        //- Không đóng vai trò cụ thể --> Có nhiều cases
        //- Min của 1 subsequence
        //- Max của 1 subsequence
        //
        //1.1, - Không đóng vai trò cụ thể --> Có nhiều cases
        //
        //Case 1:
        //- 1 phần tử normal ở tập khác nhưng max/ min ở tập kia
        //VD:
        //3,7,1,2,6,5
        //
        //{7,6,5}, {3,1,2}
        //--> Bê 5 sang {3,1,2} sẽ không thỏa mãn.
        //==> Chỗ này có thể xử lý theo kiểu chọn max- min được
        //
        //Case 2:
        //- 1 phần tử có thể thỏa mãn ở 2 sub sequences nhưng 1 cái sẽ tối ưu hơn cho min và max
        //--> Khi nó sang thì nó sẽ change min/max khíến cho (subsequence còn lại có thể thêm element mới)
        //==> Chỉ có tác dụng khi element x sẽ là phần tử trung gian của sub-1 --> chuyển sang sub-2 sẽ thành (min/max)
        //==> Nhưng khi x là element trung gian tức là min<x<max ==> chuyển sang sub-2 add phần tử ==> sub-1 cũng add được phần tử (Không cần sub-2)
        //
        //==> Như ta thấy thì có thể làm greedy được.
        //
        //Case 3 :
        //- Case số lượng phần tử duplicate --> Không ảnh hưởng đến số lượng subsequences
        //
        //2,
        //1,3,6,1,2,5
        //==> 1,3,6,2,5
        //==> Order không quá quan trọng vì đang làm theo element[i] --> có thể chọn element[j]
        //==> Swap nó đi (i,j) <=> (j,i) là tương đương nhau.
        //Sorting:
        //1,3,6,1,2,5
        //--> 1,1,2,3,5,6
        //
        //3, Cách làm ta sẽ loop bình thường
        //- currentValue = nums[0]
        //- Nếu nums[i] - currentValue > k : count++;
        //- currentValue = nums[i]
        //
        //4, Refactor
        //- Ta sẽ dùng cache lại index trước đó --> chứ không cache
        //- Sau đó sẽ so sánh nums[index] > nums[prevIndex]
    }
}
