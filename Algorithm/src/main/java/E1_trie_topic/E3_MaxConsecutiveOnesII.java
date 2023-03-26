package E1_trie_topic;

import java.util.ArrayList;
import java.util.List;

public class E3_MaxConsecutiveOnesII {

    public static int findMaxConsecutiveOnes(int[] nums) {
        int n=nums.length;
        int[] left=new int[n+1];
        int[] right=new int[n+1];
        int sumLeft=0;
        int sumRight=0;
        int rs=0;
        List<Integer> listIndexZeros=new ArrayList<>();

        for(int i=1;i<=n;i++){
            if(nums[i-1]==0){
                listIndexZeros.add(i-1);
                sumLeft=0;
            }
            if(nums[n-i]==0){
                sumRight=0;
            }
            sumLeft+=nums[i-1];
            sumRight+=nums[n-i];
            right[n-i]=sumRight;
            left[i]=sumLeft;
            rs=Math.max(rs, sumLeft);
        }
//        println(left);
//        println(right);
        //1,0,1,1,0
        //0,(1),2,3,(4)
        //0, 1, 0, 1, 2, 0 | 1 --> n
        //0, 0, 1, 1, 0, 0 | n-1 --> -1 ==> Tiến 1 đơn vị (n) --> 0

        for (int currentIndex : listIndexZeros) {
            rs = Math.max(rs, left[currentIndex] + right[currentIndex+1]+1);
        }
        return rs;
    }

    public static int findMaxConsecutiveOnesSlideWindow(int[] nums) {
        int n=nums.length;
        int rs=0;
        int start=0;
        int numZeros=0;
        int prevIndexZero=0;
        int currentNumOne=0;

        for(int i=0;i<n;i++){
            if(nums[i]==0){
                numZeros++;
            }else{
                currentNumOne++;
            }
            if(numZeros==2){
                System.out.printf("%s %s %s\n", prevIndexZero, start, currentNumOne);
                numZeros--;
                currentNumOne-=prevIndexZero-start;
                start=prevIndexZero+1;
                prevIndexZero=i;
            }
            if(nums[i]==0){
                prevIndexZero=i;
            }
            System.out.printf("After %s %s %s\n", prevIndexZero, start, currentNumOne);
            rs=Math.max(rs, currentNumOne);
        }
        if(numZeros!=0){
            rs++;
        }
        //1,0,1,1,0
        //- Khi gặp số 0 khác --> Thay thế số 0 cũ bằng số 0 mới
        //- Trừ đi số số 1 trước số 0 cũ
        return rs;
    }

    public static void println(int[] arr){
        for (int j : arr) {
            System.out.printf("%s, ", j);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {1,0,1,1,0};
//        int[] nums = {1,0,1,1,0,1};
//        int[] nums = {1};
        System.out.println(findMaxConsecutiveOnes(nums));
        //** Đề bài:
        //VD: nums={1,0,1,1,0}
        //- Tìm độ dài lớn nhất của các phần tử 1 liên tiếp --> Khi được phép flip 1 phần tử nào đó từ 0 --> 1
        //
        //** Tư duy như sau:
        //
        //1.
        //1.1, Ý tưởng dynamic grogramming:
        //1,0,1,1,0,1,1,1,1
        //- Từ các số 0 --> Đi sang 2 bên và tìm tổng max nhất
        //+ Việc đi sang 2 bên không cần thiết --> Vì ta có thể cache lại thành 2 array
        //left[I]= nums[0]+ ... + nums[I-1]
        //right[]= nums[i+1]+ ... + nums[n-1]
        //+ Ta sẽ chọn (i) là index sum của (0...i-1)
        //--> Chọn thế này khá phức tạp ==> Nch nên chọn trùng index luôn
        //VD: (i)=sum (0 --> i) cho đơn giản.
        //
        //1.2, Complexity
        //- Time complexity : O(n)
        //- Space complextiY : O(n)
        //
        //2. Method 2
        //2.1,
        //{1,0,1,1,0}
        //- Khi gặp số 0 khác --> Thay thế số 0 cũ bằng số 0 mới ==> Lưu index số 0 cũ + đếm chữ số 0
        //- Check xem (numZeros==2 chưa) ==> Thì thực hiện:
        //+ Trừ đi số số 1 trước số 0 cũ
        //+ update start vị trí bắt đầu tính (subarray)
        //- rs ban đầu sẽ là danh sách số chữ số 1 lớn nhất liên tiếp + (cách nhau bởi số 0/ liên tiếp nhau 1)
        //- Đến cuối nếu numzero!=0 --> tức là có tồn tại số 0 : return rs+1
        //
        //2.2, Complexity:
        //- Time complexity : O(n)
        //- Space complexity : O(1)
        //
        //** Kinh nghiệm:
        //- Mấy bài dạng này thường có format (ĐỂ TRÁNH NHẦM LẪN) :
        //+ if(x==k) --> remove ==>
        //+ Lấy kết quả sau khi remove
        //+ Sau đó sẽ loop tiếp.
        //- Vị trí lấy kết quả còn phụ thuộc vào elements(i) có được lấy hay không:
        //+ Lấy + kết quả valid : lấy result bên dưới
        //+ Lấy + kết quả invalid : ==> Cần reduce để valid --> Mới lấy được.
        //
        System.out.println(findMaxConsecutiveOnesSlideWindow(nums));
        //#Reference:
        //488. Zuma Game
        //2155. All Divisions With the Highest Score of a Binary Array
    }
}
